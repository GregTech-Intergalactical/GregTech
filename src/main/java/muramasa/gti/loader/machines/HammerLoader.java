package muramasa.gti.loader.machines;


import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gti.data.GregTechData;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import static muramasa.antimatter.Data.*;
import static muramasa.gti.data.RecipeMaps.HAMMERING;

public class HammerLoader {
    public static void init() {
        PLATE.all().forEach(plate -> {
            if (!plate.has(INGOT)) return;
            HAMMERING.RB().ii(INGOT.getMaterialIngredient(plate, 3)).io(PLATE.get(plate, 2)).add(plate.getMass()*2, 13);
        });
        HAMMERING.RB().ii(RecipeIngredient.of(Items.OAK_SLAB,1)).io(PLATE.get(Wood,1)).add(20, 24);
    }
}
