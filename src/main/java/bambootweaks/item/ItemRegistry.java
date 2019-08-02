package bambootweaks.item;

import bambootweaks.block.BlockRegistry;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class ItemRegistry {
    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier("bambootweaks", "bamboo_spike"), new BlockItem(BlockRegistry.BAMBOO_SPIKE_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("bambootweaks", "bamboo_rod"), new BlockItem(BlockRegistry.BAMBOO_ROD, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("bambootweaks", "bamboo_block"), new BlockItem(BlockRegistry.BAMBOO_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("bambootweaks", "bamboo_torch"), new WallStandingBlockItem(BlockRegistry.BAMBOO_TORCH_BLOCK, BlockRegistry.BAMBOO_TORCH_BLOCK_WALL, new Item.Settings().group(ItemGroup.MISC)));
    }
}