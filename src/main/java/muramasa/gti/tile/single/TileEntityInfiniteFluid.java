package muramasa.gti.tile.single;

import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.cover.CoverOutput;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.util.Direction;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import java.util.List;

import static muramasa.gti.data.Materials.Steam;

public class TileEntityInfiniteFluid extends TileEntityMachine<TileEntityInfiniteFluid> {

    @Override
    public void onFirstTick() {
        super.onFirstTick();
        coverHandler.ifPresent(c -> {
            ICover stack = c.getOutputCover();
            ((CoverOutput) stack).setEjects(true, false);
        });
    }

    @Override
    public void onServerUpdate() {
        super.onServerUpdate();
        coverHandler.ifPresent(c -> {
            ICover stack = c.get(c.getOutputFacing());
            ((CoverOutput) stack).manualOutput();
        });
    }

    public TileEntityInfiniteFluid(Machine<?> type) {
        super(type);
        fluidHandler.set(() -> new InfiniteFluidHandler(this));
        // TODO
        /*
        interactHandler.setup((tile, tag) -> new MachineInteractHandler<TileEntityMachine>(tile, tag) {
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

    protected static class InfiniteFluidHandler extends MachineFluidHandler<TileEntityInfiniteFluid> {

        public InfiniteFluidHandler(TileEntityInfiniteFluid tile) {
            super(tile);
            tanks.put(FluidDirection.OUTPUT, FluidTanks.create(tile, ContentEvent.FLUID_OUTPUT_CHANGED, b -> {
                b.tank(Integer.MAX_VALUE);
                return b;
            }));
            FluidTank tank = tanks.get(FluidDirection.OUTPUT).getTank(0);
            tank.setFluid(Steam.getGas(Integer.MAX_VALUE-1));
        }

        @Override
        public boolean canInput(FluidStack fluid, Direction direction) {
            return false;
        }

        @Override
        public boolean canInput(Direction direction) {
            return false;
        }

        @Nonnull
        @Override
        public FluidStack drain(FluidStack stack, FluidAction action) {
            return stack.copy();
        }

        @Nonnull
        @Override
        public FluidStack drain(int maxDrain, FluidAction action) {
            return Steam.getGas(maxDrain);
        }
    }
}