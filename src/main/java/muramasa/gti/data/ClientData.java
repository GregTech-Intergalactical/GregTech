package muramasa.gti.data;

import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.gti.gui.ScreenCoalBoiler;
import muramasa.gti.gui.ScreenLavaBoiler;
import muramasa.gti.gui.ScreenSteamMachine;
import net.minecraft.client.gui.ScreenManager;

public class ClientData {
    public static final ScreenManager.IScreenFactory SCREEN_STEAM = (a, b, c) -> new ScreenSteamMachine((ContainerBasicMachine) a,b,c);
    public static final ScreenManager.IScreenFactory SCREEN_COAL = (a, b, c) -> new ScreenCoalBoiler((ContainerMachine) a,b,c);
    public static final ScreenManager.IScreenFactory SCREEN_LAVA = (a, b, c) -> new ScreenLavaBoiler((ContainerMachine) a,b,c);
}
