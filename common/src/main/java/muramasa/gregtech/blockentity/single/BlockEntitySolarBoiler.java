package muramasa.gregtech.blockentity.single;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.base.PlatformFluidHandler;
import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import tesseract.TesseractCapUtils;

import java.util.Optional;

import static muramasa.antimatter.machine.MachineState.ACTIVE;
import static muramasa.antimatter.machine.MachineState.IDLE;
import static muramasa.gregtech.data.Materials.DistilledWater;

public class BlockEntitySolarBoiler extends BlockEntityMachine<BlockEntitySolarBoiler> {

    public BlockEntitySolarBoiler(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        fluidHandler.set(() -> new LavaBoilerFluidHandler(this));
        recipeHandler.set(() -> new SolarBoilerRecipeHandler(this));
    }

    public int getHeat() {
        return recipeHandler.map(r -> (((SolarBoilerRecipeHandler) r).getHeat())).orElse(0);
    }

    public int getMaxHeat() {
        return recipeHandler.map(r -> (((SolarBoilerRecipeHandler) r).getMaxHeat())).orElse(0);
    }

    public boolean isAllowedToWork() {
        return recipeHandler.map(r -> (((SolarBoilerRecipeHandler) r).isAllowedToWork())).orElse(false);
    }

    public static class SolarBoilerRecipeHandler extends MachineRecipeHandler<BlockEntitySolarBoiler> implements ISteamBoilerHandler {
        int maxHeat, heat, lossTimer = 0;
        boolean hadNoWater;

        public SolarBoilerRecipeHandler(BlockEntitySolarBoiler tile) {
            super(tile);
            maxHeat = 1000;
        }

        @Override
        public int getProcessDelay() {
            return 25;
        }

        @Override
        public int getLossTimerMax() {
            return 45;
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
            exportFluidFromMachineToSide(this.tile.getFacing());
        }

        public int getMaxHeat() {
            return maxHeat;
        }

        private boolean isAllowedToWork()
        {
            // Must be daytime, clear weather and top of block must be able to see the sky
            return tile.getLevel().canSeeSky(tile.getBlockPos().above()) &&
                    tile.getLevel().isDay() &&
                    !tile.getLevel().isRainingAt(tile.getBlockPos().above()) &&
                    !tile.getLevel().isThundering();
        }

        @Override
        public void onServerUpdate() {
            tick();
            if (tile.getLevel().getGameTime() % 12L == 0L) {
                if(isAllowedToWork()) {
                    this.heat += 1;

                    if(this.heat >= this.maxHeat) {
                        this.heat = this.maxHeat;
                    }
                    this.setActive(true);
                } else {
                    this.setActive(false);
                }
            }
            super.onServerUpdate();
        }

        public void exportFluidFromMachineToSide(Direction side){
            Optional<PlatformFluidHandler> cap = TesseractCapUtils.getFluidHandler(tile.getLevel(), tile.getBlockPos().relative(side), side.getOpposite());
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
                    || fluid.getFluid() == DistilledWater.getLiquid();
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
            nbt.putInt("lossTimer", lossTimer);
            nbt.putBoolean("hadNoWater", hadNoWater);
            return nbt;
        }

        @Override
        public void deserialize(CompoundTag nbt) {
            super.deserialize(nbt);
            this.heat = nbt.getInt("heat");
            this.maxHeat = nbt.getInt("maxHeat");
            this.lossTimer = nbt.getInt("lossTimer");
            this.hadNoWater = nbt.getBoolean("hadNoWater");
        }
    }

    public static class LavaBoilerFluidHandler extends MachineFluidHandler<BlockEntitySolarBoiler> {
        public LavaBoilerFluidHandler(BlockEntitySolarBoiler tile) {
            super(tile, 16000, 1000 * (250 + tile.getMachineTier().getIntegerId()));
            tanks.put(FluidDirection.INPUT, FluidTanks.create(tile, SlotType.FL_IN, b -> {
                b.tank(p -> p.getFluid() == Fluids.WATER || p.getFluid() == DistilledWater.getLiquid(), 16000);
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