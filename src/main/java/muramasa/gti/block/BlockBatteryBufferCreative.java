package muramasa.gti.block;

import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.single.TileBatteryBufferCreative;

import static muramasa.antimatter.machine.MachineFlag.*;

public class BlockBatteryBufferCreative extends Machine<BlockBatteryBufferCreative> {

    public BlockBatteryBufferCreative(String domain, String id, Object... data) {
        super(domain, id, data);
        setTile(() -> new TileBatteryBufferCreative(this));
        setTiers(Tier.getAllElectric());
        addFlags(BASIC, ENERGY, COVERABLE, CONFIGURABLE);
    }
}
