package muramasa.gregtech.cover;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.blockentity.pipe.BlockEntityPipe;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.ButtonOverlay;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.cover.base.CoverFilter;
import muramasa.gregtech.gui.ButtonOverlays;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tesseract.util.ItemHandlerUtils;

public class CoverItemFilter extends CoverFilter {
    public CoverItemFilter(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        getGui().getSlots().add(SlotType.DISPLAY_SETTABLE, 79, 53);
        addGuiCallback(t -> {
            t.addSwitchButton(70, 34, 16, 16, ButtonOverlay.WHITELIST, ButtonOverlay.BLACKLIST, h -> blacklist, true, b -> "tooltip.gti." + (b ? "blacklist" : "whitelist"));
            t.addSwitchButton(88, 34, 16, 16, ButtonOverlays.NBT_OFF, ButtonOverlays.NBT_ON, h -> !ignoreNBT, true, b -> "tooltip.gti.nbt." + (b ? "on" : "off"));
        });;
    }

    @Override
    public void clearFilter(){
        super.clearFilter();
        getInventory(SlotType.DISPLAY_SETTABLE).clearContent();
    }

    @Override
    public <T> boolean blocksCapability(Class<T> cap, @Nullable Direction side) {
        return false;
    }

    @Override
    public boolean onTransfer(Object object, boolean inputSide, boolean simulate) {
        super.onTransfer(object, inputSide, simulate);
        if (object instanceof ItemStack item) {
            ItemStack filter = getInventory(SlotType.DISPLAY_SETTABLE).getItem(0);
            boolean empty = filter.isEmpty();
            if (empty) {
                if (!blacklist) {
                    return true;
                }
            }
            boolean matches = ignoreNBT ? item.is(filter.getItem()) : ItemHandlerUtils.canItemStacksStack(item, filter);
            if (blacklist == matches){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){
            GuiEvents.GuiEvent ev = (GuiEvents.GuiEvent) event;
            if (ev.data[1] == 0){
                blacklist = !blacklist;
            } else if (ev.data[1] == 1){
                ignoreNBT = !ignoreNBT;
            }
        }
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
