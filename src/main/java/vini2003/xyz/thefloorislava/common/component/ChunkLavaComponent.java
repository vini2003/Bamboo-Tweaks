package vini2003.xyz.thefloorislava.common.component;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

public class ChunkLavaComponent implements Component, ServerTickingComponent {
	private boolean placed = false;
	
	private Chunk chunk;
	
	public ChunkLavaComponent(Chunk chunk) {
		this.chunk = chunk;
	}
	
	@Override
	public void serverTick() {
		WorldChunk worldChunk = ((WorldChunk) chunk);
		
		int maxY =
				worldChunk.getWorld().getRegistryKey().equals(World.OVERWORLD) ? 82 :
						worldChunk.getWorld().getRegistryKey().equals(World.NETHER) ? 65 :
								worldChunk.getWorld().getRegistryKey().equals(World.END) ? 45 : 0;
		
		if (!placed) {
			placed = true;
			
			for (int x = chunk.getPos().getStartX(); x <= chunk.getPos().getEndX(); ++x) {
				for (int z = chunk.getPos().getStartZ(); z <= chunk.getPos().getEndZ(); ++z) {
					int topY = Math.min(maxY, worldChunk.getWorld().getTopY(Heightmap.Type.WORLD_SURFACE, x, z));
					
					for (int y = maxY; y >= topY; --y) {
						worldChunk.getWorld().setBlockState(new BlockPos(x, y, z), Blocks.LAVA.getDefaultState());
					}
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
		placed = compoundTag.getBoolean("Placed");
	}
	
	@Override
	public void writeToNbt(CompoundTag compoundTag) {
		compoundTag.putBoolean("Placed", placed);
	}
}
