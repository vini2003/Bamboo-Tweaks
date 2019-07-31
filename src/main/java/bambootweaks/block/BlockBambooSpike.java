package bambootweaks.block;

import net.minecraft.block.BlockState;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.Entity;

import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockRenderType;
import net.minecraft.world.BlockView;
import net.minecraft.block.Block;

public class BlockBambooSpike extends Block {
    public BlockBambooSpike(Settings block_settings) {
        super(block_settings);
    }

    protected static final VoxelShape SHAPE = BlockBambooSpike.createCuboidShape(13.0D, 0.0D, 13.0D, 3.0D, 12.0D, 3.0D);

    public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
        return SHAPE;
    }
 
    public VoxelShape getCollisionShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
       return this.collidable ? blockState_1.getOutlineShape(blockView_1, blockPos_1) : VoxelShapes.empty();
    }
    public BlockRenderType getRenderType(BlockState blockState_1) {
        return BlockRenderType.MODEL;
     }
  
    public BlockRenderLayer getRenderLayer() {
       return BlockRenderLayer.CUTOUT;
    }

    @Override
    public void onEntityCollision(BlockState blockState_1, World world_1, BlockPos blockPos_1, Entity entity_1) {
        entity_1.damage(DamageSource.CACTUS, 2.0F);
        super.onEntityCollision(blockState_1, world_1, blockPos_1, entity_1);
     }
}

