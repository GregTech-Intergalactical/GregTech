package muramasa.gregtech.loader.multi;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.INGOT;
import static muramasa.antimatter.data.AntimatterMaterialTypes.INGOT_HOT;
import static muramasa.gregtech.data.RecipeMaps.VACUUM_FREEZER;

public class VacuumFreezerLoader {
    public static void init() {
        INGOT_HOT.all().forEach(hi -> {
            Item ingot = INGOT.get(hi);
            VACUUM_FREEZER.RB().ii(RecipeIngredient.of(INGOT_HOT.getMaterialTag(hi),1))
                    .io(new ItemStack(ingot,1)).add("ingot_hot_" + hi.getId(),hi.getMass(), 120);
        });
    }
}
