package muramasa.gti.proxy;

import muramasa.antimatter.AntimatterAPI;
import muramasa.gti.block.BlockCasing;
import muramasa.gti.data.GregTechData;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {

    public static void setup(FMLClientSetupEvent e) {
        e.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(GregTechData.RUBBER_SAPLING, RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(GregTechData.RUBBER_LEAVES, RenderType.cutout());
            AntimatterAPI.all(BlockCasing.class, t -> ItemBlockRenderTypes.setRenderLayer(t, RenderType.cutout()));
        });
    }
}
