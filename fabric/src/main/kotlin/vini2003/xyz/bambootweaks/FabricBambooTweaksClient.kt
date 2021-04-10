package vini2003.xyz.bambootweaks

import net.fabricmc.api.ClientModInitializer

class FabricBambooTweaksClient : ClientModInitializer {
	override fun onInitializeClient() {
		CommonBambooTweaksClient.init()
	}
}