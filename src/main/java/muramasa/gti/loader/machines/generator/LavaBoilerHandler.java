package muramasa.gti.loader.machines.generator;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fluids.FluidStack;
import static muramasa.gti.data.RecipeMaps.LAVA_BOILERS;

public class LavaBoilerHandler {
    public static void init(){
        LAVA_BOILERS.RB().ii(RecipeIngredient.of(Items.LAVA_BUCKET,1)).io(new ItemStack(Items.BUCKET)).add(1000);
    }
}
