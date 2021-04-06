	package vini2003.xyz.eco.common.world.layer.implementation.plain;
	
	import Auburn.FastNoiseLite.Java.FastNoiseLite;
	import vini2003.xyz.eco.common.util.NoiseUtils;
	import vini2003.xyz.eco.common.world.layer.base.NoiseLayer;
	
	/**
	 * A noise layer representing a plain field,
	 * at a Y level of 64 - corresponding to the
	 * sea level.
	 *
	 * Starting layer - other layers build on top
	 * of this.
	 */
	public class PlainNoiseLayer extends NoiseLayer {
		public PlainNoiseLayer(long seed) {
			super(seed);
		}
		
		@Override
		public float getNoise(int x, int z) {
			return NoiseUtils.normalize(NoiseUtils.getNoise(noise, x, z, 8, 0.5F, 1.0F, 0.33F));
		}
	}
