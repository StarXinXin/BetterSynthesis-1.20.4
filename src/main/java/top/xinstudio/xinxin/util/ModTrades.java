package top.xinstudio.xinxin.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import top.xinstudio.xinxin.item.ModItems;
import top.xinstudio.xinxin.villager.ModVillagers;

public class ModTrades {
    public static void registerTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.ROTTEN_FLESH, 42),
                    new ItemStack(Items.PHANTOM_MEMBRANE, 1),
                    6, 5, 0.05f)
            );
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.ROTTEN_FLESH, 22),
                    new ItemStack(Items.LEATHER, 1),
                    6, 5, 0.05f)
            );
        });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.Cooking_Stoves_MASTER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.ROTTEN_FLESH, 10),
                    new ItemStack(ModItems.ITEM_HardboiledEggs, 1),
                    6, 5, 0.05f)
            );
        });
    }
}
