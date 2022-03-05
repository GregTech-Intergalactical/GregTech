package muramasa.gregtech.tile.single;

import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTypeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class TileEntityTypeFilter extends TileEntityItemFilter {
    public TileEntityTypeFilter(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public boolean accepts(ItemStack stack) {
        boolean proceed = itemHandler.map(i -> {
            ItemStack tagStack = i.getHandler(SlotType.DISPLAY_SETTABLE).getStackInSlot(0);
            if (tagStack.isEmpty()) return false;
            Set<ResourceLocation> list = tagStack.getItem().getTags();
            if (list.isEmpty()) return false;
            boolean hasTag = false;
            for (ResourceLocation tag : list){
                if ((tag.getNamespace().equals("forge") || tag.getNamespace().equals("minecraft")) && !tag.getPath().contains("/")){
                    if (stack.getItem().getTags().contains(tag)){
                        hasTag = true;
                        break;
                    }
                }
            }
            return blacklist != hasTag;
        }).orElse(false);
        return proceed;
    }
}