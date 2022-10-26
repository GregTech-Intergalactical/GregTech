package muramasa.gregtech.tile.multi;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.gregtech.block.BlockCoil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class TileEntityMultiSmelter extends TileEntityMultiMachine<TileEntityMultiSmelter> {

    private int level = 1, discount = 1;

    public TileEntityMultiSmelter(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

//    @Override
//    public void onRecipeFound() {
////        this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
////        this.mEfficiencyIncrease = 10000;
//
//        int tier = Utils.getVoltageTier(getMaxInputVoltage());
//        EUt = (-4 * (1 << tier - 1) * (1 << tier - 1) * level / discount);
//        maxProgress = Math.max(1, 512 / (1 << tier - 1));
//    }

    @Override
    public boolean onStructureFormed() {
        super.onStructureFormed();
        List<BlockState> coils = getStates("coil");
        BlockCoil firstType = ((BlockCoil) coils.get(0).getBlock());
        if (coils.stream().allMatch(s -> s.getBlock() == firstType)) {
            setCoilValues(firstType);
            return true;
        } else {
            this.result.withError("all coils do not match");
            return false;
        }
    }

    public void setCoilValues(BlockCoil coil) {
        switch (coil.getId()) {
            case "kanthal":
                level = 2;
                break;
            case "nichrome":
                level = 4;
                break;
            case "tungstensteel":
                level = 8;
                break;
            case "hssg":
                level = 16;
                discount = 2;
                break;
            case "naquadah":
                level = 16;
                discount = 4;
                break;
            case "naquadah_alloy":
                level = 16;
                discount = 8;
                break;
        }
    }
}
