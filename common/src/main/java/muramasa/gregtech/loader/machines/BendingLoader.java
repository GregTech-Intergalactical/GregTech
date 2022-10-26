package muramasa.gregtech.loader.machines;

import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.Materials;

import static muramasa.antimatter.Data.*;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;
import static muramasa.gregtech.data.RecipeMaps.BENDING;

public class BendingLoader {
    public static void init() {
        PLATE.all().forEach(t -> {
            if (!t.has(INGOT)) return;
            long duration = Math.max(t.getMass(), 1);
            BENDING.RB().ii(INGOT.getMaterialIngredient(t,1),INT_CIRCUITS.get(1)).io(PLATE.get(t,1)).add(duration, 24);
        });
        FOIL.all().forEach(foil -> {
            if (!foil.has(PLATE)) return;
            long duration = Math.max(foil.getMass(),1);
            BENDING.RB().ii(PLATE.getMaterialIngredient(foil,1), INT_CIRCUITS.get(1)).io(FOIL.get(foil,4)).add(duration,24);
        });
        BENDING.RB().ii(PLATE.getMaterialIngredient(Materials.Tin,2), INT_CIRCUITS.get(4)).io(GregTechData.CellTin.getDefaultInstance()).add(80,24);
        BENDING.RB().ii(PLATE.getMaterialIngredient(Materials.Steel,2), INT_CIRCUITS.get(4)).io(GregTechData.CellSteel.getDefaultInstance()).add(80,96);
        BENDING.RB().ii(PLATE.getMaterialIngredient(Materials.TungstenSteel,2), INT_CIRCUITS.get(4)).io(GregTechData.CellTungstensteel.getDefaultInstance()).add(80,384);
    }
}
