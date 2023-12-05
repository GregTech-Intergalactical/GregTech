package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterials;

import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.FLUID_HEATER;

public class FluidHeaterLoader {
    public static void init(){
        FLUID_HEATER.RB().fi(Acetone.getLiquid(100)).fo(Ethenone.getGas(50)).add("ethenone", 16, 30);
        FLUID_HEATER.RB().fi(CalciumAcetateSolution.getLiquid(180)).fo(Acetone.getLiquid(100)).add("acetone", 16, 30);
        FLUID_HEATER.RB().fi(DistilledWater.getLiquid(6)).fo(Steam.getGas(960)).add("steam", 30, 32);
        FLUID_HEATER.RB().fi(AntimatterMaterials.Water.getLiquid(6)).fo(Steam.getGas(960)).add("steam_2", 30, 32);
    }
}
