package vini2003.xyz

import me.shedaniel.architectury.platform.forge.EventBuses
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.MOD_CONTEXT
import vini2003.xyz.bambootweaks.CommonBambooTweaks
import vini2003.xyz.bambootweaks.CommonBambooTweaksClient
import vini2003.xyz.bambootweaks.client.registry.BambooTweaksRenderTypes
import vini2003.xyz.bambootweaks.common.registry.BambooTweaksBlocks
import vini2003.xyz.bambootweaks.common.registry.BambooTweaksDeferredRegisters
import vini2003.xyz.bambootweaks.common.registry.BambooTweaksItemGroups
import vini2003.xyz.bambootweaks.common.registry.BambooTweaksItems

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