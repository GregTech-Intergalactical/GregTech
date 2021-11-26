package muramasa.gti.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gti.block.BlockCasing;
import muramasa.gti.data.GregTechData;
import net.minecraft.block.Block;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;

import java.util.function.Consumer;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.material.MaterialTag.WIRE;
import static muramasa.gti.data.GregTechData.*;
import static muramasa.gti.data.Materials.*;
import static muramasa.gti.data.RecipeMaps.ASSEMBLING;
import static muramasa.gti.data.TierMaps.INT_CIRCUITS;
import static muramasa.gti.data.TierMaps.TIER_CIRCUITS;

public class BlockParts {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        FRAME.all().forEach(frame -> {
            if (!frame.has(ROD)) return;
            provider.addItemRecipe(output, "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),FRAME.get().get(frame).asItem(),
                    ImmutableMap.of('R', ROD.get(frame), 'W', WRENCH.getTag())
            , "RRR","RWR", "RRR");
        });

        addBrickedCasing(output, provider, Bronze, GregTechData.CASING_BRICKED_BRONZE);
        addBrickedCasing(output, provider, Steel, GregTechData.CASING_BRICKED_STEEL);

        addFirebox(output, provider, Bronze, GregTechData.CASING_FIREBOX_BRONZE);
        addFirebox(output, provider, Steel, GregTechData.CASING_FIREBOX_STEEL);
        addFirebox(output, provider, Titanium, GregTechData.CASING_FIREBOX_TITANIUM);
        addFirebox(output, provider, TungstenSteel, GregTechData.CASING_FIREBOX_TUNGSTENSTEEL);

        addTurbine(output, provider, Steel, GregTechData.CASING_TURBINE_1);
        addTurbine(output, provider, StainlessSteel, GregTechData.CASING_TURBINE_2);
        addTurbine(output, provider, Titanium, GregTechData.CASING_TURBINE_3);
        addTurbine(output, provider, TungstenSteel, GregTechData.CASING_TURBINE_4);

        addCasing(output, provider, Invar, GregTechData.CASING_HEAT_PROOF);
        addCasing(output, provider, Aluminium, GregTechData.CASING_FROST_PROOF);
        addCasing(output, provider, Steel, GregTechData.CASING_SOLID_STEEL);
        addCasing(output, provider, StainlessSteel, GregTechData.CASING_STAINLESS_STEEL);
        addCasing(output, provider, Titanium, GregTechData.CASING_TITANIUM);
        addCasing(output, provider, Lead, GregTechData.CASING_RADIATION_PROOF);
        addCasing(output, provider, TungstenSteel, GregTechData.CASING_TUNGSTENSTEEL);

        addTierCasing(output, provider, WroughtIron, Lead, GregTechData.CASING_ULV,1);
        addTierCasing(output, provider, Steel, Copper, GregTechData.CASING_LV,2);
        addTierCasing(output, provider, Aluminium, StainlessSteel, GregTechData.CASING_MV,3);
        addTierCasing(output, provider, TungstenSteel, TungstenCarbide, GregTechData.CASING_HV,4);
        addTierCasing(output, provider, Ultimet, HSSG, GregTechData.CASING_EV,5);
        addTierCasing(output, provider, HSSE, HSSS, GregTechData.CASING_IV,6);
        //addTierCasing(output, provider, TungstenSteel, GregTechData.CASING_LUV);
        //addTierCasing(output, provider, Osmiridium, GregTechData.CASING_ZPM);
        //addTierCasing(output, provider, Ultimet, GregTechData.CASING_UV);
        //addTierCasing(output, provider, RedSteel, GregTechData.CASING_MAX);

        addTierHull(output, provider, Iron, WIRE_RED_ALLOY, CircuitBasic, GregTechData.CASING_ULV, GregTechData.HULL_ULV,1);
        addTierHull(output, provider, AnnealedCopper, WIRE_TIN, CircuitBasic, GregTechData.CASING_LV, GregTechData.HULL_LV,2);
        addTierHull(output, provider, Silver, WIRE_CUPRONICKEL, CircuitGood, GregTechData.CASING_MV,GregTechData.HULL_MV,3);
        addTierHull(output, provider, SterlingSilver, WIRE_ELECTRUM, CircuitGood, GregTechData.CASING_HV, GregTechData.HULL_HV,4);
        addTierHull(output, provider, RoseGold, WIRE_NICHROME, CircuitAdv, GregTechData.CASING_EV, GregTechData.HULL_EV,5);
        addTierHull(output, provider, RedSteel, WIRE_NIOBIUM_TITANIUM, CircuitAdv, GregTechData.CASING_IV, GregTechData.HULL_IV,6);
    }

    private static void addCasing(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), casing,
                ImmutableMap.of('P', PLATE.getMaterialTag(mat), 'W', WRENCH.getTag(), 'H', HAMMER.getTag(), 'F', FRAME.get().get(mat).asItem())
                ,
                "PHP", "PFP", "PWP");
    }

    private static void addBrickedCasing(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), casing,
                ImmutableMap.of('B', Items.BRICK, 'P', PLATE.getMaterialTag(mat), 'H', HAMMER.getTag())
                , "PPP", "PHP", "BBB");
    }

    private static void addFirebox(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), casing,
                ImmutableMap.of('P', PLATE.getMaterialTag(mat), 'W', WRENCH.getTag(), 'H', HAMMER.getTag(), 'F', FRAME.get().get(mat).asItem())
                , "PHP", "PFP", "PWP");
    }

    private static void addTurbine(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), casing,
                ImmutableMap.of('P', PLATE.getMaterialTag(mat), 'R', ROD.getMaterialTag(mat), 'F', FRAME.get().get(mat).asItem())
                , "PRP", "PFP", "PRP");
    }

    private static void addTierCasing(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Material mat2, Block casing, int tier) {
        provider.addItemRecipe(output, "casings", "has_casing", provider.hasSafeItem(WRENCH.getTag()), casing,
                ImmutableMap.of('P', PLATE.getMaterialTag(mat), 'W', WRENCH.getTag(), 'B', BOLT.getMaterialTag(mat2))
                , "BPB", "PWP", "BPB");
        }

    private static void addTierHull(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Wire w, ItemBasic circ, Block casing, Block hull, int tier) {
        provider.addItemRecipe(output, "hulls", "has_hull", provider.hasSafeItem(WRENCH.getTag()), hull,
                ImmutableMap.of('S', SCREW.getMaterialTag(mat), 'R', Items.REDSTONE, 'W', w.getBlockItem(PipeSize.VTINY), 'K', casing, 'C', TIER_CIRCUITS.getOrDefault(tier, circ))
                , "SRS", "WKW", "SCS");
        }
}
