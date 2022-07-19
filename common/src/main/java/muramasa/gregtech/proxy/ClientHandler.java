package muramasa.gregtech.proxy;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.client.ModelUtils;
import muramasa.gregtech.block.BlockCasing;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.client.renderer.RenderType;

public class ClientHandler {

    public static void setup() {
        ModelUtils.setRenderLayer(GregTechData.RUBBER_SAPLING, RenderType.cutout());
        ModelUtils.setRenderLayer(GregTechData.RUBBER_LEAVES, RenderType.cutout());
        AntimatterAPI.all(BlockCasing.class, t -> ModelUtils.setRenderLayer(t, RenderType.cutout()));
    }
}
