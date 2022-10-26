package muramasa.gregtech.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.data.GregTechData;

import static muramasa.antimatter.Data.*;
import static muramasa.gregtech.data.RecipeMaps.FLUID_SOLIDIFYING;

public class FluidSolidifier {
    public static void init() {
        AntimatterAPI.all(Material.class, mat -> {
            if (!(mat.has(LIQUID))) return;
            if (mat.has(PLATE)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldPlate, 1).setNoConsume())
                .fi(mat.getLiquid(amount(1.0f))).io(PLATE.get(mat,1)).add(30, 16);
            }
            if (mat.has(GEAR)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldGear, 1).setNoConsume())
                .fi(mat.getLiquid(amount(4.0f))).io(GEAR.get(mat,1)).add(30, 16);
            }
            if (mat.has(INGOT)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldIngot, 1).setNoConsume())
                .fi(mat.getLiquid(amount(1.0f))).io(INGOT.get(mat,1)).add(30, 16);
            }
            if (mat.has(GEAR_SMALL)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldGearSmall, 1).setNoConsume())
                .fi(mat.getLiquid(amount(1.0f))).io(GEAR_SMALL.get(mat,1)).add(30, 16);
            }
            if (mat.has(NUGGET)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldNugget, 1).setNoConsume())
                .fi(mat.getLiquid(ratio()/9)).io(NUGGET.get(mat,1)).add(30, 16);
            }
            if (mat.has(BLOCK)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldBlock, 1).setNoConsume())
                .fi(mat.getLiquid(amount(1.0f))).io(BLOCK.get().get(mat).asStack(1)).add(30, 16);
            }
        });
    }

    private static long ratio(){
        return AntimatterPlatformUtils.isForge() ? 144L : 9000L;
    }

    private static long amount(float ingots){
        return (long) (ratio() * ingots);
    }
}
