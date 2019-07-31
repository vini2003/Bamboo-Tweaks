package bambootweaks.item;

import bambootweaks.block.BlockRegistry;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

public class ItemRegistry {
    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier("bambootweaks", "bamboo_torch"), new WallStandingBlockItem(BlockRegistry.BambooTorchBlockGroundBlock, BlockRegistry.BambooTorchBlockWallBlock, new Item.Settings().group(ItemGroup.DECORATIONS)));
    }
}