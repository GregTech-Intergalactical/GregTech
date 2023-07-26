package muramasa.gregtech.tile.multi;

import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.IRecipeValidator;
import muramasa.antimatter.recipe.ingredient.FluidIngredient;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.RecipeMaps;
import muramasa.gregtech.items.ItemIntCircuit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gregtech.data.Materials.DistilledWater;
import static muramasa.gregtech.data.Materials.Steam;

public class TileEntityLargeBoiler extends TileEntityMultiMachine<TileEntityLargeBoiler> {

    private boolean firstRun = true;
    private int mSuperEfficencyIncrease = 0;
    private int mEfficiency = 0;
    private int mEfficiencyIncrease;
    private int integratedCircuitConfig = 0; //Steam output is reduced by 1000L per config
    private int excessFuel = 0; //Eliminate rounding errors for fuels that burn half items
    private int excessProjectedEU = 0; //Eliminate rounding errors from throttling the boiler
    private int maxProgress = 0;
    private int progress = 0;
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

    public Texture getTextureForHatches(Direction dir, BlockPos hatchPos){
        if (hatchPos.getY() != this.getBlockPos().getY()) return super.getTextureForHatches(dir, hatchPos);
        String prefix = tier == LV ? "bronze" : tier == MV ? "steel" : tier == HV ? "titanium" : "tungstensteel";
        return new Texture(GTIRef.ID, "block/casing/" + prefix + "_firebox");
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        if (isStructureValid()){
            if (maxProgress > 0)
                if (++progress >= maxProgress) {

                }
        }
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
        IRecipe recipe = RecipeMaps.COMBUSTION_FUELS.find(itemHandler, fluidHandler, null, this::validateRecipe);
        if (recipe != null){
            FluidIngredient ing = recipe.getInputFluids().get(0).copyMB(1000);
            if (fluidHandler.map(f -> !f.consumeAndReturnInputs(List.of(ing), true).isEmpty()).orElse(false)){
                this.maxProgress = adjustBurnTimeForConfig(runtimeBoost((int) (recipe.getPower() / 2)));
                this.euPerTick = adjustEUtForConfig(getEUt());
                this.mEfficiencyIncrease = this.maxProgress * getEfficiencyIncrease() * 4;
                fluidHandler.ifPresent(f -> f.consumeAndReturnInputs(List.of(ing), false));
                return true;
            }
        }
        recipe = RecipeMaps.SEMI_FUELS.find(itemHandler, fluidHandler, null, this::validateRecipe);
        if (recipe != null){
            FluidIngredient ing = recipe.getInputFluids().get(0).copyMB(1000);
            if (fluidHandler.map(f -> !f.consumeAndReturnInputs(List.of(ing), true).isEmpty()).orElse(false)){
                this.maxProgress = adjustBurnTimeForConfig(runtimeBoost((int) (recipe.getPower() * 2)));
                this.euPerTick = adjustEUtForConfig(getEUt());
                this.mEfficiencyIncrease = this.maxProgress * getEfficiencyIncrease();
                fluidHandler.ifPresent(f -> f.consumeAndReturnInputs(List.of(ing), false));
                return true;
            }
        }
        List<ItemStack> tInputList = itemHandler.map(MachineItemHandler::getInputList).orElse(Collections.emptyList());
        if (!tInputList.isEmpty()) {
            for (ItemStack tInput : tInputList) {
                if (tInput.getItem() != Items.LAVA_BUCKET){
                    if (!FluidHooks.isFluidContainingItem(tInput)) {
                        int burnTime = AntimatterPlatformUtils.getBurnTime(tInput, RecipeType.SMELTING);
                        if ((this.maxProgress = burnTime / 80) > 0) {
                            this.excessFuel += burnTime % 80;
                            this.maxProgress += this.excessFuel / 80;
                            this.excessFuel %= 80;
                            this.maxProgress = adjustBurnTimeForConfig(runtimeBoost(this.maxProgress));
                            this.euPerTick = adjustEUtForConfig(getEUt());
                            this.mEfficiencyIncrease = this.maxProgress * getEfficiencyIncrease();
                            //this.mOutputItems = new ItemStack[]{GT_Utility.getContainerItem(tInput, true)};
                            itemHandler.ifPresent(i -> i.consumeAndReturnInputs(Utils.ca(1, tInput)));
                            if (this.mEfficiencyIncrease > 5000) {
                                this.mEfficiencyIncrease = 0;
                                this.mSuperEfficencyIncrease = 20;
                            }
                            return true;
                        }
                    }
                }
            }
        }
        this.maxProgress = 0;
        this.euPerTick = 0;
        return false;
    }
    public boolean onRunningTick() {
        if (this.euPerTick > 0) {
            if (this.mSuperEfficencyIncrease > 0)
                mEfficiency = Math.max(0, Math.min(mEfficiency + mSuperEfficencyIncrease, 10000));
            int tGeneratedEU = (int) (this.euPerTick * 2L * this.mEfficiency / 10000L);
            if (tGeneratedEU > 0) {
                int amount = (tGeneratedEU + 160) / 160;
                fluidHandler.ifPresent(f -> {
                    if (f.drainInput(AntimatterMaterials.Water.getLiquid(amount), false).getFluidAmount() == amount || f.drainInput(DistilledWater.getLiquid(amount), false).getFluidAmount() == amount) {
                        f.addOutputs(Steam.getGas(tGeneratedEU));
                    } else {
                        explodeMultiblock();
                    }
                });

            }
            return true;
        }
        return true;
    }

    protected boolean validateRecipe(IRecipe r) {
        List<ItemStack> consumed = this.itemHandler.map(t -> t.consumeInputs(r, true)).orElse(Collections.emptyList());
        for (IRecipeValidator validator : r.getValidators()) {
            if (!validator.validate(r, this)) {
                return false;
            }
        }
        return (consumed.size() > 0 || (!r.hasInputItems())) && r.getSpecialValue() > 0 && r.getSpecialValue() < 4;
    }

    private int adjustEUtForConfig(int rawEUt) {
        int adjustedSteamOutput = rawEUt - 25 * integratedCircuitConfig;
        return Math.max(adjustedSteamOutput, 25);
    }

    private int adjustBurnTimeForConfig(int rawBurnTime) {
        if (mEfficiency < 10000) {
            return rawBurnTime;
        }
        int adjustedEUt = Math.max(25, getEUt() - 25 * integratedCircuitConfig);
        int adjustedBurnTime = rawBurnTime * getEUt() / adjustedEUt;
        this.excessProjectedEU += getEUt() * rawBurnTime - adjustedEUt * adjustedBurnTime;
        adjustedBurnTime += this.excessProjectedEU / adjustedEUt;
        this.excessProjectedEU %= adjustedEUt;
        return adjustedBurnTime;
    }
}
