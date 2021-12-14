package muramasa.gregtech.proxy;

import muramasa.antimatter.AntimatterAPI;
import muramasa.gregtech.block.BlockCasing;
import muramasa.gregtech.data.GregTechData;
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
