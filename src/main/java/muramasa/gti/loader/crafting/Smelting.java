package muramasa.gti.loader.crafting;

import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Consumer;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.INGOT;
import static muramasa.antimatter.util.TagUtils.nc;

public class Smelting {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        DUST.all().forEach(t -> {
            if (t.needsBlastFurnace()) return;
            if (!t.has(INGOT)) return;
            CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(nc(TagUtils.getItemTag(DUST.getMaterialTag(t).getName()))), INGOT.get(t), 1, 200).addCriterion("has_"+DUST.get(t).getName(),provider.hasSafeItem(DUST.get(t))).build(output);
        });
    }
}
