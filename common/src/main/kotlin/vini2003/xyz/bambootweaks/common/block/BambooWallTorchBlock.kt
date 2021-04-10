package vini2003.xyz.bambootweaks.common.block

import net.minecraft.block.WallTorchBlock
import net.minecraft.particle.ParticleEffect

class BambooWallTorchBlock(settings: Settings, particleEffect: ParticleEffect) : WallTorchBlock(settings, particleEffect) {
	override fun getTranslationKey(): String = "block.bambootweaks.bamboo_torch"
}