package muramasa.gti.loader.machines;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import static muramasa.gti.data.RecipeMaps.FLUID_CANNING;

public class FluidCanningLoader {
    public static void init() {
        ForgeRegistries.FLUIDS.forEach(fluid -> {
            Item bucket = fluid.getFilledBucket();
            if (bucket == Items.AIR) return;
            //Only the source.
            if (fluid instanceof FlowingFluid) return;
            FLUID_CANNING.RB().ii(RecipeIngredient.of(bucket,1)).fo(new FluidStack(fluid,1000)).add(20, 8);
        });
    }
}
