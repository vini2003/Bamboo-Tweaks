package vini2003.xyz.eco.common.world.layer.base;

import Auburn.FastNoiseLite.Java.FastNoiseLite;
import vini2003.xyz.eco.common.function.BiInt2FloatFunction;

public abstract class NoiseLayer {
	public final long seed;
	
	public final FastNoiseLite noise;
	
	public NoiseLayer(long seed) {
		this.seed = seed;
		
		this.noise = new FastNoiseLite((int) seed);
	}
	
	/**
	 * Return the noise in the range of
	 * [0..1] at the given coordinates.
	 * @param x the given X coordinate.
	 * @param z the given Z coordinate.
	 * @return the requested noise.
	 */
	public abstract float getNoise(int x, int z);
	
	public long getSeed() {
		return seed;
	}
}
