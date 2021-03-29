package vini2003.xyz.thefloorislava.registry.common;

import dev.onyxstudios.cca.api.v3.chunk.ChunkComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.chunk.ChunkComponentInitializer;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import vini2003.xyz.thefloorislava.TheFloorIsLava;
import vini2003.xyz.thefloorislava.common.component.ChunkLavaComponent;

public class TheFloorIsLavaClientComponents implements ChunkComponentInitializer {
	public static final ComponentKey<ChunkLavaComponent> CHUNK_LAVA_COMPONENT = ComponentRegistry.getOrCreate(TheFloorIsLava.identifier("chunk_lava_component"), ChunkLavaComponent.class);
	
	@Override
	public void registerChunkComponentFactories(ChunkComponentFactoryRegistry chunkComponentFactoryRegistry) {
		chunkComponentFactoryRegistry.register(CHUNK_LAVA_COMPONENT, ChunkLavaComponent::new);
	}
}
