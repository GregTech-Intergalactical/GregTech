package muramasa.gregtech.loader.multi;

import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import io.github.gregtechintergalactical.gtcore.data.GTCoreFluids;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.FluidIngredient;
import muramasa.gregtech.data.Materials;
import net.minecraft.world.level.material.Fluids;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;

import static muramasa.antimatter.data.AntimatterMaterials.Lava;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.HEAT_EXCHANGING;

public class HeatExchangerLoader {
    public static void init() {
        HEAT_EXCHANGING.RB().fi(HotCoolant.getLiquid(1))
                .fo(Coolant.getLiquid(1))
                .add("hot_coolant",20, 1);
        HEAT_EXCHANGING.RB().fi(Lava.getLiquid(1))
                .fo(FluidHooks.newFluidHolder(GTCoreFluids.PAHOEHOE_LAVA.getFluid(), TesseractGraphWrappers.dropletMultiplier, null))
                .add("lava", 5, 16);
        HEAT_EXCHANGING.RB().fi(HotCarbonDioxide.getGas(1)).fo(CarbonDioxide.getGas(1)).add("hot_carbon_dioxide", 20, 1);
    }
}
