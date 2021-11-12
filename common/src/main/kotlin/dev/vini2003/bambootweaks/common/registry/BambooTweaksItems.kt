package dev.vini2003.bambootweaks.common.registry

import me.shedaniel.architectury.registry.DeferredRegister
import me.shedaniel.architectury.registry.RegistrySupplier
import net.minecraft.item.Item
import net.minecraft.item.WallStandingBlockItem
import net.minecraft.util.registry.Registry
import dev.vini2003.bambootweaks.CommonBambooTweaks

object BambooTweaksItems {
	private val Items = BambooTweaksDeferredRegisters.Items
	
	fun init() {
	
	}
	
	fun <T : Item> register(name: String, supplier: () -> T): RegistrySupplier<T> {
		return Items.register(CommonBambooTweaks.identifier(name), supplier)
	}
	
	private fun itemSettings() = Item.Settings().group(BambooTweaksItemGroups.BambooTweaks)!!
	
	val BambooTorch = register("bamboo_torch") {
		WallStandingBlockItem(
			BambooTweaksBlocks.BambooTorch.get(),
			BambooTweaksBlocks.BambooWallTorch.get(),
			itemSettings()
		)
	}
	
	val DryBambooTorch = register("dry_bamboo_torch") {
		WallStandingBlockItem(
			BambooTweaksBlocks.DryBambooTorch.get(),
			BambooTweaksBlocks.DryBambooWallTorch.get(),
			itemSettings()
		)
	}
	
	val DryBamboo = register("dry_bamboo") {
		Item(itemSettings())
	}
}