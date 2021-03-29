package vini2003.xyz.thefloorislava.mixin.common;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.FluidTags;
import net.minecraft.world.chunk.ChunkSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkSection.class)
public class ChunkSectionMixin {
	@Inject(at = @At("HEAD"), method = "setBlockState(IIILnet/minecraft/block/BlockState;Z)Lnet/minecraft/block/BlockState;", cancellable = true)
	void thefloorislava_setBlockState(int x, int y, int z, BlockState state, boolean lock, CallbackInfoReturnable<BlockState> cir) {
		if (state.getFluidState().isIn(FluidTags.WATER)) {
			cir.setReturnValue(Blocks.AIR.getDefaultState());
			cir.cancel();
		}
	}
}
