package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.Data.*;
import static muramasa.gregtech.data.RecipeMaps.THERMAL_CENTRIFUGING;

public class ThermalCentrifuge {
    public static void init() {
        CRUSHED_PURIFIED.all().forEach(m -> {
            Material aOreByProduct1 = m.getByProducts().size() >= 1 ? m.getByProducts().get(0) : MaterialTags.MACERATE_INTO.getMapping(m);
            Material aOreByProduct2 = m.getByProducts().size() >= 2 ? m.getByProducts().get(1) : aOreByProduct1;
            ItemStack stoneDust = DUST.get(AntimatterMaterials.Stone, 1);

            THERMAL_CENTRIFUGING.RB().ii(RecipeIngredient.of(CRUSHED_PURIFIED.get(m),1)).io(CRUSHED_CENTRIFUGED.get(m, 1), DUST_TINY.get(aOreByProduct2, 1), stoneDust).add(500, 48,0,2);
        });
    }
}
