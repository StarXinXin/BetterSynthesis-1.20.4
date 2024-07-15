package top.xinstudio.xinxin;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.xinstudio.xinxin.block.ModBlocks;
import top.xinstudio.xinxin.block.entity.ModBlockEntities;
import top.xinstudio.xinxin.enchantments.ModEnchantments;
import top.xinstudio.xinxin.item.ModItemGroup;
import top.xinstudio.xinxin.item.ModItems;
import top.xinstudio.xinxin.recipe.ModRecipes;
import top.xinstudio.xinxin.screen.ModScreenHandlers;
import top.xinstudio.xinxin.util.ModLootTableModifiers;
import top.xinstudio.xinxin.util.ModTrades;

public class BetterSynthesis implements ModInitializer {
    public static final String MOD_ID = "better-synthesis";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    @Override
    public void onInitialize() {
        ModItemGroup.registerModItemGroup();
        ModItems.registerModItems();
        ModTrades.registerTrades();
        ModBlocks.registerModBlocks();

        ModBlockEntities.registerBlockEntities();

        ModScreenHandlers.registerScreenHandlers();

        ModRecipes.registerRecipes();

        ModEnchantments.registerEnchantments();

        ModLootTableModifiers.modifierLootTables();
        LOGGER.info("Hello Fabric World! 摘星辰 - 更好的合成");

    }

}