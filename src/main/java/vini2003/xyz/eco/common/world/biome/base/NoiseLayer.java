package vini2003.xyz.eco.common.world.biome.base;

import Auburn.FastNoiseLite.Java.FastNoiseLite;

public abstract class NoiseLayer {
	public final long seed;
	
	public final FastNoiseLite noise;
	
	public NoiseLayer(long seed) {
		this.seed = seed;
		
		this.noise = new FastNoiseLite((int) seed);
	}
	
	public abstract float getNoise(int x, int z);
	
	public long getSeed() {
		return seed;
	}
}
