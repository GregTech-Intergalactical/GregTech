package muramasa.gtu.common.tileentities.multi;

import muramasa.antimatter.Ref;
import muramasa.antimatter.blocks.BlockCoil;
import muramasa.antimatter.tileentities.multi.TileEntityMultiMachine;
import muramasa.antimatter.util.Utils;

public class TileEntityElectricBlastFurnace extends TileEntityMultiMachine {

    private int heatingCapacity;

    @Override
    public void onRecipeFound() {
        if (heatingCapacity >= activeRecipe.getSpecialValue()) {
            //this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
            //this.mEfficiencyIncrease = 10000;
            int tier = Utils.getVoltageTier(getMaxInputVoltage());
            int heatDiv = (heatingCapacity - activeRecipe.getSpecialValue()) / 900;
            if (activeRecipe.getPower() <= 16) {
                EUt = (activeRecipe.getPower() * (1 << tier - 1) * (1 << tier - 1));
                maxProgress = (activeRecipe.getDuration() / (1 << tier - 1));
            } else {
                EUt = activeRecipe.getPower();
                maxProgress = activeRecipe.getDuration();
                for (int i = 2; i < Ref.V.length; i+= 2) {
                    if (EUt > Ref.V[tier - 1]) break;
                    EUt *= 4;
                    maxProgress /= (heatDiv >= i ? 4 : 2);
                }
            }
            if (heatDiv > 0) EUt = (long)(EUt * (Math.pow(0.95, heatDiv)));
            maxProgress = Math.max(1, maxProgress);

            System.out.println("max: " + maxProgress + " - rec: " + activeRecipe.getDuration());
            System.out.println("eu: " + EUt + " - rec: " + activeRecipe.getPower());
        }
    }

    @Override
    public boolean onStructureFormed() {
        heatingCapacity = getStates("coil").stream().mapToInt(s -> ((BlockCoil) s.getBlock()).getHeatCapacity()).sum();
        return true;
    }
}
