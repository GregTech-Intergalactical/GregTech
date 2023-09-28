package muramasa.gregtech.cover;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.blockentity.pipe.BlockEntityPipe;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.cover.base.CoverBasicTransport;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class CoverRobotArm extends CoverBasicTransport {
    int slot = 0;
    public CoverRobotArm(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        addGuiCallback(t -> {
            t.addTextButton(70, 53, 36, 12, h -> ((CoverRobotArm)h).slot, i -> Utils.literal("Slot: ").append("" + i), 0, true);
        });
    }

    @Override
    protected String getRenderId() {
        return "conveyor";
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicDepthModel();
    }

    @Override
    public boolean onTransfer(Object object, boolean inputSide, boolean simulate) {
        if (object instanceof ItemStack stack && !exportMode.isExport() && handler.getTile() instanceof BlockEntityMachine<?> machine && inputSide) {
            if (machine.itemHandler.map(h -> h.getInputCount() > 0).orElse(false)){
                machine.itemHandler.ifPresent(h -> {

                });
            }
        }
        return false;
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        ImportExportMode previous = exportMode;
        super.onGuiEvent(event, playerEntity);
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){

            GuiEvents.GuiEvent ev = (GuiEvents.GuiEvent) event;
            if (ev.data[1] == 1){
                if (previous.isExport() != exportMode.isExport()){
                    slot = 0;
                    if (handler.getTile() instanceof BlockEntityMachine<?> machine) machine.onBlockUpdate(machine.getBlockPos());
                }
            }
            if (ev.data[1] == 2){
                if (handler.getTile() instanceof BlockEntityMachine<?> machine){
                    machine.itemHandler.ifPresent(h -> {
                        if (exportMode.isExport()){
                            if (h.getOutputCount() > 0){
                                slot++;
                                if (slot >= h.getOutputCount()) slot = 0;
                            } else {
                                slot = 0;
                            }
                        }
                        if (!exportMode.isExport()){
                            if (h.getInputCount() > 0){
                                slot++;
                                if (slot >= h.getInputCount()) slot = 0;
                            } else if (h.getHandler(SlotType.STORAGE).getContainerSize() > 0){
                                slot++;
                                if (slot >= h.getHandler(SlotType.STORAGE).getContainerSize()) slot = 0;
                            }else {
                                slot = 0;
                            }
                        }
                        machine.onBlockUpdate(machine.getBlockPos());
                    });
                }
            }
        }
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        slot = nbt.getInt("slot");
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag nbt =  super.serialize();
        nbt.putInt("slot", slot);
        return nbt;
    }
}
