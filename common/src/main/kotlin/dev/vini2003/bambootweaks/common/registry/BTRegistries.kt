package dev.vini2003.bambootweaks.common.registry

import dev.architectury.registry.registries.DeferredRegister
import net.minecraft.util.registry.Registry
import dev.vini2003.bambootweaks.CBT

object BTRegistries {
	val Blocks = DeferredRegister.create(CBT.Id, Registry.BLOCK_KEY)
	val Items = DeferredRegister.create(CBT.Id, Registry.ITEM_KEY)
	
	fun init() {
		Blocks.register()
		Items.register()
	}
}