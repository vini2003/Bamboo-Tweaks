package com.github.vini2003.bambootweaks.registry;

import com.github.vini2003.bambootweaks.BambooTweaks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BambooTweaksItems {
	public static final Item TORCH_ITEM = Registry.register(Registry.ITEM, new Identifier(BambooTweaks.MOD_ID, "bamboo_torch"), new WallStandingBlockItem(BambooTweaksBlocks.BAMBOO_TORCH_BLOCK, BambooTweaksBlocks.BAMBOO_TORCH_BLOCK_WALL, new Item.Settings().group(ItemGroup.MISC)));

	public BambooTweaksItems() {
		// NO-OP
	}

	public static void initialize() {

	}

	public static <T extends Item> T register(String name, T item) {
		return Registry.register(Registry.ITEM, new Identifier(BambooTweaks.MOD_ID, name), item);
	}
}