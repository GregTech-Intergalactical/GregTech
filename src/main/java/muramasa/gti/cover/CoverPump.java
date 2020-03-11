package muramasa.gti.cover;

import muramasa.antimatter.cover.Cover;
import muramasa.antimatter.machines.Tier;
import muramasa.gti.common.Data;
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
            case "lv": return Data.PumpLV.get(1);
            case "mv": return Data.PumpMV.get(1);
            case "hv": return Data.PumpHV.get(1);
            case "ev": return Data.PumpEV.get(1);
            case "iv": return Data.PumpIV.get(1);
            case "luv": return Data.PumpLUV.get(1);
            case "zpm": return Data.PumpZPM.get(1);
            case "uv": return Data.PumpUV.get(1);
            case "uhv": return Data.PumpUHV.get(1);
            case "ueu": return Data.PumpUEU.get(1);
            default: return Data.PumpULV.get(1);
        }
    }
}
