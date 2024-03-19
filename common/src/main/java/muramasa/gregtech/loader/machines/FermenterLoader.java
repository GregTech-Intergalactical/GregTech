package muramasa.gregtech.loader.machines;
import static muramasa.gregtech.data.Materials.*;

import static muramasa.gregtech.data.RecipeMaps.FERMENTER;

public class FermenterLoader {
    public static void init() {
        FERMENTER.RB().fi(Biomass.getLiquid(100)).fo(FermentedBiomass.getLiquid(100)).add("fermented_biomass",100,2);
    }
}
