package vini2003.xyz.bambootweaks.common.registry

import me.shedaniel.architectury.registry.DeferredRegister
import me.shedaniel.architectury.registry.RegistrySupplier
import net.minecraft.item.Item
import net.minecraft.item.WallStandingBlockItem
import net.minecraft.util.registry.Registry
import vini2003.xyz.bambootweaks.CommonBambooTweaks

object BambooTweaksItems {
	val ItemRegistry = DeferredRegister.create(CommonBambooTweaks.Id, Registry.ITEM_KEY)
	
	fun init() {
		ItemRegistry.register()
	}
	
	fun <T : Item> register(name: String, itemSupplier: () -> T): RegistrySupplier<T> {
		return ItemRegistry.register(CommonBambooTweaks.identifier(name), itemSupplier)
	}
	
	private fun itemSettings() = Item.Settings().group(BambooTweaksItemGroups.BambooTweaks)!!
	
	val BambooTorch = register(
		"bamboo_torch") {
		WallStandingBlockItem(
			BambooTweaksBlocks.BambooTorch.orNull!!,
			BambooTweaksBlocks.BambooWallTorch.orNull!!,
			itemSettings()
		)
	}
	
	val DriedBambooTorch = register(
		"dried_bamboo_torch") {
		WallStandingBlockItem(
			BambooTweaksBlocks.DriedBambooTorch.orNull!!,
			BambooTweaksBlocks.DriedBambooWallTorch.orNull!!,
			itemSettings()
		)
	}
	
	val DriedBamboo = register(
		"dried_bamboo") {
		Item(itemSettings())
	}
}