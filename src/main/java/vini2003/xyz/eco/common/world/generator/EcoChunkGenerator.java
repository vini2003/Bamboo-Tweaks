package vini2003.xyz.eco.common.world.generator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.BlockView;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import vini2003.xyz.eco.common.biome.EcoBiomeSource;
import vini2003.xyz.eco.common.world.layer.base.AggregateLayer;
import vini2003.xyz.eco.common.world.layer.implementation.ocean.OceanAggregateLayer;
import vini2003.xyz.eco.common.world.layer.implementation.plain.PlainAggregateLayer;
import vini2003.xyz.eco.common.world.layer.implementation.mountain.MountainAggregateLayer;

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

	private final List<AggregateLayer> aggregateLayers = new ArrayList<>();
	
	public EcoChunkGenerator(long seed, Registry<Biome> biomeRegistry) {
		super(new EcoBiomeSource(biomeRegistry, seed), new StructuresConfig(false));
		
		this.seed = seed;
		this.biomeRegistry = biomeRegistry;
		
		aggregateLayers.add(MountainAggregateLayer.create(seed));
		aggregateLayers.add(PlainAggregateLayer.create(seed));
		aggregateLayers.add(OceanAggregateLayer.create(seed));
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
		aggregateLayers.forEach(it -> it.buildSurface(chunk));
	}
	
	@Override
	public void populateNoise(WorldAccess world, StructureAccessor accessor, Chunk chunk) {
		aggregateLayers.forEach(it -> it.buildSubstrate(chunk));
	}
	
	@Override
	public int getHeight(int x, int z, Heightmap.Type heightmapType) {
		return aggregateLayers.stream().map(it -> it.getHeightLayer().getHeight(x, z)).max(Integer::compareTo).orElse(0);
	}
	
	@Override
	public BlockView getColumnSample(int x, int z) {
		BlockState[] states = new BlockState[256];
		Arrays.fill(states, Blocks.AIR.getDefaultState());
		return new VerticalBlockSample(states);
	}
}
