package vini2003.xyz.voidrising;

import net.fabricmc.api.ClientModInitializer;
import vini2003.xyz.voidrising.registry.client.VoidRisingNetworking;

public class VoidRisingClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		VoidRisingNetworking.initialize();
	}
}
