package muramasa.gti.loader.machines;

import muramasa.antimatter.recipe.RecipeHelper;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.Utils;
import net.minecraft.item.ItemStack;

import static muramasa.antimatter.Data.*;
import static muramasa.gti.data.RecipeMaps.SIFTING;

public class SiftingLoader {
    public static void init() {
        CRUSHED_PURIFIED.all().forEach(m -> {
            if (!m.has(GEM) || !m.has(ORE)) return;
            int multiplier = 1;
            RecipeIngredient ore = ORE.get().get(m, STONE).asIngredient(), crushed = CRUSHED.getIngredient(m, 1), dust = DUST.getIngredient(m, 1);
            ItemStack dustStack = DUST.get(Stone, 1);
            ItemStack gem = m.hasDirectSmeltInto() ? GEM.get(m.getDirectSmeltInto(), 1) : GEM.get(m, 1);
            RecipeHelper.addSmelting(ore, Utils.ca(multiplier * m.getSmeltingMulti(), gem));
            if (m.has(GEM_BRITTLE)) {
                ItemStack gemBrittle = GEM_BRITTLE.get(m, 1);
                SIFTING.RB().ii(crushed).io(GEM_POLISHED.get(m, 1), gem, gem, dustStack, gemBrittle, gemBrittle).chances(5, 15, 32, 44, 78, 100).add(800, 16);
            } else {
                SIFTING.RB().ii(crushed).io(gem, gem, gem, gem, dustStack, dustStack).chances(1, 4, 15, 20, 40, 50).add(800, 16);
            }
        });
    }
}
