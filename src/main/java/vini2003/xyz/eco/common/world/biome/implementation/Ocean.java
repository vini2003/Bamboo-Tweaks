package vini2003.xyz.eco.common.world.biome.implementation;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import vini2003.xyz.eco.common.world.biome.base.*;

public class Ocean {
	public static class Noise extends NoiseLayer {
		public Noise(long seed) {
			super(seed);
		}
		
		@Override
		public float getNoise(int x, int z) {
			return 0.0F;
		}
	}
	
	public static class Height extends HeightLayer {
		public Height(NoiseLayer noiseLayer) {
			super(noiseLayer);
		}
		
		@Override
		public int getHeight(int x, int z) {
			return 64;
		}
	}
	
	public static class Substrate extends SubstrateLayer {
		public Substrate(HeightLayer heightLayer) {
			super(heightLayer);
		}
		
		@Override
		public void build(Chunk chunk, int x, int z) {
			int height = heightLayer.getHeight(x, z);
			
			for (int y = height; y > 0 && chunk.getBlockState(new BlockPos(x, y, z)).isAir(); --y) {
				chunk.setBlockState(new BlockPos(x, y, z), Blocks.WATER.getDefaultState(), false);
			}
		}
	}
	
	public static class Surface extends SurfaceLayer {
		public Surface(HeightLayer heightLayer) {
			super(heightLayer);
		}
		
		@Override
		public void build(Chunk chunk, int x, int z) {}
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
