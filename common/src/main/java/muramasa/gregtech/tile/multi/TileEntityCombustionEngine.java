package muramasa.gregtech.tile.multi;

import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.TesseractGraphWrappers;

import java.util.List;

import static muramasa.gregtech.data.Materials.Lubricant;
import static muramasa.gregtech.data.Materials.Oxygen;

public class TileEntityCombustionEngine extends TileEntityMultiMachine<TileEntityCombustionEngine> {

    long lastEu = 0;

    public TileEntityCombustionEngine(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new MachineRecipeHandler<>(this){

            int lubeTicker = 0;
            int startup = 0;
            @Override
            protected boolean consumeGeneratorResources(boolean simulate) {
                boolean boostEU = fluidHandler.map(f -> f.drainInput(Oxygen.getGas(2), true).getFluidAmount() == 2 * TesseractGraphWrappers.dropletMultiplier).orElse(false);
                int fuelConsumption = (int) (boostEU ? (4096 / activeRecipe.getPower()) : (2048 / activeRecipe.getPower()));
                int lubeConsume = boostEU ? 2 : 1;
                if ((lubeTicker == 72 || simulate) &&!fluidHandler.map(f -> f.drainInput(Lubricant.getLiquid(lubeConsume), true).getFluidAmount() == lubeConsume * TesseractGraphWrappers.dropletMultiplier).orElse(false)) {
                    if (!simulate && startup > 0) startup = 0;
                    return false;
                }

                if (fluidHandler.map(f -> !f.consumeAndReturnInputs(List.of(activeRecipe.getInputFluids().get(0).copyMB(fuelConsumption)), simulate).isEmpty()).orElse(false)){
                    if (!simulate) {
                        fluidHandler.ifPresent(f -> {
                            f.drainInput(Oxygen.getGas(2), false);
                            if (lubeTicker == 72) f.drainInput(Lubricant.getLiquid(lubeConsume), false);
                        });
                        long euPerTick = boostEU ? 6144 : 2048;
                        lastEu = startup < 2000 ? 0 : euPerTick * (startup / 10000);
                        energyHandler.ifPresent(e -> e.insertInternal(lastEu, simulate));
                        if (startup < 10000){
                            startup += 15;
                            if (startup > 10000) startup = 10000;
                        }
                        lubeTicker++;
                        if (lubeTicker > 72) lubeTicker = 0;
                    }
                    return true;
                }
                if (!simulate && startup > 0) startup = 0;
                return false;
            }

            @Override
            public CompoundTag serialize() {
                CompoundTag nbt = super.serialize();
                nbt.putInt("startup", startup);
                nbt.putInt("lubeTicker", lubeTicker);
                return nbt;
            }

            @Override
            public void deserialize(CompoundTag nbt) {
                super.deserialize(nbt);
                startup = nbt.getInt("startup");
                lubeTicker = nbt.getInt("lubeTicker");
            }
        });
    }
}
