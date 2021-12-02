package muramasa.gti.cover;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.MaterialType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

public class CoverTypeFilter extends BaseCover {

    public final MaterialType<?> type;
    public CoverTypeFilter(ICoverHandler<?> source, Tier tier, Direction side, CoverFactory factory, MaterialType<?> type) {
        super(source, tier, side, factory);
        this.type = type;
    }
    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }
    @Override
    public void onTransfer(Object object, boolean inputSide, boolean execute) {
        // TODO Auto-generated method stub
        super.onTransfer(object, inputSide, execute);
        if (object instanceof ItemStack) {
            ItemStack item = ((ItemStack)object);
            if (type.getMaterialFromStack(item) == null) {
                item.setCount(0);
            }
        }
        /*
        if (object instanceof FluidStack && type instanceof MaterialTypeFluid) {
            FluidStack item = ((FluidStack)object);
            MaterialTypeFluid typ = (MaterialTypeFluid) type;
            typ.allowGen(material)
            if (type.getMaterialFromStack(item) == null) {
                item.setCount(0);
            }
        }*/
    }

    
}
