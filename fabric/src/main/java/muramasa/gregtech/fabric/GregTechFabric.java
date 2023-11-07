package muramasa.gregtech.fabric;

import muramasa.antimatter.event.fabric.CraftingEvents;
import muramasa.antimatter.event.fabric.LoaderEvents;
import muramasa.antimatter.event.fabric.ProviderEvents;
import muramasa.antimatter.event.fabric.WorldGenEvents;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.loader.WorldGenLoader;
import net.fabricmc.api.ModInitializer;

public class GregTechFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        WorldGenEvents.WORLD_GEN.register(WorldGenLoader::init);
        LoaderEvents.LOADER.register(GregTech::registerRecipeLoaders);
        CraftingEvents.CRAFTING.register(GregTech::registerCraftingLoaders);
        ProviderEvents.PROVIDERS.register(GregTech::onProviders);
    }
}
