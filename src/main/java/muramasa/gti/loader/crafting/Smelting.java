package muramasa.gti.loader.crafting;

import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.util.TagUtils;
import muramasa.gti.data.GregTechData;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Consumer;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.util.TagUtils.nc;
import static muramasa.gti.data.Materials.Fireclay;

public class Smelting {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        DUST.all().forEach(t -> {
            if (t.needsBlastFurnace()) return;
            if (!t.has(INGOT)) return;
            CookingRecipeBuilder.smelting(Ingredient.of(nc(TagUtils.getItemTag(DUST.getMaterialTag(t).getName()))), INGOT.get(t), 1, 200).unlockedBy("has_dust_" + t.getId(), provider.hasSafeItem(DUST.get(t))).save(output);
        });
        CookingRecipeBuilder.smelting(Ingredient.of(DUST.get(Fireclay)), GregTechData.Firebrick, 10, 100).unlockedBy("has_dust_" + Fireclay.getId(), provider.hasSafeItem(DUST.get(Fireclay))).save(output);
    }
}
