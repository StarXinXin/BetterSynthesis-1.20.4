package top.xinstudio.xinxin.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import top.xinstudio.xinxin.BetterSynthesis;
import top.xinstudio.xinxin.block.ModBlocks;
import top.xinstudio.xinxin.enchantments.ModEnchantments;

public class ModItemGroup {
    public static final ItemGroup SILVER_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(BetterSynthesis.MOD_ID, BetterSynthesis.MOD_ID),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.BetterSynthesis"))
                    .icon(() -> new ItemStack(ModItems.ITEM_CraftingTable)).entries((displayContext, entries) -> {
                        entries.add(ModItems.ITEM_HardboiledEggs);
                        entries.add(ModItems.ITEM_Cabbage_SEEDS);
                        entries.add(ModItems.ITEM_Cabbage);
                        entries.add(ModBlocks.BLOCK_BSFURNACE);
                        entries.add(ModBlocks.BLOCK_SAUCEPAN);
                    }).build());

    public static void registerModItemGroup(){
        BetterSynthesis.LOGGER.debug("注册mod项目组: "+ BetterSynthesis.MOD_ID);
    }
}