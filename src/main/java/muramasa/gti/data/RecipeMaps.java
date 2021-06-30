package muramasa.gti.data;

import muramasa.antimatter.integration.jei.renderer.InfoRenderers;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.recipe.RecipeProxies;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.recipe.map.RecipeMap;

import static muramasa.gti.data.Guis.MULTI_DISPLAY;
import static muramasa.gti.data.RecipeBuilders.*;

public class RecipeMaps {

    public static RecipeMap<RecipeBuilder> ORE_BYPRODUCTS = new RecipeMap<>("ore_byproducts", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> STEAM_FUELS = new RecipeMap<>("steam_fuels", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> GAS_FUELS = new RecipeMap<>("gas_fuels", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> COMBUSTION_FUELS = new RecipeMap<>("combustion_fuels", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> NAQUADAH_FUELS = new RecipeMap<>("naquadah_fuels", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> PLASMA_FUELS = new RecipeMap<>("plasma_fuels", new RecipeBuilder());

    public static RecipeMap<RecipeBuilder> COAL_BOILERS = new RecipeMap<>("coal_boilers", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> SMALL_BOILERS = new RecipeMap<>("small_boilers", new RecipeBuilder());

    public static RecipeMap<RecipeBuilder> ALLOY_SMELTING = new RecipeMap<>("alloy_smelting", new AlloySmeltingBuilder());
    public static RecipeMap<RecipeBuilder> ASSEMBLING = new RecipeMap<>("assembling", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> AUTOCLAVING = new RecipeMap<>("autoclaving", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> BENDING = new RecipeMap<>("bending", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> CANNING = new RecipeMap<>("canning", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> CENTRIFUGING = new RecipeMap<>("centrifuging", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> COMPRESSING = new RecipeMap<>("compressing", new CompressingBuilder());
    public static RecipeMap<RecipeBuilder> CUTTING = new RecipeMap<>("cutting", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> DISTILLING = new RecipeMap<>("distilling", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> SMELTING = new RecipeMap<>("smelting", new RecipeBuilder()).setProxy(RecipeProxies.FURNACE_PROXY.apply(8, 60));
    public static RecipeMap<RecipeBuilder> EXTRACTING = new RecipeMap<>("extracting", new ExtractingBuilder());
    public static RecipeMap<RecipeBuilder> EXTRUDING = new RecipeMap<>("extruding", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> LATHING = new RecipeMap<>("lathing", new RecipeBuilder());
    public static RecipeMap<PulverizingBuilder> PULVERIZING = new RecipeMap<>("pulverizing", new PulverizingBuilder()).setGuiTier(Tier.IV);
    //For LV machine!
    public static RecipeMap<RecipeBuilder> RECYCLING = new RecipeMap<>("recycling", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> SCANNING = new RecipeMap<>("scanning", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> WIRE_MILLING = new RecipeMap<>("wire_milling", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> ELECTROLYZING = new RecipeMap<>("electrolyzing", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> THERMAL_CENTRIFUGING = new RecipeMap<>("thermal_centrifuging", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> ORE_WASHING = new RecipeMap<>("ore_washing", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> CHEMICAL_REACTING = new RecipeMap<>("chemical_reacting", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> FLUID_CANNING = new RecipeMap<>("fluid_canning", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> DISASSEMBLING = new RecipeMap<>("disassembling", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> MASS_FABRICATING = new RecipeMap<>("mass_fabricating", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> AMP_FABRICATING = new RecipeMap<>("amp_fabricating", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> REPLICATING = new RecipeMap<>("replicating", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> FERMENTING = new RecipeMap<>("fermenting", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> FLUID_EXTRACTING = new RecipeMap<>("fluid_extracting", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> FLUID_SOLIDIFYING = new RecipeMap<>("fluid_solidifying", new RecipeBuilder());  
    public static RecipeMap<RecipeBuilder> CHEMICAL_BATHING = new RecipeMap<>("chemical_bathing", new RecipeBuilder());  
    public static RecipeMap<RecipeBuilder> MIXING = new RecipeMap<>("mixing", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> LASER_ENGRAVING = new RecipeMap<>("laser_engraving", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> PRESSING = new RecipeMap<>("pressing", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> HAMMERING = new RecipeMap<>("hammering", new HammeringBuilder());
    public static RecipeMap<RecipeBuilder> SIFTING = new RecipeMap<>("sifting", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> ARC_SMELTING = new RecipeMap<>("arc_smelting", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> PLASMA_ARC_SMELTING = new RecipeMap<>("plasma_arc_smelting", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> TUMBLING = new RecipeMap<>("tumbling", new RecipeBuilder());

    public static RecipeMap<RecipeBuilder> STEAM_SMELTING = new RecipeMap<>("steam_smelting", new RecipeBuilder()).setProxy(RecipeProxies.FURNACE_PROXY.apply(8, 160)).setGuiTier(Tier.BRONZE);
    public static RecipeMap<RecipeBuilder> STEAM_PULVERIZING = new RecipeMap<>("steam_pulverizing", new RecipeBuilder()).setGuiTier(Tier.BRONZE);
    public static RecipeMap<RecipeBuilder> STEAM_EXTRACTING = new RecipeMap<>("steam_extracting", new RecipeBuilder()).setGuiTier(Tier.BRONZE);
    public static RecipeMap<RecipeBuilder> STEAM_HAMMERING = new RecipeMap<>("steam_hammering", new RecipeBuilder()).setGuiTier(Tier.BRONZE);
    public static RecipeMap<RecipeBuilder> STEAM_COMPRESSING = new RecipeMap<>("steam_compressing", new RecipeBuilder()).setGuiTier(Tier.BRONZE);
    public static RecipeMap<RecipeBuilder> STEAM_ALLOY_SMELTING = new RecipeMap<>("steam_alloy_smelting", new RecipeBuilder()).setGuiTier(Tier.BRONZE);

    public static RecipeMap<RecipeBuilder> COKING = new RecipeMap<>("coking", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> BASIC_BLASTING = new RecipeMap<>("basic_blasting", new RecipeBuilder());
    public static RecipeMap<BlastingBuilder> BLASTING = new RecipeMap<>("elec_blasting", new BlastingBuilder()).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> IMPLOSION_COMPRESSING = new RecipeMap<>("implosion_compressing", new RecipeBuilder()).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> VACUUM_FREEZING = new RecipeMap<>("vacuum_freezing", new RecipeBuilder()).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> FUSION = new RecipeMap<>("fusion", new RecipeBuilder()).setGuiData(MULTI_DISPLAY);

    static {
        ORE_BYPRODUCTS.setGuiData(Guis.ORE_BYPRODUCTS);
        //SMELTING.setGuiData(Guis.MULTI_DISPLAY_COMPACT);
        STEAM_FUELS.setGuiData(Guis.MULTI_DISPLAY);
        GAS_FUELS.setGuiData(Guis.MULTI_DISPLAY);
        COMBUSTION_FUELS.setGuiData(Guis.MULTI_DISPLAY);
        NAQUADAH_FUELS.setGuiData(Guis.MULTI_DISPLAY);
        PLASMA_FUELS.setGuiData(Guis.MULTI_DISPLAY);
    }

    public static void clientMaps() {
        ORE_BYPRODUCTS.setInfoRenderer(InfoRenderers.EMPTY_RENDERER);
        STEAM_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        GAS_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        COMBUSTION_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        NAQUADAH_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        PLASMA_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);

        BLASTING.setInfoRenderer(InfoRenderers.BLASTING_RENDERER);
        STEAM_SMELTING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_COMPRESSING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_ALLOY_SMELTING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_EXTRACTING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_PULVERIZING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_HAMMERING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
    }
}
