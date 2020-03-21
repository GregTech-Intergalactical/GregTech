package muramasa.gti.block;

import muramasa.antimatter.block.BlockCasing;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockFusionCasing extends BlockCasing {

    public BlockFusionCasing(String domain, String id, Block.Properties properties) {
        super(domain, id, properties);
    }

    public BlockFusionCasing(String domain, String id) {
        super(domain, id);
    }

    @Override
    public boolean canConnect(IBlockReader world, BlockState state, BlockPos pos) {
        return state.getBlock() instanceof BlockFusionCasing;
    }
}
