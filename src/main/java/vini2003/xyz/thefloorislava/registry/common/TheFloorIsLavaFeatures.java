package vini2003.xyz.thefloorislava.registry.common;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import vini2003.xyz.thefloorislava.TheFloorIsLava;
import vini2003.xyz.thefloorislava.common.feature.LavaOceanFeature;

public class TheFloorIsLavaFeatures {
	public static final Feature<DefaultFeatureConfig> OVERWORLD_LAVA_OCEAN = new LavaOceanFeature(DefaultFeatureConfig.CODEC, 80);
	public static final ConfiguredFeature<?, ?> CONFIGURED_OVERWORLD_LAVA_OCEAN = OVERWORLD_LAVA_OCEAN.configure(DefaultFeatureConfig.DEFAULT).decorate(Decorator.COUNT.configure(new CountConfig(1)));
	public static final RegistryKey<ConfiguredFeature<?, ?>> CONFIGURED_OVERWORLD_LAVA_OCEAN_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, TheFloorIsLava.identifier("overworld_lava_ocean"));
	
	public static final Feature<DefaultFeatureConfig> NETHER_LAVA_OCEAN = new LavaOceanFeature(DefaultFeatureConfig.CODEC, 65);
	public static final ConfiguredFeature<?, ?> CONFIGURED_NETHER_LAVA_OCEAN = NETHER_LAVA_OCEAN.configure(DefaultFeatureConfig.DEFAULT).decorate(Decorator.COUNT.configure(new CountConfig(1)));
	public static final RegistryKey<ConfiguredFeature<?, ?>> CONFIGURED_NETHER_LAVA_OCEAN_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, TheFloorIsLava.identifier("nether_lava_ocean"));
	
	public static final Feature<DefaultFeatureConfig> END_LAVA_OCEAN = new LavaOceanFeature(DefaultFeatureConfig.CODEC, 45);
	public static final ConfiguredFeature<?, ?> CONFIGURED_END_LAVA_OCEAN = END_LAVA_OCEAN.configure(DefaultFeatureConfig.DEFAULT).decorate(Decorator.COUNT.configure(new CountConfig(1)));
	public static final RegistryKey<ConfiguredFeature<?, ?>> CONFIGURED_END_LAVA_OCEAN_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, TheFloorIsLava.identifier("end_lava_ocean"));
	
	public static void initialize() {
		Registry.register(Registry.FEATURE, TheFloorIsLava.identifier("overworld_lava_ocean"), OVERWORLD_LAVA_OCEAN);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, CONFIGURED_OVERWORLD_LAVA_OCEAN_KEY.getValue(), CONFIGURED_OVERWORLD_LAVA_OCEAN);
		
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.LAKES, CONFIGURED_OVERWORLD_LAVA_OCEAN_KEY);
		
		Registry.register(Registry.FEATURE, TheFloorIsLava.identifier("nether_lava_ocean"), NETHER_LAVA_OCEAN);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, CONFIGURED_NETHER_LAVA_OCEAN_KEY.getValue(), CONFIGURED_NETHER_LAVA_OCEAN);
		
		BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.LAKES, CONFIGURED_NETHER_LAVA_OCEAN_KEY);
		
		Registry.register(Registry.FEATURE, TheFloorIsLava.identifier("end_lava_ocean"), END_LAVA_OCEAN);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, CONFIGURED_END_LAVA_OCEAN_KEY.getValue(), CONFIGURED_END_LAVA_OCEAN);
		
		BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.LAKES, CONFIGURED_END_LAVA_OCEAN_KEY);
	}
}
