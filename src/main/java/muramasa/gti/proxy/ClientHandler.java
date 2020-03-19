package muramasa.gti.proxy;

import muramasa.antimatter.client.AntimatterModelManager;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.gti.Ref;
import muramasa.gti.common.Data;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler {

    public static void init() {
        AntimatterModelManager.addProvider(Ref.ID, g -> new AntimatterBlockStateProvider(Ref.ID, Ref.NAME + " BlockStates", g));
        AntimatterModelManager.addProvider(Ref.ID, g -> new AntimatterItemModelProvider(Ref.ID, Ref.NAME + " Item Models", g));
    }

    @SubscribeEvent
    public static void setup(FMLClientSetupEvent e) {
        RenderTypeLookup.setRenderLayer(Data.RUBBER_SAPLING, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Data.RUBBER_LEAVES, RenderType.getCutout());
    }
}
