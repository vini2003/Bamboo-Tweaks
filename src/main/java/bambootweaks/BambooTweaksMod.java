package bambootweaks;

import net.fabricmc.api.ModInitializer;

import bambootweaks.block.BlockRegistry;
import bambootweaks.item.ItemRegistry;

public class BambooTweaksMod implements ModInitializer {
    @Override
    public void onInitialize() {
        BlockRegistry.registerBlocks();
        ItemRegistry.registerItems();
    }
}