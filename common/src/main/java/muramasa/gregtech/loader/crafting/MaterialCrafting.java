package muramasa.gregtech.loader.crafting;

import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.gregtech.Ref;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.gregtech.data.Materials.*;

public class MaterialCrafting {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        addDustRecipe(output, provider,
                "bronze_dust", DUST.get(Tin), DUST.get(Gold), DUST.get(Bronze, 4), "AA ", "AB ", "   ");
    }

    private static void addDustRecipe(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, String recipeName, ItemLike input1,
                                      ItemLike input2, ItemStack outputItem, String... pattern)
    {
        provider.addItemRecipe(output, Ref.ID, recipeName, "misc", "has_wrench",
                provider.hasSafeItem(WRENCH.getTag()), outputItem.getItem(), of(
                'A', input1,
                'B', input2
        ), pattern);
    }
}
