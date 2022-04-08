package muramasa.gregtech.tile.multi;

import muramasa.antimatter.capability.IComponentHandler;
import muramasa.antimatter.capability.IHeatHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;

import java.util.Collections;
import java.util.List;

public class TileEntityHeatExchanger extends TileEntityMultiMachine<TileEntityHeatExchanger> {

    protected List<IHeatHandler> HEAT_HANDLERS;


    public TileEntityHeatExchanger(Machine type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new MachineRecipeHandler<>(this) {
            @Override
            public boolean consumeResourceForRecipe(boolean simulate) {
                Recipe r = activeRecipe;
                if (simulate) return TileEntityHeatExchanger.this.HEAT_HANDLERS.stream().mapToInt(IHeatHandler::getHeat).sum() >= r.getPower();
                int[] count = new int[1];
                count[0] = (int) r.getPower();
                for (IHeatHandler heat_handler : TileEntityHeatExchanger.this.HEAT_HANDLERS) {
                    var txn = heat_handler.extract();
                    txn.addData(count[0], -1, a -> count[0] -= a);
                    txn.commit();
                    if (count[0] == 0) break;
                }
                return true;
            }
        });
    }
}
