package muramasa.gregtech.tile.multi;

import muramasa.antimatter.Ref;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.block.BlockCoil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityElectricBlastFurnace extends TileEntityMultiMachine<TileEntityElectricBlastFurnace> {

    private int heatingCapacity;

    public TileEntityElectricBlastFurnace(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new MachineRecipeHandler<>(this) {

            @Override
            protected void calculateDurations() {
                super.calculateDurations();
                maxProgress = activeRecipe.getDuration();
                overclock = 0;
                //Divide by 2, for amps.
                int tier = Utils.getVoltageTier(getMaxInputVoltage() / 2);
                int recipeTier = Utils.getVoltageTier(activeRecipe.getPower());
                if (recipeTier == tier) {
                    EUt = activeRecipe.getPower();
                    return;
                }
                if (heatingCapacity >= activeRecipe.getSpecialValue()) {
                    int heatDiv = (heatingCapacity - activeRecipe.getSpecialValue()) / 900;
                    if (activeRecipe.getPower() <= 16) {
                        EUt = (activeRecipe.getPower() * (1L << tier - 1) * (1L << tier - 1));
                        maxProgress = (activeRecipe.getDuration() / (1 << tier - 1));
                    } else {
                        EUt = activeRecipe.getPower();
                        maxProgress = activeRecipe.getDuration();
                        for (int i = 2; i < Ref.V.length; i += 2) {
                            if (EUt > Ref.V[tier - 1]) break;
                            EUt *= 4;
                            maxProgress /= (heatDiv >= i ? 4 : 2);
                        }
                    }
                }
            }

            @Override
            public long getPower() {
                return EUt;
            }

            @Override
            protected boolean validateRecipe(IRecipe r) {
                return super.validateRecipe(r) && heatingCapacity > r.getSpecialValue();
            }
        });
    }

    @Override
    public boolean onStructureFormed() {
        super.onStructureFormed();
        heatingCapacity = getStates("coil").stream().mapToInt(s -> ((BlockCoil) s.getBlock()).getHeatCapacity()).sum();
        return true;
    }
}
