package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.Materials;
import muramasa.gregtech.data.RecipeMaps;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluids;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.gregtech.data.RecipeMaps.CUTTING;

public class CuttingLoader {
    public static void init() {
        for (Material mat : AntimatterMaterialTypes.PLATE.all()) {
            if (!mat.has(AntimatterMaterialTypes.BLOCK))
                continue;
            int multiplier = 1;//mat.has(AntimatterMaterialTypes.GEM) ? 8 : 3;
            if (mat == AntimatterMaterials.Diamond || mat == AntimatterMaterials.NetherizedDiamond)
                multiplier = 5;
            int count = mat.has(MaterialTags.QUARTZ_LIKE_BLOCKS) ? 4 : 9;
            addCutterRecipe(BLOCK.getMaterialTag(mat), PLATE.get(mat, count), "plate_" + mat.getId(), (int) (mat.getMass() * 10 * multiplier), 30);
            if (mat.has(ITEM_CASING)){
                addCutterRecipe(PLATE.getMaterialTag(mat), ITEM_CASING.get(mat, 2), "item_casing_" + mat.getId(), (int) (mat.getMass() * 5 * multiplier), 16);
            }

        }
        AntimatterMaterialTypes.BOLT.all().forEach(t -> {
            if (t.has(AntimatterMaterialTypes.ROD)) {
                addCutterRecipe(ROD.getMaterialTag(t), BOLT.get(t, 4), "bolt_" + t.getId(), (int) (t.getMass() * 2), 4);
            }
        });
        AntimatterMaterialTypes.ROD_LONG.all().forEach(m -> {
            addCutterRecipe(ROD_LONG.getMaterialTag(m), ROD.get(m, 2), "rod_" + m.getId(), (int) (m.getMass() * 2), 4);
        });
    }

    private static void addCutterRecipe(TagKey<Item> input, ItemStack output, String id, int duration, int euPerTick){
        CUTTING.RB().ii(RecipeIngredient.of(input, 1))
                .fi(FluidPlatformUtils.createFluidStack(Fluids.WATER, Math.max(4, Math.min(1000, duration * euPerTick / 320)) * TesseractGraphWrappers.dropletMultiplier))
                .io(output).add(id + "_with_water", duration * 2L, euPerTick);
        CUTTING.RB().ii(RecipeIngredient.of(input, 1))
                .fi(Materials.Lubricant.getLiquid(Math.max(1, Math.min(250, duration * euPerTick / 1280))))
                .io(output).add(id + "_with_lubricant", duration, euPerTick);
        CUTTING.RB().ii(RecipeIngredient.of(input, 1))
                .fi(Materials.DistilledWater.getLiquid(Math.max(3, Math.min(750, duration * euPerTick / 426))))
                .io(output).add(id + "_with_distilled_water", duration * 2L, euPerTick);
    }
}
