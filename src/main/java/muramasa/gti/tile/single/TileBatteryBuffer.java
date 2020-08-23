package muramasa.gti.tile.single;

import muramasa.antimatter.capability.IEnergyHandler;
import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityStorage;
import tesseract.util.Dir;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

import static muramasa.antimatter.machine.MachineFlag.ENERGY;

public class TileBatteryBuffer extends TileEntityStorage {

    public TileBatteryBuffer(Machine<?> type) {
        super(type);
    }

    @Override
    public void onLoad() {
        //Anonymous inherited classes are annoying since you have to rewrite code. probably move the energy handlers to an actual class.
        if (has(ENERGY)) energyHandler = Optional.of(new MachineEnergyHandler(this, 0, getMachineTier().getVoltage() * 64L, getMachineTier().getVoltage(), getMachineTier().getVoltage(), 0,0) {
            public boolean canOutput(@Nonnull Dir direction) {
                //TODO: For debugging, set to always true for simplicity.
               return tile.getFacing().getIndex() == direction.getIndex();
            }

            @Override
            public boolean canChargeItem() {
                return true;
            }
        });
        super.onLoad();
    }

    @Override
    public List<String> getInfo() {
        List<String> info = super.getInfo();

        info.add("Amperage in: " + energyHandler.map(handler -> handler.getInputAmperage()).orElse(0));
        info.add("Amperage out: " + energyHandler.map(handler -> handler.getOutputAmperage()).orElse(0));
        return info;
    }
}