package fr.oxodao.fixescape.mixin;

import fr.oxodao.fixescape.ClientEventHandler;
import fr.oxodao.fixescape.ClientModEvents;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.gui.components.EditBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "com.refinedmods.refinedstorage.common.support.widget.SearchFieldWidget")
public class RefinedStorageSearchFieldMixin {

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    public void onKeyPressed(KeyEvent keyEvent, CallbackInfoReturnable<Boolean> cir) {
        if (!((EditBox) (Object) this).isFocused()
                || keyEvent.key() != ClientModEvents.NEW_ESCAPE.get().getKey().getValue()) {
            return;
        }

        ClientEventHandler.unfocus((EditBox) (Object) this);
        cir.setReturnValue(true);
    }
}
