package muramasa.gti.proxy;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.client.ScreenSetup;
import muramasa.antimatter.gui.container.ContainerCover;
import muramasa.antimatter.gui.container.ContainerHatch;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import muramasa.antimatter.gui.screen.*;
import muramasa.gti.block.BlockCasing;
import muramasa.gti.data.GregTechData;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {

    public static void setup(FMLClientSetupEvent e) {
        AntimatterAPI.runLaterClient(() -> {
            RenderTypeLookup.setRenderLayer(GregTechData.RUBBER_SAPLING, RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(GregTechData.RUBBER_LEAVES, RenderType.getCutout());
            //AntimatterAPI.all(BlockCasingMachine.class, t -> RenderTypeLookup.setRenderLayer(t, RenderType.getCutout()));
            AntimatterAPI.all(BlockCasing.class, t -> RenderTypeLookup.setRenderLayer(t, RenderType.getCutout()));
        });

        ScreenSetup.<ContainerMachine, ScreenBasicMachine<ContainerMachine>>setScreenMapping(Data.BASIC_MENU_HANDLER, ScreenBasicMachine::new);
        ScreenSetup.<ContainerCover, ScreenCover<ContainerCover>>setScreenMapping(Data.COVER_MENU_HANDLER, ScreenCover::new);
        ScreenSetup.<ContainerMultiMachine, ScreenMultiMachine<ContainerMultiMachine>>setScreenMapping(Data.MULTI_MENU_HANDLER, ScreenMultiMachine::new);
        ScreenSetup.<ContainerHatch, ScreenHatch<ContainerHatch>>setScreenMapping(Data.HATCH_MENU_HANDLER, ScreenHatch::new);
        ScreenSetup.<ContainerMachine, ScreenSteamMachine<ContainerMachine>>setScreenMapping(Data.STEAM_MENU_HANDLER, ScreenSteamMachine::new);
    }
}
