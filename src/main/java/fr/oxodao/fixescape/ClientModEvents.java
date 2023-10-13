package fr.oxodao.fixescape;

import net.minecraftforge.fml.common.Mod;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = FixEscape.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    public static final Lazy<KeyMapping> NEW_ESCAPE = Lazy.of(() -> new KeyMapping(
        "key.fixescape.second_escape",
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_UNKNOWN,
        "key.categories.misc"
    ));

    @SubscribeEvent
    public static void registerKeybinds(RegisterKeyMappingsEvent evt) {
        evt.register(NEW_ESCAPE.get());
    }
}
