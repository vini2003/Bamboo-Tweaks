package vini2003.xyz.eco.common.world.layer.implementation.plain;

import vini2003.xyz.eco.common.world.layer.base.HeightLayer;
import vini2003.xyz.eco.common.world.layer.base.NoiseLayer;

/**
 * A height layer representing a plain field,
 * at a Y level of 64 - corresponding to the
 * sea level.
 *
 * Starting layer - other layers build on top
 * of this.
 */
public class PlainHeightLayer extends HeightLayer {
	public PlainHeightLayer(NoiseLayer noiseLayer) {
		super(noiseLayer);
	}
	
	/**
	 * The resulting height conforms to
	 * the range [64..80].
	 */
	@Override
	public int getHeight(int x, int z) {
		return 64 + (int) (noiseLayer.getNoise(x, z) * 16.0F);
	}
}
