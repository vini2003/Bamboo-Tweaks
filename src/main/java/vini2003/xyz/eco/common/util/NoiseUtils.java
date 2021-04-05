package vini2003.xyz.eco.common.util;

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
}
