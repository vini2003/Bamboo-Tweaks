package vini2003.xyz.eco.common.world.layer.implementation.mountain;

import vini2003.xyz.eco.common.world.layer.base.HeightLayer;
import vini2003.xyz.eco.common.world.layer.base.NoiseLayer;

public class MountainHeightLayer extends HeightLayer {
	public MountainHeightLayer(NoiseLayer noiseLayer) {
		super(noiseLayer);
	}
	
	@Override
	public int getHeight(int x, int z) {
		return (int) (noiseLayer.getNoise(x, z)  * 192.0F + 48.0F);
	}
}
