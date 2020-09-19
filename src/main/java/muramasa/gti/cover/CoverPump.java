package muramasa.gti.cover;

import muramasa.antimatter.cover.CoverInstance;
import muramasa.antimatter.cover.CoverTiered;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.Utils;
import muramasa.gti.Ref;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

public class CoverPump extends CoverTiered {

    public static String ID = "pump";

    static int[] speeds = new int[]{640, 2560, 10240, 40960, 163840};

    private CoverPump(Tier tier) {
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
    public void onUpdate(CoverInstance<?> instance, Direction side) {
        if (instance.getTile() == null || instance.getTile().getWorld().getGameTime() % (20) != 0) return;
        TileEntity adjTile = instance.getTile().getWorld().getTileEntity(instance.getTile().getPos().offset(side));
        if (adjTile == null) return;
        Utils.transferFluidsOnCap(instance.getTile(), adjTile, speeds[getTier().getIntegerId()]);
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }
}
