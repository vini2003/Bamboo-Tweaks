package vini2003.xyz.eco.common.world.layer.base;

import net.minecraft.world.chunk.Chunk;

import java.util.function.Consumer;

public abstract class SurfaceLayer {
	protected HeightLayer heightLayer;
	
	public SurfaceLayer(HeightLayer heightLayer) {
		this.heightLayer = heightLayer;
	}
	
	public abstract void build(Chunk chunk);
}
