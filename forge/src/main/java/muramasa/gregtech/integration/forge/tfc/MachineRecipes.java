package muramasa.gregtech.integration.forge.tfc;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.data.Materials;
import muramasa.gregtech.data.RecipeMaps;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.TFCMaterials;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.material.MaterialTags.MACERATE_INTO;
import static muramasa.antimatter.material.MaterialTags.ORE_MULTI;
import static muramasa.gregtech.data.Materials.*;
import static net.dries007.tfc.common.blocks.rock.Ore.*;

public class MachineRecipes {
    public static void init(){
        initMaceratorRecipes();
    }

    public static void initMaceratorRecipes(){
        addMaceratorRecipe(NATIVE_COPPER, Copper);
        addMaceratorRecipe(NATIVE_GOLD, Gold);
        addMaceratorRecipe(NATIVE_SILVER, Silver);
        addMaceratorRecipe(HEMATITE, Iron);
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
        for (Material material : TFCRegistrar.array) {
            addMaceratorRecipe(material);
        }
    }

    private static void addMaceratorRecipe(Ore input, Material material){
        int multiplier = ORE_MULTI.getInt(material);
        ItemStack crushedStack = AntimatterMaterialTypes.CRUSHED.get(MACERATE_INTO.getMapping(material), multiplier);
        Material oreByProduct1 = !material.getByProducts().isEmpty() ? material.getByProducts().get(0) : MACERATE_INTO.getMapping(material);
        if (input.isGraded()){
            RecipeMaps.MACERATING.RB().ii(TFCItems.GRADED_ORES.get(input).get(Ore.Grade.POOR).get()).io(crushedStack, DUST.get(oreByProduct1, 1)).chances(1.0, 0.05 * multiplier).add("poor_" + material.getId() + "_tfc", 400, 2);
            RecipeMaps.MACERATING.RB().ii(TFCItems.GRADED_ORES.get(input).get(Ore.Grade.NORMAL).get()).io(Utils.ca(multiplier * 2, crushedStack), DUST.get(oreByProduct1, 1)).chances(1.0, 0.1 * multiplier).add("normal_" + material.getId() + "_tfc", 400, 2);
            RecipeMaps.MACERATING.RB().ii(TFCItems.GRADED_ORES.get(input).get(Ore.Grade.RICH).get()).io(Utils.ca(multiplier * 3, crushedStack), DUST.get(oreByProduct1, 1)).chances(1.0, 0.15 * multiplier).add("rich_" + material.getId() + "_tfc", 400, 2);
        } else {
            RecipeMaps.MACERATING.RB().ii(TFCItems.ORES.get(input).get()).io(Utils.ca(multiplier * 2, crushedStack), DUST.get(oreByProduct1, 1)).chances(1.0, 0.1 * multiplier).add("normal_" + material.getId() + "_tfc", 400, 2);
        }


    }

    private static void addMaceratorRecipe(Material material){
        int multiplier = ORE_MULTI.getInt(material);
        ItemStack crushedStack = AntimatterMaterialTypes.CRUSHED.get(MACERATE_INTO.getMapping(material), multiplier);
        Material oreByProduct1 = !material.getByProducts().isEmpty() ? material.getByProducts().get(0) : MACERATE_INTO.getMapping(material);
        RecipeMaps.MACERATING.RB().ii(GregTech.get(Item.class, "poor_" + material.getId())).io(crushedStack, DUST.get(oreByProduct1, 1)).chances(1.0, 0.05 * multiplier).add("poor_" + material.getId() + "_tfc", 400, 2);
        RecipeMaps.MACERATING.RB().ii(GregTech.get(Item.class, "normal_" + material.getId())).io(Utils.ca(multiplier * 2, crushedStack), DUST.get(oreByProduct1, 1)).chances(1.0, 0.1 * multiplier).add("normal_" + material.getId() + "_tfc", 400, 2);
        RecipeMaps.MACERATING.RB().ii(GregTech.get(Item.class, "rich_" + material.getId())).io(Utils.ca(multiplier * 3, crushedStack), DUST.get(oreByProduct1, 1)).chances(1.0, 0.15 * multiplier).add("rich_" + material.getId() + "_tfc", 400, 2);
    }
}
