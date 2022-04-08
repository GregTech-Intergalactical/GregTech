package muramasa.gregtech.tile.multi;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityVacuumFreezer extends TileEntityMultiMachine<TileEntityVacuumFreezer> {

    public TileEntityVacuumFreezer(Machine type, BlockPos pos, BlockState state) {
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
//            for (int i = 0; i < Ref.V.length; i++) {
//                if (EUt > Ref.V[tier - 1]) break;
//                EUt *= 4;
//                maxProgress /= 2;
//            }
//        }
//        maxProgress = Math.max(1, maxProgress);
//    }
}
