package muramasa.gregtech.integration.forge.tfc;


import io.github.gregtechintergalactical.gtcore.data.GTCoreTags;
import io.github.gregtechintergalactical.gtcore.integration.tfc.TFCRubberData;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.data.RecipeMaps;
import muramasa.gregtech.loader.machines.CuttingLoader;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Helpers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.material.MaterialTags.MACERATE_INTO;
import static muramasa.antimatter.material.MaterialTags.ORE_MULTI;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.FLUID_PRESS;
import static net.dries007.tfc.common.blocks.rock.Ore.*;

public class MachineRecipes {
    public static void init(){
        initMaceratorRecipes();
    }

    public static void initMaceratorRecipes(){
        addMaceratorRecipe(NATIVE_COPPER, Copper);
        addMaceratorRecipe(NATIVE_GOLD, Gold);
        addMaceratorRecipe(NATIVE_SILVER, Silver);
        addMaceratorRecipe(HEMATITE, Hematite);
        addMaceratorRecipe(CASSITERITE, Cassiterite);
        addMaceratorRecipe(BISMUTHINITE, Bismuth);
        addMaceratorRecipe(GARNIERITE, Garnierite);
        addMaceratorRecipe(MALACHITE, Malachite);
        addMaceratorRecipe(MAGNETITE, Magnetite);
        addMaceratorRecipe(LIMONITE, YellowLimonite);
        addMaceratorRecipe(SPHALERITE, Sphalerite);
        addMaceratorRecipe(TETRAHEDRITE, Tetrahedrite);
        addMaceratorRecipe(BITUMINOUS_COAL, Coal);
        addMaceratorRecipe(LIGNITE, Lignite);
        //addMaceratorRecipe(KAOLINITE);
        //addMaceratorRecipe(GYPSUM,);
        addMaceratorRecipe(GRAPHITE, Graphite);
        addMaceratorRecipe(SULFUR, Sulfur);
        addMaceratorRecipe(CINNABAR, Cinnabar);
        addMaceratorRecipe(CRYOLITE, Redstone);
        addMaceratorRecipe(SALTPETER, Saltpeter);
        addMaceratorRecipe(HALITE, Salt);
        addMaceratorRecipe(LAPIS_LAZULI, Lapis);
        addMaceratorRecipe(EMERALD, Emerald);
        addMaceratorRecipe(DIAMOND, Diamond);
        addMaceratorRecipe(PYRITE, Pyrite);
        addMaceratorRecipe(RUBY, Ruby);
        addMaceratorRecipe(SAPPHIRE, Sapphire);
        addMaceratorRecipe(TOPAZ, Topaz);
        addMaceratorRecipe(OPAL, Opal);
        addMaceratorRecipe(AMETHYST, Amethyst);
        /*for (Material material : TFCRegistrar.array) {
            addMaceratorRecipe(material);
        }*/
        Helpers.mapOfKeys(net.dries007.tfc.common.blocks.wood.Wood.class, w -> {
            CuttingLoader.addWoodRecipe(TagUtils.getItemTag(new ResourceLocation(Ref.MOD_TFC, w.name().toLowerCase() + "_logs")), AntimatterPlatformUtils.getItemFromID(Ref.MOD_TFC, "wood/lumber/" + w.name().toLowerCase()), 2, w.name().toLowerCase() + "_lumber", 200, 8);
            return true;
        });
        CuttingLoader.addWoodRecipe(GTCoreTags.RUBBER_LOGS, TFCRubberData.RUBBER_LUMBER, 2, "rubber_lumber", 200, 8);
        FLUID_PRESS.RB().ii(RecipeIngredient.of(TagUtils.getItemTag(new ResourceLocation("tfc:seeds")))).fo(SeedOil.getLiquid(10)).add("seed_oil_tfc", 32, 2);
        FLUID_PRESS.RB().ii(AntimatterPlatformUtils.getItemFromID(Ref.MOD_TFC, "food/cod")).fo(FishOil.getLiquid(40)).add("fish_oil_cod_tfc", 16, 4);
        FLUID_PRESS.RB().ii(AntimatterPlatformUtils.getItemFromID(Ref.MOD_TFC, "food/salmon")).fo(FishOil.getLiquid(60)).add("fish_oil_salmon_tfc", 16, 4);
        FLUID_PRESS.RB().ii(AntimatterPlatformUtils.getItemFromID(Ref.MOD_TFC, "food/tropical_fish")).fo(FishOil.getLiquid(70)).add("fish_oil_tropical_fish_tfc", 16, 4);
    }

    private static void addMaceratorRecipe(Ore input, Material material){
        int multiplier = ORE_MULTI.getInt(material);
        ItemStack crushedStack = AntimatterMaterialTypes.CRUSHED.get(MACERATE_INTO.getMapping(material), multiplier);
        Material oreByProduct1 = !material.getByProducts().isEmpty() ? material.getByProducts().get(0) : MACERATE_INTO.getMapping(material);
        if (input.isGraded()){
            RecipeMaps.MACERATOR.RB().ii(TFCItems.GRADED_ORES.get(input).get(Ore.Grade.POOR).get()).io(crushedStack, DUST.get(oreByProduct1, 1)).chances(1.0, 0.05 * multiplier).add("poor_" + material.getId() + "_tfc", 400, 2);
            RecipeMaps.MACERATOR.RB().ii(TFCItems.GRADED_ORES.get(input).get(Ore.Grade.NORMAL).get()).io(Utils.ca(multiplier * 2, crushedStack), DUST.get(oreByProduct1, 1)).chances(1.0, 0.1 * multiplier).add("normal_" + material.getId() + "_tfc", 400, 2);
            RecipeMaps.MACERATOR.RB().ii(TFCItems.GRADED_ORES.get(input).get(Ore.Grade.RICH).get()).io(Utils.ca(multiplier * 3, crushedStack), DUST.get(oreByProduct1, 1)).chances(1.0, 0.15 * multiplier).add("rich_" + material.getId() + "_tfc", 400, 2);
        } else {
            RecipeMaps.MACERATOR.RB().ii(TFCItems.ORES.get(input).get()).io(Utils.ca(multiplier * 2, crushedStack), DUST.get(oreByProduct1, 1)).chances(1.0, 0.1 * multiplier).add("normal_" + material.getId() + "_tfc", 400, 2);
        }


    }

    private static void addMaceratorRecipe(Material material){
        int multiplier = ORE_MULTI.getInt(material);
        ItemStack crushedStack = AntimatterMaterialTypes.CRUSHED.get(MACERATE_INTO.getMapping(material), multiplier);
        Material oreByProduct1 = !material.getByProducts().isEmpty() ? material.getByProducts().get(0) : MACERATE_INTO.getMapping(material);
        RecipeMaps.MACERATOR.RB().ii(GregTech.get(Item.class, "poor_" + material.getId())).io(crushedStack, DUST.get(oreByProduct1, 1)).chances(1.0, 0.05 * multiplier).add("poor_" + material.getId() + "_tfc", 400, 2);
        RecipeMaps.MACERATOR.RB().ii(GregTech.get(Item.class, "normal_" + material.getId())).io(Utils.ca(multiplier * 2, crushedStack), DUST.get(oreByProduct1, 1)).chances(1.0, 0.1 * multiplier).add("normal_" + material.getId() + "_tfc", 400, 2);
        RecipeMaps.MACERATOR.RB().ii(GregTech.get(Item.class, "rich_" + material.getId())).io(Utils.ca(multiplier * 3, crushedStack), DUST.get(oreByProduct1, 1)).chances(1.0, 0.15 * multiplier).add("rich_" + material.getId() + "_tfc", 400, 2);
    }
}
