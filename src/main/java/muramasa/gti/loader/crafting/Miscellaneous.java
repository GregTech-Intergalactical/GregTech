package muramasa.gti.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.gti.data.GregTechData;
import muramasa.gti.data.Materials;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.function.Consumer;

import static muramasa.antimatter.Data.*;
import static muramasa.gti.data.GregTechData.*;
import static muramasa.gti.data.Materials.Brick;
import static muramasa.gti.data.Materials.Clay;

public class Miscellaneous {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.addStackRecipe(output, "gti", "firebrick_casing", "casings", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), new ItemStack(CASING_FIRE_BRICK, 3),
                ImmutableMap.of('S', Items.SMOOTH_STONE, 'F', Firebrick, 'P', PLATE.get(Iron))
                , "FSF", "SPS", "FSF");

        provider.shapeless(output, "fireclay", "misc", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),
                new ItemStack(DUST.get(Materials.Fireclay),2),DUST.get(Clay), DUST.get(Brick));

        provider.shapeless(output, "clay_dust", "misc", "has_mortar", provider.hasSafeItem(MORTAR.getTag()),
                new ItemStack(DUST.get(Clay)),MORTAR.getTag(),Items.CLAY_BALL);

        provider.shapeless(output, "brick_dust", "misc", "has_mortar", provider.hasSafeItem(MORTAR.getTag()),
                new ItemStack(DUST.get(Brick)),MORTAR.getTag(),Items.BRICK);
    }
}