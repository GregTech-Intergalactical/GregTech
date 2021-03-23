package muramasa.gti.loader.machines;

import muramasa.antimatter.item.ItemFluidCell;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialStack;
import muramasa.antimatter.recipe.RecipeBuilder;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.LazyValue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static muramasa.antimatter.Data.*;
import static muramasa.gti.data.Materials.*;
import static muramasa.gti.data.RecipeMaps.ELECTROLYZING;

public class ElectrolyzerLoader {
    public static void init() {
        add(Bauxite, 64, 400, Oxygen);
        add(Calcite,  64, 200, Oxygen);
        add(BrownLimonite,  24, 200, Oxygen);
        add(Cassiterite,  24, 250, Oxygen);
        add(Grossular,  64, 300, Oxygen);
        add(Garnierite,  64, 300, Oxygen);
        add(Magnetite,  32, 300, Oxygen);
    }

    private static void add(Material dust, long euT, int duration, Material liquid) {
        add(dust, dust.getProcessInto().stream().mapToInt(t -> t.s).sum(), euT, duration, liquid);
    }

    private static void add(Material dust, int count, long euT, int duration, Material liquid) {
        List<MaterialStack> stacks = dust.getProcessInto();
        //Whenever there is more than 1 liquid, stackToNotCell should always be not null.
        for (ItemFluidCell cell : EMPTY_CELLS) {
            if (liquid != null) {
                MaterialStack fluidStack = stacks.stream().filter(t -> t.m == liquid).findAny().get();
                List<ItemStack> cellStacks = stacks.stream().filter(t -> t.m.has(GAS) && !t.m.has(DUST) && t.m != liquid).map(t -> {
                    ItemStack stack = t.m.has(LIQUID) ? t.m.getCell(1000, cell) : t.m.getCellGas(1000, cell);
                    stack.setCount(t.s);
                    return stack;
                }).collect(Collectors.toList());
                int cellCount = cellStacks.stream().mapToInt(ItemStack::getCount).sum();
                if (cellStacks.size() == 0) {
                    //1 fluid
                    ELECTROLYZING.RB().ii(AntimatterIngredient.of(DUST.get(dust), count)).io(
                            dust.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m), t.s))
                                    .toArray(ItemStack[]::new)).fo(liquid.getGas(fluidStack.s * 1000)).add(duration, euT);
                    return;
                } else {
                    RecipeBuilder rb = ELECTROLYZING.RB();
                    if (cellCount > 0) {
                        RecipeIngredient celli = AntimatterIngredient.of(cell, cellCount);
                        rb.ii(AntimatterIngredient.of(DUST.get(dust), count), celli);
                    } else {
                        rb.ii(AntimatterIngredient.of(DUST.get(dust), count));
                    }
                    cellStacks.addAll(Arrays.asList(dust.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m), t.s)).toArray(ItemStack[]::new)));
                    rb.io(cellStacks.toArray(new ItemStack[0])).fo(liquid.getGas(fluidStack.s * 1000)).add(duration, euT);
                }
            } else {
                ELECTROLYZING.RB().ii(AntimatterIngredient.of(DUST.get(dust), count)).io(
                        dust.getProcessInto().stream().filter(t -> DUST.allowGen(t.m)).map(t -> new ItemStack(DUST.get(t.m), t.s))
                                .toArray(ItemStack[]::new)).add(duration, euT);
                return;
            }
        }
    }
}
