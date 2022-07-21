package muramasa.gregtech.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import static muramasa.gregtech.data.RecipeMaps.*;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class DistilleryLoader {
    public static void init() {
        AntimatterAPI.all(Material.class).forEach(m -> {
            if (!(m.canDistill)) return;
            for(int i=0;i<m.distillsInto.length;i++){
                DISTILLING.RB().ii(INT_CIRCUITS.get(i).setNoConsume()).fi(m.getLiquid(fluidamount(m.amount))).fo(m.distillsInto[i].getLiquid(m.amount[i])).add(m.getDensity(),20);
            }
        });
    }

    private static int fluidamount(int[] amount){
        int x=0;
        for(int i=0;i<amount.length;i++){
            x += amount[i];
        }
        return x;
    }
}
