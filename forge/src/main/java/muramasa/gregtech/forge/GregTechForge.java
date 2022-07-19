package muramasa.gregtech.forge;

import muramasa.antimatter.event.forge.AntimatterCraftingEvent;
import muramasa.antimatter.event.forge.AntimatterLoaderEvent;
import muramasa.antimatter.event.forge.AntimatterProvidersEvent;
import muramasa.antimatter.event.forge.AntimatterWorldGenEvent;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.Ref;
import muramasa.gregtech.events.forge.RemappingEvents;
import muramasa.gregtech.loader.WorldGenLoader;
import muramasa.gregtech.proxy.ClientHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Ref.ID)
public class GregTechForge {

    public GregTechForge(){
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(RemappingEvents.class);
        MinecraftForge.EVENT_BUS.addListener(GregTechForge::registerRecipeLoaders);
        MinecraftForge.EVENT_BUS.addListener(GregTechForge::registerCraftingLoaders);
        MinecraftForge.EVENT_BUS.addListener(GregTechForge::onProviders);
        MinecraftForge.EVENT_BUS.addListener(GregTechForge::onWorldGen);
    }

    private static void onProviders(AntimatterProvidersEvent ev) {
        GregTech.onProviders(ev.getEvent());
    }

    private static void registerCraftingLoaders(AntimatterCraftingEvent event) {
        GregTech.registerCraftingLoaders(event.getEvent());
    }

    private static void registerRecipeLoaders(AntimatterLoaderEvent event) {
        GregTech.registerRecipeLoaders(event.sender, event.registrat);
    }

    private static void onWorldGen(AntimatterWorldGenEvent event){
        WorldGenLoader.init(event.getEvent());
    }

    private void clientSetup(final FMLClientSetupEvent e) {
        e.enqueueWork(ClientHandler::setup);
    }

    private void setup(final FMLCommonSetupEvent e) {
    }
}
