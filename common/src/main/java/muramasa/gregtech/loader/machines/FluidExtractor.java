package muramasa.gregtech.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.world.item.Item;

import static muramasa.antimatter.Data.*;
import static muramasa.gregtech.data.RecipeMaps.FLUID_EXTRACTING;

public class FluidExtractor {
    public static void init() {
        ROD.all().forEach(r -> {
            add(r, ROD.get(r), 0.5f);
        });
        PLATE.all().forEach(r -> {
            add(r, PLATE.get(r), 1f);
        });
        INGOT.all().forEach(r -> {
            add(r, INGOT.get(r), 0.5f);
        });
        SCREW.all().forEach(r -> {
            add(r, SCREW.get(r), 0.125f);
        });
        GEAR.all().forEach(r -> {
            add(r, GEAR.get(r), 4f);
        });
        FOIL.all().forEach(r -> {
            add(r, FOIL.get(r), 0.25f);
        });
        BOLT.all().forEach(r -> {
            add(r, BOLT.get(r), 0.125f);
        });
        PLATE_DENSE.all().forEach(r -> {
            add(r, PLATE_DENSE.get(r), 9f);
        });
    }

    private static void add(Material m, Item i, float ratio) {
        if (!m.has(LIQUID)) return;
        long amount = amount(ratio);
        FLUID_EXTRACTING.RB()
                .ii(RecipeIngredient.of(i,1))
                .fo(m.getLiquid(amount))
                .add((long)(m.getMass()*((float)amount/ratio())), 64, MaterialTags.MELTING_POINT.getInt(m));
    }

    private static long ratio(){
        return AntimatterPlatformUtils.isForge() ? 144L : 9000L;
    }

    private static long amount(float ingots){
        return (long) (ratio() * ingots);
    }
}
