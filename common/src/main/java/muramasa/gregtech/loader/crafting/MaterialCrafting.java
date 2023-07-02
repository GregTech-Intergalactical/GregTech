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

import static muramasa.antimatter.data.AntimatterDefaultTools.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Copper;
import static muramasa.antimatter.data.AntimatterMaterials.Gold;
import static muramasa.gregtech.data.Materials.*;

public class MaterialCrafting {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        addShapelessDustRecipe(output, provider, "bronze_dust", DUST.get(Bronze, 4), DUST.get(Copper), DUST.get(Copper), DUST.get(Copper), DUST.get(Tin));
        addShapelessDustRecipe(output, provider, "brass_dust", DUST.get(Brass, 4), DUST.get(Copper), DUST.get(Copper), DUST.get(Copper), DUST.get(Zinc));
        addShapelessDustRecipe(output, provider, "black_bronze_dust", DUST.get(BlackSteel, 5), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Silver), DUST.getMaterialTag(Gold));
        addShapelessDustRecipe(output, provider, "black_steel_dust", DUST.get(BlackSteel, 5), DUST.getMaterialTag(Steel), DUST.getMaterialTag(Steel), DUST.getMaterialTag(Steel), DUST.getMaterialTag(BlackBronze), DUST.getMaterialTag(Nickel));
        addShapelessDustRecipe(output, provider, "rose_gold", DUST.get(RoseGold, 5), DUST.getMaterialTag(Gold), DUST.getMaterialTag(Gold), DUST.getMaterialTag(Gold), DUST.getMaterialTag(Gold), DUST.getMaterialTag(Copper));
        addShapelessDustRecipe(output, provider, "sterling_silver", DUST.get(SterlingSilver, 5), DUST.getMaterialTag(Silver), DUST.getMaterialTag(Silver), DUST.getMaterialTag(Silver), DUST.getMaterialTag(Silver), DUST.getMaterialTag(Copper));
        addShapelessDustRecipe(output, provider, "bismuth_bronze", DUST.get(BismuthBronze, 5), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Bismuth), DUST.getMaterialTag(Zinc));
        addShapelessDustRecipe(output, provider, "red_steel", DUST.get(RedSteel, 8), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel),
                DUST.getMaterialTag(Steel), DUST.getMaterialTag(Steel), DUST.getMaterialTag(BismuthBronze), DUST.getMaterialTag(SterlingSilver));
        addShapelessDustRecipe(output, provider, "blue_steel", DUST.get(BlueSteel, 8), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel),
                DUST.getMaterialTag(Steel), DUST.getMaterialTag(Steel), DUST.getMaterialTag(Brass), DUST.getMaterialTag(RoseGold));
        provider.shapeless(output, GTIRef.ID, "", "dusts", "has_mortor", provider.hasSafeItem(MORTAR.getTag()), AntimatterMaterialTypes.DUST_SMALL.get(Clay, 2), MORTAR.getTag(), Items.CLAY_BALL);
        loadAutoRecipes(output, provider);
    }

    public static void loadAutoRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){

    }

    private static void addShapelessDustRecipe(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, String recipeName, ItemStack outputItem, Object... inputs)
    {
        provider.shapeless(output, GTIRef.ID, recipeName, "misc", "has_wrench",
                provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag()), outputItem, inputs);
    }
}
