package muramasa.gregtech.cover.base;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoverFilter extends BaseCover {
    protected boolean blacklist = false;
    protected boolean ignoreNBT = false;
    public CoverFilter(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public ItemStack getDroppedStack() {
        ItemStack stack =  super.getDroppedStack();
        stack.getOrCreateTag().putBoolean("blacklist", blacklist);
        stack.getOrCreateTag().putBoolean("ignoreNBT", ignoreNBT);
        return stack;
    }

    @Override
    public void addInfoFromStack(ItemStack stack) {
        super.addInfoFromStack(stack);
        if (stack.getTag() == null) return;
        CompoundTag tag = stack.getTag();
        blacklist = tag.getBoolean("blacklist");
        ignoreNBT = tag.getBoolean("ignoreNBT");
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }
}
