package vini2003.xyz.eco.common.world.layer.base;

import net.minecraft.world.chunk.Chunk;
import vini2003.xyz.eco.common.function.BiInt2FloatFunction;

import java.util.function.Consumer;

public abstract class SubstrateLayer {
	protected HeightLayer heightLayer;
	
	public SubstrateLayer(HeightLayer heightLayer) {
		this.heightLayer = heightLayer;
	}
	
	public abstract void build(Chunk chunk);
}
