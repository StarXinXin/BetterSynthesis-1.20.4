package top.xinstudio.xinxin.block;


import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import top.xinstudio.xinxin.block.entity.CookingPotBlockEntity;
import top.xinstudio.xinxin.block.entity.ModBlockEntities;

public class CookingPotBlock extends BlockWithEntity  implements BlockEntityProvider {


    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.d, .0d, 2.d, 14.d, 10.d, 14.d);

    public CookingPotBlock(Settings luminance) {
        super(Settings.create().mapColor(MapColor.IRON_GRAY).hardness(.5f).resistance(6.f).sounds(BlockSoundGroup.LANTERN));
    }

    public static final MapCodec<CookingPotBlock> CODEC = CookingPotBlock.createCodec(CookingPotBlock::new);

    public MapCodec<CookingPotBlock> getCodec() {
        return CODEC;
    }


    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CookingPotBlockEntity(pos, state);
    }


    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof CookingPotBlockEntity) {
                ItemScatterer.spawn(world, pos, (CookingPotBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((CookingPotBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }



    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.COOKING_POT_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
        /*
         * 在1.20.2中，checkType对应validateTicker
         * */
    }


}