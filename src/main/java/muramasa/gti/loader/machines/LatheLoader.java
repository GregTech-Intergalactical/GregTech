package muramasa.gti.loader.machines;

import muramasa.antimatter.Data;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gti.data.RecipeMaps;

public class LatheLoader {
    public static void init() {
        Data.ROD.all().forEach(t -> {
            if (t.has(Data.INGOT)) {
                RecipeMaps.LATHING.RB().ii(RecipeIngredient.of(Data.INGOT.getMaterialTag(t), 1))
                .io(Data.ROD.get(t, 1), Data.DUST_SMALL.get(t, 2)).add(t.getMass() * 2, 24);
            } else if (t.has(Data.GEM)) {
                RecipeMaps.LATHING.RB().ii(RecipeIngredient.of(Data.GEM.getMaterialTag(t), 1))
                .io(Data.ROD.get(t, 1), Data.DUST_SMALL.get(t, 2)).add(t.getMass() * 2, 24);
            }
        });
        Data.SCREW.all().forEach(t -> {
            if (t.has(Data.BOLT)) {
                  RecipeMaps.LATHING.RB().ii(RecipeIngredient.of(Data.BOLT.getMaterialTag(t), 1))
                          .io(Data.SCREW.get(t, 1)).add(t.getMass()/2, 8);
            }
        });
    }
}
