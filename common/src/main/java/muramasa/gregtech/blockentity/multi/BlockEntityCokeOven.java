package muramasa.gregtech.blockentity.multi;

import muramasa.antimatter.blockentity.multi.BlockEntityBasicMultiMachine;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityCokeOven extends BlockEntityBasicMultiMachine<BlockEntityCokeOven> {

    public BlockEntityCokeOven(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public boolean allowsFakeTiles() {
        return true;
    }
}
