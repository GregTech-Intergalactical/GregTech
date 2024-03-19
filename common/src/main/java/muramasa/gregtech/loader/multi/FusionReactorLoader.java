package muramasa.gregtech.loader.multi;

import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.data.AntimatterMaterials.Gold;
import static muramasa.antimatter.data.AntimatterMaterials.Iron;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.FUSION;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class FusionReactorLoader {
    public static void init(){
        FUSION.RB().fi(Deuterium.getGas(2000)).ii(INT_CIRCUITS.get(1)).fo(Helium3.getGas(500), Tritium.getGas(500)).add("helium3", 730, -8192, 191365120);
        FUSION.RB().fi(Helium3.getGas(2000)).ii(INT_CIRCUITS.get(1)).fo(Helium.getGas(1000)).add("helium", 1280, -8192, 338165760);
        FUSION.RB().fi(Tritium.getGas(2000)).ii(INT_CIRCUITS.get(1)).fo(Helium.getGas(1000)).add("helium_2", 1130, -8192, 269222720);
        FUSION.RB().fi(Helium3.getGas(1000), Deuterium.getGas(1000)).fo(Helium.getGas(1000)).add("helium_3", 1820, -2048, 60000000); //plasma
        FUSION.RB().fi(Tritium.getGas(1000), Deuterium.getGas(1000)).fo(Helium.getGas(1000)).add("helium_4", 1760, -8192, 40000000); //plasma
        FUSION.RB().fi(Helium.getGas(1000), Carbon.getLiquid(L)).fo(Oxygen.getGas(1000)).add("oxygen", 716, -8192, 80000000); //plasma
        FUSION.RB().fi(Magnesium.getLiquid(L), Silicon.getLiquid(L)).fo(Iron.getLiquid(L)).add("molten_iron", 288, -8192, 360000000); //plasma
        FUSION.RB().fi(Hydrogen.getGas(125), Manganese.getLiquid(L / 9)).fo(Iron.getLiquid(L / 9)).add("molten_iron_2", 64, 8192, 120000000);
        FUSION.RB().fi(Lithium.getLiquid(L), Aluminium.getLiquid(L)).fo(Sulfur.getLiquid(L)).add("molten_sulfur", 288, -10240, 240000000); //plasma
        FUSION.RB().fi(Hydrogen.getGas(375), Beryllium.getLiquid(L / 9)).fo(Nitrogen.getGas(175)).add("nitrogen", 16, -16384, 180000000); //plasma
        FUSION.RB().fi(Radon.getGas(125), Gallium.getLiquid(L / 9)).fo(Duranium.getLiquid(L / 9)).add("molten_duranium", 64, 16384, 140000000);
        FUSION.RB().fi(Tritium.getGas(125), Tantalum.getLiquid(L / 9)).fo(Tungsten.getLiquid(L / 9)).add("molten_tungsten", 64, 24576, 150000000);
        FUSION.RB().fi(Lithium.getLiquid(L / 9), Silver.getLiquid(L / 9)).fo(Indium.getLiquid(L / 9)).add("molten_indium", 32, 24576, 380000000);
        FUSION.RB().fi(Hydrogen.getGas(125), Vanadium.getLiquid(L / 9)).fo(Chromium.getLiquid(L / 9)).add("molten_chromium", 64, 24576, 140000000);
        FUSION.RB().fi(Hydrogen.getGas(48), Neodymium.getLiquid(L / 9)).fo(Europium.getLiquid(L / 9)).add("molten_europium", 64, 24576, 150000000);
        FUSION.RB().fi(Helium.getGas(125), Tungsten.getLiquid(L / 9)).fo(Osmium.getLiquid(L / 9)).add("molten_osmium", 64, 24578, 150000000);
        FUSION.RB().fi(Fluorine.getGas(1000), Potassium.getLiquid(L)).fo(Nickel.getLiquid(L)).add("molten_nickel", 144, -32768, 480000000); //plasma
        FUSION.RB().fi(Tungsten.getLiquid(L / 9), Beryllium.getLiquid(L / 9)).fo(Platinum.getLiquid(L / 9)).add("molten_platinum", 32, 32768, 150000000);
        FUSION.RB().fi(Tungsten.getLiquid(L / 9), Lithium.getLiquid(L / 9)).fo(Iridium.getLiquid(L / 9)).add("molten_iridium", 32, 32768, 300000000);
        FUSION.RB().fi(Duranium.getLiquid((L / 9) * 2), Titanium.getLiquid(L / 3)).fo(Tritanium.getLiquid(L / 9)).add("molten_tritanium", 64, 32768, 200000000);
        FUSION.RB().fi(Mercury.getLiquid(L / 9), Gold.getLiquid(L / 9)).fo(Radon.getGas(125)).add("radon", 64, 32768, 200000000);
        FUSION.RB().fi(Thorium.getLiquid(L / 9), Plutonium239.getLiquid(L / 9)).fo(Naquadah.getLiquid(L / 9)).add("molten_naquadah", 64, 32768, 300000000);
        FUSION.RB().fi(Magnesium.getLiquid(L / 9), Mercury.getLiquid(L / 9)).fo(Uranium.getLiquid(L / 9)).add("molten_uranium", 64, 49152, 240000000);
        FUSION.RB().fi(Aluminium.getLiquid(L / 9), Gold.getLiquid(L / 9)).fo(Uranium.getLiquid(L / 9)).add("molten_uranium_2", 64, 49152, 240000000);
        FUSION.RB().fi(Radon.getGas(125), EnrichedNaquadah.getLiquid(L / 9)).fo(Naquadria.getLiquid(3)).add("molten_naquadria", 64, 49152, 400000000);
        FUSION.RB().fi(Chromium.getLiquid(L / 9), Lutetium.getLiquid(L / 9)).fo(Americium.getLiquid(L / 9)).add("molten_americium", 96, 49152, 200000000);
        FUSION.RB().fi(Helium.getGas(125), Uranium.getLiquid(L / 9)).fo(Plutonium239.getLiquid(L / 9)).add("molten_plutonium239", 128, 49152, 480000000);
        FUSION.RB().fi(Naquadria.getLiquid(L / 9), Americium.getLiquid(L / 9)).fo(Neutronium.getLiquid(1)).add("molten_neutronium", 1200, 98304, 600000000);
    }
}
