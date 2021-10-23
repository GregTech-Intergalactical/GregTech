package muramasa.gti.data;

import muramasa.antimatter.integration.jei.renderer.InfoRenderers;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.recipe.RecipeProxies;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.recipe.map.RecipeMap;
import muramasa.gti.Ref;

import static muramasa.gti.data.Guis.MULTI_DISPLAY;
import static muramasa.gti.data.RecipeBuilders.*;

public class RecipeMaps {

    public static RecipeMap<RecipeBuilder> ORE_BYPRODUCTS = new RecipeMap<>(Ref.ID, "ore_byproducts", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> STEAM_FUELS = new RecipeMap<>(Ref.ID, "steam_fuels", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> GAS_FUELS = new RecipeMap<>(Ref.ID, "gas_fuels", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> COMBUSTION_FUELS = new RecipeMap<>(Ref.ID, "combustion_fuels", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> NAQUADAH_FUELS = new RecipeMap<>(Ref.ID,"naquadah_fuels", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> PLASMA_FUELS = new RecipeMap<>(Ref.ID, "plasma_fuels", new RecipeBuilder());

    public static RecipeMap<RecipeBuilder> COAL_BOILERS = new RecipeMap<>(Ref.ID, "coal_boilers", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> SMALL_BOILERS = new RecipeMap<>(Ref.ID, "small_boilers", new RecipeBuilder());

    public static RecipeMap<RecipeBuilder> ALLOY_SMELTING = new RecipeMap<>(Ref.ID, "alloy_smelting", new AlloySmeltingBuilder());
    public static RecipeMap<RecipeBuilder> ASSEMBLING = new RecipeMap<>(Ref.ID, "assembling", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> AUTOCLAVING = new RecipeMap<>(Ref.ID, "autoclaving", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> BENDING = new RecipeMap<>(Ref.ID, "bending", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> CANNING = new RecipeMap<>(Ref.ID, "canning", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> CENTRIFUGING = new RecipeMap<>(Ref.ID, "centrifuging", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> COMPRESSING = new RecipeMap<>(Ref.ID, "compressing", new CompressingBuilder());
    public static RecipeMap<RecipeBuilder> CUTTING = new RecipeMap<>(Ref.ID, "cutting", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> DISTILLING = new RecipeMap<>(Ref.ID, "distilling", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> SMELTING = new RecipeMap<>(Ref.ID, "smelting", new RecipeBuilder()).setProxy(RecipeProxies.FURNACE_PROXY.apply(8, 60));
    public static RecipeMap<RecipeBuilder> EXTRACTING = new RecipeMap<>(Ref.ID, "extracting", new ExtractingBuilder());
    public static RecipeMap<RecipeBuilder> EXTRUDING = new RecipeMap<>(Ref.ID, "extruding", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> LATHING = new RecipeMap<>(Ref.ID, "lathing", new RecipeBuilder());
    public static RecipeMap<PulverizingBuilder> PULVERIZING = new RecipeMap<>(Ref.ID, "pulverizing", new PulverizingBuilder()).setGuiTier(Tier.IV);
    //For LV machine!
    public static RecipeMap<RecipeBuilder> RECYCLING = new RecipeMap<>(Ref.ID, "recycling", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> SCANNING = new RecipeMap<>(Ref.ID, "scanning", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> WIRE_MILLING = new RecipeMap<>(Ref.ID, "wire_milling", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> ELECTROLYZING = new RecipeMap<>(Ref.ID, "electrolyzing", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> THERMAL_CENTRIFUGING = new RecipeMap<>(Ref.ID, "thermal_centrifuging", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> ORE_WASHING = new RecipeMap<>(Ref.ID, "ore_washing", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> CHEMICAL_REACTING = new RecipeMap<>(Ref.ID, "chemical_reacting", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> FLUID_CANNING = new RecipeMap<>(Ref.ID, "fluid_canning", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> DISASSEMBLING = new RecipeMap<>(Ref.ID, "disassembling", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> MASS_FABRICATING = new RecipeMap<>(Ref.ID, "mass_fabricating", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> AMP_FABRICATING = new RecipeMap<>(Ref.ID, "amp_fabricating", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> REPLICATING = new RecipeMap<>(Ref.ID, "replicating", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> FERMENTING = new RecipeMap<>(Ref.ID, "fermenting", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> FLUID_EXTRACTING = new RecipeMap<>(Ref.ID, "fluid_extracting", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> FLUID_SOLIDIFYING = new RecipeMap<>(Ref.ID, "fluid_solidifying", new RecipeBuilder());  
    public static RecipeMap<RecipeBuilder> CHEMICAL_BATHING = new RecipeMap<>(Ref.ID, "chemical_bathing", new RecipeBuilder());  
    public static RecipeMap<RecipeBuilder> MIXING = new RecipeMap<>(Ref.ID, "mixing", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> LASER_ENGRAVING = new RecipeMap<>(Ref.ID, "laser_engraving", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> PRESSING = new RecipeMap<>(Ref.ID, "pressing", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> HAMMERING = new RecipeMap<>(Ref.ID, "hammering", new HammeringBuilder());
    public static RecipeMap<RecipeBuilder> SIFTING = new RecipeMap<>(Ref.ID, "sifting", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> ARC_SMELTING = new RecipeMap<>(Ref.ID, "arc_smelting", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> PLASMA_ARC_SMELTING = new RecipeMap<>(Ref.ID, "plasma_arc_smelting", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> TUMBLING = new RecipeMap<>(Ref.ID, "tumbling", new RecipeBuilder());

    public static RecipeMap<RecipeBuilder> STEAM_SMELTING = new RecipeMap<>(Ref.ID, "steam_smelting", new RecipeBuilder()).setProxy(RecipeProxies.FURNACE_PROXY.apply(8, 160)).setGuiTier(Tier.BRONZE);
    public static RecipeMap<RecipeBuilder> STEAM_PULVERIZING = new RecipeMap<>(Ref.ID, "steam_pulverizing", new RecipeBuilder()).setGuiTier(Tier.BRONZE);
    public static RecipeMap<RecipeBuilder> STEAM_EXTRACTING = new RecipeMap<>(Ref.ID, "steam_extracting", new RecipeBuilder()).setGuiTier(Tier.BRONZE);
    public static RecipeMap<RecipeBuilder> STEAM_HAMMERING = new RecipeMap<>(Ref.ID, "steam_hammering", new RecipeBuilder()).setGuiTier(Tier.BRONZE);
    public static RecipeMap<RecipeBuilder> STEAM_COMPRESSING = new RecipeMap<>(Ref.ID, "steam_compressing", new RecipeBuilder()).setGuiTier(Tier.BRONZE);
    public static RecipeMap<RecipeBuilder> STEAM_ALLOY_SMELTING = new RecipeMap<>(Ref.ID, "steam_alloy_smelting", new RecipeBuilder()).setGuiTier(Tier.BRONZE);

    public static RecipeMap<RecipeBuilder> COKING = new RecipeMap<>(Ref.ID, "coking", new RecipeBuilder());
    public static RecipeMap<RecipeBuilder> BASIC_BLASTING = new RecipeMap<>(Ref.ID, "basic_blasting", new RecipeBuilder());
    public static RecipeMap<BlastingBuilder> BLASTING = new RecipeMap<>(Ref.ID, "elec_blasting", new BlastingBuilder()).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> IMPLOSION_COMPRESSING = new RecipeMap<>(Ref.ID, "implosion_compressing", new RecipeBuilder()).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> VACUUM_FREEZING = new RecipeMap<>(Ref.ID, "vacuum_freezing", new RecipeBuilder()).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> FUSION = new RecipeMap<>(Ref.ID, "fusion", new RecipeBuilder()).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> DISTILLATION = new RecipeMap<>(Ref.ID, "distillation", new RecipeBuilder()).setGuiData(MULTI_DISPLAY);

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
