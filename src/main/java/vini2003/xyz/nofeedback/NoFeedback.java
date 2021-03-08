package vini2003.xyz.nofeedback;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import vini2003.xyz.nofeedback.registry.common.NoFeedbackCommands;
import vini2003.xyz.nofeedback.registry.common.NoFeedbackNetworking;

public class NoFeedback implements ModInitializer {
	public static final String ID = "nofeedback";
	
	public static Identifier identifier(String path) {
		return new Identifier(ID, path);
	}
	
	@Override
	public void onInitialize() {
		NoFeedbackCommands.initialize();
		NoFeedbackNetworking.initialize();
	}
}
