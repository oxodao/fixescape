package fr.oxodao.fixescape.mixin;

import fr.oxodao.fixescape.ClientEventHandler;
import fr.oxodao.fixescape.ClientModEvents;
import net.minecraft.client.gui.components.EditBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "com.refinedmods.refinedstorage.common.support.widget.SearchFieldWidget")
public class RefinedStorageSearchFieldMixin {

    @Inject(method = "keyPressed", at = @At("HEAD"))
    public void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (!((EditBox) (Object) this).isFocused()
                || keyCode != ClientModEvents.NEW_ESCAPE.get().getKey().getValue()) {
            return;
        }

        ((EditBox) (Object) this).setFocused(false);
        ClientEventHandler.omitNextEscape();
    }
}
