package muramasa.gregtech.loader.machines;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.Materials;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.material.MaterialTags.CENT;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.RecipeMaps.CENTRIFUGING;

public class CentrifugingLoader {
    public static void init() {
        DUST_IMPURE.all().forEach(dust -> {
            if (dust.hasByProducts()) CENTRIFUGING.RB().ii(of(DUST_IMPURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1), DUST_TINY.get(dust.getByProducts().get(0), 1)).add(400, 2);
            else CENTRIFUGING.RB().ii(of(DUST_IMPURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1)).chances(1.0, 0.1).add(400, 2);
        });
        DUST_PURE.all().forEach(dust -> {
            if (dust.hasByProducts())  {
                int index = dust.getByProducts().size() > 1 ? 1 : 0;
                CENTRIFUGING.RB().ii(of(DUST_PURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1), DUST_TINY.get(dust.getByProducts().get(index), 1)).add(400, 2);
            }
            else CENTRIFUGING.RB().ii(of(DUST_IMPURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1)).chances(1.0, 0.1).add(400, 2);
        });
        DUST.all().stream().filter(t -> t.has(CENT)).forEach(t -> {
            FluidStack[] fluids = t.getProcessInto().stream().filter(mat -> ((mat.m.has(GAS) || mat.m.has(LIQUID)) && !mat.m.has(DUST))).map(mat -> mat.m.has(GAS) ? mat.m.getGas(mat.s*1000) : mat.m.getLiquid(mat.s*1000)).toArray(FluidStack[]::new);
            if (fluids.length > 2) return;
            for (FluidStack fluid : fluids) {
                if (fluid.isEmpty())
                    return;
            }
            ItemStack[] items = t.getProcessInto().stream().filter(mat -> mat.m.has(DUST)).map(mat -> DUST.get(mat.m, mat.s)).toArray(ItemStack[]::new);
            RecipeIngredient input = DUST.getMaterialIngredient(t, t.getProcessInto().stream().mapToInt(mat -> mat.s).sum());
            CENTRIFUGING.RB().ii(input).io(items).fo(fluids).add(t.getMass()*10, t.getMass() < 10 ? 30 : 64);
        });
        //some stone dust recipe from gtnh without metal mixture
        CENTRIFUGING.RB().ii(RecipeIngredient.of(DUST.get(Stone, 32))).io(DUST.get(Quartz, 9), DUST.get(Materials.PotassiumFeldspar, 9), DUST.get(Materials.Marble, 8), DUST.get(Materials.Biotite, 4),
                DUST.get(Materials.Sodalite, 4)).add(7680, 30);
    }
}
