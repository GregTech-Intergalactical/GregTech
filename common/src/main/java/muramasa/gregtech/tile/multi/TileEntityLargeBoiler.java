package muramasa.gregtech.tile.multi;

import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.IRecipeValidator;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.RecipeMaps;
import muramasa.gregtech.items.ItemIntCircuit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Collections;
import java.util.List;

import static muramasa.antimatter.machine.Tier.*;

public class TileEntityLargeBoiler extends TileEntityMultiMachine<TileEntityLargeBoiler> {

    private boolean firstRun = true;
    private int mSuperEfficencyIncrease = 0;
    private int mEfficiencyIncrease;
    private int integratedCircuitConfig = 0; //Steam output is reduced by 1000L per config
    private int excessFuel = 0; //Eliminate rounding errors for fuels that burn half items
    private int excessProjectedEU = 0; //Eliminate rounding errors from throttling the boiler
    private int maxProgress = 0;
    private int euPerTick = 0;

    public TileEntityLargeBoiler(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public Block getCasing(){
        if (tier == LV){
            return GregTechData.CASING_BRONZE_PLATED_BRICK;
        } else if (tier == MV){
            return GregTechData.CASING_SOLID_STEEL;
        } else if (tier == HV){
            return GregTechData.CASING_TITANIUM;
        }
        return GregTechData.CASING_TUNGSTENSTEEL;
    }

    public int getEUt(){
        if (tier == LV){
            return 400;
        } else if (tier == MV){
            return 600;
        } else if (tier == HV){
            return 800;
        }
        return 1000;
    }

    public int getEfficiencyIncrease(){
        if (tier == LV){
            return 16;
        } else if (tier == MV){
            return 12;
        } else if (tier == HV){
            return 8;
        }
        return 4;
    }

    int runtimeBoost(int time) {
        if (tier == LV) return time * 2;
        int dividend = tier == MV ? 150 : tier == HV ? 130 : 120;
        return time * dividend / 100;
    }

    public Block getFireboxCasing(){
        if (tier == LV){
            return GregTechData.CASING_FIREBOX_BRONZE;
        } else if (tier == MV){
            return GregTechData.CASING_FIREBOX_STEEL;
        } else if (tier == HV){
            return GregTechData.CASING_FIREBOX_TITANIUM;
        }
        return GregTechData.CASING_FIREBOX_TUNGSTENSTEEL;
    }

    public Block getPipeCasing(){
        if (tier == LV){
            return GregTechData.CASING_PIPE_BRONZE;
        } else if (tier == MV){
            return GregTechData.CASING_PIPE_STEEL;
        } else if (tier == HV){
            return GregTechData.CASING_PIPE_TITANIUM;
        }
        return GregTechData.CASING_PIPE_TUNGSTENSTEEL;
    }

    public boolean checkRecipe(ItemStack stack){
        itemHandler.ifPresent(i -> {
            ItemStack circuit = i.getHandler(SlotType.STORAGE).getItem(0);
            if (circuit.getItem() instanceof ItemIntCircuit intCircuit){
                if (intCircuit.circuitId > 0 && intCircuit.circuitId <= 24){
                    integratedCircuitConfig = intCircuit.circuitId;
                }
            } else {
                if (integratedCircuitConfig != 0){
                    integratedCircuitConfig = 0;
                }
            }
        });
        this.mSuperEfficencyIncrease = 0;
        IRecipe recipe = RecipeMaps.COMBUSTION_FUELS.find(itemHandler, fluidHandler, HV, this::validateRecipe);
        if (recipe != null){
            this.maxProgress = adjustBurnTimeForConfig(runtimeBoost((int) (recipe.getPower() / 2)));
            this.euPerTick = adjustEUtForConfig(getEUt());
            this.mEfficiencyIncrease = this.maxProgress * getEfficiencyIncrease() * 4;
            return true;
        }
        recipe = RecipeMaps.SEMI_FUELS.find(itemHandler, fluidHandler, HV, this::validateRecipe);
        if (recipe != null){
            this.maxProgress = adjustBurnTimeForConfig(runtimeBoost((int) (recipe.getPower() / 2)));
            this.euPerTick = adjustEUtForConfig(getEUt());
            this.mEfficiencyIncrease = this.maxProgress * getEfficiencyIncrease() * 4;
            return true;
        }
        /*for (GT_Recipe tRecipe : GT_Recipe.GT_Recipe_Map.sDieselFuels.mRecipeList) {
            FluidStack tFluid = GT_Utility.getFluidForFilledItem(tRecipe.getRepresentativeInput(0), true);
            if (tFluid != null && tRecipe.mSpecialValue > 1) {
                tFluid.amount = 1000;
                if (depleteInput(tFluid)) {
                    this.mMaxProgresstime = adjustBurnTimeForConfig(runtimeBoost(tRecipe.mSpecialValue / 2));
                    this.mEUt = adjustEUtForConfig(getEUt());
                    this.mEfficiencyIncrease = this.mMaxProgresstime * getEfficiencyIncrease() * 4;
                    return true;
                }
            }
        }*/
        return true;
    }

    protected boolean validateRecipe(IRecipe r) {
        List<ItemStack> consumed = this.itemHandler.map(t -> t.consumeInputs(r, true)).orElse(Collections.emptyList());
        for (IRecipeValidator validator : r.getValidators()) {
            if (!validator.validate(r, this)) {
                return false;
            }
        }
        return (consumed.size() > 0 || !r.hasInputItems());
    }

    private int adjustEUtForConfig(int rawEUt) {
        int adjustedSteamOutput = rawEUt - 25 * integratedCircuitConfig;
        return Math.max(adjustedSteamOutput, 25);
    }

    private int adjustBurnTimeForConfig(int rawBurnTime) {
        /*if (mEfficiency < getMaxEfficiency(mInventory[1]) - ((getIdealStatus() - getRepairStatus()) * 1000)) {
            return rawBurnTime;
        }*/
        int adjustedEUt = Math.max(25, getEUt() - 25 * integratedCircuitConfig);
        int adjustedBurnTime = rawBurnTime * getEUt() / adjustedEUt;
        this.excessProjectedEU += getEUt() * rawBurnTime - adjustedEUt * adjustedBurnTime;
        adjustedBurnTime += this.excessProjectedEU / adjustedEUt;
        this.excessProjectedEU %= adjustedEUt;
        return adjustedBurnTime;
    }
}
