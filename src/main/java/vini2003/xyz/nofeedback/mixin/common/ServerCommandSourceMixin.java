package vini2003.xyz.nofeedback.mixin.common;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerCommandSource.class)
public class ServerCommandSourceMixin {
	@Inject(at = @At("HEAD"), method = "sendFeedback", cancellable = true)
	void nofeedback_sendFeedback(Text message, boolean broadcastToOps, CallbackInfo ci) {
		ci.cancel();
	}
}
