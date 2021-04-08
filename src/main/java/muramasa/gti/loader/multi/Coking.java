package muramasa.gti.loader.multi;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.tags.ItemTags;

import static muramasa.antimatter.Data.GEM;
import static muramasa.gti.data.Materials.Charcoal;
import static muramasa.gti.data.RecipeMaps.COKING;

public class Coking {
    public static void init() {
        COKING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 1)).io(GEM.get(Charcoal, 1)).add(600, 0);
    }
}
