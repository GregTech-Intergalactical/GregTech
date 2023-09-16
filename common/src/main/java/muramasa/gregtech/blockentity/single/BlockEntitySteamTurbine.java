package muramasa.gregtech.blockentity.single;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntitySteamTurbine extends BlockEntityMachine<BlockEntitySteamTurbine> {
    public BlockEntitySteamTurbine(Machine<?> type, BlockPos pos, BlockState state) {
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
