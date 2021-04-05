package vini2003.xyz.eco.common.world.layer.implementation.mountain;

import vini2003.xyz.eco.common.world.layer.base.*;

public class MountainAggregateLayer extends AggregateLayer {
	public MountainAggregateLayer(NoiseLayer noiseLayer, HeightLayer heightLayer, SubstrateLayer substrateLayer, SurfaceLayer surfaceLayer) {
		super(noiseLayer, heightLayer, substrateLayer, surfaceLayer);
	}
	
	public static AggregateLayer create(long seed) {
		NoiseLayer noiseLayer = new MountainNoiseLayer(seed);
		HeightLayer heightLayer = new MountainHeightLayer(noiseLayer);
		SubstrateLayer substrateLayer = new MountainSubstrateLayer(heightLayer);
		SurfaceLayer surfaceLayer = new MountainSurfaceLayer(heightLayer);
		
		return new MountainAggregateLayer(noiseLayer, heightLayer, substrateLayer, surfaceLayer);
	}
}
