package muramasa.gti.loader.machines;

import muramasa.antimatter.item.ItemBasic;
import muramasa.gti.data.GregTechData;
import net.minecraft.item.ItemStack;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gti.data.RecipeMaps.PRESSING;

public class FormingPress {
    public static void init() {
        molds(GregTechData.MoldAnvil);
        molds(GregTechData.MoldBall);
        molds(GregTechData.MoldBlock);
        molds(GregTechData.MoldBottle);
        molds(GregTechData.MoldCoinage);
        molds(GregTechData.MoldGear);
        molds(GregTechData.MoldGearSmall);
        molds(GregTechData.MoldIngot);
        molds(GregTechData.MoldNugget);
        molds(GregTechData.MoldPlate);
    }
    private static void molds(ItemBasic mold){
        PRESSING.RB().ii(of(GregTechData.EmptyShape,1),of(mold,1)).io(new ItemStack(mold,1)).add(120,20);
    }
}
