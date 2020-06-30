package muramasa.gti.tile.single;

import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityStorage;
import tesseract.util.Dir;

import javax.annotation.Nonnull;
import java.util.Optional;

import static muramasa.antimatter.machine.MachineFlag.ENERGY;

public class TileBatteryBufferCreative extends TileEntityStorage {

    public TileBatteryBufferCreative(Machine<?> type) {
        super(type);
    }

    @Override
    public void onLoad() {
        //Anonymous inherited classes are annoying since you have to rewrite code. probably move the energy handlers to an actual class.
        if (/*isServerSide() &&*/ has(ENERGY)) energyHandler = Optional.of(new MachineEnergyHandler(this, getMachineTier().getVoltage() * 64L, getMachineTier().getVoltage() * 64L, getMachineTier().getVoltage(), getMachineTier().getVoltage(), 1, 1) {
            @Override
            public long extract(long maxExtract, boolean simulate) {
                return maxExtract;
            }

            public boolean canOutput(@Nonnull Dir direction) {
                //TODO: For debugging, set to always true for simplicity.
                return true;
               // return tile.getOutputFacing().getIndex() == direction.getIndex();
            }
        });
        super.onLoad();
    }
}