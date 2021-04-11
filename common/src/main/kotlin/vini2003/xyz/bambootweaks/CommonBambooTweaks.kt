package vini2003.xyz.bambootweaks

import net.minecraft.util.Identifier
import vini2003.xyz.bambootweaks.common.registry.BambooTweaksBlocks
import vini2003.xyz.bambootweaks.common.registry.BambooTweaksDeferredRegisters
import vini2003.xyz.bambootweaks.common.registry.BambooTweaksItemGroups
import vini2003.xyz.bambootweaks.common.registry.BambooTweaksItems

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

