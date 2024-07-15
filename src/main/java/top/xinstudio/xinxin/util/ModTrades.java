package top.xinstudio.xinxin.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import top.xinstudio.xinxin.enchantments.ModEnchantments;
import top.xinstudio.xinxin.item.ModItems;
import top.xinstudio.xinxin.villager.ModVillagers;

public class ModTrades {
    public static void registerTrades() {
//        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 1, factories -> {
//            factories.add((entity, random) -> new TradeOffer(
//                    new ItemStack(Items.ROTTEN_FLESH, 42),
//                    new ItemStack(Items.PHANTOM_MEMBRANE, 1),
//                    6, 5, 0.05f)
//            );
//            factories.add((entity, random) -> new TradeOffer(
//                    new ItemStack(Items.ROTTEN_FLESH, 22),
//                    new ItemStack(Items.LEATHER, 1),
//                    6, 5, 0.05f)
//            );
//        });


        //为“图书管理员”职业的村民注册自定义交易
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 1, factories -> {
            factories.add((entity, random) -> {
                if (random.nextFloat() < 0.2F) { // 20% chance to offer the custom trade
                    ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
                    EnchantedBookItem.addEnchantment(enchantedBook, new EnchantmentLevelEntry(ModEnchantments.NO_MAGMA_DAMAGE, 1));
                    return new TradeOffer(
                            new ItemStack(Items.EMERALD, 10), // 10颗绿宝石
                            new ItemStack(Items.BOOK, 1), // 一本书
                            enchantedBook, // 附魔书
                            12, // 最大使用量
                            5, // 获得经验
                            0.05F // 价格折扣
                    );
                }
                return null;
            });
        });

        //为“制箭师”职业的村民注册自定义交易
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FLETCHER, 1, factories -> factories.add((entity, random) -> new TradeOffer(
                new ItemStack(Items.BAMBOO, 20),
                new ItemStack(Items.EMERALD, 1),
                6, 5, 0.05f)
        ));

        TradeOfferHelper.registerVillagerOffers(ModVillagers.BLOCK_BSFURNACE_MASTER, 1, factories -> {
            //10个腐个肉兑换1硬煮鸡蛋
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.ROTTEN_FLESH, 10),
                    new ItemStack(ModItems.ITEM_HardboiledEggs, 1),
                    6, 5, 0.05f)
            );
            //42个腐肉兑换1个幻影膜
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.ROTTEN_FLESH, 42),
                    new ItemStack(Items.PHANTOM_MEMBRANE, 1),
                    6, 5, 0.05f)
            );
            //22个腐肉兑换1个皮革
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.ROTTEN_FLESH, 22),
                    new ItemStack(Items.LEATHER, 1),
                    6, 5, 0.05f)
            );
            // 二十个腐肉换一个绿宝石
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.ROTTEN_FLESH, 20),
                    new ItemStack(Items.EMERALD, 1),
                    6, 5, 0.05f)
            );
        });

    }
}
