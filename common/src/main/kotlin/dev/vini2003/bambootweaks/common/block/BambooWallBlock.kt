package dev.vini2003.bambootweaks.common.block

import net.minecraft.block.*
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView

class BambooWallBlock(settings: Settings) : Block(settings) {
	val ShapeX = createCuboidShape(7.0, 0.0, 0.0, 9.0, 16.0, 16.0)
	val ShapeZ = createCuboidShape(0.0, 0.0, 7.0, 16.0, 16.0, 9.0)
	
	init {
		defaultState = defaultState.with(HorizontalFacingBlock.FACING, Direction.NORTH)
	}
	
	override fun getOutlineShape(
		blockState: BlockState,
		blockView: BlockView,
		blockPos: BlockPos,
		shapeContext: ShapeContext
	): VoxelShape {
		return when (blockState.get(HorizontalFacingBlock.FACING)!!) {
			Direction.NORTH -> ShapeZ
			Direction.SOUTH -> ShapeZ
			Direction.WEST -> ShapeX
			Direction.EAST -> ShapeX
			
			else -> VoxelShapes.empty()
		}
	}
	
	override fun getCollisionShape(
		blockState: BlockState,
		blockView: BlockView,
		blockPos: BlockPos,
		shapeContext: ShapeContext
	): VoxelShape {
		return getOutlineShape(blockState, blockView, blockPos, shapeContext)
	}
	
	override fun getPlacementState(itemPlacementContext: ItemPlacementContext): BlockState? {
		return defaultState.with(
			HorizontalFacingBlock.FACING,
			itemPlacementContext.playerFacing.opposite
		)
	}
	
	override fun appendProperties(builder: StateManager.Builder<Block?, BlockState?>) {
		super.appendProperties(builder)
		
		builder.add(HorizontalFacingBlock.FACING)
	}
}