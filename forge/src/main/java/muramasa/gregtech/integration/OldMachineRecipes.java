//package muramasa.gregtech.integration;
//
//import net.minecraft.init.Blocks;
//import net.minecraft.init.Items;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.fluids.FluidRegistry;
//import net.minecraftforge.fluids.FluidStack;
//
//public class OldMachineRecipes {
//
//    public static void init() {

//        /** Metallurgy **/
//        OrePrefixes.ingot, ShadowIron, 1L), GT_Values.NI, Oxygen.getGas(1000L), GT_Values.NF, (OrePrefixes.ingot, ShadowSteel, 1L), (OrePrefixes.dustSmall, DarkAsh, 1L), 500, 120, 1100);
//
//        /** EnderIO **/
//        OrePrefixes.ingot, ElectricalSteel, 1L), (OrePrefixes.dust, Obsidian, 1L), GT_Values.NF, GT_Values.NF, (OrePrefixes.ingot, DarkSteel, 1L), GT_Values.NI, 4000, 120, 2000);
//
//        /** BuildCraft **/
//        GT_Values.RA.addFormingPressRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1L, 0), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1L, 1), 100, 120);
//        GT_Values.RA.addFormingPressRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.WroughtIron, 1L), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1L, 0), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1L, 1), 100, 120);
//        GT_Values.RA.addFormingPressRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Gold, 1L), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1L, 0), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1L, 2), 200, 120);
//        GT_Values.RA.addFormingPressRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Diamond, 1L), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1L, 0), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1L, 3), 100, 480);
//        GT_Values.RA.addFormingPressRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.EnderPearl, 1L), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1L, 0), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 2L, 4), 200, 120);
//        GT_Values.RA.addFormingPressRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.NetherQuartz, 1L), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1L, 0), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1L, 5), 300, 120);
//        GT_Values.RA.addFormingPressRecipe(new ItemStack(Items.comparator, 1, 32767), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1L, 0), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1L, 6), 300, 120);
//        for (int j = 0; j < Dyes.dyeRed.getSizeOfFluidList(); j++) {
//            GT_Values.RA.addChemicalBathRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.RedAlloy, 1L), Dyes.dyeRed.getFluidDye(j, 72L), GT_ModHandler.getModItem("BuildCraft|Transport", "pipeWire", 4L, 0), GT_Values.NI, GT_Values.NI, null, 32, 16);
//        }
//        for (int j = 0; j < Dyes.dyeBlue.getSizeOfFluidList(); j++) {
//            GT_Values.RA.addChemicalBathRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.RedAlloy, 1L), Dyes.dyeBlue.getFluidDye(j, 72L), GT_ModHandler.getModItem("BuildCraft|Transport", "pipeWire", 4L, 1), GT_Values.NI, GT_Values.NI, null, 32, 16);
//        }
//        for (int j = 0; j < Dyes.dyeGreen.getSizeOfFluidList(); j++) {
//            GT_Values.RA.addChemicalBathRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.RedAlloy, 1L), Dyes.dyeGreen.getFluidDye(j, 72L), GT_ModHandler.getModItem("BuildCraft|Transport", "pipeWire", 4L, 2), GT_Values.NI, GT_Values.NI, null, 32, 16);
//        }
//        for (int j = 0; j < Dyes.dyeYellow.getSizeOfFluidList(); j++) {
//            GT_Values.RA.addChemicalBathRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.RedAlloy, 1L), Dyes.dyeYellow.getFluidDye(j, 72L), GT_ModHandler.getModItem("BuildCraft|Transport", "pipeWire", 4L, 3), GT_Values.NI, GT_Values.NI, null, 32, 16);
//        }
//        GT_Values.RA.addCutterRecipe(GT_ModHandler.getModItem("BuildCraft|Transport", "item.buildcraftPipe.pipestructurecobblestone", 1L, 0), GT_ModHandler.getModItem("BuildCraft|Transport", "pipePlug", 8L, 0), GT_Values.NI, 32, 16);
//
//        /** RailCraft **/
//        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getModItem("Railcraft", "cube.crushed.obsidian", 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Obsidian, 1L), GT_Values.NI, 0, true);
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Aluminium, 6L), ItemList.RC_Rail_Standard.get(2L, new Object[0]), 200, 15);
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 6L), ItemList.RC_Rail_Standard.get(4L, new Object[0]), 400, 15);
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.WroughtIron, 6L), ItemList.RC_Rail_Standard.get(5L, new Object[0]), 400, 15);
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Bronze, 6L), ItemList.RC_Rail_Standard.get(3L, new Object[0]), 300, 15);
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 6L), ItemList.RC_Rail_Standard.get(8L, new Object[0]), 800, 15);
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.StainlessSteel, 6L), ItemList.RC_Rail_Standard.get(12L, new Object[0]), 1200, 15);
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Titanium, 6L), ItemList.RC_Rail_Standard.get(16L, new Object[0]), 1600, 15);
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.TungstenSteel, 6L), ItemList.RC_Rail_Reinforced.get(24L, new Object[0]), 2400, 30);
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Aluminium, 12L), ItemList.RC_Rebar.get(4L, new Object[0]), 200, 15);
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 12L), ItemList.RC_Rebar.get(8L, new Object[0]), 400, 15);
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.WroughtIron, 12L), ItemList.RC_Rebar.get(10L, new Object[0]), 400, 15);
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Bronze, 12L), ItemList.RC_Rebar.get(8L, new Object[0]), 400, 15);
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 12L), ItemList.RC_Rebar.get(16L, new Object[0]), 800, 15);
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.StainlessSteel, 12L), ItemList.RC_Rebar.get(24L, new Object[0]), 1200, 15);
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Titanium, 12L), ItemList.RC_Rebar.get(32L, new Object[0]), 1600, 15);
//        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.TungstenSteel, 12L), ItemList.RC_Rebar.get(48L, new Object[0]), 2400, 15);
//
//        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.stone_slab, 3, 0), ItemList.RC_Rebar.get(1L, new Object[0]), ItemList.RC_Tie_Stone.get(1L, new Object[0]), 128, 8);
//        GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.stone_slab, 3, 7), ItemList.RC_Rebar.get(1L, new Object[0]), ItemList.RC_Tie_Stone.get(1L, new Object[0]), 128, 8);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.Copper, 9L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Lead, 2L), GT_Values.NF, ItemList.RC_ShuntingWire.get(4L, new Object[0]), 1600, 4);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.AnnealedCopper, 9L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Lead, 2L), GT_Values.NF, ItemList.RC_ShuntingWire.get(4L, new Object[0]), 1600, 4);
//        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 3L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Gold, 3L), Materials.Blaze.getMolten(432L), ItemList.RC_Rail_HS.get(8L, new Object[0]), 400, 4);
//        GT_Values.RA.addAssemblerRecipe(ItemList.RC_Rail_Standard.get(3L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Gold, 3L), Materials.Redstone.getMolten(432L), ItemList.RC_Rail_Adv.get(8L, new Object[0]), 400, 4);
//        GT_Values.RA.addAssemblerRecipe(ItemList.RC_Rail_Standard.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.Copper, 1L), ItemList.RC_Rail_Electric.get(1L, new Object[0]), 50, 4);
//        GT_Values.RA.addAssemblerRecipe(ItemList.RC_Rail_Standard.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.AnnealedCopper, 1L), ItemList.RC_Rail_Electric.get(1L, new Object[0]), 50, 4);
//        GT_Values.RA.addAssemblerRecipe(ItemList.RC_Tie_Wood.get(6L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), ItemList.RC_Rail_Wooden.get(6L, new Object[0]), 400, 4);
//        GT_Values.RA.addAssemblerRecipe(ItemList.RC_Tie_Wood.get(6L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.WroughtIron, 1L), ItemList.RC_Rail_Wooden.get(6L, new Object[0]), 400, 4);
//        GT_Values.RA.addAssemblerRecipe(ItemList.RC_Tie_Wood.get(4L, new Object[0]), ItemList.Circuit_Integrated.getWithDamage(0L, 4L, new Object[0]), ItemList.RC_Bed_Wood.get(1L, new Object[0]), 200, 4);
//        GT_Values.RA.addAssemblerRecipe(ItemList.RC_Tie_Stone.get(4L, new Object[0]), ItemList.Circuit_Integrated.getWithDamage(0L, 4L, new Object[0]), ItemList.RC_Bed_Stone.get(1L, new Object[0]), 200, 4);
//        for (ItemStack tRail : new ItemStack[]{ItemList.RC_Rail_Standard.get(6L, new Object[0]), ItemList.RC_Rail_Adv.get(6L, new Object[0]), ItemList.RC_Rail_Reinforced.get(6L, new Object[0]), ItemList.RC_Rail_Electric.get(6L, new Object[0]), ItemList.RC_Rail_HS.get(6L, new Object[0]), ItemList.RC_Rail_Wooden.get(6L, new Object[0])}) {
//            for (ItemStack tBed : new ItemStack[]{ItemList.RC_Bed_Wood.get(1L, new Object[0]), ItemList.RC_Bed_Stone.get(1L, new Object[0])}) {
//                GT_Values.RA.addAssemblerRecipe(tBed, tRail, GT_ModHandler.getRecipeOutput(new ItemStack[]{tRail, GT_Values.NI, tRail, tRail, tBed, tRail, tRail, GT_Values.NI, tRail}), 400, 4);
//                GT_Values.RA.addAssemblerRecipe(tBed, tRail, Materials.Redstone.getMolten(144L), GT_ModHandler.getRecipeOutput(new ItemStack[]{tRail, GT_Values.NI, tRail, tRail, tBed, tRail, tRail, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), tRail}), 400, 4);
//                GT_Values.RA.addAssemblerRecipe(tBed, tRail, Materials.Redstone.getMolten(288L), GT_ModHandler.getRecipeOutput(new ItemStack[]{tRail, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), tRail, tRail, tBed, tRail, tRail, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), tRail}), 400, 4);
//            }
//        }
//        if (Utils.isModLoaded("Railcraft")) {
//            GT_Values.RA.addPyrolyseRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Coal, 16), null, 1, RailcraftToolItems.getCoalCoke(16), Materials.Creosote.getFluid(8000), 640, 64);
//            GT_Values.RA.addPyrolyseRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Coal, 16), Materials.Nitrogen.getGas(1000), 2, RailcraftToolItems.getCoalCoke(16), Materials.Creosote.getFluid(8000), 320, 96);
//            GT_Values.RA.addPyrolyseRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Coal, 8), null, 1, EnumCube.COKE_BLOCK.getItem(8), Materials.Creosote.getFluid(32000), 2560, 64);
//            GT_Values.RA.addPyrolyseRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Coal, 8), Materials.Nitrogen.getGas(1000), 2, EnumCube.COKE_BLOCK.getItem(8), Materials.Creosote.getFluid(32000), 1280, 96);
//        }
//        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tin, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter, 1L), Materials.Glass.getMolten(864L), GT_Values.NF, GT_ModHandler.getModItem("Railcraft", "tile.railcraft.glass", 6L), 50);
//
//        /** Twilight **/
//        GT_Values.RA.addCentrifugeRecipe(GT_ModHandler.getIC2Item("terraWart", 16L), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(1152L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
//        GT_Values.RA.addCentrifugeRecipe(GT_ModHandler.getModItem("TwilightForest", "item.meefRaw", 12L, 32767), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(1152L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
//        GT_Values.RA.addCentrifugeRecipe(GT_ModHandler.getModItem("TwilightForest", "item.meefSteak", 16L, 32767), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(1152L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
//        GT_Values.RA.addCentrifugeRecipe(GT_ModHandler.getModItem("TwilightForest", "item.venisonRaw", 12L, 32767), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(1152L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
//        GT_Values.RA.addCentrifugeRecipe(GT_ModHandler.getModItem("TwilightForest", "item.venisonCooked", 16L, 32767), GT_Values.NI, GT_Values.NF, Materials.Methane.getGas(1152L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, null, 4608, 5);
//        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem("TwilightForest", "item.charmOfLife1", 4L, 0), ItemList.Circuit_Integrated.getWithDamage(0L, 4L, new Object[0]), GT_Values.NF, GT_ModHandler.getModItem("TwilightForest", "item.charmOfLife2", 1L, 0), 100, 8);
//        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem("TwilightForest", "item.charmOfKeeping1", 4L, 0), ItemList.Circuit_Integrated.getWithDamage(0L, 4L, new Object[0]), GT_Values.NF, GT_ModHandler.getModItem("TwilightForest", "item.charmOfKeeping2", 1L, 0), 100, 8);
//        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem("TwilightForest", "item.charmOfKeeping2", 4L, 0), ItemList.Circuit_Integrated.getWithDamage(0L, 4L, new Object[0]), GT_Values.NF, GT_ModHandler.getModItem("TwilightForest", "item.charmOfKeeping3", 1L, 0), 100, 8);
//        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem("TwilightForest", "item.charmOfLife2", 1L, 0), ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), GT_Values.NF, GT_ModHandler.getModItem("TwilightForest", "item.charmOfLife1", 4L, 0), 100, 8);
//        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem("TwilightForest", "item.charmOfKeeping2", 1L, 0), ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), GT_Values.NF, GT_ModHandler.getModItem("TwilightForest", "item.charmOfKeeping1", 4L, 0), 100, 8);
//        GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getModItem("TwilightForest", "item.charmOfKeeping3", 1L, 0), ItemList.Circuit_Integrated.getWithDamage(0L, 1L, new Object[0]), GT_Values.NF, GT_ModHandler.getModItem("TwilightForest", "item.charmOfKeeping2", 4L, 0), 100, 8);
//
//        /** Magnaticraft **/
//        if (GregTech_API.mMagneticraft && GT_Mod.gregtechproxy.mMagneticraftRecipes) {
//            GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getModItem("Magneticraft", "item.ingotCarbide", 8));
//            GT_Values.RA.addAlloySmelterRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 8), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.WroughtIron, 1), GT_ModHandler.getModItem("Magneticraft", "item.ingotCarbide", 1), 600, 24);
//            GT_Values.RA.addBasicBlast(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 8), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.TungstenCarbide, 1), null, null, GT_ModHandler.getModItem("Magneticraft", "item.ingotCarbide", 8), null, 100, 120, 2600);
//            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.chunks", 1, 4));
//            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.pebbles", 1, 4));
//            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.rubble", 1, 4));
//            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.chunks", 1, 13));
//            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.pebbles", 1, 13));
//            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.rubble", 1, 13));
//            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.chunks", 1, 15));
//            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.pebbles", 1, 15));
//            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.rubble", 1, 15));
//            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.chunks", 1, 16));
//            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.pebbles", 1, 16));
//            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.rubble", 1, 16));
//            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.chunks", 1, 21));
//            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.pebbles", 1, 21));
//            GT_ModHandler.removeFurnaceSmelting(GT_ModHandler.getModItem("Magneticraft", "item.rubble", 1, 21));
//        }
//
//        /** Thaumcraft **/
//        GT_Values.RA.addFuel(GT_ModHandler.getModItem("Thaumcraft", "ItemShard", 1L, 6), null, 720, 5);
//        if (Utils.isModLoaded("Thaumcraft")) {
//            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(4, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedAir, 1L), null, null, Materials.FierySteel.getFluid(10), null, ItemList.MSFMixture.get(4, new Object[]{}), 100, 64);
//            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(4, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedEarth, 1L), null, null, Materials.FierySteel.getFluid(10), null, ItemList.MSFMixture.get(4, new Object[]{}), 100, 64);
//            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(4, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedEntropy, 1L), null, null, Materials.FierySteel.getFluid(10), null, ItemList.MSFMixture.get(4, new Object[]{}), 100, 64);
//            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(4, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedFire, 1L), null, null, Materials.FierySteel.getFluid(10), null, ItemList.MSFMixture.get(4, new Object[]{}), 100, 64);
//            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(4, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedOrder, 1L), null, null, Materials.FierySteel.getFluid(10), null, ItemList.MSFMixture.get(4, new Object[]{}), 100, 64);
//            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(4, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedWater, 1L), null, null, Materials.FierySteel.getFluid(10), null, ItemList.MSFMixture.get(4, new Object[]{}), 100, 64);
//
//            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(2, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedAir, 1L), null, null, Materials.Mercury.getFluid(50), null, ItemList.MSFMixture.get(2, new Object[]{}), 100, 64);
//            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(2, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedEarth, 1L), null, null, Materials.Mercury.getFluid(50), null, ItemList.MSFMixture.get(2, new Object[]{}), 100, 64);
//            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(2, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedEntropy, 1L), null, null, Materials.Mercury.getFluid(50), null, ItemList.MSFMixture.get(2, new Object[]{}), 100, 64);
//            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(2, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedFire, 1L), null, null, Materials.Mercury.getFluid(50), null, ItemList.MSFMixture.get(2, new Object[]{}), 100, 64);
//            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(2, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedOrder, 1L), null, null, Materials.Mercury.getFluid(50), null, ItemList.MSFMixture.get(2, new Object[]{}), 100, 64);
//            GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(2, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedWater, 1L), null, null, Materials.Mercury.getFluid(50), null, ItemList.MSFMixture.get(2, new Object[]{}), 100, 64);
//
//            FluidStack tFD = FluidRegistry.getFluidStack("fluiddeath", 10);
//            if (tFD != null && tFD.getFluid() != null && tFD.amount > 0) {
//
//                GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(8, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedAir, 1L), null, null, tFD, null, ItemList.MSFMixture.get(8, new Object[]{}), 100, 64);
//                GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(8, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedEarth, 1L), null, null, tFD, null, ItemList.MSFMixture.get(8, new Object[]{}), 100, 64);
//                GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(8, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedEntropy, 1L), null, null, tFD, null, ItemList.MSFMixture.get(8, new Object[]{}), 100, 64);
//                GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(8, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedFire, 1L), null, null, tFD, null, ItemList.MSFMixture.get(8, new Object[]{}), 100, 64);
//                GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(8, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedOrder, 1L), null, null, tFD, null, ItemList.MSFMixture.get(8, new Object[]{}), 100, 64);
//                GT_Values.RA.addMixerRecipe(ItemList.SFMixture.get(8, new Object[]{}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.InfusedWater, 1L), null, null, tFD, null, ItemList.MSFMixture.get(8, new Object[]{}), 100, 64);
//
//                GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.MSFMixture.get(6, new Object[]{}), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.NitroFuel.getFluid(1000), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//                GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.MSFMixture.get(4, new Object[]{}), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.NitroFuel.getFluid(800), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//                GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Coal, 1), ItemList.MSFMixture.get(2, new Object[]{}), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.NitroFuel.getFluid(500), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//
//                GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.MSFMixture.get(6, new Object[]{}), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.HeavyFuel.getFluid(1500), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//                GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.MSFMixture.get(4, new Object[]{}), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.HeavyFuel.getFluid(1200), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//                GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Coal, 1), ItemList.MSFMixture.get(2, new Object[]{}), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.HeavyFuel.getFluid(750), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//
//
//                GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Lignite, 1), ItemList.MSFMixture.get(6, new Object[]{}), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.LPG.getFluid(1500), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//                GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Charcoal, 1), ItemList.MSFMixture.get(4, new Object[]{}), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.LPG.getFluid(1200), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//                GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Coal, 1), ItemList.MSFMixture.get(2, new Object[]{}), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 4), null, Materials.LPG.getFluid(750), null, ItemList.Block_MSSFUEL.get(1, new Object[]{}), 120, 96);
//            }
//        }
//
//        /** Forbidden Magic **/
//        GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "GluttonyShard", 1L), null, 720, 5);
//        GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "FMResource", 1L, 3), null, 720, 5);
//        GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "NetherShard", 1L), null, 720, 5);
//        GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "NetherShard", 1L, 1), null, 720, 5);
//        GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "NetherShard", 1L, 2), null, 720, 5);
//        GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "NetherShard", 1L, 3), null, 720, 5);
//        GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "NetherShard", 1L, 4), null, 720, 5);
//        GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "NetherShard", 1L, 5), null, 720, 5);
//        GT_Values.RA.addFuel(GT_ModHandler.getModItem("ForbiddenMagic", "NetherShard", 1L, 6), null, 720, 5);
//
//        /** Tainted Magic **/
//        GT_Values.RA.addFuel(GT_ModHandler.getModItem("TaintedMagic", "WarpedShard", 1L), null, 720, 5);
//        GT_Values.RA.addFuel(GT_ModHandler.getModItem("TaintedMagic", "FluxShard", 1L), null, 720, 5);
//        GT_Values.RA.addFuel(GT_ModHandler.getModItem("TaintedMagic", "EldritchShard", 1L), null, 720, 5);
//
//        /** Thaumic Tinkerer **/
//        GT_Values.RA.addFuel(GT_ModHandler.getModItem("ThaumicTinkerer", "kamiResource", 1L, 6), null, 720, 5);
//        GT_Values.RA.addFuel(GT_ModHandler.getModItem("ThaumicTinkerer", "kamiResource", 1L, 7), null, 720, 5);
//
//        /** Hardcore Ender Expansion **/
//        GT_Values.RA.addForgeHammerRecipe(GT_ModHandler.getModItem("HardcoreEnderExpansion", "endium_ore", 1), GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Endium, 1), 16, 10);
//        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getModItem("HardcoreEnderExpansion", "endium_ore", 1), GT_OreDictUnificator.get(OrePrefixes.crushed, Materials.Endium, 2), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Endstone, 1), 50, GT_Values.NI, 0, true);
//        GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.Endium, GT_ModHandler.getModItem("HardcoreEnderExpansion", "endium_ingot", 1), true, true);
//    }
//}
