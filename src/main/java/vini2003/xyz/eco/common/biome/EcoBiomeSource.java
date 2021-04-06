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
	import vini2003.xyz.eco.common.util.NoiseUtils;
	import vini2003.xyz.eco.common.world.layer.base.AggregateLayer;
	import vini2003.xyz.eco.common.world.layer.implementation.hill.HillAggregateLayer;
	import vini2003.xyz.eco.common.world.layer.implementation.mountain.MountainAggregateLayer;
	import vini2003.xyz.eco.common.world.layer.implementation.ocean.OceanAggregateLayer;
	import vini2003.xyz.eco.common.world.layer.implementation.plain.PlainAggregateLayer;
	
	import java.util.Arrays;
	import java.util.Comparator;
	
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
		
		private final AggregateLayer mountainLayer;
		private final AggregateLayer plainLayer;
		private final AggregateLayer hillLayer;
		private final AggregateLayer oceanLayer;
		
		private final FastNoiseLite plainsNoise;
		private final FastNoiseLite oakForestNoise;
		private final FastNoiseLite birchForestNoise;
		private final FastNoiseLite darkForestNoise;
		
		private final FastNoiseLite maskNoise;
		
		public EcoBiomeSource(Registry<Biome> biomeRegistry, long seed) {
			super(ImmutableList.of());
			
			this.seed = seed;
			this.biomeRegistry = biomeRegistry;
			
			this.mountainLayer = MountainAggregateLayer.create(seed);
			this.plainLayer = PlainAggregateLayer.create(seed);
			this.hillLayer = HillAggregateLayer.create(seed);
			this.oceanLayer = OceanAggregateLayer.create(seed);
			
			this.plainsNoise = new FastNoiseLite((int) seed >> 2);
			
			this.oakForestNoise = new FastNoiseLite((int) seed << 2);
			
			this.birchForestNoise = new FastNoiseLite((int) seed >> 4);
			
			this.darkForestNoise = new FastNoiseLite((int) seed << 4);
			
			this.maskNoise = new FastNoiseLite((int) seed << 4);
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
			int mountainHeight = mountainLayer.getHeightLayer().getHeight(biomeX, biomeZ);
			int plainHeight = plainLayer.getHeightLayer().getHeight(biomeX, biomeZ);
			int hillHeight = hillLayer.getHeightLayer().getHeight(biomeX, biomeZ);
			int oceanHeight = oceanLayer.getHeightLayer().getHeight(biomeX, biomeZ);
			
			if (mountainHeight > plainHeight && mountainHeight > hillHeight && mountainHeight > oceanHeight) {
				return biomeRegistry.get(BiomeKeys.MOUNTAINS);
			} else if (hillHeight > oceanHeight || plainHeight > oceanHeight) {
				float plainsSample = NoiseUtils.getNoise(plainsNoise, biomeX / 64.0F, biomeZ / 64.0F, 2, 1.0F, 1.0F, 0.33F) + maskNoise.GetNoise(biomeX, biomeZ);
				float oakForestSample = NoiseUtils.getNoise(oakForestNoise, biomeX / 64.0F, biomeZ / 64.0F, 2, 1.0F, 1.0F, 0.33F) + maskNoise.GetNoise(biomeX, biomeZ);
				float birchForestSample = NoiseUtils.getNoise(birchForestNoise, biomeX / 64.0F, biomeZ / 64.0F, 2, 1.0F, 1.0F, 0.33F) + maskNoise.GetNoise(biomeX, biomeZ);
				float darkForestSample = NoiseUtils.getNoise(darkForestNoise, biomeX / 64.0F, biomeZ / 64.0F, 2, 1.0F, 1.0F, 0.33F) + maskNoise.GetNoise(biomeX, biomeZ);
				
				return Arrays.stream(new Float[] { plainsSample, oakForestSample, birchForestSample, darkForestSample }).max(Float::compareTo).map(it -> {
					return it == plainsSample ? biomeRegistry.get(BiomeKeys.PLAINS) :
							it == oakForestSample ? biomeRegistry.get(BiomeKeys.FOREST) :
									it == birchForestSample ? biomeRegistry.get(BiomeKeys.BIRCH_FOREST) : biomeRegistry.get(BiomeKeys.DARK_FOREST);
					}).orElse(biomeRegistry.get(BiomeKeys.THE_VOID));
			} else {
				return biomeRegistry.get(BiomeKeys.OCEAN);
			}
		}
		
		static {
			BIOMES = ImmutableList.of(BiomeKeys.OCEAN, BiomeKeys.PLAINS, BiomeKeys.DESERT, BiomeKeys.MOUNTAINS, BiomeKeys.FOREST, BiomeKeys.TAIGA, BiomeKeys.SWAMP, BiomeKeys.RIVER, BiomeKeys.FROZEN_OCEAN, BiomeKeys.FROZEN_RIVER, BiomeKeys.SNOWY_TUNDRA, BiomeKeys.SNOWY_MOUNTAINS, new RegistryKey[]{BiomeKeys.MUSHROOM_FIELDS, BiomeKeys.MUSHROOM_FIELD_SHORE, BiomeKeys.BEACH, BiomeKeys.DESERT_HILLS, BiomeKeys.WOODED_HILLS, BiomeKeys.TAIGA_HILLS, BiomeKeys.MOUNTAIN_EDGE, BiomeKeys.JUNGLE, BiomeKeys.JUNGLE_HILLS, BiomeKeys.JUNGLE_EDGE, BiomeKeys.DEEP_OCEAN, BiomeKeys.STONE_SHORE, BiomeKeys.SNOWY_BEACH, BiomeKeys.BIRCH_FOREST, BiomeKeys.BIRCH_FOREST_HILLS, BiomeKeys.DARK_FOREST, BiomeKeys.SNOWY_TAIGA, BiomeKeys.SNOWY_TAIGA_HILLS, BiomeKeys.GIANT_TREE_TAIGA, BiomeKeys.GIANT_TREE_TAIGA_HILLS, BiomeKeys.WOODED_MOUNTAINS, BiomeKeys.SAVANNA, BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.BADLANDS, BiomeKeys.WOODED_BADLANDS_PLATEAU, BiomeKeys.BADLANDS_PLATEAU, BiomeKeys.WARM_OCEAN, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.COLD_OCEAN, BiomeKeys.DEEP_WARM_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.SUNFLOWER_PLAINS, BiomeKeys.DESERT_LAKES, BiomeKeys.GRAVELLY_MOUNTAINS, BiomeKeys.FLOWER_FOREST, BiomeKeys.TAIGA_MOUNTAINS, BiomeKeys.SWAMP_HILLS, BiomeKeys.ICE_SPIKES, BiomeKeys.MODIFIED_JUNGLE, BiomeKeys.MODIFIED_JUNGLE_EDGE, BiomeKeys.TALL_BIRCH_FOREST, BiomeKeys.TALL_BIRCH_HILLS, BiomeKeys.DARK_FOREST_HILLS, BiomeKeys.SNOWY_TAIGA_MOUNTAINS, BiomeKeys.GIANT_SPRUCE_TAIGA, BiomeKeys.GIANT_SPRUCE_TAIGA_HILLS, BiomeKeys.MODIFIED_GRAVELLY_MOUNTAINS, BiomeKeys.SHATTERED_SAVANNA, BiomeKeys.SHATTERED_SAVANNA_PLATEAU, BiomeKeys.ERODED_BADLANDS, BiomeKeys.MODIFIED_WOODED_BADLANDS_PLATEAU, BiomeKeys.MODIFIED_BADLANDS_PLATEAU});
		}
	}
