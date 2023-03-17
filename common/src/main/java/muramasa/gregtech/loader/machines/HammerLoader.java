package muramasa.gregtech.loader.machines;

import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.gregtech.data.Materials.AnnealedCopper;
import static muramasa.gregtech.data.Materials.WroughtIron;
import static muramasa.gregtech.data.RecipeMaps.HAMMERING;

public class HammerLoader {
    public static void init() {
        PLATE.all().forEach(plate -> {
            if (!plate.has(INGOT)) return;
            HAMMERING.RB().ii(INGOT.getMaterialIngredient(plate, 3)).io(PLATE.get(plate, 2)).add("plate_" + plate.getId(),plate.getHardness()/20, 13);
        });
        //Wrought Iron and Annealed Copper 2 to 1 (pre Arc Furnace)
        HAMMERING.RB().ii(DUST.getMaterialIngredient(Iron, 2)).io(DUST.get(WroughtIron, 1)).add("wrought_iron_pre_arc",Iron.getHardness()/20, 10);
        HAMMERING.RB().ii(DUST.getMaterialIngredient(Copper, 2)).io(DUST.get(AnnealedCopper, 1)).add("annealed_copper_pre_arc",AnnealedCopper.getHardness()/20, 10);
    }
}
