package muramasa.gregtech.cover;

import muramasa.antimatter.blockentity.BlockEntityMachine;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class CoverItemRegulator extends CoverBasicTransport {
    int slotLimit = 0;
    public CoverItemRegulator(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        addGuiCallback(t -> {
            t.addButton(61,53, ButtonOverlay.MINUS, true);
            t.addButton(97,53, ButtonOverlay.PLUS, true);
            t.addWidget(SyncableTextWidget.build(i -> {
                CoverItemRegulator itemRegulator = (CoverItemRegulator) i;
                if (itemRegulator.slotLimit == 0) return "N/A";
                return String.valueOf(itemRegulator.slotLimit);
            }, 4210752, true).setSize(78, 58, 18, 18));
        });
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        super.onGuiEvent(event, playerEntity);
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){

            GuiEvents.GuiEvent ev = (GuiEvents.GuiEvent) event;
            int button = ev.data[1];
            if (button == 2){
                if (slotLimit > 0){
                    slotLimit--;
                    handler.getTile().setChanged();
                }
            }
            if (button == 3){
                if (slotLimit < 64){
                    slotLimit++;
                    handler.getTile().setChanged();
                }
            }
        }
    }

    @Override
    public boolean onTransfer(Object object, boolean inputSide, boolean simulate) {
        if (object instanceof ItemStack stack && !exportMode.isExport() && handler.getTile() instanceof BlockEntityMachine<?> machine && inputSide) {
            if (machine.itemHandler.isPresent()){
                if (stack.isEmpty()) return true;
                if (slotLimit > 0 && stack.getCount() < slotLimit) return true;
                ItemStack toInsert = slotLimit > 0 ? Utils.ca(slotLimit, stack) : stack.copy();
                MachineItemHandler<?> itemHandler = machine.itemHandler.get();
                if (itemHandler == null) return true;
                ItemStack inserted = itemHandler.forSide(side).map(i -> Utils.insertItem(i, toInsert, true)).orElse(toInsert);
                if (inserted.isEmpty()){
                    if (!simulate) {
                        itemHandler.forSide(side).ifPresent(i -> Utils.insertItem(i, toInsert, false));
                    }
                    stack.setCount(0);
                } else if (inserted.getCount() < toInsert.getCount()) {
                    if (!simulate) {
                        itemHandler.forSide(side).ifPresent(i -> Utils.insertItem(i, toInsert, false));
                    }
                    stack.setCount(toInsert.getCount() - inserted.getCount());
                }
                return true;
            }
        }
        return false;
    }
}
