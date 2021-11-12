package dev.vini2003

import dev.architectury.platform.forge.EventBuses
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import dev.vini2003.bambootweaks.CBT
import dev.vini2003.bambootweaks.registry.client.BTRenderTypes
import dev.vini2003.bambootweaks.common.registry.BTBlocks
import dev.vini2003.bambootweaks.common.registry.BTRegistries
import dev.vini2003.bambootweaks.common.registry.BTItemGroups
import dev.vini2003.bambootweaks.common.registry.BTItems

@Mod(CBT.Id)
class FOBT {
	init {
		EventBuses.registerModEventBus(CBT.Id, MOD_BUS)
		
		MOD_BUS.addListener(::init)
		MOD_BUS.addListener(::initClient)
		
		BTBlocks.init()
		BTItems.init()
		BTItemGroups.init()
		
		BTRegistries.init()
	}
	
	fun init(event: FMLCommonSetupEvent) {

	}
	
	fun initClient(event: FMLClientSetupEvent) {
		BTRenderTypes.init()
	}
}