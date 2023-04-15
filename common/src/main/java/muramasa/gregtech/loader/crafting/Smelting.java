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

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterialTypes.INGOT;
import static muramasa.antimatter.util.TagUtils.nc;

public class Smelting {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.IRON_INGOT), INGOT.get(Materials.WroughtIron), 0.5f, 200).unlockedBy("has_ingot_iron", provider.hasSafeItem(Items.IRON_INGOT)).save(output, "smelting_ingot_wrought_iron");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.IRON_INGOT), INGOT.get(Materials.WroughtIron), 0.5f, 100).unlockedBy("has_dust_iron", provider.hasSafeItem(Items.IRON_INGOT)).save(output, "blasting_ingot_wrought_iron");
    }
}
