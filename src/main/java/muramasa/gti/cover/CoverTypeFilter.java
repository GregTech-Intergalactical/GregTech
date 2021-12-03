package muramasa.gti.cover;

import java.util.function.BiConsumer;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.MaterialType;
import muramasa.antimatter.texture.Texture;
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
    public void setTextures(BiConsumer<String, Texture> texer) {
        texer.accept("overlay", factory.getTextures().iterator().next());
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }
    @Override
    public void onTransfer(Object object, boolean inputSide, boolean execute) {
        super.onTransfer(object, inputSide, execute);
        if (object instanceof ItemStack) {
            ItemStack item = ((ItemStack)object);
            if (type.getMaterialFromStack(item) == null) {
                item.setCount(0);
            }
        }
    }

    
}
