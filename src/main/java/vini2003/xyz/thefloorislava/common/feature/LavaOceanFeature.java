package vini2003.xyz.thefloorislava.common.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class LavaOceanFeature extends Feature<DefaultFeatureConfig> {
	private final int topY;
	
	public LavaOceanFeature(Codec<DefaultFeatureConfig> configCodec, int topY) {
		super(configCodec);
		this.topY = topY;
	}
	
	@Override
	public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig config) {
		ChunkPos chunkPos = world.getChunk(pos).getPos();
		
		for (int x = chunkPos.getStartX(); x <= chunkPos.getEndX(); ++x) {
			for (int z = chunkPos.getStartZ(); z <= chunkPos.getEndZ(); ++z) {
				int topY = Math.max(77, Math.min(80, world.getTopY(Heightmap.Type.WORLD_SURFACE, x, z)));
				
				for (int y = 80; y >= topY; --y) {
					world.setBlockState(new BlockPos(x, y, z), Blocks.LAVA.getDefaultState(), 0);
				}
			}
		}
		
		return true;
	}
}
