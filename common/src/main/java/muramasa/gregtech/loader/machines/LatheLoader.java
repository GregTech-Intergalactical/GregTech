package muramasa.gregtech.loader.machines;

import muramasa.gregtech.data.RecipeMaps;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;

public class LatheLoader {
    public static void init() {
        ROD_LONG.all().forEach(t -> {
            if (t.has(INGOT)) {
                RecipeMaps.LATHING.RB().ii(of(INGOT.getMaterialTag(t), 1))
                .io(ROD_LONG.get(t, 1)).add(t.getId() + "_long_rod",t.getMass() * 5, 16);
            } else if (t.has(GEM)) {
                RecipeMaps.LATHING.RB().ii(of(GEM.getMaterialTag(t), 1))
                .io(ROD_LONG.get(t, 1)).add(t.getId() + "_long_rod",t.getMass() * 5, 16);
            }
        });
        SCREW.all().forEach(t -> {
            if (t.has(BOLT)) {
                  RecipeMaps.LATHING.RB().ii(of(BOLT.getMaterialTag(t), 1))
                          .io(SCREW.get(t, 1)).add(t.getId() + "_screw",t.getMass()/8, 4);
            }
        });
        LENS.all().stream().filter(m -> m.has(DUST)).forEach(m -> {
            if (m.has(PLATE)){
                RecipeMaps.LATHING.RB().ii(PLATE.getMaterialIngredient(m, 1)).io(LENS.get(m, 1), DUST_SMALL.get(m, 1)).add(m.getId() + "_lens", Math.max(m.getMass() / 2, 1), 24);
            }
            if (m.has(GEM_EXQUISITE)){
                RecipeMaps.LATHING.RB().ii(GEM_EXQUISITE.getMaterialIngredient(m, 1)).io(LENS.get(m, 1), DUST.get(m, 2)).add(m.getId() + "_lens_exquisite", Math.max(m.getMass(), 1), 24);
            }
        });
    }
}
