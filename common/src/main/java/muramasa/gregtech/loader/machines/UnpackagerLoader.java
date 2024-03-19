package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.gregtech.data.TierMaps;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.gregtech.data.RecipeMaps.UNPACKAGER;

public class UnpackagerLoader {
    public static void init() {
        for (Material material : DUST.all()) {
            if (material.has(DUST_TINY)) {
                UNPACKAGER.RB().ii(DUST.getMaterialIngredient(material, 1), TierMaps.INT_CIRCUITS.get(9)).io(DUST_TINY.get(material, 9)).add("dust_tiny_" + material.getId(),100, 2);
            }
            if (material.has(DUST_SMALL)) {
                UNPACKAGER.RB().ii(DUST.getMaterialIngredient(material, 1), TierMaps.INT_CIRCUITS.get(4)).io(DUST_SMALL.get(material, 4)).add("dust_small_" + material.getId(),100, 2);
            }
        }

    }
}
