package muramasa.gti.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.item.ItemStack;

import static muramasa.antimatter.Data.*;
import static muramasa.gti.data.Materials.Stone;
import static muramasa.gti.data.RecipeMaps.THERMAL_CENTRIFUGING;

public class ThermalCentrifuge {
    public static void init() {
        CRUSHED_PURIFIED.all().forEach(m -> {
            Material aOreByProduct1 = m.getByProducts().size() >= 1 ? m.getByProducts().get(0) : m.getMacerateInto();
            Material aOreByProduct2 = m.getByProducts().size() >= 2 ? m.getByProducts().get(1) : aOreByProduct1;
            ItemStack stoneDust = DUST.get(Stone, 1);

            THERMAL_CENTRIFUGING.RB().ii(RecipeIngredient.of(CRUSHED_PURIFIED.get(m),1)).io(CRUSHED_CENTRIFUGED.get(m, 1), DUST_TINY.get(aOreByProduct2, 1), stoneDust).add(500, 48,0,2);
        });
    }
}
