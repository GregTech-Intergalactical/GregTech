package muramasa.gregtech.tile.multi;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import muramasa.antimatter.capability.IComponentHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TileEntityDistillationTower extends TileEntityMultiMachine<TileEntityDistillationTower> {
    public Set<Integer> HATCH_LAYER_MAP = new HashSet<>();
    public List<IComponentHandler> FO_HATCHES = new ArrayList<>();
    public TileEntityDistillationTower(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public boolean checkStructure() {
        HATCH_LAYER_MAP.clear();
        return super.checkStructure();
    }
}
