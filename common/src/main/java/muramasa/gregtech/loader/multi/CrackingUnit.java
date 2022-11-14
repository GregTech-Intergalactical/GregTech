package muramasa.gregtech.loader.multi;
import static muramasa.gregtech.data.RecipeMaps.CRACKING;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class CrackingUnit {
    public static void init() {
        //HYDRO
        CRACKING.RB().fi(Ethane.getGas(1000),Hydrogen.getGas(1000)).fo(Methane.getGas(2000)).add(100, 500);
        CRACKING.RB().fi(Propane.getGas(1000),Hydrogen.getGas(1000)).fo(Methane.getGas(2000),Ethane.getGas(1000)).add(100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Butane.getGas(1000),Hydrogen.getGas(2000)).fo(Ethane.getGas(2000)).add(100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Butane.getGas(1000),Hydrogen.getGas(2000)).fo(Propane.getGas(1000),Methane.getGas(1000)).add(100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Ethylene.getGas(1000),Hydrogen.getGas(2000)).fo(Ethane.getGas(1000)).add(100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Ethylene.getGas(1000),Hydrogen.getGas(4000)).fo(Methane.getGas(2000)).add(200, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Propene.getGas(1000),Hydrogen.getGas(2000)).fo(Propane.getGas(1000)).add(100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Propene.getGas(1000),Hydrogen.getGas(2000)).fo(Ethylene.getGas(1000),Methane.getGas(1000)).add(100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Propene.getGas(1000),Hydrogen.getGas(2000)).fo(Ethane.getGas(1000),Methane.getGas(1000)).add(200, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Butene.getGas(1000),Hydrogen.getGas(2000)).fo(Butane.getGas(1000)).add(100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Butene.getGas(1000),Hydrogen.getGas(2000)).fo(Propane.getGas(1000),Methane.getGas(1000)).add(100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Butene.getGas(1000),Hydrogen.getGas(2000)).fo(Ethane.getGas(2000)).add(200, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Butadiene.getGas(1000),Hydrogen.getGas(2000)).fo(Ethylene.getGas(2000)).add(100, 500);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Butadiene.getGas(1000),Hydrogen.getGas(4000)).fo(Butane.getGas(1000)).add(200, 500);
        //STEAM
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Ethane.getGas(1000),Steam.getGas(1000)).fo(Methane.getGas(1000),Methanol.getLiquid(1000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Ethane.getGas(1000),Steam.getGas(1000)).fo(Methane.getGas(1000),Methanol.getLiquid(1000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Ethane.getGas(1000),Steam.getGas(2000)).fo(Methanol.getLiquid(2000),Hydrogen.getGas(2000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Propane.getGas(1000),Steam.getGas(1000)).fo(Ethane.getGas(1000),Methanol.getLiquid(1000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Propane.getGas(1000),Steam.getGas(1000)).fo(Ethane.getGas(1000),Methanol.getLiquid(1000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Propane.getGas(1000),Steam.getGas(2000)).fo(Methanol.getLiquid(1000),Ethanol.getLiquid(1000),Hydrogen.getGas(2000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Butane.getGas(1000),Steam.getGas(1000)).fo(Propane.getGas(1000),Methanol.getLiquid(1000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Butane.getGas(1000),Steam.getGas(1000)).fo(Methane.getGas(1000),Propanol.getLiquid(1000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Butane.getGas(1000),Steam.getGas(2000)).fo(Hydrogen.getGas(2000),Ethanol.getLiquid(2000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Ethylene.getGas(1000),Steam.getGas(2000)).fo(Ethanediol.getLiquid(1000),Hydrogen.getGas(2000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Ethylene.getGas(1000),Steam.getGas(1000)).fo(Ethanol.getLiquid(1000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Propene.getGas(1000),Steam.getGas(1000)).fo(Propanol.getLiquid(1000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Propene.getGas(1000),Steam.getGas(1000)).fo(Methanol.getLiquid(1000),Ethylene.getGas(1000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Propene.getGas(1000),Steam.getGas(1000)).fo(Ethenol.getLiquid(1000),Methane.getGas(1000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(4).setNoConsume()).fi(Propene.getGas(1000),Steam.getGas(2000)).fo(Propanediol.getLiquid(1000),Hydrogen.getGas(2000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(5).setNoConsume()).fi(Propene.getGas(1000),Steam.getGas(2000)).fo(Ethanol.getLiquid(1000),Methanol.getLiquid(1000),Hydrogen.getGas(2000)).add(200, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(6).setNoConsume()).fi(Propene.getGas(1000),Steam.getGas(2000)).fo(Ethenol.getLiquid(1000),Methanol.getLiquid(1000),Hydrogen.getGas(2000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(7).setNoConsume()).fi(Propene.getGas(1000),Steam.getGas(3000)).fo(Methanol.getLiquid(1000),Ethanediol.getLiquid(1000),Hydrogen.getGas(1000)).add(200, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(8).setNoConsume()).fi(Propene.getGas(1000),Steam.getGas(1000)).fo(Ethanediol.getLiquid(1000),Methane.getGas(1000)).add(200, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Butene.getGas(1000),Steam.getGas(1000)).fo(Butanol.getLiquid(1000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Butene.getGas(1000),Steam.getGas(2000)).fo(Butanediol.getLiquid(1000),Hydrogen.getGas(2000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Butene.getGas(1000),Steam.getGas(2000)).fo(Methanol.getLiquid(1000),Propenol.getLiquid(1000),Hydrogen.getGas(2000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(4).setNoConsume()).fi(Butene.getGas(1000),Steam.getGas(2000)).fo(Methanol.getLiquid(1000),Propanol.getLiquid(1000),Hydrogen.getGas(2000)).add(200, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(5).setNoConsume()).fi(Butene.getGas(1000),Steam.getGas(1000)).fo(Ethanol.getLiquid(2000),Hydrogen.getGas(2000)).add(200, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Butadiene.getGas(1000),Steam.getGas(2000)).fo(Ethanol.getLiquid(2000),Hydrogen.getGas(2000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Butadiene.getGas(1000),Steam.getGas(1000)).fo(Ethanol.getLiquid(1000),Ethane.getGas(1000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Butadiene.getGas(1000),Steam.getGas(1000)).fo(Butenol.getLiquid(1000)).add(100, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(4).setNoConsume()).fi(Butadiene.getGas(1000),Steam.getGas(2000)).fo(Butanediol.getLiquid(1000),Hydrogen.getGas(2000)).add(200, 172);
        CRACKING.RB().ii(INT_CIRCUITS.get(5).setNoConsume()).fi(Butadiene.getGas(1000),Steam.getGas(3000)).fo(Ethanediol.getLiquid(2000)).add(300, 172);
    }
}