package muramasa.gregtech.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.gregtech.data.GregTechMaterialTags;
import muramasa.gregtech.data.RecipeMaps;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.ToLongFunction;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;

public class LatheLoader {
    public static void init() {
        ToLongFunction<Material> baseDuration = m -> {
            if (m.has(GregTechMaterialTags.RECIPE_MASS)) return GregTechMaterialTags.RECIPE_MASS.get(m);
            return m.getMass();
        };
        ROD_LONG.all().forEach(t -> {
            if (t.has(INGOT)) {
                RecipeMaps.LATHE.RB().ii(of(INGOT.getMaterialTag(t), 1))
                .io(ROD_LONG.get(t, 1)).add(t.getId() + "_long_rod",baseDuration.applyAsLong(t) * 5, 16);
            } else if (t.has(GEM)) {
                RecipeMaps.LATHE.RB().ii(of(GEM.getMaterialTag(t), 1))
                .io(ROD_LONG.get(t, 1)).add(t.getId() + "_long_rod",baseDuration.applyAsLong(t) * 5, 16);
            }
        });
        SCREW.all().forEach(t -> {
            if (t.has(BOLT)) {
                  RecipeMaps.LATHE.RB().ii(of(BOLT.getMaterialTag(t), 1))
                          .io(SCREW.get(t, 1)).add(t.getId() + "_screw",baseDuration.applyAsLong(t)/8, 4);
            }
        });
        LENS.all().stream().filter(m -> m.has(DUST)).forEach(m -> {
            if (m.has(PLATE)){
                RecipeMaps.LATHE.RB().ii(PLATE.getMaterialIngredient(m, 1)).io(LENS.get(m, 1), DUST_SMALL.get(m, 1)).add(m.getId() + "_lens", Math.max(baseDuration.applyAsLong(m) / 2, 1), 24);
            }
            if (m.has(GEM_EXQUISITE)){
                RecipeMaps.LATHE.RB().ii(GEM_EXQUISITE.getMaterialIngredient(m, 1)).io(LENS.get(m, 1), DUST.get(m, 2)).add(m.getId() + "_lens_exquisite", Math.max(baseDuration.applyAsLong(m), 1), 24);
            }
        });
        RecipeMaps.LATHE.RB().ii(of(ItemTags.PLANKS)).io(new ItemStack(Items.STICK, 2)).add("stick", 25, 4);
    }
}
