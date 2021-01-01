package muramasa.gti.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialStack;
import muramasa.antimatter.recipe.RecipeBuilder;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import muramasa.gti.data.GregTechData;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.stream.Collectors;

import static muramasa.antimatter.Data.*;
import static muramasa.gti.data.Materials.*;
import static muramasa.gti.data.RecipeMaps.ELECTROLYZING;

public class ElectrolyzerLoader {
    public static void init() {
        add(Bauxite, 39,64, 400, Oxygen);
        add(Calcite, 5, 64, 200, Oxygen);
    }
    //only 1 regular fluid output, stackToNotCell is the fluid output.
    private static void add(Material dust, int count, long euT, int duration, Material liquid) {
        List<MaterialStack> stacks = dust.getProcessInto();
        //Whenever there is more than 1 liquid, stackToNotCell should always be not null.
        if (liquid != null) {
            MaterialStack fluidStack = stacks.stream().filter(t -> t.m == liquid).findAny().get();
            List<ItemStack> cellStacks = stacks.stream().filter(t -> t.m.has(GAS) && !t.m.has(DUST) && t.m != liquid).map(t -> {
                ItemStack stack = t.m.getCell(1000, GregTechData.CellTin);
                stack.setCount(t.s);
                return stack;
            }).collect(Collectors.toList());
            int cellCount = cellStacks.stream().mapToInt(t -> t.getCount()).sum();
            if (cellStacks.size() == 0) {
                //1 fluid
                ELECTROLYZING.RB().ii(AntimatterIngredient.of(DUST.get(dust),count)).io(
                        dust.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m),t.s))
                                .toArray(ItemStack[]::new)).fo(liquid.getGas(fluidStack.s*1000)).add(duration,euT);
            } else {
                cellStacks.addAll(dust.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m),t.s)).collect(Collectors.toList()));
                RecipeBuilder rb = ELECTROLYZING.RB();
                if (cellCount > 0) {
                    rb.ii(AntimatterIngredient.of(DUST.get(dust),count), AntimatterIngredient.of(GregTechData.CellTin,cellCount));
                } else {
                    rb.ii(AntimatterIngredient.of(DUST.get(dust),count));
                }
                rb.io(cellStacks.toArray(new ItemStack[0])).fo(liquid.getGas(fluidStack.s*1000)).add(duration,euT);
            }
        }
    }
}
