package vini2003.xyz.thefloorislava.common.component;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.WorldChunk;

public class ChunkLavaComponent implements Component, ServerTickingComponent {
	private boolean placed = false;
	
	private Chunk chunk;
	
	public ChunkLavaComponent(Chunk chunk) {
		this.chunk = chunk;
	}
	
	@Override
	public void serverTick() {
		WorldChunk worldChunk = ((WorldChunk) chunk);
		
		if (worldChunk.getWorld().getRegistryKey().equals(World.OVERWORLD) && !placed) {
			placed = true;
			
			for (int x = chunk.getPos().getStartX(); x <= chunk.getPos().getEndX(); ++x) {
				for (int z = chunk.getPos().getStartZ(); z <= chunk.getPos().getEndZ(); ++z) {
					worldChunk.getWorld().setBlockState(new BlockPos(x, 70, z), Blocks.LAVA.getDefaultState());
				}
			}
		}
	}
	
	public boolean isPlaced() {
		return placed;
	}
	
	public void setPlaced(boolean placed) {
		this.placed = placed;
	}
	
	@Override
	public void readFromNbt(CompoundTag compoundTag) {
		compoundTag.putBoolean("Placed", placed);
	}
	
	@Override
	public void writeToNbt(CompoundTag compoundTag) {
		placed = compoundTag.getBoolean("Placed");
	}
}
