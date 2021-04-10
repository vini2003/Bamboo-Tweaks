package vini2003.xyz.bambootweaks.common.block

import net.minecraft.block.*
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView

class BambooRodBlock(settings: Settings) : Block(settings) {
	companion object {
		val ShapeX = createCuboidShape(0.0, 6.0, 6.0, 16.0, 10.0, 10.0)
		val ShapeY = createCuboidShape(6.0, 0.0, 6.0, 10.0, 16.0, 10.0)
		val ShapeZ = createCuboidShape(6.0, 6.0, 0.0, 10.0, 10.0, 16.0)
	}
	
	init {
		defaultState = defaultState.with(FacingBlock.FACING, Direction.UP)
	}
	
	override fun getOutlineShape(
		blockState: BlockState,
		blockView: BlockView,
		blockPos: BlockPos,
		shapeContext: ShapeContext?
	): VoxelShape {
		return when (blockState.get(FacingBlock.FACING).axis!!) {
			Direction.Axis.X -> ShapeX
			Direction.Axis.Y -> ShapeY
			Direction.Axis.Z -> ShapeZ
		}
	}
	
	override fun getCollisionShape(
		blockState: BlockState,
		blockView: BlockView,
		blockPos: BlockPos,
		shapeContext: ShapeContext
	): VoxelShape {
		return when (collidable) {
			true -> blockState.getOutlineShape(blockView, blockPos)
			false -> VoxelShapes.empty()
		}
	}
	
	override fun getPlacementState(itemPlacementContext: ItemPlacementContext): BlockState {
		val direction = itemPlacementContext.side
		val blockState = itemPlacementContext.world.getBlockState(itemPlacementContext.blockPos.offset(direction.opposite))
		
		return if (blockState.block === this && blockState.get(FacingBlock.FACING) == direction) defaultState.with(
			FacingBlock.FACING,
			direction.opposite
		) else defaultState.with(FacingBlock.FACING, direction)
	}
	
	override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
		super.appendProperties(builder)
		
		builder.add(FacingBlock.FACING)
	}
}