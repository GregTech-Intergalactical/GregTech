package muramasa.gti.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.gti.data.GregTechData;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.function.Consumer;

import static muramasa.antimatter.Data.*;
import static muramasa.gti.data.GregTechData.CASING_FIRE_BRICK;
import static muramasa.gti.data.GregTechData.Firebrick;
import static muramasa.gti.data.Materials.Brick;

public class Miscellaneous {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.addStackRecipe(output, "casings", "firebrick_casing", "has_wrench", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), new ItemStack(CASING_FIRE_BRICK, 3),
                ImmutableMap.of('S', Items.SMOOTH_STONE, 'F', Firebrick, 'P', PLATE.get(Iron))
                , "FSF", "SPS", "FSF");

        provider.addStackRecipe(output, "dusts", "firelay", "has_wrench", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), new ItemStack(GregTechData.Fireclay, 2),
                ImmutableMap.of('C', Items.CLAY_BALL, 'B', DUST.get(Brick))
                , "CB ", "   ", "   ");
    }
}