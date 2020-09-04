package muramasa.gti.block;

import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.single.TileEntityInfiniteStorage;

import static muramasa.antimatter.machine.MachineFlag.*;

public class BlockInfiniteStorage extends Machine<BlockInfiniteStorage> {

    public BlockInfiniteStorage(String domain, String id, Object... data) {
        super(domain, id, data);
        setTile(() -> new TileEntityInfiniteStorage(this, 16));
        setTiers(Tier.getAllElectric());
        addFlags(BASIC, ENERGY, COVERABLE, CONFIGURABLE);
    }
}
