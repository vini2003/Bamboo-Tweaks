package vini2003.xyz.bambootweaks;

import net.minecraft.util.Identifier;
import vini2003.xyz.bambootweaks.registry.common.BambooTweaksBlocks;
import vini2003.xyz.bambootweaks.registry.common.BambooTweaksFuels;
import vini2003.xyz.bambootweaks.registry.common.BambooTweaksItemGroups;
import vini2003.xyz.bambootweaks.registry.common.BambooTweaksItems;
import net.fabricmc.api.ModInitializer;

public class BambooTweaks implements ModInitializer {
	public static final String ID = "bambootweaks";
	
	public static Identifier identifier(String path) {
		return new Identifier(ID, path);
	}

	@Override
	public void onInitialize() {
		BambooTweaksItems.initialize();
		BambooTweaksBlocks.initialize();
		BambooTweaksItemGroups.initialize();
		BambooTweaksFuels.initialize();
	}
}