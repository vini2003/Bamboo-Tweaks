package vini2003.xyz.bambootweaks.common.registry

import me.shedaniel.architectury.registry.DeferredRegister
import net.minecraft.util.registry.Registry
import vini2003.xyz.bambootweaks.CommonBambooTweaks

object BambooTweaksDeferredRegisters {
	val Blocks = DeferredRegister.create(CommonBambooTweaks.Id, Registry.BLOCK_KEY)
	val Items = DeferredRegister.create(CommonBambooTweaks.Id, Registry.ITEM_KEY)
	
	fun init() {
		Blocks.register()
		Items.register()
	}
}