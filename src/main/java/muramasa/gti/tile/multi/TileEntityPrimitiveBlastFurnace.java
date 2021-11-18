package muramasa.gti.tile.multi;

import muramasa.antimatter.capability.machine.CookingRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.tile.multi.TileEntityBasicMultiMachine;
import muramasa.antimatter.util.int3;
import net.minecraft.block.Blocks;

public class TileEntityPrimitiveBlastFurnace extends TileEntityBasicMultiMachine<TileEntityPrimitiveBlastFurnace> {

    public TileEntityPrimitiveBlastFurnace(Machine type) {
        super(type);
        recipeHandler.set(() -> new CookingRecipeHandler<>(this));
    }

    @Override
    public void onMachineStarted(Recipe r) {
        super.onMachineStarted(r);
        int3 controller = new int3(getBlockPos(), getFacing());
        controller.back(1);
        getLevel().setBlock(controller, Blocks.LAVA.defaultBlockState(), 2);
        controller.above(1);
        getLevel().setBlock(controller, Blocks.LAVA.defaultBlockState(), 2);
    }

    @Override
    public void onMachineStop() {
        super.onMachineStop();
        int3 controller = new int3(getBlockPos(), getFacing());
        controller.back(1);
        getLevel().setBlock(controller, Blocks.AIR.defaultBlockState(), 2);
        controller.above(1);
        getLevel().setBlock(controller, Blocks.AIR.defaultBlockState(), 2);
    }
}
