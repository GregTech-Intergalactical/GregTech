package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterStoneTypes;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.RecipeHelper;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.Utils;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.gregtech.data.RecipeMaps.SIFTING;

public class SiftingLoader {
    public static void init() {
        CRUSHED_PURIFIED.all().forEach(m -> {
            if (!m.has(GEM) || !m.has(ORE)) return;
            int multiplier = 1;
            RecipeIngredient ore = ORE.get().get(m, AntimatterStoneTypes.STONE).asIngredient();
            ItemStack gem = m != MaterialTags.DIRECT_SMELT_INTO.getMapping(m) ? GEM.get(MaterialTags.DIRECT_SMELT_INTO.getMapping(m), 1) : GEM.get(m, 1);
            RecipeHelper.addSmelting(ore, Utils.ca(multiplier * MaterialTags.SMELTING_MULTI.getInt(m), gem));
            ItemStack gemExquisite = GEM_EXQUISITE.get(m, 1), gemFlawless = GEM_FLAWLESS.get(m, 1), gemFlawed = GEM_FLAWED.get(m, 1), gemChipped = GEM_CHIPPED.get(m, 1), dustPurified = DUST_PURE.get(m, 1);
            SIFTING.RB().ii(CRUSHED_PURIFIED.getMaterialIngredient(m,1)).io(gemExquisite, gemFlawless, gem, gemFlawed, gemChipped, dustPurified).chances(0.05, 0.125, 0.25, 0.5, 0.75, 1.0).add("crushed_" + m.getId(),800, 16);
        });
    }
}
