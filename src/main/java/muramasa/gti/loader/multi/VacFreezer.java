package muramasa.gti.loader.multi;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.Data.INGOT;
import static muramasa.antimatter.Data.INGOT_HOT;
import static muramasa.gti.data.RecipeMaps.VACUUM_FREEZING;

public class VacFreezer {
    public static void init() {
        INGOT_HOT.all().forEach(hi -> {
            Item ingot = INGOT.get(hi);
            int temp = hi.getBlastTemp();
            int voltage;
            if (temp < 1800) {
                voltage = 120;
            } else if (temp < 3000) {
                voltage = 480;
            } else {
                voltage = 1920;
            }
            VACUUM_FREEZING.RB().ii(RecipeIngredient.of(INGOT_HOT.get(hi),1))
                    .io(new ItemStack(ingot,1)).add(hi.getMass(), voltage);
        });
    }
}
