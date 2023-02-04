package muramasa.gregtech.loader.multi;

import muramasa.antimatter.recipe.map.RecipeBuilder;

import static muramasa.antimatter.material.MaterialTags.DISTILLATION_FLUID_INPUT_AMOUNT;
import static muramasa.antimatter.material.MaterialTags.DISTILL_INTO;
import static muramasa.gregtech.data.RecipeMaps.DISTILLATION;

public class DistillationTower {
    public static void init() {
        DISTILL_INTO.getAll().forEach((material, fluidProducts) -> {
           RecipeBuilder builder = DISTILLATION.RB()
                   .fi(material.getLiquid(DISTILLATION_FLUID_INPUT_AMOUNT.getInt(material)));

           for(int i=0;i<fluidProducts.size();i++){
               builder.fo(fluidProducts.get(i).mat().getLiquid(fluidProducts.get(i).amount()));
           }

           builder.add(material.getId(), material.getMass(), 20);
        });
    }
}
