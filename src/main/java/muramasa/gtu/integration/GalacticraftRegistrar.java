//package muramasa.gtu.integration;
//
//import muramasa.gtu.Ref;
//import muramasa.antimatter.blocks.BlockStorage;
//import muramasa.gtu.data.Materials;
//import muramasa.antimatter.materials.Material;
//import muramasa.antimatter.materials.MaterialTag;
//import muramasa.antimatter.materials.MaterialType;
//import muramasa.antimatter.registration.IGregTechRegistrar;
//import muramasa.antimatter.registration.RegistrationEvent;
//import net.minecraftforge.fml.ModList;
//
//import static muramasa.antimatter.materials.MaterialType.ORE;
//import static muramasa.antimatter.materials.TextureSet.METALLIC;
//
//public class GalacticraftRegistrar implements IGregTechRegistrar {
//
//    public static Material MeteoricIron, MeteoricSteel;
//    public static BlockStorage GC_BLOCK_1;
//
//    @Override
//    public String getId() {
//        return Ref.MOD_GC;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return ModList.get().isLoaded(Ref.MOD_GC) && ModList.get().isLoaded(Ref.MOD_GC_PLANETS);
//    }
//
//
//    @Override
//    public void onRegistrationEvent(RegistrationEvent event) {
//        switch (event) {
//            case MATERIAL:
//                MeteoricIron = new Material("meteoric_iron", 0x643250, METALLIC).asMetal(1811, 0, ORE).addTools(6.0f, 384, 2);
//                MeteoricSteel = new Material("meteoric_steel", 0x321928, METALLIC).asMetal(1811, 1000).addTools(6.0f, 768, 2);
//                MaterialTag.CALCITE3X.add(MeteoricIron);
//                MeteoricIron.addByProduct(Materials.Iron);
//                break;
//            case DATA:
//                GC_BLOCK_1 = new BlockStorage("gc_0", MaterialType.BLOCK, MeteoricIron, MeteoricSteel);
//                break;
//            case RECIPE:
//                init();
//                break;
//        }
//    }
//
//    public void init() {
//        //IMPLOSION_COMPRESSING.RB().ii(MeteoricIron.getPlate(1))
//        //RecipeAdder.addImplosionRecipe(GT_Utility.ca(m == Materials.MeteoricIron ? 1 : 2, aPlateStack), 2, Unifier.get(Prefix.compressed, m), Unifier.get(Prefix.dustTiny, Materials.DarkAsh, 1));
//    }
//
////    @Override
////    public void onMachineRecipeRegistration() {
//        /*if (Utils.isModLoaded("GalacticraftMars")) {
//            GT_ModHandler.addCraftingRecipe(ItemList.Ingot_Heavy1.get(1L, new Object[0]), GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{"BhB", "CAS", "B B", 'B', OrePrefixes.bolt.get(Materials.StainlessSteel), 'C', OrePrefixes.compressed.get(Materials.Bronze), 'A', OrePrefixes.compressed.get(Materials.Aluminium), 'S', OrePrefixes.compressed.get(Materials.Steel)});
//            GT_ModHandler.addCraftingRecipe(ItemList.Ingot_Heavy2.get(1L, new Object[0]), GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{" BB", "hPC", " BB", 'B', OrePrefixes.bolt.get(Materials.Tungsten), 'C', OrePrefixes.compressed.get(Materials.MeteoricIron), 'P', GT_ModHandler.getModItem("GalacticraftCore", "item.heavyPlating", 1L)});
//            GT_ModHandler.addCraftingRecipe(ItemList.Ingot_Heavy3.get(1L, new Object[0]), GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{" BB", "hPC", " BB", 'B', OrePrefixes.bolt.get(Materials.TungstenSteel), 'C', OrePrefixes.compressed.get(Materials.Desh), 'P', GT_ModHandler.getModItem("GalacticraftMars", "item.null", 1L, 3)});
//
//            GT_Values.RA.addImplosionRecipe(ItemList.Ingot_Heavy1.get(1L, new Object[0]), 8, GT_ModHandler.getModItem("GalacticraftCore", "item.heavyPlating", 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.StainlessSteel, 2L));
//            GT_Values.RA.addImplosionRecipe(ItemList.Ingot_Heavy2.get(1L, new Object[0]), 8, GT_ModHandler.getModItem("GalacticraftMars", "item.null", 1L, 3), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tungsten, 2L));
//            GT_Values.RA.addImplosionRecipe(ItemList.Ingot_Heavy3.get(1L, new Object[0]), 8, GT_ModHandler.getModItem("GalacticraftMars", "item.itemBasicAsteroids", 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.TungstenSteel, 2L));
//
//            GT_Values.RA.addCentrifugeRecipe(GT_ModHandler.getModItem("GalacticraftCore", "tile.moonBlock", 1L, 5), null, null, Materials.Helium_3.getGas(33), new ItemStack(Blocks.sand, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Aluminium, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Magnesium, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Rutile, 1), new int[]{5000, 400, 400, 100, 100, 100}, 400, 8);
//            GT_Values.RA.addPulveriserRecipe(GT_ModHandler.getModItem("GalacticraftCore", "tile.moonBlock", 1L, 4), new ItemStack[]{GT_ModHandler.getModItem("GalacticraftCore", "tile.moonBlock", 1L, 5)}, null, 400, 2);
//            GT_Values.RA.addFluidExtractionRecipe(GT_ModHandler.getModItem("GalacticraftMars", "tile.mars", 1L, 9), new ItemStack(Blocks.stone, 1), Materials.Iron.getMolten(50), 10000, 250, 16);
//            GT_Values.RA.addPulveriserRecipe(GT_ModHandler.getModItem("GalacticraftMars", "tile.asteroidsBlock", 1L, 1), new ItemStack[]{GT_ModHandler.getModItem("GalacticraftMars", "tile.asteroidsBlock", 1L, 0)}, null, 400, 2);
//            GT_Values.RA.addPulveriserRecipe(GT_ModHandler.getModItem("GalacticraftMars", "tile.asteroidsBlock", 1L, 2), new ItemStack[]{GT_ModHandler.getModItem("GalacticraftMars", "tile.asteroidsBlock", 1L, 0)}, null, 400, 2);
//            GT_Values.RA.addCentrifugeRecipe(GT_ModHandler.getModItem("GalacticraftMars", "tile.asteroidsBlock", 1L, 0), null, null, Materials.Nitrogen.getGas(33), new ItemStack(Blocks.sand, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Aluminium, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gallium, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Platinum, 1), new int[]{5000, 400, 400, 100, 100, 100}, 400, 8);
//        }*/
////        OrePrefixes.ingot, MeteoricIron, 1L), GT_Values.NI, Oxygen.getGas(1000L), GT_Values.NF, (OrePrefixes.ingot, MeteoricSteel, 1L), (OrePrefixes.dustSmall, DarkAsh, 1L), 500, 120, 1200);
////    }
//}
