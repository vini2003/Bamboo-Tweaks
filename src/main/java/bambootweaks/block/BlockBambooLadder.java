package bambootweaks.block;

import bambootweaks.BlockClimbable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LadderBlock;
import net.minecraft.util.math.Direction;

public class BlockBambooLadder extends LadderBlock implements BlockClimbable {
    protected BlockBambooLadder(Block.Settings block$Settings_1) {
        super(block$Settings_1);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateFactory.getDefaultState()).with(FACING, Direction.NORTH)).with(WATERLOGGED, false));
    }
}