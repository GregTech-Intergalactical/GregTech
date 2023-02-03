package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.RecipeMaps;

public class LatheLoader {
    public static void init() {
        AntimatterMaterialTypes.ROD.all().forEach(t -> {
            if (t.has(AntimatterMaterialTypes.INGOT)) {
                RecipeMaps.LATHING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.INGOT.getMaterialTag(t), 1))
                .io(AntimatterMaterialTypes.ROD.get(t, 1), AntimatterMaterialTypes.DUST_SMALL.get(t, 2)).add(t.getId() + "_rod",t.getMass() * 2, 24);
            } else if (t.has(AntimatterMaterialTypes.GEM)) {
                RecipeMaps.LATHING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.GEM.getMaterialTag(t), 1))
                .io(AntimatterMaterialTypes.ROD.get(t, 1), AntimatterMaterialTypes.DUST_SMALL.get(t, 2)).add(t.getId() + "_rod",t.getMass() * 2, 24);
            }
        });
        AntimatterMaterialTypes.SCREW.all().forEach(t -> {
            if (t.has(AntimatterMaterialTypes.BOLT)) {
                  RecipeMaps.LATHING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.BOLT.getMaterialTag(t), 1))
                          .io(AntimatterMaterialTypes.SCREW.get(t, 1)).add(t.getId() + "_screw",t.getMass()/2, 8);
            }
        });
    }
}
