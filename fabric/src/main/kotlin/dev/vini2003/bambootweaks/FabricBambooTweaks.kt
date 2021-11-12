package dev.vini2003.bambootweaks

import net.fabricmc.api.ModInitializer

class FabricBambooTweaks : ModInitializer {
	override fun onInitialize() {
		CommonBambooTweaks.init()
	}
}