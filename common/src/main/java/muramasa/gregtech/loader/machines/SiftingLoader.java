package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.data.AntimatterStoneTypes;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.RecipeHelper;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.Utils;
import net.minecraft.world.item.ItemStack;

import static muramasa.gregtech.data.RecipeMaps.SIFTING;

public class SiftingLoader {
    public static void init() {
        AntimatterMaterialTypes.CRUSHED_PURIFIED.all().forEach(m -> {
            if (!m.has(AntimatterMaterialTypes.GEM) || !m.has(AntimatterMaterialTypes.ORE)) return;
            int multiplier = 1;
            RecipeIngredient ore = AntimatterMaterialTypes.ORE.get().get(m, AntimatterStoneTypes.STONE).asIngredient(), crushed = AntimatterMaterialTypes.CRUSHED.getIngredient(m, 1), dust = AntimatterMaterialTypes.DUST.getIngredient(m, 1);
            ItemStack dustStack = AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Stone, 1);
            ItemStack gem = m != MaterialTags.DIRECT_SMELT_INTO.getMapping(m) ? AntimatterMaterialTypes.GEM.get(MaterialTags.DIRECT_SMELT_INTO.getMapping(m), 1) : AntimatterMaterialTypes.GEM.get(m, 1);
            RecipeHelper.addSmelting(ore, Utils.ca(multiplier * MaterialTags.SMELTING_MULTI.getInt(m), gem));
            if (m.has(AntimatterMaterialTypes.GEM_BRITTLE)) {
                ItemStack gemBrittle = AntimatterMaterialTypes.GEM_BRITTLE.get(m, 1);
                SIFTING.RB().ii(crushed).io(AntimatterMaterialTypes.GEM_POLISHED.get(m, 1), gem, gem, dustStack, gemBrittle, gemBrittle).chances(0.05, 0.15, 0.32, 0.44, 0.78, 1.0).add("crushed_" + m.getId(),800, 16);
            } else {
                SIFTING.RB().ii(crushed).io(gem, gem, gem, gem, dustStack, dustStack).chances(0.01, 0.04, 0.15, 0.2, 0.4, 0.5).add("crushed_" + m.getId(),800, 16);
            }
        });
    }
}
