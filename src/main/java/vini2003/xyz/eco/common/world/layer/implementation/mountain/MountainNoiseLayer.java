package vini2003.xyz.eco.common.world.layer.implementation.mountain;

import Auburn.FastNoiseLite.Java.FastNoiseLite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import vini2003.xyz.eco.common.util.NoiseUtils;
import vini2003.xyz.eco.common.util.NormalizeUtils;
import vini2003.xyz.eco.common.world.layer.base.NoiseLayer;

public class MountainNoiseLayer extends NoiseLayer {
	private final FastNoiseLite mountainNoise;
	
	public MountainNoiseLayer(long seed) {
		super(seed);
		
		this.mountainNoise = new FastNoiseLite((int) seed);
		this.mountainNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
		this.mountainNoise.SetFrequency(0.005F);
		this.mountainNoise.SetDomainWarpType(FastNoiseLite.DomainWarpType.BasicGrid);
		this.mountainNoise.SetDomainWarpAmp(30.0F);
		this.mountainNoise.SetFractalType(FastNoiseLite.FractalType.None);
	}
	
	@Override
	public float getNoise(int x, int z) {
		float distance = (float) (512.0F - Math.sqrt(new BlockPos(x, 0, z).getSquaredDistance(BlockPos.ZERO)));
		
		float result = NoiseUtils.normalize(NoiseUtils.getNoise(mountainNoise, x, z, 8, 1.0F, 1.0F, 0.33F));
		
		return (1.0F - (distance - 128.0F) / (64.0F + Math.abs(distance - 128.0F))) * 0.375F * result;
	}
}
