package muramasa.gregtech.cover;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tesseract.util.ItemHandlerUtils;

public class CoverItemFilter extends BaseCover {
    boolean blacklist = false;
    boolean ignoreNBt = false;
    public CoverItemFilter(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        getGui().getSlots().add(SlotType.DISPLAY_SETTABLE, 80, 25);
    }

    @Override
    public <T> boolean blocksCapability(Class<T> cap, @Nullable Direction side) {
        return false;
    }

    @Override
    public boolean onTransfer(Object object, boolean inputSide, boolean execute) {
        super.onTransfer(object, inputSide, execute);
        if (object instanceof ItemStack item) {
            boolean stop = false;
            ItemStack filter = getInventory(SlotType.DISPLAY_SETTABLE).getItem(0);
            boolean empty = filter.isEmpty();
            if (empty) {
                if (!blacklist) {
                    return true;
                }
            }
            boolean matches = ignoreNBt ? item.is(filter.getItem()) : ItemHandlerUtils.canItemStacksStack(item, filter);
            if (blacklist == matches){
                return true;
            }
        }
        return false;
    }

    @Override
    protected String getRenderId() {
        return "item_filter";
    }

    @Override
    public String getId() {
        return "item_filter";
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }
}
