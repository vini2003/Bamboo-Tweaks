package dev.vini2003.bambootweaks

import net.minecraft.util.Identifier
import dev.vini2003.bambootweaks.common.registry.BTBlocks
import dev.vini2003.bambootweaks.common.registry.BTRegistries
import dev.vini2003.bambootweaks.common.registry.BTItemGroups
import dev.vini2003.bambootweaks.common.registry.BTItems

object CBT {
	const val Id = "bambootweaks"
	
	fun identifier(path: String): Identifier {
		return Identifier(Id, path)
	}
	
	fun init() {
		BTBlocks.init()
		BTItems.init()
		BTItemGroups.init()
		BTRegistries.init()
	}
}

