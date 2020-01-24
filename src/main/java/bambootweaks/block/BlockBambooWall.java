package bambootweaks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class BlockBambooWall extends Block {
	protected static final VoxelShape Z_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);
	protected static final VoxelShape X_SHAPE = Block.createCuboidShape(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 16.0D);
	private static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public BlockBambooWall(Settings block$Settings) {
		super(block$Settings);
		setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPosition, EntityContext entityContext) {
		switch (blockState.get(FACING)) {
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
		return getOutlineShape(blockState, blockView, blockPosition, entityContext);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext placementContext) {
		return getDefaultState().with(FACING, placementContext.getPlayerFacing().getOpposite());
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(FACING);
	}
}