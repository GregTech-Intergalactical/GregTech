package muramasa.gregtech.blockentity.single;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.api.item.ExtendedItemContainer;

import java.util.List;
import java.util.Objects;

public class BlockEntityTypeFilter extends BlockEntityItemFilter {
    public BlockEntityTypeFilter(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public boolean test(SlotType<?> slotType, int slot, ItemStack stack) {
        if (slotType == SlotType.STORAGE){
            return itemHandler.map(i -> {
                ItemStack tagStack = i.getHandler(SlotType.DISPLAY_SETTABLE).getStackInSlot(0);
                if (tagStack.isEmpty()) return false;
                List<TagKey<Item>> tags = tagStack.getItem().builtInRegistryHolder().tags().toList();
                String forge = AntimatterPlatformUtils.isForge() ? "forge" : "c";
                String compare = tags.stream().filter(t -> t.location().toString().contains("/") && t.location().getNamespace().equals(forge)).findFirst().map(t -> t.location().toString()).orElse("");
                if (compare.isEmpty()) return false;
                compare = compare.substring(0, compare.lastIndexOf("/"));

                boolean hasTag = stack.is(TagUtils.getItemTag(new ResourceLocation(compare)));
                return blacklist != hasTag;
            }).orElse(false);
        }
        return true;
    }
}