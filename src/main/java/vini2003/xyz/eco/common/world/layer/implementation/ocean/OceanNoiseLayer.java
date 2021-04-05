package vini2003.xyz.eco.common.world.layer.implementation.ocean;

import vini2003.xyz.eco.common.world.layer.base.NoiseLayer;

public class OceanNoiseLayer extends NoiseLayer {
	public OceanNoiseLayer(long seed) {
		super(seed);
	}
	
	@Override
	public float getNoise(int x, int z) {
		return 0.0F;
	}
}
