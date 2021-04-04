	package vini2003.xyz.eco.common.biome;
	
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
		
		private final FastNoiseLite waterNoise;
		private final FastNoiseLite humidityNoise;
		private final FastNoiseLite temperatureNoise;
		
		public EcoBiomeSource(Registry<Biome> biomeRegistry, long seed) {
			super(ImmutableList.of());
			
			this.seed = seed;
			this.biomeRegistry = biomeRegistry;
			
			this.waterNoise = new FastNoiseLite((int) seed << 2);
			this.waterNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
			
			this.humidityNoise = new FastNoiseLite((int) seed >> 2);
			this.humidityNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
			this.humidityNoise.SetFrequency(-0.005F);
			this.humidityNoise.SetFractalType(FastNoiseLite.FractalType.None);
			
			this.temperatureNoise = new FastNoiseLite((int) seed << 4);
			this.temperatureNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
			this.temperatureNoise.SetFrequency(-0.005F);
			this.temperatureNoise.SetFractalType(FastNoiseLite.FractalType.None);
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
			float humidity = humidityNoise.GetNoise(biomeX, biomeZ);
			float temperature = temperatureNoise.GetNoise(biomeX, biomeZ);
			
			if (humidity > 0F) {
				if (temperature > 0.5F) {
					return biomeRegistry.get(BiomeKeys.JUNGLE);
				} else if (temperature > 0F) {
					if (temperature > 0.25F) {
						return biomeRegistry.get(BiomeKeys.FOREST);
					} else {
						return biomeRegistry.get(BiomeKeys.BIRCH_FOREST);
					}
				} else {
					return biomeRegistry.get(BiomeKeys.SNOWY_TUNDRA);
				}
			} else {
				if (temperature > 0.5F) {
					return biomeRegistry.get(BiomeKeys.BADLANDS);
				} else if (temperature > 0F) {
					return biomeRegistry.get(BiomeKeys.DESERT);
				} else {
					return biomeRegistry.get(BiomeKeys.SNOWY_MOUNTAINS);
				}
			}
		}
		
		static {
			BIOMES = ImmutableList.of(BiomeKeys.OCEAN, BiomeKeys.PLAINS, BiomeKeys.DESERT, BiomeKeys.MOUNTAINS, BiomeKeys.FOREST, BiomeKeys.TAIGA, BiomeKeys.SWAMP, BiomeKeys.RIVER, BiomeKeys.FROZEN_OCEAN, BiomeKeys.FROZEN_RIVER, BiomeKeys.SNOWY_TUNDRA, BiomeKeys.SNOWY_MOUNTAINS, new RegistryKey[]{BiomeKeys.MUSHROOM_FIELDS, BiomeKeys.MUSHROOM_FIELD_SHORE, BiomeKeys.BEACH, BiomeKeys.DESERT_HILLS, BiomeKeys.WOODED_HILLS, BiomeKeys.TAIGA_HILLS, BiomeKeys.MOUNTAIN_EDGE, BiomeKeys.JUNGLE, BiomeKeys.JUNGLE_HILLS, BiomeKeys.JUNGLE_EDGE, BiomeKeys.DEEP_OCEAN, BiomeKeys.STONE_SHORE, BiomeKeys.SNOWY_BEACH, BiomeKeys.BIRCH_FOREST, BiomeKeys.BIRCH_FOREST_HILLS, BiomeKeys.DARK_FOREST, BiomeKeys.SNOWY_TAIGA, BiomeKeys.SNOWY_TAIGA_HILLS, BiomeKeys.GIANT_TREE_TAIGA, BiomeKeys.GIANT_TREE_TAIGA_HILLS, BiomeKeys.WOODED_MOUNTAINS, BiomeKeys.SAVANNA, BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.BADLANDS, BiomeKeys.WOODED_BADLANDS_PLATEAU, BiomeKeys.BADLANDS_PLATEAU, BiomeKeys.WARM_OCEAN, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.COLD_OCEAN, BiomeKeys.DEEP_WARM_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.SUNFLOWER_PLAINS, BiomeKeys.DESERT_LAKES, BiomeKeys.GRAVELLY_MOUNTAINS, BiomeKeys.FLOWER_FOREST, BiomeKeys.TAIGA_MOUNTAINS, BiomeKeys.SWAMP_HILLS, BiomeKeys.ICE_SPIKES, BiomeKeys.MODIFIED_JUNGLE, BiomeKeys.MODIFIED_JUNGLE_EDGE, BiomeKeys.TALL_BIRCH_FOREST, BiomeKeys.TALL_BIRCH_HILLS, BiomeKeys.DARK_FOREST_HILLS, BiomeKeys.SNOWY_TAIGA_MOUNTAINS, BiomeKeys.GIANT_SPRUCE_TAIGA, BiomeKeys.GIANT_SPRUCE_TAIGA_HILLS, BiomeKeys.MODIFIED_GRAVELLY_MOUNTAINS, BiomeKeys.SHATTERED_SAVANNA, BiomeKeys.SHATTERED_SAVANNA_PLATEAU, BiomeKeys.ERODED_BADLANDS, BiomeKeys.MODIFIED_WOODED_BADLANDS_PLATEAU, BiomeKeys.MODIFIED_BADLANDS_PLATEAU});
		}
	}
