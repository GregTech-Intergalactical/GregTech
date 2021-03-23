package muramasa.gti.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;

import static muramasa.antimatter.Data.*;
import static muramasa.gti.data.GregTechData.INT_CIRCUITS;
import static muramasa.gti.data.Materials.*;

import java.util.List;
import java.util.stream.Collectors;

import static muramasa.gti.data.RecipeMaps.MIXING;



public class MixerLoader {

    public static void init() {
        addDust(StainlessSteel, 120, 45*20);
        addDust(Nichrome, 120, 25*20);
        addDust(Invar, 7, 15*20);
    }

    private static void addDust(Material mat, int eut, int duration) {
        for (MaterialTypeItem<?> type : new MaterialTypeItem[]{DUST, DUST_SMALL, DUST_TINY}) {
            List<RecipeIngredient> ings = mat.getProcessInto().stream().map(t -> type.getMaterialIngredient(t.m, t.s)).collect(Collectors.toList());
            if (ings.size() == 0) return;
            int count = mat.getProcessInto().stream().mapToInt(t -> t.s).sum();
            ings.add(INT_CIRCUITS.get(4));
            MIXING.RB().ii(ings).io(DUST.get(mat,count)).add(duration,eut);
        }
    }
}
