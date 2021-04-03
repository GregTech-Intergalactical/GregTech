package muramasa.gti.loader.machines;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;

import static muramasa.antimatter.Data.BLOCK;
import static muramasa.antimatter.Data.INGOT;
import static muramasa.gti.data.RecipeMaps.COMPRESSING;

public class CompressorLoader {
    public static void init() {
        INGOT.all().forEach(ingot -> {
            if (ingot.has(BLOCK)) {
                COMPRESSING.RB().ii(RecipeIngredient.of(INGOT.get(ingot),9)).io(BLOCK.get().get(ingot).asStack(1)).add(Math.max(40,ingot.getMass()*2), 16);
            }
        });
    }
}
