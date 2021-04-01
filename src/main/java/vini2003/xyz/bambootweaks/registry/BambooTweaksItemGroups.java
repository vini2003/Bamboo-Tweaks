package vini2003.xyz.bambootweaks.registry;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import vini2003.xyz.bambootweaks.BambooTweaks;

public class BambooTweaksItemGroups {
	public static final ItemGroup BAMBOO_TWEAKS = FabricItemGroupBuilder.create(BambooTweaks.identifier("bambootweaks")).icon(() -> new ItemStack(BambooTweaksBlocks.BAMBOO_BLOCK)).build();
	
	public static void initialize() {
	
	}
}
