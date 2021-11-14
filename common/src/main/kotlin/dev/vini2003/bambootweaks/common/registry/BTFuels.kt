package dev.vini2003.bambootweaks.common.registry

import dev.architectury.registry.fuel.FuelRegistry

object BTFuels {
	fun init() {
		FuelRegistry.register(500, BTBlocks.BambooBlock.orNull)
		FuelRegistry.register(200, BTBlocks.BambooDoor.orNull)
		FuelRegistry.register(300, BTBlocks.BambooFence.orNull)
		FuelRegistry.register(300, BTBlocks.BambooFenceGate.orNull)
		FuelRegistry.register(300, BTBlocks.BambooWall.orNull)
		FuelRegistry.register(300, BTBlocks.BambooLadder.orNull)
		FuelRegistry.register(200, BTBlocks.BambooRod.orNull)
		FuelRegistry.register(300, BTBlocks.BambooStairs.orNull)
		FuelRegistry.register(300, BTBlocks.BambooSpikes.orNull)
		FuelRegistry.register(300, BTBlocks.BambooSlab.orNull)
		FuelRegistry.register(300, BTBlocks.BambooTrapdoor.orNull)
		FuelRegistry.register(300, BTBlocks.BambooTrapdoor.orNull)
		
		FuelRegistry.register(500, BTBlocks.DryBambooBlock.orNull)
		FuelRegistry.register(200, BTBlocks.DryBambooDoor.orNull)
		FuelRegistry.register(300, BTBlocks.DryBambooFence.orNull)
		FuelRegistry.register(300, BTBlocks.DryBambooFenceGate.orNull)
		FuelRegistry.register(300, BTBlocks.DryBambooWall.orNull)
		FuelRegistry.register(300, BTBlocks.DryBambooLadder.orNull)
		FuelRegistry.register(200, BTBlocks.DryBambooRod.orNull)
		FuelRegistry.register(300, BTBlocks.DryBambooStairs.orNull)
		FuelRegistry.register(300, BTBlocks.DryBambooSpikes.orNull)
		FuelRegistry.register(300, BTBlocks.DryBambooSlab.orNull)
		FuelRegistry.register(300, BTBlocks.DryBambooTrapdoor.orNull)
		FuelRegistry.register(300, BTBlocks.DryBambooTrapdoor.orNull)
		
		FuelRegistry.register(60, BTItems.DryBamboo.orNull)
	}
}