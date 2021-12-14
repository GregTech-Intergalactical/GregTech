package muramasa.gregtech.loader.machines;

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
    }
}
