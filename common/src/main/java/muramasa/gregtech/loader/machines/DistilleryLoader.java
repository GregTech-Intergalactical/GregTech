package muramasa.gregtech.loader.machines;

import muramasa.antimatter.material.Material;

import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.*;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class DistilleryLoader {
    public static void init() {
        DISTILLING.RB().fi(Creosote.getLiquid(50)).fo(Lubricant.getLiquid(25)).add(40,24);
        add_oil(Oil,100);
        add_oil(OilLight,150);
        add_oil(OilMedium,50);
        add_oil(OilHeavy,150);
    }

    private static void add_oil(Material fluid, int x){
        DISTILLING.RB().ii(INT_CIRCUITS.get(1)).fi(fluid.getLiquid(x)).fo(SulfuricLightFuel.getLiquid(x/2)).add(40,24);
        DISTILLING.RB().ii(INT_CIRCUITS.get(2)).fi(fluid.getLiquid(x)).fo(SulfuricHeavyFuel.getLiquid(x)).add(80,48);
        DISTILLING.RB().ii(INT_CIRCUITS.get(3)).fi(fluid.getLiquid(x)).fo(SulfuricNaphtha.getLiquid(x/2)).add(40,24);
        DISTILLING.RB().ii(INT_CIRCUITS.get(4)).fi(fluid.getLiquid(x)).fo(SulfuricGas.getGas(2*x)).add(20,12);
        if (fluid!=OilMedium){
            DISTILLING.RB().ii(INT_CIRCUITS.get(0)).fi(fluid.getLiquid(x)).fo(OilMedium.getLiquid(x)).add(40,24);
        }
    }
}