package muramasa.gregtech.blockentity.single;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.base.PlatformFluidHandler;
import muramasa.antimatter.blockentity.BlockEntityCache;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractCapUtils;
import tesseract.TesseractGraphWrappers;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static muramasa.antimatter.machine.MachineState.ACTIVE;
import static muramasa.antimatter.machine.MachineState.IDLE;
import static muramasa.gregtech.data.Materials.DistilledWater;

public class BlockEntityLavaBoiler extends BlockEntityMachine<BlockEntityLavaBoiler> {

    public BlockEntityLavaBoiler(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        fluidHandler.set(() -> new LavaBoilerFluidHandler(this));
        recipeHandler.set(() -> new LavaBoilerRecipeHandler(this));
    }

    public int getHeat() {
        return recipeHandler.map(r -> (((LavaBoilerRecipeHandler) r).getHeat())).orElse(0);
    }

    public int getMaxHeat() {
        return recipeHandler.map(r -> (((LavaBoilerRecipeHandler) r).getMaxHeat())).orElse(0);
    }

    public static class LavaBoilerRecipeHandler extends MachineRecipeHandler<BlockEntityLavaBoiler> implements ISteamBoilerHandler {
        int maxHeat, heat, fuel = 0, maxFuel, lossTimer = 0, lavaPerOperation = 100;
        boolean hadNoWater;

        public LavaBoilerRecipeHandler(BlockEntityLavaBoiler tile) {
            super(tile);
            maxHeat = 1000;
        }

        @Override
        public int getProcessDelay() {
            return 10;
        }

        @Override
        public int getLossTimerMax() {
            return 20;
        }

        public int getHeat() {
            return heat;
        }

        @Override
        public void setHeat(int heat) {
            this.heat = heat;
        }

        @Override
        public int getLossTimer() {
            return lossTimer;
        }

        @Override
        public void setLossTimer(int lossTimer) {
            this.lossTimer = lossTimer;
        }

        @Override
        public boolean hadNoWater() {
            return hadNoWater;
        }

        @Override
        public void setHadNoWater(boolean hadNoWater) {
            this.hadNoWater = hadNoWater;
        }

        @Override
        public BlockEntityMachine<?> getTile() {
            return tile;
        }

        @Override
        public void exportFluid() {
            if (tile.fluidHandler.map(f -> f.getOutputTanks().isEmpty()).orElse(false)) return;
            Arrays.stream(Direction.values()).filter(f -> f != Direction.DOWN).collect(Collectors.toList()).forEach(this::exportFluidFromMachineToSide);
        }

        public int getMaxHeat() {
            return maxHeat;
        }

        @Override
        public void onServerUpdate() {

            tick();
            if (tile.getLevel().getGameTime() % 10L == 0L) {
                tile.fluidHandler.ifPresent(f -> {
                    FluidHolder[] inputs = f.getInputs();

                    // If we have lava then produce heat
                    if(inputs[1].getFluidAmount() >= lavaPerOperation * TesseractGraphWrappers.dropletMultiplier) {
                        setActive(true);
                        this.heat += 1;

                        // Gets approximately 77 heat per lava bucket
                        if(tile.getLevel().getGameTime() % 16L == 0L) {
                            f.drainInput(FluidPlatformUtils.createFluidStack(inputs[1].getFluid(), lavaPerOperation), false);
                        }

                        if(this.heat >= this.maxHeat) {
                            this.heat = this.maxHeat;
                        }
                    } else {
                        setActive(false);
                    }

                });
            }

            super.onServerUpdate();
        }

        public void exportFluidFromMachineToSide(Direction side){
            if (tile.fluidHandler.map(f -> f.getOutputTanks().isEmpty()).orElse(false)) return;
            Optional<PlatformFluidHandler> cap = BlockEntityCache.getFluidHandlerCached(tile.getLevel(), tile.getBlockPos().relative(side), side.getOpposite());
            tile.fluidHandler.ifPresent(f -> cap.ifPresent(other -> Utils.transferFluids(f.getOutputTanks(), other, 1000)));
        }

        public void setActive(boolean t){
            if (t && tile.getMachineState() != ACTIVE){
                tile.setMachineState(ACTIVE);
            } else if (!t && tile.getMachineState() == ACTIVE){
                tile.setMachineState(IDLE);
            }
        }

        @Override
        public boolean accepts(FluidHolder fluid) {
            return fluid.getFluid() == Fluids.WATER
                    || fluid.getFluid() == DistilledWater.getLiquid()
                    || fluid.getFluid() == Fluids.LAVA;
        }

        @Override
        public boolean canOutput() {
            return true;
        }

        @Override
        public CompoundTag serialize() {
            CompoundTag nbt = super.serialize();
            nbt.putInt("heat", heat);
            nbt.putInt("maxHeat", maxHeat);
            nbt.putInt("fuel", fuel);
            nbt.putInt("maxFuel", maxFuel);
            nbt.putInt("lossTimer", lossTimer);
            nbt.putBoolean("hadNoWater", hadNoWater);
            return nbt;
        }

        @Override
        public void deserialize(CompoundTag nbt) {
            super.deserialize(nbt);
            this.heat = nbt.getInt("heat");
            this.maxHeat = nbt.getInt("maxHeat");
            this.fuel = nbt.getInt("fuel");
            this.maxFuel = nbt.getInt("maxFuel");
            this.lossTimer = nbt.getInt("lossTimer");
            this.hadNoWater = nbt.getBoolean("hadNoWater");
        }
    }

    public static class LavaBoilerFluidHandler extends MachineFluidHandler<BlockEntityLavaBoiler> {
        public LavaBoilerFluidHandler(BlockEntityLavaBoiler tile) {
            super(tile, 16000, 1000 * (250 + tile.getMachineTier().getIntegerId()));
            tanks.put(FluidDirection.INPUT, FluidTanks.create(tile, SlotType.FL_IN, b -> {
                b.tank(p -> p.getFluid() == Fluids.WATER || p.getFluid() == DistilledWater.getLiquid(), 16000)
                        .tank(p -> p.getFluid() == Fluids.LAVA, 16000);
                return b;
            }));
            tanks.put(FluidDirection.OUTPUT, FluidTanks.create(tile, SlotType.FL_OUT, b -> {
                b.tank(16000);
                return b;
            }));
        }

        @Override
        protected FluidTanks getCellAccessibleTanks() {
            return getInputTanks();
        }
    }
}