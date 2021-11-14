package dev.vini2003.bambootweaks.common.registry

import dev.architectury.registry.block.BlockProperties
import dev.architectury.registry.block.BlockProperties.copy
import dev.architectury.registry.block.ToolType
import dev.architectury.registry.registries.RegistrySupplier
import dev.vini2003.bambootweaks.common.block.*
import net.minecraft.block.*
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.BlockSoundGroup
import dev.vini2003.bambootweaks.common.registry.BTRegistries.Items
import dev.vini2003.bambootweaks.common.registry.BTRegistries.Blocks

object BTBlocks {
	fun init() {

	}
	
	private fun <T : Block> registerBlock(name: String, supplier: () -> T): RegistrySupplier<T> {
		return Blocks.register(name, supplier)
	}
	
	private fun <T : Block> registerBlockWithItem(name: String, supplier: () -> T, settings: (Item.Settings) -> (Item.Settings)): RegistrySupplier<T> {
		return registerBlock(name, supplier).let { block ->
			Items.register(name) {
				BlockItem(block.get(), settings.invoke(itemSettings()))
			}
			
			return@let block
		}
	}
	
	private fun <T : Block> registerBlockWithItem(name: String, supplier: () -> T): RegistrySupplier<T> {
		return registerBlockWithItem(name, supplier) { it }
	}
	
	private fun blockSettings(material: Material) = BlockProperties.of(material)!!
	
	private fun itemSettings() = Item.Settings().group(BTItemGroups.BambooTweaks)!!
	
	val BambooBlock = registerBlockWithItem("bamboo_block") {
		Block(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}
	
	val DryBambooBlock = registerBlockWithItem("dry_bamboo_block") {
		Block(copy(BambooBlock.orNull))
	}
	
	val BambooDoor = registerBlockWithItem("bamboo_door") {
		ExposedDoorBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(3.0F).nonOpaque())
	}
	
	val DryBambooDoor = registerBlockWithItem("dry_bamboo_door") {
		ExposedDoorBlock(copy(BambooDoor.orNull))
	}
	
	val BambooFence = registerBlockWithItem("bamboo_fence") {
		FenceBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(2.0F, 3.0F))
	}
	
	val DryBambooFence = registerBlockWithItem("dry_bamboo_fence") {
		FenceBlock(copy(BambooFence.orNull))
	}
	
	val BambooFenceGate = registerBlockWithItem("bamboo_fence_gate") {
		FenceGateBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).nonOpaque().strength(2.0F, 3.0F))
	}
	
	val DryBambooFenceGate = registerBlockWithItem("dry_bamboo_fence_gate") {
		FenceGateBlock(copy(BambooFenceGate.orNull))
	}
	
	val BambooWall = registerBlockWithItem("bamboo_wall") {
		BambooWallBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).nonOpaque().strength(2.0F, 3.0F))
	}
	
	val DryBambooWall = registerBlockWithItem("dry_bamboo_wall") {
		BambooWallBlock(copy(BambooWall.orNull))
	}
	
	val BambooLadder = registerBlockWithItem("bamboo_ladder") {
		ExposedLadderBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).sounds(BlockSoundGroup.LADDER).strength(0.4F).nonOpaque())
	}
	
	val DryBambooLadder = registerBlockWithItem("dry_bamboo_ladder") {
		ExposedLadderBlock(copy(BambooLadder.orNull))
	}
	
	val BambooRod = registerBlockWithItem("bamboo_rod") {
		BambooRodBlock(blockSettings(Material.DECORATION).breakInstantly().nonOpaque())
	}
	
	val DryBambooRod = registerBlockWithItem("dry_bamboo_rod") {
		BambooRodBlock(copy(BambooRod.orNull))
	}
	
	val BambooSpikes = registerBlockWithItem("bamboo_spikes") {
		BambooSpikesBlock(blockSettings(Material.DECORATION).breakInstantly().nonOpaque())
	}
	
	val DryBambooSpikes = registerBlockWithItem("dry_bamboo_spikes") {
		BambooSpikesBlock(copy(BambooSpikes.orNull))
	}
	
	val BambooStairs = registerBlockWithItem("bamboo_stairs") {
		ExposedStairsBlock(BambooBlock.orNull!!.defaultState, blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(2.0F, 3.0F))
	}
	
	val DryBambooStairs = registerBlockWithItem("dry_bamboo_stairs") {
		ExposedStairsBlock(DryBambooBlock.orNull!!.defaultState, copy(BambooStairs.orNull))
	}
	
	val BambooSlab = registerBlockWithItem("bamboo_slab") {
		SlabBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(2.0F, 3.0F))
	}
	
	val DryBambooSlab = registerBlockWithItem("dry_bamboo_slab") {
		SlabBlock(copy(BambooSlab.orNull))
	}
	
	val BambooTrapdoor = registerBlockWithItem("bamboo_trapdoor") {
		ExposedTrapdoorBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(2.0F, 3.0F).nonOpaque().allowsSpawning { _, _, _, _ -> false})
	}
	
	val DryBambooTrapdoor = registerBlockWithItem("dry_bamboo_trapdoor") {
		ExposedTrapdoorBlock(copy(BambooTrapdoor.orNull))
	}
	
	val BambooTorch = registerBlock("bamboo_torch") {
		ExposedTorchBlock(blockSettings(Material.DECORATION).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
	}
	
	val DryBambooTorch = registerBlock("dry_bamboo_torch") {
		ExposedTorchBlock(copy(BambooTorch.orNull), ParticleTypes.FLAME)
	}
	
	val BambooWallTorch = registerBlock("bamboo_wall_torch") {
		ExposedWallTorchBlock(blockSettings(Material.DECORATION).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
	}
	
	val DryBambooWallTorch = registerBlock("dry_bamboo_wall_torch") {
		ExposedWallTorchBlock(copy(BambooWallTorch.orNull), ParticleTypes.FLAME)
	}
}