package muramasa.gregtech.loader.multi;

import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechMaterialTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.INGOT;
import static muramasa.antimatter.data.AntimatterMaterialTypes.INGOT_HOT;
import static muramasa.gregtech.data.GregTechMaterialTags.BLAST_FURNACE_TEMP;
import static muramasa.gregtech.data.RecipeMaps.VACUUM_FREEZING;

public class VacFreezer {
    public static void init() {
        INGOT_HOT.all().forEach(hi -> {
            Item ingot = INGOT.get(hi);
            int temp = BLAST_FURNACE_TEMP.getInt(hi);
            int voltage;
            if (temp < 1800) {
                voltage = 120;
            } else if (temp < 3000) {
                voltage = 480;
            } else {
                voltage = 1920;
            }
            VACUUM_FREEZING.RB().ii(RecipeIngredient.of(INGOT_HOT.get(hi),1))
                    .io(new ItemStack(ingot,1)).add("ingot_hot_" + hi.getId(),hi.getMass(), voltage);
        });
    }
}
