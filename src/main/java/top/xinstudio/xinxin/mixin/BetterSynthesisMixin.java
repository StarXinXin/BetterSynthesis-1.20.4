package top.xinstudio.xinxin.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//@Mixin(MinecraftServer.class)
//public class BetterSynthesisMixin {
//	@Inject(at = @At("HEAD"), method = "loadWorld")
//	private void init(CallbackInfo info) {
//		// This code is injected into the start of MinecraftServer.loadWorld()V
//	}
//}

import net.minecraft.block.BlockState;
import net.minecraft.block.MagmaBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import top.xinstudio.xinxin.enchantments.ModEnchantments;

@Mixin(MagmaBlock.class)
public class BetterSynthesisMixin {
    @Inject(method = "onSteppedOn", at = @At("HEAD"), cancellable = true)
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            int level = EnchantmentHelper.getEquipmentLevel(ModEnchantments.NO_MAGMA_DAMAGE, player);
            if (level > 0) {
                ci.cancel();
            }
        }
    }
}