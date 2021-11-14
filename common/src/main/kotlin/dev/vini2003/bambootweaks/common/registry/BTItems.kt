package dev.vini2003.bambootweaks.common.registry

import dev.architectury.registry.registries.RegistrySupplier
import net.minecraft.item.Item
import net.minecraft.item.WallStandingBlockItem
import dev.vini2003.bambootweaks.CBT
import dev.vini2003.bambootweaks.common.registry.BTRegistries.Items

object BTItems {
	fun init() {
	
	}
	
	private fun <T : Item> register(name: String, supplier: () -> T): RegistrySupplier<T> {
		return Items.register(CBT.identifier(name), supplier)
	}
	
	private fun itemSettings() = Item.Settings().group(BTItemGroups.BambooTweaks)!!
	
	val BambooTorch = register("bamboo_torch") {
		WallStandingBlockItem(BTBlocks.BambooTorch.orNull, BTBlocks.BambooWallTorch.orNull, itemSettings())
	}
	
	val DryBambooTorch = register("dry_bamboo_torch") {
		WallStandingBlockItem(BTBlocks.DryBambooTorch.orNull, BTBlocks.DryBambooWallTorch.orNull, itemSettings())
	}
	
	val DryBamboo = register("dry_bamboo") {
		Item(itemSettings())
	}
}