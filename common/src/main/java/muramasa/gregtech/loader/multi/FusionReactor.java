package muramasa.gregtech.loader.multi;

import muramasa.gregtech.data.RecipeMaps;

import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST_TINY;
import static muramasa.antimatter.data.AntimatterMaterials.Iron;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.FUSION;

public class FusionReactor {
    public static void init(){
        FUSION.RB().fi(Helium3.getGas(125), Deuterium.getGas(125)).fo(Helium.getGas(125)).add("helium", 16, 2048, 60000000); //plasma
        FUSION.RB().fi(Tritium.getGas(125), Deuterium.getGas(125)).fo(Helium.getGas(125)).add("helium_2", 16, 4096, 40000000); //plasma
        FUSION.RB().fi(Helium3.getGas(125), Carbon.getLiquid(125)).fo(Oxygen.getGas(125)).add("oxygen", 32, 4096, 80000000); //plasma
        FUSION.RB().fi(Magnesium.getLiquid(L / 9), Silicon.getLiquid(L / 9)).fo(Iron.getLiquid(L / 9)).add("molten_iron", 32, 8192, 360000000); //plasma
        FUSION.RB().fi(Hydrogen.getGas(125), Manganese.getLiquid(L / 9)).fo(Iron.getLiquid(L / 9)).add("molten_iron_2", 64, 8192, 120000000);
        FUSION.RB().fi(Lithium.getLiquid(L / 9), Aluminium.getLiquid(L / 9)).io(DUST_TINY.get(Sulfur)).add("sulfur", 32, 10240, 240000000); //plasma
        FUSION.RB().fi(Hydrogen.getGas(375), Beryllium.getLiquid(L / 9)).fo(Nitrogen.getGas(175)).add("nitrogen", 16, 16384, 180000000); //plasma
        FUSION.RB().fi(Radon.getGas(125), Gallium.getLiquid(L / 9)).fo(Duranium.getLiquid(L / 9)).add("molten_duranium", 64, 16384, 140000000);
        FUSION.RB().fi(Tritium.getGas(125), Tantalum.getLiquid(L / 9)).fo(Tungsten.getLiquid(L / 9)).add("molten_tungsten", 64, 24576, 150000000);
        FUSION.RB().fi(Lithium.getLiquid(L / 9), Silver.getLiquid(L / 9)).fo(Indium.getLiquid(L / 9)).add("molten_indium", 32, 24576, 380000000);
        FUSION.RB().fi(Hydrogen.getGas(125), Vanadium.getLiquid(L / 9)).fo(Chromium.getLiquid(L / 9)).add("molten_chromium", 64, 24576, 140000000);
        FUSION.RB().fi(Hydrogen.getGas(48), Neodymium.getLiquid(L / 9)).fo(Europium.getLiquid(L / 9)).add("molten_europium", 64, 24576, 150000000);
        FUSION.RB().fi(Helium.getGas(125), Tungsten.getLiquid(L / 9)).fo(Osmium.getLiquid(L / 9)).add("molten_osmium", 64, 24578, 150000000);
        FUSION.RB().fi(Fluorine.getGas(125), Potassium.getLiquid(L / 9)).fo(Nickel.getLiquid(L / 9)).add("molten_nickel", 16, 32768, 480000000); //plasma
        FUSION.RB().fi(Tungsten.getLiquid(L / 9), Beryllium.getLiquid(L / 9)).fo(Platinum.getLiquid(L / 9)).add("molten_platinum", 32, 32768, 150000000);
        FUSION.RB().fi(Tungsten.getLiquid(L / 9), Lithium.getLiquid(L / 9)).fo(Iridium.getLiquid(L / 9)).add("molten_iridium", 32, 32768, 300000000);
        FUSION.RB().fi(Duranium.getLiquid((L / 9) * 2), Titanium.getLiquid(L / 3)).fo(Tritanium.getLiquid(L / 9)).add("molten_tritanium", 64, 32768, 200000000);



    }
}
