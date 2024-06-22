package top.xinstudio.xinxin.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import top.xinstudio.xinxin.BetterSynthesis;
import top.xinstudio.xinxin.block.ModBlocks;

public class ModBlockEntities {
    public static final BlockEntityType<CookingPotBlockEntity> COOKING_POT_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(BetterSynthesis.MOD_ID,"cooking_pot_block_entity"),
            FabricBlockEntityTypeBuilder.create(CookingPotBlockEntity::new, ModBlocks.ITEM_COOKINGSTOVES).build()
    );

    public static void registerBlockEntities(){

    }
}
