package top.xinstudio.xinxin.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import top.xinstudio.xinxin.BetterSynthesis;

public class ModBlocks {

    public static final Block ITEM_COOKINGSTOVES = registerBlocks("cooking_pot", new CookingPotBlock(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).instrument(Instrument.BASEDRUM).strength(3.5f).luminance(Blocks.createLightLevelFromLitBlockState(13))));
//    public static final Block ITEM_COOKINGSTOVES = registerBlocks("cooking_pot", (Block) new CookingPotBlock(FabricBlockSettings.copyOf(Blocks.CAULDRON)));




    private static Block registerBlocks(String name,Block block){
        registerBlockItems(name,block);
        return Registry.register(Registries.BLOCK,new Identifier(BetterSynthesis.MOD_ID,name),block);
    }
    private static Item registerBlockItems(String name, Block block){
        return Registry.register(Registries.ITEM,new Identifier(BetterSynthesis.MOD_ID,name),
                new BlockItem(block,new FabricItemSettings()));
    }

    public static void registerModBlocks(){

    }
}
