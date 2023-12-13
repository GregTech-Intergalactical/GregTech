package muramasa.gregtech.blockentity.multi;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityOilCrackingUnit extends BlockEntityMultiMachine<BlockEntityOilCrackingUnit> {

    public BlockEntityOilCrackingUnit(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public int maxShares() {
        return 0;
    }
}
