package muramasa.gregtech.data;

import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.gregtech.gui.ScreenCoalBoiler;
import muramasa.gregtech.gui.ScreenSteamMachine;
import net.minecraft.client.gui.screens.MenuScreens;

public class ClientData {
    public static final MenuScreens.ScreenConstructor SCREEN_STEAM = (a, b, c) -> new ScreenSteamMachine((ContainerBasicMachine) a,b,c);
    public static final MenuScreens.ScreenConstructor SCREEN_COAL = (a, b, c) -> new ScreenCoalBoiler((ContainerMachine) a,b,c);
}
