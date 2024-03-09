package muramasa.gregtech.cover;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
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
import muramasa.gregtech.cover.base.CoverFilter;
import muramasa.gregtech.gui.ButtonOverlays;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoverFluidFilter extends CoverFilter {
    public CoverFluidFilter(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        getGui().getSlots().add(SlotType.FLUID_DISPLAY_SETTABLE, 79, 53);
        addGuiCallback(t -> {
            t.addSwitchButton(70, 34, 16, 16, ButtonOverlay.WHITELIST, ButtonOverlay.BLACKLIST,h -> blacklist, true, b -> "tooltip.gti." + (b ? "blacklist" : "whitelist"));
            t.addCycleButton(88, 34, 16, 15, h -> ((CoverFluidFilter)h).filterMode, true, i -> "tooltip.gti.filter_mode." + i, ButtonOverlay.EXPORT_IMPORT, ButtonOverlay.IMPORT, ButtonOverlay.EXPORT);
        });
    }
    @Override
    public void clearFilter(){
        super.clearFilter();
        getInventory(SlotType.FLUID_DISPLAY_SETTABLE).clearContent();
    }

    @Override
    public <T> boolean blocksCapability(Class<T> cap, @Nullable Direction side) {
        return false;
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){
            GuiEvents.GuiEvent ev = (GuiEvents.GuiEvent) event;
            if (ev.data[1] == 0){
                blacklist = !blacklist;
                this.handler.getTile().setChanged();
            } else if (ev.data[1] == 1){
                if (filterMode == 0){
                    filterMode = 1;
                } else if (filterMode == 1){
                    filterMode = 2;
                } else {
                    filterMode = 0;
                }
                this.handler.getTile().setChanged();
            }
        }
    }

    @Override
    public boolean onTransfer(Object object, boolean inputSide, boolean simulate) {
        super.onTransfer(object, inputSide, simulate);
        if (object instanceof FluidHolder fluidHolder) {
            if ((filterMode == 1 && !inputSide) || (filterMode == 2 && inputSide)) return false;
            ItemStack filter = getInventory(SlotType.FLUID_DISPLAY_SETTABLE).getItem(0);
            boolean empty = filter.isEmpty() || FluidHooks.safeGetItemFluidManager(filter).map(f -> {
                for (int i = 0; i < f.getTankAmount(); i++){
                    if (!f.getFluidInTank(i).isEmpty()){
                        return false;
                    }
                }
                return true;
            }).orElse(true);
            if (empty) {
                if (!blacklist) {
                    return true;
                }
            }
            boolean matches = FluidHooks.safeGetItemFluidManager(filter).map(f -> {
                for (int i = 0; i < f.getTankAmount(); i++){
                    boolean match = ignoreNBT ? fluidHolder.getFluid() == f.getFluidInTank(i).getFluid() : f.getFluidInTank(i).matches(fluidHolder);
                    if (match){
                        return true;
                    }
                }
                return false;
            }).orElse(false);
            if (blacklist == matches){
                return true;
            }
        }
        return false;
    }

    @Override
    protected String getRenderId() {
        return "fluid_filter";
    }

    @Override
    public String getId() {
        return "fluid_filter";
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }
}
