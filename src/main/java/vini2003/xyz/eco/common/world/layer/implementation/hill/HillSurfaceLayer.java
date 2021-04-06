package vini2003.xyz.eco.common.world.layer.implementation.hill;

import Auburn.FastNoiseLite.Java.FastNoiseLite;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import vini2003.xyz.eco.common.world.layer.base.HeightLayer;
import vini2003.xyz.eco.common.world.layer.base.SurfaceLayer;

public class HillSurfaceLayer extends SurfaceLayer {
	private final FastNoiseLite snowNoise;
	private final FastNoiseLite grassNoise;
	
	public HillSurfaceLayer(HeightLayer heightLayer) {
		super(heightLayer);
		
		this.snowNoise = new FastNoiseLite((int) heightLayer.getSeed());
		this.grassNoise = new FastNoiseLite((int) heightLayer.getSeed());
	}
	
	@Override
	public void build(Chunk chunk) {
		for (int x = chunk.getPos().getStartX(); x <= chunk.getPos().getEndX(); ++x) {
			for (int z = chunk.getPos().getStartZ(); z <= chunk.getPos().getEndZ(); ++z) {
				int height = heightLayer.getHeight(x, z);
				
				for (int y = height; y > height - 5; --y) {
					if (y == height && y > 63) {
						chunk.setBlockState(new BlockPos(x, y, z), Blocks.GRASS_BLOCK.getDefaultState(), false);
					} else {
						chunk.setBlockState(new BlockPos(x, y, z), Blocks.DIRT.getDefaultState(), false);
					}
				}
			}
		}
	}
}
