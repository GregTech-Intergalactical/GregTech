package muramasa.gregtech.loader.machines;

import muramasa.gregtech.data.RecipeMaps;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;

public class LatheLoader {
    public static void init() {
        ROD.all().forEach(t -> {
            if (t.has(INGOT)) {
                RecipeMaps.LATHING.RB().ii(of(INGOT.getMaterialTag(t), 1))
                .io(ROD.get(t, 2), DUST_SMALL.get(t, 2)).add(t.getId() + "_rod",t.getHardness(), 24);
            } else if (t.has(GEM)) {
                RecipeMaps.LATHING.RB().ii(of(GEM.getMaterialTag(t), 1))
                .io(ROD.get(t, 2), DUST_SMALL.get(t, 2)).add(t.getId() + "_rod",t.getHardness(), 24);
            }
        });
        SCREW.all().forEach(t -> {
            if (t.has(BOLT)) {
                  RecipeMaps.LATHING.RB().ii(of(BOLT.getMaterialTag(t), 1))
                          .io(SCREW.get(t, 1)).add(t.getId() + "_screw",t.getHardness()/2, 8);
            }
        });
        LENS.all().forEach(m -> {
            if (m.has(PLATE) && m.has(DUST)){
                RecipeMaps.LATHING.RB().ii(PLATE.getMaterialIngredient(m, 1)).io(LENS.get(m, 1), DUST_SMALL.get(m, 1)).add(m.getId() + "_lens", m.getHardness(), 16);
            }
        });
    }
}
