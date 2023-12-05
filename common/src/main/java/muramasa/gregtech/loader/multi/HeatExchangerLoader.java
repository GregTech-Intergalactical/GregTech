package muramasa.gregtech.loader.multi;

import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import io.github.gregtechintergalactical.gtcore.data.GTCoreFluids;
import tesseract.TesseractGraphWrappers;

import static muramasa.antimatter.data.AntimatterMaterials.Lava;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.HEAT_EXCHANGER;

public class HeatExchangerLoader {
    public static void init() {
        HEAT_EXCHANGER.RB().fi(HotCoolant.getLiquid(1))
                .fo(Coolant.getLiquid(1))
                .add("hot_coolant",20, 1);
        HEAT_EXCHANGER.RB().fi(Lava.getLiquid(1))
                .fo(FluidHooks.newFluidHolder(GTCoreFluids.PAHOEHOE_LAVA.getFluid(), TesseractGraphWrappers.dropletMultiplier, null))
                .add("lava", 5, 16);
        HEAT_EXCHANGER.RB().fi(HotCarbonDioxide.getGas(1)).fo(CarbonDioxide.getGas(1)).add("hot_carbon_dioxide", 20, 1);
    }
}
