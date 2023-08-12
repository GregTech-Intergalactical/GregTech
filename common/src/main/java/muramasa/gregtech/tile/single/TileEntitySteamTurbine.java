package muramasa.gregtech.tile.single;

import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntitySteamTurbine extends TileEntityMachine<TileEntitySteamTurbine> {
    public TileEntitySteamTurbine(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            @Override
            public int consumedFluidPerOperation(IRecipe r) {
                return getEfficiency();
            }

            @Override
            protected long calculateGeneratorProduction(IRecipe r) {
                return r.getPower() * 3;
            }
        });
    }
}
