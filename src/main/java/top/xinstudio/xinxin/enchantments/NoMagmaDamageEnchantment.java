package top.xinstudio.xinxin.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class NoMagmaDamageEnchantment
        extends Enchantment {
    public NoMagmaDamageEnchantment(Enchantment.Rarity weight, EquipmentSlot ... slotTypes) {
        super(weight, EnchantmentTarget.ARMOR_FEET, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return level * 10;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 15;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }


    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other)/* && other != Enchantments.DEPTH_STRIDER */;
    }
}
