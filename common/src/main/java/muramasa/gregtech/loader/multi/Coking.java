package muramasa.gregtech.loader.multi;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.tags.ItemTags;

import static muramasa.antimatter.data.AntimatterMaterialTypes.GEM;
import static muramasa.gregtech.data.Materials.Charcoal;
import static muramasa.gregtech.data.RecipeMaps.COKING;

public class Coking {
    public static void init() {
        COKING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 1)).io(GEM.get(Charcoal, 1)).add(600, 0);
    }
}
