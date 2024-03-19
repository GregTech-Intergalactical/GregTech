package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.gregtech.data.RecipeMaps.SIFTER;

public class SifterLoader {
    public static void init() {
        CRUSHED_PURIFIED.all().forEach(m -> {
            if (!m.has(GEM)) return;
            ItemStack gem = GEM.get(m, 1);
            boolean e = m.has(GEM_EXQUISITE);
            double[] chances = e ? new double[]{0.03, 0.12, 0.45, 0.14, 0.28, 0.35} : new double[]{0.01, 0.04, 0.15, 0.2, 0.4, 0.5};
            ItemStack dustPurified = DUST.get(m, 1);
            SIFTER.RB().ii(CRUSHED_PURIFIED.getMaterialIngredient(m,1)).io(e ? GEM_EXQUISITE.get(m, 1) : gem,
                    e ? GEM_FLAWLESS.get(m, 1) : gem, gem,
                    e ? GEM_FLAWED.get(m, 1) : gem,
                    e ? GEM_CHIPPED.get(m, 1) : gem, dustPurified).chances(chances/*0.05, 0.125, 0.25, 0.5, 0.75, 1.0*/).add("crushed_" + m.getId(),800, 16);
        });
        SIFTER.RB().ii(RecipeIngredient.of(ForgeCTags.GRAVEL, 1)).io(Items.FLINT).add("flint", 40 * 20, 16);
    }
}
