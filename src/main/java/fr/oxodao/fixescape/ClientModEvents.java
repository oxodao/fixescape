package fr.oxodao.fixescape;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

public class ClientModEvents
{
    public static final Lazy<KeyMapping> NEW_ESCAPE = Lazy.of(() -> new KeyMapping(
            "key.fixescape.second_escape",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            KeyMapping.Category.MISC));

    public static void registerKeybinds(RegisterKeyMappingsEvent evt)
    {
        evt.register(NEW_ESCAPE.get());
    }
}
