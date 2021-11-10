package muramasa.gti.loader.machines;

import muramasa.antimatter.Data;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gti.data.Materials;
import net.minecraft.fluid.Fluids;
import net.minecraftforge.fluids.FluidStack;

import static muramasa.gti.data.RecipeMaps.CUTTING;

public class CuttingLoader {
    public static void init() {
        for (Material mat : Data.PLATE.all()) {
            if (!mat.has(Data.BLOCK))
                continue;
            int multiplier = mat.has(Data.GEM) ? 8 : 3;
            if (mat == Data.Diamond)
                multiplier = 20;
            CUTTING.RB().ii(RecipeIngredient.of(Data.BLOCK.getMaterialTag(mat), 1)).io(Data.PLATE.get(mat, 9))
                    .fi(new FluidStack(Fluids.WATER, 1000)).add(mat.getMass() * 2 * multiplier, 24);
            CUTTING.RB().ii(RecipeIngredient.of(Data.BLOCK.getMaterialTag(mat), 1)).io(Data.PLATE.get(mat, 9))
                    .fi(Materials.Lubricant.getLiquid(250)).add(mat.getMass() * multiplier, 16);

        }
    }
}
