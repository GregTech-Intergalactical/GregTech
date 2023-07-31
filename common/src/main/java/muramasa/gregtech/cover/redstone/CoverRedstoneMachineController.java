package muramasa.gregtech.cover.redstone;

import muramasa.antimatter.Ref;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.cover.ICoverMode;
import muramasa.antimatter.cover.ICoverModeHandler;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.gregtech.cover.RedstoneMode;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;

import static muramasa.gregtech.gui.ButtonOverlays.*;

public class CoverRedstoneMachineController extends BaseCover implements ICoverModeHandler {

    protected RedstoneMode coverMode;
    protected int redstonePower;

    public CoverRedstoneMachineController(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        this.coverMode = RedstoneMode.NORMAL;
        addGuiCallback(t -> {
            t.addButton(61, 34, 16, 16, TORCH_ON).addButton(79, 34, 16, 16, TORCH_OFF).addButton(97, 34, 16, 16, REDSTONE);
        });
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    @Override
    public String getId() {
        return "redstone_machine_controller";
    }

    @Override
    public void onRemove() {
        if (handler.getTile() instanceof TileEntityMachine){
            TileEntityMachine<?> machine = (TileEntityMachine<?>) handler.getTile();
            if (machine.getMachineState() == MachineState.DISABLED){
                machine.toggleMachine();
            }
        }
    }

    public boolean isPowered(){
        if (coverMode == RedstoneMode.NORMAL){
            return redstonePower > 0;
        }
        if (coverMode == RedstoneMode.INVERTED){
            return redstonePower == 0;
        }
        return false;
    }

    @Override
    public void onUpdate() {
        if (handler.getTile() instanceof TileEntityMachine){
            TileEntityMachine<?> machine = (TileEntityMachine<?>) handler.getTile();
            if (machine.getMachineState() != MachineState.DISABLED){
                if (coverMode == RedstoneMode.NO_WORK){
                    machine.toggleMachine();
                } else if (coverMode == RedstoneMode.NORMAL){
                    if (redstonePower == 0){
                        machine.toggleMachine();
                    }
                } else {
                    if (redstonePower > 0){
                        machine.toggleMachine();
                    }
                }
            } else {
                if (coverMode == RedstoneMode.NORMAL){
                    if (redstonePower > 0){
                        machine.toggleMachine();
                    }
                } else if (coverMode == RedstoneMode.INVERTED){
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
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){
            GuiEvents.GuiEvent ev = (GuiEvents.GuiEvent) event;
            coverMode = RedstoneMode.values()[Math.min(ev.data[0], 2)];
        }
    }

    @Override
    public ICoverMode getCoverMode() {
        return coverMode;
    }

    @Override
    public int coverModeToInt() {
        return coverMode.ordinal();
    }

    @Override
    public ICoverMode getCoverMode(int index) {
        return RedstoneMode.values()[index];
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
