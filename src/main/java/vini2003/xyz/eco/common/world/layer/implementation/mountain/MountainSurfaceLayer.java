package vini2003.xyz.eco.common.world.layer.implementation.mountain;

import Auburn.FastNoiseLite.Java.FastNoiseLite;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import vini2003.xyz.eco.common.world.layer.base.HeightLayer;
import vini2003.xyz.eco.common.world.layer.base.SurfaceLayer;

public class MountainSurfaceLayer extends SurfaceLayer {
	private final FastNoiseLite snowNoise;
	private final FastNoiseLite grassNoise;
	
	public MountainSurfaceLayer(HeightLayer heightLayer) {
		super(heightLayer);
		
		this.snowNoise = new FastNoiseLite((int) heightLayer.getSeed());
		this.grassNoise = new FastNoiseLite((int) heightLayer.getSeed());
	}
	
	@Override
	public void build(Chunk chunk) {
		for (int x = chunk.getPos().getStartX(); x <= chunk.getPos().getEndX(); ++x) {
			for (int z = chunk.getPos().getStartZ(); z <= chunk.getPos().getEndZ(); ++z) {
				int height = heightLayer.getHeight(x, z);
				
				float snow = snowNoise.GetNoise(x / 8F, z / 8F);
				float grass = grassNoise.GetNoise(x, z / 8F / 8F);
				
				if (height < 80 + (8 * grass)) {
					for (int y = height - 1; y > 80 + (8 * grass); --y) {
						chunk.setBlockState(new BlockPos(x, y, z), Blocks.DIRT.getDefaultState(), false);
					}
					
					if (height < 64) {
						chunk.setBlockState(new BlockPos(x, height, z), Blocks.DIRT.getDefaultState(), false);
					} else {
						chunk.setBlockState(new BlockPos(x, height, z), Blocks.GRASS_BLOCK.getDefaultState(), false);
					}
				}
				
				if (height > 136 + (16 * snow)) {
					for (int y = height - 1; y > 136 + (16 * snow); --y) {
						chunk.setBlockState(new BlockPos(x, y, z), Blocks.SNOW_BLOCK.getDefaultState(), false);
					}
					
					chunk.setBlockState(new BlockPos(x, height, z), Blocks.SNOW.getDefaultState(), false);
				}
			}
		}
	}
}
