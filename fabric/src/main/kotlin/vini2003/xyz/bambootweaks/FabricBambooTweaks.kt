package vini2003.xyz.bambootweaks

import net.fabricmc.api.ModInitializer

class FabricBambooTweaks : ModInitializer {
	override fun onInitialize() {
		CommonBambooTweaks.init()
	}
}