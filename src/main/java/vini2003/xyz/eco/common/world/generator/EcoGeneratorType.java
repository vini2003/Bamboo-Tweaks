package vini2003.xyz.eco.common.world.generator;

import net.minecraft.client.world.GeneratorType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;

public class EcoGeneratorType extends GeneratorType {
	public EcoGeneratorType() {
		super("eco");
	}
	
	@Override
	protected ChunkGenerator getChunkGenerator(Registry<Biome> biomeRegistry, Registry<ChunkGeneratorSettings> chunkGeneratorSettingsRegistry, long seed) {
		return new EcoChunkGenerator(seed, biomeRegistry);
	}
}
