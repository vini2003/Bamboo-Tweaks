package com.github.vini2003.bambootweaks.registry;

import com.github.vini2003.bambootweaks.BambooTweaks;
import com.github.vini2003.bambootweaks.block.*;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.impl.content.registry.FuelRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BambooTweaksBlocks {
	public static final Block BAMBOO_BLOCK = register("bamboo_block", new Block(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(0.5F, 2.5F).build()), new Item.Settings().group(ItemGroup.MISC));
	public static final Block BAMBOO_DOOR = register("bamboo_door", new ExposedDoorBlock(FabricBlockSettings.of(Material.BAMBOO).strength(3.0F, 0.0F).sounds(BlockSoundGroup.BAMBOO).nonOpaque().build()), new Item.Settings().group(ItemGroup.MISC));
	public static final Block BAMBOO_FENCE_BLOCK = register("bamboo_fence", new FenceBlock(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(2.0F, 3.0F).build()), new Item.Settings().group(ItemGroup.MISC));
	public static final Block BAMBOO_FENCE_GATE_BLOCK = register("bamboo_fence_gate", new FenceGateBlock(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(2.0F, 3.0F).build()), new Item.Settings().group(ItemGroup.MISC));
	public static final Block BAMBOO_FENCE_WALL_BLOCK = register("bamboo_wall", new BlockBambooWall(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(2.0F, 3.0F).build()), new Item.Settings().group(ItemGroup.MISC));
	public static final Block BAMBOO_LADDER_BLOCK = register("bamboo_ladder", new ExposedLadderBlock(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(0.4F, 0.0F).nonOpaque().build()), new Item.Settings().group(ItemGroup.MISC));
	public static final Block BAMBOO_ROD = register("bamboo_rod", new BlockBambooRod(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).breakInstantly().build()), new Item.Settings().group(ItemGroup.MISC));
	public static final Block BAMBOO_SPIKE_BLOCK = register("bamboo_spike", new BlockBambooSpike(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(2.0F, 3.0F).build()), new Item.Settings().group(ItemGroup.MISC));
	public static final Block BAMBOO_STAIRS_BLOCK = register("bamboo_stairs", new ExposedStairsBlock(BAMBOO_BLOCK.getDefaultState(), FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(2.0F, 3.0F).build()), new Item.Settings().group(ItemGroup.MISC));
	public static final Block BAMBOO_TORCH_BLOCK = register("bamboo_torch", new ExposedTorchBlock(FabricBlockSettings.of(Material.ORGANIC_PRODUCT).lightLevel(14).noCollision().sounds(BlockSoundGroup.BAMBOO).nonOpaque().build(), ParticleTypes.FLAME), (BlockItem) null);
	public static final Block BAMBOO_TORCH_BLOCK_WALL = register("bamboo_torch_wall", new ExposedWallTorchBlock(FabricBlockSettings.of(Material.ORGANIC_PRODUCT).lightLevel(14).noCollision().sounds(BlockSoundGroup.BAMBOO).nonOpaque().build(), ParticleTypes.FLAME), (BlockItem) null);

	public BambooTweaksBlocks() {
		// NO-OP
	}

	public static void initialize() {
		FuelRegistryImpl.INSTANCE.add(BAMBOO_BLOCK, 500);
		FuelRegistryImpl.INSTANCE.add(BAMBOO_DOOR, 200);
		FuelRegistryImpl.INSTANCE.add(BAMBOO_FENCE_BLOCK, 300);
		FuelRegistryImpl.INSTANCE.add(BAMBOO_FENCE_GATE_BLOCK, 300);
		FuelRegistryImpl.INSTANCE.add(BAMBOO_FENCE_WALL_BLOCK, 300);
		FuelRegistryImpl.INSTANCE.add(BAMBOO_LADDER_BLOCK, 300);
		FuelRegistryImpl.INSTANCE.add(BAMBOO_ROD, 200);
		FuelRegistryImpl.INSTANCE.add(BAMBOO_STAIRS_BLOCK, 300);
		FuelRegistryImpl.INSTANCE.add(BAMBOO_SPIKE_BLOCK, 300);
	}

	static <T extends Block> T register(String name, T block, Item.Settings settings) {
		return register(name, block, new BlockItem(block, settings));
	}

	static <T extends Block> T register(String name, T block, BlockItem item) {
		T b = Registry.register(Registry.BLOCK, new Identifier(BambooTweaks.MOD_ID, name), block);
		if (item != null) {
			BambooTweaksItems.register(name, item);
		}
		return b;
	}
}
