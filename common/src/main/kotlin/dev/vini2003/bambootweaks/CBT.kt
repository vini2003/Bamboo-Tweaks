package dev.vini2003.bambootweaks

import dev.vini2003.bambootweaks.common.registry.*
import net.minecraft.util.Identifier

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
		
		BTFuels.init()
	}
}

