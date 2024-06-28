package top.xinstudio.xinxin.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import top.xinstudio.xinxin.BetterSynthesis;
import top.xinstudio.xinxin.block.ModBlocks;

public class ModItems {
    public static final Item ITEM_HardboiledEggs = registerItem("item_hardboiled_egg", new Item(new FabricItemSettings().food(ModFoodComponents.ITEM_HardboiledEggs)));


    public static final Item ITEM_Cabbage = registerItem("item_cabbage",new Item(new FabricItemSettings().food(ModFoodComponents.ITEM_Cabbage)));

    public static final Item ITEM_Cabbage_SEEDS = registerItem("item_cabbage_seeds",new AliasedBlockItem(ModBlocks.Cabbage_CROP,new FabricItemSettings()));

    public static final Item ITEM_Cabbage_RESULT = registerItem("item_cabbage_result",new AliasedBlockItem(ModBlocks.Cabbage_CROP,new FabricItemSettings()));

    public static final Item ITEM_CraftingTable = registerItem("item_crafting_table", new Item(new FabricItemSettings()));

//    private static void addItemsToIG(FabricItemGroupEntries entries) {
//        entries.add(ITEM_HardboiledEggs);
//    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BetterSynthesis.MOD_ID, name), item);
    }
    public static void registerModItems() {
//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemsToIG);
    }
}
