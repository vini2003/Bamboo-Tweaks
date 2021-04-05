package vini2003.xyz.eco.common.world.layer.implementation.plain;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import vini2003.xyz.eco.common.world.layer.base.HeightLayer;
import vini2003.xyz.eco.common.world.layer.base.SubstrateLayer;

public class PlainSubstrateLayer extends SubstrateLayer {
	public PlainSubstrateLayer(HeightLayer heightLayer) {
		super(heightLayer);
	}
	
	public int getOffsetY() {
		return -5;
	}
	
	@Override
	public void build(Chunk chunk) {
		for (int x = chunk.getPos().getStartX(); x <= chunk.getPos().getEndX(); ++x) {
			for (int z = chunk.getPos().getStartZ(); z <= chunk.getPos().getEndZ(); ++z) {
				int height = heightLayer.getHeight(x, z);
				
				for (int y = height + getOffsetY(); y > 0; --y) {
					chunk.setBlockState(new BlockPos(x, y, z), Blocks.STONE.getDefaultState(), false);
				}
			}
		}
	}
}
