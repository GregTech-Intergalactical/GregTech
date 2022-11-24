package muramasa.gregtech.tile.single;

import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.Arrays;
import java.util.stream.Collectors;

import static muramasa.antimatter.machine.MachineState.ACTIVE;
import static muramasa.antimatter.machine.MachineState.IDLE;
import static muramasa.gregtech.data.Materials.DistilledWater;
import static muramasa.gregtech.data.Materials.Steam;

public class TileEntitySolarBoiler extends TileEntityMachine<TileEntitySolarBoiler> {

    public TileEntitySolarBoiler(Machine<?> type, BlockPos pos, BlockState state) {
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

    public static class SolarBoilerRecipeHandler extends MachineRecipeHandler<TileEntitySolarBoiler> {
        int maxHeat, heat, lossTimer = 0;
        boolean hadNoWater;

        public SolarBoilerRecipeHandler(TileEntitySolarBoiler tile) {
            super(tile);
            maxHeat = 1000;
        }

        public int getHeat() {
            return heat;
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
            Arrays.stream(Direction.values()).filter(f -> f != Direction.DOWN).collect(Collectors.toList()).forEach(this::exportFluidFromMachineToSide);

            if (tile.getLevel().getGameTime() % 10L == 0L) {
                tile.fluidHandler.ifPresent(f -> {
                    FluidStack[] inputs = f.getInputs();

                    // Produce heat if we can see the sky
                    if(isAllowedToWork())
                    {
                        this.heat += 1;

                        if(this.heat >= this.maxHeat) {
                            this.heat = this.maxHeat;
                        }
                    }
                    else {
                        // Start to cool down if we can't see the sky anymore
                        if (++this.lossTimer > 20 && this.heat > 0) {
                            this.heat -= 1;
                            this.lossTimer = 0;
                        }
                    }

                    // If heat is above 100, and we have water then produce steam otherwise explode
                    if (this.heat > 100) {
                        if (inputs[0].getAmount() == 0) {
                            hadNoWater = true;
                        } else {
                            if (hadNoWater) {
                                tile.getLevel().explode(null, tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ(), 4.0F, Explosion.BlockInteraction.DESTROY);
                                tile.getLevel().setBlockAndUpdate(tile.getBlockPos(), Blocks.AIR.defaultBlockState());
                                return;
                            }
                            f.drainInput(new FluidStack(inputs[0].getFluid(), 1), IFluidHandler.FluidAction.EXECUTE);

                            int room = 16000 - f.getOutputs()[0].getAmount();
                            int fill = Math.min(room, 150);
                            if (room > 0){
                                f.fillOutput(Steam.getGas(fill), IFluidHandler.FluidAction.EXECUTE);
                            }
                            if (fill < 150) {
                                //TODO:steam sounds
                                tile.getLevel().playSound(null, tile.getBlockPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 1.0f);
                                if (tile.getLevel() instanceof ServerLevel)
                                    ((ServerLevel) tile.getLevel()).sendParticles(ParticleTypes.SMOKE, tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ(), tile.getLevel().getRandom().nextInt(8) + 1, 0.0D, 0.2D, 0.0D, 0.0D);
                                f.drain(4000, IFluidHandler.FluidAction.EXECUTE);
                            }
                        }
                    } else {
                        this.hadNoWater = false;
                    }
                });
            }

            super.onServerUpdate();
        }

        public void exportFluidFromMachineToSide(Direction side){
            BlockEntity adjTile = tile.getLevel().getBlockEntity(tile.getBlockPos().relative(side));
            if (adjTile == null) return;
            LazyOptional<IFluidHandler> cap = adjTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side.getOpposite());
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
        public boolean accepts(FluidStack fluid) {
            return fluid.getFluid() == Fluids.WATER
                    || fluid.getFluid() == DistilledWater.getLiquid();
        }

        @Override
        public boolean canOutput() {
            return true;
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag nbt = super.serializeNBT();
            nbt.putInt("heat", heat);
            nbt.putInt("maxHeat", maxHeat);
            nbt.putInt("lossTimer", lossTimer);
            nbt.putBoolean("hadNoWater", hadNoWater);
            return nbt;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            super.deserializeNBT(nbt);
            this.heat = nbt.getInt("heat");
            this.maxHeat = nbt.getInt("maxHeat");
            this.lossTimer = nbt.getInt("lossTimer");
            this.hadNoWater = nbt.getBoolean("hadNoWater");
        }
    }

    public static class LavaBoilerFluidHandler extends MachineFluidHandler<TileEntitySolarBoiler> {
        public LavaBoilerFluidHandler(TileEntitySolarBoiler tile) {
            super(tile, 16000, 1000 * (250 + tile.getMachineTier().getIntegerId()));
            tanks.put(FluidDirection.INPUT, FluidTanks.create(tile, ContentEvent.FLUID_INPUT_CHANGED, b -> {
                b.tank(p -> p.getFluid() == Fluids.WATER || p.getFluid() == DistilledWater.getLiquid(), 16000);
                return b;
            }));
            tanks.put(FluidDirection.OUTPUT, FluidTanks.create(tile, ContentEvent.FLUID_OUTPUT_CHANGED, b -> {
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