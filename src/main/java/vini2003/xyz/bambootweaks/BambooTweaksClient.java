package vini2003.xyz.bambootweaks;

import vini2003.xyz.bambootweaks.registry.client.BambooTweaksBlockRenderLayers;
import vini2003.xyz.bambootweaks.registry.common.BambooTweaksBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class BambooTweaksClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BambooTweaksBlockRenderLayers.initialize();
	}
}
