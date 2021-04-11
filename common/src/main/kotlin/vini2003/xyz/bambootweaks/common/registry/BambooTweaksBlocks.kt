package vini2003.xyz.bambootweaks.common.registry

import me.shedaniel.architectury.registry.DeferredRegister
import me.shedaniel.architectury.registry.RegistrySupplier
import net.minecraft.block.*
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.registry.Registry
import vini2003.xyz.bambootweaks.CommonBambooTweaks
import vini2003.xyz.bambootweaks.common.block.*

object BambooTweaksBlocks {
	private val Blocks = BambooTweaksDeferredRegisters.Blocks
	private val Items = BambooTweaksDeferredRegisters.Items
	
	fun init() {

	}
	
	fun <T : Block> register(name: String, supplier: () -> T): RegistrySupplier<T> {
		return Blocks.register(name, supplier)
	}
	
	fun <T : Block> register(name: String, supplier: () -> T, settings: (Item.Settings) -> (Item.Settings)): RegistrySupplier<T> {
		Blocks.register(name, supplier).let { block ->
			Items.register(name) {
				BlockItem(block.get(), settings.invoke(itemSettings()))
			}
			
			return block
		}
	}
	
	private fun blockSettings(material: Material) = AbstractBlock.Settings.of(material)!!
	
	private fun itemSettings() = Item.Settings().group(BambooTweaksItemGroups.BambooTweaks)!!
	
	val Bamboo = register("bamboo_block", {
		Block(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}, { it })
	
	val BambooDoor = register("bamboo_door", {
		ExposedDoorBlock(blockSettings(Material.BAMBOO).strength(3.0F).nonOpaque())
	}, { it })
	
	val BambooFence = register("bamboo_fence", {
		FenceBlock(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}, { it })
	
	val BambooFenceGate = register("bamboo_fence_gate", {
		FenceGateBlock(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}, { it })
	
	val BambooWall = register("bamboo_wall", {
		BambooWallBlock(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}, { it })
	
	val BambooLadder = register("bamboo_ladder", {
		ExposedLadderBlock(blockSettings(Material.BAMBOO).sounds(BlockSoundGroup.LADDER).strength(0.4F).nonOpaque())
	}, { it })
	
	val BambooRod = register("bamboo_rod", {
		BambooRodBlock(blockSettings(Material.SUPPORTED).breakInstantly().nonOpaque())
	}, { it })
	
	val BambooSpikes = register("bamboo_spikes", {
		BambooSpikesBlock(blockSettings(Material.SUPPORTED).breakInstantly().nonOpaque())
	}, { it })
	
	val BambooStairs = register("bamboo_stairs", {
		ExposedStairsBlock(Bamboo.orNull!!.defaultState, blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}, { it })
	
	val BambooTorch = register("bamboo_torch") {
		ExposedTorchBlock(blockSettings(Material.SUPPORTED).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
	}
	
	val BambooWallTorch = register("bamboo_wall_torch") {
		ExposedWallTorchBlock(blockSettings(Material.SUPPORTED).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
	}
	
	val DryBamboo = register("dry_bamboo_block", {
		Block(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}, { it })
	
	val DryBambooDoor = register("dry_bamboo_door", {
		ExposedDoorBlock(blockSettings(Material.BAMBOO).strength(3.0F).nonOpaque())
	}, { it })
	
	val DryBambooFence = register("dry_bamboo_fence", {
		FenceBlock(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}, { it })
	
	val DryBambooFenceGate = register("dry_bamboo_fence_gate", {
		FenceGateBlock(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}, { it })
	
	val DryBambooWall = register("dry_bamboo_wall", {
		BambooWallBlock(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}, { it })
	
	val DryBambooLadder = register("dry_bamboo_ladder", {
		ExposedLadderBlock(blockSettings(Material.BAMBOO).sounds(BlockSoundGroup.LADDER).strength(0.4F).nonOpaque())
	}, { it })
	
	val DryBambooRod = register("dry_bamboo_rod", {
		BambooRodBlock(blockSettings(Material.SUPPORTED).breakInstantly().nonOpaque())
	}, { it })
	
	val DryBambooSpikes = register("dry_bamboo_spikes", {
		BambooSpikesBlock(blockSettings(Material.SUPPORTED).breakInstantly().nonOpaque())
	}, { it })
	
	val DryBambooStairs = register("dry_bamboo_stairs", {
		ExposedStairsBlock(DryBamboo.orNull!!.defaultState, blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
	}, { it })
	
	val DryBambooTorch = register("dry_bamboo_torch") {
		ExposedTorchBlock(blockSettings(Material.SUPPORTED).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
	}
	
	val DryBambooWallTorch = register("dry_bamboo_wall_torch") {
		ExposedWallTorchBlock(blockSettings(Material.SUPPORTED).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
	}
}