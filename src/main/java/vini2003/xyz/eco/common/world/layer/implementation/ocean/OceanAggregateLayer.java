package vini2003.xyz.eco.common.world.layer.implementation.ocean;

import vini2003.xyz.eco.common.world.layer.base.*;

public class OceanAggregateLayer extends AggregateLayer {
	public OceanAggregateLayer(NoiseLayer noiseLayer, HeightLayer heightLayer, SubstrateLayer substrateLayer, SurfaceLayer surfaceLayer) {
		super(noiseLayer, heightLayer, substrateLayer, surfaceLayer);
	}
	
	public static AggregateLayer create(long seed) {
		NoiseLayer noiseLayer = new OceanNoiseLayer(seed);
		HeightLayer heightLayer = new OceanHeightLayer(noiseLayer);
		SubstrateLayer substrateLayer = new OceanSubstrateLayer(heightLayer);
		SurfaceLayer surfaceLayer = new OceanSurfaceLayer(heightLayer);
		
		return new OceanAggregateLayer(noiseLayer, heightLayer, substrateLayer, surfaceLayer);
	}
}
