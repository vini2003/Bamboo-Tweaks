package vini2003.xyz.eco;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Eco implements ModInitializer {
	public static final String ID = "heal";
	
	public static Identifier identifier(String path) {
		return new Identifier(ID, path);
	}
	
	@Override
	public void onInitialize() {
	
	}
}
