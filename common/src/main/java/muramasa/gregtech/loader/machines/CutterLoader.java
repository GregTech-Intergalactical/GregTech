package muramasa.gregtech.loader.machines;

import io.github.gregtechintergalactical.gtcore.data.GTCoreBlocks;
import io.github.gregtechintergalactical.gtcore.data.GTCoreTags;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.ore.CobbleStoneType;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechMaterialTags;
import muramasa.gregtech.data.Materials;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;

import java.util.function.ToLongFunction;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Wood;
import static muramasa.gregtech.data.RecipeMaps.CUTTER;

public class CutterLoader {
    public static void init() {
        ToLongFunction<Material> baseDuration = m -> {
            if (m.has(GregTechMaterialTags.RECIPE_MASS)) return GregTechMaterialTags.RECIPE_MASS.get(m);
            return m.getMass();
        };
        for (Material mat : AntimatterMaterialTypes.PLATE.all()) {
            if (!mat.has(AntimatterMaterialTypes.BLOCK))
                continue;
            int multiplier = 1;//mat.has(AntimatterMaterialTypes.GEM) ? 8 : 3;
            if (mat == AntimatterMaterials.Diamond || mat == AntimatterMaterials.NetherizedDiamond)
                multiplier = 5;
            int count = mat.has(MaterialTags.QUARTZ_LIKE_BLOCKS) ? 4 : 9;
            addCutterRecipe(BLOCK.getMaterialTag(mat), PLATE.get(mat, count), "plate_" + mat.getId(), (int) (baseDuration.applyAsLong(mat) * 10 * multiplier), 30);
            if (mat.has(ITEM_CASING)){
                addCutterRecipe(PLATE.getMaterialTag(mat), ITEM_CASING.get(mat, 2), "item_casing_" + mat.getId(), (int) (baseDuration.applyAsLong(mat) * 5 * multiplier), 16);
            }

        }
        AntimatterAPI.all(StoneType.class, s -> {
            if (s instanceof CobbleStoneType c){
                for (String type : CobbleStoneType.SUFFIXES){
                    String id = (type.isEmpty() ? c.getId() : c.getId() + "_" + type) + "_cover";
                    Item cover = AntimatterAPI.get(Item.class, id, Ref.SHARED_ID);
                    if (cover == null) continue;
                    addCutterRecipe(c.getBlock(type).asItem(), new ItemStack(cover, 8), DUST.get(c.getMaterial(), 1), id, 20, 2);
                }
            }
        });
        AntimatterMaterialTypes.BOLT.all().forEach(t -> {
            if (t.has(AntimatterMaterialTypes.ROD)) {
                addCutterRecipe(ROD.getMaterialTag(t), BOLT.get(t, 4), "bolt_" + t.getId(), (int) (baseDuration.applyAsLong(t) * 2), 4);
            }
        });
        AntimatterMaterialTypes.ROD_LONG.all().forEach(m -> {
            addCutterRecipe(ROD_LONG.getMaterialTag(m), ROD.get(m, 2), "rod_" + m.getId(), (int) (baseDuration.applyAsLong(m) * 2), 4);
        });
        if (!AntimatterAPI.isModLoaded(Ref.MOD_TFC)){
            addWoodRecipe(ItemTags.OAK_LOGS, Items.OAK_PLANKS, 1, "oak_planks", 200, 8);
            addWoodRecipe(ItemTags.BIRCH_LOGS, Items.BIRCH_PLANKS, 1, "birch_planks", 200, 8);
            addWoodRecipe(ItemTags.SPRUCE_LOGS, Items.SPRUCE_PLANKS, 1, "spruce_planks", 200, 8);
            addWoodRecipe(ItemTags.ACACIA_LOGS, Items.ACACIA_PLANKS, 1, "acacia_planks", 200, 8);
            addWoodRecipe(ItemTags.DARK_OAK_LOGS, Items.DARK_OAK_PLANKS, 1, "dark_oak_planks", 200, 8);
            addWoodRecipe(ItemTags.JUNGLE_LOGS, Items.JUNGLE_PLANKS, 1, "jungle_planks", 200, 8);
            addWoodRecipe(ItemTags.CRIMSON_STEMS, Items.CRIMSON_PLANKS, 1, "crimson_planks", 200, 8);
            addWoodRecipe(ItemTags.WARPED_STEMS, Items.WARPED_PLANKS, 1, "warped_planks", 200, 8);
            addWoodRecipe(GTCoreTags.RUBBER_LOGS, GTCoreBlocks.RUBBER_PLANKS.asItem(), 1, "rubber_planks", 200, 8);
        }
    }

    private static void addCutterRecipe(TagKey<Item> input, ItemStack output, String id, int duration, int euPerTick){
        CUTTER.RB().ii(RecipeIngredient.of(input, 1))
                .fi(FluidPlatformUtils.createFluidStack(Fluids.WATER, Math.max(4, Math.min(1000, duration * euPerTick / 320)) * TesseractGraphWrappers.dropletMultiplier))
                .io(output).add(id + "_with_water", duration * 2L, euPerTick);
        CUTTER.RB().ii(RecipeIngredient.of(input, 1))
                .fi(Materials.Lubricant.getLiquid(Math.max(1, Math.min(250, duration * euPerTick / 1280))))
                .io(output).add(id + "_with_lubricant", duration, euPerTick);
        CUTTER.RB().ii(RecipeIngredient.of(input, 1))
                .fi(Materials.DistilledWater.getLiquid(Math.max(3, Math.min(750, duration * euPerTick / 426))))
                .io(output).add(id + "_with_distilled_water", duration * 2L, euPerTick);
    }

    private static void addCutterRecipe(Item input, ItemStack output, ItemStack dust, String id, int duration, int euPerTick){
        CUTTER.RB().ii(RecipeIngredient.of(input, 1))
                .fi(FluidPlatformUtils.createFluidStack(Fluids.WATER, Math.max(4, Math.min(1000, duration * euPerTick / 320)) * TesseractGraphWrappers.dropletMultiplier))
                .io(output, dust).add(id + "_with_water", duration * 2L, euPerTick);
        CUTTER.RB().ii(RecipeIngredient.of(input, 1))
                .fi(Materials.Lubricant.getLiquid(Math.max(1, Math.min(250, duration * euPerTick / 1280))))
                .io(output, dust).add(id + "_with_lubricant", duration, euPerTick);
        CUTTER.RB().ii(RecipeIngredient.of(input, 1))
                .fi(Materials.DistilledWater.getLiquid(Math.max(3, Math.min(750, duration * euPerTick / 426))))
                .io(output, dust).add(id + "_with_distilled_water", duration * 2L, euPerTick);
    }

    public static void addWoodRecipe(TagKey<Item> input, Item output, int multiplier, String id, int duration, int euPerTick){
        CUTTER.RB().ii(RecipeIngredient.of(input, 1))
                .fi(FluidPlatformUtils.createFluidStack(Fluids.WATER, Math.max(4, Math.min(1000, duration * euPerTick / 320)) * TesseractGraphWrappers.dropletMultiplier))
                .io(new ItemStack(output, 4 * multiplier), DUST.get(Wood, 2)).add(id + "_with_water", duration * 2L, euPerTick);
        CUTTER.RB().ii(RecipeIngredient.of(input, 1))
                .fi(Materials.Lubricant.getLiquid(Math.max(1, Math.min(250, duration * euPerTick / 1280))))
                .io(new ItemStack(output, 6 * multiplier), DUST.get(Wood, 1)).add(id + "_with_lubricant", duration, euPerTick);
        CUTTER.RB().ii(RecipeIngredient.of(input, 1))
                .fi(Materials.DistilledWater.getLiquid(Math.max(3, Math.min(750, duration * euPerTick / 426))))
                .io(new ItemStack(output, 4 * multiplier), DUST.get(Wood, 2)).add(id + "_with_distilled_water", duration * 2L, euPerTick);
    }
}
