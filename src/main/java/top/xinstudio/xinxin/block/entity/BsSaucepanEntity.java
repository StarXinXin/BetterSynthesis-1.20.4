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
import top.xinstudio.xinxin.recipe.BsSaucepanRecipe;
import top.xinstudio.xinxin.screen.BsSaucepanScreenHandler;

import java.util.Objects;
import java.util.Optional;

public class BsSaucepanEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    public static boolean iswork = false;

    protected final PropertyDelegate propertyDelegate;

    public static int zhizhuo = 0;

//    private static final int FUEL_SLOT = 1;
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);
    private int progress = 0;
    private int maxProgress = 72;
//    private int fuelTime = 0;

    public BsSaucepanEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLOCK_BSSAUCEPAN_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BsSaucepanEntity.this.progress;
                    case 1 -> BsSaucepanEntity.this.maxProgress;
//                    case 2 -> BsSaucepanEntity.this.fuelTime;
//                    case 3 -> CookingPotBlockEntity.this.maxFuelTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BsSaucepanEntity.this.progress = value;
                    case 1 -> BsSaucepanEntity.this.maxProgress = value;
//                    case 2 -> BsSaucepanEntity.this.fuelTime = value;
//                    case 3 -> CookingPotBlockEntity.this.maxFuelTime = value;
                }
            }

            @Override
            public int size() {
                return 2;
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
        return Text.translatable("container.BsSaucepan");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BsSaucepanScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("bs_saucepan_progress", progress);
//        nbt.putInt("bs_saucepan_fuel_time", fuelTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("bs_saucepan_progress");
//        fuelTime = nbt.getInt("bs_saucepan_fuel_time");
    }


    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }

//        if (fuelTime <= 0 && this.hasFuelItem()) {
//            this.consumeFuel();
//        }

        if (isInputSlotAvailable()) {
            if (isOutputSlotAvailable()) {
                if (this.hsaRecipe() && hasFuel()) {

                    this.increaseCraftProgress();
                    markDirty(world, pos, state);
                    iswork = true;
//                    if (fuelTime > 0) {
//                        fuelTime--;
//                    }

                    if (hasCraftingFinished()) {
                        this.craftItem();
                        this.resetProgress();
                    }
                } else {
                    this.resetProgress();
                    markDirty(world, pos, state);
                    iswork = false;
                }
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
            iswork = false;
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

    // 检查燃料槽是否有燃料
    public boolean hasFuel() {
        if (BsFurnaceEntity.hasFuel) {
//            BetterSynthesis.LOGGER.info("ucket");
            return BsFurnaceEntity.hasFuel;
        } else {
//            BetterSynthesis.LOGGER.info("kong");
            return BsFurnaceEntity.hasFuel;
        }
    }


    private void craftItem() {
        this.removeStack(INPUT_SLOT, 1);
        
        Optional<RecipeEntry<BsSaucepanRecipe>> recipe = getCurrentRecipe();

        if (recipe.isPresent()) {


            ItemStack result = recipe.get().value().getResult(null);
            ItemStack outputStack = this.getStack(OUTPUT_SLOT);
            int newCount = outputStack.getCount() + result.getCount();
            this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), newCount));

            zhizhuo++;
            System.err.println(zhizhuo);


        } else {
            System.err.println("在 BsSaucepanEntity 中找不到当前输入的配方。");
        }


    }


    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hsaRecipe() {
        Optional<RecipeEntry<BsSaucepanRecipe>> recipe = getCurrentRecipe();

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null)) &&
                canInsertItemIntoOutputSlot(recipe.get().value().getResult(null).getItem());
    }

    private Optional<RecipeEntry<BsSaucepanRecipe>> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for (int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }
        return Objects.requireNonNull(getWorld()).getRecipeManager().getFirstMatch(BsSaucepanRecipe.Type.INSTANCE, inv, getWorld());
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

