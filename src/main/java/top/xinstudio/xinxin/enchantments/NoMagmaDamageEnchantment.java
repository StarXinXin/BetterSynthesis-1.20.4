package top.xinstudio.xinxin.enchantments;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FrostedIceBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

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
//
//public class NoMagmaDamageEnchantment extends Enchantment {
//
//    protected NoMagmaDamageEnchantment(Enchantment.Rarity weight, EquipmentSlot... slotTypes) {
//        super(weight, EnchantmentTarget.ARMOR_FEET, slotTypes);
//    }
//
//
////    @Override
////    public boolean isAcceptableItem(ItemStack stack) {
////        return stack.getItem() instanceof ElytraItem;
////    }
//
////    @Override
////    public boolean isAcceptableItem(ItemStack stack) {
////        return stack.getItem() instanceof ArmorItem &&((ArmorItem) stack.getItem()).getSlotType() == EquipmentSlot.FEET;
////    }
//
//    public boolean canAccept(Enchantment other) {
//        return super.canAccept(other) && other != ModEnchantments.NO_MAGMA_DAMAGE;
//    }
//
//    @Override
//    public boolean isTreasure() {
//        return true;
//    }
//
//    @Override
//    public int getMinPower(int level) {
//        return 1;
//    }
//
//    @Override
//    public int getMaxLevel() {
//        return 1;
//    }
//}
