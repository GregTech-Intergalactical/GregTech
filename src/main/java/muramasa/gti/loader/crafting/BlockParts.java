package muramasa.gti.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.gti.block.BlockCasing;
import muramasa.gti.data.GregTechData;
import net.minecraft.block.Block;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.function.Consumer;

import static muramasa.antimatter.Data.*;
import static muramasa.gti.data.Materials.*;

public class BlockParts {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        FRAME.all().forEach(frame -> {
            if (!frame.has(ROD)) return;
            provider.addItemRecipe(output, "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),FRAME.get().get(frame).asItem(),
                    ImmutableMap.of('R', ROD.get(frame), 'W', WRENCH.getTag())
            , "RRR","RWR", "RRR");
        });

        addCasing(output, provider, Invar, GregTechData.CASING_HEAT_PROOF);
        addCasing(output, provider, Aluminium, GregTechData.CASING_FROST_PROOF);
        addCasing(output, provider, Steel, GregTechData.CASING_SOLID_STEEL);
        addCasing(output, provider, StainlessSteel, GregTechData.CASING_STAINLESS_STEEL);
        addCasing(output, provider, Titanium, GregTechData.CASING_TITANIUM);
        addCasing(output, provider, Lead, GregTechData.CASING_RADIATION_PROOF);
        addCasing(output, provider, TungstenSteel, GregTechData.CASING_TUNGSTENSTEEL);
        addTierCasing(output, provider, Steel, GregTechData.CASING_LV);
        addTierCasing(output, provider, WroughtIron, GregTechData.CASING_ULV);
        addTierCasing(output, provider, Aluminium, GregTechData.CASING_MV);
        addTierCasing(output, provider, Titanium, GregTechData.CASING_EV);
        addTierCasing(output, provider, TungstenSteel, GregTechData.CASING_IV);
        addTierCasing(output, provider, StainlessSteel, GregTechData.CASING_HV);
        addTierCasing(output, provider, Osmium, GregTechData.CASING_UV);

        addTurbine(output, provider, Steel, GregTechData.CASING_TURBINE_1);
        addTurbine(output, provider, StainlessSteel, GregTechData.CASING_TURBINE_2);
        addTurbine(output, provider, Titanium, GregTechData.CASING_TURBINE_3);
        addTurbine(output, provider, TungstenSteel, GregTechData.CASING_TURBINE_4);

        addFirebox(output, provider, Bronze, GregTechData.CASING_FIREBOX_BRONZE);
        addFirebox(output, provider, Steel, GregTechData.CASING_FIREBOX_STEEL);
        addFirebox(output, provider, Titanium, GregTechData.CASING_FIREBOX_TITANIUM);
        addFirebox(output, provider, TungstenSteel, GregTechData.CASING_FIREBOX_TUNGSTENSTEEL);

        addBrickedCasing(output, provider, Bronze, GregTechData.CASING_BRICKED_BRONZE);
        addBrickedCasing(output, provider, Steel, GregTechData.CASING_BRICKED_STEEL);
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

    private static void addTierCasing(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), casing,
                ImmutableMap.of('P', PLATE.getMaterialTag(mat), 'W', WRENCH.getTag())
                , "PPP", "PWP", "PPP");
    }
}
