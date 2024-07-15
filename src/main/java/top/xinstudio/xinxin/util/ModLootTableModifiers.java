package top.xinstudio.xinxin.util;


import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.loot.condition.RandomChanceLootCondition;

import com.google.gson.JsonObject;
import top.xinstudio.xinxin.enchantments.ModEnchantments;

public class ModLootTableModifiers {
    private static final Identifier JUNGLE_TEMPLE_ID =
            new Identifier("minecraft", "chests/jungle_temple");
    //丛林神庙

    private static final Identifier ANCIENT_CITY_ID =
            new Identifier("minecraft", "chests/ancient_city");
    //古代城市

//    private static final Identifier SPAWN_BONUS_CHEST_ID =
//            new Identifier("minecraft", "chests/spawn_bonus_chest");
//    //出生点

    private static final Identifier STRONGHOLD_CORRIDOR_ID =
            new Identifier("minecraft", "chests/stronghold_corridor");
    //要塞走廊

    private static final Identifier STRONGHOLD_CROSSING_ID =
            new Identifier("minecraft", "chests/stronghold_crossing");
    //要塞储藏室

    private static final Identifier STRONGHOLD_LIBRARY_ID =
            new Identifier("minecraft", "chests/stronghold_library");
    //要塞图书馆

    private static final Identifier WOODLAND_MANSION_ID =
            new Identifier("minecraft", "chests/woodland_mansion");
    //林地府邸

    private static final Identifier SIMPLE_DUNGEON_ID =
            new Identifier("minecraft", "chests/simple_dungeon");
    //地牢

    private static final Identifier ABANDED_MINESHAFT_ID =
            new Identifier("minecraft", "chests/abandoned_mineshaft");
    //废弃矿井

    private static final Identifier DESERT_PYRAMID_ID =
            new Identifier("minecraft", "chests/desert_pyramid");
    //沙漠神殿

    private static final Identifier PILLAGER_OUTPOST_ID =
            new Identifier("minecraft", "chests/pillager_outpost");
    //掠夺者哨塔
    public static void modifierLootTables() {

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (JUNGLE_TEMPLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.28f))
                        .with(ItemEntry.builder(Items.ENCHANTED_BOOK)
                                .apply(SetNbtLootFunction.builder(createEnchantedBook().getNbt()))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (ANCIENT_CITY_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.28f))
                        .with(ItemEntry.builder(Items.ENCHANTED_BOOK)
                                .apply(SetNbtLootFunction.builder(createEnchantedBook().getNbt()))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (STRONGHOLD_CORRIDOR_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.28f))
                        .with(ItemEntry.builder(Items.ENCHANTED_BOOK)
                                .apply(SetNbtLootFunction.builder(createEnchantedBook().getNbt()))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (STRONGHOLD_CROSSING_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.28f))
                        .with(ItemEntry.builder(Items.ENCHANTED_BOOK)
                                .apply(SetNbtLootFunction.builder(createEnchantedBook().getNbt()))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (STRONGHOLD_LIBRARY_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.28f))
                        .with(ItemEntry.builder(Items.ENCHANTED_BOOK)
                                .apply(SetNbtLootFunction.builder(createEnchantedBook().getNbt()))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (WOODLAND_MANSION_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1494f))
                        .with(ItemEntry.builder(Items.ENCHANTED_BOOK)
                                .apply(SetNbtLootFunction.builder(createEnchantedBook().getNbt()))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (SIMPLE_DUNGEON_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.28f))
                        .with(ItemEntry.builder(Items.ENCHANTED_BOOK)
                                .apply(SetNbtLootFunction.builder(createEnchantedBook().getNbt()))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (ABANDED_MINESHAFT_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.28f))
                        .with(ItemEntry.builder(Items.ENCHANTED_BOOK)
                                .apply(SetNbtLootFunction.builder(createEnchantedBook().getNbt()))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (DESERT_PYRAMID_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.2349f))
                        .with(ItemEntry.builder(Items.ENCHANTED_BOOK)
                                .apply(SetNbtLootFunction.builder(createEnchantedBook().getNbt()))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (PILLAGER_OUTPOST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.28f))
                        .with(ItemEntry.builder(Items.ENCHANTED_BOOK)
                                .apply(SetNbtLootFunction.builder(createEnchantedBook().getNbt()))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }

    public static ItemStack createEnchantedBook() {
        ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
        EnchantedBookItem.addEnchantment(enchantedBook, new EnchantmentLevelEntry(ModEnchantments.NO_MAGMA_DAMAGE, 1));
        return enchantedBook;
    }


}
