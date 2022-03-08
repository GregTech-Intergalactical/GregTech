package muramasa.gregtech.tile.single;

import net.minecraft.world.item.ItemStack;

public interface IFilterable {
    boolean accepts(ItemStack stack);
}