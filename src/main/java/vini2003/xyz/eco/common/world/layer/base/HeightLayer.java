package vini2003.xyz.eco.common.world.layer.base;

public abstract class HeightLayer {
	protected NoiseLayer noiseLayer;
	
	public HeightLayer(NoiseLayer noiseLayer) {
		this.noiseLayer = noiseLayer;
	}
	
	/**
	 * Return the height in the range of
	 * [0..255] at the given coordinates.
	 * @param x the given X coordinate.
	 * @param z the given Z coordinate.
	 * @return the requested height.
	 */
	public abstract int getHeight(int x, int z);
	
	public long getSeed() {
		return noiseLayer.getSeed();
	}
}
