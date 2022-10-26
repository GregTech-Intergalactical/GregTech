package muramasa.gregtech.data;

import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.slot.AbstractSlot;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.gregtech.tile.single.IFilterable;
import net.minecraftforge.items.wrapper.EmptyHandler;

public class SlotTypes {
    public static SlotType<AbstractSlot<?>> FILTERABLE = new SlotType<>("filterable", (type, gui, inv, i, d) -> new AbstractSlot<>(type, gui, inv.getOrDefault(type, new EmptyHandler()), i, d.getX(), d.getY()), (t, i) -> {
        if (t instanceof IFilterable) {
            return ((IFilterable)t).accepts(i);
        }
        return true;
    },ContentEvent.ITEM_INPUT_CHANGED, true, true);
}
