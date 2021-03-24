package muramasa.gti.loader.machines;

import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.EMPTY_CELLS;
import static muramasa.gti.data.Materials.*;
import static muramasa.gti.data.RecipeMaps.CHEMICAL_REACTING;

public class ChemicalReactorLoader {
    public static void init() {
        CHEMICAL_REACTING.RB().ii(AntimatterIngredient.of(
                DUST.get(Sulfur),1
        ),AntimatterIngredient.of(
                DUST.get(RawRubber),9
        )).fo(Rubber.getLiquid(144*9)).add(200,24);

        /* TITANIUM */
        CHEMICAL_REACTING.RB().ii(
                AntimatterIngredient.of(DUST.get(Rutile,1)),
                AntimatterIngredient.of(DUST.get(Carbon,2))
        ).fi(Chlorine.getGas(4000))
            .fo(Titaniumtetrachloride.getLiquid(1000))
                .add(25*20, 480);
        CHEMICAL_REACTING.RB().ii(
                AntimatterIngredient.of(DUST.get(MagnesiumChloride,2)),
                AntimatterIngredient.of(DUST.get(Carbon,1))
        ).io(DUST.get(Magnesium,1))
                .fo(Chlorine.getGas(3000))
                .add(20*15, 256);

        /* PLASTICS */
        EMPTY_CELLS.forEach(cell -> CHEMICAL_REACTING.RB().ii(
                AntimatterIngredient.of(cell.fill(Oxygen.getGas()))
        ).fi(Ethylene.getGas(144)).fo(Plastic.getLiquid(144)).add(8*20, 30));
    }
}
