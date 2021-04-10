package vini2003.xyz

import me.shedaniel.architectury.platform.forge.EventBuses
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import vini2003.xyz.bambootweaks.CommonBambooTweaks
import vini2003.xyz.bambootweaks.CommonBambooTweaksClient

@Mod(CommonBambooTweaks.Id)
class ForgeBambooTweaks {
	init {
		EventBuses.registerModEventBus(CommonBambooTweaks.Id, FMLJavaModLoadingContext.get().modEventBus)
		
		CommonBambooTweaks.init()
		CommonBambooTweaksClient.init()
	}
}