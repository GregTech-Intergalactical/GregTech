package muramasa.gti.loader;

import muramasa.antimatter.recipe.RecipeBuilder;

import static muramasa.gti.data.Materials.*;
import static muramasa.gti.data.RecipeMaps.FUSION;

public class MachineRecipeLoader {

    public static RecipeBuilder RB = new RecipeBuilder();

    public static void init() {

       // ASSEMBLING.RB().ii(AntimatterIngredient.fromTag(ItemTags.LOGS,2), AntimatterIngredient.fromTag(ItemTags.ANVIL,1), AntimatterIngredient.of(Items.ACACIA_DOOR,2)).io(new ItemStack(Items.DIAMOND,1)).add(60,16);
     //   BLASTING.RB().ii(AntimatterIngredient.fromTag(ItemTags.LOGS,2), AntimatterIngredient.of(Items.ACACIA_DOOR,2)).io(new ItemStack(Items.DIAMOND,1)).add(60,16);
    //    ASSEMBLING.RB().ii(AntimatterIngredient.fromTag(ItemTags.LOGS,2), AntimatterIngredient.of(Items.ACACIA_DOOR,2)).io(new ItemStack(Items.DIAMOND,1)).add(60,16);

        //TEMP
//        STEAM_FUELS.RB().fi(Steam.getGas(2)).add(1,1);
//
//        LATHING.RB().ii(PLATE.get(Glass, 1)).io(LENS.get(Glass, 1), DUST_SMALL.get(Glass, 1)).add(20, 12);
//
//        //OreDictionary.getOres("logWood").forEach(i -> COKING.RB().ii(Utils.ca(2, i)).io(Charcoal.getGem(1)).fo(Creosote.getLiquid(250)).add(3600)); //Coal?
//        COKING.RB().ii(GEM.get(Coal, 1)).io(GEM.get(CoalCoke, 1)).fo(Creosote.getLiquid(500)).add(3600);
//        COKING.RB().ii(GEM.get(Lignite, 1)).io(GEM.get(LigniteCoke, 1)).fo(Creosote.getLiquid(750)).add(3600);
//
//        //Add Basic blasting for mixed metals
//        Data.DUST.all().forEach(m -> {
//            if (m.getDirectSmeltInto() != m && !m.has(NOSMELT) && !(m.needsBlastFurnace() || m.getDirectSmeltInto().needsBlastFurnace()) && !m.has(NOBBF)) {
//                if (m.getDirectSmeltInto().has(INGOT)) { //TODO INGOT check was added to avoid DOES NOT GENERATE: P(INGOT) M(mercury)
//                    BASIC_BLASTING.RB().add(new ItemStack[]{DUST.get(m, 2)}, new ItemStack[]{INGOT.get(m.getDirectSmeltInto(), MaterialRecipeLoader.mixedOreYield)}, 2, 2400);
//                }
//            }
//        });
//
//        SMALL_BOILERS.RB().ii(GEM.get(Coal, 1)).io(DUST_TINY.get(DarkAsh, 1)).fo(Steam.getGas(150)).chances(33).add(10);
//        SMALL_BOILERS.RB().ii(GEM.get(Charcoal, 1)).io(DUST_TINY.get(DarkAsh, 1)).fo(Steam.getGas(150)).chances(33).add(10);
//        SMALL_BOILERS.RB().ii(GEM.get(Lignite, 1)).io(DUST_TINY.get(DarkAsh, 1)).fo(Steam.getGas(150)).chances(15).add(10);
//        SMALL_BOILERS.RB().ii(GEM.get(CoalCoke, 1)).io(DUST_TINY.get(DarkAsh, 1)).fo(Steam.getGas(150)).chances(50).add(10);
//        SMALL_BOILERS.RB().ii(GEM.get(LigniteCoke, 1)).io(DUST_TINY.get(DarkAsh, 1)).fo(Steam.getGas(150)).chances(40).add(10);
//
//        /** Temp Testing Recipes **/ //TODO remove
//        COMBUSTION_FUELS.RB().fi(Diesel.getLiquid(1)).fo(CarbonDioxide.getGas(1)).add(1, 0, 1024);
//        COMBUSTION_FUELS.RB().fi(Titanium.getLiquid(1999)).fo(CarbonDioxide.getGas(2000)).add(1, 0, 1024);
//        COMBUSTION_FUELS.RB().fi(Osmium.getLiquid(2001)).fo(CarbonDioxide.getGas(3000)).add(1, 0, 1024);
//
//        //How the hell does empty stacks even get into smeltingList?!
//        //FurnaceRecipes.instance().getSmeltingList().entrySet().stream().filter((set) -> !set.getKey().isEmpty()).forEach((set) -> SMELTING.RB().ii(set.getKey()).io(set.getValue()).add(140, 2));
//
//        IMPLOSION_COMPRESSING.RB().ii(GregTechData.IridiumAlloyIngot.get(1)).io(GregTechData.IridiumReinforcedPlate.get(1), DUST_TINY.get(DarkAsh, 4)).add(20, 30);
//
//        BLASTING.RB().ii(INGOT.get(Tungsten, 1), INGOT.get(Steel, 1)).io(INGOT_HOT.get(TungstenSteel, 2), DUST_SMALL.get(DarkAsh, 1)).add(Math.max(TungstenSteel.getMass() / 80L, 1L) * TungstenSteel.getBlastTemp(), 480, TungstenSteel.getBlastTemp());
//        BLASTING.RB().ii(INGOT.get(Tungsten, 1), DUST.get(Carbon, 1)).io(INGOT_HOT.get(TungstenCarbide, 1), DUST_SMALL.get(Ash, 2)).add(Math.max(TungstenCarbide.getMass() / 40L, 1L) * TungstenCarbide.getBlastTemp(), 480, TungstenCarbide.getBlastTemp());
//        BLASTING.RB().ii(INGOT.get(Vanadium, 3), INGOT.get(Gallium, 1)).io(INGOT_HOT.get(VanadiumGallium, 4), DUST_SMALL.get(DarkAsh, 2)).add(Math.max(VanadiumGallium.getMass() / 40L, 1L) * VanadiumGallium.getBlastTemp(), 480, VanadiumGallium.getBlastTemp());
//        BLASTING.RB().ii(INGOT.get(Niobium, 1), INGOT.get(Titanium, 1)).io(INGOT_HOT.get(NiobiumTitanium, 2), DUST_SMALL.get(DarkAsh, 1)).add(Math.max(NiobiumTitanium.getMass() / 80L, 1L) * NiobiumTitanium.getBlastTemp(), 480, NiobiumTitanium.getBlastTemp());
//        BLASTING.RB().ii(INGOT.get(Nickel, 4), INGOT.get(Chrome, 1)).io(INGOT_HOT.get(Nichrome, 5), DUST_SMALL.get(DarkAsh, 2)).add(Math.max(Nichrome.getMass() / 32L, 1L) * Nichrome.getBlastTemp(), 480, Nichrome.getBlastTemp());
//        BLASTING.RB().ii(DUST.get(Ruby, 1)).io(NUGGET.get(Aluminium, 3), DUST_TINY.get(DarkAsh, 1)).add(400, 100, 1200);
//        BLASTING.RB().ii(GEM.get(Ruby, 1)).io(NUGGET.get(Aluminium, 3),DUST_TINY.get(DarkAsh, 1)).add(320, 100, 1200);
//        //RB.get(BLASTING).ii(GreenSapphire.getDust(1)).io(Aluminium.getNugget(3), DarkAsh.getDustTiny(1)).add(400, 100, 1200);
//        //RB.get(BLASTING).ii(GreenSapphire.getGem(1)).io(Aluminium.getNugget(3), DarkAsh.getDustTiny(1)).add(320, 100, 1200);
//        BLASTING.RB().ii(DUST.get(BlueSapphire, 1)).io(NUGGET.get(Aluminium, 3)).add(400, 100, 1200);
//        BLASTING.RB().ii(GEM.get(BlueSapphire, 1)).io(NUGGET.get(Aluminium, 3)).add(320, 100, 1200);
//        BLASTING.RB().ii(DUST.get(Ilmenite, 1), DUST.get(Carbon, 1)).io(NUGGET.get(WroughtIron, 4), DUST_TINY.get(Rutile, 4)).add(800, 500, 1700);
//        BLASTING.RB().ii(DUST.get(Magnesium, 2)).fi(Titaniumtetrachloride.getLiquid(1000)).io(INGOT_HOT.get(Titanium, 1), DUST.get(MagnesiumChloride, 2)).add(800, 480, Titanium.getBlastTemp() + 200);
//
//        BLASTING.RB().ii(DUST.get(Galena, 1)).fi(Oxygen.getGas(2000)).io(NUGGET.get(Silver, 4), NUGGET.get(Lead, 4)).add(400, 500, 1500);
//        BLASTING.RB().ii(DUST.get(Magnetite, 1)).fi(Oxygen.getGas(2000)).io(NUGGET.get(WroughtIron, 4), DUST_SMALL.get(DarkAsh, 1)).add(400, 500, 1000);
//        BLASTING.RB().ii(INGOT.get(Iron, 1)).fi(Oxygen.getGas(1000)).io(INGOT.get(Steel, 1), DUST_SMALL.get(DarkAsh, 1)).add(500, 120, 1000);
//        BLASTING.RB().ii(INGOT.get(WroughtIron, 1)).fi(Oxygen.getGas(1000)).io(INGOT.get(Steel, 1), DUST_SMALL.get(DarkAsh, 1)).add(100, 120, 1000);
//        BLASTING.RB().ii(DUST.get(Copper, 1)).fi(Oxygen.getGas(1000)).io(INGOT.get(AnnealedCopper, 1)).add(500, 120, 1200);
//        BLASTING.RB().ii(INGOT.get(Copper, 1)).fi(Oxygen.getGas(1000)).io(INGOT.get(AnnealedCopper, 1)).add(500, 120, 1200);
//        BLASTING.RB().ii(INGOT.get(Iridium, 3), INGOT.get(Osmium, 1)).fi(Helium.getGas(1000)).io(INGOT_HOT.get(Osmiridium, 4)).add(500, 1920, 2900);
//        BLASTING.RB().ii(INGOT.get(Naquadah, 1), INGOT.get(Osmiridium, 1)).fi(Argon.getGas(1000)).io(INGOT_HOT.get(NaquadahAlloy, 2)).add(500, 30720, NaquadahAlloy.getBlastTemp());

        /** FUSION AGE **/

        //Power Gen Recipes
        FUSION.RB().fi(Deuterium.getGas(125), Tritium.getGas(125)).fo(Helium.getPlasma(125)).add(16, 4096, 40000000); //Mark 1 Cheap
        FUSION.RB().fi(Deuterium.getGas(125), Helium3.getGas(125)).fo(Helium.getPlasma(125)).add(16, 2048, 60000000); //Mark 1 Expensive
        FUSION.RB().fi(Carbon.getLiquid(125), Helium3.getGas(125)).fo(Oxygen.getPlasma(125)).add(32, 4096, 80000000); //Mark 1 Expensive
        FUSION.RB().fi(Aluminium.getLiquid(16), Lithium.getLiquid(125)).fo(Sulfur.getPlasma(125)).add(32, 10240, 240000000); //Mark 2 Cheap
        FUSION.RB().fi(Beryllium.getLiquid(16), Deuterium.getGas(375)).fo(Nitrogen.getPlasma(175)).add(16, 16384, 180000000); //Mark 2 Expensive
        FUSION.RB().fi(Silicon.getLiquid(16), Magnesium.getLiquid(16)).fo(Iron.getPlasma(125)).add(32, 8192, 360000000); //Mark 3 Cheap
        FUSION.RB().fi(Potassium.getLiquid(16), Fluorine.getGas(125)).fo(Nickel.getPlasma(125)).add(16, 32768, 480000000); //Mark 3 Expensive

        //Material Gen Recipes
        FUSION.RB().fi(Beryllium.getLiquid(16), Tungsten.getLiquid(16)).fo(Platinum.getLiquid(16)).add(32, 32768, 150000000);
        FUSION.RB().fi(Neodymium.getLiquid(16), Hydrogen.getGas(16)).fo(Europium.getLiquid(16)).add(64, 24576, 150000000);
        FUSION.RB().fi(Lutetium.getLiquid(16), Chrome.getLiquid(16)).fo(Americium.getLiquid(16)).add(96, 49152, 200000000);
        FUSION.RB().fi(Plutonium.getLiquid(16), Thorium.getLiquid(16)).fo(Naquadah.getLiquid(16)).add(64, 32768, 300000000);
        FUSION.RB().fi(Americium.getLiquid(16), Naquadria.getLiquid(16)).fo(Neutronium.getLiquid(1)).add(1200, 98304, 600000000);
        FUSION.RB().fi(Lithium.getLiquid(16), Tungsten.getLiquid(16), Iridium.getLiquid(16)).add(32, 32768, 300000000);
        FUSION.RB().fi(Tungsten.getLiquid(16), Helium.getGas(16)).fo(Osmium.getLiquid(16)).add(64, 24578, 150000000);
        FUSION.RB().fi(Manganese.getLiquid(16), Hydrogen.getGas(16)).fo(Iron.getLiquid(16)).add(64, 8192, 120000000);
        FUSION.RB().fi(Mercury.getLiquid(16), Magnesium.getLiquid(16)).fo(Uranium.getLiquid(16)).add(64, 49152, 240000000);
        FUSION.RB().fi(Gold.getLiquid(16), Aluminium.getLiquid(16)).fo(Uranium.getLiquid(16)).add(64, 49152, 240000000);
        FUSION.RB().fi(Uranium.getLiquid(16), Helium.getGas(16)).fo(Plutonium.getLiquid(16)).add(128, 49152, 480000000);
        FUSION.RB().fi(Vanadium.getLiquid(16), Hydrogen.getGas(125)).fo(Chrome.getLiquid(16)).add(64, 24576, 140000000);
        FUSION.RB().fi(Gallium.getLiquid(16), Radon.getGas(125)).fo(Duranium.getLiquid(16)).add(64, 16384, 140000000);
        FUSION.RB().fi(Titanium.getLiquid(16), Duranium.getLiquid(125)).fo(Tritanium.getLiquid(16)).add(64, 32768, 200000000);
        FUSION.RB().fi(Gold.getLiquid(16), Mercury.getLiquid(16)).fo(Radon.getGas(125)).add(64, 32768, 200000000);
        FUSION.RB().fi(EnrichedNaquadah.getLiquid(15), Radon.getGas(125)).fo(Naquadria.getLiquid(3)).add(64, 49152, 400000000);
    }
}
