package vini2003.xyz.eco.common.world.layer.implementation.mountain;

import Auburn.FastNoiseLite.Java.FastNoiseLite;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import vini2003.xyz.eco.common.world.layer.base.HeightLayer;
import vini2003.xyz.eco.common.world.layer.base.SurfaceLayer;

public class MountainSurfaceLayer extends SurfaceLayer {
	private final FastNoiseLite snowNoise;
	
	public MountainSurfaceLayer(HeightLayer heightLayer) {
		super(heightLayer);
		
		this.snowNoise = new FastNoiseLite((int) heightLayer.getSeed());
	}
	
	@Override
	public void build(Chunk chunk) {
		for (int x = chunk.getPos().getStartX(); x <= chunk.getPos().getEndX(); ++x) {
			for (int z = chunk.getPos().getStartZ(); z <= chunk.getPos().getEndZ(); ++z) {
				int height = heightLayer.getHeight(x, z);
				
				float snow = snowNoise.GetNoise(x, z);
				
				if (height > 148 + (16 * snow)) {
					for (int y = height - 1; y > 148 + (16 * snow); --y) {
						chunk.setBlockState(new BlockPos(x, y, z), Blocks.SNOW_BLOCK.getDefaultState(), false);
					}
					
					chunk.setBlockState(new BlockPos(x, height, z), Blocks.SNOW.getDefaultState(), false);
				}
			}
		}
	}
}
