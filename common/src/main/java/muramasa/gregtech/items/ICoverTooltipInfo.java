package muramasa.gregtech.items;

import muramasa.antimatter.machine.BlockMachine;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@FunctionalInterface
public interface ICoverTooltipInfo {
    void getTooltips(ItemStack stack, @Nullable BlockGetter world, List<Component> tooltip, TooltipFlag flag);
}
