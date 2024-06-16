package top.xinstudio.xinxin;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.xinstudio.xinxin.block.ModBlocks;
import top.xinstudio.xinxin.item.ModItemGroup;
import top.xinstudio.xinxin.item.ModItems;
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

		LOGGER.info("Hello Fabric world!");
	}
}