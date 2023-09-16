package muramasa.gregtech.blockentity.multi;

import muramasa.antimatter.capability.machine.CookingRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.blockentity.multi.BlockEntityBasicMultiMachine;
import muramasa.antimatter.util.int3;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityPrimitiveBlastFurnace extends BlockEntityBasicMultiMachine<BlockEntityPrimitiveBlastFurnace> {

    public BlockEntityPrimitiveBlastFurnace(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new CookingRecipeHandler<>(this, 2.0f));
    }

    @Override
    public void onMachineStarted(IRecipe r) {
        super.onMachineStarted(r);
        int3 controller = new int3(getBlockPos(), getFacing());
        controller.back(1);
        getLevel().setBlock(controller, GregTechData.LAVA.defaultBlockState(), 2);
        controller.above(1);
        getLevel().setBlock(controller, GregTechData.LAVA.defaultBlockState(), 2);
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
