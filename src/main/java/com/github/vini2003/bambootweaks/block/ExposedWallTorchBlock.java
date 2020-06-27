package com.github.vini2003.bambootweaks.block;

import net.minecraft.block.WallTorchBlock;
import net.minecraft.particle.ParticleEffect;

public class ExposedWallTorchBlock extends WallTorchBlock {
	public ExposedWallTorchBlock(Settings settings, ParticleEffect particleEffect) {
		super(settings, particleEffect);
	}

	@Override
	public String getTranslationKey() {
		return "block.com.github.vini2003.bambootweaks.bamboo_torch";
	}
}