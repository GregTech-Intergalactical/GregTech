package muramasa.gregtech.cover;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.Tier;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tesseract.util.ItemHandlerUtils;

public class CoverFluidFilter extends BaseCover {
    boolean blacklist = false;
    boolean ignoreNBt = false;
    public CoverFluidFilter(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        getGui().getSlots().add(SlotType.DISPLAY_SETTABLE, 80, 25);
    }

    @Override
    public <T> boolean blocksCapability(Class<T> cap, @Nullable Direction side) {
        return false;
    }

    @Override
    public void onTransfer(Object object, boolean inputSide, boolean execute) {
        super.onTransfer(object, inputSide, execute);
        if (object instanceof FluidHolder fluidHolder) {
            ItemStack filter = getInventory(SlotType.DISPLAY_SETTABLE).getItem(0);
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
                    fluidHolder.setAmount(0);
                }
                return;
            }
            boolean matches = FluidHooks.safeGetItemFluidManager(filter).map(f -> {
                for (int i = 0; i < f.getTankAmount(); i++){
                    boolean match = ignoreNBt ? fluidHolder.getFluid() == f.getFluidInTank(i).getFluid() : f.getFluidInTank(i).matches(fluidHolder);
                    if (match){
                        return true;
                    }
                }
                return false;
            }).orElse(false);
            if (blacklist == matches){
                fluidHolder.setAmount(0);
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
