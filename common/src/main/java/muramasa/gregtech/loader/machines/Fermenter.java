package muramasa.gregtech.loader.machines;
import static muramasa.gregtech.data.Materials.*;

import static muramasa.gregtech.data.RecipeMaps.FERMENTING;

public class Fermenter {
    public static void init() {
        FERMENTING.RB().fi(Biomass.getLiquid(100)).fo(FermentedBiomass.getLiquid(100)).add(100,2);
    }
}
