package fr.oxodao.fixescape.mixin;

import fr.oxodao.fixescape.ClientEventHandler;
import fr.oxodao.fixescape.ClientModEvents;
import net.minecraft.client.gui.components.EditBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to unfocus EMI search bar when pressing the new escape key.
 */
@Pseudo
@Mixin(targets = "appeng.client.gui.widgets.AETextField")
public class AETextFieldMixin {

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    public void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (!((EditBox) (Object) this).isFocused() ||
                keyCode != ClientModEvents.NEW_ESCAPE.get().getKey().getValue()) {
            return;
        }

        ClientEventHandler.unfocus((EditBox) (Object) this);
        cir.setReturnValue(true);
    }

}
