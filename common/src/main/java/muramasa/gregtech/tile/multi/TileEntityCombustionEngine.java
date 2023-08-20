package muramasa.gregtech.tile.multi;

import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.TesseractGraphWrappers;

import java.util.List;

import static muramasa.gregtech.data.Materials.Lubricant;
import static muramasa.gregtech.data.Materials.Oxygen;

public class TileEntityCombustionEngine extends TileEntityMultiMachine<TileEntityCombustionEngine> {

    public TileEntityCombustionEngine(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new MachineRecipeHandler<>(this){

            int lubeTicker = 0;
            @Override
            protected boolean consumeGeneratorResources(boolean simulate) {
                boolean boostEU = fluidHandler.map(f -> f.drainInput(Oxygen.getGas(2), true).getFluidAmount() == 2 * TesseractGraphWrappers.dropletMultiplier).orElse(false);
                int fuelConsumption = (int) (boostEU ? (4096 / activeRecipe.getPower()) : (2048 / activeRecipe.getPower()));
                if (lubeTicker == 72 &&!fluidHandler.map(f -> f.drainInput(Lubricant.getLiquid(2), true).getFluidAmount() == 2 * TesseractGraphWrappers.dropletMultiplier).orElse(false))
                    return false;

                if (fluidHandler.map(f -> !f.consumeAndReturnInputs(List.of(activeRecipe.getInputFluids().get(0).copyMB(fuelConsumption)), simulate).isEmpty()).orElse(false)){
                    if (!simulate) {
                        fluidHandler.ifPresent(f -> {
                            f.drainInput(Oxygen.getGas(2), false);
                            if (lubeTicker == 72) f.drainInput(Lubricant.getLiquid(2), false);
                        });
                    }
                    energyHandler.ifPresent(e -> e.insertInternal(boostEU ? 4096 : 2048, simulate));
                    lubeTicker++;
                    if (lubeTicker > 72) lubeTicker = 0;
                    return true;
                }
                return false;
            }
        });
    }
}
