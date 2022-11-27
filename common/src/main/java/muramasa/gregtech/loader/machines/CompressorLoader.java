package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;

import static muramasa.antimatter.Data.*;
import static muramasa.gregtech.data.Materials.WoodPulp;
import static muramasa.gregtech.data.RecipeMaps.COMPRESSING;

public class CompressorLoader {
    public static void init() {
        INGOT.all().forEach(ingot -> {
            if (ingot.has(BLOCK)) {
                COMPRESSING.RB().ii(RecipeIngredient.of(INGOT.get(ingot), 9)).io(BLOCK.get().get(ingot).asStack(1))
                        .add(Math.max(80, ingot.getMass() * 2), 16);
            }
        });
        GEM.all().forEach(ingot -> {
            if (ingot.has(BLOCK)) {
                COMPRESSING.RB().ii(RecipeIngredient.of(GEM.get(ingot), 9)).io(BLOCK.get().get(ingot).asStack(1))
                        .add(Math.max(80, ingot.getMass() * 2), 16);
            }
        });
        COMPRESSING.RB().ii(RecipeIngredient.of(DUST.get(WoodPulp), 1)).io(PLATE.get(AntimatterMaterials.Wood, 1)).add(60, 4);
    }
}
