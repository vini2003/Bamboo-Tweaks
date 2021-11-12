package dev.vini2003

import me.shedaniel.architectury.platform.forge.EventBuses
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.MOD_CONTEXT
import dev.vini2003.bambootweaks.CommonBambooTweaks
import dev.vini2003.bambootweaks.CommonBambooTweaksClient
import dev.vini2003.bambootweaks.client.registry.BambooTweaksRenderTypes
import dev.vini2003.bambootweaks.common.registry.BambooTweaksBlocks
import dev.vini2003.bambootweaks.common.registry.BambooTweaksDeferredRegisters
import dev.vini2003.bambootweaks.common.registry.BambooTweaksItemGroups
import dev.vini2003.bambootweaks.common.registry.BambooTweaksItems

@Mod(CommonBambooTweaks.Id)
class ForgeBambooTweaks {
	init {
		EventBuses.registerModEventBus(CommonBambooTweaks.Id, MOD_BUS)
		
		MOD_BUS.addListener(::init)
		MOD_BUS.addListener(::initClient)
		
		BambooTweaksBlocks.init()
		BambooTweaksItems.init()
		BambooTweaksItemGroups.init()
		
		BambooTweaksDeferredRegisters.init()
	}
	
	fun init(event: FMLCommonSetupEvent) {

	}
	
	fun initClient(event: FMLClientSetupEvent) {
		BambooTweaksRenderTypes.init()
	}
}