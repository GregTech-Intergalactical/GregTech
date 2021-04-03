package muramasa.gti.loader.multi;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static muramasa.antimatter.Data.INGOT;
import static muramasa.antimatter.Data.INGOT_HOT;
import static muramasa.gti.data.RecipeMaps.VACUUM_FREEZING;

public class VacFreezer {
    public static void init() {
        INGOT_HOT.all().forEach(hi -> {
            Item ingot = INGOT.get(hi);
            VACUUM_FREEZING.RB().ii(RecipeIngredient.of(INGOT_HOT.get(hi),1))
                    .io(new ItemStack(ingot,1)).add(hi.getMass(), 120);
        });
    }
}
