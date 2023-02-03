package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.world.item.Item;

import static muramasa.gregtech.data.RecipeMaps.FLUID_EXTRACTING;

public class FluidExtractor {
    public static void init() {
        AntimatterMaterialTypes.ROD.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.ROD, 0.5f);
        });
        AntimatterMaterialTypes.PLATE.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.PLATE, 1f);
        });
        AntimatterMaterialTypes.INGOT.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.INGOT, 0.5f);
        });
        AntimatterMaterialTypes.SCREW.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.SCREW, 0.125f);
        });
        AntimatterMaterialTypes.GEAR.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.GEAR, 4f);
        });
        AntimatterMaterialTypes.FOIL.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.FOIL, 0.25f);
        });
        AntimatterMaterialTypes.BOLT.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.BOLT, 0.125f);
        });
        AntimatterMaterialTypes.PLATE_DENSE.all().forEach(r -> {
            add(r, AntimatterMaterialTypes.PLATE_DENSE, 9f);
        });
    }

    private static void add(Material m, MaterialTypeItem<?> i, float ratio) {
        if (!m.has(AntimatterMaterialTypes.LIQUID)) return;
        long amount = amount(ratio);
        FLUID_EXTRACTING.RB()
                .ii(RecipeIngredient.of(i.getMaterialTag(m),1))
                .fo(m.getLiquid(amount))
                .add(m.getId() + "_from_" + i.getId(), (long)(m.getMass()*((float)amount/ratio())), 64, MaterialTags.MELTING_POINT.getInt(m));
    }

    private static long ratio(){
        return AntimatterPlatformUtils.isForge() ? 144L : 9000L;
    }

    private static long amount(float ingots){
        return (long) (ratio() * ingots);
    }
}
