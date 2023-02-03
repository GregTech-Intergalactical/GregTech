package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;

import static muramasa.gregtech.data.Materials.WoodPulp;
import static muramasa.gregtech.data.RecipeMaps.COMPRESSING;

public class CompressorLoader {
    public static void init() {
        AntimatterMaterialTypes.INGOT.all().forEach(ingot -> {
            if (ingot.has(AntimatterMaterialTypes.BLOCK)) {
                COMPRESSING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.INGOT.get(ingot), 9)).io(AntimatterMaterialTypes.BLOCK.get().get(ingot).asStack(1))
                        .add("block_" + ingot.getId(),Math.max(80, ingot.getMass() * 2), 16);
            }
        });
        AntimatterMaterialTypes.GEM.all().forEach(ingot -> {
            if (ingot.has(AntimatterMaterialTypes.BLOCK)) {
                COMPRESSING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.GEM.get(ingot), 9)).io(AntimatterMaterialTypes.BLOCK.get().get(ingot).asStack(1))
                        .add("gem_" + ingot.getId(),Math.max(80, ingot.getMass() * 2), 16);
            }
        });
        COMPRESSING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.DUST.get(WoodPulp), 1)).io(AntimatterMaterialTypes.PLATE.get(AntimatterMaterials.Wood, 1)).add("wood_plate",60, 4);
    }
}
