package muramasa.gti.tile.single;

import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.LazyHolder;
import net.minecraft.util.Direction;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import tesseract.api.IRefreshable;

import javax.annotation.Nonnull;
import java.util.List;

import static muramasa.antimatter.Data.COVEROUTPUT;
import static muramasa.gti.data.Materials.Steam;

public class TileEntityInfiniteFluid extends TileEntityMachine {

    @Override
    public boolean setFacing(Direction side) {
        boolean ok = super.setFacing(side);
        if (ok) {
            fluidHandler.ifPresent(IRefreshable::refreshNet);
        }
        return ok;
    }

    @Override
    public void onServerUpdate() {
        super.onServerUpdate();
        coverHandler.ifPresent(c -> {
            CoverStack<?> stack = c.get(c.getOutputFacing());
            COVEROUTPUT.manualOutput(stack);
        });
    }

    public TileEntityInfiniteFluid(Machine<?> type) {
        super(type);
        this.fluidHandler = LazyHolder.of(() -> new InfiniteFluidHandler(this) {

        });
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