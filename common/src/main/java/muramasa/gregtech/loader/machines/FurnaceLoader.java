package muramasa.gregtech.loader.machines;

import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;

import java.util.function.Consumer;

public class FurnaceLoader {

    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        SimpleCookingRecipeBuilder.smelting(RecipeIngredient.of(GregTechData.CompressedFireClay, 1), GregTechData.FireBrick, 0.5F, 200).unlockedBy("has_compressed_fire_clay", provider.hasSafeItem(GregTechData.CompressedFireClay)).save(output, GTIRef.ID + ":firebrick");
    }
}
