package vini2003.xyz.eco.common.dimension.type;

import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.BiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import vini2003.xyz.eco.common.biome.EcoBiomeAccessType;

import java.util.OptionalLong;

public final class EcoDimensionType extends DimensionType {
	public static final EcoDimensionType INSTANCE = new EcoDimensionType();
	
	private EcoDimensionType() {
		super(OptionalLong.empty(), true, false, false, true, 1D, false, true, true, false, true, 256, EcoBiomeAccessType.INSTANCE, BlockTags.INFINIBURN_OVERWORLD.getId(), OVERWORLD_ID, 0.0F);
	}
	
	@Override
	public BiomeAccessType getBiomeAccessType() {
		return super.getBiomeAccessType();
	}
}
