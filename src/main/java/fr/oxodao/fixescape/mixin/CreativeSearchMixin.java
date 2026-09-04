package fr.oxodao.fixescape.mixin;

import fr.oxodao.fixescape.ClientModEvents;
import fr.oxodao.fixescape.ClientEventHandler;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CreativeModeInventoryScreen.class)
public class CreativeSearchMixin {
    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    public void onKeyPressed(KeyEvent keyEvent, CallbackInfoReturnable<Boolean> cir) {
        if (keyEvent.key() == ClientModEvents.NEW_ESCAPE.get().getKey().getValue()) {
            var screen = ClientEventHandler.getCurrentScreen();
            if (screen != null && screen.shouldCloseOnEsc()) {
                screen.onClose();
            }
            cir.setReturnValue(true);
        }
    }
}
