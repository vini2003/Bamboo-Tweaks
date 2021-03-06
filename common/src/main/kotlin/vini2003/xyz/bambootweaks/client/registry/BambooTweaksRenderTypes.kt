package vini2003.xyz.bambootweaks.client.registry

import me.shedaniel.architectury.registry.RenderTypes
import net.minecraft.client.render.RenderLayer
import vini2003.xyz.bambootweaks.common.registry.BambooTweaksBlocks

object BambooTweaksRenderTypes {
	fun init() {
		RenderTypes.register(RenderLayer.getCutout(), BambooTweaksBlocks.BambooDoor.get())
		RenderTypes.register(RenderLayer.getCutout(), BambooTweaksBlocks.BambooLadder.get())
		RenderTypes.register(RenderLayer.getCutout(), BambooTweaksBlocks.BambooTorch.get())
		RenderTypes.register(RenderLayer.getCutout(), BambooTweaksBlocks.BambooWallTorch.get())
		
		RenderTypes.register(RenderLayer.getCutout(), BambooTweaksBlocks.DryBambooDoor.get())
		RenderTypes.register(RenderLayer.getCutout(), BambooTweaksBlocks.DryBambooLadder.get())
		RenderTypes.register(RenderLayer.getCutout(), BambooTweaksBlocks.DryBambooTorch.get())
		RenderTypes.register(RenderLayer.getCutout(), BambooTweaksBlocks.DryBambooWallTorch.get())
	}
}