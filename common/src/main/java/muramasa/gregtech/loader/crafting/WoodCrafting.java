package muramasa.gregtech.loader.crafting;

import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.GregTechBlocks;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;

public class WoodCrafting {

    public static void loadRecipes(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider) {
        provider.addItemRecipe(consumer, GTIRef.ID, "tiny_wooden_fluid_pipe", "pipes",
                GregTechBlocks.FLUID_PIPE_WOOD.getBlockItem(PipeSize.TINY), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.WOODEN_SLABS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", " s ", "H  ");
        provider.addItemRecipe(consumer, GTIRef.ID, "small_wooden_fluid_pipe", "pipes",
                GregTechBlocks.FLUID_PIPE_WOOD.getBlockItem(PipeSize.SMALL), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", " s ", "H  ");
        provider.addItemRecipe(consumer, GTIRef.ID, "normal_wooden_fluid_pipe", "pipes",
                GregTechBlocks.FLUID_PIPE_WOOD.getBlockItem(PipeSize.NORMAL), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", "sss", "H  ");
        provider.addItemRecipe(consumer, GTIRef.ID, "large_wooden_fluid_pipe", "pipes",
                GregTechBlocks.FLUID_PIPE_WOOD.getBlockItem(PipeSize.LARGE), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "ssS", "s s", "Hss");
        provider.addItemRecipe(consumer, GTIRef.ID, "huge_wooden_fluid_pipe", "pipes",
                GregTechBlocks.FLUID_PIPE_WOOD.getBlockItem(PipeSize.HUGE), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.LOGS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", "s s", "H  ");
    }
}
