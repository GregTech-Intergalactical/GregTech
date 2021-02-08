package muramasa.gti.loader.machines;

import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.ForgeRegistries;

import static muramasa.gti.data.RecipeMaps.FLUID_CANNING;

public class FluidCanningLoader {
    public static void init() {
        ForgeRegistries.FLUIDS.forEach(fluid -> {
            Item bucket = fluid.getFilledBucket();
            if (bucket == Items.AIR) return;
            //Only the source.
            if (fluid instanceof ForgeFlowingFluid.Flowing) return;
            FLUID_CANNING.RB().ii(AntimatterIngredient.of(bucket,1)).fo(new FluidStack(fluid,1000)).add(20, 8);
        });
    }
}
