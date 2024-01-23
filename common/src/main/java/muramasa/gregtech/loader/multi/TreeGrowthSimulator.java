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
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.COKE_OVEN;
import static muramasa.gregtech.data.RecipeMaps.TREE_GROWTH_SIMULATOR;

public class TreeGrowthSimulator {
    public static void init() {
        TREE_GROWTH_SIMULATOR.RB().ii(RecipeIngredient.of(Items.OAK_SAPLING, 1).setNoConsume()).io(new ItemStack(Items.OAK_LOG, 6)).add("oak_log", 10, 16);
        TREE_GROWTH_SIMULATOR.RB().ii(RecipeIngredient.of(Items.BIRCH_SAPLING, 1).setNoConsume()).io(new ItemStack(Items.BIRCH_LOG, 6)).add("birch_log", 10, 16);
        TREE_GROWTH_SIMULATOR.RB().ii(RecipeIngredient.of(Items.SPRUCE_SAPLING, 1).setNoConsume()).io(new ItemStack(Items.SPRUCE_LOG, 10)).add("spruce_log", 10, 16);
        TREE_GROWTH_SIMULATOR.RB().ii(RecipeIngredient.of(Items.JUNGLE_SAPLING, 1).setNoConsume()).io(new ItemStack(Items.JUNGLE_LOG, 10)).add("jungle_log", 10, 16);
        TREE_GROWTH_SIMULATOR.RB().ii(RecipeIngredient.of(Items.DARK_OAK_SAPLING, 4).setNoConsume()).io(new ItemStack(Items.DARK_OAK_LOG, 40)).add("dark_oak_log", 10, 16);
        TREE_GROWTH_SIMULATOR.RB().ii(RecipeIngredient.of(Items.ACACIA_SAPLING, 1).setNoConsume()).io(new ItemStack(Items.ACACIA_LOG, 8)).add("acacia_log", 10, 16);
        TREE_GROWTH_SIMULATOR.RB().ii(RecipeIngredient.of(GTCoreBlocks.RUBBER_SAPLING, 1).setNoConsume()).io(new ItemStack(GTCoreBlocks.RUBBER_LOG, 8)).add("rubber_log", 10, 16);

    }
}
