package vini2003.xyz.eco.common.world.generator;

import Auburn.FastNoiseLite.Java.FastNoiseLite;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.registry.Registry;
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
	
	private final long seed;
	private final Registry<Biome> biomeRegistry;
	
	private final FastNoiseLite noise;
	
	public EcoBiomeSource(Registry<Biome> biomeRegistry, long seed) {
		super(ImmutableList.of());
		
		this.seed = seed;
		this.biomeRegistry = biomeRegistry;
		
		this.noise = new FastNoiseLite((int) seed);
		this.noise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
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
		float sample = noise.GetNoise(biomeX / 8F, biomeZ / 8F);
		
		if (biomeY > 32) {
			return biomeRegistry.get(BiomeKeys.SNOWY_MOUNTAINS);
		} else {
			if (sample > 0F && sample < 0.5F) {
				return biomeRegistry.get(BiomeKeys.PLAINS);
			} else if (sample > 0.5F) {
				return biomeRegistry.get(BiomeKeys.PLAINS);
			} else if (sample < 0F && sample > -0.5F) {
				return biomeRegistry.get(BiomeKeys.DESERT);
			} else {
				return biomeRegistry.get(BiomeKeys.MOUNTAINS);
			}
		}
	}
}
