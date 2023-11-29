package muramasa.gregtech.loader.multi;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST_TINY;
import static muramasa.gregtech.data.RecipeMaps.CRACKING;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class CrackingUnit {
    public static void init() {
        CRACKING.RB().fi(Hydrogen.getGas(1000), LightFuel.getLiquid(1000)).fo(HydroCrackedLightFuel.getLiquid(2000)).add("light_fuel_hydrogen", 20, 120);
        CRACKING.RB().fi(Steam.getGas(1000), LightFuel.getLiquid(1000)).fo(SteamCrackedLightFuel.getLiquid(2000)).add("light_fuel_steam", 20, 120);
        CRACKING.RB().fi(Hydrogen.getGas(1000), HeavyFuel.getLiquid(1000)).fo(HydroCrackedHeavyFuel.getLiquid(2000)).add("heavy_fuel_hydrogen", 20, 120);
        CRACKING.RB().fi(Steam.getGas(1000), HeavyFuel.getLiquid(1000)).fo(SteamCrackedHeavyFuel.getLiquid(2000)).add("heavy_fuel_steam", 20, 120);
        CRACKING.RB().fi(Hydrogen.getGas(1000), Naphtha.getLiquid(1000)).fo(SteamCrackedNaphtha.getLiquid(2000)).add("naptha_hydrogen", 20, 120);
        CRACKING.RB().fi(Steam.getGas(1000), Naphtha.getLiquid(1000)).fo(SteamCrackedNaphtha.getLiquid(2000)).add("naptha_steam", 20, 120);
        CRACKING.RB().fi(Hydrogen.getGas(1000), RefineryGas.getGas(1000)).fo(HydroCrackedRefineryGas.getGas(2000)).add("refinery_gas_hydrogen", 20, 120);
        /*CRACKING.RB().fi(Steam.getGas(1000), RefineryGas.getGas(1000)).fo(SteamCrackedRefineryGas.getGas(2000)).add("refinery_gas_steam", 20, 120);*/
        CRACKING.RB().fi(Hydrogen.getGas(1000), Propane.getGas(1000)).fo(HydroCrackedPropane.getGas(2000)).add("propane_hydrogen", 20, 120);
        CRACKING.RB().fi(Steam.getGas(1000), Propane.getGas(1000)).fo(SteamCrackedPropane.getGas(2000)).add("propane_steam", 20, 120);
        CRACKING.RB().fi(Hydrogen.getGas(1000), Butane.getGas(1000)).fo(HydroCrackedButane.getGas(2000)).add("butane_hydrogen", 20, 120);
        CRACKING.RB().fi(Steam.getGas(1000), Butane.getGas(1000)).fo(SteamCrackedButane.getGas(2000)).add("butane_steam", 20, 120);
        CRACKING.RB().fi(Hydrogen.getGas(1000), Ethane.getGas(1000)).fo(HydroCrackedEthane.getGas(2000)).add("ethane_hydrogen", 20, 120);
        CRACKING.RB().fi(Steam.getGas(1000), Ethane.getGas(1000)).fo(SteamCrackedEthane.getGas(2000)).add("ethane_steam", 20, 120);
        CRACKING.RB().fi(Steam.getGas(1000), Tar.getLiquid(1000)).io(DUST.get(Sulfur), DUST_TINY.get(PetroleumCoke), DUST_TINY.get(Bitumen)).chances(1, 0.8, 1).add("tar", 20, 120);
    }
}