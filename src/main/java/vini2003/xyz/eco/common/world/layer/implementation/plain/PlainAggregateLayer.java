package vini2003.xyz.eco.common.world.layer.implementation.plain;

import vini2003.xyz.eco.common.world.layer.base.*;

public class PlainAggregateLayer extends AggregateLayer {
	public PlainAggregateLayer(NoiseLayer noiseLayer, HeightLayer heightLayer, SubstrateLayer substrateLayer, SurfaceLayer surfaceLayer) {
		super(noiseLayer, heightLayer, substrateLayer, surfaceLayer);
	}
	
	public static AggregateLayer create(long seed) {
		NoiseLayer noiseLayer = new PlainNoiseLayer(seed);
		HeightLayer heightLayer = new PlainHeightLayer(noiseLayer);
		SubstrateLayer substrateLayer = new PlainSubstrateLayer(heightLayer);
		SurfaceLayer surfaceLayer = new PlainSurfaceLayer(heightLayer);
		
		return new PlainAggregateLayer(noiseLayer, heightLayer, substrateLayer, surfaceLayer);
	}
}
