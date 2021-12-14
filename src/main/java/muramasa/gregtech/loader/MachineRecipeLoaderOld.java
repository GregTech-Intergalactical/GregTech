//package muramasa.gregtech.loaders;
//
//import muramasa.gregtech.api.data.Machines;
//import muramasa.gregtech.api.data.Materials;
//import muramasa.gregtech.api.recipe.RecipeBuilder;
//import net.minecraft.init.Blocks;
//import net.minecraft.init.Items;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.common.ForgeHooks;
//import net.minecraftforge.fluids.Fluid;
//import net.minecraftforge.fluids.FluidRegistry;
//import net.minecraftforge.fluids.FluidStack;
//
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.Map;
//
//import static muramasa.gregtech.api.data.Machines.*;
//
//public class MachineRecipeLoaderOld {
//
//    public static RecipeBuilder RB = new RecipeBuilder();
//
////    public static void init() {
////        for (Material m : GenerationFlag.ORE.all()) {
////            RecipeBuilder.get(PULVERIZER).ii(m.getChunk(1)).io(m.getCrushed(2)).build(40, 1);
////            RecipeBuilder.get(THERMAL_CENTRIFUGE).ii(m.getCrushed(1)).io(m.getCrushedC(1), m.getDust(1), m.getDustT(4)).build(40, 1);
////            RecipeBuilder.get(ORE_WASHER).ii(m.getCrushed(1)).fi(new FluidStack(FluidRegistry.WATER, 100)).io(m.getCrushedP(1), m.getDustT(1), Stone.getDust(1)).build(40, 1);
////        }
////
////        RecipeBuilder.get(ALLOY_SMELTER).ii(Copper.getIngot(1), Redstone.getDust(4)).io(RedAlloy.getIngot(1)).build(10, 1);
////        RecipeBuilder.get(ALLOY_SMELTER).ii(Copper.getIngot(1), Cobalt.getDust(4)).io(RedAlloy.getIngot(16)).build(10, 1);
////
////        RecipeBuilder.get(PRIMITIVE_BLAST_FURNACE).ii(Coal.getGem(4), Iron.getIngot(1)).io(Steel.getIngot(1)).build(7200, 0);
////        RecipeBuilder.get(BRONZE_BLAST_FURNACE).ii(Coal.getGem(4), Iron.getIngot(1)).io(Steel.getIngot(1)).build(7200, 0);
////
////        RecipeBuilder.get(BLAST_FURNACE).ii(Silicon.getDust(1)).io(Silicon.getIngot(1)).build(10, 1);
////        RecipeBuilder.get(BLAST_FURNACE).ii(Nickel.getIngot(4), Chrome.getIngot(1)).io(Nichrome.getIngotH(5), DarkAsh.getDustS(2)).build(10, 1);
////        RecipeBuilder.get(BLAST_FURNACE).ii(Aluminium.getIngot(1), Aluminium.getIngot(1), Aluminium.getIngot(1)).io(Aluminium.getIngot(1), Aluminium.getIngot(1), Aluminium.getIngot(1)).build(10, 1);
////
////        RecipeBuilder.get(BLAST_FURNACE).ii(WroughtIron.getIngot(1)).fi(Oxygen.getGasStack(1)).io(Steel.getIngot(1), DarkAsh.getDustS(1)).build(10, 1);
////        RecipeBuilder.get(BLAST_FURNACE).ii(Iron.getIngot(1)).fi(Oxygen.getGasStack(1), Hydrogen.getGasStack(1)).io(Steel.getIngot(1), DarkAsh.getDustS(1)).build(10, 1);
////        RecipeBuilder.get(BLAST_FURNACE).ii(Aluminium.getIngot(1)).fi(Oxygen.getGasStack(1), Hydrogen.getGasStack(1), Deuterium.getGasStack(1)).io(Steel.getIngot(1), DarkAsh.getDustS(1)).build(10, 1);
////
////        RecipeBuilder.get(BLAST_FURNACE).ii(WroughtIron.getIngot(1)).fi(Oxygen.getGasStack(1)).io(Steel.getIngot(1), DarkAsh.getDustS(1)).fo(Oxygen.getGasStack(1)).build(10, 1);
////        RecipeBuilder.get(BLAST_FURNACE).ii(Iron.getIngot(1)).fi(Oxygen.getGasStack(1), Hydrogen.getGasStack(1)).io(Steel.getIngot(1), DarkAsh.getDustS(1)).fo(Oxygen.getGasStack(1), Hydrogen.getGasStack(1)).build(10, 1);
////        RecipeBuilder.get(BLAST_FURNACE).ii(Aluminium.getIngot(1)).fi(Oxygen.getGasStack(1), Hydrogen.getGasStack(1), Deuterium.getGasStack(1)).io(Steel.getIngot(1), DarkAsh.getDustS(1)).fo(Oxygen.getGasStack(1), Hydrogen.getGasStack(1), Deuterium.getGasStack(1)).build(10, 1);
////    }
//
//    public void init() {
//
//        GT_Values.RA.addFormingPressRecipe(ItemList.Empty_Board_Basic.get(1, new Object[0]), ItemList.Circuit_Parts_Wiring_Basic.get(4L, new Object[0]), ItemList.Circuit_Board_Basic.get(1L, new Object[0]), 32, 16);
//        GT_Values.RA.addFormingPressRecipe(ItemList.Empty_Board_Basic.get(1, new Object[0]), ItemList.Circuit_Parts_Wiring_Advanced.get(4L, new Object[0]), ItemList.Circuit_Board_Advanced.get(1L, new Object[0]), 32, 64);
//        GT_Values.RA.addFormingPressRecipe(ItemList.Empty_Board_Elite.get(1, new Object[0]), ItemList.Circuit_Parts_Wiring_Elite.get(4L, new Object[0]), ItemList.Circuit_Board_Elite.get(1L, new Object[0]), 32, 256);
//        GT_Values.RA.addFormingPressRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Lapis, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glowstone, 1L), ItemList.Circuit_Parts_Advanced.get(2L, new Object[0]), 32, 64);
//        GT_Values.RA.addFormingPressRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Lazurite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glowstone, 1L), ItemList.Circuit_Parts_Advanced.get(2L, new Object[0]), 32, 64);
//        GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Graphene, 1L), GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.Graphene, 1L), 400, 2);
//
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.ring, Materials.Iron, 2L), ItemList.Component_Minecart_Wheels_Iron.get(1L, new Object[0]), 500, 2);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.WroughtIron, 1L), GT_OreDictUnificator.get(OrePrefixes.ring, Materials.WroughtIron, 2L), ItemList.Component_Minecart_Wheels_Iron.get(1L, new Object[0]), 400, 2);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.ring, Materials.Steel, 2L), ItemList.Component_Minecart_Wheels_Steel.get(1L, new Object[0]), 300, 2);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.gear, Materials.CobaltBrass, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Diamond, 1L), ItemList.Component_Sawblade_Diamond.get(1L, new Object[0]), 1600, 2);
//
//
//
//        GT_Values.RA.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 2L), 0, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 1L), ItemList.TE_Slag.get(1L, new Object[]{GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 1L)}), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, 250);
//        GT_Values.RA.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uranium, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Uranium235, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Plutonium, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{2000, 200}, 800, 320);
//        GT_Values.RA.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Plutonium, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Plutonium241, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Uranium, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{2000, 3000}, 1600, 320);
//        GT_Values.RA.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Naquadah, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.NaquadahEnriched, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Naquadria, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{5000, 1000}, 3200, 320);
//        GT_Values.RA.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.NaquadahEnriched, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Naquadria, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Naquadah, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{2000, 3000}, 6400, 640);
//        GT_Values.RA.addCentrifugeRecipe(GT_Values.NI, GT_Values.NI, Materials.Hydrogen.getGas(160L), Materials.Deuterium.getGas(40L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 160, 20);
//        GT_Values.RA.addCentrifugeRecipe(GT_Values.NI, GT_Values.NI, Materials.Deuterium.getGas(160L), Materials.Tritium.getGas(40L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 160, 80);
//        GT_Values.RA.addCentrifugeRecipe(GT_Values.NI, GT_Values.NI, Materials.Helium.getGas(80L), Materials.Helium_3.getGas(5L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 160, 80);
//        GT_Values.RA.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glowstone, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Redstone, 2L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Gold, 2L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 488, 80);
//        GT_Values.RA.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Endstone, 1L), GT_Values.NI, GT_Values.NF, Materials.Helium.getGas(120L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Tungstate, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Platinum, 1L), new ItemStack(Blocks.sand, 1), GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{1250, 625, 9000, 0, 0, 0}, 320, 20);
//        GT_Values.RA.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Netherrack, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Redstone, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Sulfur, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Coal, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Gold, 1L), GT_Values.NI, GT_Values.NI, new int[]{5625, 9900, 5625, 625, 0, 0}, 160, 20);
//        GT_Values.RA.addCentrifugeRecipe(GT_Values.NI, GT_Values.NI, Materials.Lava.getFluid(100L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Copper, 1L), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Tin, 1L), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Gold, 1L), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Silver, 1L), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Tantalum, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Tungstate, 1L), new int[]{2000, 1000, 250, 250, 250, 250}, 80, 80);
//        GT_Values.RA.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.RareEarth, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Neodymium, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Yttrium, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Lanthanum, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Cerium, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Cadmium, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Caesium, 1L), new int[]{2500, 2500, 2500, 2500, 2500, 2500}, 64, 20);
//
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 4L), null, Materials.Creosote.getFluid(1000), null, ItemList.SFMixture.get(8, new Object[]{}), 1600, 16);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lithium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 4L), null, Materials.Creosote.getFluid(1000), null, ItemList.SFMixture.get(8, new Object[]{}), 1600, 16);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Caesium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 4L), null, Materials.Creosote.getFluid(1000), null, ItemList.SFMixture.get(12, new Object[]{}), 1600, 16);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 4L), null, Materials.Lubricant.getFluid(300), null, ItemList.SFMixture.get(8, new Object[]{}), 1200, 16);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lithium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 4L), null, Materials.Lubricant.getFluid(300), null, ItemList.SFMixture.get(8, new Object[]{}), 1200, 16);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Caesium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 4L), null, Materials.Lubricant.getFluid(300), null, ItemList.SFMixture.get(12, new Object[]{}), 1200, 16);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 4L), null, Materials.Glue.getFluid(333), null, ItemList.SFMixture.get(8, new Object[]{}), 800, 16);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lithium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 4L), null, Materials.Glue.getFluid(333), null, ItemList.SFMixture.get(8, new Object[]{}), 800, 16);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Caesium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 4L), null, Materials.Glue.getFluid(333), null, ItemList.SFMixture.get(12, new Object[]{}), 800, 16);
//
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), GT_ModHandler.getIC2Item("machine", 1L), 25, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.WroughtIron, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_ULV.get(1L, new Object[0]), 25, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_LV.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_MV.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.StainlessSteel, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_HV.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Titanium, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_EV.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.TungstenSteel, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_IV.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Chrome, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_LuV.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iridium, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_ZPM.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Osmium, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_UV.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Neutronium, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_MAX.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Invar, 6L), GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Invar, 1L), ItemList.Casing_HeatProof.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.Cupronickel, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_Coil_Cupronickel.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.Kanthal, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_Coil_Kanthal.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.Nichrome, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_Coil_Nichrome.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.TungstenSteel, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_Coil_TungstenSteel.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.HSSG, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_Coil_HSSG.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.Naquadah, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_Coil_Naquadah.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.NaquadahAlloy, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_Coil_NaquadahAlloy.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.Superconductor, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), ItemList.Casing_Coil_Superconductor.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 6L), GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Steel, 1L), ItemList.Casing_SolidSteel.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 6L), GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Aluminium, 1L), ItemList.Casing_FrostProof.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.TungstenSteel, 6L), GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.TungstenSteel, 1L), ItemList.Casing_RobustTungstenSteel.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.StainlessSteel, 6L), GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.StainlessSteel, 1L), ItemList.Casing_CleanStainlessSteel.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Titanium, 6L), GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Titanium, 1L), ItemList.Casing_StableTitanium.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.TungstenSteel, 6L), ItemList.Casing_LuV.get(1L, new Object[0]), ItemList.Casing_Fusion.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Magnalium, 6L), GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.BlueSteel, 1L), ItemList.Casing_Turbine.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.StainlessSteel, 6L), ItemList.Casing_Turbine.get(1L, new Object[0]), ItemList.Casing_Turbine1.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Titanium, 6L), ItemList.Casing_Turbine.get(1L, new Object[0]), ItemList.Casing_Turbine2.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.TungstenSteel, 6L), ItemList.Casing_Turbine.get(1L, new Object[0]), ItemList.Casing_Turbine3.get(1L, new Object[0]), 50, 16);
//
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Lead, 2L), ItemList.Casing_ULV.get(1L, new Object[0]), Materials.Plastic.getMolten(288), ItemList.Hull_ULV.get(1L, new Object[0]), 25, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Tin, 2L), ItemList.Casing_LV.get(1L, new Object[0]), Materials.Plastic.getMolten(288), ItemList.Hull_LV.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Copper, 2L), ItemList.Casing_MV.get(1L, new Object[0]), Materials.Plastic.getMolten(288), ItemList.Hull_MV.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.AnnealedCopper, 2L), ItemList.Casing_MV.get(1L, new Object[0]), Materials.Plastic.getMolten(288), ItemList.Hull_MV.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Gold, 2L), ItemList.Casing_HV.get(1L, new Object[0]), Materials.Plastic.getMolten(288), ItemList.Hull_HV.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Aluminium, 2L), ItemList.Casing_EV.get(1L, new Object[0]), Materials.Plastic.getMolten(288), ItemList.Hull_EV.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Tungsten, 2L), ItemList.Casing_IV.get(1L, new Object[0]), Materials.Plastic.getMolten(288), ItemList.Hull_IV.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.VanadiumGallium, 2L), ItemList.Casing_LuV.get(1L, new Object[0]), Materials.Plastic.getMolten(288), ItemList.Hull_LuV.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Naquadah, 2L), ItemList.Casing_ZPM.get(1L, new Object[0]), Materials.Polytetrafluoroethylene.getMolten(288), ItemList.Hull_ZPM.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.NaquadahAlloy, 2L), ItemList.Casing_UV.get(1L, new Object[0]), Materials.Polytetrafluoroethylene.getMolten(288), ItemList.Hull_UV.get(1L, new Object[0]), 50, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.Superconductor, 2L), ItemList.Casing_MAX.get(1L, new Object[0]), Materials.Polytetrafluoroethylene.getMolten(288), ItemList.Hull_MAX.get(1L, new Object[0]), 50, 16);
//
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Tin, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.BatteryAlloy, 1L), Materials.Plastic.getMolten(144), ItemList.Battery_Hull_LV.get(1L, new Object[0]), 800, 1);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Copper, 2L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.BatteryAlloy, 3L), Materials.Plastic.getMolten(432), ItemList.Battery_Hull_MV.get(1L, new Object[0]), 1600, 2);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.AnnealedCopper, 2L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.BatteryAlloy, 3L), Materials.Plastic.getMolten(432), ItemList.Battery_Hull_MV.get(1L, new Object[0]), 1600, 2);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Gold, 4L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.BatteryAlloy, 9L), Materials.Plastic.getMolten(1296), ItemList.Battery_Hull_HV.get(1L, new Object[0]), 3200, 4);
//
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 3L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Silicon, 1L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.ElectricalSteel, 4L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh, 2L), (int) Math.max(Materials.ElectricalSteel.getMass() / 40L, 1L) * Materials.ElectricalSteel.mBlastFurnaceTemp, 120, Materials.ElectricalSteel.mBlastFurnaceTemp);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Gold, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glowstone, 1L), Materials.Redstone.getMolten(144), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.EnergeticAlloy, 1L), null, Materials.EnergeticAlloy.mBlastFurnaceTemp / 10, 120, Materials.EnergeticAlloy.mBlastFurnaceTemp);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.EnergeticAlloy, 1L), GT_OreDictUnificator.get(OrePrefixes.gem, Materials.EnderPearl, 1L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.PhasedGold, 1L), null, Materials.VibrantAlloy.mBlastFurnaceTemp / 10, 480, Materials.VibrantAlloy.mBlastFurnaceTemp);
//        GT_Values.RA.addAlloySmelterRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Silicon, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.RedstoneAlloy, 1L), 400, 24);
//        GT_Values.RA.addAlloySmelterRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.ConductiveIron, 1L), 400, 24);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.gem, Materials.EnderPearl, 1L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.PhasedIron, 1L), null, Materials.PulsatingIron.mBlastFurnaceTemp / 10, 120, Materials.PulsatingIron.mBlastFurnaceTemp);
//        GT_Values.RA.addAlloySmelterRecipe(new ItemStack(Blocks.soul_sand), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Gold, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Soularium, 1L), 400, 24);
//
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tungsten, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.TungstenSteel, 2L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh, 1L), (int) Math.max(Materials.TungstenSteel.getMass() / 80L, 1L) * Materials.TungstenSteel.mBlastFurnaceTemp, 480, Materials.TungstenSteel.mBlastFurnaceTemp);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tungsten, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 1L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.TungstenCarbide, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Ash, 2L), (int) Math.max(Materials.TungstenCarbide.getMass() / 40L, 1L) * Materials.TungstenCarbide.mBlastFurnaceTemp, 480, Materials.TungstenCarbide.mBlastFurnaceTemp);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Vanadium, 3L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Gallium, 1L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.VanadiumGallium, 4L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh, 2L), (int) Math.max(Materials.VanadiumGallium.getMass() / 40L, 1L) * Materials.VanadiumGallium.mBlastFurnaceTemp, 480, Materials.VanadiumGallium.mBlastFurnaceTemp);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Niobium, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Titanium, 1L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.NiobiumTitanium, 2L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh, 1L), (int) Math.max(Materials.NiobiumTitanium.getMass() / 80L, 1L) * Materials.NiobiumTitanium.mBlastFurnaceTemp, 480, Materials.NiobiumTitanium.mBlastFurnaceTemp);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Nickel, 4L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Chrome, 1L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.Nichrome, 5L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh, 2L), (int) Math.max(Materials.Nichrome.getMass() / 32L, 1L) * Materials.Nichrome.mBlastFurnaceTemp, 480, Materials.Nichrome.mBlastFurnaceTemp);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ruby, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Aluminium, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 1L), 400, 100, 1200);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Ruby, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Aluminium, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 1L), 320, 100, 1200);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.GreenSapphire, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Aluminium, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 1L), 400, 100, 1200);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GreenSapphire, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Aluminium, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 1L), 320, 100, 1200);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.BlueSapphire, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Aluminium, 3L), GT_Values.NI, 400, 100, 1200);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.BlueSapphire, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Aluminium, 3L), GT_Values.NI, 320, 100, 1200);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ilmenite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 1L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.WroughtIron, 4L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Rutile, 4L), 800, 500, 1700);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Ilmenite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 1L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.WroughtIron, 4L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Rutile, 4L), 800, 500, 1700);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Magnesium, 2L), GT_Values.NI, Materials.Titaniumtetrachloride.getFluid(1000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.Titanium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Magnesiumchloride, 2L), 800, 480, Materials.Titanium.mBlastFurnaceTemp + 200);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Galena, 1L), GT_Values.NI, Materials.Oxygen.getGas(2000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Silver, 4L), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Lead, 4L), 400, 500, 1500);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Galena, 1L), GT_Values.NI, Materials.Oxygen.getGas(2000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Silver, 5L), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Lead, 5L), 320, 500, 1500);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Magnetite, 1L), GT_Values.NI, Materials.Oxygen.getGas(2000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.WroughtIron, 4L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh, 1L), 400, 500, 1000);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Magnetite, 1L), GT_Values.NI, Materials.Oxygen.getGas(2000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.WroughtIron, 5L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Ash, 1L), 320, 500, 1000);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L), GT_Values.NI, Materials.Oxygen.getGas(1000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh, 1L), 500, 120, 1000);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.PigIron, 1L), GT_Values.NI, Materials.Oxygen.getGas(1000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh, 1L), 100, 120, 1000);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.WroughtIron, 1L), GT_Values.NI, Materials.Oxygen.getGas(1000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh, 1L), 100, 120, 1000);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.ShadowIron, 1L), GT_Values.NI, Materials.Oxygen.getGas(1000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.ShadowSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh, 1L), 500, 120, 1100);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.MeteoricIron, 1L), GT_Values.NI, Materials.Oxygen.getGas(1000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.MeteoricSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh, 1L), 500, 120, 1200);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Copper, 1L), GT_Values.NI, Materials.Oxygen.getGas(1000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.AnnealedCopper, 1L), GT_Values.NI, 500, 120, 1200);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 1L), GT_Values.NI, Materials.Oxygen.getGas(1000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.AnnealedCopper, 1L), GT_Values.NI, 500, 120, 1200);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.ElectricalSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Obsidian, 1L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.DarkSteel, 1L), GT_Values.NI, 4000, 120, 2000);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iridium, 3L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Osmium, 1L), Materials.Helium.getGas(1000), null, GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.Osmiridium, 4L), null, 500, 1920, 2900);
//        GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Naquadah, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Osmiridium, 1L), Materials.Argon.getGas(1000), null, GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.NaquadahAlloy, 2L), null, 500, 30720, Materials.NaquadahAlloy.mBlastFurnaceTemp);
//
//        for (OrePrefixes tPrefix : Arrays.asList(new OrePrefixes[]{OrePrefixes.dust, OrePrefixes.dustSmall, OrePrefixes.dustTiny})) {
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.EnderPearl, 1L), GT_OreDictUnificator.get(tPrefix, Materials.Blaze, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.EnderEye, 1L * tPrefix.mMaterialAmount), (int) (100L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.Gold, 1L), GT_OreDictUnificator.get(tPrefix, Materials.Silver, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.Electrum, 2L * tPrefix.mMaterialAmount), (int) (200L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.Iron, 2L), GT_OreDictUnificator.get(tPrefix, Materials.Nickel, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.Invar, 3L * tPrefix.mMaterialAmount), (int) (300L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.Iron, 4L), GT_OreDictUnificator.get(tPrefix, Materials.Invar, 3L), GT_OreDictUnificator.get(tPrefix, Materials.Manganese, 1L), GT_OreDictUnificator.get(tPrefix, Materials.Chrome, 1L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.StainlessSteel, 9L * tPrefix.mMaterialAmount), (int) (900L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.Iron, 1L), GT_OreDictUnificator.get(tPrefix, Materials.Aluminium, 1L), GT_OreDictUnificator.get(tPrefix, Materials.Chrome, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.Kanthal, 3L * tPrefix.mMaterialAmount), (int) (300L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.Copper, 3L), GT_OreDictUnificator.get(tPrefix, Materials.Barium, 2L), GT_OreDictUnificator.get(tPrefix, Materials.Yttrium, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.YttriumBariumCuprate, 6L * tPrefix.mMaterialAmount), (int) (600L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.Copper, 3L), GT_OreDictUnificator.get(tPrefix, Materials.Zinc, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.Brass, 4L * tPrefix.mMaterialAmount), (int) (400L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.Copper, 3L), GT_OreDictUnificator.get(tPrefix, Materials.Tin, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.Bronze, 4L * tPrefix.mMaterialAmount), (int) (400L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.Copper, 1L), GT_OreDictUnificator.get(tPrefix, Materials.Nickel, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.Cupronickel, 2L * tPrefix.mMaterialAmount), (int) (200L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.Copper, 1L), GT_OreDictUnificator.get(tPrefix, Materials.Gold, 4L), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.RoseGold, 5L * tPrefix.mMaterialAmount), (int) (500L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.Copper, 1L), GT_OreDictUnificator.get(tPrefix, Materials.Silver, 4L), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.SterlingSilver, 5L * tPrefix.mMaterialAmount), (int) (500L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.Copper, 3L), GT_OreDictUnificator.get(tPrefix, Materials.Electrum, 2L), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.BlackBronze, 5L * tPrefix.mMaterialAmount), (int) (500L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.Bismuth, 1L), GT_OreDictUnificator.get(tPrefix, Materials.Brass, 4L), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.BismuthBronze, 5L * tPrefix.mMaterialAmount), (int) (500L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.BlackBronze, 1L), GT_OreDictUnificator.get(tPrefix, Materials.Nickel, 1L), GT_OreDictUnificator.get(tPrefix, Materials.Steel, 3L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.BlackSteel, 5L * tPrefix.mMaterialAmount), (int) (500L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.SterlingSilver, 1L), GT_OreDictUnificator.get(tPrefix, Materials.BismuthBronze, 1L), GT_OreDictUnificator.get(tPrefix, Materials.BlackSteel, 4L), GT_OreDictUnificator.get(tPrefix, Materials.Steel, 2L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.RedSteel, 8L * tPrefix.mMaterialAmount), (int) (800L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.RoseGold, 1L), GT_OreDictUnificator.get(tPrefix, Materials.Brass, 1L), GT_OreDictUnificator.get(tPrefix, Materials.BlackSteel, 4L), GT_OreDictUnificator.get(tPrefix, Materials.Steel, 2L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.BlueSteel, 8L * tPrefix.mMaterialAmount), (int) (800L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.Cobalt, 5L), GT_OreDictUnificator.get(tPrefix, Materials.Chrome, 2L), GT_OreDictUnificator.get(tPrefix, Materials.Nickel, 1L), GT_OreDictUnificator.get(tPrefix, Materials.Molybdenum, 1L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.Ultimet, 9L * tPrefix.mMaterialAmount), (int) (900L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.Brass, 7L), GT_OreDictUnificator.get(tPrefix, Materials.Aluminium, 1L), GT_OreDictUnificator.get(tPrefix, Materials.Cobalt, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.CobaltBrass, 9L * tPrefix.mMaterialAmount), (int) (900L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.Saltpeter, 2L), GT_OreDictUnificator.get(tPrefix, Materials.Sulfur, 1L), GT_OreDictUnificator.get(tPrefix, Materials.Coal, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.Gunpowder, 4L * tPrefix.mMaterialAmount), (int) (400L * tPrefix.mMaterialAmount / 3628800L), 8);
//            GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(tPrefix, Materials.Saltpeter, 2L), GT_OreDictUnificator.get(tPrefix, Materials.Sulfur, 1L), GT_OreDictUnificator.get(tPrefix, Materials.Charcoal, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.getDust(Materials.Gunpowder, 3L * tPrefix.mMaterialAmount), (int) (300L * tPrefix.mMaterialAmount / 3628800L), 8);
//        }
//
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.NetherQuartz, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 1L), Materials.Water.getFluid(1000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.NetherQuartz, 3L), 500);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.CertusQuartz, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 1L), Materials.Water.getFluid(1000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.CertusQuartz, 3L), 500);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Quartzite, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 1L), Materials.Water.getFluid(1000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Quartzite, 3L), 500);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.NetherQuartz, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 1L), GT_ModHandler.getDistilledWater(1000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.NetherQuartz, 3L), 500);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.CertusQuartz, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 1L), GT_ModHandler.getDistilledWater(1000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.CertusQuartz, 3L), 500);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Quartzite, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 1L), GT_ModHandler.getDistilledWater(1000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Quartzite, 3L), 500);
//
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uraninite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Aluminium, 1L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uranium, 1L), 1000);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uraninite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Magnesium, 1L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uranium, 1L), 1000);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 1L), Materials.Oxygen.getGas(3000L), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 5L), 500);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 1L), GT_Values.NI, Materials.Hydrogen.getGas(4000L), Materials.Methane.getGas(5000L), GT_Values.NI, 3500);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), Materials.Water.getFluid(2000L), Materials.SulfuricAcid.getFluid(3000L), GT_Values.NI, 1150);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 1L), Materials.Oxygen.getGas(4000L), Materials.SodiumPersulfate.getFluid(6000L), GT_Values.NI, 8000);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Nitrogen, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 1L), Materials.Water.getFluid(2000L), Materials.Glyceryl.getFluid(4000L), ItemList.Cell_Empty.get(1L, new Object[0]), 2700);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Fuel, 1L), GT_Values.NI, Materials.Glyceryl.getFluid(250L), Materials.NitroFuel.getFluid(1000L), ItemList.Cell_Empty.get(1L, new Object[0]), 250);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Glyceryl, 1L), GT_Values.NI, Materials.Fuel.getFluid(4000L), Materials.NitroFuel.getFluid(4000L), ItemList.Cell_Empty.get(1L, new Object[0]), 1000);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.LightFuel, 1L), GT_Values.NI, Materials.Glyceryl.getFluid(250L), Materials.NitroFuel.getFluid(1250L), ItemList.Cell_Empty.get(1L, new Object[0]), 250);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Glyceryl, 1L), GT_Values.NI, Materials.LightFuel.getFluid(4000L), Materials.NitroFuel.getFluid(5000L), ItemList.Cell_Empty.get(1L, new Object[0]), 1000);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Nitrogen, 1L), GT_Values.NI, Materials.Oxygen.getGas(2000L), Materials.NitrogenDioxide.getGas(3000L), ItemList.Cell_Empty.get(1L, new Object[0]), 1250);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oxygen, 2L), GT_Values.NI, Materials.Nitrogen.getGas(1000L), Materials.NitrogenDioxide.getGas(3000L), ItemList.Cell_Empty.get(2L, new Object[0]), 1250);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oxygen, 1L), GT_Values.NI, Materials.Hydrogen.getGas(2000L), GT_ModHandler.getDistilledWater(3000L), ItemList.Cell_Empty.get(1L, new Object[0]), 10);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Hydrogen, 1L), GT_Values.NI, Materials.Oxygen.getGas(500L), GT_ModHandler.getDistilledWater(1500L), ItemList.Cell_Empty.get(1L, new Object[0]), 5);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Rutile, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 3L), Materials.Chlorine.getFluid(2000L), Materials.Titaniumtetrachloride.getFluid(1000L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 1L), 500, 480);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Magnesiumchloride, 2L), GT_Values.NF, Materials.Chlorine.getFluid(1500L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Magnesium, 6L), 300, 240);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.RawRubber, 9L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), GT_Values.NF, Materials.Rubber.getMolten(1296L), GT_Values.NI, 600, 16);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.NitrogenDioxide, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Hydrogen, 3L), Materials.Air.getGas(500), new FluidStack(ItemList.sRocketFuel, 3000), ItemList.Cell_Water.get(4, new Object[0]), 1000, 388);
//
//        GT_Values.RA.addAutoclaveRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.NetherStar, 1), Materials.UUMatter.getFluid(576), GT_OreDictUnificator.get(OrePrefixes.gem, Materials.NetherStar, 1), 3333, 72000, 480);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.EnderPearl, 1), GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Basic, 4), Materials.Osmium.getMolten(288), ItemList.Field_Generator_LV.get(1, new Object[0]), 1800, 30);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.EnderEye, 1), GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Good, 4), Materials.Osmium.getMolten(576), ItemList.Field_Generator_MV.get(1, new Object[0]), 1800, 120);
//        GT_Values.RA.addAssemblerRecipe(ItemList.QuantumEye.get(1, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Advanced, 4), Materials.Osmium.getMolten(1152), ItemList.Field_Generator_HV.get(1, new Object[0]), 1800, 480);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.NetherStar, 1), GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Elite, 4), Materials.Osmium.getMolten(2304), ItemList.Field_Generator_EV.get(1, new Object[0]), 1800, 1920);
//        GT_Values.RA.addAssemblerRecipe(ItemList.QuantumStar.get(1, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Master, 4), Materials.Osmium.getMolten(4608), ItemList.Field_Generator_IV.get(1, new Object[0]), 1800, 7680);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Steel, 64), GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Zinc, 16), null, ItemList.Component_Filter.get(1, new Object[0]), 1600, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Graphite, 8), GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Silicon, 1), Materials.Glue.getFluid(250L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Graphene, 1), 480, 240);
//
//        GT_Values.RA.addCentrifugeRecipe(ItemList.Cell_Empty.get(1, new Object[0]), null, Materials.Air.getGas(10000), Materials.Nitrogen.getGas(3900), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oxygen, 1), null, null, null, null, null, null, 1600, 8);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.NitrogenDioxide, 4), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oxygen, 1), Materials.Water.getFluid(2000), new FluidStack(ItemList.sNitricAcid, 4000), ItemList.Cell_Empty.get(5, new Object[0]), 950, 30);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.crushedPurified, Materials.Pentlandite, 1), null, new FluidStack(ItemList.sNitricAcid, 8000), new FluidStack(ItemList.sNickelSulfate, 9000), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.PlatinumGroupSludge, 1), 50, 30);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.crushedPurified, Materials.Chalcopyrite, 1), null, new FluidStack(ItemList.sNitricAcid, 8000), new FluidStack(ItemList.sBlueVitriol, 9000), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.PlatinumGroupSludge, 1), 50, 30);
//        GT_Values.RA.addElectrolyzerRecipe(ItemList.Cell_Empty.get(1, new Object[0]), null, new FluidStack(ItemList.sBlueVitriol, 9000), Materials.SulfuricAcid.getFluid(8000), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Copper, 1), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oxygen, 1), null, null, null, null, null, 900, 30);
//        GT_Values.RA.addElectrolyzerRecipe(ItemList.Cell_Empty.get(1, new Object[0]), null, new FluidStack(ItemList.sNickelSulfate, 9000), Materials.SulfuricAcid.getFluid(8000), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel, 1), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oxygen, 1), null, null, null, null, null, 900, 30);
//        GT_Values.RA.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.PlatinumGroupSludge, 1), null, null, null, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.SiliconDioxide, 1), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Gold, 1), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Platinum, 1), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Palladium, 1), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Iridium, 1), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Osmium, 1), new int[]{10000, 10000, 10000, 8000, 6000, 6000}, 900, 30);
//
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Silicon, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Plastic, 1L), ItemList.Empty_Board_Basic.get(1, new Object[0]), 32, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Silicon, 2L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Polytetrafluoroethylene, 1L), ItemList.Empty_Board_Elite.get(1, new Object[0]), 32, 256);
//
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Plutonium, 6), null, null, Materials.Radon.getGas(100), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Plutonium, 6), 12000, 8);
//        GT_Values.RA.addChemicalBathRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.EnderEye, 1), Materials.Radon.getGas(250), ItemList.QuantumEye.get(1L, new Object[0]), null, null, null, 480, 384);
//        GT_Values.RA.addChemicalBathRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.NetherStar, 1), Materials.Radon.getGas(1250), ItemList.QuantumStar.get(1L, new Object[0]), null, null, null, 1920, 384);
//        GT_Values.RA.addAutoclaveRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.NetherStar, 1), Materials.Neutronium.getMolten(288), ItemList.Gravistar.get(1L, new Object[0]), 10000, 480, 7680);
//        GT_Values.RA.addVacuumFreezerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Water, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Ice, 1L), 50);
//    }
//
//    private final MaterialStack[][] mAlloySmelterList = {{new MaterialStack(Materials.Tetrahedrite, 3L), new MaterialStack(Materials.Tin, 1L), new MaterialStack(Materials.Bronze, 3L)}, {new MaterialStack(Materials.Tetrahedrite, 3L), new MaterialStack(Materials.Zinc, 1L), new MaterialStack(Materials.Brass, 3L)}, {new MaterialStack(Materials.Copper, 3L), new MaterialStack(Materials.Tin, 1L), new MaterialStack(Materials.Bronze, 4L)}, {new MaterialStack(Materials.Copper, 3L), new MaterialStack(Materials.Zinc, 1L), new MaterialStack(Materials.Brass, 4L)}, {new MaterialStack(Materials.Copper, 1L), new MaterialStack(Materials.Nickel, 1L), new MaterialStack(Materials.Cupronickel, 2L)}, {new MaterialStack(Materials.Copper, 1L), new MaterialStack(Materials.Redstone, 4L), new MaterialStack(Materials.RedAlloy, 1L)}, {new MaterialStack(Materials.AnnealedCopper, 3L), new MaterialStack(Materials.Tin, 1L), new MaterialStack(Materials.Bronze, 4L)}, {new MaterialStack(Materials.AnnealedCopper, 3L), new MaterialStack(Materials.Zinc, 1L), new MaterialStack(Materials.Brass, 4L)}, {new MaterialStack(Materials.AnnealedCopper, 1L), new MaterialStack(Materials.Nickel, 1L), new MaterialStack(Materials.Cupronickel, 2L)}, {new MaterialStack(Materials.AnnealedCopper, 1L), new MaterialStack(Materials.Redstone, 4L), new MaterialStack(Materials.RedAlloy, 1L)}, {new MaterialStack(Materials.Iron, 1L), new MaterialStack(Materials.Tin, 1L), new MaterialStack(Materials.TinAlloy, 2L)}, {new MaterialStack(Materials.WroughtIron, 1L), new MaterialStack(Materials.Tin, 1L), new MaterialStack(Materials.TinAlloy, 2L)}, {new MaterialStack(Materials.Iron, 2L), new MaterialStack(Materials.Nickel, 1L), new MaterialStack(Materials.Invar, 3L)}, {new MaterialStack(Materials.WroughtIron, 2L), new MaterialStack(Materials.Nickel, 1L), new MaterialStack(Materials.Invar, 3L)}, {new MaterialStack(Materials.Tin, 9L), new MaterialStack(Materials.Antimony, 1L), new MaterialStack(Materials.SolderingAlloy, 10L)}, {new MaterialStack(Materials.Lead, 4L), new MaterialStack(Materials.Antimony, 1L), new MaterialStack(Materials.BatteryAlloy, 5L)}, {new MaterialStack(Materials.Gold, 1L), new MaterialStack(Materials.Silver, 1L), new MaterialStack(Materials.Electrum, 2L)}, {new MaterialStack(Materials.Magnesium, 1L), new MaterialStack(Materials.Aluminium, 2L), new MaterialStack(Materials.Magnalium, 3L)}, {new MaterialStack(Materials.Silver, 1L), new MaterialStack(Materials.Nikolite, 4L), new MaterialStack(Materials.BlueAlloy, 1L)}};
//
//    public void initOld() {
//        GT_Log.out.println("GT_Mod: Adding non-OreDict Machine Recipes.");
//
//
//        try {
//            GT_DummyWorld tWorld = (GT_DummyWorld) GT_Values.DW;
//            while (tWorld.rand.mIterationStep > 0) {
//                GT_Values.RA.addFluidExtractionRecipe(GT_Utility.copyAmount(1L, new Object[]{ForgeHooks.getGrassSeed(tWorld)}), GT_Values.NI, Materials.SeedOil.getFluid(5L), 10000, 64, 2);
//            }
//        } catch (Throwable e) {
//            GT_Log.out.println("GT_Mod: failed to iterate somehow, maybe it's your Forge Version causing it. But it's not that important\n");
//            e.printStackTrace(GT_Log.err);
//        }
//
////        GT_Values.RA.addArcFurnaceRecipe(ItemList.Block_BronzePlate.get(1, new Object[]{}), new ItemStack[]{ GT_OreDictUnificator.get(OrePrefixes.ingot,Materials.Bronze,4), GT_OreDictUnificator.get(OrePrefixes.dust,Materials.Stone,1)}, null, 160, 96);
////        GT_Values.RA.addArcFurnaceRecipe(ItemList.Block_IridiumTungstensteel.get(1, new Object[]{}), new ItemStack[]{ GT_OreDictUnificator.get(OrePrefixes.ingot,Materials.Bronze,4), GT_OreDictUnificator.get(OrePrefixes.dust,Materials.Stone,1)}, null, 160, 96);
//        GT_Values.RA.addArcFurnaceRecipe(ItemList.Block_TungstenSteelReinforced.get(1, new Object[]{}), new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.TungstenSteel, 2), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Concrete, 1)}, null, 160, 96);
//
//        //Temporary until circuit overhaul
//        GT_Values.RA.addAlloySmelterRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Rubber, 2), GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.Copper, 1), GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Copper, 1), 100, 16);
//
//        GT_Values.RA.addPrinterRecipe(GT_OreDictUnificator.get(OrePrefixes.plateDouble, Materials.Paper, 1L), FluidRegistry.getFluidStack("squidink", 36), GT_Values.NI, ItemList.Paper_Punch_Card_Empty.get(1L, new Object[0]), 100, 2);
//        GT_Values.RA.addPrinterRecipe(ItemList.Paper_Punch_Card_Empty.get(1L, new Object[0]), FluidRegistry.getFluidStack("squidink", 36), ItemList.Tool_DataStick.getWithName(0L, "With Punch Card Data", new Object[0]), ItemList.Paper_Punch_Card_Encoded.get(1L, new Object[0]), 100, 2);
//        GT_Values.RA.addPrinterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Paper, 3L), FluidRegistry.getFluidStack("squidink", 144), ItemList.Tool_DataStick.getWithName(0L, "With Scanned Book Data", new Object[0]), ItemList.Paper_Printed_Pages.get(1L, new Object[0]), 400, 2);
//        GT_Values.RA.addPrinterRecipe(new ItemStack(Items.map, 1, 32767), FluidRegistry.getFluidStack("squidink", 144), ItemList.Tool_DataStick.getWithName(0L, "With Scanned Map Data", new Object[0]), new ItemStack(Items.filled_map, 1, 0), 400, 2);
//        GT_Values.RA.addPrinterRecipe(new ItemStack(Items.book, 1, 32767), FluidRegistry.getFluidStack("squidink", 144), GT_Values.NI, GT_Utility.getWrittenBook("Manual_Printer", ItemList.Book_Written_01.get(1L, new Object[0])), 400, 2);
//
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Clay, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Stone, 3L), GT_Values.NI, GT_Values.NI, Materials.Water.getFluid(500L), Materials.Concrete.getMolten(576L), GT_Values.NI, 20, 16);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Redstone, 5L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Ruby, 4L), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, ItemList.IC2_Energium_Dust.get(1L, new Object[0]), 100, 8);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 5L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ruby, 4L), GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, ItemList.IC2_Energium_Dust.get(9L, new Object[0]), 900, 8);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sugar, 1L), new ItemStack(Blocks.brown_mushroom, 1), new ItemStack(Items.spider_eye, 1), GT_Values.NI, GT_Values.NF, GT_Values.NF, new ItemStack(Items.fermented_spider_eye, 1), 100, 8);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Gold, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.LiveRoot, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.IronWood, 2L), 100, 8);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron, 9L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.LiveRoot, 9L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.IronWood, 18L), 900, 8);
//
//        GT_Values.RA.addMixerRecipe(ItemList.IC2_Fertilizer.get(1L, new Object[0]), new ItemStack(Blocks.dirt, 8, 32767), GT_Values.NI, GT_Values.NI, Materials.Water.getFluid(1000L), GT_Values.NF, GT_ModHandler.getModItem(aTextForestry, "soil", 8L, 0), 64, 16);
//        GT_Values.RA.addMixerRecipe(ItemList.FR_Fertilizer.get(1L, new Object[0]), new ItemStack(Blocks.dirt, 8, 32767), GT_Values.NI, GT_Values.NI, Materials.Water.getFluid(1000L), GT_Values.NF, GT_ModHandler.getModItem(aTextForestry, "soil", 8L, 0), 64, 16);
//        GT_Values.RA.addMixerRecipe(ItemList.FR_Compost.get(1L, new Object[0]), new ItemStack(Blocks.dirt, 8, 32767), GT_Values.NI, GT_Values.NI, Materials.Water.getFluid(1000L), GT_Values.NF, GT_ModHandler.getModItem(aTextForestry, "soil", 8L, 0), 64, 16);
//        GT_Values.RA.addMixerRecipe(ItemList.FR_Mulch.get(1L, new Object[0]), new ItemStack(Blocks.dirt, 8, 32767), GT_Values.NI, GT_Values.NI, Materials.Water.getFluid(1000L), GT_Values.NF, GT_ModHandler.getModItem(aTextForestry, "soil", 9L, 0), 64, 16);
//        GT_Values.RA.addMixerRecipe(new ItemStack(Blocks.sand, 1, 32767), new ItemStack(Blocks.dirt, 1, 32767), GT_Values.NI, GT_Values.NI, Materials.Water.getFluid(250L), GT_Values.NF, GT_ModHandler.getModItem(aTextForestry, "soil", 2L, 1), 16, 16);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.LightFuel, 5L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.HeavyFuel, 1L), null, null, null, null, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Fuel, 6L), 16, 16);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Water, 5L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Stone, 1L), null, null, Materials.Lubricant.getFluid(20), new FluidStack(ItemList.sDrillingFluid, 5000), ItemList.Cell_Empty.get(5, new Object[0]), 64, 16);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lapis, 1L), null, null, null, Materials.Water.getFluid(125), FluidRegistry.getFluidStack("ic2coolant", 125), null, 256, 48);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lapis, 1L), null, null, null, GT_ModHandler.getDistilledWater(1000), FluidRegistry.getFluidStack("ic2coolant", 1000), null, 256, 48);
//
//        GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(2, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.EnderEye, 1L), null, null, Materials.Mercury.getFluid(50), null, ItemList.MSFMixture.get(2, new Object[]{}), 100, 64);
//        GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(2, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Blaze, 1L), null, null, Materials.Mercury.getFluid(50), null, ItemList.MSFMixture.get(2, new Object[]{}), 100, 64);
//
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.MSFMixture.get(6, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Diamond, 1L), null, Materials.NitroFuel.getFluid(1000), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.MSFMixture.get(4, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Diamond, 1L), null, Materials.NitroFuel.getFluid(800), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Coal, 1), ItemList.MSFMixture.get(2, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Diamond, 1L), null, Materials.NitroFuel.getFluid(500), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.MSFMixture.get(6, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Emerald, 1L), null, Materials.NitroFuel.getFluid(1000), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.MSFMixture.get(4, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Emerald, 1L), null, Materials.NitroFuel.getFluid(800), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Coal, 1), ItemList.MSFMixture.get(2, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Emerald, 1L), null, Materials.NitroFuel.getFluid(500), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.MSFMixture.get(6, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.BlueSapphire, 1L), null, Materials.NitroFuel.getFluid(1000), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.MSFMixture.get(4, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.BlueSapphire, 1L), null, Materials.NitroFuel.getFluid(800), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Coal, 1), ItemList.MSFMixture.get(2, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.BlueSapphire, 1L), null, Materials.NitroFuel.getFluid(500), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.MSFMixture.get(6, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.GreenSapphire, 1L), null, Materials.NitroFuel.getFluid(1000), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.MSFMixture.get(4, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.GreenSapphire, 1L), null, Materials.NitroFuel.getFluid(800), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Coal, 1), ItemList.MSFMixture.get(2, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.GreenSapphire, 1L), null, Materials.NitroFuel.getFluid(500), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.SFMixture.get(6, new Object[]{}), null, null, Materials.NitroFuel.getFluid(1000), null, ItemList.Block_SSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.SFMixture.get(4, new Object[]{}), null, null, Materials.NitroFuel.getFluid(800), null, ItemList.Block_SSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Coal, 1), ItemList.SFMixture.get(2, new Object[]{}), null, null, Materials.NitroFuel.getFluid(500), null, ItemList.Block_SSFUEL.get(1, new Object[]{}), 120, 96);
//
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.SFMixture.get(6, new Object[]{}), null, null, Materials.HeavyFuel.getFluid(1500), null, ItemList.Block_SSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.SFMixture.get(4, new Object[]{}), null, null, Materials.HeavyFuel.getFluid(1200), null, ItemList.Block_SSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Coal, 1), ItemList.SFMixture.get(2, new Object[]{}), null, null, Materials.HeavyFuel.getFluid(750), null, ItemList.Block_SSFUEL.get(1, new Object[]{}), 120, 96);
//
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.SFMixture.get(6, new Object[]{}), null, null, Materials.LPG.getFluid(1500), null, ItemList.Block_SSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.SFMixture.get(4, new Object[]{}), null, null, Materials.LPG.getFluid(1200), null, ItemList.Block_SSFUEL.get(1, new Object[]{}), 120, 96);
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Coal, 1), ItemList.SFMixture.get(2, new Object[]{}), null, null, Materials.LPG.getFluid(750), null, ItemList.Block_SSFUEL.get(1, new Object[]{}), 120, 96);
//
//        if (Utils.isModLoaded("Railcraft")) {
//            GT_Values.RA.addMixerRecipe(EnumCube.COKE_BLOCK.getItem(), ItemList.SFMixture.get(1, new Object[]{}), null, null, Materials.NitroFuel.getFluid(250), null, ItemList.Block_SSFUEL.get(1, new Object[]{}), 120, 96);
//            GT_Values.RA.addMixerRecipe(EnumCube.COKE_BLOCK.getItem(), ItemList.SFMixture.get(1, new Object[]{}), null, null, Materials.HeavyFuel.getFluid(375), null, ItemList.Block_SSFUEL.get(1, new Object[]{}), 120, 96);
//            GT_Values.RA.addMixerRecipe(EnumCube.COKE_BLOCK.getItem(), ItemList.SFMixture.get(1, new Object[]{}), null, null, Materials.LPG.getFluid(375), null, ItemList.Block_SSFUEL.get(1, new Object[]{}), 120, 96);
//            if (Utils.isModLoaded("Thaumcraft")) {
//                GT_Values.RA.addMixerRecipe(EnumCube.COKE_BLOCK.getItem(), ItemList.MSFMixture.get(1, new Object[]{}), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.NitroFuel.getFluid(250), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//                GT_Values.RA.addMixerRecipe(EnumCube.COKE_BLOCK.getItem(), ItemList.MSFMixture.get(1, new Object[]{}), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.HeavyFuel.getFluid(375), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//                GT_Values.RA.addMixerRecipe(EnumCube.COKE_BLOCK.getItem(), ItemList.MSFMixture.get(1, new Object[]{}), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.LPG.getFluid(375), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//            }
//        }
//        GT_Values.RA.addExtruderRecipe(ItemList.FR_Wax.get(1L, new Object[0]), ItemList.Shape_Extruder_Cell.get(0L, new Object[0]), ItemList.FR_WaxCapsule.get(1L, new Object[0]), 64, 16);
//        GT_Values.RA.addExtruderRecipe(ItemList.FR_RefractoryWax.get(1L, new Object[0]), ItemList.Shape_Extruder_Cell.get(0L, new Object[0]), ItemList.FR_RefractoryCapsule.get(1L, new Object[0]), 128, 16);
//
//        GT_Values.RA.addFluidCannerRecipe(ItemList.Battery_Hull_LV.get(1L, new Object[0]), ItemList.IC2_ReBattery.get(1L, new Object[0]), Materials.Redstone.getMolten(288L), GT_Values.NF);
//        GT_Values.RA.addFluidCannerRecipe(ItemList.Battery_Hull_LV.get(1L, new Object[0]), ItemList.Battery_SU_LV_Mercury.getWithCharge(1L, Integer.MAX_VALUE, new Object[0]), Materials.Mercury.getFluid(1000L), GT_Values.NF);
//        GT_Values.RA.addFluidCannerRecipe(ItemList.Battery_Hull_MV.get(1L, new Object[0]), ItemList.Battery_SU_MV_Mercury.getWithCharge(1L, Integer.MAX_VALUE, new Object[0]), Materials.Mercury.getFluid(4000L), GT_Values.NF);
//        GT_Values.RA.addFluidCannerRecipe(ItemList.Battery_Hull_HV.get(1L, new Object[0]), ItemList.Battery_SU_HV_Mercury.getWithCharge(1L, Integer.MAX_VALUE, new Object[0]), Materials.Mercury.getFluid(16000L), GT_Values.NF);
//        GT_Values.RA.addFluidCannerRecipe(ItemList.Battery_Hull_LV.get(1L, new Object[0]), ItemList.Battery_SU_LV_SulfuricAcid.getWithCharge(1L, Integer.MAX_VALUE, new Object[0]), Materials.SulfuricAcid.getFluid(1000L), GT_Values.NF);
//        GT_Values.RA.addFluidCannerRecipe(ItemList.Battery_Hull_MV.get(1L, new Object[0]), ItemList.Battery_SU_MV_SulfuricAcid.getWithCharge(1L, Integer.MAX_VALUE, new Object[0]), Materials.SulfuricAcid.getFluid(4000L), GT_Values.NF);
//        GT_Values.RA.addFluidCannerRecipe(ItemList.Battery_Hull_HV.get(1L, new Object[0]), ItemList.Battery_SU_HV_SulfuricAcid.getWithCharge(1L, Integer.MAX_VALUE, new Object[0]), Materials.SulfuricAcid.getFluid(16000L), GT_Values.NF);
//        GT_Values.RA.addFluidCannerRecipe(ItemList.TF_Vial_FieryTears.get(1L, new Object[0]), ItemList.Bottle_Empty.get(1L, new Object[0]), GT_Values.NF, Materials.FierySteel.getFluid(250L));
//
//
//        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Ball.get(0L, new Object[0]), Materials.Mercury.getFluid(1000L), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 1, 3), 128, 4);
//        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Ball.get(0L, new Object[0]), Materials.Mercury.getFluid(1000L), GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Mercury, 1L), 128, 4);
//
//        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Block.get(0L, new Object[0]), Materials.Concrete.getMolten(144L), new ItemStack(GregTech_API.sBlockConcretes, 1, 8), 12, 4);
//
//        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Plate.get(0L, new Object[0]), Materials.Glass.getMolten(144L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Glass, 1L), 12, 4);
//        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Bottle.get(0L, new Object[0]), Materials.Glass.getMolten(144L), ItemList.Bottle_Empty.get(1L, new Object[0]), 12, 4);
//
//        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Anvil.get(0L, new Object[0]), Materials.Iron.getMolten(4464L), new ItemStack(Blocks.anvil, 1, 0), 128, 16);
//        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Anvil.get(0L, new Object[0]), Materials.WroughtIron.getMolten(4464L), new ItemStack(Blocks.anvil, 1, 0), 128, 16);
//        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Anvil.get(0L, new Object[0]), Materials.Steel.getMolten(4464L), GT_ModHandler.getModItem("Railcraft", "tile.railcraft.anvil", 1L, 0), 128, 16);
//
//
//        GT_Values.RA.addChemicalBathRecipe(GT_ModHandler.getIC2Item("dynamite", 1L), Materials.Glue.getFluid(10L), GT_ModHandler.getIC2Item("stickyDynamite", 1L), GT_Values.NI, GT_Values.NI, null, 16, 4);
//        GT_Values.RA.addChemicalRecipe(new ItemStack(Items.paper, 1), new ItemStack(Items.string, 1), Materials.Glyceryl.getFluid(150), GT_Values.NF, GT_ModHandler.getIC2Item("dynamite", 1L), 160, 4);
//        GT_Values.RA.addChemicalBathRecipe(GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Steel, 1L), Materials.Concrete.getMolten(144L), GT_ModHandler.getIC2Item("reinforcedStone", 1L), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 1L), Materials.Water.getFluid(125L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HydratedCoal, 1L), GT_Values.NI, GT_Values.NI, null, 12, 4);
//        GT_Values.RA.addChemicalBathRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L), Materials.Water.getFluid(100L), new ItemStack(Items.paper, 1, 0), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Paper, 1L), Materials.Water.getFluid(100L), new ItemStack(Items.paper, 1, 0), GT_Values.NI, GT_Values.NI, null, 100, 4);
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(Items.reeds, 1, 32767), Materials.Water.getFluid(100L), new ItemStack(Items.paper, 1, 0), GT_Values.NI, GT_Values.NI, null, 100, 8);
//        GT_Values.RA.addChemicalBathRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 1L), GT_ModHandler.getDistilledWater(125L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HydratedCoal, 1L), GT_Values.NI, GT_Values.NI, null, 12, 4);
//        GT_Values.RA.addChemicalBathRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L), GT_ModHandler.getDistilledWater(100L), new ItemStack(Items.paper, 1, 0), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Paper, 1L), GT_ModHandler.getDistilledWater(100L), new ItemStack(Items.paper, 1, 0), GT_Values.NI, GT_Values.NI, null, 100, 4);
//
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 8), Materials.Water.getFluid(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 0), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 9), Materials.Water.getFluid(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 1), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 10), Materials.Water.getFluid(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 2), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 11), Materials.Water.getFluid(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 3), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 12), Materials.Water.getFluid(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 4), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 13), Materials.Water.getFluid(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 5), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 14), Materials.Water.getFluid(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 6), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 15), Materials.Water.getFluid(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 7), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 8), GT_ModHandler.getDistilledWater(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 0), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 9), GT_ModHandler.getDistilledWater(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 1), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 10), GT_ModHandler.getDistilledWater(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 2), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 11), GT_ModHandler.getDistilledWater(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 3), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 12), GT_ModHandler.getDistilledWater(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 4), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 13), GT_ModHandler.getDistilledWater(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 5), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 14), GT_ModHandler.getDistilledWater(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 6), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addChemicalBathRecipe(new ItemStack(GregTech_API.sBlockConcretes, 1, 15), GT_ModHandler.getDistilledWater(250L), new ItemStack(GregTech_API.sBlockConcretes, 1, 7), GT_Values.NI, GT_Values.NI, null, 200, 4);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.BlackSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Plastic, 1L), Materials.Concrete.getMolten(144L), ItemList.Block_Plascrete.get(1L, new Object[0]), 200, 48);
//        GT_Values.RA.addChemicalBathRecipe(GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.TungstenSteel, 1L), Materials.Concrete.getMolten(144L), ItemList.Block_TungstenSteelReinforced.get(1L, new Object[0]), GT_Values.NI, GT_Values.NI, null, 200, 4);
//
//
//
//
//
//        for (byte i = 0; i < 16; i = (byte) (i + 1)) {
//            for (int j = 0; j < Dyes.VALUES[i].getSizeOfFluidList(); j++) {
//                if (i != 15) {
//                    GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.wool, 1, 0), Dyes.VALUES[i].getFluidDye(j, 72L), new ItemStack(Blocks.wool, 1, 15 - i), GT_Values.NI, GT_Values.NI, null, 64, 2);
//                }
//                GT_Values.RA.addAssemblerRecipe(new ItemStack(Items.string, 3), ItemList.Circuit_Integrated.getWithDamage(0L, 3L, new Object[0]), Dyes.VALUES[i].getFluidDye(j, 24L), new ItemStack(Blocks.carpet, 2, 15 - i), 128, 5);
//                GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.glass, 1, 0), Dyes.VALUES[i].getFluidDye(j, 18L), new ItemStack(Blocks.stained_glass, 1, 15 - i), GT_Values.NI, GT_Values.NI, null, 64, 2);
//                GT_Values.RA.addChemicalBathRecipe(new ItemStack(Blocks.hardened_clay, 1, 0), Dyes.VALUES[i].getFluidDye(j, 18L), new ItemStack(Blocks.stained_hardened_clay, 1, 15 - i), GT_Values.NI, GT_Values.NI, null, 64, 2);
//            }
//        }
//        GT_Values.RA.addFluidExtractionRecipe(ItemList.Dye_SquidInk.get(1L, new Object[0]), GT_Values.NI, FluidRegistry.getFluidStack("squidink", 144), 10000, 128, 4);
//        GT_Values.RA.addFluidExtractionRecipe(ItemList.Dye_Indigo.get(1L, new Object[0]), GT_Values.NI, FluidRegistry.getFluidStack("indigo", 144), 10000, 128, 4);
//
//
//        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L), ItemList.IC2_Plantball.get(1L, new Object[0]), Materials.Creosote.getFluid(5L), 100, 16, 4);
//        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HydratedCoal, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 1L), Materials.Water.getFluid(100L), 10000, 32, 4);
//        GT_Values.RA.addFluidExtractionRecipe(GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 1, 3), GT_Values.NI, Materials.Mercury.getFluid(1000L), 10000, 128, 4);
//        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Mercury, 1L), GT_Values.NI, Materials.Mercury.getFluid(1000L), 10000, 128, 4);
//        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Monazite, 1L), GT_Values.NI, Materials.Helium.getFluid(200L), 10000, 64, 64);
//
//        GT_Values.RA.addFluidSmelterRecipe(new ItemStack(Items.snowball, 1, 0), GT_Values.NI, Materials.Water.getFluid(250L), 10000, 32, 4);
//        GT_Values.RA.addFluidSmelterRecipe(new ItemStack(Blocks.snow, 1, 0), GT_Values.NI, Materials.Water.getFluid(1000L), 10000, 128, 4);
//        GT_Values.RA.addFluidSmelterRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ice, 1L), GT_Values.NI, Materials.Ice.getSolid(1000L), 10000, 128, 4);
//        GT_Values.RA.addFluidSmelterRecipe(GT_ModHandler.getModItem(aTextForestry, "phosphor", 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphor, 1L), Materials.Lava.getFluid(800L), 1000, 256, 128);
//
//
//        GT_Values.RA.addFormingPressRecipe(new ItemStack(Blocks.glass, 1, 32767), ItemList.Shape_Mold_Arrow.get(0L, new Object[0]), ItemList.Arrow_Head_Glass_Emtpy.get(1L, new Object[0]), 64, 4);
//        for (Materials tMat : Materials.values()) {
//            if ((tMat.mStandardMoltenFluid != null) && (tMat.contains(SubTag.SOLDERING_MATERIAL))) {
//                int tMultiplier = tMat.contains(SubTag.SOLDERING_MATERIAL_GOOD) ? 1 : tMat.contains(SubTag.SOLDERING_MATERIAL_BAD) ? 4 : 2;
//
//                GT_Values.RA.addAssemblerRecipe(ItemList.IC2_Item_Casing_Steel.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.RedAlloy, 2L), tMat.getMolten(144L * tMultiplier / 8L), ItemList.Circuit_Primitive.get(1L, new Object[0]), 16, 8);
//                GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Plastic, 1L), GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.RedAlloy, 1L), tMat.getMolten(144L * tMultiplier / 8L), ItemList.Circuit_Primitive.get(1L, new Object[0]), 16, 8);
//                GT_Values.RA.addAssemblerRecipe(ItemList.Circuit_Board_Basic.get(1L, new Object[0]), ItemList.Circuit_Primitive.get(2L, new Object[0]), tMat.getMolten(144L * tMultiplier / 4L), ItemList.Circuit_Basic.get(1L, new Object[0]), 32, 16);
//                GT_Values.RA.addAssemblerRecipe(ItemList.Circuit_Basic.get(1L, new Object[0]), ItemList.Circuit_Primitive.get(2L, new Object[0]), tMat.getMolten(144L * tMultiplier / 4L), ItemList.Circuit_Good.get(1L, new Object[0]), 32, 16);
//                GT_Values.RA.addAssemblerRecipe(ItemList.Circuit_Board_Advanced.get(1L, new Object[0]), ItemList.Circuit_Parts_Advanced.get(2L, new Object[0]), tMat.getMolten(144L * tMultiplier / 2L), ItemList.Circuit_Advanced.get(1L, new Object[0]), 32, 64);
//                GT_Values.RA.addAssemblerRecipe(ItemList.Circuit_Board_Advanced.get(1L, new Object[0]), ItemList.Circuit_Parts_Crystal_Chip_Elite.get(1L, new Object[0]), tMat.getMolten(144L * tMultiplier / 2L), ItemList.Circuit_Data.get(1L, new Object[0]), 32, 64);
//                GT_Values.RA.addAssemblerRecipe(ItemList.Circuit_Board_Elite.get(1L, new Object[0]), ItemList.Circuit_Data.get(3L, new Object[0]), tMat.getMolten(144L * tMultiplier / 1L), ItemList.Circuit_Elite.get(1L, new Object[0]), 32, 256);
//                GT_Values.RA.addAssemblerRecipe(ItemList.Circuit_Board_Elite.get(1L, new Object[0]), ItemList.Circuit_Parts_Crystal_Chip_Master.get(3L, new Object[0]), tMat.getMolten(144L * tMultiplier / 1L), ItemList.Circuit_Master.get(1L, new Object[0]), 32, 256);
//                GT_Values.RA.addAssemblerRecipe(ItemList.Circuit_Data.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Plastic, 2L), tMat.getMolten(144L * tMultiplier / 2L), ItemList.Tool_DataStick.get(1L, new Object[0]), 128, 64);
//                for (ItemStack tPlate : new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.WroughtIron, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1L)}) {
//                    GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.lever, 1, 32767), tPlate, tMat.getMolten(144L * tMultiplier / 2L), ItemList.Cover_Controller.get(1L, new Object[0]), 800, 16);
//                    GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.redstone_torch, 1, 32767), tPlate, tMat.getMolten(144L * tMultiplier / 2L), ItemList.Cover_ActivityDetector.get(1L, new Object[0]), 800, 16);
//                    GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.heavy_weighted_pressure_plate, 1, 32767), tPlate, tMat.getMolten(144L * tMultiplier / 2L), ItemList.Cover_FluidDetector.get(1L, new Object[0]), 800, 16);
//                    GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.light_weighted_pressure_plate, 1, 32767), tPlate, tMat.getMolten(144L * tMultiplier / 2L), ItemList.Cover_ItemDetector.get(1L, new Object[0]), 800, 16);
//                    GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("ecMeter", 1L), tPlate, tMat.getMolten(144L * tMultiplier / 2L), ItemList.Cover_EnergyDetector.get(1L, new Object[0]), 800, 16);
//                }
//            }
//        }
//        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.redstone_torch, 2, 32767), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), Materials.Concrete.getMolten(144L), new ItemStack(Items.repeater, 1, 0), 800, 1);
//        GT_Values.RA.addAssemblerRecipe(new ItemStack(Items.leather, 1, 32767), new ItemStack(Items.lead, 1, 32767), Materials.Glue.getFluid(50L), new ItemStack(Items.name_tag, 1, 0), 100, 8);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Paper, 8L), new ItemStack(Items.compass, 1, 32767), GT_Values.NF, new ItemStack(Items.map, 1, 0), 100, 8);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tantalum, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Manganese, 1L), Materials.Plastic.getMolten(144L), ItemList.Battery_RE_ULV_Tantalum.get(1L, new Object[0]), 100, 4);
//        GT_Values.RA.addAssemblerRecipe(ItemList.Circuit_Elite.get(2L, new Object[0]), ItemList.Circuit_Parts_Crystal_Chip_Elite.get(18L, new Object[0]), GT_Values.NF, ItemList.Tool_DataOrb.get(1L, new Object[0]), 512, 256);
//        GT_Values.RA.addAssemblerRecipe(ItemList.Circuit_Master.get(2L, new Object[0]), ItemList.Circuit_Parts_Crystal_Chip_Master.get(18L, new Object[0]), GT_Values.NF, ItemList.Energy_LapotronicOrb.get(1L, new Object[0]), 512, 1024);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Europium, 4L), ItemList.Energy_LapotronicOrb.get(8L, new Object[0]), GT_Values.NF, ItemList.Energy_LapotronicOrb2.get(1L, new Object[0]), 2048, 4096);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Neutronium, 16L), ItemList.Energy_LapotronicOrb2.get(8L, new Object[0]), GT_Values.NF, ItemList.ZPM2.get(1L, new Object[0]), 32768, 4096);
//
//        GT_Values.RA.addAssemblerRecipe(ItemList.FR_Wax.get(6L, new Object[0]), new ItemStack(Items.string, 1, 32767), Materials.Water.getFluid(600L), GT_ModHandler.getModItem(aTextForestry, "candle", 24L, 0), 64, 8);
//        GT_Values.RA.addAssemblerRecipe(ItemList.FR_Wax.get(2L, new Object[0]), ItemList.FR_Silk.get(1L, new Object[0]), Materials.Water.getFluid(200L), GT_ModHandler.getModItem(aTextForestry, "candle", 8L, 0), 16, 8);
//        GT_Values.RA.addAssemblerRecipe(ItemList.FR_Silk.get(9L, new Object[0]), ItemList.Circuit_Integrated.getWithDamage(0L, 9L, new Object[0]), Materials.Water.getFluid(500L), GT_ModHandler.getModItem(aTextForestry, "craftingMaterial", 1L, 3), 64, 8);
//        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem(aTextForestry, "propolis", 5L, 2), ItemList.Circuit_Integrated.getWithDamage(0L, 5L, new Object[0]), GT_Values.NF, GT_ModHandler.getModItem(aTextForestry, "craftingMaterial", 1L, 1), 16, 8);
//        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem(aTextForestry, "sturdyMachine", 1L, 0), GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Diamond, 4L), Materials.Water.getFluid(5000L), ItemList.FR_Casing_Hardened.get(1L, new Object[0]), 64, 32);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Bronze, 8L), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), GT_Values.NF, ItemList.FR_Casing_Sturdy.get(1L, new Object[0]), 32, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 6L), Materials.Water.getFluid(1000L), GT_ModHandler.getModItem(aTextForestry, "chipsets", 1L, 0), 16, 8);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Bronze, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 6L), Materials.Water.getFluid(1000L), GT_ModHandler.getModItem(aTextForestry, "chipsets", 1L, 1), 32, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 6L), Materials.Water.getFluid(1000L), GT_ModHandler.getModItem(aTextForestry, "chipsets", 1L, 2), 48, 24);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.WroughtIron, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 6L), Materials.Water.getFluid(1000L), GT_ModHandler.getModItem(aTextForestry, "chipsets", 1L, 2), 48, 24);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Gold, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 6L), Materials.Water.getFluid(1000L), GT_ModHandler.getModItem(aTextForestry, "chipsets", 1L, 3), 64, 32);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), new ItemStack(Blocks.wool, 1, 32767), Materials.Creosote.getFluid(1000L), new ItemStack(Blocks.torch, 6, 0), 400, 1);
//        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem(aTextForestry, "craftingMaterial", 5L, 1), ItemList.Circuit_Integrated.getWithDamage(0L, 5L, new Object[0]), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.EnderPearl, 1L), 64, 8);
//        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.piston, 1, 32767), new ItemStack(Items.slime_ball, 1, 32767), GT_Values.NF, new ItemStack(Blocks.sticky_piston, 1, 0), 100, 4);
//        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.piston, 1, 32767), ItemList.IC2_Resin.get(1L, new Object[0]), GT_Values.NF, new ItemStack(Blocks.sticky_piston, 1, 0), 100, 4);
//        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.piston, 1, 32767), ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), Materials.Glue.getFluid(100L), new ItemStack(Blocks.sticky_piston, 1, 0), 100, 4);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Rubber, 3L), GT_ModHandler.getIC2Item("carbonMesh", 3L), Materials.Glue.getFluid(300L), ItemList.Duct_Tape.get(1L, new Object[0]), 100, 64);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Paper, 3L), new ItemStack(Items.leather, 1, 32767), Materials.Glue.getFluid(20L), new ItemStack(Items.book, 1, 0), 32, 8);
//        GT_Values.RA.addAssemblerRecipe(ItemList.Paper_Printed_Pages.get(1L, new Object[0]), new ItemStack(Items.leather, 1, 32767), Materials.Glue.getFluid(20L), new ItemStack(Items.written_book, 1, 0), 32, 8);
//        GT_Values.RA.addAssemblerRecipe(ItemList.IC2_Item_Casing_Tin.get(4L, new Object[0]), new ItemStack(Blocks.glass_pane, 1, 32767), GT_Values.NF, ItemList.Cell_Universal_Fluid.get(1L, new Object[0]), 128, 8);
//
//
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 2L), new ItemStack(Items.iron_door, 1), ItemList.Cover_Shutter.get(2L, new Object[0]), 800, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 2L), new ItemStack(Items.iron_door, 1), ItemList.Cover_Shutter.get(2L, new Object[0]), 800, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.WroughtIron, 2L), new ItemStack(Items.iron_door, 1), ItemList.Cover_Shutter.get(2L, new Object[0]), 800, 16);
//
//        GT_Values.RA.addUniversalDistillationRecipe(Materials.OilLight.getFluid(150), new FluidStack[]{Materials.SulfuricGas.getGas(240), Materials.SulfuricNaphtha.getFluid(30), Materials.SulfuricLightFuel.getFluid(20), Materials.SulfuricHeavyFuel.getFluid(10)}, null, 32, 64);
//        GT_Values.RA.addUniversalDistillationRecipe(Materials.OilMedium.getFluid(100), new FluidStack[]{Materials.SulfuricGas.getGas(60), Materials.SulfuricNaphtha.getFluid(20), Materials.SulfuricLightFuel.getFluid(50), Materials.SulfuricHeavyFuel.getFluid(15)}, null, 32, 64);
//        GT_Values.RA.addUniversalDistillationRecipe(Materials.Oil.getFluid(50L), new FluidStack[]{Materials.SulfuricGas.getGas(60), Materials.SulfuricNaphtha.getFluid(20), Materials.SulfuricLightFuel.getFluid(50), Materials.SulfuricHeavyFuel.getFluid(15)}, null, 32, 64);
//
//        if (FluidRegistry.getFluid("oilgc") != null) {
//            GT_Values.RA.addUniversalDistillationRecipe(new FluidStack(FluidRegistry.getFluid("oilgc"), 50), new FluidStack[]{Materials.SulfuricGas.getGas(60), Materials.SulfuricNaphtha.getFluid(20), Materials.SulfuricLightFuel.getFluid(50), Materials.SulfuricHeavyFuel.getFluid(15)}, null, 32, 64);
//        }
//        GT_Values.RA.addUniversalDistillationRecipe(Materials.OilHeavy.getFluid(100), new FluidStack[]{Materials.SulfuricGas.getGas(60), Materials.SulfuricNaphtha.getFluid(15), Materials.SulfuricLightFuel.getFluid(45), Materials.SulfuricHeavyFuel.getFluid(250)}, null, 32, 192);
//        GT_Values.RA.addUniversalDistillationRecipe(Materials.CrackedLightFuel.getFluid(100), new FluidStack[]{Materials.Gas.getGas(240), Materials.Naphtha.getFluid(30), Materials.HeavyFuel.getFluid(10), new FluidStack(ItemList.sToluene, 10)}, null, 16, 64);
//        GT_Values.RA.addUniversalDistillationRecipe(Materials.CrackedHeavyFuel.getFluid(100), new FluidStack[]{Materials.Gas.getGas(80), Materials.Naphtha.getFluid(10), Materials.LightFuel.getFluid(40), new FluidStack(ItemList.sToluene, 30), Materials.Lubricant.getFluid(5)}, GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.HydratedCoal, 1L), 16, 64);
//
//        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), Materials.HeavyFuel.getFluid(10L), new FluidStack(ItemList.sToluene, 4), 16, 24, false);
//        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), new FluidStack(ItemList.sToluene, 30), Materials.LightFuel.getFluid(30L), 16, 24, false);
//        GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Ball.get(0L, new Object[0]), new FluidStack(ItemList.sToluene, 100), ItemList.GelledToluene.get(1, new Object[0]), 100, 16);
//        GT_Values.RA.addChemicalRecipe(ItemList.GelledToluene.get(4, new Object[0]), GT_Values.NI, Materials.SulfuricAcid.getFluid(250), GT_Values.NF, new ItemStack(Blocks.tnt, 1), 200, 24);
//
//        GT_Values.RA.addChemicalRecipe(new ItemStack(Items.sugar), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Plastic, 1), new FluidStack(ItemList.sToluene, 133), GT_Values.NF, ItemList.GelledToluene.get(2, new Object[0]), 140, 192);
//
//        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SulfuricAcid, 1), null, null, null, new FluidStack(ItemList.sNitricAcid, 1000), new FluidStack(ItemList.sNitrationMixture, 2000), ItemList.Cell_Empty.get(1, new Object[0]), 500, 2);
//        GT_Values.RA.addChemicalRecipe(ItemList.GelledToluene.get(4, new Object[0]), GT_Values.NI, new FluidStack(ItemList.sNitrationMixture, 250), GT_Values.NF, GT_ModHandler.getIC2Item("industrialTnt", 1L), 80, 480);
//
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Hydrogen, 1L), ItemList.Cell_Empty.get(1, new Object[0]), Materials.NatruralGas.getGas(16000), Materials.Gas.getGas(16000), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.HydricSulfide, 2L), 160);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Hydrogen, 1L), ItemList.Cell_Empty.get(1, new Object[0]), Materials.SulfuricGas.getGas(16000), Materials.Gas.getGas(16000), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.HydricSulfide, 2L), 160);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Hydrogen, 1L), ItemList.Cell_Empty.get(1, new Object[0]), Materials.SulfuricNaphtha.getFluid(7000), Materials.Naphtha.getFluid(7000), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.HydricSulfide, 2L), 160);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Hydrogen, 1L), ItemList.Cell_Empty.get(1, new Object[0]), Materials.SulfuricLightFuel.getFluid(6000), Materials.LightFuel.getFluid(6000), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.HydricSulfide, 2L), 160);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Hydrogen, 1L), ItemList.Cell_Empty.get(1, new Object[0]), Materials.SulfuricHeavyFuel.getFluid(4000), Materials.HeavyFuel.getFluid(4000), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.HydricSulfide, 2L), 160);
//
//        GT_Values.RA.addCentrifugeRecipe(ItemList.Cell_Empty.get(4, new Object[0]), null, Materials.Gas.getGas(8000), Materials.Methane.getGas(4000), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.LPG, 4), null, null, null, null, null, new int[]{10000}, 200, 5);
//
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.HydricSulfide, 2L), ItemList.Cell_Water.get(2, new Object[0]), null, Materials.SulfuricAcid.getFluid(3000), ItemList.Cell_Empty.get(4, new Object[0]), 320);
//        GT_Values.RA.addChemicalRecipe(ItemList.Cell_Water.get(2, new Object[0]), null, new FluidStack(ItemList.sHydricSulfur, 2000), Materials.SulfuricAcid.getFluid(3000), ItemList.Cell_Empty.get(2, new Object[0]), 320);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.HydricSulfide, 2L), null, Materials.Water.getFluid(2000), Materials.SulfuricAcid.getFluid(3000), ItemList.Cell_Empty.get(2, new Object[0]), 320);
//
//
//        GT_Values.RA.addChemicalRecipe(ItemList.Cell_Air.get(2, new Object[0]), null, Materials.Naphtha.getFluid(288), Materials.Plastic.getMolten(144), ItemList.Cell_Empty.get(2, new Object[0]), 640);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Titanium, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oxygen, 16L), Materials.Naphtha.getFluid(1296), Materials.Plastic.getMolten(1296), ItemList.Cell_Empty.get(16, new Object[0]), 640);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter, 1L), null, Materials.Naphtha.getFluid(576), Materials.Polycaprolactam.getMolten(1296), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Potassium, 1), 640);
//        GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Polycaprolactam, 1L), new ItemStack(Items.string, 32), 80, 48);
//
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Chlorine, 1L), Materials.LPG.getFluid(432), new FluidStack(ItemList.sEpichlorhydrin, 432), ItemList.Cell_Empty.get(1, new Object[0]), 480, 30);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Naphtha, 3L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Fluorine, 1L), new FluidStack(ItemList.sEpichlorhydrin, 432), Materials.Polytetrafluoroethylene.getMolten(432), ItemList.Cell_Empty.get(4, new Object[0]), 240, 256);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Silicon, 1L), null, new FluidStack(ItemList.sEpichlorhydrin, 144), Materials.Silicone.getMolten(144), null, 240, 96);
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Naphtha, 3L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.NitrogenDioxide, 1L), new FluidStack(ItemList.sEpichlorhydrin, 144), Materials.Epoxid.getMolten(288), ItemList.Cell_Empty.get(4, new Object[0]), 240, 30);
//
//        GT_Values.RA.addCrackingRecipe(Materials.LightFuel.getFluid(128), Materials.CrackedLightFuel.getFluid(192), 16, 320);
//        GT_Values.RA.addCrackingRecipe(Materials.HeavyFuel.getFluid(128), Materials.CrackedHeavyFuel.getFluid(192), 16, 320);
//
//        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 4L, new Object[0]), Materials.Creosote.getFluid(3L), Materials.Lubricant.getFluid(1L), 16, 24, false);
//        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 4L, new Object[0]), Materials.SeedOil.getFluid(4L), Materials.Lubricant.getFluid(1L), 16, 24, false);
//        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 4L, new Object[0]), Materials.FishOil.getFluid(3L), Materials.Lubricant.getFluid(1L), 16, 24, false);
//        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), Materials.Biomass.getFluid(40L), Materials.Ethanol.getFluid(12L), 16, 24, false);
//        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 5L, new Object[0]), Materials.Biomass.getFluid(40L), Materials.Water.getFluid(12L), 16, 24, false);
//        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 5L, new Object[0]), Materials.Water.getFluid(5L), GT_ModHandler.getDistilledWater(4L), 16, 8, false);
//        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 4L, new Object[0]), Materials.OilLight.getFluid(300L), Materials.Oil.getFluid(100L), 16, 24, false);
//        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 4L, new Object[0]), Materials.OilMedium.getFluid(200L), Materials.Oil.getFluid(100L), 16, 24, false);
//        GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 4L, new Object[0]), Materials.OilHeavy.getFluid(100L), Materials.Oil.getFluid(100L), 16, 24, false);
//
//        GT_Values.RA.addFluidHeaterRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), Materials.Water.getFluid(6L), Materials.Water.getGas(960L), 30, 32);
//        GT_Values.RA.addFluidHeaterRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), GT_ModHandler.getDistilledWater(6L), Materials.Water.getGas(960L), 30, 32);
//        GT_Values.RA.addFluidHeaterRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), Materials.SeedOil.getFluid(16L), Materials.FryingOilHot.getFluid(16L), 16, 32);
//        GT_Values.RA.addFluidHeaterRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), Materials.FishOil.getFluid(16L), Materials.FryingOilHot.getFluid(16L), 16, 32);
//
//        GT_Values.RA.addBrewingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Talc, 1L), FluidRegistry.getFluid("oil"), FluidRegistry.getFluid("lubricant"), false);
//        GT_Values.RA.addBrewingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Soapstone, 1L), FluidRegistry.getFluid("oil"), FluidRegistry.getFluid("lubricant"), false);
//        GT_Values.RA.addBrewingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), FluidRegistry.getFluid("oil"), FluidRegistry.getFluid("lubricant"), false);
//        GT_Values.RA.addBrewingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Talc, 1L), FluidRegistry.getFluid("creosote"), FluidRegistry.getFluid("lubricant"), false);
//        GT_Values.RA.addBrewingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Soapstone, 1L), FluidRegistry.getFluid("creosote"), FluidRegistry.getFluid("lubricant"), false);
//        GT_Values.RA.addBrewingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), FluidRegistry.getFluid("creosote"), FluidRegistry.getFluid("lubricant"), false);
//        GT_Values.RA.addBrewingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Talc, 1L), FluidRegistry.getFluid("seedoil"), FluidRegistry.getFluid("lubricant"), false);
//        GT_Values.RA.addBrewingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Soapstone, 1L), FluidRegistry.getFluid("seedoil"), FluidRegistry.getFluid("lubricant"), false);
//        GT_Values.RA.addBrewingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), FluidRegistry.getFluid("seedoil"), FluidRegistry.getFluid("lubricant"), false);
//
//        GT_ModHandler.addCompressionRecipe(new ItemStack(Blocks.ice, 2, 32767), new ItemStack(Blocks.packed_ice, 1, 0));
//        GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ice, 1L), new ItemStack(Blocks.ice, 1, 0));
//        GT_ModHandler.addCompressionRecipe(new ItemStack(Items.quartz, 4, 0), new ItemStack(Blocks.quartz_block, 1, 0));
//        GT_ModHandler.addCompressionRecipe(new ItemStack(Items.wheat, 9, 0), new ItemStack(Blocks.hay_block, 1, 0));
//        GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glowstone, 4L), new ItemStack(Blocks.glowstone, 1));
//        GT_Values.RA.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Graphite, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Graphite, 9L), GT_Values.NI, 500, 48);
//        GT_ModHandler.removeFurnaceSmelting(GT_OreDictUnificator.get(OrePrefixes.ore, Materials.Graphite, 1L));
//        GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get(OrePrefixes.ore, Materials.Graphite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Graphite, 1L));
//        GT_ModHandler.removeFurnaceSmelting(GT_OreDictUnificator.get(OrePrefixes.oreBlackgranite, Materials.Graphite, 1L));
//        GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get(OrePrefixes.oreBlackgranite, Materials.Graphite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Graphite, 1L));
//        GT_ModHandler.removeFurnaceSmelting(GT_OreDictUnificator.get(OrePrefixes.oreEndstone, Materials.Graphite, 1L));
//        GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get(OrePrefixes.oreEndstone, Materials.Graphite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Graphite, 1L));
//        GT_ModHandler.removeFurnaceSmelting(GT_OreDictUnificator.get(OrePrefixes.oreNetherrack, Materials.Graphite, 1L));
//        GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get(OrePrefixes.oreNetherrack, Materials.Graphite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Graphite, 1L));
//        GT_ModHandler.removeFurnaceSmelting(GT_OreDictUnificator.get(OrePrefixes.oreRedgranite, Materials.Graphite, 1L));
//        GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get(OrePrefixes.oreRedgranite, Materials.Graphite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Graphite, 1L));
//
//
//        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.blaze_rod, 1), new ItemStack(Items.blaze_powder, 3), new ItemStack(Items.blaze_powder, 1), 50, false);
//
//        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.flint, 1, 32767), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Flint, 4L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Flint, 1L), 40, true);
//        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.red_mushroom, 1, 32767), ItemList.IC2_Grin_Powder.get(1L, new Object[0]));
//        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.item_frame, 1, 32767), new ItemStack(Items.leather, 1), GT_OreDictUnificator.getDust(Materials.Wood, OrePrefixes.stick.mMaterialAmount * 4L), 95, false);
//        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.bow, 1, 0), new ItemStack(Items.string, 3), GT_OreDictUnificator.getDust(Materials.Wood, OrePrefixes.stick.mMaterialAmount * 3L), 95, false);
//
//        GT_Values.RA.addAmplifier(ItemList.IC2_Scrap.get(9L, new Object[0]), 180, 1);
//        GT_Values.RA.addAmplifier(ItemList.IC2_Scrapbox.get(1L, new Object[0]), 180, 1);
//
//        GT_Values.RA.addBoxingRecipe(ItemList.IC2_Scrap.get(9L, new Object[0]), ItemList.Schematic_3by3.get(0L, new Object[0]), ItemList.IC2_Scrapbox.get(1L, new Object[0]), 16, 1);
//
//        GT_ModHandler.removeRecipeByOutput(ItemList.IC2_Fertilizer.get(1L, new Object[0]));
//        GT_Values.RA.addImplosionRecipe(ItemList.IC2_Compressed_Coal_Chunk.get(1L, new Object[0]), 8, ItemList.IC2_Industrial_Diamond.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 4L));
//        GT_Values.RA.addImplosionRecipe(ItemList.Ingot_IridiumAlloy.get(1L, new Object[0]), 8, GT_OreDictUnificator.get(OrePrefixes.plateAlloy, Materials.Iridium, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 4L));
//
//        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Quartzite, 1L), null, Materials.Glass.getMolten(72), 10000, 600, 28);//(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SiliconDioxide,1L), GT_OreDictUnificator.get(OrePrefixes.dust,Materials.SiliconDioxide,2L),GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Glass,1L)/** GT_Utility.fillFluidContainer(Materials.Glass.getMolten(1000), ItemList.Cell_Empty.get(1, new Object[0]), true, true)**/, 600, 16);
//
//        GT_Values.RA.addDistillationTowerRecipe(Materials.Creosote.getFluid(24L), new FluidStack[]{Materials.Lubricant.getFluid(12L)}, null, 16, 96);
//        GT_Values.RA.addDistillationTowerRecipe(Materials.SeedOil.getFluid(32L), new FluidStack[]{Materials.Lubricant.getFluid(12L)}, null, 16, 96);
//        GT_Values.RA.addDistillationTowerRecipe(Materials.FishOil.getFluid(24L), new FluidStack[]{Materials.Lubricant.getFluid(12L)}, null, 16, 96);
//        GT_Values.RA.addDistillationTowerRecipe(Materials.Biomass.getFluid(150L), new FluidStack[]{Materials.Ethanol.getFluid(60L), Materials.Water.getFluid(60L)}, GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Wood, 1L), 25, 64);
//        GT_Values.RA.addDistillationTowerRecipe(Materials.Water.getFluid(288L), new FluidStack[]{GT_ModHandler.getDistilledWater(260L)}, null, 16, 64);
//        if (!GregTech_API.mIC2Classic) {
//            GT_Values.RA.addDistillationTowerRecipe(new FluidStack(FluidRegistry.getFluid("ic2biomass"), 250), new FluidStack[]{new FluidStack(FluidRegistry.getFluid("ic2biogas"), 8000), Materials.Water.getFluid(125L)}, ItemList.IC2_Fertilizer.get(1, new Object[0]), 250, 480);
//            GT_Values.RA.addFuel(GT_ModHandler.getIC2Item("biogasCell", 1L), null, 32, 1);
//
//            GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), new FluidStack(FluidRegistry.getFluid("ic2biomass"), 1), new FluidStack(FluidRegistry.getFluid("ic2biogas"), 32), 40, 16, false);
//            GT_Values.RA.addDistilleryRecipe(ItemList.Circuit_Integrated.getWithDamage(0L, 2L, new Object[0]), new FluidStack(FluidRegistry.getFluid("ic2biomass"), 4), Materials.Water.getFluid(2), 80, 30, false);
//        }
//
//        GT_Values.RA.addElectrolyzerRecipe(GT_Values.NI, ItemList.Cell_Empty.get(1L, new Object[0]), Materials.Water.getFluid(3000L), Materials.Hydrogen.getGas(2000L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oxygen, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 1500, 30);
//        GT_Values.RA.addElectrolyzerRecipe(GT_Values.NI, ItemList.Cell_Empty.get(1L, new Object[0]), GT_ModHandler.getDistilledWater(3000L), Materials.Hydrogen.getGas(2000L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oxygen, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 1500, 30);
//        GT_Values.RA.addElectrolyzerRecipe(GT_ModHandler.getIC2Item("electrolyzedWaterCell", 3L), 0, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Hydrogen, 2L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oxygen, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 30, 30);
//        GT_Values.RA.addElectrolyzerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Water, 1L), 0, GT_ModHandler.getIC2Item("electrolyzedWaterCell", 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, 490, 30);
//        GT_Values.RA.addElectrolyzerRecipe(ItemList.Dye_Bonemeal.get(3L, new Object[0]), 0, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, 98, 26);
//        GT_Values.RA.addElectrolyzerRecipe(new ItemStack(Blocks.sand, 8), 0, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.SiliconDioxide, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, 500, 25);
//        GT_Values.RA.addElectrolyzerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tungstate, 7L), GT_Values.NI, Materials.Hydrogen.getGas(7000L), Materials.Oxygen.getGas(4000L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tungsten, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lithium, 2L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{10000, 10000, 0, 0, 0, 0}, 120, 1920);
//        GT_Values.RA.addElectrolyzerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Scheelite, 7L), GT_Values.NI, Materials.Hydrogen.getGas(7000L), Materials.Oxygen.getGas(4000L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tungsten, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 2L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{10000, 10000, 0, 0, 0, 0}, 120, 1920);
//        //GT_Values.RA.addElectrolyzerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.CarbonDioxide, 4), GT_Values.NI, GT_Values.NF,GT_Values.NF,									   GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 3),   GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oxygen, 1), ItemList.Cell_Empty.get(3, new Object[0]), GT_Values.NI, GT_Values.NI, GT_Values.NI,new int[]{10000,10000,10000,0,0,0}, 180, 60);
//        GT_Values.RA.addElectrolyzerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Graphite, 1), 0, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 4), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, 100, 64);
//
//
//
//
//        GT_Values.RA.addBenderRecipe(ItemList.IC2_Mixed_Metal_Ingot.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.plateAlloy, Materials.Advanced, 1L), 100, 8);
//
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Tin, 12L), ItemList.Cell_Empty.get(6L, new Object[0]), 1200, 8);
//
//        GT_Values.RA.addBenderRecipe(ItemList.IC2_Item_Casing_Iron.get(2L, new Object[0]), GT_ModHandler.getIC2Item("fuelRod", 1L), 100, 8);
//        GT_Values.RA.addBenderRecipe(ItemList.IC2_Item_Casing_Tin.get(1L, new Object[0]), ItemList.IC2_Food_Can_Empty.get(1L, new Object[0]), 100, 8);
//        GT_Values.RA.addPulveriserRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Marble, 1L), new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Marble, 1L)}, null, 160, 4);
//
//        GT_Values.RA.addAlloySmelterRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lead, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Obsidian, 2L), ItemList.TE_Hardened_Glass.get(2L, new Object[0]), 200, 16);
//        GT_Values.RA.addAlloySmelterRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Lead, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Obsidian, 2L), ItemList.TE_Hardened_Glass.get(2L, new Object[0]), 200, 16);
//        GT_Values.RA.addAlloySmelterRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.RawRubber, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Rubber, 1L), 200, 8);
//
//
//
//
//        GT_Values.RA.addLatheRecipe(new ItemStack(Blocks.wooden_slab, 1, GT_Values.W), new ItemStack(Items.bowl, 1), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Wood, 1), 50, 8);
//        GT_Values.RA.addLatheRecipe(GT_ModHandler.getModItem(aTextForestry, "slabs", 1L, GT_Values.W), new ItemStack(Items.bowl, 1), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Wood, 1), 50, 8);
//        GT_Values.RA.addLatheRecipe(GT_ModHandler.getModItem(aTextEBXL, "woodslab", 1L, GT_Values.W), new ItemStack(Items.bowl, 1), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Wood, 1), 50, 8);
//
//        GT_Values.RA.addFormingPressRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Cupronickel, 1L), ItemList.Shape_Mold_Credit.get(0L, new Object[0]), ItemList.Credit_Greg_Cupronickel.get(4L, new Object[0]), 100, 16);
//        GT_Values.RA.addFormingPressRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Brass, 1L), ItemList.Shape_Mold_Credit.get(0L, new Object[0]), ItemList.Coin_Doge.get(4L, new Object[0]), 100, 16);
//        GT_Values.RA.addFormingPressRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), ItemList.Shape_Mold_Credit.get(0L, new Object[0]), ItemList.Credit_Iron.get(4L, new Object[0]), 100, 16);
//        GT_Values.RA.addFormingPressRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.WroughtIron, 1L), ItemList.Shape_Mold_Credit.get(0L, new Object[0]), ItemList.Credit_Iron.get(4L, new Object[0]), 100, 16);
//
//        if (!GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "torchesFromCoal", false)) {
//            GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), new ItemStack(Items.coal, 1, 32767), new ItemStack(Blocks.torch, 4), 400, 1);
//        }
////        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Flint, 5L), new ItemStack(Blocks.tnt, 3, 32767), GT_ModHandler.getIC2Item("industrialTnt", 5L), 800, 2);
////        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gunpowder, 4L), new ItemStack(Blocks.sand, 4, 32767), new ItemStack(Blocks.tnt, 1), 400, 1);
//
//        for (MaterialStack[] tMats : this.mAlloySmelterList) {
//            ItemStack tDust1 = GT_OreDictUnificator.get(OrePrefixes.dust, tMats[0].mMaterial, tMats[0].mAmount);
//            ItemStack tDust2 = GT_OreDictUnificator.get(OrePrefixes.dust, tMats[1].mMaterial, tMats[1].mAmount);
//            ItemStack tIngot1 = GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[0].mMaterial, tMats[0].mAmount);
//            ItemStack tIngot2 = GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[1].mMaterial, tMats[1].mAmount);
//            ItemStack tOutputIngot = GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[2].mMaterial, tMats[2].mAmount);
//            if (tOutputIngot != GT_Values.NI) {
//                GT_ModHandler.addAlloySmelterRecipe(tIngot1, tDust2, tOutputIngot, (int) tMats[2].mAmount * 50, 16, false);
//                GT_ModHandler.addAlloySmelterRecipe(tIngot1, tIngot2, tOutputIngot, (int) tMats[2].mAmount * 50, 16, false);
//                GT_ModHandler.addAlloySmelterRecipe(tDust1, tIngot2, tOutputIngot, (int) tMats[2].mAmount * 50, 16, false);
//                GT_ModHandler.addAlloySmelterRecipe(tDust1, tDust2, tOutputIngot, (int) tMats[2].mAmount * 50, 16, false);
//            }
//        }
//
//
//    }
//}
