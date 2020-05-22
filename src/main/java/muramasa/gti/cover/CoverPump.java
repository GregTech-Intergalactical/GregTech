package muramasa.gti.cover;

import muramasa.antimatter.cover.Cover;
import muramasa.antimatter.cover.CoverInstance;
import muramasa.antimatter.cover.CoverTiered;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.Utils;
import muramasa.gti.Ref;
import muramasa.gti.data.Data;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class CoverPump extends CoverTiered {

    public static String ID = "pump";

    static int[] speeds = new int[]{640,2560,10240,40960,163840};

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
    public void onUpdate(CoverInstance instance, Direction side) {
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
