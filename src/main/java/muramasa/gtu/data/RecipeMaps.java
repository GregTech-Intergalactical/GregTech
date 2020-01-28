package muramasa.gtu.data;

import muramasa.antimatter.recipe.RecipeBuilder;
import muramasa.antimatter.recipe.RecipeMap;

import static muramasa.gtu.data.RecipeBuilders.*;

public class RecipeMaps {

    public static RecipeMap ORE_BYPRODUCTS = new RecipeMap<>("ore_byproducts", new RecipeBuilder());
    public static RecipeMap STEAM_FUELS = new RecipeMap<>("steam_fuels", new RecipeBuilder());
    public static RecipeMap GAS_FUELS = new RecipeMap<>("gas_fuels", new RecipeBuilder());
    public static RecipeMap COMBUSTION_FUELS = new RecipeMap<>("combustion_fuels", new RecipeBuilder());
    public static RecipeMap NAQUADAH_FUELS = new RecipeMap<>("naquadah_fuels", new RecipeBuilder());
    public static RecipeMap PLASMA_FUELS = new RecipeMap<>("plasma_fuels", new RecipeBuilder());

    public static RecipeMap SMALL_BOILERS = new RecipeMap<>("small_boilers", new RecipeBuilder());

    public static RecipeMap ALLOY_SMELTING = new RecipeMap<>("alloy_smelting", new AlloySmeltingBuilder());
    public static RecipeMap ASSEMBLING = new RecipeMap<>("assembling", new RecipeBuilder());
    public static RecipeMap AUTOCLAVING = new RecipeMap<>("autoclaving", new RecipeBuilder());
    public static RecipeMap BENDING = new RecipeMap<>("bending", new RecipeBuilder());
    public static RecipeMap CANNING = new RecipeMap<>("canning", new RecipeBuilder());
    public static RecipeMap CENTRIFUGING = new RecipeMap<>("centrifuging", new RecipeBuilder());
    public static RecipeMap COMPRESSING = new RecipeMap<>("compressing", new CompressingBuilder());
    public static RecipeMap CUTTING = new RecipeMap<>("cutting", new RecipeBuilder());
    public static RecipeMap DISTILLING = new RecipeMap<>("distilling", new RecipeBuilder());
    public static RecipeMap SMELTING = new RecipeMap<>("smelting", new SmeltingBuilder());
    public static RecipeMap EXTRACTING = new RecipeMap<>("extracting", new ExtractingBuilder());
    public static RecipeMap EXTRUDING = new RecipeMap<>("extruding", new RecipeBuilder());
    public static RecipeMap LATHING = new RecipeMap<>("lathing", new RecipeBuilder());
    public static RecipeMap PULVERIZING = new RecipeMap<>("pulverizing", new PulverizingBuilder());
    public static RecipeMap RECYCLING = new RecipeMap<>("recycling", new RecipeBuilder());
    public static RecipeMap SCANNING = new RecipeMap<>("scanning", new RecipeBuilder());
    public static RecipeMap WIRE_MILLING = new RecipeMap<>("wire_milling", new RecipeBuilder());
    public static RecipeMap ELECTROLYZING = new RecipeMap<>("electrolyzing", new RecipeBuilder());
    public static RecipeMap THERMAL_CENTRIFUGING = new RecipeMap<>("thermal_centrifuging", new RecipeBuilder());
    public static RecipeMap ORE_WASHING = new RecipeMap<>("ore_washing", new RecipeBuilder());
    public static RecipeMap CHEMICAL_REACTING = new RecipeMap<>("chemical_reacting", new RecipeBuilder());
    public static RecipeMap FLUID_CANNING = new RecipeMap<>("fluid_canning", new RecipeBuilder());
    public static RecipeMap DISASSEMBLING = new RecipeMap<>("disassembling", new RecipeBuilder());
    public static RecipeMap MASS_FABRICATING = new RecipeMap<>("mass_fabricating", new RecipeBuilder());
    public static RecipeMap AMP_FABRICATING = new RecipeMap<>("amp_fabricating", new RecipeBuilder());
    public static RecipeMap REPLICATING = new RecipeMap<>("replicating", new RecipeBuilder());
    public static RecipeMap FERMENTING = new RecipeMap<>("fermenting", new RecipeBuilder());
    public static RecipeMap FLUID_EXTRACTING = new RecipeMap<>("fluid_extracting", new RecipeBuilder());
    public static RecipeMap FLUID_SOLIDIFYING = new RecipeMap<>("fluid_solidifying", new RecipeBuilder());  
    public static RecipeMap CHEMICAL_BATHING = new RecipeMap<>("chemical_bathing", new RecipeBuilder());  
    public static RecipeMap MIXING = new RecipeMap<>("mixing", new RecipeBuilder());
    public static RecipeMap LASER_ENGRAVING = new RecipeMap<>("laser_engraving", new RecipeBuilder());
    public static RecipeMap PRESSING = new RecipeMap<>("pressing", new RecipeBuilder());
    public static RecipeMap HAMMERING = new RecipeMap<>("hammering", new HammeringBuilder());
    public static RecipeMap SIFTING = new RecipeMap<>("sifting", new RecipeBuilder());
    public static RecipeMap ARC_SMELTING = new RecipeMap<>("arc_smelting", new RecipeBuilder());
    public static RecipeMap PLASMA_ARC_SMELTING = new RecipeMap<>("plasma_arc_smelting", new RecipeBuilder());
    public static RecipeMap TUMBLING = new RecipeMap<>("tumbling", new RecipeBuilder());

    public static RecipeMap STEAM_SMELTING = new RecipeMap<>("steam_smelting", new RecipeBuilder());
    public static RecipeMap STEAM_PULVERIZING = new RecipeMap<>("steam_pulverizing", new RecipeBuilder());
    public static RecipeMap STEAM_EXTRACTING = new RecipeMap<>("steam_extracting", new RecipeBuilder());
    public static RecipeMap STEAM_HAMMERING = new RecipeMap<>("steam_hammering", new RecipeBuilder());
    public static RecipeMap STEAM_COMPRESSING = new RecipeMap<>("steam_compressing", new RecipeBuilder());
    public static RecipeMap STEAM_ALLOY_SMELTING = new RecipeMap<>("steam_alloy_smelting", new RecipeBuilder());

    public static RecipeMap COKING = new RecipeMap<>("coking", new RecipeBuilder());
    public static RecipeMap<BasicBlastingBuilder> BASIC_BLASTING = new RecipeMap<>("basic_blasting", new BasicBlastingBuilder());
    public static RecipeMap BLASTING = new RecipeMap<>("elec_blasting", new RecipeBuilder());
    public static RecipeMap IMPLOSION_COMPRESSING = new RecipeMap<>("implosion_compressing", new RecipeBuilder());
    public static RecipeMap VACUUM_FREEZING = new RecipeMap<>("vacuum_freezing", new RecipeBuilder());
    public static RecipeMap FUSION = new RecipeMap<>("fusion", new RecipeBuilder());
}
