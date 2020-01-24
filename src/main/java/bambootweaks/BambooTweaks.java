package bambootweaks;

import bambootweaks.registry.BlockRegistry;
import bambootweaks.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BambooTweaks implements ModInitializer {
	public static final String LOG_ID = "BambooTweaks";
	public static final String MOD_ID = LOG_ID.toLowerCase();
	public static Logger logger = LogManager.getLogger("BambooTweaks");

	@Override
	public void onInitialize() {
		ItemRegistry.initialize();
		BlockRegistry.initialize();
	}
}