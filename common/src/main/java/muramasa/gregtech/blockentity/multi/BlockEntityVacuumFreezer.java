package muramasa.gregtech.blockentity.multi;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityVacuumFreezer extends BlockEntityMultiMachine<BlockEntityVacuumFreezer> {

    public BlockEntityVacuumFreezer(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

//    @Override
//    public void onRecipeFound() {
////        this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
////        this.mEfficiencyIncrease = 10000;
//        int tier = Utils.getVoltageTier(getMaxInputVoltage());
//        if (activeRecipe.getPower() <= 16) {
//            EUt = (activeRecipe.getPower() * (1 << tier - 1) * (1 << tier - 1));
//            maxProgress = (activeRecipe.getDuration() / (1 << tier - 1));
//        } else {
//            EUt = activeRecipe.getPower();
//            maxProgress = activeRecipe.getDuration();
//            for (int i = 0; i < GTIRef.V.length; i++) {
//                if (EUt > GTIRef.V[tier - 1]) break;
//                EUt *= 4;
//                maxProgress /= 2;
//            }
//        }
//        maxProgress = Math.max(1, maxProgress);
//    }
}
