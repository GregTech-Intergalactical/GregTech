package muramasa.gtu.loaders;

import muramasa.antimatter.materials.MaterialType;
import muramasa.antimatter.recipe.RecipeBuilder;
import muramasa.gtu.common.Data;
import net.minecraft.item.ItemStack;

import static muramasa.gtu.data.Materials.*;
import static muramasa.gtu.data.RecipeMaps.*;
import static muramasa.antimatter.materials.MaterialTag.NOBBF;
import static muramasa.antimatter.materials.MaterialTag.NOSMELT;
import static muramasa.antimatter.materials.MaterialType.INGOT;

public class MachineRecipeLoader {

    public static RecipeBuilder RB = new RecipeBuilder();

    public static void init() {
        
        //TODO: Glass processing recipes here
        LATHING.RB().ii(Glass.getPlate(1)).io(Glass.getLens(1), Glass.getDustSmall(1)).add(20, 12);

        //OreDictionary.getOres("logWood").forEach(i -> COKING.RB().ii(Utils.ca(2, i)).io(Charcoal.getGem(1)).fo(Creosote.getLiquid(250)).add(3600)); //Coal?
        COKING.RB().ii(Coal.getGem(1)).io(CoalCoke.getGem(1)).fo(Creosote.getLiquid(500)).add(3600);
        COKING.RB().ii(Lignite.getGem(1)).io(LigniteCoke.getGem(1)).fo(Creosote.getLiquid(750)).add(3600);

        //Add Basic blasting for mixed metals
        MaterialType.DUST.all().forEach(m -> {
            if (m.getDirectSmeltInto() != m && !m.has(NOSMELT) && !(m.needsBlastFurnace() || m.getDirectSmeltInto().needsBlastFurnace()) && !m.has(NOBBF)) {
                if (m.getDirectSmeltInto().has(INGOT)) { //TODO INGOT check was added to avoid DOES NOT GENERATE: P(INGOT) M(mercury)
                    BASIC_BLASTING.RB().add(new ItemStack[]{m.getDust(2)}, new ItemStack[]{m.getDirectSmeltInto().getIngot(MaterialRecipeLoader.mixedOreYield)}, 2, 2400);
                }
            }
        });

        SMALL_BOILERS.RB().ii(Coal.getGem(1)).io(DarkAsh.getDustTiny(1)).fo(Steam.getGas(150)).chances(33).add(10);
        SMALL_BOILERS.RB().ii(Charcoal.getGem(1)).io(DarkAsh.getDustTiny(1)).fo(Steam.getGas(150)).chances(33).add(10);
        SMALL_BOILERS.RB().ii(Lignite.getGem(1)).io(DarkAsh.getDustTiny(1)).fo(Steam.getGas(150)).chances(15).add(10);
        SMALL_BOILERS.RB().ii(CoalCoke.getGem(1)).io(DarkAsh.getDustTiny(1)).fo(Steam.getGas(150)).chances(50).add(10);
        SMALL_BOILERS.RB().ii(LigniteCoke.getGem(1)).io(DarkAsh.getDustTiny(1)).fo(Steam.getGas(150)).chances(40).add(10);

        /** Temp Testing Recipes **/ //TODO remove
        COMBUSTION_FUELS.RB().fi(Diesel.getLiquid(1)).fo(CarbonDioxide.getGas(1)).add(1, 0, 1024);
        COMBUSTION_FUELS.RB().fi(Titanium.getLiquid(1999)).fo(CarbonDioxide.getGas(2000)).add(1, 0, 1024);
        COMBUSTION_FUELS.RB().fi(Osmium.getLiquid(2001)).fo(CarbonDioxide.getGas(3000)).add(1, 0, 1024);

        //How the hell does empty stacks even get into smeltingList?!
        //FurnaceRecipes.instance().getSmeltingList().entrySet().stream().filter((set) -> !set.getKey().isEmpty()).forEach((set) -> SMELTING.RB().ii(set.getKey()).io(set.getValue()).add(140, 2));

        IMPLOSION_COMPRESSING.RB().ii(Data.IridiumAlloyIngot.get(1)).io(Data.IridiumReinforcedPlate.get(1), DarkAsh.getDustTiny(4)).add(20, 30);

        BLASTING.RB().ii(Tungsten.getIngot(1), Steel.getIngot(1)).io(TungstenSteel.getIngotHot(2), DarkAsh.getDustSmall(1)).add(Math.max(TungstenSteel.getMass() / 80L, 1L) * TungstenSteel.getBlastTemp(), 480, TungstenSteel.getBlastTemp());
        BLASTING.RB().ii(Tungsten.getIngot(1), Carbon.getDust(1)).io(TungstenCarbide.getIngotHot(1), Ash.getDustSmall(2)).add(Math.max(TungstenCarbide.getMass() / 40L, 1L) * TungstenCarbide.getBlastTemp(), 480, TungstenCarbide.getBlastTemp());
        BLASTING.RB().ii(Vanadium.getIngot(3), Gallium.getIngot(1)).io(VanadiumGallium.getIngotHot(4), DarkAsh.getDustSmall(2)).add(Math.max(VanadiumGallium.getMass() / 40L, 1L) * VanadiumGallium.getBlastTemp(), 480, VanadiumGallium.getBlastTemp());
        BLASTING.RB().ii(Niobium.getIngot(1), Titanium.getIngot(1)).io(NiobiumTitanium.getIngotHot(2), DarkAsh.getDustSmall(1)).add(Math.max(NiobiumTitanium.getMass() / 80L, 1L) * NiobiumTitanium.getBlastTemp(), 480, NiobiumTitanium.getBlastTemp());
        BLASTING.RB().ii(Nickel.getIngot(4), Chrome.getIngot(1)).io(Nichrome.getIngotHot(5), DarkAsh.getDustSmall(2)).add(Math.max(Nichrome.getMass() / 32L, 1L) * Nichrome.getBlastTemp(), 480, Nichrome.getBlastTemp());
        BLASTING.RB().ii(Ruby.getDust(1)).io(Aluminium.getNugget(3), DarkAsh.getDustTiny(1)).add(400, 100, 1200);
        BLASTING.RB().ii(Ruby.getGem(1)).io(Aluminium.getNugget(3), DarkAsh.getDustTiny(1)).add(320, 100, 1200);
        //RB.get(BLASTING).ii(GreenSapphire.getDust(1)).io(Aluminium.getNugget(3), DarkAsh.getDustTiny(1)).add(400, 100, 1200);
        //RB.get(BLASTING).ii(GreenSapphire.getGem(1)).io(Aluminium.getNugget(3), DarkAsh.getDustTiny(1)).add(320, 100, 1200);
        BLASTING.RB().ii(BlueSapphire.getDust(1)).io(Aluminium.getNugget(3)).add(400, 100, 1200);
        BLASTING.RB().ii(BlueSapphire.getGem(1)).io(Aluminium.getNugget(3)).add(320, 100, 1200);
        BLASTING.RB().ii(Ilmenite.getDust(1), Carbon.getDust(1)).io(WroughtIron.getNugget(4), Rutile.getDustTiny(4)).add(800, 500, 1700);
        BLASTING.RB().ii(Magnesium.getDust(2)).fi(Titaniumtetrachloride.getLiquid(1000)).io(Titanium.getIngotHot(1), MagnesiumChloride.getDust(2)).add(800, 480, Titanium.getBlastTemp() + 200);

        BLASTING.RB().ii(Galena.getDust(1)).fi(Oxygen.getGas(2000)).io(Silver.getNugget(4), Lead.getNugget(4)).add(400, 500, 1500);
        BLASTING.RB().ii(Magnetite.getDust(1)).fi(Oxygen.getGas(2000)).io(WroughtIron.getNugget(4), DarkAsh.getDustSmall(1)).add(400, 500, 1000);
        BLASTING.RB().ii(Iron.getIngot(1)).fi(Oxygen.getGas(1000)).io(Steel.getIngot(1), DarkAsh.getDustSmall(1)).add(500, 120, 1000);
        BLASTING.RB().ii(WroughtIron.getIngot(1)).fi(Oxygen.getGas(1000)).io(Steel.getIngot(1), DarkAsh.getDustSmall(1)).add(100, 120, 1000);
        BLASTING.RB().ii(Copper.getDust(1)).fi(Oxygen.getGas(1000)).io(AnnealedCopper.getIngot(1)).add(500, 120, 1200);
        BLASTING.RB().ii(Copper.getIngot(1)).fi(Oxygen.getGas(1000)).io(AnnealedCopper.getIngot(1)).add(500, 120, 1200);
        BLASTING.RB().ii(Iridium.getIngot(3), Osmium.getIngot(1)).fi(Helium.getGas(1000)).io(Osmiridium.getIngotHot(4)).add(500, 1920, 2900);
        BLASTING.RB().ii(Naquadah.getIngot(1), Osmiridium.getIngot(1)).fi(Argon.getGas(1000)).io(NaquadahAlloy.getIngotHot(2)).add(500, 30720, NaquadahAlloy.getBlastTemp());

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
        FUSION.RB().fi(NaquadahEnriched.getLiquid(15), Radon.getGas(125)).fo(Naquadria.getLiquid(3)).add(64, 49152, 400000000);
    }
}
