package vini2003.xyz.eco.common.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeAccessType;

public class EcoBiomeAccessType implements BiomeAccessType {
	public static final EcoBiomeAccessType INSTANCE = new EcoBiomeAccessType();
	
	@Override
	public Biome getBiome(long seed, int x, int y, int z, BiomeAccess.Storage storage) {
		return storage.getBiomeForNoiseGen(x, y, z);
	}
}
