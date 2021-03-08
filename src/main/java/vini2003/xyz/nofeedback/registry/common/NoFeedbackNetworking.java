package vini2003.xyz.nofeedback.registry.common;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import vini2003.xyz.nofeedback.NoFeedback;

public class NoFeedbackNetworking {
	public static final Identifier TOGGLE_FEEDBACK = NoFeedback.identifier("toggle_feedback");
	
	public static void sendToggleFeedbackPacket(PlayerEntity player, boolean enableFeedback) {
		PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
		
		buf.writeBoolean(enableFeedback);
		
		ServerPlayNetworking.send((ServerPlayerEntity) player, TOGGLE_FEEDBACK, buf);
	}
	
	public static void initialize() {
	
	}
}
