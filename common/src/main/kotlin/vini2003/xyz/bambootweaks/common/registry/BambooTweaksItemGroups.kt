package vini2003.xyz.bambootweaks.common.registry

import me.shedaniel.architectury.registry.CreativeTabs
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import vini2003.xyz.bambootweaks.CommonBambooTweaks

object BambooTweaksItemGroups {
	fun init() {
	
	}
	
	val BambooTweaks: ItemGroup = CreativeTabs.create(CommonBambooTweaks.identifier(CommonBambooTweaks.Id)) { ItemStack(
		BambooTweaksBlocks.Bamboo.orNull!!
	) }
}