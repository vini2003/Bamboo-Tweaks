package vini2003.xyz.eco.common.world.layer.implementation.hill;

import vini2003.xyz.eco.common.world.layer.base.*;

public class HillAggregateLayer extends AggregateLayer {
	public HillAggregateLayer(NoiseLayer noiseLayer, HeightLayer heightLayer, SubstrateLayer substrateLayer, SurfaceLayer surfaceLayer) {
		super(noiseLayer, heightLayer, substrateLayer, surfaceLayer);
	}
	
	public static AggregateLayer create(long seed) {
		NoiseLayer noiseLayer = new HillNoiseLayer(seed);
		HeightLayer heightLayer = new HillHeightLayer(noiseLayer);
		SubstrateLayer substrateLayer = new HillSubstrateLayer(heightLayer);
		SurfaceLayer surfaceLayer = new HillSurfaceLayer(heightLayer);
		
		return new HillAggregateLayer(noiseLayer, heightLayer, substrateLayer, surfaceLayer);
	}
}
