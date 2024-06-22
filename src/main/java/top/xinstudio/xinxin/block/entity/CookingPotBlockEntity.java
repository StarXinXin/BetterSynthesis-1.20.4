package top.xinstudio.xinxin.block.entity;


import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import top.xinstudio.xinxin.item.ModItems;
import top.xinstudio.xinxin.recipe.CookingRecipe;
import top.xinstudio.xinxin.screen.CookingPotScreenHandler;

import java.util.ArrayList;
import java.util.List;

public class CookingPotBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private static final int FUEL_SLOT = 2;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 100;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    private final List<CookingRecipe> recipes = new ArrayList<>();

    public CookingPotBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COOKING_POT_BLOCK_ENTITY, pos, state);
        recipes.add(new CookingRecipe(Items.EGG, ModItems.ITEM_HardboiledEggs, 1));



        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CookingPotBlockEntity.this.progress;
                    case 1 -> CookingPotBlockEntity.this.maxProgress;
                    case 2 -> CookingPotBlockEntity.this.fuelTime;
                    case 3 -> CookingPotBlockEntity.this.maxFuelTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CookingPotBlockEntity.this.progress = value;
                    case 1 -> CookingPotBlockEntity.this.maxProgress = value;
                    case 2 -> CookingPotBlockEntity.this.fuelTime = value;
                    case 3 -> CookingPotBlockEntity.this.maxFuelTime = value;
                }
            }

            @Override
            public int size() {
                return 4;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.CookingStoves");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CookingPotScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("cooking_pot_progress", progress);
        nbt.putInt("cooking_pot_fuel_time", fuelTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("cooking_pot_progress");
        fuelTime = nbt.getInt("cooking_pot_fuel_time");
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }

        if (fuelTime > 0) {
            fuelTime--;
        }

        if (isOutputSlotAvailable()) {
            if (this.hasRecipe() && this.hasFuel()) {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if (hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }

        if (fuelTime <= 0 && this.hasFuelItem()) {
            this.consumeFuel();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        ItemStack inputStack = getStack(INPUT_SLOT);
        for (CookingRecipe recipe : recipes) {
            if (recipe.getInputItem() == inputStack.getItem()) {
                removeStack(INPUT_SLOT, 1); // Remove input item
                ItemStack outputStack = new ItemStack(recipe.getOutputItem(), recipe.getOutputAmount());
                setStack(OUTPUT_SLOT, new ItemStack(outputStack.getItem(), getStack(OUTPUT_SLOT).getCount() + outputStack.getCount()));
                markDirty();
                break; // Exit loop after finding and crafting the recipe
            }
        }
    }

    private boolean hasRecipe() {
        ItemStack inputStack = getStack(INPUT_SLOT);
        for (CookingRecipe recipe : recipes) {
            if (recipe.getInputItem() == inputStack.getItem() && canInsertAmountIntoOutputSlot(new ItemStack(recipe.getOutputItem(), recipe.getOutputAmount())) && canInsertItemIntoOutputSlot(recipe.getOutputItem())) {
                return true; // Found a matching recipe
            }
        }
        return false; // No matching recipe found
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }


    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean isOutputSlotAvailable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean hasFuel() {
        return fuelTime > 0;
    }

    private boolean hasFuelItem() {
        return !getStack(FUEL_SLOT).isEmpty();
    }

    private void consumeFuel() {
        ItemStack fuelStack = getStack(FUEL_SLOT);
        if (!fuelStack.isEmpty()) {
            if (isValidFuel(fuelStack)) {
                fuelTime = 205; // 假设每个有效的燃料项目燃烧 200 个刻度
                maxFuelTime = fuelTime;
                fuelStack.decrement(1);
            }
        }
    }

    private boolean isValidFuel(ItemStack stack) {
        //在此处实现您的逻辑，以检查给定的 ItemStack 是否是有效的燃料项
        //示例：接受木材（例如橡木或木炭）或煤炭
        Item item = stack.getItem();
        return item == Items.OAK_LOG || item == Items.CHARCOAL || item == Items.COAL;
    }
}