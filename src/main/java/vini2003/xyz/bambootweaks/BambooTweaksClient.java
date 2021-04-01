package vini2003.xyz.bambootweaks;

import vini2003.xyz.bambootweaks.registry.BambooTweaksBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class BambooTweaksClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(BambooTweaksBlocks.BAMBOO_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BambooTweaksBlocks.BAMBOO_LADDER_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BambooTweaksBlocks.BAMBOO_TORCH_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BambooTweaksBlocks.BAMBOO_TORCH_BLOCK_WALL, RenderLayer.getCutout());
	}
}
