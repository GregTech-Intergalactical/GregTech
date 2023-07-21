package muramasa.gregtech.loader.multi;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.FluidIngredient;
import muramasa.gregtech.data.Materials;
import net.minecraft.world.level.material.Fluids;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;

import static muramasa.gregtech.data.RecipeMaps.HEAT_EXCHANGING;

public class HeatExchangerLoader {
    public static void init() {
        HEAT_EXCHANGING.RB().fi(FluidIngredient.of(FluidPlatformUtils.createFluidStack(Fluids.WATER, 150 * TesseractGraphWrappers.dropletMultiplier)))
                .fo(Materials.Steam.getGas(2000))
                .add("steam",60, 30);
        HEAT_EXCHANGING.RB().fi(FluidIngredient.of(Materials.DistilledWater.getLiquid(100)))
                .fo(Materials.Steam.getGas(2000))
                .add("steam_1",60, 25);
    }
}
