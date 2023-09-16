package muramasa.gregtech.cover.redstone;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.ButtonOverlay;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.Tier;
import muramasa.gregtech.cover.CoverBasicRedstone;
import muramasa.gregtech.cover.RedstoneMode;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class CoverRedstoneMachineController extends CoverBasicRedstone {
    protected int redstonePower;

    public CoverRedstoneMachineController(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        addGuiCallback(t -> {
            t.addCycleButton(79, 34, 16, 16, h -> ((CoverBasicRedstone)h).redstoneMode.ordinal(), i -> "tooltip.gti.redstone_mode." + i, ButtonOverlay.TORCH_OFF, ButtonOverlay.TORCH_ON, ButtonOverlay.REDSTONE);
        });
    }

    @Override
    public String getId() {
        return "redstone_machine_controller";
    }

    @Override
    public void onRemove() {
        if (handler.getTile() instanceof BlockEntityMachine<?> machine){
            if (machine.getLevel().isLoaded(machine.getBlockPos())) {
                if (machine.getMachineState() == MachineState.DISABLED){
                    machine.toggleMachine();
                }
            }
        }
    }

    public boolean isPowered(){
        if (redstoneMode == RedstoneMode.NORMAL){
            return redstonePower > 0;
        }
        if (redstoneMode == RedstoneMode.INVERTED){
            return redstonePower == 0;
        }
        return false;
    }

    @Override
    public void onUpdate() {
        if (handler.getTile() instanceof BlockEntityMachine<?> machine){
            if (machine.getMachineState() != MachineState.DISABLED){
                if (redstoneMode == RedstoneMode.NO_WORK){
                    machine.toggleMachine();
                } else if (redstoneMode == RedstoneMode.NORMAL){
                    if (redstonePower == 0){
                        machine.toggleMachine();
                    }
                } else {
                    if (redstonePower > 0){
                        machine.toggleMachine();
                    }
                }
            } else {
                if (redstoneMode == RedstoneMode.NORMAL){
                    if (redstonePower > 0){
                        machine.toggleMachine();
                    }
                } else if (redstoneMode == RedstoneMode.INVERTED){
                    if (redstonePower == 0){
                        machine.toggleMachine();
                    }
                }
            }

        }
    }

    @Override
    public void onBlockUpdate() {
        redstonePower = handler.getTile().getLevel().getSignal(handler.getTile().getBlockPos().relative(side), side);
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }

    @Override
    public boolean hasGui() {
        return true;
    }
}
