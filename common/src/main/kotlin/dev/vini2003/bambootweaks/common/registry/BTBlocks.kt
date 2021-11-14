package dev.vini2003.bambootweaks.common.registry

import dev.architectury.registry.block.BlockProperties
import dev.architectury.registry.block.ToolType
import dev.architectury.registry.registries.RegistrySupplier
import net.minecraft.block.*
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.BlockSoundGroup
import dev.vini2003.bambootweaks.common.block.*

object BTBlocks {
	private val Blocks = BTRegistries.Blocks
	private val Items = BTRegistries.Items
	
	fun init() {

	}
	
	fun <T : Block> registerBlock(name: String, supplier: () -> T): RegistrySupplier<T> {
		return Blocks.register(name, supplier)
	}
	
	fun <T : Block> registerBlockWithItem(name: String, supplier: () -> T, settings: (Item.Settings) -> (Item.Settings)): RegistrySupplier<T> {
		return registerBlock(name, supplier).let { block ->
			Items.register(name) {
				BlockItem(block.get(), settings.invoke(itemSettings()))
			}
			
			return@let block
		}
	}
	
	fun <T : Block> registerBlockWithItem(name: String, supplier: () -> T): RegistrySupplier<T> {
		return registerBlockWithItem(name, supplier) { it }
	}
	
	private fun blockSettings(material: Material) = BlockProperties.of(material)!!
	
	private fun itemSettings() = Item.Settings().group(BTItemGroups.BambooTweaks)!!
	
	val Bamboo = registerBlockWithItem("bamboo_block") {
		Block(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}
	
	val BambooDoor = registerBlockWithItem("bamboo_door") {
		ExposedDoorBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(3.0F).nonOpaque())
	}
	
	val BambooFence = registerBlockWithItem("bamboo_fence") {
		FenceBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(2.0F, 3.0F))
	}
	
	val BambooFenceGate = registerBlockWithItem("bamboo_fence_gate") {
		FenceGateBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(2.0F, 3.0F))
	}
	
	val BambooWall = registerBlockWithItem("bamboo_wall") {
		BambooWallBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).nonOpaque().strength(2.0F, 3.0F))
	}
	
	val BambooLadder = registerBlockWithItem("bamboo_ladder") {
		ExposedLadderBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).sounds(BlockSoundGroup.LADDER).strength(0.4F).nonOpaque())
	}
	
	val BambooRod = registerBlockWithItem("bamboo_rod") {
		net.minecraft.block.Blocks.END_ROD
		BambooRodBlock(blockSettings(Material.DECORATION).breakInstantly().nonOpaque())
	}
	
	val BambooSpikes = registerBlockWithItem("bamboo_spikes") {
		BambooSpikesBlock(blockSettings(Material.DECORATION).breakInstantly().nonOpaque())
	}
	
	val BambooStairs = registerBlockWithItem("bamboo_stairs") {
		ExposedStairsBlock(Bamboo.orNull!!.defaultState, blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(2.0F, 3.0F))
	}
	
	val BambooSlab = registerBlockWithItem("bamboo_slab") {
		SlabBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(2.0F, 3.0F))
	}
	
	val BambooTrapdoor = registerBlockWithItem("bamboo_trapdoor") {
		ExposedTrapdoorBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(2.0F, 3.0F).nonOpaque().allowsSpawning { _, _, _, _ -> false})
	}
	
	val BambooTorch = registerBlock("bamboo_torch") {
		ExposedTorchBlock(blockSettings(Material.DECORATION).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
	}
	
	val BambooWallTorch = registerBlock("bamboo_wall_torch") {
		ExposedWallTorchBlock(blockSettings(Material.DECORATION).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
	}
	
	val DryBamboo = registerBlockWithItem("dry_bamboo_block") {
		Block(blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(2.0F, 3.0F))
	}
	
	val DryBambooDoor = registerBlockWithItem("dry_bamboo_door") {
		ExposedDoorBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(3.0F).nonOpaque())
	}
	
	val DryBambooFence = registerBlockWithItem("dry_bamboo_fence") {
		FenceBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(2.0F, 3.0F))
	}
	
	val DryBambooFenceGate = registerBlockWithItem("dry_bamboo_fence_gate") {
		FenceGateBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(2.0F, 3.0F))
	}
	
	val DryBambooWall = registerBlockWithItem("dry_bamboo_wall") {
		BambooWallBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).nonOpaque().strength(2.0F, 3.0F))
	}
	
	val DryBambooLadder = registerBlockWithItem("dry_bamboo_ladder") {
		ExposedLadderBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).sounds(BlockSoundGroup.LADDER).strength(0.4F).nonOpaque())
	}
	
	val DryBambooRod = registerBlockWithItem("dry_bamboo_rod") {
		BambooRodBlock(blockSettings(Material.DECORATION).breakInstantly().nonOpaque())
	}
	
	val DryBambooSpikes = registerBlockWithItem("dry_bamboo_spikes") {
		BambooSpikesBlock(blockSettings(Material.DECORATION).breakInstantly().nonOpaque())
	}
	
	val DryBambooStairs = registerBlockWithItem("dry_bamboo_stairs") {
		ExposedStairsBlock(DryBamboo.orNull!!.defaultState, blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}
	
	val DryBambooSlab = registerBlockWithItem("dry_bamboo_slab") {
		SlabBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(2.0F, 3.0F))
	}
	
	val DryBambooTrapdoor = registerBlockWithItem("dry_bamboo_trapdoor") {
		ExposedTrapdoorBlock(blockSettings(Material.BAMBOO).tool(ToolType.AXE).strength(2.0F, 3.0F).nonOpaque().allowsSpawning { _, _, _, _ -> false})
	}
	
	val DryBambooTorch = registerBlock("dry_bamboo_torch") {
		ExposedTorchBlock(blockSettings(Material.DECORATION).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
	}
	
	val DryBambooWallTorch = registerBlock("dry_bamboo_wall_torch") {
		ExposedWallTorchBlock(blockSettings(Material.DECORATION).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
	}
}