package dev.vini2003.bambootweaks

import net.fabricmc.api.ClientModInitializer

class FABTClient : ClientModInitializer {
	override fun onInitializeClient() {
		CBTClient.init()
	}
}