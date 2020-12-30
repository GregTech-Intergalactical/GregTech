package muramasa.gti.loader.machines.generator;

import static muramasa.gti.data.Materials.Steam;
import static muramasa.gti.data.RecipeMaps.STEAM_FUELS;

public class SteamFuels {
    public static void init() {
        STEAM_FUELS.RB().fi(Steam.getGas(2)).add(1,1);
    }
}
