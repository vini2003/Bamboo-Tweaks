package bambootweaks.block;

import bambootweaks.util.BlockClimbable;
import net.minecraft.block.Block;
import net.minecraft.block.LadderBlock;
import net.minecraft.util.math.Direction;

public class ExposedLadderBlock extends LadderBlock implements BlockClimbable {
	public ExposedLadderBlock(Block.Settings settings) {
		super(settings);
	}
}