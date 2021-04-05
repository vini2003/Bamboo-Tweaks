package vini2003.xyz.eco.common.world.layer.implementation.ocean;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import vini2003.xyz.eco.common.world.layer.base.HeightLayer;
import vini2003.xyz.eco.common.world.layer.base.SurfaceLayer;

public class OceanSurfaceLayer extends SurfaceLayer {
	public OceanSurfaceLayer(HeightLayer heightLayer) {
		super(heightLayer);
	}
	
	@Override
	public void build(Chunk chunk) {}
}
