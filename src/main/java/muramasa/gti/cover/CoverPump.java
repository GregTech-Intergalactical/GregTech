package muramasa.gti.cover;

import muramasa.antimatter.cover.Cover;
import muramasa.antimatter.cover.CoverTiered;
import muramasa.antimatter.machine.Tier;
import muramasa.gti.Ref;
import muramasa.gti.data.Data;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class CoverPump extends CoverTiered {

    protected Tier tier;

    public static String ID = "pump";

    public CoverPump(Tier tier) {
        this.tier = tier;
    }

    @Override
    public String getId() {
        return "pump";
    }

    @Override
    protected String ID() {
        return null;
    }

    @Override
    protected CoverTiered getTiered(Tier tier) {
        return null;
    }

    @Override
    public ItemStack getDroppedStack() {
      /*  switch (tier.getId()) {
            case "lv": return Data.PumpLV.get(1);
            case "mv": return Data.PumpMV.get(1);
            case "hv": return Data.PumpHV.get(1);
            case "ev": return Data.PumpEV.get(1);
            case "iv": return Data.PumpIV.get(1);
            default: return Data.PumpLV.get(1);
        }*/
        return null;
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }


}
