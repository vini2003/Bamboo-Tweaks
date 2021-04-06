package vini2003.xyz.eco.common.world.biome.base;

import Auburn.FastNoiseLite.Java.FastNoiseLite;
import net.minecraft.world.chunk.Chunk;

public abstract class SurfaceLayer {
	protected final HeightLayer heightLayer;
	
	protected final FastNoiseLite maskNoise;
	
	public SurfaceLayer(HeightLayer heightLayer) {
		this.heightLayer = heightLayer;
		
		this.maskNoise = new FastNoiseLite((int) heightLayer.getSeed());
	}
	
	public abstract void build(Chunk chunk, int x, int z);
}
