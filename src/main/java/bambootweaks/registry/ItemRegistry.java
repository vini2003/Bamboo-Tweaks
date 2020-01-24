package bambootweaks.registry;

import bambootweaks.BambooTweaks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
	public static final Item TORCH_ITEM = Registry.register(Registry.ITEM, new Identifier(BambooTweaks.MOD_ID, "bamboo_torch"), new WallStandingBlockItem(BlockRegistry.BAMBOO_TORCH_BLOCK, BlockRegistry.BAMBOO_TORCH_BLOCK_WALL, new Item.Settings().group(ItemGroup.MISC)));

	public ItemRegistry() {
		// NO-OP
	}

	public static void initialize() {

	}

	public static <T extends Item> T register(String name, T item) {
		return Registry.register(Registry.ITEM, new Identifier(BambooTweaks.MOD_ID, name), item);
	}
}