package muramasa.gregtech.loader.machines;

import muramasa.antimatter.item.ItemBasic;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.world.item.ItemStack;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.RecipeMaps.PRESSING;

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

        molds(GregTechData.ShapeBolt);
        molds(GregTechData.ShapeGear);
        molds(GregTechData.ShapeGearSmall);
        molds(GregTechData.ShapeRing);
        molds(GregTechData.ShapeRod);
        molds(GregTechData.ShapePipeHuge);
        molds(GregTechData.ShapePipeLarge);
        molds(GregTechData.ShapePipeNormal);
        molds(GregTechData.ShapePipeSmall);
        molds(GregTechData.ShapePipeTiny);
        molds(GregTechData.ShapeWire);
        molds(GregTechData.ShapePlate);
    }
    private static void molds(ItemBasic mold){
        PRESSING.RB().ii(of(GregTechData.EmptyShape,1),of(mold,1).setNoConsume()).io(new ItemStack(mold,1)).add(120,20);
    }
}
