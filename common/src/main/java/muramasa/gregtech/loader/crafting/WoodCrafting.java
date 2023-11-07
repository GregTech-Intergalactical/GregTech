package muramasa.gregtech.loader.crafting;

import io.github.gregtechintergalactical.gtcore.GTCoreConfig;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterDefaultTools.SAW;

public class WoodCrafting {

    public static void loadRecipes(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider) {
        provider.addItemRecipe(consumer, GTIRef.ID, "tiny_wooden_fluid_pipe", "pipes",
                GregTechData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.TINY), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.WOODEN_SLABS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", " s ", "H  ");
        provider.addItemRecipe(consumer, GTIRef.ID, "small_wooden_fluid_pipe", "pipes",
                GregTechData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.SMALL), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", " s ", "H  ");
        provider.addItemRecipe(consumer, GTIRef.ID, "normal_wooden_fluid_pipe", "pipes",
                GregTechData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.NORMAL), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", "sss", "H  ");
        provider.addItemRecipe(consumer, GTIRef.ID, "large_wooden_fluid_pipe", "pipes",
                GregTechData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.LARGE), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "ssS", "s s", "Hss");
        provider.addItemRecipe(consumer, GTIRef.ID, "huge_wooden_fluid_pipe", "pipes",
                GregTechData.FLUID_PIPE_WOOD.getBlockItem(PipeSize.HUGE), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.LOGS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", "s s", "H  ");
        if (GTCoreConfig.HARDER_WOOD.get()){
            provider.addStackRecipe(consumer, "minecraft", "", "wood_stuff", new ItemStack(Items.STICK, 2), of('P', ItemTags.PLANKS), "P", "P");
            provider.addStackRecipe(consumer, GTIRef.ID, "sticks_4", "wood_stuff", new ItemStack(Items.STICK, 4), of('P', ItemTags.PLANKS, 'S', SAW.getTag()), "S", "P", "P");
            addWoodRecipe(consumer, provider, "minecraft", ItemTags.OAK_LOGS, Items.OAK_PLANKS);
            addWoodRecipe(consumer, provider, "minecraft", ItemTags.BIRCH_LOGS, Items.BIRCH_PLANKS);
            addWoodRecipe(consumer, provider, "minecraft", ItemTags.SPRUCE_LOGS, Items.SPRUCE_PLANKS);
            addWoodRecipe(consumer, provider, "minecraft", ItemTags.JUNGLE_LOGS, Items.JUNGLE_PLANKS);
            addWoodRecipe(consumer, provider, "minecraft", ItemTags.ACACIA_LOGS, Items.ACACIA_PLANKS);
            addWoodRecipe(consumer, provider, "minecraft", ItemTags.DARK_OAK_LOGS, Items.DARK_OAK_PLANKS);
            addWoodRecipe(consumer, provider, "minecraft", ItemTags.CRIMSON_STEMS, Items.CRIMSON_PLANKS);
            addWoodRecipe(consumer, provider, "minecraft", ItemTags.WARPED_STEMS, Items.WARPED_PLANKS);
        }
        String[] stones = {"stone", "smooth_stone", "sandstone", "cut_sandstone", "cobblestone", "red_sandstone", "cut_red_sandstone", "prismarine", "dark_prismarine", "polished_granite", "smooth_red_sandstone", "polished_diorite", "mossy_cobblestone", "smooth_sandstone", "smooth_quartz", "granite", "andesite", "polished_andesite", "diorite", "blackstone", "polished_blackstone", "purpur", "quartz", "brick", "stone_brick", "nether_brick", "prismarine_brick", "mossy_stone_brick", "end_stone_brick", "red_nether_brick", "polished_blackstone_brick"};
        for (String stone : stones) {
            Item full = AntimatterPlatformUtils.getItemFromID(new ResourceLocation(stone + (stone.equals("purpur") || stone.equals("quartz") ? "_block" : stone.contains("brick") ? "s" : "")));
            Item slab = AntimatterPlatformUtils.getItemFromID(new ResourceLocation(stone + "_slab"));
            String[] pattern = stone.equals("purpur") || stone.equals("quartz") || stone.equals("sandstone") || stone.equals("red_sandstone") || stone.equals("stone_brick") || stone.equals("nether_brick") || stone.equals("polished_blackstone") ? new String[]{"SS"} : new String[]{"S", "S"};
            provider.addItemRecipe(consumer, GTIRef.ID, stone + "_slab_to_" + stone, "slabs", full, of('S', slab), pattern);
        }
        String[] wood = {"oak", "birch", "spruce", "jungle", "acacia", "dark_oak", "crimson", "warped"};
        for (String s : wood) {
            ResourceLocation name = new ResourceLocation(s + "_planks");
            ResourceLocation slab = new ResourceLocation(s + "_slab");
            provider.addItemRecipe(consumer, GTIRef.ID, slab.getPath() + "_to_" + name.getPath(), "slabs", AntimatterPlatformUtils.getItemFromID(name), of('S', AntimatterPlatformUtils.getItemFromID(slab)), "S", "S");
        }
    }

    public static void addWoodRecipe(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider, String domain, TagKey<Item> log, Item plank){
        provider.shapeless(consumer, domain, "", "planks", new ItemStack(plank, 2), log);
        provider.addStackRecipe(consumer, domain, plank.getRegistryName().getPath() + "_4", "planks", new ItemStack(plank, 4), of('S', SAW.getTag(), 'P', log), "S", "P");
    }
}
