package dev.vini2003.bambootweaks.common.registry

import dev.architectury.registry.CreativeTabRegistry
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import dev.vini2003.bambootweaks.CBT

object BTItemGroups {
	fun init() {
	
	}
	
	val BambooTweaks: ItemGroup = CreativeTabRegistry.create(CBT.identifier(CBT.Id)) {
		ItemStack(BTBlocks.BambooBlock.orNull!!)
	}
}