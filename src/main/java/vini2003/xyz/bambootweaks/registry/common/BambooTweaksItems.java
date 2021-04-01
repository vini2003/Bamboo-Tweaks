package vini2003.xyz.bambootweaks.registry.common;

import vini2003.xyz.bambootweaks.BambooTweaks;
import net.minecraft.item.Item;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.util.registry.Registry;

public class BambooTweaksItems {
	public static final Item BAMBOO_TORCH = register("bamboo_torch", new WallStandingBlockItem(BambooTweaksBlocks.BAMBOO_TORCH_BLOCK, BambooTweaksBlocks.BAMBOO_TORCH_BLOCK_WALL, new Item.Settings().group(BambooTweaksItemGroups.BAMBOO_TWEAKS)));
	public static final Item DRIED_BAMBOO_TORCH = register("dried_bamboo_torch", new WallStandingBlockItem(BambooTweaksBlocks.DRIED_BAMBOO_TORCH_BLOCK, BambooTweaksBlocks.DRIED_BAMBOO_TORCH_BLOCK_WALL, new Item.Settings().group(BambooTweaksItemGroups.BAMBOO_TWEAKS)));
	
	public static final Item DRIED_BAMBOO = register("dried_bamboo", new Item(new Item.Settings().group(BambooTweaksItemGroups.BAMBOO_TWEAKS)));
	
	public BambooTweaksItems() {
		// NO-OP
	}

	public static void initialize() {

	}

	public static <T extends Item> T register(String name, T item) {
		return Registry.register(Registry.ITEM, BambooTweaks.identifier(name), item);
	}
}