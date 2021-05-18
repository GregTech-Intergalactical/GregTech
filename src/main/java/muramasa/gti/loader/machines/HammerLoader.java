package muramasa.gti.loader.machines;


import static muramasa.antimatter.Data.INGOT;
import static muramasa.antimatter.Data.PLATE;
import static muramasa.gti.data.RecipeMaps.HAMMERING;

public class HammerLoader {
    public static void init() {
        PLATE.all().forEach(plate -> {
            if (!plate.has(INGOT)) return;

            HAMMERING.RB().ii(INGOT.getMaterialIngredient(plate, 3)).io(PLATE.get(plate, 2)).add(plate.getMass()*2, 13);
        });
    }
}
