package vini2003.xyz.bambootweaks.client.registry

import me.shedaniel.architectury.registry.RenderTypes
import net.minecraft.client.render.RenderLayer
import vini2003.xyz.bambootweaks.common.registry.BambooTweaksBlocks

object BambooTweaksRenderTypes {
	fun init() {
		RenderTypes.register(RenderLayer.getCutout(), BambooTweaksBlocks.BambooDoor.orNull!!)
		RenderTypes.register(RenderLayer.getCutout(), BambooTweaksBlocks.BambooLadder.orNull!!)
		RenderTypes.register(RenderLayer.getCutout(), BambooTweaksBlocks.BambooTorch.orNull!!)
		RenderTypes.register(RenderLayer.getCutout(), BambooTweaksBlocks.BambooWallTorch.orNull!!)
		
		RenderTypes.register(RenderLayer.getCutout(), BambooTweaksBlocks.DriedBambooDoor.orNull!!)
		RenderTypes.register(RenderLayer.getCutout(), BambooTweaksBlocks.DriedBambooLadder.orNull!!)
		RenderTypes.register(RenderLayer.getCutout(), BambooTweaksBlocks.DriedBambooTorch.orNull!!)
		RenderTypes.register(RenderLayer.getCutout(), BambooTweaksBlocks.DriedBambooWallTorch.orNull!!)
	}
}