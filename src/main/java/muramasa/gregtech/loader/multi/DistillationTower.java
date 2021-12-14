package muramasa.gregtech.loader.multi;

import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.DISTILLATION;

public class DistillationTower {
    public static void init() {
        DISTILLATION.RB().fi(Oil.getLiquid(100)).fo(SulfuricLightFuel.getLiquid(40), SulfuricHeavyFuel.getLiquid(20), SulfuricNaphtha.getLiquid(10), SulfuricGas.getGas(25),
                SulfuricAcid.getLiquid(5)).add(20, 384);
    }
}
