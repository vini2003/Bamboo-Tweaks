package vini2003.xyz.eco.common.world.generator;

import Auburn.FastNoiseLite.Java.FastNoiseLite;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.BlockView;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.chunk.VerticalBlockSample;

import java.util.Arrays;
import java.util.Set;

public class EcoChunkGenerator extends ChunkGenerator {
	public static Codec<EcoChunkGenerator> CODEC =
			RecordCodecBuilder.create(
					instance -> instance.group(
							Codec.LONG
									.fieldOf("seed")
									.forGetter(gen -> gen.seed),
							RegistryLookupCodec
									.of(Registry.BIOME_KEY)
									.forGetter(source -> source.biomeRegistry))
							.apply(instance, EcoChunkGenerator::new));
	
	private final long seed;
	private final Registry<Biome> biomeRegistry;
	
	private final FastNoiseLite noise;
	
	public EcoChunkGenerator(long seed, Registry<Biome> biomeRegistry) {
		super(new EcoBiomeSource(biomeRegistry, seed), new StructuresConfig(false));
		
		this.seed = seed;
		this.biomeRegistry = biomeRegistry;
		
		this.noise = new FastNoiseLite((int) seed);
		this.noise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
	}
	
	@Override
	protected Codec<? extends ChunkGenerator> getCodec() {
		return CODEC;
	}
	
	@Override
	public ChunkGenerator withSeed(long seed) {
		return new EcoChunkGenerator(seed, biomeRegistry);
	}
	
	@Override
	public void buildSurface(ChunkRegion region, Chunk chunk) {
		for (int x = chunk.getPos().getStartX(); x <= chunk.getPos().getEndX(); ++x) {
			for (int z = chunk.getPos().getStartZ(); z <= chunk.getPos().getEndZ(); ++z) {
				int t = 0;
				
				for (int y = 256; y > 0; --y) {
					float mainSample = getNoiseAverage(x, z, 2) + Math.max(0F, 64F / y) - (float) ((10.0 / (y + 1.0)) - (10.0 / (y - 257.0)) - 0.155);
					
					mainSample /= (float) Math.log(y);
					
					Biome biome = biomeSource.getBiomeForNoiseGen(x, y, z);
					
					if (mainSample > 0.7F) {
						for (int cY = y; cY > y - 5; --cY) {
							if (biome.getCategory() == Biome.Category.DESERT) {
								if (t == 0) {
									chunk.setBlockState(new BlockPos(x, cY, z), Blocks.SAND.getDefaultState(), false);
								} else {
									chunk.setBlockState(new BlockPos(x, cY, z), Blocks.SANDSTONE.getDefaultState(), false);
								}
								
								++t;
								
								if (t == 5) {
									break;
								}
							} else {
								if (t == 0) {
									if (cY > 512 * mainSample) {
										chunk.setBlockState(new BlockPos(x, cY, z), Blocks.SNOW_BLOCK.getDefaultState(), false);
									} else {
										chunk.setBlockState(new BlockPos(x, cY, z), Blocks.GRASS_BLOCK.getDefaultState(), false);
									}
								} else if (t < 4) {
									chunk.setBlockState(new BlockPos(x, cY, z), Blocks.DIRT.getDefaultState(), false);
								} else {
									chunk.setBlockState(new BlockPos(x, cY, z), Blocks.STONE.getDefaultState(), false);
								}
								
								++t;
								
								if (t == 5) {
									break;
								}
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public void populateNoise(WorldAccess world, StructureAccessor accessor, Chunk chunk) {
		for (int x = chunk.getPos().getStartX(); x <= chunk.getPos().getEndX(); ++x) {
			for (int z = chunk.getPos().getStartZ(); z <= chunk.getPos().getEndZ(); ++z) {
				for (int y = 256; y > 0; --y) {
					float mainSample = getNoiseAverage(x, z, 2) + Math.max(0F, 64F / y) - (float) ((10.0 / (y + 1.0)) - (10.0 / (y - 257.0)) - 0.155);
					
					mainSample /= (float) Math.log(y);
					
					if (mainSample > 0.7F) {
						for (int cY = y; cY > 0; --cY) {
							float caveSample = noise.GetNoise(x, cY * 8F, z);
							
							if (cY < 16) {
								chunk.setBlockState(new BlockPos(x, cY, z), Blocks.LAVA.getDefaultState(), false);
							} else if (caveSample < 0.5F) {
								chunk.setBlockState(new BlockPos(x, cY, z), Blocks.STONE.getDefaultState(), false);
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public int getHeight(int x, int z, Heightmap.Type heightmapType) {
		return 0;
	}
	
	@Override
	public BlockView getColumnSample(int x, int z) {
		BlockState[] states = new BlockState[256];
		Arrays.fill(states, Blocks.AIR.getDefaultState());
		return new VerticalBlockSample(states);
	}
	
	public float getNoise(float x, float z) {
		return noise.GetNoise(x, z);
	}

	public float getNoise(float x, float y, int z) {
		return noise.GetNoise(x, y, z);
	}
	
	public float getNoise(float sample, int steps, float scale) {
		float result = 0F;
		
		for (int step = 0; step < steps; ++step) {
			result += sample * scale;
			
			scale /= 2F;
		}
		
		return result;
	}
	
	public float getNoiseAverage(float x, float z, int radius) {
		x /= 4F;
		z /= 4F;
		
		float result = 0F;
		
		for (float rX = x - 16; rX < x + 16; rX += 8) {
			for (float rZ = z - 16; rZ < z + 16; rZ += 8) {
				Biome biome = getBiome((int) rX, (int) rZ);
				
				result += getNoise(rX / (biome.getScale() * 16F), rZ / (biome.getScale() * 16F));
			}
		}
		
		result /= (radius * radius);
		
		return result;
	}
	
	public Biome getBiome(int x, int z) {
		return biomeSource.getBiomeForNoiseGen(x, 0, z);
	}
	
	public Biome getBiome(int x, int y, int z) {
		return biomeSource.getBiomeForNoiseGen(x, y, z);
	}
	
	public Set<Biome> getBiomes(int x, int z, int radius) {
		return biomeSource.getBiomesInArea(x, 0, z, radius);
	}
	
	public Set<Biome> getBiomes(int x, int y, int z, int radius) {
		return biomeSource.getBiomesInArea(x, y, z, radius);
	}
}
