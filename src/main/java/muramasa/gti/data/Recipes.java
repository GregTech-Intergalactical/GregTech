package muramasa.gti.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.builder.AntimatterShapedRecipeBuilder;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.condition.ConfigCondition;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.gti.Ref;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.material.MaterialType.*;
import static muramasa.antimatter.util.Utils.getForgeItemTag;
import static muramasa.gti.data.Data.ItemFilter;
import static muramasa.gti.data.Materials.*;

public class Recipes extends RecipeProvider {

    public Recipes(DataGenerator gen) {
        super(gen);
    }

    @Override
    public void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        ConditionalRecipe.builder().addCondition(new ConfigCondition(Ref.class, "sulfurTorch"))
                .addRecipe(ShapedRecipeBuilder.shapedRecipe(Blocks.TORCH, 6)
                .key('D', getForgeItemTag("dusts/sulfur")).key('R', Tags.Items.RODS_WOODEN)
                .patternLine("D")
                .patternLine("R").addCriterion("has_sulfur_dust", this.hasItem(getForgeItemTag("dusts/sulfur")))::build)
                .build(consumer, Ref.ID, "sulfur_torch");  // Wow thanks Forge

        ShapedRecipeBuilder.shapedRecipe(Blocks.HOPPER).key('C', Blocks.CHEST).key('I', getForgeItemTag("plates/iron")).key('W', WRENCH.getTag())
                .patternLine("IWI")
                .patternLine("ICI")
                .patternLine(" I ").addCriterion("has_wrench", this.hasItem(WRENCH.getTag())).build(consumer, Ref.ID + ":hopper");

        ShapedRecipeBuilder.shapedRecipe(ItemFilter).key('Z', getForgeItemTag("foils/zinc")).key('I', getForgeItemTag("plates/iron"))
                .patternLine("ZZZ")
                .patternLine("ZIZ")
                .patternLine("ZZZ").addCriterion("has_iron_plate", this.hasItem(getForgeItemTag("plates/iron"))).build(consumer);

        ShapedRecipeBuilder.shapedRecipe(GEAR.get(Wood)).key('P', ItemTags.PLANKS).key('W', WRENCH.getTag()).key('R', Tags.Items.RODS_WOODEN)
                .patternLine("RPR")
                .patternLine("PWP")
                .patternLine("RPR").addCriterion("has_wooden_rod", this.hasItem(Tags.Items.RODS_WOODEN)).setGroup("gears").build(consumer);

        ShapedRecipeBuilder.shapedRecipe(GEAR.get(Wood)).key('P', ItemTags.PLANKS).key('W', WRENCH.getTag()).key('R', Tags.Items.RODS_WOODEN)
                .patternLine("PRP")
                .patternLine("RWR")
                .patternLine("PRP").addCriterion("has_wooden_rod", this.hasItem(Tags.Items.RODS_WOODEN))
                .setGroup("gears").build(consumer, GEAR.get(Wood).getRegistryName().toString() + "_alt");

        ShapedRecipeBuilder.shapedRecipe(GEAR.get(Stone)).key('S', Tags.Items.STONE).key('W', WRENCH.getTag()).key('C', Tags.Items.COBBLESTONE)
                .patternLine("CSC")
                .patternLine("SWS")
                .patternLine("CSC").addCriterion("has_stone", this.hasItem(Tags.Items.STONE)).setGroup("gears").build(consumer);

        ShapedRecipeBuilder.shapedRecipe(GEAR.get(Stone)).key('S', Tags.Items.STONE).key('W', WRENCH.getTag()).key('C', Tags.Items.COBBLESTONE)
                .patternLine("SCS")
                .patternLine("CWC")
                .patternLine("SCS").addCriterion("has_stone", this.hasItem(Tags.Items.STONE))
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

        List<Material> gtMainMats = AntimatterAPI.all(Material.class).stream().filter(m -> (m.getDomain().equals(Ref.ID) && m.has(TOOLS))).collect(Collectors.toList());
        List<Material> gtHandleMats = AntimatterAPI.all(Material.class).stream().filter(m -> (m.getDomain().equals(Ref.ID) && m.isHandle())).collect(Collectors.toList());

        gtMainMats.forEach(main -> {
            if (!main.has(INGOT)) return;
            for (Material handle : gtHandleMats) {
                Tag<Item> ingotTag = getForgeItemTag("ingots/".concat(main.getId()));
                Tag<Item> rodTag = getForgeItemTag("rods/".concat(handle.getId()));
                Tag<Item> plateTag = getForgeItemTag("plates/".concat(main.getId()));
                if (handle == Wood) rodTag = Tags.Items.RODS_WOODEN; // Screw conventions...
                AntimatterShapedRecipeBuilder.shapedRecipe(AntimatterAPI.get(IAntimatterTool.class, PICKAXE.getId()).asItemStack(main, handle)).key('I', ingotTag).key('R', rodTag)
                        .patternLine("III")
                        .patternLine(" R ")
                        .patternLine(" R ").addCriterion("has_material_" + main.getId(), this.hasItem(INGOT.get(main)))
                        .setGroup("antimatter_pickaxes")
                        .build(consumer, fixLoc(PICKAXE.getId() + "_" + main.getId() + "_" + handle.getId()));
                AntimatterShapedRecipeBuilder.shapedRecipe(AntimatterAPI.get(IAntimatterTool.class, AXE.getId()).asItemStack(main, handle)).key('I', ingotTag).key('R', rodTag)
                        .patternLine("II ")
                        .patternLine("IR ")
                        .patternLine(" R ").addCriterion("has_material_" + main.getId(), this.hasItem(INGOT.get(main)))
                        .setGroup("antimatter_axes")
                        .build(consumer, fixLoc(AXE.getId() + "_" + main.getId() + "_" + handle.getId()));
                AntimatterShapedRecipeBuilder.shapedRecipe(AntimatterAPI.get(IAntimatterTool.class, HOE.getId()).asItemStack(main, handle)).key('I', ingotTag).key('R', rodTag)
                        .patternLine("II ")
                        .patternLine(" R ")
                        .patternLine(" R ").addCriterion("has_material_" + main.getId(), this.hasItem(INGOT.get(main)))
                        .setGroup("antimatter_hoes")
                        .build(consumer, fixLoc(HOE.getId() + "_" + main.getId() + "_" + handle.getId()));
                AntimatterShapedRecipeBuilder.shapedRecipe(AntimatterAPI.get(IAntimatterTool.class, SHOVEL.getId()).asItemStack(main, handle)).key('I', ingotTag).key('R', rodTag)
                        .patternLine("I")
                        .patternLine("R")
                        .patternLine("R").addCriterion("has_material_" + main.getId(), this.hasItem(INGOT.get(main)))
                        .setGroup("antimatter_shovels")
                        .build(consumer, fixLoc(SHOVEL.getId() + "_" + main.getId() + "_" + handle.getId()));
                AntimatterShapedRecipeBuilder.shapedRecipe(AntimatterAPI.get(IAntimatterTool.class, SWORD.getId()).asItemStack(main, handle)).key('I', ingotTag).key('R', rodTag)
                        .patternLine("I")
                        .patternLine("I")
                        .patternLine("R").addCriterion("has_material_" + main.getId(), this.hasItem(INGOT.get(main)))
                        .setGroup("antimatter_swords")
                        .build(consumer, fixLoc(SWORD.getId() + "_" + main.getId() + "_" + handle.getId()));
                AntimatterShapedRecipeBuilder.shapedRecipe(AntimatterAPI.get(IAntimatterTool.class, SAW.getId()).asItemStack(main, handle))
                        .key('P', plateTag).key('R', rodTag).key('F', FILE.getTag()).key('H', HAMMER.getTag())
                        .patternLine("PPR")
                        .patternLine("FH ").addCriterion("has_material_" + main.getId(), this.hasItem(INGOT.get(main)))
                        .setGroup("antimatter_saws")
                        .build(consumer, fixLoc(SAW.getId() + "_" + main.getId() + "_" + handle.getId()));
            }
        });

//        RegistrationHelper.getMaterialsForDomain(Ref.ID).stream().filter(m -> m.has(TOOLS)).forEach(mat -> {
//            RegistrationHelper.getMaterialsForDomain(Ref.ID).stream().filter(s -> s.isHandle()).forEach(s -> {
//                if (mat.equals(s)) return;
//                Item sword = SWORD.get(mat, s);
//                Tag<Item> rodTag = getForgeItemTag("rods/".concat(s.getId()));
//                if (s == Wood) rodTag = Tags.Items.RODS_WOODEN; // Screw conventions...
//                if (mat.has(INGOT)) {
//                    Tag<Item> ingotTag = getForgeItemTag("ingots/".concat(mat.getId()));
//                    ShapedRecipeBuilder.shapedRecipe(sword).key('I', ingotTag).key('H', rodTag)
//                            .patternLine("P").patternLine("P").patternLine("H").setGroup("ingot_swords")
//                            .addCriterion("has_ingot_" + mat.getId(), this.hasItem(ingotTag)).build(consumer);
//                }
//                else if (mat.has(GEM)) {
//                    Tag<Item> gemTag = getForgeItemTag("gems/".concat(mat.getId()));
//                    ShapedRecipeBuilder.shapedRecipe(sword).key('I', gemTag).key('H', rodTag)
//                            .patternLine("P").patternLine("P").patternLine("H").setGroup("gem_swords")
//                            .addCriterion("has_ingot_" + mat.getId(), this.hasItem(gemTag)).build(consumer);
//                }
//                else if (mat == Flint) {
//                    ShapedRecipeBuilder.shapedRecipe(SWORD.get(Flint, s)).key('I', Items.FLINT).key('H', rodTag)
//                            .patternLine("P").patternLine("P").patternLine("H").setGroup("primitive_swords")
//                            .addCriterion("has_flint", this.hasItem(Items.FLINT)).build(consumer);
//                }
//            });
//        });
//
//        RegistrationHelper.getMaterialsForDomain(Ref.ID).stream().filter(m -> m.has(PLATE, INGOT)).forEach(mat -> {
//            Item plate = PLATE.get(mat);
//            Item ingot = INGOT.get(mat);
//            Tag<Item> ingotTag = getForgeItemTag("ingots/".concat(mat.getId()));
//            ShapedRecipeBuilder.shapedRecipe(plate).key('H', HAMMER.getTag()).key('I', ingotTag)
//                    .patternLine("H").patternLine("I").patternLine("I").setGroup("ingots_hammer_to_plates")
//                    .addCriterion("has_ingot_" + mat.getId(), this.hasItem(ingotTag)).build(consumer, sigh(ingot.getRegistryName().getPath() + "_hammer_to_" + plate.getRegistryName().getPath()));
//        });
//
//        RegistrationHelper.getMaterialsForDomain(Ref.ID).stream().filter(m -> m.has(DUST)).forEach(mat -> {
//            Item dust = DUST.get(mat);
//            if (mat.has(ROCK)) {
//                Tag<Item> rockTag = getForgeItemTag("rocks/".concat(mat.getId()));
//                Item rock = ROCK.get(mat);
//                Item smallDust = DUST_SMALL.get(mat);
//                ShapelessRecipeBuilder.shapelessRecipe(dust)
//                        .addIngredient(rockTag).addIngredient(rockTag).addIngredient(rockTag)
//                        .addIngredient(rockTag).addIngredient(rockTag).addIngredient(rockTag)
//                        .addIngredient(rockTag).addIngredient(rockTag).addIngredient(MORTAR.getTag())
//                        .addCriterion("has_rock_" + mat.getId(), this.hasItem(rockTag))
//                        .setGroup("rocks_grind_to_dust").build(consumer, sigh(rock.getRegistryName().getPath() + "_grind_to_" + dust.getRegistryName().getPath()));
//
//                ShapelessRecipeBuilder.shapelessRecipe(smallDust)
//                        .addIngredient(rockTag).addIngredient(rockTag)
//                        .addIngredient(rockTag).addIngredient(rockTag).addIngredient(MORTAR.getTag())
//                        .addCriterion("has_rock_" + mat.getId(), this.hasItem(getForgeItemTag("rocks/".concat(mat.getId()))))
//                        .setGroup("rocks_grind_to_small_dust").build(consumer, sigh(rock.getRegistryName().getPath() + "_grind_to_" + smallDust.getRegistryName().getPath()));
//            }
//            if (mat.has(INGOT, GRINDABLE)) {
//                Item ingot = INGOT.get(mat);
//                Tag<Item> ingotTag = getForgeItemTag("ingots/".concat(mat.getId()));
//                ShapelessRecipeBuilder.shapelessRecipe(dust).addIngredient(ingotTag).addIngredient(MORTAR.getTag())
//                        .addCriterion("has_ingot_" + mat.getId(), this.hasItem(getForgeItemTag("ingots/".concat(mat.getId()))))
//                        .setGroup("ingots_grind_to_dust")
//                        .build(consumer, sigh(ingot.getRegistryName().getPath() + "_grind_to_" + dust.getRegistryName().getPath()));
//            }
//        });
    }

    @Override
    public String getName() {
        return "GregTech Crafting Recipes";
    }

    private String fixLoc(String attach) {
        return Ref.ID.concat(":").concat(attach);
    }

}
