package bambootweaks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class BlockBambooRod extends Block {
    private static final DirectionProperty FACING = FacingBlock.FACING;

    protected static final VoxelShape Y_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_SHAPE = Block.createCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D);
    protected static final VoxelShape X_SHAPE = Block.createCuboidShape(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);

    public BlockBambooRod(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateFactory.getDefaultState()).with(FACING, Direction.UP)));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPosition, EntityContext entityContext) {
        switch(((Direction)blockState.get(FACING)).getAxis()) {
        	case Z:
				return Z_SHAPE;
        	case Y:
				return Y_SHAPE;
			case X:
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
    	Direction direction = placementContext.getSide();
        BlockState blockState = placementContext.getWorld().getBlockState(placementContext.getBlockPos().offset(direction.getOpposite()));
        return blockState.getBlock() == this && blockState.get(FACING) == direction ? (BlockState)this.getDefaultState().with(FACING, direction.getOpposite()) : (BlockState)this.getDefaultState().with(FACING, direction);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
    	return BlockRenderLayer.CUTOUT;
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
    	builder.add(FACING);
    }
}

