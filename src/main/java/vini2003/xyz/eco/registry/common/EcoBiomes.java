package vini2003.xyz.eco.registry.common;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

public class EcoBiomes {
	public static void initialize() {
	
	}
			
	
	private static int getSkyColor(float temperature) {
		float f = temperature / 3.0F;
		
		f = MathHelper.clamp(f, -1.0F, 1.0F);
		
		return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
	}
}
