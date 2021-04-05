package vini2003.xyz.eco.common.world.layer.implementation.mountain;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import vini2003.xyz.eco.common.world.layer.base.HeightLayer;
import vini2003.xyz.eco.common.world.layer.base.SubstrateLayer;

public class MountainSubstrateLayer extends SubstrateLayer {
	public MountainSubstrateLayer(HeightLayer heightLayer) {
		super(heightLayer);
	}
	
	@Override
	public void build(Chunk chunk) {
		for (int x = chunk.getPos().getStartX(); x <= chunk.getPos().getEndX(); ++x) {
			for (int z = chunk.getPos().getStartZ(); z <= chunk.getPos().getEndZ(); ++z) {
				int height = heightLayer.getHeight(x, z);
				
				for (int y = height - (height > 168 ? 3 : 0); y > 0; --y) {
					if (chunk.getBlockState(new BlockPos(x, y, z)).getFluidState().isEmpty()) {
						chunk.setBlockState(new BlockPos(x, y, z), Blocks.STONE.getDefaultState(), false);
					}
				}
			}
		}
	}
}
