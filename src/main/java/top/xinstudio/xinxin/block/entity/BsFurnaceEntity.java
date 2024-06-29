package top.xinstudio.xinxin.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import top.xinstudio.xinxin.block.BsFurnace;
import top.xinstudio.xinxin.recipe.BsFurnaceRecipe;
import top.xinstudio.xinxin.screen.BsFurnaceScreenHandler;

import java.util.Objects;
import java.util.Optional;

public class BsFurnaceEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 2;
    public static final int FUEL_SLOT = 1;

    public static boolean hasFuel = false;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 200;
    private int fuelTime = 0;

    public BsFurnaceEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLOCK_BSFURNACE_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BsFurnaceEntity.this.progress;
                    case 1 -> BsFurnaceEntity.this.maxProgress;
                    case 2 -> BsFurnaceEntity.this.fuelTime;
//                    case 3 -> CookingPotBlockEntity.this.maxFuelTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BsFurnaceEntity.this.progress = value;
                    case 1 -> BsFurnaceEntity.this.maxProgress = value;
                    case 2 -> BsFurnaceEntity.this.fuelTime = value;
//                    case 3 -> CookingPotBlockEntity.this.maxFuelTime = value;
                }
            }

            @Override
            public int size() {
                return 3;
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
        return Text.translatable("container.BsFurnace");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BsFurnaceScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("bs_furnace_progress", progress);
        nbt.putInt("bs_furnace_fuel_time", fuelTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("bs_furnace_progress");
        fuelTime = nbt.getInt("bs_furnace_fuel_time");
    }


    public void tick(World world, BlockPos pos, BlockState state) {

        if (world.isClient()) {
            return;
        }

        hasFuel = this.hasFuelItem();

        if (fuelTime <= 0 && this.hasFuelItem()) {
            this.consumeFuel();
        }

        if (BsSaucepanEntity.zhizhuo == 8) {
            BsSaucepanEntity.zhizhuo = 0;
            ItemStack fuelStack = getStack(FUEL_SLOT);
            fuelStack.decrement(1);
        }


        if (BsSaucepanEntity.iswork) {
            world.setBlockState(pos, world.getBlockState(pos).with(BsFurnace.LIT, true));
        } else {
            world.setBlockState(pos, world.getBlockState(pos).with(BsFurnace.LIT, false));
        }

        if (isInputSlotAvailable()) {
            if (isOutputSlotAvailable()) {
                if (this.hsaRecipe() && this.hasFuel()) {
                    world.setBlockState(pos, world.getBlockState(pos).with(BsFurnace.LIT, true));
                    this.increaseCraftProgress();
                    markDirty(world, pos, state);

                    if (fuelTime > 0) {
                        fuelTime--;
                    }

                    if (hasCraftingFinished()) {
                        this.craftItem();
                        this.resetProgress();
                    }
                } else {
                    this.resetProgress();
                    markDirty(world, pos, state);
                    world.setBlockState(pos, world.getBlockState(pos).with(BsFurnace.LIT, false));
                }
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    @Override
    public void markDirty() {
        assert world != null;
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }


    private void resetProgress() {
        this.progress = 0;
    }


    private void craftItem() {
        this.removeStack(INPUT_SLOT, 1);

        Optional<RecipeEntry<BsFurnaceRecipe>> recipe = getCurrentRecipe();

        if (recipe.isPresent()) {
            ItemStack result = recipe.get().value().getResult(null);
            ItemStack outputStack = this.getStack(OUTPUT_SLOT);
            int newCount = outputStack.getCount() + result.getCount();
            this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), newCount));
        } else {
            System.err.println("在 BsFurnaceEntity 中找不到当前输入的配方。");
        }
    }


    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hsaRecipe() {
        Optional<RecipeEntry<BsFurnaceRecipe>> recipe = getCurrentRecipe();

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null)) &&
                canInsertItemIntoOutputSlot(recipe.get().value().getResult(null).getItem());
    }

    private Optional<RecipeEntry<BsFurnaceRecipe>> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for (int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }
        return Objects.requireNonNull(getWorld()).getRecipeManager().getFirstMatch(BsFurnaceRecipe.Type.INSTANCE, inv, getWorld());
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

    private boolean isInputSlotAvailable() {
        return !getStack(INPUT_SLOT).isEmpty();
    }

    private boolean hasFuel() {
        return fuelTime > 0;
    }

    public boolean hasFuelItem() {
        return !getStack(FUEL_SLOT).isEmpty();
    }

    private void consumeFuel() {
        ItemStack fuelStack = getStack(FUEL_SLOT);
        if (!fuelStack.isEmpty()) {
            if (isValidFuel(fuelStack)) {
                if (fuelStack.getItem() == Items.COAL) {
                    fuelTime = 1600;
                } else if (fuelStack.getItem() == Items.CHARCOAL) {
                    fuelTime = 1600;
                } else if (fuelStack.getItem() == Items.COAL) {
                    fuelTime = 1600;
                }
                fuelStack.decrement(1);
            }
        }
    }


    private boolean isValidFuel(ItemStack stack) {
        Item item = stack.getItem();
        return item == Items.OAK_LOG || item == Items.CHARCOAL || item == Items.COAL;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

}

