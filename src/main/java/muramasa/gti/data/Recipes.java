package muramasa.gti.data;

import muramasa.antimatter.recipe.condition.ConfigCondition;
import muramasa.antimatter.registration.RegistrationHelper;
import muramasa.gti.Ref;
import muramasa.gti.common.Data;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;

import java.util.function.Consumer;

import static muramasa.antimatter.materials.MaterialTag.GRINDABLE;
import static muramasa.antimatter.materials.MaterialType.*;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.util.Utils.getForgeItemTag;
import static muramasa.gti.common.Data.*;
import static muramasa.gti.data.Materials.*;

public class Recipes extends RecipeProvider {

    public Recipes(DataGenerator gen) {
        super(gen);
    }

    @Override
    public void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        ConditionalRecipe.builder().addCondition(new ConfigCondition(Ref.class, "sulfurTorch")).addRecipe(ShapedRecipeBuilder.shapedRecipe(Blocks.TORCH, 6)
                .key('D', getForgeItemTag("dusts/sulfur")).key('S', Tags.Items.RODS_WOODEN)
                .patternLine("D")
                .patternLine("S").addCriterion("has_sulfur_dust", this.hasItem(getForgeItemTag("dusts/sulfur")))::build).build(consumer, Ref.ID, "sulfur_torch");  // Wow thanks Forge

        ShapedRecipeBuilder.shapedRecipe(Blocks.HOPPER).key('C', Blocks.CHEST).key('I', getForgeItemTag("plates/iron")).key('W', WRENCH.getTag())
                .patternLine("IWI")
                .patternLine("ICI")
                .patternLine(" I ").addCriterion("has_wrench", this.hasItem(WRENCH.getTag())).build(consumer, Ref.ID + ":hopper");

        ShapedRecipeBuilder.shapedRecipe(ItemFilter).key('Z', getForgeItemTag("foils/zinc")).key('I', getForgeItemTag("plates/iron"))
                .patternLine("ZZZ")
                .patternLine("ZIZ")
                .patternLine("ZZZ").addCriterion("has_iron_plate", this.hasItem(getForgeItemTag("plates/iron"))).build(consumer);

        // ShapedRecipeBuilder.shapedRecipe(Blocks.TORCH, 6).key('D', getForgeItemTag("dusts/sulfur")).key('S', Tags.Items.RODS_WOODEN)
                // .patternLine("D")
                // .patternLine("S").addCriterion("has_sulfur_dust", this.hasItem(getForgeItemTag("dusts/sulfur"))).build(consumer, Ref.ID + ":sulfur_torch");

        ShapedRecipeBuilder.shapedRecipe(GEAR.get(Wood)).key('S', ItemTags.PLANKS).key('P', Tags.Items.RODS_WOODEN)
                .patternLine("SPS")
                .patternLine("P P") // w
                .patternLine("SPS").addCriterion("has_wooden_rod", this.hasItem(Tags.Items.RODS_WOODEN)).setGroup("gears").build(consumer);

        ShapedRecipeBuilder.shapedRecipe(GEAR.get(Wood)).key('S', ItemTags.PLANKS).key('P', Tags.Items.RODS_WOODEN)
                .patternLine("PSP")
                .patternLine("S S") // w
                .patternLine("PSP").addCriterion("has_wooden_rod", this.hasItem(Tags.Items.RODS_WOODEN))
                .setGroup("gears").build(consumer, GEAR.get(Wood).getRegistryName().toString() + "_alt");

        ShapedRecipeBuilder.shapedRecipe(GEAR.get(Stone)).key('S', Tags.Items.STONE).key('P', Tags.Items.COBBLESTONE)
                .patternLine("SPS")
                .patternLine("P P") // w
                .patternLine("SPS").addCriterion("has_stone", this.hasItem(Tags.Items.STONE)).setGroup("gears").build(consumer);

        ShapedRecipeBuilder.shapedRecipe(GEAR.get(Stone)).key('S', Tags.Items.STONE).key('P', Tags.Items.COBBLESTONE)
                .patternLine("PSP")
                .patternLine("S S") // w
                .patternLine("PSP").addCriterion("has_stone", this.hasItem(Tags.Items.STONE))
                .setGroup("gears").build(consumer, GEAR.get(Stone).getRegistryName().toString() + "_alt");

        ShapelessRecipeBuilder.shapelessRecipe(Blocks.STICKY_PISTON)
                .addIngredient(Blocks.PISTON).addIngredient(Data.StickyResin)
                .addCriterion("has_piston", this.hasItem(Blocks.PISTON)).build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(ROD.get(IronMagnetic))
                .addIngredient(getForgeItemTag("rods/iron")).addIngredient(Items.REDSTONE).addIngredient(Items.REDSTONE).addIngredient(Items.REDSTONE).addIngredient(Items.REDSTONE)
                .addCriterion("has_iron_rod", this.hasItem(getForgeItemTag("rods/iron"))).setGroup("magnetic_rods").build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(ROD.get(Neodymium))
                .addIngredient(getForgeItemTag("rods/neodymium")).addIngredient(Items.REDSTONE).addIngredient(Items.REDSTONE).addIngredient(Items.REDSTONE).addIngredient(Items.REDSTONE)
                .addCriterion("has_neodymium_rod", this.hasItem(getForgeItemTag("rods/neodymium"))).setGroup("magnetic_rods").build(consumer);

        RegistrationHelper.getMaterialsForDomain(Ref.ID).stream().filter(m -> m.has(TOOLS)).forEach(mat -> {
            RegistrationHelper.getMaterialsForDomain(Ref.ID).stream().filter(s -> s.isHandle()).forEach(s -> {
                if (mat.equals(s)) return;
                Item sword = SWORD.get(mat, s);
                Tag<Item> rodTag = getForgeItemTag("rods/".concat(s.getId()));
                if (s == Wood) rodTag = Tags.Items.RODS_WOODEN; // Screw conventions...
                if (mat.has(INGOT)) {
                    Tag<Item> ingotTag = getForgeItemTag("ingots/".concat(mat.getId()));
                    ShapedRecipeBuilder.shapedRecipe(sword).key('P', ingotTag).key('H', rodTag)
                            .patternLine("P").patternLine("P").patternLine("H").setGroup("ingot_swords")
                            .addCriterion("has_ingot_" + mat.getId(), this.hasItem(ingotTag)).build(consumer);
                }
                else if (mat.has(GEM)) {
                    Tag<Item> gemTag = getForgeItemTag("gems/".concat(mat.getId()));
                    ShapedRecipeBuilder.shapedRecipe(sword).key('P', gemTag).key('H', rodTag)
                            .patternLine("P").patternLine("P").patternLine("H").setGroup("gem_swords")
                            .addCriterion("has_ingot_" + mat.getId(), this.hasItem(gemTag)).build(consumer);
                }
                else if (mat == Flint) {
                    ShapedRecipeBuilder.shapedRecipe(SWORD.get(Flint, s)).key('P', Items.FLINT).key('H', rodTag)
                            .patternLine("P").patternLine("P").patternLine("H").setGroup("primitive_swords")
                            .addCriterion("has_flint", this.hasItem(Items.FLINT)).build(consumer);
                }
            });
        });

        RegistrationHelper.getMaterialsForDomain(Ref.ID).stream().filter(m -> m.has(PLATE, INGOT)).forEach(mat -> {
            Item plate = PLATE.get(mat);
            Item ingot = INGOT.get(mat);
            Tag<Item> ingotTag = getForgeItemTag("ingots/".concat(mat.getId()));
            ShapedRecipeBuilder.shapedRecipe(plate).key('H', HAMMER.getTag()).key('I', ingotTag)
                    .patternLine("H").patternLine("I").patternLine("I").setGroup("ingots_hammer_to_plates")
                    .addCriterion("has_ingot_" + mat.getId(), this.hasItem(ingotTag)).build(consumer, sigh(ingot.getRegistryName().getPath() + "_hammer_to_" + plate.getRegistryName().getPath()));
        });

        RegistrationHelper.getMaterialsForDomain(Ref.ID).stream().filter(m -> m.has(DUST)).forEach(mat -> {
            Item dust = DUST.get(mat);
            if (mat.has(ROCK)) {
                Tag<Item> rockTag = getForgeItemTag("rocks/".concat(mat.getId()));
                Item rock = ROCK.get(mat);
                Item smallDust = DUST_SMALL.get(mat);
                ShapelessRecipeBuilder.shapelessRecipe(dust)
                        .addIngredient(rockTag).addIngredient(rockTag).addIngredient(rockTag)
                        .addIngredient(rockTag).addIngredient(rockTag).addIngredient(rockTag)
                        .addIngredient(rockTag).addIngredient(rockTag).addIngredient(MORTAR.getTag())
                        .addCriterion("has_rock_" + mat.getId(), this.hasItem(rockTag))
                        .setGroup("rocks_grind_to_dust").build(consumer, sigh(rock.getRegistryName().getPath() + "_grind_to_" + dust.getRegistryName().getPath()));

                ShapelessRecipeBuilder.shapelessRecipe(smallDust)
                        .addIngredient(rockTag).addIngredient(rockTag)
                        .addIngredient(rockTag).addIngredient(rockTag).addIngredient(MORTAR.getTag())
                        .addCriterion("has_rock_" + mat.getId(), this.hasItem(getForgeItemTag("rocks/".concat(mat.getId()))))
                        .setGroup("rocks_grind_to_small_dust").build(consumer, sigh(rock.getRegistryName().getPath() + "_grind_to_" + smallDust.getRegistryName().getPath()));
            }
            if (mat.has(INGOT, GRINDABLE)) {
                Item ingot = INGOT.get(mat);
                Tag<Item> ingotTag = getForgeItemTag("ingots/".concat(mat.getId()));
                ShapelessRecipeBuilder.shapelessRecipe(dust).addIngredient(ingotTag).addIngredient(MORTAR.getTag())
                        .addCriterion("has_ingot_" + mat.getId(), this.hasItem(getForgeItemTag("ingots/".concat(mat.getId()))))
                        .setGroup("ingots_grind_to_dust")
                        .build(consumer, sigh(ingot.getRegistryName().getPath() + "_grind_to_" + dust.getRegistryName().getPath()));
            }
        });
    }

    @Override
    public String getName() {
        return "GregTech Crafting Recipes";
    }

    private String sigh(String attach) {
        return Ref.ID.concat(":").concat(attach);
    }

}
