package com.github.vini2003.bambootweaks.utilities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;

public interface BlockClimbable {
	default boolean canClimb(LivingEntity livingEntity, BlockState blockState, BlockPos blockPos) {
		return true;
	}
}