package muramasa.gti.tile.multi;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityBasicMultiMachine;
import muramasa.antimatter.util.int3;
import net.minecraft.block.Blocks;

public class TileEntityPrimitiveBlastFurnace extends TileEntityBasicMultiMachine {

    public TileEntityPrimitiveBlastFurnace(Machine type) {
        super(type);
    }

    @Override
    public boolean onStructureFormed() {
        super.onStructureFormed();
        int3 controller = new int3(getPos(), getFacing());
        controller.back(1);
        getWorld().setBlockState(controller, Blocks.LAVA.getDefaultState(), 3);
        controller.up(1);
        getWorld().setBlockState(controller, Blocks.LAVA.getDefaultState(), 3);
        return true;
    }
}
