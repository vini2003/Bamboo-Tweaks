package dev.vini2003.bambootweaks.common.block

import net.minecraft.block.*
import net.minecraft.particle.ParticleEffect

class ExposedDoorBlock(settings: Settings) : DoorBlock(settings)

class ExposedLadderBlock(settings: Settings) : LadderBlock(settings)

class ExposedStairsBlock(blockState: BlockState, settings: Settings) : StairsBlock(blockState, settings)

class ExposedTorchBlock(settings: Settings, effect: ParticleEffect) : TorchBlock(settings, effect)

class ExposedWallTorchBlock(settings: Settings, effect: ParticleEffect) : WallTorchBlock(settings, effect)


