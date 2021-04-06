package vini2003.xyz.eco.common.world.biome.implementation;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import vini2003.xyz.eco.common.util.NoiseUtils;
import vini2003.xyz.eco.common.world.biome.base.*;

public class Forest {
	public static class Noise extends NoiseLayer {
		public Noise(long seed) {
			super(seed);
		}
		
		@Override
		public float getNoise(int x, int z) {
			return NoiseUtils.normalize(NoiseUtils.getNoise(noise, x, z, 8, 0.5F, 1.0F, 0.33F));
		}
	}
	
	public static class Height extends HeightLayer {
		public Height(NoiseLayer noiseLayer) {
			super(noiseLayer);
		}
		
		@Override
		public int getHeight(int x, int z) {
			return 64 + (int) (noiseLayer.getNoise(x, z) * 16.0F);
		}
	}
	
	public static class Substrate extends SubstrateLayer {
		public Substrate(HeightLayer heightLayer) {
			super(heightLayer);
		}
		
		public int getOffsetY() {
			return -5;
		}
		
		@Override
		public void build(Chunk chunk, int x, int z) {
			int height = heightLayer.getHeight(x, z);
			
			for (int y = height + getOffsetY(); y > 0; --y) {
				chunk.setBlockState(new BlockPos(x, y, z), Blocks.STONE.getDefaultState(), false);
			}
		}
	}
	
	public static class Surface extends SurfaceLayer {
		public Surface(HeightLayer heightLayer) {
			super(heightLayer);
		}
		
		@Override
		public void build(Chunk chunk, int x, int z) {
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
	
	public static class Aggregate extends AggregateLayer {
		public Aggregate(NoiseLayer noiseLayer, HeightLayer heightLayer, SubstrateLayer substrateLayer, SurfaceLayer surfaceLayer) {
			super(noiseLayer, heightLayer, substrateLayer, surfaceLayer);
		}
		
		public static AggregateLayer create(long seed) {
			NoiseLayer noiseLayer = new Noise(seed);
			HeightLayer heightLayer = new Height(noiseLayer);
			SubstrateLayer substrateLayer = new Substrate(heightLayer);
			SurfaceLayer surfaceLayer = new Surface(heightLayer);
			
			return new Aggregate(noiseLayer, heightLayer, substrateLayer, surfaceLayer);
		}
	}
}
