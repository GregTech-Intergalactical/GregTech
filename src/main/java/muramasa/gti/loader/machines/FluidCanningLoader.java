package muramasa.gti.loader.machines;

import muramasa.antimatter.Data;
import muramasa.antimatter.item.ItemFluidCell;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import static muramasa.gti.data.RecipeMaps.FLUID_CANNING;

public class FluidCanningLoader {
    public static void init() {
        ForgeRegistries.FLUIDS.forEach(fluid -> {
            Item bucket = fluid.getBucket();
            if (bucket == Items.AIR) return;
            //Only the source, so we don't get duplicates.
            if (!fluid.isSource(fluid.defaultFluidState())) return;
            FLUID_CANNING.RB().ii(RecipeIngredient.of(bucket, 1)).fo(new FluidStack(fluid, 1000)).io(Items.BUCKET.getDefaultInstance()).add(20, 8);
            FLUID_CANNING.RB().ii(RecipeIngredient.of(Items.BUCKET, 1)).fi(new FluidStack(fluid, 1000)).io(new ItemStack(bucket, 1)).add(20, 8);

            for (ItemFluidCell emptyCell : Data.EMPTY_CELLS) {
                int size = emptyCell.getCapacity();
                ItemStack filled = emptyCell.fill(fluid, size);
                FLUID_CANNING.RB().ii(RecipeIngredient.of(filled)).fo(new FluidStack(fluid, size)).io(emptyCell.getDefaultInstance()).add(20, 8);
                FLUID_CANNING.RB().ii(RecipeIngredient.of(emptyCell, 1)).fi(new FluidStack(fluid, size)).io(filled).add(20, 8);
            }
        });
    }
}
