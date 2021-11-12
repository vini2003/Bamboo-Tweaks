package dev.vini2003.bambootweaks.common.registry

import me.shedaniel.architectury.registry.CreativeTabs
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import dev.vini2003.bambootweaks.CommonBambooTweaks

object BambooTweaksItemGroups {
	fun init() {
	
	}
	
	val BambooTweaks: ItemGroup = CreativeTabs.create(CommonBambooTweaks.identifier(CommonBambooTweaks.Id)) { ItemStack(
		BambooTweaksBlocks.Bamboo.orNull!!
	) }
}