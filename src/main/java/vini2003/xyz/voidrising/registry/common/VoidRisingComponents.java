package vini2003.xyz.voidrising.registry.common;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import vini2003.xyz.voidrising.VoidRising;
import vini2003.xyz.voidrising.common.component.WorldVoidComponent;

public class VoidRisingComponents implements WorldComponentInitializer {
	public static final ComponentKey<WorldVoidComponent> WORLD_VOID_COMPONENT = ComponentRegistry.getOrCreate(VoidRising.identifier("world_void_component"), WorldVoidComponent.class);
	
	@Override
	public void registerWorldComponentFactories(WorldComponentFactoryRegistry worldComponentFactoryRegistry) {
		worldComponentFactoryRegistry.register(WORLD_VOID_COMPONENT, WorldVoidComponent::new);
	}
}
