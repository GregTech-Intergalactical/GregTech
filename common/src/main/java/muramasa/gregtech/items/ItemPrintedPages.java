package muramasa.gregtech.items;

import muramasa.antimatter.item.ItemBasic;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.StringUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemPrintedPages extends ItemBasic<ItemPrintedPages> {
    public ItemPrintedPages(String domain, String id) {
        super(domain, id);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        if (stack.hasTag()) {
            CompoundTag compoundTag = stack.getTag();
            String string = compoundTag.getString("title");
            if (!StringUtil.isNullOrEmpty(string)) {
                tooltipComponents.add(new TextComponent(string));
            }
            string = compoundTag.getString("author");
            if (!StringUtil.isNullOrEmpty(string)) {
                tooltipComponents.add((new TranslatableComponent("book.byAuthor", string)));
            }
        }
    }
}
