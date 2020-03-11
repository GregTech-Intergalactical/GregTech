package muramasa.gti.cover;

import muramasa.antimatter.cover.Cover;
import muramasa.antimatter.machines.Tier;
import muramasa.gti.common.Data;
import net.minecraft.item.ItemStack;

public class CoverSolar extends Cover {

    protected Tier tier;

    public CoverSolar(Tier tier) {
        this.tier = tier;
    }

    @Override
    public String getId() {
        return "solar";
    }

    @Override
    public ItemStack getDroppedStack() {
        switch (tier.getId()) {
            case "ulv": return Data.SolarULV.get(1);
            case "lv": return Data.SolarLV.get(1);
            case "mv": return Data.SolarMV.get(1);
            case "hv": return Data.SolarHV.get(1);
            case "ev": return Data.SolarEV.get(1);
            case "iv": return Data.SolarIV.get(1);
            case "luv": return Data.SolarLUV.get(1);
            case "zpm": return Data.SolarZPM.get(1);
            case "uv": return Data.SolarUV.get(1);
            case "uhv": return Data.SolarUHV.get(1);
            case "ueu": return Data.SolarUEU.get(1);
            default: return Data.Solar.get(1);
        }
    }
}
