package top.xinstudio.xinxin.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import top.xinstudio.xinxin.BetterSynthesis;
import top.xinstudio.xinxin.block.ModBlocks;

public class ModBlockEntities {


    public static final BlockEntityType<BsFurnaceEntity> BLOCK_BSFURNACE_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(BetterSynthesis.MOD_ID, "bs_furnace"),
                    FabricBlockEntityTypeBuilder.create(BsFurnaceEntity::new,
                            ModBlocks.BLOCK_BSFURNACE).build());


    public static final BlockEntityType<BsSaucepanEntity> BLOCK_BSSAUCEPAN_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(BetterSynthesis.MOD_ID, "bs_saucepan"),
                    FabricBlockEntityTypeBuilder.create(BsSaucepanEntity::new,
                            ModBlocks.BLOCK_SAUCEPAN).build());


    public static void registerBlockEntities(){

    }
}
