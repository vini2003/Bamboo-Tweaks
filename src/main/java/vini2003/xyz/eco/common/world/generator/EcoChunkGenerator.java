package vini2003.xyz.eco.common.world.generator;

import Auburn.FastNoiseLite.Java.FastNoiseLite;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
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
import vini2003.xyz.eco.common.world.BiomeSourceCache;

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
	
	private final ThreadLocal<BiomeSourceCache> biomeCache;
	
	private final FastNoiseLite noise;
	private final FastNoiseLite riverNoise;
	
	public EcoChunkGenerator(long seed, Registry<Biome> biomeRegistry) {
		super(new EcoBiomeSource(biomeRegistry, seed), new StructuresConfig(false));
		
		this.seed = seed;
		this.biomeRegistry = biomeRegistry;
		this.biomeCache = ThreadLocal.withInitial(() -> new BiomeSourceCache(getBiomeSource()));
		
		this.noise = new FastNoiseLite((int) seed);
		this.noise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
		
		this.riverNoise = new FastNoiseLite((int) seed);
		this.riverNoise.SetNoiseType(FastNoiseLite.NoiseType.Cellular);
		this.riverNoise.SetRotationType3D(FastNoiseLite.RotationType3D.ImproveXYPlanes);
		this.riverNoise.SetFrequency(-0.02F);
		this.riverNoise.SetFractalType(FastNoiseLite.FractalType.None);
		this.riverNoise.SetCellularDistanceFunction(FastNoiseLite.CellularDistanceFunction.EuclideanSq);
		this.riverNoise.SetCellularReturnType(FastNoiseLite.CellularReturnType.Distance);
		this.riverNoise.SetCellularJitter(1F);
		this.riverNoise.SetDomainWarpType(FastNoiseLite.DomainWarpType.OpenSimplex2);
		this.riverNoise.SetDomainWarpAmp(70F);
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
				
				int h = getHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
				
				for (int y = h + 5; y > h; --y) {
					Biome biome = getBiome(x, z);
					
					if (t == 0) {
						if (y > 96) {
							chunk.setBlockState(new BlockPos(x, y, z), Blocks.SNOW_BLOCK.getDefaultState(), false);
						} else {
							if (y < 48) {
								chunk.setBlockState(new BlockPos(x, y, z), Blocks.GRAVEL.getDefaultState(), false);
							} else {
								boolean nearWater = false;
								
								for (int wX = x - 1; wX < x + 1; ++wX) {
									for (int wZ = z - 1; wZ < z + 1; ++wZ) {
										if (chunk.getBlockState(new BlockPos(wX, y, wZ)).getFluidState().isIn(FluidTags.WATER)) {
											nearWater = true;
										}
									}
								}
								
								if (nearWater) {
									chunk.setBlockState(new BlockPos(x, y, z), Blocks.GRAVEL.getDefaultState(), false);
								} else {
									if (biome.getCategory() == Biome.Category.DESERT) {
										chunk.setBlockState(new BlockPos(x, y, z), Blocks.SAND.getDefaultState(), false);
									} else {
										chunk.setBlockState(new BlockPos(x, y, z), Blocks.GRASS_BLOCK.getDefaultState(), false);
									}
								}
							}
						}
					} else if (t < 4) {
						if (y < 48) {
							chunk.setBlockState(new BlockPos(x, y, z), Blocks.GRAVEL.getDefaultState(), false);
						} else {
							if (biome.getCategory() == Biome.Category.DESERT) {
								chunk.setBlockState(new BlockPos(x, y, z), Blocks.SANDSTONE.getDefaultState(), false);
							} else {
								chunk.setBlockState(new BlockPos(x, y, z), Blocks.DIRT.getDefaultState(), false);
							}
						}
					} else {
						chunk.setBlockState(new BlockPos(x, y, z), Blocks.STONE.getDefaultState(), false);
					}
					
					++t;
					
					if (t == 5) {
						break;
					}
				}
			}
		}
	}
	
	@Override
	public void populateNoise(WorldAccess world, StructureAccessor accessor, Chunk chunk) {
		for (int x = chunk.getPos().getStartX(); x <= chunk.getPos().getEndX(); ++x) {
			for (int z = chunk.getPos().getStartZ(); z <= chunk.getPos().getEndZ(); ++z) {
				int h = getHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
				
				for (int y = h; y > 0; --y) {
					if (y < 16) {
						chunk.setBlockState(new BlockPos(x, y, z), Blocks.LAVA.getDefaultState(), false);
					} else {
						chunk.setBlockState(new BlockPos(x, y, z), Blocks.STONE.getDefaultState(), false);
					}
				}
				
				if (h < 48) {
					for (int y = 48; y > h; --y) {
						chunk.setBlockState(new BlockPos(x, y, z), Blocks.WATER.getDefaultState(), false);
					}
				}
			}
		}
	}
	
	@Override
	public int getHeight(int x, int z, Heightmap.Type heightmapType) {
		int h = (int) ((getNoiseAverage(x, z, 4)) / 2F * 256F);
		return MathHelper.clamp(h, 0, 255);
	}
	
	@Override
	public BlockView getColumnSample(int x, int z) {
		BlockState[] states = new BlockState[256];
		Arrays.fill(states, Blocks.AIR.getDefaultState());
		return new VerticalBlockSample(states);
	}
	
	public float getNoise(float x, float z, float scale, float depth) {
		float result = 0F;
		
		for (int i = 1; i < depth * 32F; ++i) {
			result += noise.GetNoise(x * scale, z * scale);
			
			scale *= 0.5F;
		}
		
		return (result + 1F) / 16F;
	}
	
	public float getNoiseAverage(float x, float z, int radius) {
		float result = 0F;
		
		for (float rX = x - radius; rX < x + radius; rX += 1) {
			for (float rZ = z - radius; rZ < z + radius; rZ += 1) {
				Biome biome = getBiome((int) rX, (int) rZ);
		
				result += getNoise(rX, rZ, biome.getScale(), biome.getDepth());
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
