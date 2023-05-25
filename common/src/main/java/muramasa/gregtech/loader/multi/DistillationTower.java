package muramasa.gregtech.loader.multi;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.map.RecipeBuilder;

import static muramasa.gregtech.data.GregTechMaterialTags.DISTILLATION_FLUID_INPUT_AMOUNT;
import static muramasa.gregtech.data.GregTechMaterialTags.DISTILL_INTO;
import static muramasa.gregtech.data.RecipeMaps.DISTILLATION;

public class DistillationTower {
    public static void init() {
        DISTILL_INTO.getAll().forEach((material, fluidProducts) -> {
           RecipeBuilder builder = DISTILLATION.RB()
                   .fi(material.has(AntimatterMaterialTypes.LIQUID) ? material.getLiquid(DISTILLATION_FLUID_INPUT_AMOUNT.getInt(material)) : material.getGas(DISTILLATION_FLUID_INPUT_AMOUNT.getInt(material)));

           for(int i=0;i<fluidProducts.size();i++){
               Material fo = fluidProducts.get(i).mat();
               if (fo.has(AntimatterMaterialTypes.LIQUID)){
                   builder.fo(fluidProducts.get(i).mat().getLiquid(fluidProducts.get(i).amount()));
               } else if (fo.has(AntimatterMaterialTypes.GAS)){
                   builder.fo(fluidProducts.get(i).mat().getGas(fluidProducts.get(i).amount()));
               }

           }

           builder.add(material.getId(), material.getMass(), 20);
        });
    }
}
