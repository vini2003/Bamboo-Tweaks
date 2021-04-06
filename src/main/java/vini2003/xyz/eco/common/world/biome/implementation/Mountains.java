package vini2003.xyz.eco.common.world.biome.implementation;

import Auburn.FastNoiseLite.Java.FastNoiseLite;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.chunk.Chunk;
import vini2003.xyz.eco.common.util.BiomeUtils;
import vini2003.xyz.eco.common.util.NoiseUtils;
import vini2003.xyz.eco.common.world.biome.base.*;

public class Mountains {
	public static class Noise extends NoiseLayer {
		private final FastNoiseLite mountainNoise;
		
		public Noise(long seed) {
			super(seed);
			
			this.mountainNoise = new FastNoiseLite((int) seed);
			this.mountainNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
			this.mountainNoise.SetFrequency(0.005F);
			this.mountainNoise.SetDomainWarpType(FastNoiseLite.DomainWarpType.BasicGrid);
			this.mountainNoise.SetDomainWarpAmp(30.0F);
			this.mountainNoise.SetFractalType(FastNoiseLite.FractalType.None);
		}
		
		@Override
		public float getNoise(int x, int z) {
			float distance = (float) (512.0F - Math.sqrt(new BlockPos(x, 0, z).getSquaredDistance(BlockPos.ZERO)));
			
			float result = NoiseUtils.normalize(NoiseUtils.getNoise(mountainNoise, x, z, 8, 1.0F, 1.0F, 0.33F));
			
			return (1.0F - (distance - 128.0F) / (64.0F + Math.abs(distance - 128.0F))) * 0.375F * result;
		}
	}
	
	public static class Height extends HeightLayer {
		public Height(NoiseLayer noiseLayer) {
			super(noiseLayer);
		}
		
		@Override
		public int getHeight(int x, int z) {
			return (int) (noiseLayer.getNoise(x, z)  * 192.0F + 48.0F);
		}
	}
	
	public static class Substrate extends SubstrateLayer {
		public Substrate(HeightLayer heightLayer) {
			super(heightLayer);
		}
		
		@Override
		public void build(Chunk chunk, int x, int z) {
			int height = heightLayer.getHeight(x, z);
			
			for (int y = height - (height > 168 ? 3 : 0); y > 0; --y) {
				if (chunk.getBlockState(new BlockPos(x, y, z)).getFluidState().isEmpty()) {
					chunk.setBlockState(new BlockPos(x, y, z), Blocks.STONE.getDefaultState(), false);
				}
			}
		}
	}
	
	public static class Surface extends SurfaceLayer {
		private final FastNoiseLite snowNoise;
		private final FastNoiseLite grassNoise;
		
		public Surface(HeightLayer heightLayer) {
			super(heightLayer);
			
			this.snowNoise = new FastNoiseLite((int) heightLayer.getSeed());
			this.grassNoise = new FastNoiseLite((int) heightLayer.getSeed());
		}
		
		@Override
		public void build(Chunk chunk, int x, int z) {
			int height = heightLayer.getHeight(x, z);
			
			float snow = snowNoise.GetNoise(x / 8F, z / 8F);
			float grass = grassNoise.GetNoise(x, z / 8F / 8F);
			
			RegistryKey<Biome> biome = BiomeUtils.getSphereBiome(x, z, maskNoise);
			
			if (height < 80 + (8 * grass)) {
				for (int y = height - 1; y > 80 + (8 * grass); --y) {
					if (biome == BiomeKeys.DESERT) {
						chunk.setBlockState(new BlockPos(x, y, z), Blocks.SAND.getDefaultState(), false);
					} else {
						chunk.setBlockState(new BlockPos(x, y, z), Blocks.DIRT.getDefaultState(), false);
					}
				}
				
				if (height < 64) {
					if (biome == BiomeKeys.DESERT) {
						chunk.setBlockState(new BlockPos(x, height, z), Blocks.SANDSTONE.getDefaultState(), false);
					} else {
						chunk.setBlockState(new BlockPos(x, height, z), Blocks.DIRT.getDefaultState(), false);
					}
				} else {
					if (biome == BiomeKeys.DESERT) {
						chunk.setBlockState(new BlockPos(x, height, z), Blocks.SAND.getDefaultState(), false);
					} else {
						chunk.setBlockState(new BlockPos(x, height, z), Blocks.GRASS_BLOCK.getDefaultState(), false);
					}
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
