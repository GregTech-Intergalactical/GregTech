package muramasa.gregtech.data;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.gregtechintergalactical.gtcore.data.RecipeBuilders;
import io.github.gregtechintergalactical.gtcore.data.RecipeBuilders.SteamBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.integration.jeirei.renderer.IRecipeInfoRenderer;
import muramasa.antimatter.integration.jeirei.renderer.InfoRenderers;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.RecipeProxies;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.Proxy;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.recipe.map.RecipeMap;
import muramasa.gregtech.GTIRef;
import net.minecraft.client.gui.Font;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;

import java.util.List;
import java.util.function.BiFunction;

import static muramasa.gregtech.data.Guis.*;

@SuppressWarnings("unchecked")
public class RecipeMaps {

    public static BiFunction<Integer, Integer, Proxy> DISSASSEMBLER_PROXY = (power, duration) -> new Proxy(RecipeType.CRAFTING, getDefaultCrafting(power, duration));
    public static RecipeMap<RecipeBuilder> STEAM_ALLOY_SMELTER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_alloy_smelter", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_COMPRESSOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_compressor", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_EXTRACTOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_extractor", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> HP_STEAM_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "hp_steam_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> STEAM_FORGE_HAMMER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_forge_hammer", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_MACERATOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_macerator", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_SIFTER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_sifter", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_FURNACE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_furnace", new RecipeBuilder())
                    .setProxy(RecipeProxies.FURNACE_PROXY.apply(8, 160)).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_OVEN = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "steam_oven", new RecipeBuilder())
                    .setProxy(RecipeProxies.SMOKING_PROXY.apply(8, 80)).setGuiTier(Tier.BRONZE));

    public static RecipeMap<RecipeBuilder> ALLOY_SMELTER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "alloy_smelter", new SteamBuilder(STEAM_ALLOY_SMELTER)));
    public static RecipeMap<RecipeBuilder> AMP_FABRICATOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "amp_fabricator", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> ARC_FURNACE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "arc_furnace", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> ASSEMBLER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "assembler", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> AUTOCLAVE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "autoclave", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> PRIMITIVE_BLAST_FURNACE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "primitive_blast_furnace", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> BENDER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "bender", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> CANNER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "canner", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> CENTRIFUGE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "centrifuge", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> BATH = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "bath", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> CHEMICAL_REACTOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "chemical_reactor", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> CIRCUIT_ASSEMBLER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "circuit_assembler", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> COAL_BOILERS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "coal_boilers", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> COKE_OVEN = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "coke_oven", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> COMBUSTION_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "combustion_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> COMPRESSOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "compressor", new SteamBuilder(STEAM_COMPRESSOR)));
    public static RecipeMap<RecipeBuilder> CUTTER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "cutter", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> CRACKING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "cracking", new RecipeBuilder())).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> ASSEMBLY_LINE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "assembly_line", new RecipeBuilder())).setGuiData(MULTI_DISPLAY_FLUID);
    public static RecipeMap<RecipeBuilder> DEHYDRATOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "dehydrator", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> DISASSEMBLER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "disassembler", new RecipeBuilder())).setProxy(DISSASSEMBLER_PROXY.apply(8, 200));
    public static RecipeMap<RecipeBuilder> DISTILLATION = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "distillation", new RecipeBuilder())).setGuiData(MULTI_DISPLAY_FLUID);
    public static RecipeMap<RecipeBuilder> CRYO_DISTILLATION = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "cryo_distillation", new RecipeBuilder())).setGuiData(MULTI_DISPLAY_FLUID);
    public static RecipeMap<RecipeBuilder> DISTILLERY = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "distillery", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilders.BlastingBuilder> E_BLAST_FURNACE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "electric_blast_furnace", new RecipeBuilders.BlastingBuilder())).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> ELECTRIC_FURNACE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "electric_furnace", new RecipeBuilder())
                    .setProxy(RecipeProxies.FURNACE_PROXY.apply(8, 60)));
    public static RecipeMap<RecipeBuilder> ELECTRIC_OVEN = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "electric_oven", new RecipeBuilder())
                    .setProxy(RecipeProxies.SMOKING_PROXY.apply(8, 30)));
    public static RecipeMap<RecipeBuilder> ELECTROLYZER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "electrolyzer", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> ELECTROMAGNETIC_SEPARATOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "electromagnetic_separator", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> EXTRACTOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "extractor", new SteamBuilder(STEAM_EXTRACTOR)));
    public static RecipeMap<RecipeBuilder> EXTRUDER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "extruder", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FERMENTER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "fermenter", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FLUID_CANNER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "fluid_canner", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FLUID_PRESS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "fluid_press", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FLUID_HEATER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "fluid_heater", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FLUID_SOLIDIFYER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "fluid_solidifyer", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FUSION = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "fusion", new RecipeBuilder())).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> GAS_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "gas_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FORGE_HAMMER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "forge_hammer", new SteamBuilder(STEAM_FORGE_HAMMER)));
    public static RecipeMap<RecipeBuilder> HEAT_EXCHANGER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "heat_exchanger", new RecipeBuilder())).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> IMPLOSION_COMPRESSOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "implosion_compressor", new RecipeBuilder())).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> LARGE_BOILERS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "large_boilers", new RecipeBuilder())).setGuiData(MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> LASER_ENGRAVER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "laser_engraver", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> LATHE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "lathe", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> MACERATOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "macerator", new SteamBuilder(STEAM_MACERATOR)).setGuiTier(Tier.EV));
    public static RecipeMap<RecipeBuilder> MASS_FABRICATOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "mass_fabricator", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> MIXER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "mixer", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> NAQUADAH_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "naquadah_gfuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> NUCLEAR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "nuclear", new RecipeBuilder()).setGuiData(MULTI_DISPLAY));
    public static RecipeMap<RecipeBuilder> ORE_BYPRODUCTS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "ore_byproducts", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> ORE_WASHER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "ore_washer", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> PACKAGER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "packager", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> POLARIZER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "polarizer", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> PLASMA_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "plasma_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FORMING_PRESS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "forming_press", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> PRINTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "printing", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> PYROLYSIS_OVEN = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "pyrolysis_oven", new RecipeBuilder())).setGuiData(MULTI_DISPLAY);

    public static RecipeMap<RecipeBuilder> ROASTER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "roaster", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> RECYCLER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "recycler", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> REPLICATOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "replicating", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> SCANNER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "scanning", new RecipeBuilder()));

    public static RecipeMap<RecipeBuilder> SEMI_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "semi_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> SIFTER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "sifter", new SteamBuilder(STEAM_SIFTER)));
    public static RecipeMap<RecipeBuilder> SMELTER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "smelter", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> SMALL_BOILERS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "small_boilers", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> THERMAL_CENTRIFUGE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "thermal_centrifuge", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> VACUUM_FREEZER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "vacuum_freezer", new RecipeBuilder()).setGuiData(MULTI_DISPLAY));
    public static RecipeMap<RecipeBuilder> WIRE_MILL = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GTIRef.ID, "wire_mill", new RecipeBuilder()));

    private static BiFunction<Recipe<?>, RecipeBuilder, IRecipe> getDefaultCrafting(int power, int duration) {
        return (t, b) -> {
            if (!(t instanceof ShapedRecipe shapedRecipe)) return null;
            List<Ingredient> ingredients = t.getIngredients();
            if (!(t.getResultItem().getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof BlockMachine)) return null;
            List<ItemStack> list = new ObjectArrayList<>();
            for (Ingredient i : ingredients){
                for (ItemStack stack : i.getItems()){
                    if (!stack.isEmpty() && !stack.isDamageableItem()){
                        list.add(stack);
                        break;
                    }
                }
            }
            ItemStack craftingOut = shapedRecipe.getResultItem();
            if (list.isEmpty()) return null;
            RecipeIngredient ing = RecipeIngredient.of(craftingOut);
            IRecipe recipe = b.recipeMapOnly().ii(ing)
                    .io(list.toArray(new ItemStack[0])).hide().add(t.getId().getPath(), duration, power, 0, 1);
            recipe.setMapId(b.getMap().getId());
            return recipe;
        };
    }

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

    public static final IRecipeInfoRenderer HEAT_EXCHANGER_RENDERER = new IRecipeInfoRenderer() {
        @Override
        public void render(PoseStack stack, IRecipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            String duration = "Duration: " + recipe.getDuration() + " ticks (" + (recipe.getDuration() / 20.0f) + " s)";
            String extraBurntime = "HU/t: " + recipe.getPower();
            String heatIncreaseMultiplier = "Total HU: " + (recipe.getPower() * recipe.getDuration());
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
        DISTILLATION.setGuiData(MULTI_DISPLAY_DISTILLATION);
        CRYO_DISTILLATION.setGuiData(MULTI_DISPLAY_DISTILLATION);
    }

    public static void clientMaps() {
        E_BLAST_FURNACE.setInfoRenderer(InfoRenderers.BLASTING_RENDERER);
        PRIMITIVE_BLAST_FURNACE.setInfoRenderer(InfoRenderers.BASIC_RENDERER);
        COKE_OVEN.setInfoRenderer(InfoRenderers.BASIC_RENDERER);
        BATH.setInfoRenderer(InfoRenderers.BASIC_RENDERER);
        COMBUSTION_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        GAS_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        NAQUADAH_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        SEMI_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        ORE_BYPRODUCTS.setInfoRenderer(InfoRenderers.EMPTY_RENDERER);
        PLASMA_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        STEAM_ALLOY_SMELTER.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_COMPRESSOR.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_EXTRACTOR.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        HP_STEAM_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        STEAM_FORGE_HAMMER.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_MACERATOR.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_FURNACE.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_SIFTER.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        LARGE_BOILERS.setInfoRenderer(LARGE_BOILER_RENDERER);
        HEAT_EXCHANGER.setInfoRenderer(HEAT_EXCHANGER_RENDERER);
    }
}
