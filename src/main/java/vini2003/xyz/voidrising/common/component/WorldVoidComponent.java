package vini2003.xyz.voidrising.common.component;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;
import net.minecraft.network.packet.s2c.play.LightUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.command.WeatherCommand;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.WorldChunk;

public class WorldVoidComponent implements Component, ServerTickingComponent {
	private World world;
	
	private int age = 0;
	private int y = -1;
	private int x = 0;
	
	public WorldVoidComponent(World world) {
		this.world = world;
	}
	
	@Override
	public void readFromNbt(CompoundTag compoundTag) {
		this.age = compoundTag.getInt("Age");
	}
	
	@Override
	public void writeToNbt(CompoundTag compoundTag) {
		compoundTag.putInt("Age", this.age);
	}
	
	@Override
	public void serverTick() {
		if (y > 100) return;
		
		++age;
		
		if (age >= 20) {
			age = 0;
			++y;

			BlockPos spawn = ((ServerWorld) world).getSpawnPos();
			
			for (int x = spawn.getX() - 128; x < spawn.getX() + 128; ++x) {
				for (int z = spawn.getZ() - 128; z < spawn.getZ() + 128; ++z) {
					world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState());
				}
			}
		}
	}
	
	public void split() {
		((ServerWorld) world).setWeather(0, 600, true, true);
		
		BlockPos spawn = ((ServerWorld) world).getSpawnPos();
		
		for (int x = (spawn.getX() / 16) - 16; x < (spawn.getX() / 16) + 16; ++x) {
			for (int z = (spawn.getZ() / 16) - 1; z < (spawn.getZ() / 16) + 1; ++z) {
				Chunk chunk = world.getChunk(x, z);
				
				
				
				for (int i = 0; i < chunk.getSectionArray().length; ++i) {
					ChunkSection section = chunk.getSectionArray()[i];
					
					chunk.getSectionArray()[i] = new ChunkSection(section.getYOffset());
					
					chunk.getSectionArray()[i].setBlockState(0, 0, 0, Blocks.AIR.getDefaultState());
				}
				
				for (PlayerEntity player : world.getPlayers()) {
					Packet<?>[] packets = new Packet[2];
					
					packets[0] = new ChunkDataS2CPacket((WorldChunk) chunk, 65535);
					packets[1] = new LightUpdateS2CPacket(chunk.getPos(), world.getLightingProvider(), true);
					
					((ServerPlayerEntity) player).sendInitialChunkPackets(chunk.getPos(), packets[0], packets[1]);
				}
				
				LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);
				lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(new BlockPos(x * 16, world.getTopY(Heightmap.Type.MOTION_BLOCKING, x * 16 + world.random.nextInt(8), z * 16 + world.random.nextInt(8)), z * 16)));
				lightningEntity.setCosmetic(true);
				world.spawnEntity(lightningEntity);
			}
		}
	}
}
