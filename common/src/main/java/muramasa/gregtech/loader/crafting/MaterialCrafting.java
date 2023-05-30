package muramasa.gregtech.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.gregtech.GTIRef;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static muramasa.antimatter.data.AntimatterDefaultTools.MORTAR;
import static muramasa.gregtech.data.Materials.*;

public class MaterialCrafting {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        addShapelessDustRecipe(output, provider, "bronze_dust", AntimatterMaterialTypes.DUST.get(Bronze, 4), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Copper), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Copper), AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Copper), AntimatterMaterialTypes.DUST.get(Tin));
        provider.shapeless(output, GTIRef.ID, "", "dusts", "has_mortor", provider.hasSafeItem(MORTAR.getTag()), AntimatterMaterialTypes.DUST_SMALL.get(Clay, 2), MORTAR.getTag(), Items.CLAY_BALL);
    }

    private static void addShapelessDustRecipe(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, String recipeName, ItemStack outputItem, Object... inputs)
    {
        provider.shapeless(output, GTIRef.ID, recipeName, "misc", "has_wrench",
                provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag()), outputItem, inputs);
    }
}
