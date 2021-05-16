package muramasa.gti.tile.multi;

import muramasa.antimatter.capability.machine.CookingRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.tile.multi.TileEntityBasicMultiMachine;
import muramasa.antimatter.util.int3;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.util.LazyOptional;

public class TileEntityPrimitiveBlastFurnace extends TileEntityBasicMultiMachine {

    public TileEntityPrimitiveBlastFurnace(Machine type) {
        super(type);
        this.recipeHandler = LazyOptional.of(() -> new CookingRecipeHandler<>(this));
    }

    @Override
    public void onRecipeActivated(Recipe r) {
        super.onRecipeActivated(r);
        int3 controller = new int3(getPos(), getFacing());
        controller.back(1);
        getWorld().setBlockState(controller, Blocks.LAVA.getDefaultState(), 2);
        controller.up(1);
        getWorld().setBlockState(controller, Blocks.LAVA.getDefaultState(), 2);
    }

    @Override
    public void onRecipeStop() {
        super.onRecipeStop();
        int3 controller = new int3(getPos(), getFacing());
        controller.back(1);
        getWorld().setBlockState(controller, Blocks.AIR.getDefaultState(), 2);
        controller.up(1);
        getWorld().setBlockState(controller, Blocks.AIR.getDefaultState(), 2);
    }
}
