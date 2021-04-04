package vini2003.xyz.eco.mixin.common;

import net.minecraft.world.biome.source.BiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vini2003.xyz.eco.common.biome.EcoBiomeAccessType;

@Mixin(DimensionType.class)
public class DimensionTypeMixin {
	@Shadow @Final protected static DimensionType OVERWORLD;
	
	@Inject(at = @At("HEAD"), method = "getBiomeAccessType", cancellable = true)
	private void eco_getBiomeAccessType(CallbackInfoReturnable<BiomeAccessType> cir) {
		if ((Object) this == OVERWORLD) {
			cir.setReturnValue(EcoBiomeAccessType.INSTANCE);
			cir.cancel();
		}
	}
}
