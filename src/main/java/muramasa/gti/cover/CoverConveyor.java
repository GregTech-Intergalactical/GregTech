package muramasa.gti.cover;

import muramasa.antimatter.cover.CoverInstance;
import muramasa.antimatter.cover.CoverTiered;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.Utils;
import muramasa.gti.Ref;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

public class CoverConveyor extends CoverTiered {

    public static String ID = "conveyor";

    static int[] speeds = {400, 100, 20, 10, 1};

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
        if (instance.getTile() == null || instance.getTile().getWorld().getGameTime() % (speeds[tier.getIntegerId()]) != 0)
            return;
        TileEntity adjTile = instance.getTile().getWorld().getTileEntity(instance.getTile().getPos().offset(side));
        if (adjTile == null) return;
        //DEBUG, just puts this item
        //((TileEntityMachine)instance.getTile()).itemHandler.get().addOutputs(new ItemStack(this.getItem(),1));
        Utils.transferItemsOnCap(instance.getTile(), adjTile);
    }

    @Override
    protected CoverTiered getTiered(Tier tier) {
        return new CoverConveyor(tier);
    }
}
