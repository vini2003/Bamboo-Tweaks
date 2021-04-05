package vini2003.xyz.eco.common.util;

public class NormalizeUtils {
	/**
	 * Normalize a noise sample to the range [0..1].
	 * @param value the value to be normalized.
	 * @param sourceMinimum the minimum value of the source range.
	 * @param sourceMaximum the maximum value of the source range.
	 * @param targetMinimum the minimum value of the target range.
	 * @param targetMaximum the maximum value of the target range.
	 * @return the normalized value.
	 */
	public static float normalize(float value, float sourceMinimum, float sourceMaximum, float targetMinimum, float targetMaximum) {
		return (value - sourceMinimum) / (sourceMaximum - (sourceMinimum)) * (targetMaximum - targetMinimum);
	}
}
