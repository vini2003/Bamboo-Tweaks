package vini2003.xyz.bambootweaks.common.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.FacingBlock
import net.minecraft.block.ShapeContext
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World

class BambooSpikesBlock(settings: Settings) : Block(settings) {
	companion object {
		val ShapeUp = createCuboidShape(13.0, 12.0, 13.0, 3.0, 0.0, 3.0)
		val ShapeDown = createCuboidShape(13.0, 16.0, 13.0, 3.0, 4.0, 3.0)
		val ShapeNorth = createCuboidShape(3.0, 13.0, 16.0, 13.0, 3.0, 4.0)
		val ShapeSouth = createCuboidShape(13.0, 13.0, 0.0, 3.0, 3.0, 12.0)
		val ShapeEast = createCuboidShape(12.0, 3.0, 13.0, 0.0, 13.0, 3.0)
		val ShapeWest = createCuboidShape(16.0, 3.0, 13.0, 4.0, 13.0, 3.0)
	}
	
	init {
		defaultState = defaultState.with(FacingBlock.FACING, Direction.UP)
	}
	
	override fun onEntityCollision(blockState: BlockState, world: World, blockPos: BlockPos, entity: Entity) {
		if (entity.type === EntityType.PLAYER || entity.type.spawnGroup != SpawnGroup.MISC) entity.damage(
			DamageSource.CACTUS, 2.0F
		)
		
		super.onEntityCollision(blockState, world, blockPos, entity)
	}
	
	override fun onLandedUpon(world: World?, pos: BlockPos?, entity: Entity, distance: Float) {
		entity.handleFallDamage(distance, 5.0F)
		
		super.onLandedUpon(world, pos, entity, distance)
	}
	
	override fun getOutlineShape(
		blockState: BlockState,
		blockView: BlockView,
		blockPos: BlockPos,
		shapeContext: ShapeContext
	): VoxelShape {
		return when (blockState.get(FacingBlock.FACING)!!) {
			Direction.UP -> ShapeUp
			Direction.DOWN -> ShapeDown
			Direction.NORTH -> ShapeNorth
			Direction.SOUTH -> ShapeSouth
			Direction.WEST -> ShapeWest
			Direction.EAST -> ShapeEast
		}
	}
	
	override fun getCollisionShape(
		blockState: BlockState,
		blockView: BlockView,
		blockPos: BlockPos,
		shapeContext: ShapeContext
	): VoxelShape {
		return when (collidable) {
			true -> blockState.getOutlineShape(blockView, blockPos, shapeContext)
			false -> VoxelShapes.empty()
		}
	}
	
	override fun getPlacementState(itemPlacementContext: ItemPlacementContext): BlockState? {
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