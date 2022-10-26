package muramasa.gregtech.loader.crafting;

import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.INGOT;
import static muramasa.antimatter.util.TagUtils.nc;

public class Smelting {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        DUST.all().forEach(t -> {
            if (t.has(MaterialTags.NEEDS_BLAST_FURNACE)) return;
            if (!t.has(INGOT)) return;
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(TagUtils.getItemTag(DUST.getMaterialTag(t).location())), INGOT.get(t), 1, 200).unlockedBy("has_dust_" + t.getId(), provider.hasSafeItem(DUST.get(t))).save(output);
        });
    }
}
