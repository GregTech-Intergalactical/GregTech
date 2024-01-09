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
                .add("hot_coolant",1, 20);
        HEAT_EXCHANGER.RB().fi(Lava.getLiquid(1))
                .fo(FluidHooks.newFluidHolder(GTCoreFluids.PAHOEHOE_LAVA.getFluid(), TesseractGraphWrappers.dropletMultiplier, null))
                .add("lava", 1, 80);
        HEAT_EXCHANGER.RB().fi(HotCarbonDioxide.getGas(1)).fo(CarbonDioxide.getGas(1)).add("hot_carbon_dioxide", 1, 20);
        HEAT_EXCHANGER.RB().fi(HotMoltenSodium.getLiquid(1)).fo(Sodium.getLiquid(1)).add("hot_molten_sodium", 1, 30);
        HEAT_EXCHANGER.RB().fi(HotMoltenTin.getLiquid(1)).fo(Tin.getLiquid(1)).add("hot_molten_tin", 1, 40);
        HEAT_EXCHANGER.RB().fi(HotMoltenLithiumChloride.getLiquid(1)).fo(LithiumChloride.getLiquid(1)).add("hot_molten_lithium_chloride", 1, 15);
        HEAT_EXCHANGER.RB().fi(HotHeavyWater.getLiquid(1)).fo(HeavyWater.getLiquid(1)).add("hot_heavy_water", 1, 50);
        HEAT_EXCHANGER.RB().fi(HotSemiheavyWater.getLiquid(1)).fo(SemiheavyWater.getLiquid(1)).add("hot_semiheavy_water", 1, 40);
        HEAT_EXCHANGER.RB().fi(HotTritiatedWater.getLiquid(1)).fo(TritiatedWater.getLiquid(1)).add("hot_tritiated_water", 1, 60);
        HEAT_EXCHANGER.RB().fi(HotHelium.getGas(1)).fo(Helium.getGas(1)).add("hot_helium", 30, 1);
    }
}
