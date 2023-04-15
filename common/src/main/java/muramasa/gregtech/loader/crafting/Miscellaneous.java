package muramasa.gregtech.loader.crafting;

import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.GTIRef;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

import static com.github.gregtechintergalactical.gtrubber.GTRubberData.StickyResin;
import static com.google.common.collect.ImmutableMap.of;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.*;

public class Miscellaneous {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.addItemRecipe(output, GTIRef.ID, "plantball", "misc", "has_wrench", provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag()), PlantBall, of(
                'C', TagUtils.getForgelikeItemTag("crops")
        ), "CCC", "C C", "CCC");

        provider.addItemRecipe(output, GTIRef.ID, "plantball2", "misc", "has_wrench", provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag()), PlantBall, of(
                'S', TagUtils.getForgelikeItemTag("seeds")
        ), "SSS", "S S", "SSS");

        provider.addStackRecipe(output, GTIRef.ID, "stickyresin", "misc", "has_wrench", provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag()), new ItemStack(StickyResin,8), of(
                'P', PlantBall,
                'D', AntimatterMaterialTypes.DUST.get(RawRubber)
        ), "PPP", "PDP", "PPP");
    }
}