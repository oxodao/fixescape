package fr.oxodao.fixescape.mixin;

import fr.oxodao.fixescape.ClientModEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CreativeModeInventoryScreen.class)
public class CreativeSearchLegacyMixin {
    @Inject(method = "keyPressed", at = @At("HEAD"))
    public void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (keyCode == ClientModEvents.NEW_ESCAPE.get().getKey().getValue()) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.screen != null && mc.screen.shouldCloseOnEsc()) {
                mc.screen.onClose();
            }
        }
    }
}
