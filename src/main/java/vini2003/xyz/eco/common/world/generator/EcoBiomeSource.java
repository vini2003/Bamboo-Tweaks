	package vini2003.xyz.eco.common.world.generator;
	
	import Auburn.FastNoiseLite.Java.FastNoiseLite;
	import com.google.common.collect.ImmutableList;
	import com.mojang.serialization.Codec;
	import com.mojang.serialization.codecs.RecordCodecBuilder;
	import net.minecraft.util.registry.Registry;
	import net.minecraft.util.registry.RegistryKey;
	import net.minecraft.util.registry.RegistryLookupCodec;
	import net.minecraft.world.biome.Biome;
	import net.minecraft.world.biome.BiomeKeys;
	import net.minecraft.world.biome.source.BiomeSource;
	import vini2003.xyz.eco.common.world.BiomeSourceCache;
	
	import java.util.List;
	
	public class EcoBiomeSource extends BiomeSource {
		public static final Codec<EcoBiomeSource> CODEC =
				RecordCodecBuilder.create(instance ->
						instance.group(
								RegistryLookupCodec
										.of(Registry.BIOME_KEY)
										.forGetter((biomeSource) -> biomeSource.biomeRegistry),
						Codec.LONG
								.fieldOf("seed")
								.stable()
								.forGetter((biomeSource) -> biomeSource.seed))
								.apply(instance, instance.stable(EcoBiomeSource::new)));
		
		private static final ImmutableList<RegistryKey<Biome>> BIOMES;
		
		private final long seed;
		private final Registry<Biome> biomeRegistry;
		
		private final FastNoiseLite noise;
		
		public EcoBiomeSource(Registry<Biome> biomeRegistry, long seed) {
			super(ImmutableList.of());
			
			this.seed = seed;
			this.biomeRegistry = biomeRegistry;
			
			this.noise = new FastNoiseLite((int) seed);
			this.noise.SetNoiseType(FastNoiseLite.NoiseType.Perlin);
		}
		
		@Override
		protected Codec<? extends BiomeSource> getCodec() {
			return CODEC;
		}
		
		@Override
		public BiomeSource withSeed(long seed) {
			return new EcoBiomeSource(biomeRegistry, seed);
		}
		
		@Override
		public Biome getBiomeForNoiseGen(int biomeX, int biomeY, int biomeZ) {
			float sample = noise.GetNoise(biomeX / 512F, biomeZ / 512F) + 1F;
			
			float steps = 2F / BIOMES.size();
			
			return biomeRegistry.get(BIOMES.get((int) (sample / steps)));
		}
		
		static {
			BIOMES = ImmutableList.of(BiomeKeys.OCEAN, BiomeKeys.PLAINS, BiomeKeys.DESERT, BiomeKeys.MOUNTAINS, BiomeKeys.FOREST, BiomeKeys.TAIGA, BiomeKeys.SWAMP, BiomeKeys.RIVER, BiomeKeys.FROZEN_OCEAN, BiomeKeys.FROZEN_RIVER, BiomeKeys.SNOWY_TUNDRA, BiomeKeys.SNOWY_MOUNTAINS, new RegistryKey[]{BiomeKeys.MUSHROOM_FIELDS, BiomeKeys.MUSHROOM_FIELD_SHORE, BiomeKeys.BEACH, BiomeKeys.DESERT_HILLS, BiomeKeys.WOODED_HILLS, BiomeKeys.TAIGA_HILLS, BiomeKeys.MOUNTAIN_EDGE, BiomeKeys.JUNGLE, BiomeKeys.JUNGLE_HILLS, BiomeKeys.JUNGLE_EDGE, BiomeKeys.DEEP_OCEAN, BiomeKeys.STONE_SHORE, BiomeKeys.SNOWY_BEACH, BiomeKeys.BIRCH_FOREST, BiomeKeys.BIRCH_FOREST_HILLS, BiomeKeys.DARK_FOREST, BiomeKeys.SNOWY_TAIGA, BiomeKeys.SNOWY_TAIGA_HILLS, BiomeKeys.GIANT_TREE_TAIGA, BiomeKeys.GIANT_TREE_TAIGA_HILLS, BiomeKeys.WOODED_MOUNTAINS, BiomeKeys.SAVANNA, BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.BADLANDS, BiomeKeys.WOODED_BADLANDS_PLATEAU, BiomeKeys.BADLANDS_PLATEAU, BiomeKeys.WARM_OCEAN, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.COLD_OCEAN, BiomeKeys.DEEP_WARM_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.SUNFLOWER_PLAINS, BiomeKeys.DESERT_LAKES, BiomeKeys.GRAVELLY_MOUNTAINS, BiomeKeys.FLOWER_FOREST, BiomeKeys.TAIGA_MOUNTAINS, BiomeKeys.SWAMP_HILLS, BiomeKeys.ICE_SPIKES, BiomeKeys.MODIFIED_JUNGLE, BiomeKeys.MODIFIED_JUNGLE_EDGE, BiomeKeys.TALL_BIRCH_FOREST, BiomeKeys.TALL_BIRCH_HILLS, BiomeKeys.DARK_FOREST_HILLS, BiomeKeys.SNOWY_TAIGA_MOUNTAINS, BiomeKeys.GIANT_SPRUCE_TAIGA, BiomeKeys.GIANT_SPRUCE_TAIGA_HILLS, BiomeKeys.MODIFIED_GRAVELLY_MOUNTAINS, BiomeKeys.SHATTERED_SAVANNA, BiomeKeys.SHATTERED_SAVANNA_PLATEAU, BiomeKeys.ERODED_BADLANDS, BiomeKeys.MODIFIED_WOODED_BADLANDS_PLATEAU, BiomeKeys.MODIFIED_BADLANDS_PLATEAU});
		}
	}
