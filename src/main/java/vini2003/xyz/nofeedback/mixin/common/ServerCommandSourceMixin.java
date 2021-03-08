package vini2003.xyz.nofeedback.mixin.common;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vini2003.xyz.nofeedback.common.util.CommandUtils;

@Mixin(ServerCommandSource.class)
public class ServerCommandSourceMixin {
	@Inject(at = @At("HEAD"), method = "sendFeedback", cancellable = true)
	void nofeedback_sendFeedback(Text message, boolean broadcastToOps, CallbackInfo ci) {
		if (!CommandUtils.enableFeedback) {
			ci.cancel();
		}
	}
	
	@Inject(at = @At("HEAD"), method = "sendError", cancellable = true)
	void nofeedback_sendError(Text message, CallbackInfo ci) {
		if (!CommandUtils.enableFeedback) {
			ci.cancel();
		}
	}
}
