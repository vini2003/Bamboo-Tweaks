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
	val BlockRegistry = DeferredRegister.create(CommonBambooTweaks.Id, Registry.BLOCK_KEY)
	
	fun init() {
		BlockRegistry.register()
	}
	
	fun <T : Block> register(name: String, blockSupplier: () -> T, settings: Item.Settings): RegistrySupplier<T> {
		return register(name, blockSupplier, true) {
			BlockItem(blockSupplier.invoke(), settings)
		}
	}
	
	fun <T : Block> register(name: String, blockSupplier: () -> T): RegistrySupplier<T> {
		return register(name, blockSupplier, false) { null }
	}
	
	fun <T : Block> register(name: String, blockSupplier: () -> T, hasItem: Boolean, itemSupplier: () -> BlockItem?): RegistrySupplier<T> {
		val b = BlockRegistry.register(CommonBambooTweaks.identifier(name), blockSupplier)
		
		if (hasItem) {
			BambooTweaksItems.register(name) { itemSupplier.invoke() as Item }
		}
		
		return b
	}
	
	private fun blockSettings(material: Material) = AbstractBlock.Settings.of(material)!!
	
	private fun itemSettings() = Item.Settings().group(BambooTweaksItemGroups.BambooTweaks)!!
	
	val Bamboo = register(
		"bamboo_block", {
			Block(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
		}, itemSettings()
	)
	
	val BambooDoor = register(
		"bamboo_door", {
			ExposedDoorBlock(blockSettings(Material.BAMBOO).strength(3.0F).nonOpaque())
		}, itemSettings()
	)
	
	val BambooFence = register(
		"bamboo_fence", {
			FenceBlock(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
		}, itemSettings()
	)
	
	val BambooFenceGate = register(
		"bamboo_fence_gate", {
			FenceGateBlock(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
		}, itemSettings()
	)
	
	val BambooWall = register<Block>(
		"bamboo_wall", {
			BambooWallBlock(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
		}, itemSettings()
	)
	
	val BambooLadder = register<Block>(
		"bamboo_ladder", { 
			ExposedLadderBlock(blockSettings(Material.BAMBOO).sounds(BlockSoundGroup.LADDER).strength(0.4F).nonOpaque())
		}, itemSettings()
	)
	
	val BambooRod = register<Block>(
		"bamboo_rod", { 
			BambooRodBlock(blockSettings(Material.SUPPORTED).breakInstantly().nonOpaque())
		}, itemSettings()
	)
	
	val BambooSpikes = register<Block>(
		"bamboo_spikes", { 
			BambooSpikesBlock(blockSettings(Material.SUPPORTED).breakInstantly().nonOpaque())
		}, itemSettings()
	)
	
	val BambooStairs = register<Block>(
		"bamboo_stairs", { 
			ExposedStairsBlock(Bamboo.orNull!!.defaultState, blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
		}, itemSettings()
	)
	
	val BambooTorch = register<Block>(
		"bamboo_torch") {
			ExposedTorchBlock(blockSettings(Material.SUPPORTED).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
		}
	
	val BambooWallTorch = register<Block>(
		"bamboo_wall_torch") {
			ExposedWallTorchBlock(blockSettings(Material.SUPPORTED).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
		}
	
	val DriedBamboo = register(
		"dried_bamboo_block", { 
			Block(blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
		}, itemSettings()
	)
	
	val DriedBambooDoor = register(
		"dried_bamboo_door", { 
			ExposedDoorBlock(
			blockSettings(Material.BAMBOO).strength(3.0F).nonOpaque())
		}, itemSettings()
	)
	
	val DriedBambooFence = register(
		"dried_bamboo_fence", { 
			FenceBlock(
			blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
		}, itemSettings()
	)
	
	val DriedBambooFenceGate = register(
		"dried_bamboo_fence_gate", { 
			FenceGateBlock(
			blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
		}, itemSettings()
	)
	
	val DriedBambooWall = register<Block>(
		"dried_bamboo_wall", { 
			BambooWallBlock(
			blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
		}, itemSettings()
	)
	
	val DriedBambooLadder = register<Block>(
		"dried_bamboo_ladder", { 
			ExposedLadderBlock(
			blockSettings(Material.BAMBOO).sounds(BlockSoundGroup.LADDER).strength(0.4F).nonOpaque())
		}, itemSettings()
	)
	
	val DriedBambooRod = register<Block>(
		"dried_bamboo_rod", { 
			BambooRodBlock(
			blockSettings(Material.SUPPORTED).breakInstantly().nonOpaque())
		}, itemSettings()
	)
	
	val DriedBambooSpikes = register<Block>(
		"dried_bamboo_spikes", { 
			BambooSpikesBlock(
			blockSettings(Material.SUPPORTED).breakInstantly().nonOpaque())
		}, itemSettings()
	)
	
	val DriedBambooStairs = register<Block>(
		"dried_bamboo_stairs", { 
			ExposedStairsBlock(
			DriedBamboo.orNull!!.defaultState, blockSettings(Material.BAMBOO).strength(2.0F, 3.0F))
		}, itemSettings()
	)
	
	val DriedBambooTorch = register<Block>(
		"dried_bamboo_torch") {
			ExposedTorchBlock(blockSettings(Material.SUPPORTED).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
		}
	
	val DriedBambooWallTorch = register<Block>(
		"dried_bamboo_wall_torch") {
			ExposedWallTorchBlock(blockSettings(Material.SUPPORTED).noCollision().breakInstantly().luminance { 14 }.noCollision(), ParticleTypes.FLAME)
		}
}