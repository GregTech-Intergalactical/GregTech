package muramasa.gregtech.blockentity.multi;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.gregtech.block.BlockCoil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityPyrolysisOven extends BlockEntityMultiMachine<BlockEntityPyrolysisOven> {
    private BlockCoil.CoilData coilData;

    public BlockEntityPyrolysisOven(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public BlockCoil.CoilData getCoilData() {
        return coilData;
    }

    public void setCoilData(BlockCoil.CoilData coilData) {
        this.coilData = coilData;
    }
}
