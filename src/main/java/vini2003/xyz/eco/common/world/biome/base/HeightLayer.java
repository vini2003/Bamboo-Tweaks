package vini2003.xyz.eco.common.world.biome.base;

public abstract class HeightLayer {
	protected NoiseLayer noiseLayer;
	
	public HeightLayer(NoiseLayer noiseLayer) {
		this.noiseLayer = noiseLayer;
	}
	
	public abstract int getHeight(int x, int z);
	
	public long getSeed() {
		return noiseLayer.getSeed();
	}
}
