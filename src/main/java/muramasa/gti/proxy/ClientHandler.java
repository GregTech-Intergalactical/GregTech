package muramasa.gti.proxy;

import muramasa.gti.common.Data;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler {

    public static void init() {

    }

    @SubscribeEvent
    public static void setup(FMLClientSetupEvent e) {
        RenderTypeLookup.setRenderLayer(Data.RUBBER_SAPLING, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(Data.RUBBER_LEAVES, RenderType.cutout());
    }
}
