package muramasa.gti.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import net.minecraft.item.Item;

import static muramasa.antimatter.Data.*;
import static muramasa.gti.data.RecipeMaps.FLUID_EXTRACTING;

public class FluidExtractor {
    public static void init() {
        ROD.all().forEach(r -> {
            add(r, ROD.get(r), 72);
        });
        PLATE.all().forEach(r -> {
            add(r, PLATE.get(r), 144);
        });
        INGOT.all().forEach(r -> {
            add(r, INGOT.get(r), 72);
        });
        SCREW.all().forEach(r -> {
            add(r, SCREW.get(r), 144/8);
        });
        GEAR.all().forEach(r -> {
            add(r, GEAR.get(r), 144*4);
        });
        FOIL.all().forEach(r -> {
            add(r, FOIL.get(r), 144/4);
        });
        BOLT.all().forEach(r -> {
            add(r, BOLT.get(r), 144/8);
        });
        PLATE_DENSE.all().forEach(r -> {
            add(r, PLATE_DENSE.get(r), 144*9);
        });
    }

    private static void add(Material m, Item i, int amount) {
        if (!m.has(LIQUID)) return;
        FLUID_EXTRACTING.RB()
                .ii(AntimatterIngredient.of(i,1))
                .fo(m.getLiquid(amount))
                .add(m.getMass()*amount/144, 64);
    }
}
