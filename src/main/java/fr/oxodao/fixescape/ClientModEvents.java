package fr.oxodao.fixescape;

import com.mojang.blaze3d.platform.InputConstants;
import java.lang.reflect.Constructor;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

public class ClientModEvents
{
    public static final Lazy<KeyMapping> NEW_ESCAPE = Lazy.of(ClientModEvents::createSecondEscapeKey);

    private static KeyMapping createSecondEscapeKey()
    {
        try {
            for (Constructor<?> constructor : KeyMapping.class.getConstructors()) {
                var parameters = constructor.getParameterTypes();
                if (parameters.length != 4
                        || parameters[0] != String.class
                        || parameters[1] != InputConstants.Type.class
                        || parameters[2] != Integer.TYPE) {
                    continue;
                }

                Object category = parameters[3] == String.class
                        ? "key.categories.misc"
                        : parameters[3].getField("MISC").get(null);
                return (KeyMapping) constructor.newInstance(
                        "key.fixescape.second_escape", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, category);
            }
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException("Unable to create FixEscape key mapping", exception);
        }

        throw new IllegalStateException("Unsupported KeyMapping constructor");
    }

    public static void registerKeybinds(RegisterKeyMappingsEvent evt)
    {
        evt.register(NEW_ESCAPE.get());
    }
}
