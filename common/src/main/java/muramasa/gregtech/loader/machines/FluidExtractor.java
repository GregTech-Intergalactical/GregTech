package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.world.item.Item;

import static muramasa.gregtech.data.RecipeMaps.FLUID_EXTRACTING;

public class FluidExtractor {
    public static void init() {
        AntimatterMaterialTypes.ROD.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.ROD.get(r), 0.5f);
        });
        AntimatterMaterialTypes.PLATE.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.PLATE.get(r), 1f);
        });
        AntimatterMaterialTypes.INGOT.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.INGOT.get(r), 0.5f);
        });
        AntimatterMaterialTypes.SCREW.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.SCREW.get(r), 0.125f);
        });
        AntimatterMaterialTypes.GEAR.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.GEAR.get(r), 4f);
        });
        AntimatterMaterialTypes.FOIL.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.FOIL.get(r), 0.25f);
        });
        AntimatterMaterialTypes.BOLT.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.BOLT.get(r), 0.125f);
        });
        AntimatterMaterialTypes.PLATE_DENSE.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.PLATE_DENSE.get(r), 9f);
        });
    }

    private static void add(Material m, Item i, float ratio) {
        if (!m.has(AntimatterMaterialTypes.LIQUID)) return;
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
