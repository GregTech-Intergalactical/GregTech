package muramasa.gregtech.loader.machines;


import static muramasa.antimatter.data.AntimatterMaterialTypes.INGOT;
import static muramasa.antimatter.data.AntimatterMaterialTypes.PLATE;
import static muramasa.gregtech.data.RecipeMaps.HAMMERING;

public class HammerLoader {
    public static void init() {
        PLATE.all().forEach(plate -> {
            if (!plate.has(INGOT)) return;

            HAMMERING.RB().ii(INGOT.getMaterialIngredient(plate, 3)).io(PLATE.get(plate, 2)).add("plate_" + plate.getId(),plate.getMass()*2, 13);
        });
    }
}
