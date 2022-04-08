package muramasa.gregtech.loader.machines;

import muramasa.gregtech.data.GregTechData;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.Materials.RawRubber;
import static muramasa.gregtech.data.RecipeMaps.EXTRACTING;

public class ExtractorLoader {
    public static void init() {
        EXTRACTING.RB().ii(of(GregTechData.RUBBER_LOG.asItem(),1)).io(new ItemStack(DUST.get(RawRubber),2)).add(200,8);
        EXTRACTING.RB().ii(of(GregTechData.RUBBER_LEAVES.asItem(),1)).io(new ItemStack(DUST.get(RawRubber))).add(150,8);
        EXTRACTING.RB().ii(of(GregTechData.RUBBER_SAPLING.asItem(),1)).io(new ItemStack(DUST.get(RawRubber))).add(150,8);
        EXTRACTING.RB().ii(of(GregTechData.StickyResin,1)).io(new ItemStack(DUST.get(RawRubber),4)).add(200,5);
    }
}
