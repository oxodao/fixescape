package fr.oxodao.fixescape;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientEventHandler {
    private static boolean IS_KEY_PRESSED = false;

    @SubscribeEvent
    public void onScreenKeyPressed(ScreenEvent.KeyPressed.Post evt){
        if (ClientEventHandler.IS_KEY_PRESSED) {
            return;
        }

        if (ClientModEvents.NEW_ESCAPE.get().getKey().getValue() != evt.getKeyCode()) {
            return;
        }

        ClientEventHandler.IS_KEY_PRESSED = true;

        Minecraft mc = Minecraft.getInstance();
        if (mc.screen != null && mc.screen.shouldCloseOnEsc()) {
            mc.screen.onClose();
        } else if (mc.screen == null) {
            mc.pauseGame(false);
        }
    }

    @SubscribeEvent
    public void onScreenKeyRelease(ScreenEvent.KeyReleased.Post evt){
        ClientEventHandler.IS_KEY_PRESSED = false;
    }

    /*
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
        if (mc.screen == null) {
            mc.pauseGame(false);
        }
    }*/
}
