package top.xinstudio.xinxin;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import top.xinstudio.xinxin.datagen.ModLootTablesProvider;
import top.xinstudio.xinxin.datagen.ModModelsProvider;
import top.xinstudio.xinxin.datagen.ModTagsProvider;

public class BetterSynthesisDataGenerator implements DataGeneratorEntrypoint {


	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelsProvider::new);
		pack.addProvider(ModTagsProvider::new);
		pack.addProvider(ModLootTablesProvider::new);

	}
}
