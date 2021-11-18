package muramasa.gti.proxy;

import muramasa.antimatter.AntimatterAPI;
import muramasa.gti.block.BlockCasing;
import muramasa.gti.data.GregTechData;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {

    public static void setup(FMLClientSetupEvent e) {
        e.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(GregTechData.RUBBER_SAPLING, RenderType.cutout());
            RenderTypeLookup.setRenderLayer(GregTechData.RUBBER_LEAVES, RenderType.cutout());
            AntimatterAPI.all(BlockCasing.class, t -> RenderTypeLookup.setRenderLayer(t, RenderType.cutout()));
        });
    }
}
