package vini2003.xyz.voidrising.registry.common;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import vini2003.xyz.voidrising.common.component.WorldVoidComponent;

public class VoidRisingCommands {
	public static int split(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		World world = context.getSource().getWorld();
		
		WorldVoidComponent component = VoidRisingComponents.WORLD_VOID_COMPONENT.get(world);
		
		component.split();
		
		return 1;
	}
	
	public static void initialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			LiteralCommandNode<ServerCommandSource> voidRisingRoot = CommandManager.literal("voidrising").build();
			
			LiteralCommandNode<ServerCommandSource> voidRisingSplit =
					CommandManager.literal("split")
							.requires((source) -> source.hasPermissionLevel(2))
							.executes(VoidRisingCommands::split)
							.build();
			
			
			voidRisingRoot.addChild(voidRisingSplit);
			
			dispatcher.getRoot().addChild(voidRisingRoot);
		});
	}
}
