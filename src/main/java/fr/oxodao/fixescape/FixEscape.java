package fr.oxodao.fixescape;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = FixEscape.MOD_ID, dist = Dist.CLIENT)
public class FixEscape
{
    public static final String MOD_ID = "fixescape";

    public FixEscape(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(ClientModEvents::registerKeybinds);
        NeoForge.EVENT_BUS.register(new ClientEventHandler());
    }
}
