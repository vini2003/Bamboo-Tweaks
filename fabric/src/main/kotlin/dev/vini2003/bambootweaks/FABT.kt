package dev.vini2003.bambootweaks

import net.fabricmc.api.ModInitializer

class FABT : ModInitializer {
	override fun onInitialize() {
		CBT.init()
	}
}