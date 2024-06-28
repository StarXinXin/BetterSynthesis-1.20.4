package top.xinstudio.xinxin.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import top.xinstudio.xinxin.block.CabbageCropBlock;
import top.xinstudio.xinxin.block.ModBlocks;
import top.xinstudio.xinxin.item.ModItems;

public class ModModelsProvider extends FabricModelProvider {
    public ModModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleState(ModBlocks.BLOCK_BSFURNACE);
        blockStateModelGenerator.registerSimpleState(ModBlocks.BLOCK_SAUCEPAN);
        blockStateModelGenerator.registerCrop(ModBlocks.Cabbage_CROP, CabbageCropBlock.AGE,0,1,2,3,4,5,6,7);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ITEM_Cabbage, Models.GENERATED);
    }
}
