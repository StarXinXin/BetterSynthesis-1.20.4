package top.xinstudio.xinxin.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import top.xinstudio.xinxin.BetterSynthesis;

public class ModEnchantments {
    public static final Enchantment NO_MAGMA_DAMAGE = new NoMagmaDamageEnchantment();

    public static void registerEnchantments() {
        Registry.register(Registries.ENCHANTMENT, new Identifier(BetterSynthesis.MOD_ID, "no_magma_damage"), NO_MAGMA_DAMAGE);
    }
}
