package muramasa.gti.loader.machines.generator;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import muramasa.gti.data.Materials;

import static muramasa.antimatter.Data.GAS;
import static muramasa.antimatter.Data.LIQUID;
import static muramasa.gti.data.Materials.Steam;
import static muramasa.gti.data.RecipeMaps.*;

public class Fuels {
    public static void init() {
        AntimatterAPI.all(Material.class, mat -> {
            if (mat != Materials.Steam && mat.getFuelPower() > 0) {
                if (mat.has(LIQUID))
                    COMBUSTION_FUELS.RB().fi(mat.getLiquid(1)).add(1, mat.getFuelPower());
                if (mat.has(GAS)) {
                    GAS_FUELS.RB().fi(mat.getGas(1)).add(1, mat.getFuelPower());
                }
            }
        });
        STEAM_FUELS.RB().fi(Steam.getGas(2)).add(1,1);
    }
}
