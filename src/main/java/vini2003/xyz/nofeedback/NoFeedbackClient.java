package vini2003.xyz.nofeedback;

import net.fabricmc.api.ClientModInitializer;
import vini2003.xyz.nofeedback.registry.client.NoFeedbackNetworking;

public class NoFeedbackClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		NoFeedbackNetworking.initialize();
	}
}
