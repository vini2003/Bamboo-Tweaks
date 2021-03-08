package vini2003.xyz.nofeedback.registry.common;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import vini2003.xyz.nofeedback.common.util.CommandUtils;

public class NoFeedbackCommands {
	public static int toggle(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		PlayerEntity player = context.getSource().getPlayer();
		
		CommandUtils.enableFeedback = !CommandUtils.enableFeedback;
		
		NoFeedbackNetworking.sendToggleFeedbackPacket(player, CommandUtils.enableFeedback);
		
		if (CommandUtils.enableFeedback) {
			context.getSource().getPlayer().sendMessage(new TranslatableText("text.nofeedback.enable"), true);
		} else {
			context.getSource().getPlayer().sendMessage(new TranslatableText("text.nofeedback.disable"), true);
		}
		
		return 1;
	}
	
	public static void initialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			LiteralCommandNode<ServerCommandSource> noFeedbackRoot = CommandManager.literal("nofeedback").build();
			
			LiteralCommandNode<ServerCommandSource> noFeedbackToggle =
					CommandManager.literal("toggle")
							.requires((source) -> source.hasPermissionLevel(2))
							.executes(NoFeedbackCommands::toggle)
							.build();
			
			
			noFeedbackRoot.addChild(noFeedbackToggle);
			
			dispatcher.getRoot().addChild(noFeedbackRoot);
		});
	}
}
