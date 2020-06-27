package com.github.vini2003.bambootweaks.mixin;

import com.github.vini2003.bambootweaks.block.ExposedLadderBlock;
import com.github.vini2003.bambootweaks.utilities.BlockClimbable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
abstract class MixinLivingEntity {

	@Inject(
			method = "isClimbing",
			at = @At(value = "RETURN", ordinal = 2),
			locals = LocalCapture.CAPTURE_FAILHARD,
			allow = 1,
			cancellable = true)
	void onIsClimbing(final CallbackInfoReturnable<Boolean> cir, final BlockState state, final Block block) {
		LivingEntity livingEntity = (LivingEntity)(Object)this;
		if (block instanceof BlockClimbable) {
			final LivingEntity self = (LivingEntity) (Object) this;
			cir.setReturnValue(((BlockClimbable) block).canClimb(self, state, livingEntity.getBlockPos()));
		} else {
			final BlockPos down = new BlockPos(livingEntity.getX(), livingEntity.getY() - 0.5, livingEntity.getZ());
			final Block below = livingEntity.world.getBlockState(down).getBlock();
			if (below instanceof ExposedLadderBlock) {
				cir.setReturnValue(livingEntity.getY() - MathHelper.floor(livingEntity.getY()) < 0.5);
			}
		}
	}
}