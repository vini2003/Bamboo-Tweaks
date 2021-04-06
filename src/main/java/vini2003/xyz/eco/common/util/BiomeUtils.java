package vini2003.xyz.eco.common.util;

import Auburn.FastNoiseLite.Java.FastNoiseLite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class BiomeUtils {
	public static RegistryKey<Biome> getSphereBiome(int x, int z, FastNoiseLite maskNoise) {
		float distance = (float) (Math.sqrt(new BlockPos(x, 0, z).getSquaredDistance(BlockPos.ZERO)));
		
		if (distance < NoiseUtils.normalize(NoiseUtils.getNoise(maskNoise, x, z, 8, 1.0F, 1.0F, 0.33F)) * 64.0F) {
			return BiomeKeys.TAIGA;
		} else {
			return BiomeKeys.SNOWY_TAIGA;
		}
	}
}
