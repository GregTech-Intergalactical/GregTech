package muramasa.gti.loader.crafting;

import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
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
            if (t.needsBlastFurnace()) return;
            if (!t.has(INGOT)) return;
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(nc(TagUtils.getItemTag(DUST.getMaterialTag(t).getName()))), INGOT.get(t), 1, 200).unlockedBy("has_dust_" + t.getId(), provider.hasSafeItem(DUST.get(t))).save(output);
        });
    }
}
