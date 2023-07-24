package muramasa.gregtech.tile.multi;

import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.items.ItemIntCircuit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import static muramasa.antimatter.machine.Tier.*;

public class TileEntityLargeBoiler extends TileEntityMultiMachine<TileEntityLargeBoiler> {

    private boolean firstRun = true;
    private int mSuperEfficencyIncrease = 0;
    private int integratedCircuitConfig = 0; //Steam output is reduced by 1000L per config
    private int excessFuel = 0; //Eliminate rounding errors for fuels that burn half items
    private int excessProjectedEU = 0; //Eliminate rounding errors from throttling the boiler

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
        return true;
    }
}
