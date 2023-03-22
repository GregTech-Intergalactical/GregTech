package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static muramasa.antimatter.material.MaterialTags.ELEC;
import static muramasa.gregtech.data.RecipeMaps.ELECTROLYZING;

public class ElectrolyzerLoader {
    public static void init() {
        AntimatterMaterialTypes.DUST.all().stream().filter(t -> t.has(ELEC)).forEach(t -> {
            FluidStack[] fluids = t.getProcessInto().stream().filter(mat -> ((mat.m.has(AntimatterMaterialTypes.GAS) || mat.m.has(AntimatterMaterialTypes.LIQUID)) && !mat.m.has(AntimatterMaterialTypes.DUST))).map(mat -> mat.m.has(AntimatterMaterialTypes.GAS) ? mat.m.getGas(mat.s*1000) : mat.m.getLiquid(mat.s*1000)).toArray(FluidStack[]::new);
            for (FluidStack fluid : fluids) {
                if (fluid.isEmpty())
                    return;
            }
            if (fluids.length > 6) return;
            ItemStack[] items = t.getProcessInto().stream().filter(mat -> mat.m.has(AntimatterMaterialTypes.DUST)).map(mat -> AntimatterMaterialTypes.DUST.get(mat.m, mat.s)).toArray(ItemStack[]::new);
            RecipeIngredient input = AntimatterMaterialTypes.DUST.getMaterialIngredient(t, t.getProcessInto().stream().mapToInt(mat -> mat.s).sum());
            ELECTROLYZING.RB().ii(input).io(items).fo(fluids).add("dust_" + t.getId(),t.getMass()*20, t.getMass() < 15 ? 30 : 64);
        });
    }

}
