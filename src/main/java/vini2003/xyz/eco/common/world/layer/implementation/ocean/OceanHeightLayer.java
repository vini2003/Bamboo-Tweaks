package vini2003.xyz.eco.common.world.layer.implementation.ocean;

import vini2003.xyz.eco.common.world.layer.base.HeightLayer;
import vini2003.xyz.eco.common.world.layer.base.NoiseLayer;

public class OceanHeightLayer extends HeightLayer {
	public OceanHeightLayer(NoiseLayer noiseLayer) {
		super(noiseLayer);
	}
	
	@Override
	public int getHeight(int x, int z) {
		return 64;
	}
}
