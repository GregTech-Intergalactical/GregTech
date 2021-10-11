package muramasa.gti.cover;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.cover.CoverTiered;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.Utils;
import muramasa.gti.Ref;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.Map;

public class CoverPump extends CoverTiered {

    public static String ID = "pump";

    static final Map<Tier, Integer> speeds = ImmutableMap.<Tier,Integer>builder().
    put(Tier.LV,640/20)
    .put(Tier.MV, 2560/20)
    .put(Tier.HV, 10240/20)
    .put(Tier.EV, 4096010/20)
    .put(Tier.IV, 163840/20).build();

    public CoverPump(Tier tier) {
        super(tier);
    }

    public CoverPump() {
        super();
    }

    @Override
    protected String ID() {
        return ID;
    }

    @Override
    protected CoverTiered getTiered(Tier tier) {
        return new CoverPump(tier);
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir, Direction facing) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicDepthModel();
    }

    @Override
    public <T> boolean blocksCapability(CoverStack<?> stack, Capability<T> cap, Direction side) {
        return side == null && cap != CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
    }

    @Override
    public void onUpdate(CoverStack<?> instance, Direction side) {
        //Pump acts on each tick.
        if (instance.getTile() == null) return;
        TileEntity adjTile = instance.getTile().getWorld().getTileEntity(instance.getTile().getPos().offset(side));
        if (adjTile == null) return;
        LazyOptional<IFluidHandler> handler = adjTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side.getOpposite());
        if (!handler.isPresent()) return;
        instance.getTile().getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side).ifPresent(ih -> handler.ifPresent(other -> Utils.transferFluids(ih, other,speeds.get(tier))));
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    @Override
    protected String getRenderId() {
        return ID();
    }
}
