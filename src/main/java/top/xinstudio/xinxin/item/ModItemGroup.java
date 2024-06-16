package top.xinstudio.xinxin.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import top.xinstudio.xinxin.BetterSynthesis;
import top.xinstudio.xinxin.block.ModBlocks;

public class ModItemGroup {
    public static final ItemGroup SILVER_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(BetterSynthesis.MOD_ID, "item_name"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.item_name"))
                    .icon(() -> new ItemStack(ModItems.ITEM_CraftingTable)).entries((displayContext, entries) -> {
                        entries.add(ModItems.ITEM_HardboiledEggs);
                        entries.add(ModBlocks.ITEM_COOKINGSTOVES);
                    }).build());

    public static void registerModItemGroup(){
        BetterSynthesis.LOGGER.debug("注册mod项目组:"+ BetterSynthesis.MOD_ID);
    }
}