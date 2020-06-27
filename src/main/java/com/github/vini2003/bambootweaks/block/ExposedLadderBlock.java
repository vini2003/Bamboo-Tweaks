package com.github.vini2003.bambootweaks.block;

import com.github.vini2003.bambootweaks.utilities.BlockClimbable;
import net.minecraft.block.Block;
import net.minecraft.block.LadderBlock;

public class ExposedLadderBlock extends LadderBlock implements BlockClimbable {
	public ExposedLadderBlock(Block.Settings settings) {
		super(settings);
	}
}