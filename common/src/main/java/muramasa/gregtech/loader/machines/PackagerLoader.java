package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;

import static muramasa.gregtech.data.RecipeMaps.PACKAGER;

public class PackagerLoader {
    public static void init() {
        for (Material material : AntimatterMaterialTypes.DUST.all()) {
            if (material.has(AntimatterMaterialTypes.DUST_TINY)) {
                PACKAGER.RB().ii(AntimatterMaterialTypes.DUST_TINY.getIngredient(material, 9)).io(AntimatterMaterialTypes.DUST.get(material).getDefaultInstance()).add("dust_" + material.getId() + "_from_tiny",100, 2);
            }
            if (material.has(AntimatterMaterialTypes.DUST_SMALL)) {
                PACKAGER.RB().ii(AntimatterMaterialTypes.DUST_SMALL.getIngredient(material, 4)).io(AntimatterMaterialTypes.DUST.get(material).getDefaultInstance()).add("dust_" + material.getId() + "_from_small",100, 2);
            }
        }

    }
}
