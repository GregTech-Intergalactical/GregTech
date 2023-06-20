package muramasa.gregtech.loader.crafting;

import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.data.Materials;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;

public class Smelting {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.IRON_NUGGET), NUGGET.get(Materials.WroughtIron), 0.5f, 200).unlockedBy("has_nugget_iron", provider.hasSafeItem(Items.IRON_NUGGET)).save(output, "smelting_nugget_wrought_iron");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.IRON_NUGGET), NUGGET.get(Materials.WroughtIron), 0.5f, 100).unlockedBy("has_nugget_iron", provider.hasSafeItem(Items.IRON_NUGGET)).save(output, "blasting_nugget_wrought_iron");
    }
}
