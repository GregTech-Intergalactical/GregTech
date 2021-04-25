package muramasa.gti.loader.machines;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.material.MaterialTag.ELEC;
import static muramasa.gti.data.RecipeMaps.ELECTROLYZING;

public class ElectrolyzerLoader {
    public static void init() {
        DUST.all().stream().filter(t -> t.has(ELEC)).forEach(t -> {
            FluidStack[] fluids = t.getProcessInto().stream().filter(mat -> ((mat.m.has(GAS) || mat.m.has(LIQUID)) && !mat.m.has(DUST))).map(mat -> mat.m.has(GAS) ? mat.m.getGas(mat.s*1000) : mat.m.getLiquid(mat.s*1000)).toArray(FluidStack[]::new);
            for (FluidStack fluid : fluids) {
                if (fluid.isEmpty())
                    return;
            }
            if (fluids.length > 2) return;
            ItemStack[] items = t.getProcessInto().stream().filter(mat -> mat.m.has(DUST)).map(mat -> DUST.get(mat.m, mat.s)).toArray(ItemStack[]::new);
            RecipeIngredient input = DUST.getMaterialIngredient(t, t.getProcessInto().stream().mapToInt(mat -> mat.s).sum());
            ELECTROLYZING.RB().ii(input).io(items).fo(fluids).add(t.getMass()*20, t.getMass() < 15 ? 30 : 64);
        });
    }

}
