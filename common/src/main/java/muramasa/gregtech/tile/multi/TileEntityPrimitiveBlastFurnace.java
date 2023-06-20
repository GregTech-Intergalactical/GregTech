package muramasa.gregtech.tile.multi;

import muramasa.antimatter.capability.machine.CookingRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.tile.multi.TileEntityBasicMultiMachine;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.int3;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Collections;
import java.util.List;

public class TileEntityPrimitiveBlastFurnace extends TileEntityBasicMultiMachine<TileEntityPrimitiveBlastFurnace> {

    public TileEntityPrimitiveBlastFurnace(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new CookingRecipeHandler<>(this){
            private boolean consume(boolean simulate) {
                List<ItemStack> stack;
                if (simulate) {
                    stack = tile.itemHandler.map(t -> t.consumeInputs(BURNABLE.get(), true)).orElse(Collections.emptyList());
                    return !stack.isEmpty();
                }
                if (!(stack = tile.itemHandler.map(t -> t.consumeInputs(BURNABLE.get(), false)).orElse(Collections.emptyList())).isEmpty()) {
                    burnDuration += AntimatterPlatformUtils.getBurnTime(stack.get(0), null)* 2;
                    return true;
                }
                return false;
            }

            @Override
            public boolean consumeResourceForRecipe(boolean simulate) {
                if (simulate) return consume(true);
                if (burnDuration == 0) {
                    if (!consume(false)) return false;
                } else {
                    burnDuration--;
                    return burnDuration >= 0;
                }
                return burnDuration > 0;
            }
        });
    }

    @Override
    public void onMachineStarted(IRecipe r) {
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
