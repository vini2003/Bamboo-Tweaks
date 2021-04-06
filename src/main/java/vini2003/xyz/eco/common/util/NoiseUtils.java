package vini2003.xyz.eco.common.util;

import Auburn.FastNoiseLite.Java.FastNoiseLite;

public class NoiseUtils {
	/**
	 * Normalize a noise sample to the range [0..1].
	 * @param sample the sample to be normalized.
	 * @return the normalized sample.
	 */
	public static float normalize(float sample) {
		// We assume that it is [-1..1] by default.
		return NormalizeUtils.normalize(sample, -1.0F, 1.0F, 0.0F, 1.0F);
	}
	
	public static float getNoise(FastNoiseLite noise, float x, float z, int octaves, float frequency, float amplitude, float persistence) {
		float total = 0.0F;
		
		float maximum = 0.0F;
		
		for (int i = 0; i < octaves; ++i) {
			total += noise.GetNoise(x * frequency, z * frequency) * amplitude;
			
			maximum += amplitude;
			amplitude *= persistence;
			frequency *= 2.0F;
		}
		
		return total / maximum;
	}
}
