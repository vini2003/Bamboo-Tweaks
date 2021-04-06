package vini2003.xyz.eco.common.world.layer.implementation.hill;

import vini2003.xyz.eco.common.world.layer.base.HeightLayer;
import vini2003.xyz.eco.common.world.layer.base.NoiseLayer;

public class HillHeightLayer extends HeightLayer {
	public HillHeightLayer(NoiseLayer noiseLayer) {
		super(noiseLayer);
	}
	
	@Override
	public int getHeight(int x, int z) {
		return 32 + (int) (noiseLayer.getNoise(x, z) * 48.0F);
	}
}
