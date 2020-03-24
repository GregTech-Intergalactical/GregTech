package muramasa.gti.block;

import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.gti.tile.single.TileBatteryBufferCreative;

import static muramasa.antimatter.machine.MachineFlag.*;

public class BlockBatteryBufferCreative extends Machine {
    public BlockBatteryBufferCreative(String domain, String id, Object... data) {
        super(domain, id, TileBatteryBufferCreative::new, data);
        setTiers(Tier.getAllElectric());
        addFlags(BASIC, ENERGY, COVERABLE, CONFIGURABLE);
    }
}
