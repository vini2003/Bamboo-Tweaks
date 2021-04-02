package vini2003.xyz.heal.registry.common;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class HealCommands {
	private static int heal(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		PlayerEntity player = context.getSource().getPlayer();
		
		context.getSource().getMinecraftServer().execute(() -> {
			player.setHealth(player.getMaxHealth());
		});
		
		return 1;
	}
	
	public static void initialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			LiteralCommandNode<ServerCommandSource> heal =
					CommandManager.literal("heal")
							.requires((source) -> source.hasPermissionLevel(2))
							.executes(HealCommands::heal)
							.build();
			
			dispatcher.getRoot().addChild(heal);
		});
	}
}
