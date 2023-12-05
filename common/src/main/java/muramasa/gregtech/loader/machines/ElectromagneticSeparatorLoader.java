package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.gregtech.data.GregTechMaterialTags;
import muramasa.gregtech.data.Materials;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.gregtech.data.RecipeMaps.ELECTROMAGNETIC_SEPARATOR;

public class ElectromagneticSeparatorLoader {
    public static void init(){
        GregTechMaterialTags.ELECSEPI.all().forEach(m -> {
            ELECTROMAGNETIC_SEPARATOR.RB().ii(DUST_PURE.getIngredient(m, 1)).io(DUST.get(m, 1), DUST_SMALL.get(AntimatterMaterials.Iron, 1), NUGGET.get(AntimatterMaterials.Iron, 1))
                    .chances(1.0, 0.4, 0.2)
                    .add(m.getId() + "_iron", 400, 24);
        });
        GregTechMaterialTags.ELECSEPG.all().forEach(m -> {
            ELECTROMAGNETIC_SEPARATOR.RB().ii(DUST_PURE.getIngredient(m, 1)).io(DUST.get(m, 1), DUST_SMALL.get(AntimatterMaterials.Gold, 1), NUGGET.get(AntimatterMaterials.Gold, 1))
                    .chances(1.0, 0.4, 0.2)
                    .add(m.getId() + "_gold", 400, 24);
        });
        GregTechMaterialTags.ELECSEPN.all().forEach(m -> {
            ELECTROMAGNETIC_SEPARATOR.RB().ii(DUST_PURE.getIngredient(m, 1)).io(DUST.get(m, 1), DUST_SMALL.get(Materials.Neodymium, 1), NUGGET.get(Materials.Neodymium, 1))
                    .chances(1.0, 0.4, 0.2)
                    .add(m.getId() + "_neodymium", 400, 24);
        });
    }
}
