package muramasa.gregtech.proxy;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.client.ModelUtils;
import muramasa.gregtech.block.BlockCasing;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.client.renderer.RenderType;

public class ClientHandler {

    public static void setup() {
        AntimatterAPI.all(BlockCasing.class, t -> ModelUtils.setRenderLayer(t, RenderType.cutout()));
    }
}
