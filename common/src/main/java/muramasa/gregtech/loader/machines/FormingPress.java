package muramasa.gregtech.loader.machines;

import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.item.ItemBasic;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.world.item.ItemStack;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.RecipeMaps.PRESSING;

public class FormingPress {
    public static void init() {
        molds(GTCoreItems.MoldAnvil);
        molds(GTCoreItems.MoldBall);
        molds(GTCoreItems.MoldBlock);
        molds(GTCoreItems.MoldBottle);
        molds(GTCoreItems.MoldCoinage);
        molds(GTCoreItems.MoldGear);
        molds(GTCoreItems.MoldGearSmall);
        molds(GTCoreItems.MoldIngot);
        molds(GTCoreItems.MoldNugget);
        molds(GTCoreItems.MoldPlate);

        molds(GTCoreItems.ShapeBolt);
        molds(GTCoreItems.ShapeGear);
        molds(GTCoreItems.ShapeGearSmall);
        molds(GTCoreItems.ShapeRing);
        molds(GTCoreItems.ShapeRod);
        molds(GTCoreItems.ShapePipeHuge);
        molds(GTCoreItems.ShapePipeLarge);
        molds(GTCoreItems.ShapePipeNormal);
        molds(GTCoreItems.ShapePipeSmall);
        molds(GTCoreItems.ShapePipeTiny);
        molds(GTCoreItems.ShapeWire);
        molds(GTCoreItems.ShapePlate);
    }
    private static void molds(ItemBasic mold){
        PRESSING.RB().ii(of(GTCoreItems.EmptyShape,1),of(mold,1).setNoConsume()).io(new ItemStack(mold,1)).add(mold.getId(),120,20);
    }
}
