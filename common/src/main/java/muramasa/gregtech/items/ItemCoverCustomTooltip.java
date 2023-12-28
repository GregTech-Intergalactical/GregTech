package muramasa.gregtech.items;

import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.ITooltipInfo;
import muramasa.antimatter.machine.Tier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemCoverCustomTooltip extends ItemCover {
    final ICoverTooltipInfo tooltipInfo;
    public ItemCoverCustomTooltip(String domain, String id, ICoverTooltipInfo tooltipInfo) {
        super(domain, id);
        this.tooltipInfo = tooltipInfo;
    }

    public ItemCoverCustomTooltip(String domain, String id, ICoverTooltipInfo tooltipInfo, Tier tier) {
        super(domain, id, tier);
        this.tooltipInfo = tooltipInfo;
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        tooltipInfo.getTooltips(stack, level, tooltipComponents, isAdvanced);
    }
}
