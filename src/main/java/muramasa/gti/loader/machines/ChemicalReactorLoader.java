package muramasa.gti.loader.machines;

import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;

import static muramasa.antimatter.Data.DUST;
import static muramasa.gti.data.Materials.*;
import static muramasa.gti.data.RecipeMaps.CHEMICAL_REACTING;

public class ChemicalReactorLoader {
    public static void init() {
        CHEMICAL_REACTING.RB().ii(AntimatterIngredient.of(
                DUST.get(Sulfur),1
        ),AntimatterIngredient.of(
                DUST.get(RawRubber),9
        )).fo(Rubber.getLiquid(144*9)).add(200,24);
    }
}
