///*package muramasa.gregtech.integration.ic2;
//
//import net.minecraft.init.Blocks;
//import net.minecraft.init.Items;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.fluids.Fluid;
//import net.minecraftforge.fluids.FluidRegistry;
//import net.minecraftforge.fluids.FluidStack;
//
//import java.util.Iterator;
//import java.util.Map;
//
//public class IC2Registrar {
//    public static void init() {
//        try {
//            GT_Utility.removeSimpleIC2MachineRecipe(GT_Values.NI, ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), ItemList.Cell_Empty.get(3L, new Object[0]));
//            GT_Utility.removeSimpleIC2MachineRecipe(ItemList.IC2_Energium_Dust.get(1L, new Object[0]), ic2.api.recipe.Recipes.compressor.getRecipes(), GT_Values.NI);
//            GT_Utility.removeSimpleIC2MachineRecipe(new ItemStack(Items.gunpowder), ic2.api.recipe.Recipes.extractor.getRecipes(), GT_Values.NI);
//            GT_Utility.removeSimpleIC2MachineRecipe(new ItemStack(Blocks.wool, 1, 32767), ic2.api.recipe.Recipes.extractor.getRecipes(), GT_Values.NI);
//        } catch (Throwable e) {
//        }
//        GT_ModHandler.addExtractionRecipe(ItemList.IC2_Resin.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.RawRubber, 3L));
//        GT_ModHandler.addExtractionRecipe(GT_ModHandler.getIC2Item("rubberSapling", 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.RawRubber, 1L));
//        GT_ModHandler.addExtractionRecipe(GT_ModHandler.getIC2Item("rubberLeaves", 16L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.RawRubber, 1L));
//        GT_ModHandler.addExtractionRecipe(ItemList.Cell_Air.get(1L, new Object[0]), ItemList.Cell_Empty.get(1L, new Object[0]));
//        GT_ModHandler.addCompressionRecipe(ItemList.IC2_Compressed_Coal_Chunk.get(1L, new Object[0]), ItemList.IC2_Industrial_Diamond.get(1L, new Object[0]));
//        GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uranium, 1L), GT_ModHandler.getIC2Item("Uran238", 1L));
//        GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uranium235, 1L), GT_ModHandler.getIC2Item("Uran235", 1L));
//        GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Plutonium, 1L), GT_ModHandler.getIC2Item("Plutonium", 1L));
//        GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Uranium235, 1L), GT_ModHandler.getIC2Item("smallUran235", 1L));
//        GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Plutonium, 1L), GT_ModHandler.getIC2Item("smallPlutonium", 1L));
//        GT_Values.RA.addAutoclaveRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 16), Materials.Palladium.getMolten(4), GT_ModHandler.getIC2Item("carbonFiber", 8L), 9000, 600, 5);
//        GT_Values.RA.addAutoclaveRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 16), Materials.Platinum.getMolten(4), GT_ModHandler.getIC2Item("carbonFiber", 8L), 5000, 600, 5);
//        GT_Values.RA.addAutoclaveRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 16), Materials.Lutetium.getMolten(4), GT_ModHandler.getIC2Item("carbonFiber", 8L), 3333, 600, 5);
//        GT_Values.RA.addAutoclaveRecipe(ItemList.IC2_Energium_Dust.get(9L, new Object[0]), Materials.Water.getFluid(1000L), ItemList.IC2_EnergyCrystal.get(1L, new Object[0]), 10000, 500, 256);
//        GT_Values.RA.addAutoclaveRecipe(ItemList.IC2_Energium_Dust.get(9L, new Object[0]), GT_ModHandler.getDistilledWater(1000L), ItemList.IC2_EnergyCrystal.get(1L, new Object[0]), 10000, 250, 256);
//        GT_Utility.removeIC2BottleRecipe(GT_ModHandler.getIC2Item("fuelRod", 1), GT_ModHandler.getIC2Item("UranFuel", 1), ic2.api.recipe.Recipes.cannerBottle.getRecipes(), GT_ModHandler.getIC2Item("reactorUraniumSimple", 1, 1));
//        GT_Utility.removeIC2BottleRecipe(GT_ModHandler.getIC2Item("fuelRod", 1), GT_ModHandler.getIC2Item("MOXFuel", 1), ic2.api.recipe.Recipes.cannerBottle.getRecipes(), GT_ModHandler.getIC2Item("reactorMOXSimple", 1, 1));
//        GT_Values.RA.addVacuumFreezerRecipe(GT_ModHandler.getIC2Item("reactorCoolantSimple", 1L, 32767), GT_ModHandler.getIC2Item("reactorCoolantSimple", 1L, 1), 100);
//        GT_Values.RA.addVacuumFreezerRecipe(GT_ModHandler.getIC2Item("reactorCoolantTriple", 1L, 32767), GT_ModHandler.getIC2Item("reactorCoolantTriple", 1L, 1), 300);
//        GT_Values.RA.addVacuumFreezerRecipe(GT_ModHandler.getIC2Item("reactorCoolantSix", 1L, 32767), GT_ModHandler.getIC2Item("reactorCoolantSix", 1L, 1), 600);
//        GT_Values.RA.addVacuumFreezerRecipe(ItemList.Reactor_Coolant_He_1.getWildcard(1L, new Object[0]), ItemList.Reactor_Coolant_He_1.get(1L, new Object[0]), 600);
//        GT_Values.RA.addVacuumFreezerRecipe(ItemList.Reactor_Coolant_He_3.getWildcard(1L, new Object[0]), ItemList.Reactor_Coolant_He_3.get(1L, new Object[0]), 1800);
//        GT_Values.RA.addVacuumFreezerRecipe(ItemList.Reactor_Coolant_He_6.getWildcard(1L, new Object[0]), ItemList.Reactor_Coolant_He_6.get(1L, new Object[0]), 3600);
//        GT_Values.RA.addVacuumFreezerRecipe(ItemList.Reactor_Coolant_NaK_1.getWildcard(1L, new Object[0]), ItemList.Reactor_Coolant_NaK_1.get(1L, new Object[0]), 600);
//        GT_Values.RA.addVacuumFreezerRecipe(ItemList.Reactor_Coolant_NaK_3.getWildcard(1L, new Object[0]), ItemList.Reactor_Coolant_NaK_3.get(1L, new Object[0]), 1800);
//        GT_Values.RA.addVacuumFreezerRecipe(ItemList.Reactor_Coolant_NaK_6.getWildcard(1L, new Object[0]), ItemList.Reactor_Coolant_NaK_6.get(1L, new Object[0]), 3600);
//        GT_Values.RA.addVacuumFreezerRecipe(GT_ModHandler.getIC2Item("airCell", 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.LiquidAir, 1L), 25);
//        GT_Values.RA.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), ItemList.IC2_Item_Casing_Iron.get(2L, new Object[0]), GT_Values.NI, 50, 16);
//        GT_Values.RA.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.WroughtIron, 1L), ItemList.IC2_Item_Casing_Iron.get(2L, new Object[0]), GT_Values.NI, 50, 16);
//        GT_Values.RA.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Gold, 1L), ItemList.IC2_Item_Casing_Gold.get(2L, new Object[0]), GT_Values.NI, 50, 16);
//        GT_Values.RA.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Bronze, 1L), ItemList.IC2_Item_Casing_Bronze.get(2L, new Object[0]), GT_Values.NI, 50, 16);
//        GT_Values.RA.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Copper, 1L), ItemList.IC2_Item_Casing_Copper.get(2L, new Object[0]), GT_Values.NI, 50, 16);
//        GT_Values.RA.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.AnnealedCopper, 1L), ItemList.IC2_Item_Casing_Copper.get(2L, new Object[0]), GT_Values.NI, 50, 16);
//        GT_Values.RA.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Tin, 1L), ItemList.IC2_Item_Casing_Tin.get(2L, new Object[0]), GT_Values.NI, 50, 16);
//        GT_Values.RA.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Lead, 1L), ItemList.IC2_Item_Casing_Lead.get(2L, new Object[0]), GT_Values.NI, 50, 16);
//        GT_Values.RA.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 1L), ItemList.IC2_Item_Casing_Steel.get(2L, new Object[0]), GT_Values.NI, 50, 16);
//        if (!GT_Mod.gregtechproxy.mDisableIC2Cables) {
//            GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Copper, 1L), GT_ModHandler.getIC2Item("copperCableItem", 3L), 100, 2);
//            GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.AnnealedCopper, 1L), GT_ModHandler.getIC2Item("copperCableItem", 3L), 100, 2);
//            GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Tin, 1L), GT_ModHandler.getIC2Item("tinCableItem", 4L), 150, 1);
//            GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), GT_ModHandler.getIC2Item("ironCableItem", 6L), 200, 2);
//            GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.WroughtIron, 1L), GT_ModHandler.getIC2Item("ironCableItem", 6L), 200, 2);
//            GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Gold, 1L), GT_ModHandler.getIC2Item("goldCableItem", 6L), 200, 1);
//        }
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 1L), ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), GT_ModHandler.getIC2Item("ironFence", 1L), 100, 4);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.WroughtIron, 1L), ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), GT_ModHandler.getIC2Item("ironFence", 1L), 100, 4);
//        GT_Values.RA.addAssemblerRecipe(ItemList.IC2_Compressed_Coal_Ball.get(8L, new Object[0]), new ItemStack(Blocks.brick_block, 1), ItemList.IC2_Compressed_Coal_Chunk.get(1L, new Object[0]), 400, 4);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 8L), new ItemStack(Items.flint, 1), ItemList.IC2_Compressed_Coal_Ball.get(1L, new Object[0]), 400, 4);
//        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("waterMill", 2L), ItemList.Circuit_Integrated.getWithDamage(0L, 2L, new Object[0]), GT_ModHandler.getIC2Item("generator", 1L), 6400, 8);
//        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("batPack", 1L, 32767), ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), ItemList.IC2_ReBattery.get(6L, new Object[0]), 800, 4);
//        GT_ModHandler.removeRecipe(new ItemStack[]{new ItemStack(Items.lava_bucket), ItemList.Cell_Empty.get(1L, new Object[0])});
//        GT_ModHandler.removeRecipe(new ItemStack[]{new ItemStack(Items.water_bucket), ItemList.Cell_Empty.get(1L, new Object[0])});
//        GT_ModHandler.removeFurnaceSmelting(ItemList.IC2_Resin.get(1L, new Object[0]));
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(Items.slime_ball, 1), ItemList.IC2_Resin.get(1L, new Object[0]));
//        for (Fluid tFluid : new Fluid[]{FluidRegistry.WATER, GT_ModHandler.getDistilledWater(1L).getFluid()}) {
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2L, new Object[0]), 200);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphorus, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(3L, new Object[0]), 300);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2L, new Object[0]), 200);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(1L, new Object[0]), 100);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(1L, new Object[0]), 100);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(3L, new Object[0]), 300);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphorus, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(4L, new Object[0]), 400);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(3L, new Object[0]), 300);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2L, new Object[0]), 200);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2L, new Object[0]), 200);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(3L, new Object[0]), 300);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphorus, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(4L, new Object[0]), 400);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(3L, new Object[0]), 300);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2L, new Object[0]), 200);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2L, new Object[0]), 200);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(3L, new Object[0]), 300);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphorus, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(4L, new Object[0]), 400);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(3L, new Object[0]), 300);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2L, new Object[0]), 200);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2L, new Object[0]), 200);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.GlauconiteSand, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(3L, new Object[0]), 300);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.GlauconiteSand, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphorus, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(4L, new Object[0]), 400);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.GlauconiteSand, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(3L, new Object[0]), 300);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.GlauconiteSand, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2L, new Object[0]), 200);
//            GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.GlauconiteSand, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 1L), new FluidStack(tFluid, 1000), GT_Values.NF, ItemList.IC2_Fertilizer.get(2L, new Object[0]), 200);
//        }
//
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, Materials.Bronze, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), ItemList.Tool_Sword_Bronze.getUndamaged(1L, new Object[0]), 100, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), ItemList.Tool_Sword_Steel.getUndamaged(1L, new Object[0]), 100, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, Materials.Bronze, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), ItemList.Tool_Pickaxe_Bronze.getUndamaged(1L, new Object[0]), 100, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), ItemList.Tool_Pickaxe_Steel.getUndamaged(1L, new Object[0]), 100, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, Materials.Bronze, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), ItemList.Tool_Shovel_Bronze.getUndamaged(1L, new Object[0]), 100, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), ItemList.Tool_Shovel_Steel.getUndamaged(1L, new Object[0]), 100, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, Materials.Bronze, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), ItemList.Tool_Axe_Bronze.getUndamaged(1L, new Object[0]), 100, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), ItemList.Tool_Axe_Steel.getUndamaged(1L, new Object[0]), 100, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, Materials.Bronze, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), ItemList.Tool_Hoe_Bronze.getUndamaged(1L, new Object[0]), 100, 16);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), ItemList.Tool_Hoe_Steel.getUndamaged(1L, new Object[0]), 100, 16);
//
//        GT_Values.RA.addCannerRecipe(GT_ModHandler.getIC2Item("fuelRod", 1), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Lithium, 1L), GT_ModHandler.getIC2Item("reactorLithiumCell", 1, 1), null, 16, 64);
//        GT_Values.RA.addFluidExtractionRecipe(GT_ModHandler.getIC2Item("TritiumCell", 1), GT_ModHandler.getIC2Item("fuelRod", 1), Materials.Tritium.getGas(32), 10000, 16, 64);
//        GT_Values.RA.addCannerRecipe(GT_ModHandler.getIC2Item("fuelRod", 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Thorium, 3), ItemList.ThoriumCell_1.get(1L, new Object[0]), null, 30, 16);
//        GT_Values.RA.addCannerRecipe(GT_ModHandler.getIC2Item("fuelRod", 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.NaquadahEnriched, 3), ItemList.NaquadahCell_1.get(1L, new Object[0]), null, 30, 16);
//        GT_Values.RA.addCannerRecipe(GT_ModHandler.getIC2Item("fuelRod", 1), GT_ModHandler.getIC2Item("UranFuel", 1), ItemList.Uraniumcell_1.get(1, new Object[0]), null, 30, 16);
//        GT_Values.RA.addCannerRecipe(GT_ModHandler.getIC2Item("fuelRod", 1), GT_ModHandler.getIC2Item("MOXFuel", 1), ItemList.Moxcell_1.get(1, new Object[0]), null, 30, 16);
//
//        Materials tMaterial = Materials.Iron;
//        if (tMaterial.mStandardMoltenFluid != null) {
//            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0L, new Object[0]), tMaterial.getMolten(72L), ItemList.IC2_Item_Casing_Iron.get(1L, new Object[0]), 16, 8);
//        }
//        tMaterial = Materials.WroughtIron;
//        if (tMaterial.mStandardMoltenFluid != null) {
//            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0L, new Object[0]), tMaterial.getMolten(72L), ItemList.IC2_Item_Casing_Iron.get(1L, new Object[0]), 16, 8);
//        }
//        tMaterial = Materials.Gold;
//        if (tMaterial.mStandardMoltenFluid != null) {
//            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0L, new Object[0]), tMaterial.getMolten(72L), ItemList.IC2_Item_Casing_Gold.get(1L, new Object[0]), 16, 8);
//        }
//        tMaterial = Materials.Bronze;
//        if (tMaterial.mStandardMoltenFluid != null) {
//            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0L, new Object[0]), tMaterial.getMolten(72L), ItemList.IC2_Item_Casing_Bronze.get(1L, new Object[0]), 16, 8);
//        }
//        tMaterial = Materials.Copper;
//        if (tMaterial.mStandardMoltenFluid != null) {
//            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0L, new Object[0]), tMaterial.getMolten(72L), ItemList.IC2_Item_Casing_Copper.get(1L, new Object[0]), 16, 8);
//        }
//        tMaterial = Materials.AnnealedCopper;
//        if (tMaterial.mStandardMoltenFluid != null) {
//            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0L, new Object[0]), tMaterial.getMolten(72L), ItemList.IC2_Item_Casing_Copper.get(1L, new Object[0]), 16, 8);
//        }
//        tMaterial = Materials.Tin;
//        if (tMaterial.mStandardMoltenFluid != null) {
//            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0L, new Object[0]), tMaterial.getMolten(72L), ItemList.IC2_Item_Casing_Tin.get(1L, new Object[0]), 16, 8);
//        }
//        tMaterial = Materials.Lead;
//        if (tMaterial.mStandardMoltenFluid != null) {
//            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0L, new Object[0]), tMaterial.getMolten(72L), ItemList.IC2_Item_Casing_Lead.get(1L, new Object[0]), 16, 8);
//        }
//        tMaterial = Materials.Steel;
//        if (tMaterial.mStandardMoltenFluid != null) {
//            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Casing.get(0L, new Object[0]), tMaterial.getMolten(72L), ItemList.IC2_Item_Casing_Steel.get(1L, new Object[0]), 16, 8);
//        }
//
//        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("carbonFiber", 2L), ItemList.Circuit_Integrated.getWithDamage(0L, 2L, new Object[0]), GT_ModHandler.getIC2Item("carbonMesh", 1L), 800, 2);
//        GT_Values.RA.addAssemblerRecipe(ItemList.NC_SensorCard.getWildcard(1L, new Object[0]), ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), ItemList.Circuit_Basic.get(3L, new Object[0]), 1600, 2);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 4L), GT_ModHandler.getIC2Item("generator", 1L), GT_ModHandler.getIC2Item("waterMill", 2L), 6400, 8);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Magnalium, 2L), GT_ModHandler.getIC2Item("generator", 1L), GT_ModHandler.getIC2Item("windMill", 1L), 6400, 8);
//        if (!GT_Mod.gregtechproxy.mDisableIC2Cables) {
//            GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("tinCableItem", 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Rubber, 1L), GT_ModHandler.getIC2Item("insulatedTinCableItem", 1L), 100, 2);
//            GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("copperCableItem", 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Rubber, 1L), GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1L), 100, 2);
//            GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("goldCableItem", 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Rubber, 2L), GT_ModHandler.getIC2Item("insulatedGoldCableItem", 1L), 200, 2);
//            GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("ironCableItem", 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Rubber, 3L), GT_ModHandler.getIC2Item("insulatedIronCableItem", 1L), 300, 2);
//        }
//
//        GT_Values.RA.addFluidExtractionRecipe(ItemList.Crop_Drop_Indigo.get(1L, new Object[0]), GT_Values.NI, FluidRegistry.getFluidStack("indigo", 144), 10000, 128, 4);
//        GT_Values.RA.addFluidExtractionRecipe(ItemList.Crop_Drop_MilkWart.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Milk, 1L), GT_ModHandler.getMilk(150L), 1000, 128, 4);
//        GT_Values.RA.addFluidExtractionRecipe(ItemList.Crop_Drop_OilBerry.get(1L, new Object[0]), GT_Values.NI, Materials.Oil.getFluid(100L), 10000, 128, 4);
//        GT_Values.RA.addFluidExtractionRecipe(ItemList.Crop_Drop_UUMBerry.get(1L, new Object[0]), GT_Values.NI, Materials.UUMatter.getFluid(4L), 10000, 128, 4);
//        GT_Values.RA.addFluidExtractionRecipe(ItemList.Crop_Drop_UUABerry.get(1L, new Object[0]), GT_Values.NI, Materials.UUAmplifier.getFluid(4L), 10000, 128, 4);
//
//        GT_Values.RA.addPyrolyseRecipe(GT_ModHandler.getIC2Item("biochaff", 4), Materials.Water.getFluid(4000), 1, null, new FluidStack(FluidRegistry.getFluid("ic2biomass"), 5000), 900, 10);
//        GT_Values.RA.addCentrifugeRecipe(ItemList.IC2_Resin.get(1L, new Object[0]), GT_Values.NI, GT_Values.NF, Materials.Glue.getFluid(100L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.RawRubber, 3L), ItemList.IC2_Plantball.get(1L, new Object[0]), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{10000, 5000}, 300, 5);
//        GT_Values.RA.addCentrifugeRecipe(GT_Values.NI, GT_Values.NI, FluidRegistry.getFluidStack("ic2pahoehoelava", 100), GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Copper, 1L), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Tin, 1L), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Gold, 1L), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Silver, 1L), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Tantalum, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Tungstate, 1L), new int[]{2000, 1000, 250, 250, 250, 250}, 40, 80);
//        if (!GregTech_API.mIC2Classic) {
//            try {
//                Map<String, HeatExchangeProperty> tLiqExchange = ic2.api.recipe.Recipes.liquidCooldownManager.getHeatExchangeProperties();
//                Iterator<Map.Entry<String, HeatExchangeProperty>> tIterator = tLiqExchange.entrySet().iterator();
//                while (tIterator.hasNext()) {
//                    Map.Entry<String, HeatExchangeProperty> tEntry = tIterator.next();
//                    if (tEntry.getKey().equals("ic2hotcoolant")) {
//                        tIterator.remove();
//                        Recipes.liquidCooldownManager.addFluid("ic2hotcoolant", "ic2coolant", 100);
//                    }
//                }
//            } catch (Throwable e) {/*Do nothing*/}
//
//            try {
//                Map<String, HeatExchangeProperty> tLiqExchange = ic2.api.recipe.Recipes.liquidHeatupManager.getHeatExchangeProperties();
//                Iterator<Map.Entry<String, HeatExchangeProperty>> tIterator = tLiqExchange.entrySet().iterator();
//                while (tIterator.hasNext()) {
//                    Map.Entry<String, HeatExchangeProperty> tEntry = tIterator.next();
//                    if (tEntry.getKey().equals("ic2coolant")) {
//                        tIterator.remove();
//                        Recipes.liquidHeatupManager.addFluid("ic2coolant", "ic2hotcoolant", 100);
//                    }
//                }
//            } catch (Throwable e) {/*Do nothing*/}
//        }
//        GT_Utility.removeSimpleIC2MachineRecipe(ItemList.Crop_Drop_BobsYerUncleRanks.get(1L, new Object[0]), GT_ModHandler.getExtractorRecipeList(), null);
//        GT_Utility.removeSimpleIC2MachineRecipe(ItemList.Crop_Drop_Ferru.get(1L, new Object[0]), GT_ModHandler.getExtractorRecipeList(), null);
//        GT_Utility.removeSimpleIC2MachineRecipe(ItemList.Crop_Drop_Aurelia.get(1L, new Object[0]), GT_ModHandler.getExtractorRecipeList(), null);
//        GT_Utility.removeSimpleIC2MachineRecipe(new ItemStack(Blocks.cobblestone), GT_ModHandler.getMaceratorRecipeList(), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Stone, 1L));
//        GT_Utility.removeSimpleIC2MachineRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Lapis, 1L), GT_ModHandler.getMaceratorRecipeList(), ItemList.IC2_Plantball.get(1L, new Object[0]));
//        GT_Utility.removeSimpleIC2MachineRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), GT_ModHandler.getMaceratorRecipeList(), ItemList.IC2_Plantball.get(1L, new Object[0]));
//        GT_Utility.removeSimpleIC2MachineRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glowstone, 1L), GT_ModHandler.getMaceratorRecipeList(), ItemList.IC2_Plantball.get(1L, new Object[0]));
//
//
//        ItemStack tCrop;
//        // Metals Line
//        tCrop = ItemList.Crop_Drop_Coppon.get(1, new Object[0]);
//        addProcess(tCrop, Materials.Copper, 100, true);
//        addProcess(tCrop, Materials.Tetrahedrite, 100, false);
//        addProcess(tCrop, Materials.Chalcopyrite, 100, false);
//        addProcess(tCrop, Materials.Malachite, 100, false);
//        addProcess(tCrop, Materials.Pyrite, 100, false);
//        addProcess(tCrop, Materials.Stibnite, 100, false);
//        tCrop = ItemList.Crop_Drop_Tine.get(1, new Object[0]);
//        addProcess(tCrop, Materials.Tin, 100, true);
//        addProcess(tCrop, Materials.Cassiterite, 100, false);
//        tCrop = ItemList.Crop_Drop_Plumbilia.get(1, new Object[0]);
//        addProcess(tCrop, Materials.Lead, 100, true);
//        addProcess(tCrop, Materials.Galena, 100, false);
//        tCrop = ItemList.Crop_Drop_Ferru.get(1, new Object[0]);
//        addProcess(tCrop, Materials.Iron, 100, true);
//        addProcess(tCrop, Materials.Magnetite, 100, false);
//        addProcess(tCrop, Materials.BrownLimonite, 100, false);
//        addProcess(tCrop, Materials.YellowLimonite, 100, false);
//        addProcess(tCrop, Materials.VanadiumMagnetite, 100, false);
//        addProcess(tCrop, Materials.BandedIron, 100, false);
//        addProcess(tCrop, Materials.Pyrite, 100, false);
//        addProcess(tCrop, Materials.MeteoricIron, 100, false);
//        tCrop = ItemList.Crop_Drop_Nickel.get(1, new Object[0]);
//        addProcess(tCrop, Materials.Nickel, 100, true);
//        addProcess(tCrop, Materials.Garnierite, 100, false);
//        addProcess(tCrop, Materials.Pentlandite, 100, false);
//        addProcess(tCrop, Materials.Cobaltite, 100, false);
//        addProcess(tCrop, Materials.Wulfenite, 100, false);
//        addProcess(tCrop, Materials.Powellite, 100, false);
//        tCrop = ItemList.Crop_Drop_Zinc.get(1, new Object[0]);
//        addProcess(tCrop, Materials.Zinc, 100, true);
//        addProcess(tCrop, Materials.Sphalerite, 100, false);
//        addProcess(tCrop, Materials.Sulfur, 100, false);
//        tCrop = ItemList.Crop_Drop_Argentia.get(1, new Object[0]);
//        addProcess(tCrop, Materials.Silver, 100, true);
//        addProcess(tCrop, Materials.Galena, 100, false);
//        tCrop = ItemList.Crop_Drop_Aurelia.get(1, new Object[0]);
//        addProcess(tCrop, Materials.Gold, 100, true);
//        addProcess(tCrop, Materials.Magnetite, Materials.Gold, 100, false);
//
//        // Rare Metals Line
//        tCrop = ItemList.Crop_Drop_Bauxite.get(1, new Object[0]);
//        addProcess(tCrop,Materials.Aluminium,60, true);
//        addProcess(tCrop,Materials.Bauxite,100, false);
//        tCrop = ItemList.Crop_Drop_Manganese.get(1, new Object[0]);
//        addProcess(tCrop,Materials.Manganese,30, true);
//        addProcess(tCrop,Materials.Grossular,100, false);
//        addProcess(tCrop,Materials.Spessartine,100, false);
//        addProcess(tCrop,Materials.Pyrolusite,100, false);
//        addProcess(tCrop,Materials.Tantalite,100, false);
//        tCrop = ItemList.Crop_Drop_Ilmenite.get(1, new Object[0]);
//        addProcess(tCrop,Materials.Titanium,100, true);
//        addProcess(tCrop,Materials.Ilmenite,100, false);
//        addProcess(tCrop,Materials.Bauxite,100, false);
//        tCrop = ItemList.Crop_Drop_Scheelite.get(1, new Object[0]);
//        addProcess(tCrop,Materials.Scheelite,100, true);
//        addProcess(tCrop,Materials.Tungstate,100, false);
//        addProcess(tCrop,Materials.Lithium,100, false);
//        tCrop = ItemList.Crop_Drop_Platinum.get(1, new Object[0]);
//        addProcess(tCrop,Materials.Platinum,40, true);
//        addProcess(tCrop,Materials.Cooperite,40, false);
//        addProcess(tCrop,Materials.Palladium,40, false);
//        addProcess(tCrop, Materials.Neodymium, 100, false);
//        addProcess(tCrop, Materials.Bastnasite, 100, false);
//        tCrop = ItemList.Crop_Drop_Iridium.get(1, new Object[0]);
//        addProcess(tCrop,Materials.Iridium,20, true);
//        tCrop = ItemList.Crop_Drop_Osmium.get(1, new Object[0]);
//        addProcess(tCrop,Materials.Osmium,20, true);
//
//        // Radioactive Line
//        tCrop = ItemList.Crop_Drop_Pitchblende.get(1, new Object[0]);
//        addProcess(tCrop,Materials.Pitchblende,50, true);
//        tCrop = ItemList.Crop_Drop_Uraninite.get(1, new Object[0]);
//        addProcess(tCrop,Materials.Uraninite,50, false);
//        addProcess(tCrop,Materials.Uranium,50, true);
//        addProcess(tCrop,Materials.Pitchblende,50, false);
//        addProcess(tCrop,Materials.Uranium235,50, false);
//        tCrop = ItemList.Crop_Drop_Thorium.get(1, new Object[0]);
//        addProcess(tCrop,Materials.Thorium,50, true);
//        tCrop = ItemList.Crop_Drop_Naquadah.get(1, new Object[0]);
//        addProcess(tCrop,Materials.Naquadah,10, true);
//        addProcess(tCrop,Materials.NaquadahEnriched,10, false);
//        addProcess(tCrop,Materials.Naquadria,10, false);
//
//        //Gem Line
//        tCrop = ItemList.Crop_Drop_BobsYerUncleRanks.get(1, new Object[0]);
//        addProcess(tCrop, Materials.Emerald, 100, true);
//        addProcess(tCrop, Materials.Beryllium, 100, false);
//
//    }
//
//    public void addProcess(ItemStack tCrop, Materials aMaterial, int chance, boolean aMainOutput) {
//        if(tCrop==null||aMaterial==null||GT_OreDictUnificator.get(OrePrefixes.crushed, aMaterial,1)==null)return;
//        if (GT_Mod.gregtechproxy.mNerfedCrops) {
//            GT_Values.RA.addChemicalRecipe(GT_Utility.copyAmount(9, tCrop), GT_OreDictUnificator.get(OrePrefixes.crushed, aMaterial, 1), Materials.Water.getFluid(1000), aMaterial.mOreByProducts.isEmpty() ? null : aMaterial.mOreByProducts.get(0).getMolten(144), GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aMaterial, 4), 96, 24);
//            GT_Values.RA.addAutoclaveRecipe(GT_Utility.copyAmount(16, tCrop), Materials.UUMatter.getFluid(Math.max(1, ((aMaterial.getMass()+9)/10))), GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aMaterial, 1), 10000, (int) (aMaterial.getMass() * 128), 384);
//        } else {
//            if (aMainOutput) GT_ModHandler.addExtractionRecipe(tCrop, GT_OreDictUnificator.get(OrePrefixes.dustTiny, aMaterial, 1));
//        }
//    }
//
//    public void addProcess(ItemStack tCrop, Materials aMaterial, int chance){
//        if(tCrop==null||aMaterial==null||GT_OreDictUnificator.get(OrePrefixes.crushed, aMaterial,1)==null)return;
//        if (GT_Mod.gregtechproxy.mNerfedCrops) {
//            GT_Values.RA.addChemicalRecipe(GT_Utility.copyAmount(9, tCrop), GT_OreDictUnificator.get(OrePrefixes.crushed, aMaterial, 1), Materials.Water.getFluid(1000), aMaterial.mOreByProducts.isEmpty() ? null : aMaterial.mOreByProducts.get(0).getMolten(144), GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aMaterial, 4), 96, 24);
//            GT_Values.RA.addAutoclaveRecipe(GT_Utility.copyAmount(16, tCrop), Materials.UUMatter.getFluid(Math.max(1, ((aMaterial.getMass()+9)/10))), GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aMaterial, 1), 10000, (int) (aMaterial.getMass() * 128), 384);
//        } else {
//            GT_ModHandler.addExtractionRecipe(tCrop, GT_OreDictUnificator.get(OrePrefixes.dustTiny, aMaterial, 1));
//        }
//    }
//
//    public void addProcess(ItemStack tCrop, Materials aMaterial, Materials aMaterialOut, int chance, boolean aMainOutput){
//        if(tCrop==null||aMaterial==null||GT_OreDictUnificator.get(OrePrefixes.crushed, aMaterial,1)==null)return;
//        if (GT_Mod.gregtechproxy.mNerfedCrops) {
//            GT_Values.RA.addChemicalRecipe(GT_Utility.copyAmount(9, tCrop), GT_OreDictUnificator.get(OrePrefixes.crushed, aMaterial, 1), Materials.Water.getFluid(1000), aMaterialOut.mOreByProducts.isEmpty() ? null : aMaterialOut.mOreByProducts.get(0).getMolten(144), GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aMaterialOut, 4), 96, 24);
//            GT_Values.RA.addAutoclaveRecipe(GT_Utility.copyAmount(16, tCrop), Materials.UUMatter.getFluid(Math.max(1, ((aMaterial.getMass()+9)/10))), GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aMaterial, 1), 10000, (int) (aMaterial.getMass() * 128), 384);
//        } else {
//            if (aMainOutput) GT_ModHandler.addExtractionRecipe(tCrop, GT_OreDictUnificator.get(OrePrefixes.dustTiny, aMaterial, 1));
//        }
//    }
//
//    public void addProcess(ItemStack tCrop, Materials aMaterial, Materials aMaterialOut, int chance){
//        if(tCrop==null||aMaterial==null||GT_OreDictUnificator.get(OrePrefixes.crushed, aMaterial,1)==null)return;
//        if (GT_Mod.gregtechproxy.mNerfedCrops) {
//            GT_Values.RA.addChemicalRecipe(GT_Utility.copyAmount(9, tCrop), GT_OreDictUnificator.get(OrePrefixes.crushed, aMaterial, 1), Materials.Water.getFluid(1000), aMaterialOut.mOreByProducts.isEmpty() ? null : aMaterialOut.mOreByProducts.get(0).getMolten(144), GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aMaterialOut, 4), 96, 24);
//            GT_Values.RA.addAutoclaveRecipe(GT_Utility.copyAmount(16, tCrop), Materials.UUMatter.getFluid(Math.max(1, ((aMaterial.getMass()+9)/10))), GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aMaterial, 1), 10000, (int) (aMaterial.getMass() * 128), 384);
//        } else {
//            GT_ModHandler.addExtractionRecipe(tCrop, GT_OreDictUnificator.get(OrePrefixes.dustTiny, aMaterial, 1));
//        }
//    }
//}
