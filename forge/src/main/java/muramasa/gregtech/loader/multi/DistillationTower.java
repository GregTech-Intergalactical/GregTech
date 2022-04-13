package muramasa.gregtech.loader.multi;

import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.DISTILLATION;

public class DistillationTower {
    public static void init() {
        DISTILLATION.RB().fi(Oil.getLiquid(100)).fo(SulfuricLightFuel.getLiquid(100), SulfuricHeavyFuel.getLiquid(30), SulfuricNaphtha.getLiquid(40), SulfuricGas.getGas(120),
                SulfuricAcid.getLiquid(20)).add(15, 384);
        DISTILLATION.RB().fi(OilMedium.getLiquid(100)).fo(SulfuricLightFuel.getLiquid(100), SulfuricHeavyFuel.getLiquid(30), SulfuricNaphtha.getLiquid(40), SulfuricGas.getGas(120),
                SulfuricAcid.getLiquid(20)).add(15, 384);
        DISTILLATION.RB().fi(OilHeavy.getLiquid(100)).fo(SulfuricLightFuel.getLiquid(20), SulfuricHeavyFuel.getLiquid(200), SulfuricNaphtha.getLiquid(10), SulfuricGas.getGas(100),
                SulfuricAcid.getLiquid(30)).add(20, 384);
        DISTILLATION.RB().fi(OilLight.getLiquid(125)).fo(SulfuricLightFuel.getLiquid(150), SulfuricHeavyFuel.getLiquid(10), SulfuricNaphtha.getLiquid(30), SulfuricGas.getGas(80),
                SulfuricAcid.getLiquid(5)).add(10, 384);
    }
}
