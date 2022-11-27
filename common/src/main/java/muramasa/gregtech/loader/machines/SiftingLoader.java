package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.RecipeHelper;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.Utils;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.Data.*;
import static muramasa.gregtech.data.RecipeMaps.SIFTING;

public class SiftingLoader {
    public static void init() {
        CRUSHED_PURIFIED.all().forEach(m -> {
            if (!m.has(GEM) || !m.has(ORE)) return;
            int multiplier = 1;
            RecipeIngredient ore = ORE.get().get(m, STONE).asIngredient(), crushed = CRUSHED.getIngredient(m, 1), dust = DUST.getIngredient(m, 1);
            ItemStack dustStack = DUST.get(AntimatterMaterials.Stone, 1);
            ItemStack gem = m != MaterialTags.DIRECT_SMELT_INTO.getMapping(m) ? GEM.get(MaterialTags.DIRECT_SMELT_INTO.getMapping(m), 1) : GEM.get(m, 1);
            RecipeHelper.addSmelting(ore, Utils.ca(multiplier * MaterialTags.SMELTING_MULTI.getInt(m), gem));
            if (m.has(GEM_BRITTLE)) {
                ItemStack gemBrittle = GEM_BRITTLE.get(m, 1);
                SIFTING.RB().ii(crushed).io(GEM_POLISHED.get(m, 1), gem, gem, dustStack, gemBrittle, gemBrittle).chances(0.05, 0.15, 0.32, 0.44, 0.78, 1.0).add(800, 16);
            } else {
                SIFTING.RB().ii(crushed).io(gem, gem, gem, gem, dustStack, dustStack).chances(0.01, 0.04, 0.15, 0.2, 0.4, 0.5).add(800, 16);
            }
        });
    }
}
