package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.Materials;
import muramasa.gregtech.data.RecipeMaps;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import static muramasa.gregtech.data.RecipeMaps.CUTTING;

public class CuttingLoader {
    public static void init() {
        for (Material mat : AntimatterMaterialTypes.PLATE.all()) {
            if (!mat.has(AntimatterMaterialTypes.BLOCK))
                continue;
            int multiplier = mat.has(AntimatterMaterialTypes.GEM) ? 8 : 3;
            if (mat == AntimatterMaterials.Diamond)
                multiplier = 20;
            CUTTING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.BLOCK.getMaterialTag(mat), 1)).io(AntimatterMaterialTypes.PLATE.get(mat, 9))
                    .fi(new FluidStack(Fluids.WATER, 1000)).add(mat.getMass() * 2 * multiplier, 24);
            CUTTING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.BLOCK.getMaterialTag(mat), 1)).io(AntimatterMaterialTypes.PLATE.get(mat, 9))
                    .fi(Materials.Lubricant.getLiquid(250)).add(mat.getMass() * multiplier, 16);

        }
        AntimatterMaterialTypes.BOLT.all().forEach(t -> {
            if (t.has(AntimatterMaterialTypes.ROD)) {
                RecipeMaps.CUTTING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.ROD.getMaterialTag(t), 1))
                        .fi(new FluidStack(Fluids.WATER, 1000))
                        .io(AntimatterMaterialTypes.BOLT.get(t, 2)).add(t.getMass()/4, 8);
                RecipeMaps.CUTTING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.ROD.getMaterialTag(t), 1))
                        .fi(Materials.Lubricant.getLiquid(250))
                        .io(AntimatterMaterialTypes.BOLT.get(t, 2)).add(t.getMass()/4, 8);
            }
        });
    }
}
