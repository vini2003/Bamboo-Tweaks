package dev.vini2003.bambootweaks.registry.client

import dev.architectury.registry.client.rendering.RenderTypeRegistry
import net.minecraft.client.render.RenderLayer
import dev.vini2003.bambootweaks.common.registry.BTBlocks

object BambooTweaksRenderTypes {
	fun init() {
		RenderTypeRegistry.register(RenderLayer.getCutout(), BTBlocks.BambooDoor.get())
		RenderTypeRegistry.register(RenderLayer.getCutout(), BTBlocks.BambooLadder.get())
		RenderTypeRegistry.register(RenderLayer.getCutout(), BTBlocks.BambooTorch.get())
		RenderTypeRegistry.register(RenderLayer.getCutout(), BTBlocks.BambooWallTorch.get())
		
		RenderTypeRegistry.register(RenderLayer.getCutout(), BTBlocks.DryBambooDoor.get())
		RenderTypeRegistry.register(RenderLayer.getCutout(), BTBlocks.DryBambooLadder.get())
		RenderTypeRegistry.register(RenderLayer.getCutout(), BTBlocks.DryBambooTorch.get())
		RenderTypeRegistry.register(RenderLayer.getCutout(), BTBlocks.DryBambooWallTorch.get())
	}
}