package muramasa.gregtech.cover;

import muramasa.antimatter.blockentity.BlockEntityBase;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.blockentity.pipe.BlockEntityPipe;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.ButtonOverlay;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.gui.widget.SyncableTextWidget;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.cover.base.CoverBasicRedstone;
import muramasa.gregtech.cover.base.CoverBasicTransport;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;
import tesseract.api.item.PlatformItemHandler;

import java.util.function.Predicate;

public class CoverRobotArm extends CoverBasicTransport {
    int slot = 0;
    int slotLimit = 0;
    public CoverRobotArm(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    protected void addButtons() {
        addGuiCallback(t -> {
            t.addCycleButton(70, 16, 16, 16, h -> ((CoverBasicRedstone)h).redstoneMode.ordinal(), true, i -> "tooltip.gti.redstone_mode." + i, ButtonOverlay.TORCH_OFF, ButtonOverlay.TORCH_ON, ButtonOverlay.REDSTONE);
            t.addCycleButton(88, 16, 16, 16, h -> ((CoverRobotArm)h).exportMode.ordinal(), true, i -> "tooltip.gti.export_mode." + i, ButtonOverlay.EXPORT, ButtonOverlay.IMPORT, ButtonOverlay.EXPORT_IMPORT, ButtonOverlay.IMPORT_EXPORT);
            t.addTextButton(70, 53, 36, 12, h -> ((CoverRobotArm)h).slot, i -> Utils.literal("Slot: ").append("" + i), 0, true);
            t.addButton(61,34, ButtonOverlay.MINUS, true);
            t.addButton(97,34, ButtonOverlay.PLUS, true);
            t.addWidget(SyncableTextWidget.build(i -> {
                CoverRobotArm robotArm = (CoverRobotArm) i;
                if (robotArm.slotLimit == 0) return "N/A";
                return String.valueOf(robotArm.slotLimit);
            }, 4210752, true).setSize(78, 38, 18, 18));
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
            if (machine.itemHandler.isPresent()){
                if (stack.isEmpty()) return true;
                if (slotLimit > 0 && stack.getCount() < slotLimit) return true;
                ItemStack toInsert = slotLimit > 0 ? Utils.ca(slotLimit, stack) : stack.copy();
                MachineItemHandler<?> itemHandler = machine.itemHandler.get();
                if (itemHandler.getInputCount() > 0){
                    ItemStack inserted = itemHandler.getInputHandler().insertItem(slot, toInsert, true);
                    if (inserted.isEmpty()){
                        if (!simulate) {
                            itemHandler.getInputHandler().insertItem(slot, toInsert, false);
                        }
                        stack.setCount(0);
                    } else if (inserted.getCount() < toInsert.getCount()) {
                        if (!simulate) {
                            itemHandler.getInputHandler().insertItem(slot, toInsert, false);
                        }
                        stack.setCount(toInsert.getCount() - inserted.getCount());
                    }
                } else if (itemHandler.getHandler(SlotType.STORAGE).getContainerSize() > 0){
                    ItemStack inserted = itemHandler.getHandler(SlotType.STORAGE).insertItem(slot, toInsert, true);
                    if (inserted.isEmpty()){
                        if (!simulate) {
                            itemHandler.getHandler(SlotType.STORAGE).insertItem(slot, toInsert, false);
                        }
                        stack.setCount(0);
                    } else if (inserted.getCount() < toInsert.getCount()) {
                        if (!simulate) {
                            itemHandler.getHandler(SlotType.STORAGE).insertItem(slot, toInsert, false);
                        }
                        stack.setCount(toInsert.getCount() - inserted.getCount());
                    }
                }
                return true;
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
            int button = ev.data[1];
            if (button == 1){
                if (previous.isExport() != exportMode.isExport()){
                    slot = 0;
                    handler.getTile().setChanged();
                }
            }
            if (button == 2){
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
                        machine.setChanged();
                    });
                }
            }
            if (button == 3){
                if (slotLimit > 0){
                    slotLimit--;
                    handler.getTile().setChanged();
                }
            }
            if (button == 4){
                if (slotLimit < 64){
                    slotLimit++;
                    handler.getTile().setChanged();
                }
            }
        }
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        slot = nbt.getInt("slot");
        if (nbt.contains("slotLimit")) {
            slotLimit = nbt.getInt("slotLimit");
        }
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag nbt =  super.serialize();
        nbt.putInt("slot", slot);
        nbt.putInt("slotLimit", slotLimit);
        return nbt;
    }
}
