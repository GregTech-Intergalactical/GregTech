package muramasa.gregtech.loader.crafting;

import muramasa.antimatter.datagen.loaders.MaterialRecipes;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.Materials;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.gregtech.data.Materials.*;

public class Smelting {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.IRON_NUGGET), NUGGET.get(Materials.WroughtIron), 0.5f, 200).unlockedBy("has_nugget_iron", provider.hasSafeItem(Items.IRON_NUGGET)).save(output, "smelting_nugget_wrought_iron");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.IRON_NUGGET), NUGGET.get(Materials.WroughtIron), 0.5f, 100).unlockedBy("has_nugget_iron", provider.hasSafeItem(Items.IRON_NUGGET)).save(output, "blasting_nugget_wrought_iron");
        SimpleCookingRecipeBuilder.smelting(RecipeIngredient.of(GregTechData.CompressedFireClay, 1), GregTechData.FireBrick, 0.5F, 200).unlockedBy("has_compressed_fire_clay", provider.hasSafeItem(GregTechData.CompressedFireClay)).save(output, GTIRef.ID + ":firebrick");
        MaterialRecipes.addSmeltingRecipe(output, provider, CRUSHED, NUGGET, 7, Cassiterite, Tin);
        MaterialRecipes.addSmeltingRecipe(output, provider, CRUSHED, NUGGET, 7, Garnierite, Nickel);
        MaterialRecipes.addSmeltingRecipe(output, provider, DUST, DUST_TINY, 3, AluminiumHydroxide, Alumina);
    }
}
