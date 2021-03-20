package muramasa.gti.proxy;

import muramasa.antimatter.AntimatterAPI;
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
    }
}
