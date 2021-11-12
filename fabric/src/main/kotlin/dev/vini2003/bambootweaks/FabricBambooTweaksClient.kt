package dev.vini2003.bambootweaks

import net.fabricmc.api.ClientModInitializer

class FabricBambooTweaksClient : ClientModInitializer {
	override fun onInitializeClient() {
		CommonBambooTweaksClient.init()
	}
}