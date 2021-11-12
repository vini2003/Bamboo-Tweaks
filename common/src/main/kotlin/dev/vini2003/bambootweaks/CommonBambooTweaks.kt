package dev.vini2003.bambootweaks

import net.minecraft.util.Identifier
import dev.vini2003.bambootweaks.common.registry.BambooTweaksBlocks
import dev.vini2003.bambootweaks.common.registry.BambooTweaksDeferredRegisters
import dev.vini2003.bambootweaks.common.registry.BambooTweaksItemGroups
import dev.vini2003.bambootweaks.common.registry.BambooTweaksItems

object CommonBambooTweaks {
	const val Id = "bambootweaks"
	
	fun identifier(path: String): Identifier {
		return Identifier(Id, path)
	}
	
	fun init() {
		BambooTweaksBlocks.init()
		BambooTweaksItems.init()
		BambooTweaksItemGroups.init()
		BambooTweaksDeferredRegisters.init()
	}
}

