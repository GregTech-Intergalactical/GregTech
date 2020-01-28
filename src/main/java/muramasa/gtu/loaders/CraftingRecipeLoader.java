//package muramasa.gtu.loaders;
//
//import muramasa.antimatter.recipe.RecipeHelper;
//import muramasa.antimatter.tools.GregTechToolType;
//import net.minecraft.block.Blocks;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.Items;
//
//import static muramasa.gtu.data.Materials.*;
//import static muramasa.gtu.common.Data.*;
//
//public class CraftingRecipeLoader {
//
//    public static void init() {
//        ItemStack rockFlint = Flint.getRock(1);
//        //Casings
//        RecipeHelper.addShaped("casing_ulv", CASING_ULV.asItemStack(), "PPP", "PwP", "PPP", 'P', WroughtIron.getPlate(1));
//        RecipeHelper.addShaped("casing_lv", CASING_LV.asItemStack(), "PPP", "PwP", "PPP", 'P', Steel.getPlate(1));
//        RecipeHelper.addShaped("casing_mv", CASING_MV.asItemStack(), "PPP", "PwP", "PPP", 'P', Aluminium.getPlate(1));
//        RecipeHelper.addShaped("casing_hv", CASING_HV.asItemStack(), "PPP", "PwP", "PPP", 'P', StainlessSteel.getPlate(1));
//        RecipeHelper.addShaped("casing_ev", CASING_EV.asItemStack(), "PPP", "PwP", "PPP", 'P', Titanium.getPlate(1));
//        RecipeHelper.addShaped("casing_iv", CASING_IV.asItemStack(), "PPP", "PwP", "PPP", 'P', TungstenSteel.getPlate(1));
//        RecipeHelper.addShaped("casing_luv", CASING_LUV.asItemStack(), "PPP", "PwP", "PPP", 'P', Chrome.getPlate(1));
//        RecipeHelper.addShaped("casing_zpm", CASING_ZPM.asItemStack(), "PPP", "PwP", "PPP", 'P', Iridium.getPlate(1));
//        RecipeHelper.addShaped("casing_uv", CASING_UV.asItemStack(), "PPP", "PwP", "PPP", 'P', Osmium.getPlate(1));
//        RecipeHelper.addShaped("casing_max", CASING_MAX.asItemStack(), "PPP", "PwP", "PPP", 'P', Neutronium.getPlate(1));
//
//        RecipeHelper.addShapeless("rock_to_flint", new ItemStack(Items.FLINT), rockFlint, rockFlint, GregTechToolType.MORTAR.getOreDict());
//
//        RecipeHelper.addShaped("mortar_flint", GregTechToolType.MORTAR.get(Flint), " F ", "SFS", "SSS", 'F', Items.FLINT, 'S', "stone");
//
//        RecipeHelper.addShaped("hopper", new ItemStack(Blocks.HOPPER), "IxI", "ICI", " I ", 'I', Vibranium.getIngot(1), 'C', new ItemStack(Blocks.CHEST));
//        RecipeHelper.addShapeless("scanner", DebugScanner.get(1), Vibranium.getIngot(1), Iridium.getIngot(1));
//
//        RecipeHelper.addShaped("gear_wood", Wood.getGear(1), "SPS", "PwP", "SPS", 'P', "plankWood", 'S', "stickWood");
//        RecipeHelper.addShaped("gear_stone", Stone.getGear(1), "SPS", "PhP", "SPS", 'P', "cobblestone", 'S', "stone");
//
//        //RecipeHelper.addShapeless("paper_ring", Paper.getRing(1), "k", "X", 'X', Paper.getRing(1));
//        //RecipeHelper.addShapeless("silicone_rubber__ring", SiliconeRubber.getRing(1), "k", "X", 'X', SiliconeRubber.getRing(1));
//        RecipeHelper.addShapeless("rubber_ring", Rubber.getRing(1), GregTechToolType.KNIFE.getOreDict(), Rubber.getPlate(1));
//        RecipeHelper.addShapeless("styrene_butadiene_rubber_ring", StyreneButadieneRubber.getRing(1), GregTechToolType.KNIFE.getOreDict(), StyreneButadieneRubber.getPlate(1));
//
//        RecipeHelper.addShaped("rubber_torch", new ItemStack(Blocks.TORCH, 4), " R ", " S ", "   ", 'R', "dropRubber", 'S', "stickWood");
//        //RecipeHelper.addShaped("sulfur_torch", new ItemStack(Blocks.TORCH, 6), " R ", " S ", "   ", 'R', Sulfur.getDust(1), 'S', "stickWood");
//
//        RecipeHelper.addShapeless("resin_sticky_piston", new ItemStack(Blocks.STICKY_PISTON), "dropRubber", Blocks.PISTON);
//
//        RecipeHelper.addShaped("item_filter", ItemFilter.asItemStack(), "XXX", "XYX", "XXX", 'X', Zinc.getFoil(1), 'Y', Iron.getPlate(1));
//        RecipeHelper.addShapeless("magnetic_rod", IronMagnetic.getRod(1), Iron.getRod(1), Items.REDSTONE, Items.REDSTONE, Items.REDSTONE, Items.REDSTONE);
//    }
//}
