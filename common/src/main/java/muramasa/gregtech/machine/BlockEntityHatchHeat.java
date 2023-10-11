package muramasa.gregtech.machine;

import muramasa.antimatter.blockentity.multi.BlockEntityHatch;
import muramasa.antimatter.capability.Holder;
import muramasa.antimatter.capability.machine.DefaultHeatHandler;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.machine.types.HatchMachine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.api.heat.IHeatHandler;

public class BlockEntityHatchHeat<T extends BlockEntityHatchHeat<T>> extends BlockEntityHatch<T> {

    public BlockEntityHatchHeat(HatchMachine type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        HeatHatch hath = (HeatHatch)type;
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        /*if (this.getComponentHandler().map(t -> t.getControllers().size()).orElse(0) == 0) {
            this.heatHandler.ifPresent(t -> t.update(false));
        }*/
    }
}
