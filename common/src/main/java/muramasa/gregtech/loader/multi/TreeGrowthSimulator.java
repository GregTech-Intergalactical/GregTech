package muramasa.gregtech.loader.multi;

import io.github.gregtechintergalactical.gtcore.data.GTCoreBlocks;
import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Coal;
import static muramasa.antimatter.data.AntimatterMaterials.Water;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.COKE_OVEN;
import static muramasa.gregtech.data.RecipeMaps.TREE_GROWTH_SIMULATOR;

public class TreeGrowthSimulator {
    public static void init() {
        TREE_GROWTH_SIMULATOR.RB().ii(RecipeIngredient.of(Items.OAK_SAPLING, 1)).fi(Water.getLiquid(1000)).io(new ItemStack(Items.OAK_LOG, 6), new ItemStack(Items.OAK_SAPLING, 2), new ItemStack(Items.APPLE)).chances(1.0, 1.0, 0.25).add("oak_log", 400, 4);
        TREE_GROWTH_SIMULATOR.RB().ii(RecipeIngredient.of(Items.BIRCH_SAPLING, 1)).fi(Water.getLiquid(1000)).io(new ItemStack(Items.BIRCH_LOG, 6), new ItemStack(Items.BIRCH_SAPLING, 2)).add("birch_log", 400, 4);
        TREE_GROWTH_SIMULATOR.RB().ii(RecipeIngredient.of(Items.SPRUCE_SAPLING, 1)).fi(Water.getLiquid(1000)).io(new ItemStack(Items.SPRUCE_LOG, 10), new ItemStack(Items.SPRUCE_SAPLING, 2)).add("spruce_log", 400, 4);
        TREE_GROWTH_SIMULATOR.RB().ii(RecipeIngredient.of(Items.JUNGLE_SAPLING, 1)).fi(Water.getLiquid(1000)).io(new ItemStack(Items.JUNGLE_LOG, 10), new ItemStack(Items.JUNGLE_SAPLING, 2)).add("jungle_log", 400, 4);
        TREE_GROWTH_SIMULATOR.RB().ii(RecipeIngredient.of(Items.DARK_OAK_SAPLING, 4)).fi(Water.getLiquid(1000)).io(new ItemStack(Items.DARK_OAK_LOG, 40), new ItemStack(Items.DARK_OAK_SAPLING, 8), new ItemStack(Items.APPLE)).chances(1.0, 1.0, 0.25).add("dark_oak_log", 400, 4);
        TREE_GROWTH_SIMULATOR.RB().ii(RecipeIngredient.of(Items.ACACIA_SAPLING, 1)).fi(Water.getLiquid(1000)).io(new ItemStack(Items.ACACIA_LOG, 8), new ItemStack(Items.ACACIA_SAPLING, 2)).add("acacia_log", 400, 4);
        TREE_GROWTH_SIMULATOR.RB().ii(RecipeIngredient.of(GTCoreBlocks.RUBBER_SAPLING, 1)).fi(Water.getLiquid(1000)).io(new ItemStack(GTCoreBlocks.RUBBER_LOG, 8), new ItemStack(GTCoreBlocks.RUBBER_SAPLING, 2), new ItemStack(GTCoreItems.StickyResin)).add("rubber_log", 400, 4);

    }
}
