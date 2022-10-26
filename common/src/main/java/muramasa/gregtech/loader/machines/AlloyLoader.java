package muramasa.gregtech.loader.machines;

import muramasa.antimatter.Data;
import muramasa.antimatter.material.MaterialStack;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.Materials;
import net.minecraft.world.item.ItemStack;

import java.util.List;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.INGOT;
import static muramasa.antimatter.material.MaterialTags.METAL;
import static muramasa.gregtech.data.RecipeMaps.ALLOY_SMELTING;

public class AlloyLoader {



    public static void init() {
        INGOT.all().forEach(t -> {
            if (t.has(MaterialTags.NEEDS_BLAST_FURNACE)) return;
            if (!t.has(METAL)) return;
            List<MaterialStack> stacks = t.getProcessInto();
            if (stacks.size() != 2) return;
            int cumulative = stacks.get(0).s + stacks.get(1).s;
            MaterialStack first = stacks.get(0);
            MaterialStack second = stacks.get(1);
            ALLOY_SMELTING.RB().ii(RecipeIngredient.of(DUST.getMaterialTag(first.m),first.s),RecipeIngredient.of(DUST.getMaterialTag(second.m),second.s)).io(new ItemStack(INGOT.get(t),cumulative)).add(100, 16);
            boolean firstIngot = first.m.has(INGOT);
            boolean secondIngot = second.m.has(INGOT);
            if (firstIngot && secondIngot) ALLOY_SMELTING.RB().ii(RecipeIngredient.of(INGOT.getMaterialTag(first.m),first.s),RecipeIngredient.of(INGOT.getMaterialTag(second.m),second.s)).io(new ItemStack(INGOT.get(t),cumulative)).add(100, 12);
            if (firstIngot) ALLOY_SMELTING.RB().ii(RecipeIngredient.of(INGOT.getMaterialTag(first.m),first.s),RecipeIngredient.of(DUST.getMaterialTag(second.m),second.s)).io(new ItemStack(INGOT.get(t),cumulative)).add(100, 12);
            if (secondIngot) ALLOY_SMELTING.RB().ii(RecipeIngredient.of(DUST.getMaterialTag(first.m),first.s),RecipeIngredient.of(INGOT.getMaterialTag(second.m),second.s)).io(new ItemStack(INGOT.get(t),cumulative)).add(100, 12);
        });

        ALLOY_SMELTING.RB().ii(RecipeIngredient.of(DUST.getMaterialTag(Materials.Brick), 1),
                RecipeIngredient.of(DUST.getMaterialTag(Data.Sand), 2))
                .io(DUST.get(Materials.Fireclay, 1))
                .add(20, 10);
    }
}
