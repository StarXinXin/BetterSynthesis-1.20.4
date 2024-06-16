package top.xinstudio.xinxin.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import top.xinstudio.xinxin.BetterSynthesis;

public class ModItems {
    public static final Item ITEM_HardboiledEggs = registerItem("item_hardboiled_egg", new Item(new FabricItemSettings().food(ModFoodComponents.ITEM_HardboiledEggs)));
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
