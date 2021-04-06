package vini2003.xyz.eco.common.world.biome.base;

import net.minecraft.world.chunk.Chunk;

public class AggregateLayer {
	private final NoiseLayer noiseLayer;
	private final HeightLayer heightLayer;
	private final SubstrateLayer substrateLayer;
	private final SurfaceLayer surfaceLayer;
	
	public AggregateLayer(NoiseLayer noiseLayer, HeightLayer heightLayer, SubstrateLayer substrateLayer, SurfaceLayer surfaceLayer) {
		this.noiseLayer = noiseLayer;
		this.heightLayer = heightLayer;
		this.substrateLayer = substrateLayer;
		this.surfaceLayer = surfaceLayer;
	}
	
	public NoiseLayer getNoiseLayer() {
		return noiseLayer;
	}
	
	public HeightLayer getHeightLayer() {
		return heightLayer;
	}
	
	public SubstrateLayer getSubstrateLayer() {
		return substrateLayer;
	}
	
	public SurfaceLayer getSurfaceLayer() {
		return surfaceLayer;
	}
	
	public final void buildSubstrate(Chunk chunk, int x, int z) {
		substrateLayer.build(chunk, x, z);
	}
	
	public final void buildSurface(Chunk chunk, int x, int z) {
		surfaceLayer.build(chunk, x, z);
	}
}
