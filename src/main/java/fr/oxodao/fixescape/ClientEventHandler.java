package fr.oxodao.fixescape;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientEventHandler {
    @SubscribeEvent
    public void onKeyPress(InputEvent.Key evt) {
        // We only care about the PRESS action
        if (evt.getAction() != InputConstants.PRESS) {
            return;
        }

        // We only care about OUR key
        if (ClientModEvents.NEW_ESCAPE.get().getKey().getValue() != evt.getKey()) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc.screen != null && mc.screen.shouldCloseOnEsc()) {
            mc.screen.onClose();
        } else if (mc.screen == null) {
            mc.pauseGame(false);
        }
    }
}
