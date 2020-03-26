package muramasa.gti.cover;

import muramasa.antimatter.cover.Cover;
import muramasa.antimatter.machine.Tier;
import muramasa.gti.data.Data;
import net.minecraft.item.ItemStack;

public class CoverPump extends Cover {

    protected Tier tier;

    public CoverPump(Tier tier) {
        this.tier = tier;
    }

    @Override
    public String getId() {
        return "pump";
    }

    @Override
    public ItemStack getDroppedStack() {
        switch (tier.getId()) {
            case "lv": return new ItemStack(Data.PumpLV.get());
            case "mv": return new ItemStack(Data.PumpMV.get());
            case "hv": return new ItemStack(Data.PumpHV.get());
            case "ev": return new ItemStack(Data.PumpEV.get());
            case "iv": return new ItemStack(Data.PumpIV.get());
            default: return new ItemStack(Data.PumpLV.get());
        }
    }
}
