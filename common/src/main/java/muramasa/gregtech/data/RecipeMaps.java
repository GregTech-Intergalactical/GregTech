package muramasa.gregtech.data;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.integration.jeirei.renderer.IRecipeInfoRenderer;
import muramasa.antimatter.integration.jeirei.renderer.InfoRenderers;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.RecipeProxies;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.recipe.map.RecipeMap;
import muramasa.gregtech.GTIRef;
import net.minecraft.client.gui.Font;

import static muramasa.gregtech.data.Guis.MULTI_DISPLAY;
import static muramasa.gregtech.data.Guis.MULTI_DISPLAY_FLUID;
import static muramasa.gregtech.data.RecipeBuilders.*;

@SuppressWarnings("unchecked")
public class RecipeMaps {
    public static RecipeMap<RecipeBuilder> STEAM_ALLOY_SMELTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_alloy_smelting", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_COMPRESSING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_compressing", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_EXTRACTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_extracting", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> HP_STEAM_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "hp_steam_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> STEAM_HAMMERING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_hammering", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_MACERATING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_macerating", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_SIFTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_sifting", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_SMELTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_smelting", new RecipeBuilder())
                    .setProxy(RecipeProxies.FURNACE_PROXY.apply(8, 160)).setGuiTier(Tier.BRONZE));

    public static RecipeMap<RecipeBuilder> ALLOY_SMELTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "alloy_smelting", new SteamBuilder(STEAM_ALLOY_SMELTING)));
    public static RecipeMap<RecipeBuilder> AMP_FABRICATING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "amp_fabricating", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> ARC_SMELTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "arc_smelting", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> ASSEMBLING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "assembling", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> AUTOCLAVING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "autoclaving", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> BASIC_BLASTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "basic_blasting", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> BENDING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "bending", new RecipeBuilder()));
    public static RecipeMap<BlastingBuilder> BLASTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "elec_blasting", new BlastingBuilder())).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> CANNING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "canning", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> CENTRIFUGING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "centrifuging", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> CHEMICAL_BATHING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "chemical_bathing", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> CHEMICAL_REACTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "chemical_reacting", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> COAL_BOILERS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "coal_boilers", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> COKING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "coking", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> COMBUSTION_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "combustion_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> COMPRESSING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "compressing", new SteamBuilder(STEAM_COMPRESSING)));
    public static RecipeMap<RecipeBuilder> CUTTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "cutting", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> CRACKING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "cracking", new RecipeBuilder())).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> COMPLEX_ASSEMBLING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "complex_assembling", new RecipeBuilder())).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> DECAYING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "decaying", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> DEHYDRATING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "dehydrating", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> DISASSEMBLING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "disassembling", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> DISTILLATION = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "distillation", new RecipeBuilder())).setGuiData(MULTI_DISPLAY_FLUID);
    public static RecipeMap<RecipeBuilder> CRYO_DISTILLATION = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "cryo_distillation", new RecipeBuilder())).setGuiData(MULTI_DISPLAY_FLUID);
    public static RecipeMap<RecipeBuilder> DISTILLING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "distilling", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> ELECTROLYZING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "electrolyzing", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> ELECTROMAGNETIC_SEPARATING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "electromagnetic_separating", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> EXTRACTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "extracting", new SteamBuilder(STEAM_EXTRACTING)));
    public static RecipeMap<RecipeBuilder> EXTRUDING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "extruding", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FERMENTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "fermenting", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FLUID_CANNING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "fluid_canning", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FLUID_EXTRACTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "fluid_extracting", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FLUID_HEATING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "fluid_heating", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FLUID_SOLIDIFYING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "fluid_solidifying", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FUSION = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "fusion", new RecipeBuilder())).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> GAS_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "gas_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> HAMMERING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "hammering", new SteamBuilder(STEAM_HAMMERING)));
    public static RecipeMap<RecipeBuilder> HEAT_EXCHANGING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "heat_exchanging", new RecipeBuilder())).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> IMPLOSION_COMPRESSING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "implosion_compressing", new RecipeBuilder())).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> LARGE_BOILERS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "large_boilers", new RecipeBuilder())).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> LASER_ENGRAVING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "laser_engraving", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> LATHING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "lathing", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> MACERATING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "macerating", new SteamBuilder(STEAM_MACERATING)).setGuiTier(Tier.EV));
    public static RecipeMap<RecipeBuilder> MASS_FABRICATING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "mass_fabricating", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> MIXING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "mixing", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> NAQUADAH_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "naquadah_gfuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> NUCLEAR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "nuclear", new RecipeBuilder()).setGuiData(MULTI_DISPLAY));
    public static RecipeMap<RecipeBuilder> ORE_BYPRODUCTS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "ore_byproducts", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> ORE_WASHING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "ore_washing", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> PACKAGING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "packaging", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> POLARIZING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "polarizing", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> PLASMA_ARC_SMELTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "plasma_arc_smelting", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> PLASMA_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "plasma_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> PRESSING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "pressing", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> PYROLYSISING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "pyrolysising", new RecipeBuilder())).setGuiData(MULTI_DISPLAY);

    public static RecipeMap<RecipeBuilder> ROASTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "roasting", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> RECYCLING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "recycling", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> REPLICATING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "replicating", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> SCANNING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "scanning", new RecipeBuilder()));

    public static RecipeMap<RecipeBuilder> SEMI_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "semi_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> SIFTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "sifting", new SteamBuilder(STEAM_SIFTING)));
    public static RecipeMap<RecipeBuilder> SMALL_BOILERS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "small_boilers", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> SMELTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "smelting", new RecipeBuilder())
                    .setProxy(RecipeProxies.FURNACE_PROXY.apply(8, 60)));
    public static RecipeMap<RecipeBuilder> THERMAL_CENTRIFUGING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "thermal_centrifuging", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> VACUUM_FREEZING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "vacuum_freezing", new RecipeBuilder()).setGuiData(MULTI_DISPLAY));
    public static RecipeMap<RecipeBuilder> WIRE_MILLING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "wire_milling", new RecipeBuilder()));

    public static final IRecipeInfoRenderer LARGE_BOILER_RENDERER = new IRecipeInfoRenderer() {
        @Override
        public void render(PoseStack stack, IRecipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            String duration = "Duration: " + recipe.getDuration() + " ticks (" + (recipe.getDuration() / 20.0f) + " s)";
            String extraBurntime = "Extra saved Burntime: " + recipe.getPower();
            String heatIncreaseMultiplier = "Heat increase multiplier: " + (Math.max(recipe.getSpecialValue(), 1));
            renderString(stack, duration, fontRenderer, 5, 0, guiOffsetX, guiOffsetY);
            renderString(stack, extraBurntime, fontRenderer, 5, 10, guiOffsetX, guiOffsetY);
            renderString(stack, heatIncreaseMultiplier, fontRenderer, 5, 20, guiOffsetX, guiOffsetY);
        }

        @Override
        public int getRows() {
            return 3;
        }
    };

    static {
        COMBUSTION_FUELS.setGuiData(Guis.MULTI_DISPLAY);
        GAS_FUELS.setGuiData(Guis.MULTI_DISPLAY);
        SEMI_FUELS.setGuiData(MULTI_DISPLAY);
        NAQUADAH_FUELS.setGuiData(Guis.MULTI_DISPLAY);
        ORE_BYPRODUCTS.setGuiData(Guis.ORE_BYPRODUCTS);
        PLASMA_FUELS.setGuiData(Guis.MULTI_DISPLAY);
        // SMELTING.setGuiData(Guis.MULTI_DISPLAY_COMPACT);
        STEAM_FUELS.setGuiData(Guis.MULTI_DISPLAY);
        HP_STEAM_FUELS.setGuiData(MULTI_DISPLAY);
    }

    public static void clientMaps() {
        BLASTING.setInfoRenderer(InfoRenderers.BLASTING_RENDERER);
        BASIC_BLASTING.setInfoRenderer(InfoRenderers.BASIC_RENDERER);
        COKING.setInfoRenderer(InfoRenderers.BASIC_RENDERER);
        COMBUSTION_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        GAS_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        NAQUADAH_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        SEMI_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        ORE_BYPRODUCTS.setInfoRenderer(InfoRenderers.EMPTY_RENDERER);
        PLASMA_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        STEAM_ALLOY_SMELTING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_COMPRESSING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_EXTRACTING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        HP_STEAM_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        STEAM_HAMMERING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_MACERATING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_SMELTING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_SIFTING.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        LARGE_BOILERS.setInfoRenderer(LARGE_BOILER_RENDERER);
    }
}
