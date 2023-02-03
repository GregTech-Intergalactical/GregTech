package muramasa.gregtech.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.data.GregTechData;

import static muramasa.gregtech.data.RecipeMaps.FLUID_SOLIDIFYING;

public class FluidSolidifier {
    public static void init() {
        AntimatterAPI.all(Material.class, mat -> {
            if (!(mat.has(AntimatterMaterialTypes.LIQUID))) return;
            if (mat.has(AntimatterMaterialTypes.PLATE)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldPlate, 1).setNoConsume())
                .fi(mat.getLiquid(amount(1.0f))).io(AntimatterMaterialTypes.PLATE.get(mat,1)).add(mat.getId() + "_plate",30, 16);
            }
            if (mat.has(AntimatterMaterialTypes.GEAR)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldGear, 1).setNoConsume())
                .fi(mat.getLiquid(amount(4.0f))).io(AntimatterMaterialTypes.GEAR.get(mat,1)).add(mat.getId() + "_gear",30, 16);
            }
            if (mat.has(AntimatterMaterialTypes.INGOT)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldIngot, 1).setNoConsume())
                .fi(mat.getLiquid(amount(1.0f))).io(AntimatterMaterialTypes.INGOT.get(mat,1)).add(mat.getId() + "_ingot",30, 16);
            }
            if (mat.has(AntimatterMaterialTypes.GEAR_SMALL)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldGearSmall, 1).setNoConsume())
                .fi(mat.getLiquid(amount(1.0f))).io(AntimatterMaterialTypes.GEAR_SMALL.get(mat,1)).add(mat.getId() + "_gear_small",30, 16);
            }
            if (mat.has(AntimatterMaterialTypes.NUGGET)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldNugget, 1).setNoConsume())
                .fi(mat.getLiquid(ratio()/9)).io(AntimatterMaterialTypes.NUGGET.get(mat,1)).add(mat.getId() + "_nugget",30, 16);
            }
            if (mat.has(AntimatterMaterialTypes.BLOCK)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldBlock, 1).setNoConsume())
                .fi(mat.getLiquid(amount(1.0f))).io(AntimatterMaterialTypes.BLOCK.get().get(mat).asStack(1)).add(mat.getId() + "_block",30, 16);
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
