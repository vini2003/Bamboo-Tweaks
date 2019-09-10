package bambootweaks.item;

import bambootweaks.block.BlockRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier("bambootweaks", "bamboo_block"), new BlockItem(BlockRegistry.BAMBOO_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("bambootweaks", "bamboo_door"), new BlockItem(BlockRegistry.BAMBOO_DOOR, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("bambootweaks", "bamboo_fence_gate"), new BlockItem(BlockRegistry.BAMBOO_FENCE_GATE_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("bambootweaks", "bamboo_fence_wall"), new BlockItem(BlockRegistry.BAMBOO_FENCE_WALL_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("bambootweaks", "bamboo_fence"), new BlockItem(BlockRegistry.BAMBOO_FENCE_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("bambootweaks", "bamboo_ladder"), new BlockItem(BlockRegistry.BAMBOO_LADDER_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("bambootweaks", "bamboo_rod"), new BlockItem(BlockRegistry.BAMBOO_ROD, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("bambootweaks", "bamboo_spike"), new BlockItem(BlockRegistry.BAMBOO_SPIKE_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("bambootweaks", "bamboo_stairs"), new BlockItem(BlockRegistry.BAMBOO_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("bambootweaks", "bamboo_torch"), new WallStandingBlockItem(BlockRegistry.BAMBOO_TORCH_BLOCK, BlockRegistry.BAMBOO_TORCH_BLOCK_WALL, new Item.Settings().group(ItemGroup.MISC)));
    }
}