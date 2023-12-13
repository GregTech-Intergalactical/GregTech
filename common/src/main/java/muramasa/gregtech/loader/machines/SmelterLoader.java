package muramasa.gregtech.loader.machines;

import io.github.gregtechintergalactical.gtcore.data.GTCoreBlocks;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.Ref.U;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.material.MaterialTags.MOLTEN;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.FLUID_PRESS;
import static muramasa.gregtech.data.RecipeMaps.SMELTER;

public class SmelterLoader {
    public static void init() {
        MaterialTypeItem<?>[] items = new MaterialTypeItem<?>[]{INGOT, NUGGET, PLATE, PLATE_DENSE, ROD, ROD_LONG, RING, FOIL, BOLT, SCREW, GEAR, GEAR_SMALL, WIRE_FINE, ROTOR};
        for (MaterialTypeItem<?> item : items) {
            item.all().forEach(m -> {
                add(m, item, item.getUnitValue());
            });
        }
        DUST.all().forEach(m -> {
            if (m.has(LIQUID) && m.has(MOLTEN)){
                add(m, DUST, DUST.getUnitValue());
            }
        });
        SMELTER.RB().ii(DUST.getMaterialIngredient(Limestone, 1)).fo(Calcite.getLiquid(L)).add("limestone_dust_to_calcite", Limestone.getMass(), 24);
        SMELTER.RB().ii(GTCoreBlocks.LIMESTONE.getState().getBlock()).fo(Calcite.getLiquid(L)).add("limestone_to_calcite", Limestone.getMass(), 24);
        SMELTER.RB().ii(Blocks.CALCITE).fo(Calcite.getLiquid(L)).add("mc_calcite_to_calcite", Limestone.getMass(), 24);
    }

    private static void add(Material m, MaterialTypeItem<?> i, long materialAmount) {
        if (!m.has(AntimatterMaterialTypes.LIQUID)) return;
        long amount = //(long) (L * ratio);
                (L * materialAmount) / U;
        long duration = Math.max(1, (24 * materialAmount) / U);
        SMELTER.RB()
                .ii(RecipeIngredient.of(i.getMaterialTag(m),1))
                .fo(m.getLiquid(amount))
                .add(m.getId() + "_from_" + i.getId(), (long)(m.getMass()*((float)amount/L)), Math.max(8, (int) Math.sqrt(2 * MaterialTags.MELTING_POINT.getInt(m))), duration);
    }
}
