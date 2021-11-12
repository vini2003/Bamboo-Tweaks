package dev.vini2003.bambootweaks.common.registry

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
	
	fun <T : Block> register(name: String, supplier: () -> T): RegistrySupplier<T> {
		return Blocks.register(name, supplier)
	}
	
	fun <T : Block> register(name: String, supplier: () -> T, settings: (Item.Settings) -> (Item.Settings)): RegistrySupplier<T> {
		return Blocks.register(name, supplier).let { block ->
			Items.register(name) {
				BlockItem(block.get(), settings.invoke(itemSettings()))
			}
			
			return@let block
		}
	}
	
	private fun blockSettings(material: Material) = AbstractBlock.Settings.of(material)!!
	
	private fun itemSettings() = Item.Settings().group(BTItemGroups.BambooTweaks)!!
	
	val Bamboo = register("bamboo_block") {
		Block(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}
	
	val BambooDoor = register("bamboo_door") {
		ExposedDoorBlock(blockSettings(Material.BAMBOO).strength(3.0F).nonOpaque())
	}
	
	val BambooFence = register("bamboo_fence") {
		FenceBlock(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}
	
	val BambooFenceGate = register("bamboo_fence_gate") {
		FenceGateBlock(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}
	
	val BambooWall = register("bamboo_wall") {
		BambooWallBlock(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}
	
	val BambooLadder = register("bamboo_ladder") {
		ExposedLadderBlock(blockSettings(Material.BAMBOO).sounds(BlockSoundGroup.LADDER).strength(0.4F).nonOpaque())
	}
	
	val BambooRod = register("bamboo_rod") {
		net.minecraft.block.Blocks.END_ROD
		BambooRodBlock(blockSettings(Material.DECORATION).breakInstantly().nonOpaque())
	}
	
	val BambooSpikes = register("bamboo_spikes") {
		BambooSpikesBlock(blockSettings(Material.DECORATION).breakInstantly().nonOpaque())
	}
	
	val BambooStairs = register("bamboo_stairs") {
		ExposedStairsBlock(Bamboo.orNull!!.defaultState, blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}
	
	val BambooTorch = register("bamboo_torch") {
		ExposedTorchBlock(blockSettings(Material.DECORATION).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
	}
	
	val BambooWallTorch = register("bamboo_wall_torch") {
		ExposedWallTorchBlock(blockSettings(Material.DECORATION).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
	}
	
	val DryBamboo = register("dry_bamboo_block") {
		Block(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}
	
	val DryBambooDoor = register("dry_bamboo_door") {
		ExposedDoorBlock(blockSettings(Material.BAMBOO).strength(3.0F).nonOpaque())
	}
	
	val DryBambooFence = register("dry_bamboo_fence") {
		FenceBlock(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}
	
	val DryBambooFenceGate = register("dry_bamboo_fence_gate") {
		FenceGateBlock(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}
	
	val DryBambooWall = register("dry_bamboo_wall") {
		BambooWallBlock(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}
	
	val DryBambooLadder = register("dry_bamboo_ladder") {
		ExposedLadderBlock(blockSettings(Material.BAMBOO).sounds(BlockSoundGroup.LADDER).strength(0.4F).nonOpaque())
	}
	
	val DryBambooRod = register("dry_bamboo_rod") {
		BambooRodBlock(blockSettings(Material.DECORATION).breakInstantly().nonOpaque())
	}
	
	val DryBambooSpikes = register("dry_bamboo_spikes") {
		BambooSpikesBlock(blockSettings(Material.DECORATION).breakInstantly().nonOpaque())
	}
	
	val DryBambooStairs = register("dry_bamboo_stairs") {
		ExposedStairsBlock(DryBamboo.orNull!!.defaultState, blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}
	
	val DryBambooTorch = register("dry_bamboo_torch") {
		ExposedTorchBlock(blockSettings(Material.DECORATION).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
	}
	
	val DryBambooWallTorch = register("dry_bamboo_wall_torch") {
		ExposedWallTorchBlock(blockSettings(Material.DECORATION).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
	}
}