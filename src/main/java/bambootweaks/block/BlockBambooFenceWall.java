package bambootweaks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class BlockBambooFenceWall extends Block {
    private static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    protected static final VoxelShape Y_SHAPE = Block.createCuboidShape(7.0D, 0.0D, 0.0D, 10.0D, 9.0D, 16.0D);
    protected static final VoxelShape Z_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);
    protected static final VoxelShape X_SHAPE = Block.createCuboidShape(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 16.0D);

    public BlockBambooFenceWall(Settings block$Settings) {
        super(block$Settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateFactory.getDefaultState()).with(FACING, Direction.NORTH)));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPosition, EntityContext entityContext) {
        switch((Direction)blockState.get(FACING)) {
        	case NORTH:
        		return Z_SHAPE;
        	case SOUTH:
        		return Z_SHAPE;
        	case WEST:
        		return X_SHAPE;
			case EAST:
				return X_SHAPE;
			default:
				return X_SHAPE;
    	}
   	}

   	@Override
   	public VoxelShape getCollisionShape(BlockState blockState, BlockView blockView, BlockPos blockPosition, EntityContext entityContext) {
    	return this.collidable ? blockState.getOutlineShape(blockView, blockPosition) : VoxelShapes.empty();
   	}

   	@Override
	public BlockState getPlacementState(ItemPlacementContext placementContext) {
    	return (BlockState)this.getDefaultState().with(FACING, placementContext.getPlayerFacing().getOpposite());
	}

	protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
}