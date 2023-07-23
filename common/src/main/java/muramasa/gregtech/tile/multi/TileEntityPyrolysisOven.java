package muramasa.gregtech.tile.multi;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.gregtech.block.BlockCoil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityPyrolysisOven extends TileEntityMultiMachine<TileEntityPyrolysisOven> {
    private BlockCoil.CoilData coilData;

    public TileEntityPyrolysisOven(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public BlockCoil.CoilData getCoilData() {
        return coilData;
    }

    public void setCoilData(BlockCoil.CoilData coilData) {
        this.coilData = coilData;
    }
}
