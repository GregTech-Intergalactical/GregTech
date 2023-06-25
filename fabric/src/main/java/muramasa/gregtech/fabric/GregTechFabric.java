package muramasa.gregtech.fabric;

import muramasa.antimatter.Ref;
import muramasa.antimatter.event.fabric.CraftingEvents;
import muramasa.antimatter.event.fabric.LoaderEvents;
import muramasa.antimatter.event.fabric.ProviderEvents;
import muramasa.antimatter.event.fabric.WorldGenEvents;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.GregTechConfig;
import muramasa.gregtech.loader.WorldGenLoader;
import net.fabricmc.api.ModInitializer;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.api.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.config.ModConfig;

public class GregTechFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ModConfigEvent.LOADING.register(GregTechConfig::onModConfigEvent);
        ModConfigEvent.RELOADING.register(GregTechConfig::onModConfigEvent);
        ModLoadingContext.registerConfig(GTIRef.ID, ModConfig.Type.COMMON, GregTechConfig.COMMON_SPEC);
        WorldGenEvents.WORLD_GEN.register(WorldGenLoader::init);
        LoaderEvents.LOADER.register(GregTech::registerRecipeLoaders);
        CraftingEvents.CRAFTING.register(GregTech::registerCraftingLoaders);
        ProviderEvents.PROVIDERS.register(GregTech::onProviders);
    }
}
