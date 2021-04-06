package vini2003.xyz.eco.common.world.layer.implementation.mountain;

import Auburn.FastNoiseLite.Java.FastNoiseLite;
import vini2003.xyz.eco.common.util.NoiseUtils;
import vini2003.xyz.eco.common.util.NormalizeUtils;
import vini2003.xyz.eco.common.world.layer.base.NoiseLayer;

public class MountainNoiseLayer extends NoiseLayer {
	private final FastNoiseLite mountainNoise;
	private final FastNoiseLite fadeNoise;
	
	public MountainNoiseLayer(long seed) {
		super(seed);
		
		this.mountainNoise = new FastNoiseLite((int) seed);
		this.mountainNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
		this.mountainNoise.SetFrequency(0.005F);
		this.mountainNoise.SetDomainWarpType(FastNoiseLite.DomainWarpType.BasicGrid);
		this.mountainNoise.SetDomainWarpAmp(30.0F);
		this.mountainNoise.SetFractalType(FastNoiseLite.FractalType.None);
		
		this.fadeNoise = new FastNoiseLite((int) seed);
		this.fadeNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
		this.fadeNoise.SetFrequency(0.030F);
		this.fadeNoise.SetDomainWarpType(FastNoiseLite.DomainWarpType.BasicGrid);
		this.fadeNoise.SetDomainWarpAmp(30.0F);
		this.fadeNoise.SetFractalType(FastNoiseLite.FractalType.None);
	}
	
	@Override
	public float getNoise(int x, int z) {
		int oX = (x % 1536);
		int oZ = (z % 1536);
		
		if (oX > 768) {
			oX = 1536 - oX;
		}
		
		if (oZ > 768) {
			oZ = 1536 - oZ;
		}

		float distance = (float) Math.sqrt(oX * oX + oZ * oZ);
		float result = NoiseUtils.normalize(mountainNoise.GetNoise(x, z));
		
		return (1.0F - (distance - 384.0F) / (64.0F + Math.abs(distance - 384.0F))) * 0.375F * result;
	}
}
