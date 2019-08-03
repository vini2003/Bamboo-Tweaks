package bambootweaks.mixin;

import bambootweaks.block.BlockBambooLadder;
import bambootweaks.BlockClimbable;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;

@Mixin(LivingEntity.class)
abstract class MixinLivingEntity extends Entity {
	private MixinLivingEntity() {
		super(null, null);
	}

	@Inject(
		method = "isClimbing",
		at = @At(value = "RETURN", ordinal = 2),
		locals = LocalCapture.CAPTURE_FAILHARD,
		allow = 1,
		cancellable = true)
	void onIsClimbing(final CallbackInfoReturnable<Boolean> cir, final BlockState state, final Block block) {
		if (block instanceof BlockClimbable) {
			final LivingEntity self = (LivingEntity) (Object) this;
			cir.setReturnValue(((BlockClimbable) block).canClimb(self, state, new BlockPos(this)));
		} 
		else {
			final BlockPos down = new BlockPos(x, y - 0.5, z);
			final Block below = world.getBlockState(down).getBlock();
			if (below instanceof BlockBambooLadder) {
				cir.setReturnValue(y - MathHelper.floor(y) < 0.5);
			}
		}
	}
}