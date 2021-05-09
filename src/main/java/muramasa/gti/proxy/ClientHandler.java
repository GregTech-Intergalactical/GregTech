package muramasa.gti.proxy;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.client.ScreenSetup;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.gti.block.BlockCasing;
import muramasa.gti.data.GregTechData;
import muramasa.gti.data.Guis;
import muramasa.gti.gui.ScreenCoalBoiler;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {

    public static void setup(FMLClientSetupEvent e) {
        e.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(GregTechData.RUBBER_SAPLING, RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(GregTechData.RUBBER_LEAVES, RenderType.getCutout());
            AntimatterAPI.all(BlockCasing.class, t -> RenderTypeLookup.setRenderLayer(t, RenderType.getCutout()));
        });
        ScreenSetup.<ContainerBasicMachine, ScreenCoalBoiler<ContainerBasicMachine>>setScreenMapping(Guis.COAL_BOILER_MENU_HANDLER, ScreenCoalBoiler::new);
    }
}
