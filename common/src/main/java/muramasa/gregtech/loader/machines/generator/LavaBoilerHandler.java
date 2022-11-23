package muramasa.gregtech.loader.machines.generator;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.gregtech.data.RecipeMaps.LAVA_BOILERS;

public class LavaBoilerHandler {
    public static void init(){
        LAVA_BOILERS.RB().ii(RecipeIngredient.of(Items.LAVA_BUCKET,1)).io(new ItemStack(Items.BUCKET,1)).add(1000);
    }
}
