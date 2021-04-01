package vini2003.xyz.bambootweaks.registry.client;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import vini2003.xyz.bambootweaks.registry.common.BambooTweaksBlocks;

public class BambooTweaksBlockRenderLayers {
	public static void initialize() {
		BlockRenderLayerMap.INSTANCE.putBlock(BambooTweaksBlocks.BAMBOO_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BambooTweaksBlocks.BAMBOO_LADDER_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BambooTweaksBlocks.BAMBOO_TORCH_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BambooTweaksBlocks.BAMBOO_TORCH_BLOCK_WALL, RenderLayer.getCutout());
		
		BlockRenderLayerMap.INSTANCE.putBlock(BambooTweaksBlocks.DRIED_BAMBOO_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BambooTweaksBlocks.DRIED_BAMBOO_LADDER_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BambooTweaksBlocks.DRIED_BAMBOO_TORCH_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BambooTweaksBlocks.DRIED_BAMBOO_TORCH_BLOCK_WALL, RenderLayer.getCutout());
	}
}
