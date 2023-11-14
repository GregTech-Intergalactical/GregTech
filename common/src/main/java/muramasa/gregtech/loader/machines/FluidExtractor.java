package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.Ref.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.FLUID_EXTRACTING;

public class FluidExtractor {
    public static void init() {
        MaterialTypeItem<?>[] items = new MaterialTypeItem<?>[]{INGOT, NUGGET, PLATE, PLATE_DENSE, ROD, ROD_LONG, RING, FOIL, BOLT, SCREW, GEAR, GEAR_SMALL, WIRE_FINE, ROTOR};
        for (MaterialTypeItem<?> item : items) {
            item.all().forEach(m -> {
                add(m, item, item.getUnitValue());
            });
        }
        add(AntimatterMaterials.Redstone, DUST, DUST.getUnitValue());
        add(Calcite, DUST, DUST.getUnitValue());
        FLUID_EXTRACTING.RB().ii(RecipeIngredient.of(TagUtils.getForgelikeItemTag("seeds"))).fo(SeedOil.getLiquid(10)).add("seed_oil", 32, 2);
        FLUID_EXTRACTING.RB().ii(Items.PUFFERFISH).fo(FishOil.getLiquid(30)).add("fish_oil_pufferfish", 16, 4);
        FLUID_EXTRACTING.RB().ii(Items.COD).fo(FishOil.getLiquid(40)).add("fish_oil_cod", 16, 4);
        FLUID_EXTRACTING.RB().ii(Items.SALMON).fo(FishOil.getLiquid(60)).add("fish_oil_salmon", 16, 4);
        FLUID_EXTRACTING.RB().ii(Items.TROPICAL_FISH).fo(FishOil.getLiquid(70)).add("fish_oil_tropical_fish", 16, 4);
    }

    private static void add(Material m, MaterialTypeItem<?> i, long materialAmount) {
        if (!m.has(AntimatterMaterialTypes.LIQUID)) return;
        long amount = //(long) (L * ratio);
        (L * materialAmount) / U;
        long duration = Math.max(1, (24 * materialAmount) / U);
        FLUID_EXTRACTING.RB()
                .ii(RecipeIngredient.of(i.getMaterialTag(m),1))
                .fo(m.getLiquid(amount))
                .add(m.getId() + "_from_" + i.getId(), (long)(m.getMass()*((float)amount/L)), Math.max(8, (int) Math.sqrt(2 * MaterialTags.MELTING_POINT.getInt(m))), duration);
    }
}
