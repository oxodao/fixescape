package fr.oxodao.fixescape;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(FixEscape.MODID)
public class FixEscape
{
    public static final String MODID = "fixescape";

    public FixEscape()
    {
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }
}
