package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.gregtech.data.GregTechMaterialTags;
import muramasa.gregtech.data.Materials;
import muramasa.gregtech.data.RecipeMaps;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.gregtech.data.RecipeMaps.ELECTROMAGNETIC_SEPARATING;

public class ElectromagneticSeparatorLoader {
    public static void init(){
        GregTechMaterialTags.ELECTROMAGNETIC_SEPARATOR_IRON.all().forEach(m -> {
            ELECTROMAGNETIC_SEPARATING.RB().ii(DUST_PURE.getIngredient(m, 1)).io(DUST.get(m, 1), DUST_SMALL.get(AntimatterMaterials.Iron, 1), NUGGET.get(AntimatterMaterials.Iron, 1))
                    .chances(1.0, 0.4, 0.2)
                    .add(m.getId() + "_iron", 400, 24);
        });
        GregTechMaterialTags.ELECTROMAGNETIC_SEPARATOR_GOLD.all().forEach(m -> {
            ELECTROMAGNETIC_SEPARATING.RB().ii(DUST_PURE.getIngredient(m, 1)).io(DUST.get(m, 1), DUST_SMALL.get(AntimatterMaterials.Gold, 1), NUGGET.get(AntimatterMaterials.Gold, 1))
                    .chances(1.0, 0.4, 0.2)
                    .add(m.getId() + "_gold", 400, 24);
        });
        GregTechMaterialTags.ELECTROMAGNETIC_SEPARATOR_NEODYMIUM.all().forEach(m -> {
            ELECTROMAGNETIC_SEPARATING.RB().ii(DUST_PURE.getIngredient(m, 1)).io(DUST.get(m, 1), DUST_SMALL.get(Materials.Neodymium, 1), NUGGET.get(Materials.Neodymium, 1))
                    .chances(1.0, 0.4, 0.2)
                    .add(m.getId() + "_neodymium", 400, 24);
        });
    }
}
