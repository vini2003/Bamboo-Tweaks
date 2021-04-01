package vini2003.xyz.bambootweaks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class ExposedDoorBlock extends DoorBlock {
	public ExposedDoorBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	public boolean isTranslucent(BlockState blockState, BlockView blockView, BlockPos blockPos) {
		return true;
	}
}