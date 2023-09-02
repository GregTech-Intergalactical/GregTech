package muramasa.gregtech.tile.single;

import muramasa.antimatter.capability.item.TrackedItemHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.api.item.ExtendedItemContainer;
import tesseract.util.ItemHandlerUtils;

public class TileEntitySuperBuffer extends TileEntityMachine<TileEntitySuperBuffer> {
    public TileEntitySuperBuffer(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        itemHandler.set(() -> new SuperBufferItemHandler(this));
    }

    public static class SuperBufferItemHandler extends MachineItemHandler<TileEntitySuperBuffer> {

        public SuperBufferItemHandler(TileEntitySuperBuffer tile) {
            super(tile);
            this.inventories.put(SlotType.STORAGE, new TrackedItemHandler<>(tile, 256, true, true, (t, s) -> true, ContentEvent.ITEM_INPUT_CHANGED));
        }
    }
}
