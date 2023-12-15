package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static io.github.gregtechintergalactical.gtcore.data.GTCoreItems.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.COMPRESSOR;

public class CompressorLoader {
    public static void init() {
        AntimatterMaterialTypes.INGOT.all().forEach(ingot -> {
            if (ingot.has(AntimatterMaterialTypes.BLOCK)) {
                int count = ingot.has(MaterialTags.QUARTZ_LIKE_BLOCKS) ? 4 : 9;
                COMPRESSOR.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.INGOT.get(ingot), count)).io(AntimatterMaterialTypes.BLOCK.get().get(ingot).asStack(1))
                        .add("block_" + ingot.getId(),Math.max(80, ingot.getMass() * 2), 16);
            }
        });
        AntimatterMaterialTypes.GEM.all().forEach(ingot -> {
            if (ingot.has(AntimatterMaterialTypes.BLOCK)) {
                int count = ingot.has(MaterialTags.QUARTZ_LIKE_BLOCKS) ? 4 : 9;
                COMPRESSOR.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.GEM.get(ingot), count)).io(AntimatterMaterialTypes.BLOCK.get().get(ingot).asStack(1))
                        .add("block_" + ingot.getId(),Math.max(80, ingot.getMass() * 2), 16);
            }
        });
        AntimatterMaterialTypes.RAW_ORE.all().forEach(raw_ore -> {
            if (raw_ore.has(AntimatterMaterialTypes.BLOCK)) {
                int count = 9;
                COMPRESSOR.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.RAW_ORE.get(raw_ore), count)).io(AntimatterMaterialTypes.RAW_ORE_BLOCK.get().get(raw_ore).asStack(1))
                        .add("block_raw_" + raw_ore.getId(),Math.max(80, raw_ore.getMass() * 2), 16);
            }
        });
        COMPRESSOR.RB().ii(DUST.getMaterialIngredient(Wood, 1)).io(AntimatterMaterialTypes.PLATE.get(Wood, 1)).add("wood_plate",60, 4);
        COMPRESSOR.RB().ii(DUST.getMaterialIngredient(Fireclay, 1)).io(new ItemStack(CompressedFireClay)).add("compressed_fireclay",200, 2);
        COMPRESSOR.RB().ii(DUST.getMaterialIngredient(Lapis, 1)).io(PLATE.get(Lapis)).add("lapis_plate", 300, 2);
        COMPRESSOR.RB().ii(DUST.getMaterialIngredient(Lazurite, 1)).io(PLATE.get(Lazurite)).add("lazurite", 300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(ItemTags.SAPLINGS, 4)).io(new ItemStack(Plantball)).add("plantball",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(ItemTags.SMALL_FLOWERS, 8)).io(new ItemStack(Plantball)).add("plantball_1",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(ForgeCTags.CROPS, 8)).io(new ItemStack(Plantball)).add("plantball_2",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(ForgeCTags.SEEDS, 16)).io(new ItemStack(Plantball)).add("plantball_3",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(ItemTags.LEAVES, 8)).io(new ItemStack(Plantball)).add("plantball_4",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.CACTUS, 8)).io(new ItemStack(Plantball)).add("plantball_5",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.DEAD_BUSH, 8)).io(new ItemStack(Plantball)).add("plantball_6",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(ForgeCTags.MUSHROOMS, 8)).io(new ItemStack(Plantball)).add("plantball_8",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.NETHER_WART_BLOCK, 8)).io(new ItemStack(Plantball)).add("plantball_9",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.WARPED_FUNGUS, 4)).io(new ItemStack(Plantball)).add("plantball_10",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.WARPED_ROOTS, 8)).io(new ItemStack(Plantball)).add("plantball_11",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.CRIMSON_FUNGUS, 4)).io(new ItemStack(Plantball)).add("plantball_12",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.CRIMSON_ROOTS, 8)).io(new ItemStack(Plantball)).add("plantball_13",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.WARPED_WART_BLOCK, 8)).io(new ItemStack(Plantball)).add("plantball_14",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.KELP, 8)).io(new ItemStack(Plantball)).add("plantball_16",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.DRIED_KELP, 8)).io(new ItemStack(Plantball)).add("plantball_17",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.PUMPKIN, 4)).io(new ItemStack(Plantball)).add("plantball_18",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.MELON, 4)).io(new ItemStack(Plantball)).add("plantball_19",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.MELON_SLICE, 8)).io(new ItemStack(Plantball)).add("plantball_20",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.SUGAR_CANE, 8)).io(new ItemStack(Plantball)).add("plantball_21",300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(CoalBall)).io(CompressedCoalBall).add("compressed_coal_ball", 300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(CoalChunk)).io(GEM.get(Diamond)).add("diamond_from_coal", 300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.SNOW_BLOCK)).io(Items.ICE).add("ice", 300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.ICE, 1)).io(Items.PACKED_ICE).add("packed_ice", 300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(Items.PACKED_ICE, 2)).io(Items.BLUE_ICE).add("blue_ice", 300, 2);
        COMPRESSOR.RB().ii(RecipeIngredient.of(CarbonMesh)).io(PLATE.get(Carbon)).add("carbon_plate", 300, 2);
    }
}
