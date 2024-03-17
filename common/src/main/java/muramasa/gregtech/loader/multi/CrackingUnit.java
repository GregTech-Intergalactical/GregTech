package muramasa.gregtech.loader.multi;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST_TINY;
import static muramasa.gregtech.data.RecipeMaps.CHEMICAL_REACTOR;
import static muramasa.gregtech.data.RecipeMaps.CRACKING;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class CrackingUnit {
    public static void init() {
        CRACKING.RB().fi(Hydrogen.getGas(1000), LightFuel.getLiquid(1000)).fo(HydroCrackedLightFuel.getLiquid(2000)).add("light_fuel_hydrogen", 20, 120);
        CRACKING.RB().fi(Steam.getGas(1000), LightFuel.getLiquid(1000)).fo(SteamCrackedLightFuel.getLiquid(2000)).add("light_fuel_steam", 20, 120);
        CRACKING.RB().fi(Hydrogen.getGas(1000), HeavyFuel.getLiquid(1000)).fo(HydroCrackedHeavyFuel.getLiquid(2000)).add("heavy_fuel_hydrogen", 20, 120);
        CRACKING.RB().fi(Steam.getGas(1000), HeavyFuel.getLiquid(1000)).fo(SteamCrackedHeavyFuel.getLiquid(2000)).add("heavy_fuel_steam", 20, 120);
        CRACKING.RB().fi(Hydrogen.getGas(1000), Naphtha.getLiquid(1000)).fo(HydroCrackedNaphtha.getLiquid(2000)).add("naphtha_hydrogen", 20, 120);
        CRACKING.RB().fi(Steam.getGas(1000), Naphtha.getLiquid(1000)).fo(SteamCrackedNaphtha.getLiquid(2000)).add("naphtha_steam", 20, 120);
        CRACKING.RB().fi(Hydrogen.getGas(1000), RefineryGas.getGas(1000)).fo(HydroCrackedRefineryGas.getGas(2000)).add("refinery_gas_hydrogen", 20, 120);
        /*CRACKING.RB().fi(Steam.getGas(1000), RefineryGas.getGas(1000)).fo(SteamCrackedRefineryGas.getGas(2000)).add("refinery_gas_steam", 20, 120);*/
        CRACKING.RB().fi(Hydrogen.getGas(1000), Propane.getGas(1000)).fo(HydroCrackedPropane.getGas(2000)).add("propane_hydrogen", 20, 120);
        CRACKING.RB().fi(Steam.getGas(1000), Propane.getGas(1000)).fo(SteamCrackedPropane.getGas(2000)).add("propane_steam", 20, 120);
        CRACKING.RB().fi(Hydrogen.getGas(1000), Butane.getGas(1000)).fo(HydroCrackedButane.getGas(2000)).add("butane_hydrogen", 20, 120);
        CRACKING.RB().fi(Steam.getGas(1000), Butane.getGas(1000)).fo(SteamCrackedButane.getGas(2000)).add("butane_steam", 20, 120);
        CRACKING.RB().fi(Hydrogen.getGas(1000), Ethane.getGas(1000)).fo(HydroCrackedEthane.getGas(2000)).add("ethane_hydrogen", 20, 120);
        CRACKING.RB().fi(Steam.getGas(1000), Ethane.getGas(1000)).fo(SteamCrackedEthane.getGas(2000)).add("ethane_steam", 20, 120);
        CRACKING.RB().fi(Steam.getGas(100), Tar.getLiquid(100)).io(DUST.get(Sulfur), DUST_TINY.get(PetroleumCoke), DUST.get(Bitumen)).chances(1, 0.8, 0.3).add("tar", 20, 120);

        //moved from chemical reactor
        //CRACKING.RB().fi(Butene.getGas(1000)).ii(INT_CIRCUITS.get(12).setNoConsume()).fo(Ethylene.getGas(1000)).add("ethylene",720, 120);
        //CRACKING.RB().fi(Propene.getGas(1000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(Ethylene.getGas(1000)).add("ethylene_1",720, 120);
        //CRACKING.RB().fi(Ethane.getGas(4000)).ii(INT_CIRCUITS.get(8).setNoConsume()).fo(Ethylene.getGas(3000), Hydrogen.getGas(1000)).add("ethylene_4",320, 120);

    }
}