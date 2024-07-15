package top.xinstudio.xinxin.enchantments;

import net.minecraft.enchantment.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import top.xinstudio.xinxin.BetterSynthesis;

public class ModEnchantments {

    public static final Enchantment NO_MAGMA_DAMAGE = register("no_magma_damage", new NoMagmaDamageEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.FEET));

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(BetterSynthesis.MOD_ID, name), enchantment);
    }
    public static void registerEnchantments() {

    }
}
