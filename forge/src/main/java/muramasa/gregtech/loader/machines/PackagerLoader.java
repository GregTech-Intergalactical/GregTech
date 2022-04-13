package muramasa.gregtech.loader.machines;

import muramasa.antimatter.Data;
import muramasa.antimatter.material.Material;

import static muramasa.antimatter.Data.*;
import static muramasa.gregtech.data.Machines.PACKAGER;
import static muramasa.gregtech.data.RecipeMaps.PACKAGING;

public class PackagerLoader {
    public static void init() {
        for (Material material : Data.DUST.all()) {
            if (material.has(DUST_TINY)) {
                PACKAGING.RB().ii(DUST_TINY.getIngredient(material, 9)).io(DUST.get(material).getDefaultInstance()).add(100, 2);
            }
            if (material.has(DUST_SMALL)) {
                PACKAGING.RB().ii(DUST_SMALL.getIngredient(material, 4)).io(DUST.get(material).getDefaultInstance()).add(100, 2);
            }
        }

    }
}
