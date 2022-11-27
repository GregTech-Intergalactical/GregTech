package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.Data.*;
import static muramasa.gregtech.data.RecipeMaps.ORE_WASHING;

public class WasherLoader {
    public static void init() {
        CRUSHED.all().forEach(m -> {
            if (!m.has(CRUSHED_PURIFIED)) return;
            ItemStack stoneDust = DUST.get(AntimatterMaterials.Stone, 1);

            Material aOreByProduct1 = m.getByProducts().size() >= 1 ? m.getByProducts().get(0) : MaterialTags.MACERATE_INTO.getMapping(m);
            //Material aOreByProduct2 = m.getByProducts().size() >= 2 ? m.getByProducts().get(1) : aOreByProduct1;
            ORE_WASHING.RB().fi(AntimatterMaterials.Water.getLiquid(1000)).ii(RecipeIngredient.of(CRUSHED.get(m,1))).io(CRUSHED_PURIFIED.get(m,1), DUST_TINY.get(aOreByProduct1,1), stoneDust).add(200, 24);
        });
    }
}
