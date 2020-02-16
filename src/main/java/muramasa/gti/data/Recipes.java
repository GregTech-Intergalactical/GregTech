package muramasa.gti.data;

import muramasa.gti.common.Data;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static muramasa.antimatter.materials.MaterialType.GEAR;
import static muramasa.antimatter.materials.MaterialType.ROD;
import static muramasa.antimatter.util.Utils.getForgeItemTag;
import static muramasa.gti.common.Data.*;
import static muramasa.gti.data.Materials.*;

public class Recipes extends RecipeProvider {

    public Recipes(DataGenerator gen) {
        super(gen);
    }

    @Override
    public void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ItemFilter).key('Z', getForgeItemTag("foils/zinc")).key('I', getForgeItemTag("plates/iron"))
                .patternLine("ZZZ")
                .patternLine("ZIZ")
                .patternLine("ZZZ").addCriterion("has_iron_plate", this.hasItem(getForgeItemTag("plates/iron"))).build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Blocks.TORCH, 6).key('D', getForgeItemTag("dusts/sulfur")).key('S', Tags.Items.RODS_WOODEN)
                .patternLine("D")
                .patternLine("S").addCriterion("has_sulfur_dust", this.hasItem(getForgeItemTag("dusts/sulfur"))).build(consumer);

        ShapedRecipeBuilder.shapedRecipe(GEAR.get(Wood)).key('S', ItemTags.PLANKS).key('P', Tags.Items.RODS_WOODEN)
                .patternLine("SPS")
                .patternLine("P P") // w
                .patternLine("SPS").addCriterion("has_wooden_rod", this.hasItem(Tags.Items.RODS_WOODEN)).build(consumer);

        ShapedRecipeBuilder.shapedRecipe(GEAR.get(Wood)).key('S', ItemTags.PLANKS).key('P', Tags.Items.RODS_WOODEN)
                .patternLine("PSP")
                .patternLine("S S") // w
                .patternLine("PSP").addCriterion("has_wooden_rod", this.hasItem(Tags.Items.RODS_WOODEN)).build(consumer, GEAR.get(Wood).getRegistryName().toString() + "_alt");

        ShapedRecipeBuilder.shapedRecipe(GEAR.get(Stone)).key('S', Tags.Items.STONE).key('P', Tags.Items.COBBLESTONE)
                .patternLine("SPS")
                .patternLine("P P") // w
                .patternLine("SPS").addCriterion("has_stone", this.hasItem(Tags.Items.STONE)).build(consumer);

        ShapedRecipeBuilder.shapedRecipe(GEAR.get(Stone)).key('S', Tags.Items.STONE).key('P', Tags.Items.COBBLESTONE)
                .patternLine("PSP")
                .patternLine("S S") // w
                .patternLine("PSP").addCriterion("has_stone", this.hasItem(Tags.Items.STONE)).build(consumer, GEAR.get(Stone).getRegistryName().toString() + "_alt");

        ShapelessRecipeBuilder.shapelessRecipe(Blocks.STICKY_PISTON)
                .addIngredient(Blocks.PISTON).addIngredient(Data.StickyResin)
                .addCriterion("has_piston", this.hasItem(Blocks.PISTON)).build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(ROD.get(IronMagnetic))
                .addIngredient(getForgeItemTag("rods/iron")).addIngredient(Items.REDSTONE).addIngredient(Items.REDSTONE).addIngredient(Items.REDSTONE).addIngredient(Items.REDSTONE)
                .addCriterion("has_iron_rod", this.hasItem(getForgeItemTag("rods/iron"))).build(consumer);
    }

    @Override
    public String getName() {
        return "GregTech Crafting Recipes";
    }

    //private <T> Tag<T> getForgeTag(String name) {

    //}
}
