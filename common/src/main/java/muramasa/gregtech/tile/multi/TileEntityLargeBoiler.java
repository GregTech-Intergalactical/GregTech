package muramasa.gregtech.tile.multi;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import static muramasa.antimatter.machine.Tier.*;

public class TileEntityLargeBoiler extends TileEntityMultiMachine<TileEntityLargeBoiler> {

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

}
