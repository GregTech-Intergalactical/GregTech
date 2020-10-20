package muramasa.gti.cover;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.cover.CoverInstance;
import muramasa.antimatter.cover.CoverTiered;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.Utils;
import muramasa.gti.Ref;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

import java.util.EnumMap;
import java.util.Map;

import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class CoverConveyor extends CoverTiered {

    public static String ID = "conveyor";

    static final Map<Tier, Integer> speeds = ImmutableMap.<Tier,Integer>builder().
            put(Tier.LV,400)
            .put(Tier.MV, 100)
            .put(Tier.HV, 20)
            .put(Tier.EV, 10)
            .put(Tier.IV, 1).build();

    public CoverConveyor(Tier tier) {
        super(tier);
    }

    public CoverConveyor() {
        super();
    }

    //Useful for using the same model for multiple tiers where id is dependent on tier.
    @Override
    protected String getRenderId() {
        return ID();
    }

    @Override
    protected String ID() {
        return ID;
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    @Override
    public void onUpdate(CoverInstance instance, Direction side) {
        if (instance.getTile() == null || instance.getTile().getWorld().getGameTime() % (speeds.get(tier)) != 0)
            return;
        TileEntity adjTile = instance.getTile().getWorld().getTileEntity(instance.getTile().getPos().offset(side));
        if (adjTile == null) return;
        if (instance.getTile() instanceof TileEntityMachine) {
            ((TileEntityMachine)instance.getTile()).itemHandler.ifPresent(ih -> adjTile.getCapability(ITEM_HANDLER_CAPABILITY).ifPresent(other -> Utils.transferItems(ih.getOutputHandler(), other)));
        } else {
            Utils.transferItemsOnCap(instance.getTile(), adjTile);
        }
    }

    @Override
    protected CoverTiered getTiered(Tier tier) {
        return new CoverConveyor(tier);
    }
}
