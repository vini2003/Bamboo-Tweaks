package vini2003.xyz.eco.common.world.generator;

import Auburn.FastNoiseLite.Java.FastNoiseLite;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.BlockView;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import vini2003.xyz.eco.common.biome.EcoBiomeSource;
import vini2003.xyz.eco.common.util.BiomeUtils;
import vini2003.xyz.eco.common.util.NoiseUtils;
import vini2003.xyz.eco.common.world.biome.base.AggregateLayer;
import vini2003.xyz.eco.common.world.biome.implementation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	private final AggregateLayer mountains;
	private final AggregateLayer ocean;
	
	private final AggregateLayer desert;
	private final AggregateLayer forest;
	private final AggregateLayer plain;
	
	private final FastNoiseLite maskNoise;
	
	public EcoChunkGenerator(long seed, Registry<Biome> biomeRegistry) {
		super(new EcoBiomeSource(biomeRegistry, seed), new StructuresConfig(false));
		
		this.seed = seed;
		this.biomeRegistry = biomeRegistry;
		
		this.mountains = Mountains.Aggregate.create(seed);
		this.ocean = Ocean.Aggregate.create(seed);
		
		this.desert = Desert.Aggregate.create(seed);
		this.forest = Forest.Aggregate.create(seed);
		this.plain = Plain.Aggregate.create(seed);
		
		this.maskNoise = new FastNoiseLite((int) seed);
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
				mountains.buildSurface(chunk, x, z);
				
				forest.buildSurface(chunk, x, z);
				
				ocean.buildSurface(chunk, x, z);
			}
		}
	}
	
	@Override
	public void populateNoise(WorldAccess world, StructureAccessor accessor, Chunk chunk) {
		for (int x = chunk.getPos().getStartX(); x <= chunk.getPos().getEndX(); ++x) {
			for (int z = chunk.getPos().getStartZ(); z <= chunk.getPos().getEndZ(); ++z) {
				mountains.buildSubstrate(chunk, x, z);
				
				forest.buildSubstrate(chunk, x, z);
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
}
