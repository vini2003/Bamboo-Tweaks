package com.github.vini2003.bambootweaks;

import com.github.vini2003.bambootweaks.registry.BambooTweaksBlocks;
import com.github.vini2003.bambootweaks.registry.BambooTweaksItems;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BambooTweaks implements ModInitializer {
	public static final String LOG_ID = "BambooTweaks";
	public static final String MOD_ID = LOG_ID.toLowerCase();
	public static Logger logger = LogManager.getLogger("BambooTweaks");

	@Override
	public void onInitialize() {
		BambooTweaksItems.initialize();
		BambooTweaksBlocks.initialize();
	}
}