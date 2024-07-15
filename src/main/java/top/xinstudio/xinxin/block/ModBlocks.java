package top.xinstudio.xinxin.block;


import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import top.xinstudio.xinxin.BetterSynthesis;

public class ModBlocks {

    public static final Block BLOCK_BSFURNACE = registerBlocks("bs_furnace",
            new BsFurnace(AbstractBlock.Settings.create()));

    public static final Block BLOCK_SAUCEPAN = registerBlocks("bs_saucepan",
            new BsSaucepan(AbstractBlock.Settings.create()));


    public static final Block Cabbage_CROP = Registry.register(Registries.BLOCK,new Identifier(BetterSynthesis.MOD_ID,"cabbage_crop"),
            new CabbageCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));

    private static Block registerBlocks(String name, Block block) {
        registerBlockItems(name, block,64);
        return Registry.register(Registries.BLOCK, new Identifier(BetterSynthesis.MOD_ID, name), block);
    }

    private static Item registerBlockItems(String name, Block block, int maxCount) {
        return Registry.register(Registries.ITEM, new Identifier(BetterSynthesis.MOD_ID, name),
                new BlockItem(block, new Item.Settings().maxCount(maxCount)));
    }

    public static void registerModBlocks() {

    }
}
