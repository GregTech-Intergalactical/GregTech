package muramasa.gregtech.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.gregtech.Ref;
import muramasa.gregtech.gui.ScreenCoalBoiler;
import muramasa.gregtech.gui.ScreenSteamMachine;
import net.minecraft.client.gui.screens.MenuScreens;

public class ClientData {
    public static final MenuScreens.ScreenConstructor SCREEN_STEAM = AntimatterAPI.register(MenuScreens.ScreenConstructor.class, "steam", Ref.ID, (MenuScreens.ScreenConstructor)(a, b, c) -> new ScreenSteamMachine((ContainerBasicMachine) a,b,c));
    public static final MenuScreens.ScreenConstructor SCREEN_COAL = AntimatterAPI.register(MenuScreens.ScreenConstructor.class, "coal", Ref.ID, (MenuScreens.ScreenConstructor)(a, b, c) -> new ScreenCoalBoiler((ContainerMachine) a,b,c));

    public static void init() {
    }
}
