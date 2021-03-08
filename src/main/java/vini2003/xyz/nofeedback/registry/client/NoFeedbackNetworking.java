package vini2003.xyz.nofeedback.registry.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import vini2003.xyz.nofeedback.NoFeedback;
import vini2003.xyz.nofeedback.common.util.CommandUtils;

public class NoFeedbackNetworking {
	public static final Identifier TOGGLE_FEEDBACK = NoFeedback.identifier("toggle_feedback");
	
	private static void receiveToggleFeedback(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {
		CommandUtils.enableFeedback = buf.readBoolean();
	}
	
	public static void initialize() {
		ClientPlayNetworking.registerGlobalReceiver(TOGGLE_FEEDBACK, NoFeedbackNetworking::receiveToggleFeedback);
	}
}
