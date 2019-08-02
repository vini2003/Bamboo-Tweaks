package bambootweaks.block;

import net.minecraft.item.ItemPlacementContext;
import net.minecraft.entity.EntityContext;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.math.Direction;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.BlockMirror;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.StateFactory;
import net.minecraft.world.BlockView;


public class BlockBambooRod extends Block {
    private static final DirectionProperty FACING = FacingBlock.FACING;

    protected static final VoxelShape Y_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_SHAPE = Block.createCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D);
    protected static final VoxelShape X_SHAPE = Block.createCuboidShape(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);

    public BlockBambooRod(Settings block_settings) {
        super(block_settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateFactory.getDefaultState()).with(FACING, Direction.UP)));
    }

    public BlockState rotate(BlockState blockState_1, BlockRotation blockRotation_1) {
        return (BlockState)blockState_1.with(FACING, blockRotation_1.rotate((Direction)blockState_1.get(FACING)));
    }

    public BlockState mirror(BlockState blockState_1, BlockMirror blockMirror_1) {
        return (BlockState)blockState_1.with(FACING, blockMirror_1.apply((Direction)blockState_1.get(FACING)));
    }

    public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
        switch(((Direction)blockState_1.get(FACING)).getAxis()) {
        case X:
        default:
           return X_SHAPE;
        case Z:
           return Z_SHAPE;
        case Y:
           return Y_SHAPE;
        }
    }
 
    public VoxelShape getCollisionShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
       return this.collidable ? blockState_1.getOutlineShape(blockView_1, blockPos_1) : VoxelShapes.empty();
    }
  
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        Direction direction_1 = itemPlacementContext_1.getSide();
        BlockState blockState_1 = itemPlacementContext_1.getWorld().getBlockState(itemPlacementContext_1.getBlockPos().offset(direction_1.getOpposite()));
        return blockState_1.getBlock() == this && blockState_1.get(FACING) == direction_1 ? (BlockState)this.getDefaultState().with(FACING, direction_1.getOpposite()) : (BlockState)this.getDefaultState().with(FACING, direction_1);
    }

    public BlockRenderLayer getRenderLayer() {
       return BlockRenderLayer.CUTOUT;
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.add(FACING);
    }
}

