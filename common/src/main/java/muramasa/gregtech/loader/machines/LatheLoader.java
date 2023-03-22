package muramasa.gregtech.loader.machines;

import muramasa.gregtech.data.RecipeMaps;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;

public class LatheLoader {
    public static void init() {
        ROD.all().forEach(t -> {
            if (t.has(INGOT)) {
                RecipeMaps.LATHING.RB().ii(of(INGOT.getMaterialTag(t), 1))
                .io(ROD.get(t, 1), DUST_SMALL.get(t, 2)).add(t.getId() + "_rod",t.getHardness(), 24);
            } else if (t.has(GEM)) {
                RecipeMaps.LATHING.RB().ii(of(GEM.getMaterialTag(t), 1))
                .io(ROD.get(t, 1), DUST_SMALL.get(t, 2)).add(t.getId() + "_rod",t.getHardness(), 24);
            }
        });
        SCREW.all().forEach(t -> {
            if (t.has(BOLT)) {
                  RecipeMaps.LATHING.RB().ii(of(BOLT.getMaterialTag(t), 1))
                          .io(SCREW.get(t, 1)).add(t.getId() + "_screw",t.getHardness()/2, 8);
            }
        });
    }
}
