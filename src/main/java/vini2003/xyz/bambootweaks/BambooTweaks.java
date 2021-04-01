package vini2003.xyz.bambootweaks;

import net.minecraft.util.Identifier;
import vini2003.xyz.bambootweaks.registry.BambooTweaksBlocks;
import vini2003.xyz.bambootweaks.registry.BambooTweaksItemGroups;
import vini2003.xyz.bambootweaks.registry.BambooTweaksItems;
import net.fabricmc.api.ModInitializer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
	}
}