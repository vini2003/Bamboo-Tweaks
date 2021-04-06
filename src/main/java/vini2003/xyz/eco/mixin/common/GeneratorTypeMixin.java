package vini2003.xyz.eco.mixin.common;

import net.minecraft.client.world.GeneratorType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vini2003.xyz.eco.common.world.generator.EcoChunkGenerator;
import vini2003.xyz.eco.common.world.generator.EcoGeneratorType;

import java.util.List;

@Mixin(GeneratorType.class)
public class GeneratorTypeMixin {
	@Shadow @Final protected static List<GeneratorType> VALUES;
	
	@Inject(at = @At("TAIL"), method = "<clinit>")
	private static void eco_cinit(CallbackInfo ci) {
		VALUES.add(new EcoGeneratorType());
	}
}
