package muramasa.gregtech.loader.crafting;

import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.gregtech.Ref;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

import static muramasa.gregtech.data.Materials.*;

public class MaterialCrafting {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        addShapelessDustRecipe(output, provider, "bronze_dust", AntimatterMaterialTypes.DUST.get(Bronze, 4), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Copper), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Copper), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Copper), AntimatterMaterialTypes.DUST.get(Tin));
    }

    private static void addShapelessDustRecipe(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, String recipeName, ItemStack outputItem, Object... inputs)
    {
        provider.shapeless(output, Ref.ID, recipeName, "misc", "has_wrench",
                provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag()), outputItem, inputs);
    }
}
