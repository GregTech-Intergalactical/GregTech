package muramasa.gregtech.loader.multi;
import static muramasa.gregtech.data.RecipeMaps.CRACKING;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class CrackingUnit {
    public static void init() {
        //HYDRO
        CRACKING.RB().fi(Ethane.getGas(1000),Hydrogen.getGas(1000)).fo(Methane.getGas(2000)).add("methane",100, 500);
        CRACKING.RB().fi(Propane.getGas(1000),Hydrogen.getGas(1000)).fo(Methane.getGas(2000),Ethane.getGas(1000)).add("methane_ethane",100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Butane.getGas(1000),Hydrogen.getGas(2000)).fo(Ethane.getGas(2000)).add("ethane",100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Butane.getGas(1000),Hydrogen.getGas(2000)).fo(Propane.getGas(1000),Methane.getGas(1000)).add("propane_methane",100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Ethylene.getGas(1000),Hydrogen.getGas(2000)).fo(Ethane.getGas(1000)).add("ethane_2",100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Ethylene.getGas(1000),Hydrogen.getGas(4000)).fo(Methane.getGas(2000)).add("methane_2",200, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Propene.getGas(1000),Hydrogen.getGas(2000)).fo(Propane.getGas(1000)).add("propane",100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Propene.getGas(1000),Hydrogen.getGas(2000)).fo(Ethylene.getGas(1000),Methane.getGas(1000)).add("ethylene_methane",100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Propene.getGas(1000),Hydrogen.getGas(2000)).fo(Ethane.getGas(1000),Methane.getGas(1000)).add("ethane_methane",200, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Butene.getGas(1000),Hydrogen.getGas(2000)).fo(Butane.getGas(1000)).add("butane",100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Butene.getGas(1000),Hydrogen.getGas(2000)).fo(Propane.getGas(1000),Methane.getGas(1000)).add("propane_methane_2",100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Butene.getGas(1000),Hydrogen.getGas(2000)).fo(Ethane.getGas(2000)).add("ethane_3",200, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Butadiene.getGas(1000),Hydrogen.getGas(2000)).fo(Ethylene.getGas(2000)).add("ethylene",100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Butadiene.getGas(1000),Hydrogen.getGas(4000)).fo(Butane.getGas(1000)).add("butane_2",200, 500);
        CRACKING.RB().fi(Hydrogen.getGas(1000), LightFuel.getLiquid(1000)).fo(HydroCrackedLightFuel.getLiquid(2000)).add("light_fuel", 20, 120);
        CRACKING.RB().fi(Steam.getGas(1000), LightFuel.getLiquid(1000)).fo(SteamCrackedLightFuel.getLiquid(2000)).add("light_fuel_1", 20, 120);
        CRACKING.RB().fi(Hydrogen.getGas(1000), HeavyFuel.getLiquid(1000)).fo(HydroCrackedHeavyFuel.getLiquid(2000)).add("heavy_fuel", 20, 120);
        CRACKING.RB().fi(Steam.getGas(1000), HeavyFuel.getLiquid(1000)).fo(SteamCrackedHeavyFuel.getLiquid(2000)).add("heavy_fuel_1", 20, 120);
        CRACKING.RB().fi(Hydrogen.getGas(1000), Naphtha.getLiquid(1000)).fo(SteamCrackedNaphtha.getLiquid(2000)).add("naptha", 20, 120);
        CRACKING.RB().fi(Steam.getGas(1000), Naphtha.getLiquid(1000)).fo(SteamCrackedNaphtha.getLiquid(2000)).add("naptha_1", 20, 120);
        //STEAM
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Ethane.getGas(1000),Steam.getGas(1000)).fo(Methane.getGas(1000),Methanol.getLiquid(1000)).add("methane_methanol",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Ethane.getGas(1000),Steam.getGas(1000)).fo(Methane.getGas(1000),Methanol.getLiquid(1000)).add("methane_methanol_2",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Ethane.getGas(1000),Steam.getGas(2000)).fo(Methanol.getLiquid(2000),Hydrogen.getGas(2000)).add("methanol_hydrogen",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Propane.getGas(1000),Steam.getGas(1000)).fo(Ethane.getGas(1000),Methanol.getLiquid(1000)).add("ethane_methanol",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Propane.getGas(1000),Steam.getGas(1000)).fo(Ethane.getGas(1000),Methanol.getLiquid(1000)).add("ethane_methanol_2",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Propane.getGas(1000),Steam.getGas(2000)).fo(Methanol.getLiquid(1000),Ethanol.getLiquid(1000),Hydrogen.getGas(2000)).add("ethane_methanol_3",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Butane.getGas(1000),Steam.getGas(1000)).fo(Propane.getGas(1000),Methanol.getLiquid(1000)).add("propane_methanol",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Butane.getGas(1000),Steam.getGas(1000)).fo(Methane.getGas(1000),Propanol.getLiquid(1000)).add("methane_propanol",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Butane.getGas(1000),Steam.getGas(2000)).fo(Hydrogen.getGas(2000),Ethanol.getLiquid(2000)).add("hydrogen_ethonal",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Ethylene.getGas(1000),Steam.getGas(2000)).fo(Ethanediol.getLiquid(1000),Hydrogen.getGas(2000)).add("ethanediol_hydrogen",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Ethylene.getGas(1000),Steam.getGas(1000)).fo(Ethanol.getLiquid(1000)).add("ethanol",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Propene.getGas(1000),Steam.getGas(1000)).fo(Propanol.getLiquid(1000)).add("propanol",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Propene.getGas(1000),Steam.getGas(1000)).fo(Methanol.getLiquid(1000),Ethylene.getGas(1000)).add("methanol_ethylene",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Propene.getGas(1000),Steam.getGas(1000)).fo(Ethenol.getLiquid(1000),Methane.getGas(1000)).add("ethenol_methane",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(4).setNoConsume()).fi(Propene.getGas(1000),Steam.getGas(2000)).fo(Propanediol.getLiquid(1000),Hydrogen.getGas(2000)).add("propanediol_hydrogen",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(5).setNoConsume()).fi(Propene.getGas(1000),Steam.getGas(2000)).fo(Ethanol.getLiquid(1000),Methanol.getLiquid(1000),Hydrogen.getGas(2000)).add("ethanol_methanol",200, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(6).setNoConsume()).fi(Propene.getGas(1000),Steam.getGas(2000)).fo(Ethenol.getLiquid(1000),Methanol.getLiquid(1000),Hydrogen.getGas(2000)).add("ethanol_methanol_2",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(7).setNoConsume()).fi(Propene.getGas(1000),Steam.getGas(3000)).fo(Methanol.getLiquid(1000),Ethanediol.getLiquid(1000),Hydrogen.getGas(1000)).add("mathanol_ethanediol",200, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(8).setNoConsume()).fi(Propene.getGas(1000),Steam.getGas(1000)).fo(Ethanediol.getLiquid(1000),Methane.getGas(1000)).add("mathanol_ethanediol_2",200, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Butene.getGas(1000),Steam.getGas(1000)).fo(Butanol.getLiquid(1000)).add("butanol",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Butene.getGas(1000),Steam.getGas(2000)).fo(Butanediol.getLiquid(1000),Hydrogen.getGas(2000)).add("butanediol_hydrogen",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Butene.getGas(1000),Steam.getGas(2000)).fo(Methanol.getLiquid(1000),Propenol.getLiquid(1000),Hydrogen.getGas(2000)).add("methanol_propenol",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(4).setNoConsume()).fi(Butene.getGas(1000),Steam.getGas(2000)).fo(Methanol.getLiquid(1000),Propanol.getLiquid(1000),Hydrogen.getGas(2000)).add("methanol_propanol",200, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(5).setNoConsume()).fi(Butene.getGas(1000),Steam.getGas(1000)).fo(Ethanol.getLiquid(2000),Hydrogen.getGas(2000)).add("ethanol_hydrogen",200, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Butadiene.getGas(1000),Steam.getGas(2000)).fo(Ethanol.getLiquid(2000),Hydrogen.getGas(2000)).add("ethanol_hydrogen_2",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Butadiene.getGas(1000),Steam.getGas(1000)).fo(Ethanol.getLiquid(1000),Ethane.getGas(1000)).add("ethanol_ethane",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Butadiene.getGas(1000),Steam.getGas(1000)).fo(Butenol.getLiquid(1000)).add("butenol",100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(4).setNoConsume()).fi(Butadiene.getGas(1000),Steam.getGas(2000)).fo(Butanediol.getLiquid(1000),Hydrogen.getGas(2000)).add("butanediol_hydrogen_2",200, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(5).setNoConsume()).fi(Butadiene.getGas(1000),Steam.getGas(3000)).fo(Ethanediol.getLiquid(2000)).add("ethanediol",300, 172);
    }
}