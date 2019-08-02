package bambootweaks.block;

import net.minecraft.item.ItemPlacementContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.Block;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.StateFactory;

public class BlockBambooSpike extends Block {
    private static final DirectionProperty FACING = FacingBlock.FACING;

    protected static final VoxelShape UP_SHAPE = Block.createCuboidShape(13.0D, 12.0D, 13.0D, 3.0D, 0.0D, 3.0D);
    protected static final VoxelShape DOWN_SHAPE = Block.createCuboidShape(13.0D, 16.0D, 13.0D, 3.0D, 4.0D, 3.0D);

    protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(3.0D, 13.0D, 16.0D, 13.0D, 3.0D, 4.0D);
    protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(13.0D, 13.0D, 0.0D, 3.0D, 3.0D, 12.0D);
 
    protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(12.0D, 3.0D, 13.0D, 0.0D, 13.0D, 3.0D);
    protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(16.0D, 3.0D, 13.0D, 4.0D, 13.0D, 3.0D);

    public BlockBambooSpike(Settings block_settings) {
        super(block_settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateFactory.getDefaultState()).with(FACING, Direction.UP)));
    }
    
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        Direction direction_1 = itemPlacementContext_1.getSide();
        BlockState blockState_1 = itemPlacementContext_1.getWorld().getBlockState(itemPlacementContext_1.getBlockPos().offset(direction_1.getOpposite()));
        return blockState_1.getBlock() == this && blockState_1.get(FACING) == direction_1 ? (BlockState)this.getDefaultState().with(FACING, direction_1.getOpposite()) : (BlockState)this.getDefaultState().with(FACING, direction_1);
    }


    public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
        switch((Direction)blockState_1.get(FACING)) {
        default:
        case UP:
            return UP_SHAPE;
        case DOWN:
            return DOWN_SHAPE;
        case NORTH:
            return NORTH_SHAPE;
        case SOUTH:
            return SOUTH_SHAPE;
        case WEST:
            return WEST_SHAPE;
        case EAST:
            return EAST_SHAPE;
        }
    }
 
    public VoxelShape getCollisionShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
       return this.collidable ? blockState_1.getOutlineShape(blockView_1, blockPos_1) : VoxelShapes.empty();
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.add(FACING);
    }

    @Override
    public void onEntityCollision(BlockState blockState_1, World world_1, BlockPos blockPos_1, Entity entity_1) {
        entity_1.damage(DamageSource.CACTUS, 2.0F);
        super.onEntityCollision(blockState_1, world_1, blockPos_1, entity_1);
    }
}

