package muramasa.gregtech.forge;

import muramasa.antimatter.event.forge.AntimatterCraftingEvent;
import muramasa.antimatter.event.forge.AntimatterLoaderEvent;
import muramasa.antimatter.event.forge.AntimatterProvidersEvent;
import muramasa.antimatter.event.forge.AntimatterWorldGenEvent;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.GregTechPostRegistrar;
import muramasa.gregtech.Ref;
import muramasa.gregtech.block.tree.RubberFoliagePlacer;
import muramasa.gregtech.events.forge.RemappingEvents;
import muramasa.gregtech.loader.WorldGenLoader;
import muramasa.gregtech.proxy.ClientHandler;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Ref.ID)
public class GregTechForge {

    public GregTechForge(){
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(GregTechForge::onRegisterFoilagePlacers);
        MinecraftForge.EVENT_BUS.register(RemappingEvents.class);
        MinecraftForge.EVENT_BUS.addListener(GregTechForge::registerRecipeLoaders);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(GregTechForge::registerCraftingLoaders);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(GregTechForge::onProviders);
        MinecraftForge.EVENT_BUS.addListener(GregTechForge::onWorldGen);
        new GregTechPostRegistrar();
        new GregTech();
    }

    private static void onRegisterFoilagePlacers(final RegistryEvent.Register<FoliagePlacerType<?>> e){
        e.getRegistry().register(RubberFoliagePlacer.RUBBER.setRegistryName(Ref.ID, "rubber_foilage_placer"));
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
