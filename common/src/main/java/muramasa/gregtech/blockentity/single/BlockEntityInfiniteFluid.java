package muramasa.gregtech.blockentity.single;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.fluid.FluidTank;
import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.cover.CoverOutput;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

import static muramasa.gregtech.data.Materials.Steam;

public class BlockEntityInfiniteFluid extends BlockEntityMachine<BlockEntityInfiniteFluid> {

    @Override
    public void onFirstTick() {
        super.onFirstTick();
        coverHandler.ifPresent(c -> {
            ICover stack = c.getOutputCover();
            ((CoverOutput) stack).setEjects(true, false);
        });
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        coverHandler.ifPresent(c -> {
            if (c.get(c.getOutputFacing()) instanceof CoverOutput stack) {
                stack.manualOutput();
            }
        });
    }

    public BlockEntityInfiniteFluid(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        fluidHandler.set(() -> new InfiniteFluidHandler(this));
        // TODO
        /*
        interactHandler.setup((tile, tag) -> new MachineInteractHandler<BlockEntityMachine>(tile, tag) {
            @Override
            public boolean onInteract(PlayerEntity player, Hand hand, Direction side, @Nullable AntimatterToolType type) {
                if ((type == SCREWDRIVER || type == ELECTRIC_SCREWDRIVER) && hand == Hand.MAIN_HAND) {
                    energyHandler.ifPresent(h -> {
                        int amps = h.getOutputAmperage();
                        amps = (amps + 1) % amperage;
                        h.setOutputAmperage(amps);
                        // TODO: Replace by new TranslationTextComponent()
                        player.sendMessage(new StringTextComponent(h.getOutputVoltage() + "V@" + h.getOutputAmperage() + "Amp"));
                    });
                    return true;
                }
                return super.onInteract(player, hand, side, type);
            }
        });
         */

    }

    @Override
    public List<String> getInfo() {
        List<String> info = super.getInfo();
        energyHandler.ifPresent(h -> {
            info.add("Amperage Out: " + h.getOutputAmperage());
        });
        return info;
    }

    protected static class InfiniteFluidHandler extends MachineFluidHandler<BlockEntityInfiniteFluid> {

        public InfiniteFluidHandler(BlockEntityInfiniteFluid tile) {
            super(tile);
            tanks.put(FluidDirection.OUTPUT, FluidTanks.create(tile, SlotType.FL_OUT, b -> {
                b.tank(Integer.MAX_VALUE);
                return b;
            }));
            FluidTank tank = tanks.get(FluidDirection.OUTPUT).getTank(0);
            tank.setFluid(0, Steam.getGas(Integer.MAX_VALUE-1));
        }

        @Override
        public boolean canInput(FluidHolder fluid, Direction direction) {
            return false;
        }

        @Override
        public boolean canInput(Direction direction) {
            return false;
        }

        @Override
        public FluidHolder extractFluid(FluidHolder fluid, boolean simulate) {
            return fluid.copyHolder();
        }

        @Override
        public FluidHolder extractFluid(long toExtract, boolean simulate) {
            return Steam.getGas(toExtract);
        }
    }
}