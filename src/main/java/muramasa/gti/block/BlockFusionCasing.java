package muramasa.gti.block;

import muramasa.antimatter.block.BlockCasing;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockFusionCasing extends BlockCasing {

    public BlockFusionCasing(Block.Properties properties) {
        super(properties);
    }

    public BlockFusionCasing() {
        super();
    }

    @Override
    public boolean canConnect(IBlockReader world, BlockState state, BlockPos pos) {
        return state.getBlock() instanceof BlockFusionCasing;
    }
}
