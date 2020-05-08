package muramasa.gti.datagen;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.builder.AntimatterShapedRecipeBuilder;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.condition.ConfigCondition;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.gti.Ref;
import muramasa.gti.data.Data;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.material.MaterialType.*;
import static muramasa.antimatter.util.Utils.getForgeItemTag;
import static muramasa.gti.data.Data.ItemFilter;
import static muramasa.gti.data.Materials.*;

public class GregTechRecipes extends AntimatterRecipeProvider {

    public GregTechRecipes(DataGenerator gen, String providerDomain, String providerName) {
        super(gen, providerDomain, providerName);
    }

    @Override
    public void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        super.registerRecipes(consumer);
        addConditionalRecipe(consumer, getStackRecipe("", "has_sulfur_dust", this.hasItem(getForgeItemTag("dusts/sulfur")),
                new ItemStack(Blocks.TORCH, 6), of('D', getForgeItemTag("dusts/sulfur"), 'R', Tags.Items.RODS_WOODEN), "D", "R"), Ref.class, "sulfurTorch", Ref.ID, "sulfur_torch");

        addItemRecipe(consumer, Ref.ID, "hopper", "", "has_wrench", this.hasItem(WRENCH.getTag()),
                Blocks.HOPPER, of('C', Blocks.CHEST, 'I', getForgeItemTag("plates/iron"), 'W', WRENCH.getTag()), "IWI", "ICI", " I ");

        addItemRecipe(consumer, "", "", "", "has_iron_plate", this.hasItem(WRENCH.getTag()),
                ItemFilter, of('Z', getForgeItemTag("foils/zinc"), 'I', getForgeItemTag("plates/iron"), 'W', WRENCH.getTag()), "ZZZ", "ZIZ", "ZZZ");

        addItemRecipe(consumer, "", "", "gears", "has_wooden_rod", this.hasItem(Tags.Items.RODS_WOODEN),
                GEAR.get(Wood), of('P', ItemTags.PLANKS, 'W', WRENCH.getTag(), 'R', Tags.Items.RODS_WOODEN), "RPR", "PWP", "RPR");

        addItemRecipe(consumer, Ref.ID, GEAR.get(Wood).getRegistryName().toString() + "_alt", "gears", "has_wooden_rod", this.hasItem(Tags.Items.RODS_WOODEN),
                GEAR.get(Wood), of('P', ItemTags.PLANKS, 'W', WRENCH.getTag(), 'R', Tags.Items.RODS_WOODEN), "RPR", "PWP", "RPR");

        addItemRecipe(consumer, "", "", "gears", "has_stone", this.hasItem(Tags.Items.STONE),
                GEAR.get(Stone), of('S', Tags.Items.STONE, 'W', WRENCH.getTag(), 'C', Tags.Items.COBBLESTONE), "SCS", "CWC", "SCS");

        addItemRecipe(consumer, Ref.ID, GEAR.get(Stone).getRegistryName().toString() + "_alt", "gears", "has_stone", this.hasItem(Tags.Items.STONE),
                GEAR.get(Stone), of('S', Tags.Items.STONE, 'W', WRENCH.getTag(), 'C', Tags.Items.COBBLESTONE), "CSC", "SWS", "CSC");


        ShapelessRecipeBuilder.shapelessRecipe(Blocks.STICKY_PISTON)
                .addIngredient(Blocks.PISTON).addIngredient(Data.StickyResin)
                .addCriterion("has_piston", this.hasItem(Blocks.PISTON)).build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(ROD.get(IronMagnetic))
                .addIngredient(getForgeItemTag("rods/iron")).addIngredient(Items.REDSTONE).addIngredient(Items.REDSTONE).addIngredient(Items.REDSTONE).addIngredient(Items.REDSTONE)
                .addCriterion("has_iron_rod", this.hasItem(getForgeItemTag("rods/iron"))).setGroup("magnetic_rods").build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(ROD.get(Neodymium))
                .addIngredient(getForgeItemTag("rods/neodymium")).addIngredient(Items.REDSTONE).addIngredient(Items.REDSTONE).addIngredient(Items.REDSTONE).addIngredient(Items.REDSTONE)
                .addCriterion("has_neodymium_rod", this.hasItem(getForgeItemTag("rods/neodymium"))).setGroup("magnetic_rods").build(consumer);

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
