package vini2003.xyz.heal;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import vini2003.xyz.heal.registry.common.HealCommands;

public class Heal implements ModInitializer {
	public static final String ID = "heal";
	
	public static Identifier identifier(String path) {
		return new Identifier(ID, path);
	}
	
	@Override
	public void onInitialize() {
		HealCommands.initialize();
	}
}
