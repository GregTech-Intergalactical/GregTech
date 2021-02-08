package muramasa.gti.loader.machines;

import muramasa.gti.data.GregTechData;
import net.minecraft.item.ItemStack;

import static muramasa.antimatter.Data.DUST;
import static muramasa.gti.data.Materials.RawRubber;
import static muramasa.gti.data.RecipeMaps.EXTRACTING;
import static muramasa.antimatter.recipe.ingredient.AntimatterIngredient.*;

public class ExtractorLoader {
    public static void init() {
        EXTRACTING.RB().ii(of(GregTechData.RUBBER_LOG.asItem(),1)).io(new ItemStack(DUST.get(RawRubber),2)).add(200,8);
        EXTRACTING.RB().ii(of(GregTechData.RUBBER_LEAVES.asItem(),1)).io(new ItemStack(DUST.get(RawRubber))).add(150,8);
        EXTRACTING.RB().ii(of(GregTechData.RUBBER_SAPLING.asItem(),1)).io(new ItemStack(DUST.get(RawRubber))).add(150,8);
    }
}
