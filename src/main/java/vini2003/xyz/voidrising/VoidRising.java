package vini2003.xyz.voidrising;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import vini2003.xyz.voidrising.registry.common.VoidRisingCommands;
import vini2003.xyz.voidrising.registry.common.VoidRisingNetworking;

public class VoidRising implements ModInitializer {
	public static final String ID = "voidrising";
	
	public static Identifier identifier(String path) {
		return new Identifier(ID, path);
	}
	
	@Override
	public void onInitialize() {
		VoidRisingCommands.initialize();
		VoidRisingNetworking.initialize();
	}
}
