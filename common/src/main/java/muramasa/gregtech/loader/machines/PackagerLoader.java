package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;

import static muramasa.gregtech.data.RecipeMaps.PACKAGING;

public class PackagerLoader {
    public static void init() {
        for (Material material : AntimatterMaterialTypes.DUST.all()) {
            if (material.has(AntimatterMaterialTypes.DUST_TINY)) {
                PACKAGING.RB().ii(AntimatterMaterialTypes.DUST_TINY.getIngredient(material, 9)).io(AntimatterMaterialTypes.DUST.get(material).getDefaultInstance()).add(100, 2);
            }
            if (material.has(AntimatterMaterialTypes.DUST_SMALL)) {
                PACKAGING.RB().ii(AntimatterMaterialTypes.DUST_SMALL.getIngredient(material, 4)).io(AntimatterMaterialTypes.DUST.get(material).getDefaultInstance()).add(100, 2);
            }
        }

    }
}
