package fr.oxodao.fixescape;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;

public class ClientEventHandler {
    private static boolean IS_KEY_PRESSED = false;
    private static boolean OMIT_NEXT_CHARACTER = false;

    public static void omitNextEscape() {
        ClientEventHandler.IS_KEY_PRESSED = true;
    }

    public static void unfocus(EditBox textField) {
        Minecraft mc = Minecraft.getInstance();
        Screen screen = getCurrentScreen(mc);
        if (screen != null && screen.getFocused() == textField) {
            screen.setFocused(null);
        }

        textField.setFocused(false);
        ClientEventHandler.OMIT_NEXT_CHARACTER = true;
        omitNextEscape();
    }

    @SubscribeEvent
    public void onScreenKeyPressed(ScreenEvent.KeyPressed.Post evt) {
        if (ClientEventHandler.IS_KEY_PRESSED) {
            return;
        }

        if (ClientModEvents.NEW_ESCAPE.get().getKey().getValue() != evt.getKeyCode()) {
            return;
        }

        ClientEventHandler.IS_KEY_PRESSED = true;

        Minecraft mc = Minecraft.getInstance();
        Screen screen = getCurrentScreen(mc);
        if (screen != null && screen.shouldCloseOnEsc()) {
            screen.onClose();
        }
    }

    @SubscribeEvent
    public void onScreenKeyReleased(ScreenEvent.KeyReleased.Post evt) {
        ClientEventHandler.IS_KEY_PRESSED = false;
        ClientEventHandler.OMIT_NEXT_CHARACTER = false;
    }

    @SubscribeEvent
    public void onScreenCharacterTyped(ScreenEvent.CharacterTyped.Pre evt) {
        if (!ClientEventHandler.OMIT_NEXT_CHARACTER) {
            return;
        }

        ClientEventHandler.OMIT_NEXT_CHARACTER = false;
        evt.setCanceled(true);
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.Key evt) {
        var isCorrectKey = ClientModEvents.NEW_ESCAPE.get().getKey().getValue() == evt.getKey();

        if (evt.getAction() == InputConstants.RELEASE && ClientEventHandler.IS_KEY_PRESSED && isCorrectKey) {
            ClientEventHandler.IS_KEY_PRESSED = false;
            return;
        } else if (evt.getAction() != InputConstants.PRESS || !isCorrectKey) {
            return;
        }

        if (!ClientEventHandler.IS_KEY_PRESSED) {
            ClientEventHandler.IS_KEY_PRESSED = true;

            Minecraft mc = Minecraft.getInstance();
            if (getCurrentScreen(mc) == null) {
                mc.pauseGame(false);
            }
        }
    }

    public static Screen getCurrentScreen() {
        return getCurrentScreen(Minecraft.getInstance());
    }

    private static Screen getCurrentScreen(Minecraft mc) {
        try {
            return (Screen) Minecraft.class.getField("screen").get(mc);
        } catch (NoSuchFieldException ignored) {
            try {
                return (Screen) mc.gui.getClass().getMethod("screen").invoke(mc.gui);
            } catch (ReflectiveOperationException exception) {
                throw new IllegalStateException("Unable to access the current screen", exception);
            }
        } catch (IllegalAccessException exception) {
            throw new IllegalStateException("Unable to access the current screen", exception);
        }
    }
}
