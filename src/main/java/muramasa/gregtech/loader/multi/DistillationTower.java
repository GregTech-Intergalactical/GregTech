package muramasa.gregtech.loader.multi;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import static muramasa.gregtech.data.RecipeMaps.DISTILLATION;

public class DistillationTower {
    public static void init() {
        AntimatterAPI.all(Material.class).forEach(m -> {
            if (!(m.canDistill)) return;
            int max = m.distillsInto.length;
            if (max==6){
                DISTILLATION.RB()
                        .fi(m.getLiquid(fluidamount(m.amount)))
                        .fo(m.distillsInto[0].getLiquid(m.amount[0]),m.distillsInto[1].getLiquid(m.amount[1]),m.distillsInto[2].getLiquid(m.amount[2]),
                                m.distillsInto[3].getLiquid(m.amount[3]),m.distillsInto[4].getLiquid(m.amount[4]),m.distillsInto[5].getLiquid(m.amount[5]))
                        .add(m.getDensity(),100);
            } else if (max==5){
                DISTILLATION.RB()
                        .fi(m.getLiquid(fluidamount(m.amount)))
                        .fo(m.distillsInto[0].getLiquid(m.amount[0]),m.distillsInto[1].getLiquid(m.amount[1]),m.distillsInto[2].getLiquid(m.amount[2]),m.distillsInto[3].getLiquid(m.amount[3]),m.distillsInto[4].getLiquid(m.amount[4]))
                        .add(m.getDensity(),100);
            } else if (max==4){
                DISTILLATION.RB()
                        .fi(m.getLiquid(fluidamount(m.amount)))
                        .fo(m.distillsInto[0].getLiquid(m.amount[0]),m.distillsInto[1].getLiquid(m.amount[1]),m.distillsInto[2].getLiquid(m.amount[2]),m.distillsInto[3].getLiquid(m.amount[3]))
                        .add(m.getDensity(),100);
            }
            else if (max==3){
                DISTILLATION.RB()
                        .fi(m.getLiquid(fluidamount(m.amount)))
                        .fo(m.distillsInto[0].getLiquid(m.amount[0]),m.distillsInto[1].getLiquid(m.amount[1]),m.distillsInto[2].getLiquid(m.amount[2]))
                        .add(m.getDensity(),100);
            }
            else if (max==2){
                DISTILLATION.RB().fi(m.getLiquid(fluidamount(m.amount)))
                        .fo(m.distillsInto[0].getLiquid(m.amount[0]),m.distillsInto[1].getLiquid(m.amount[1])).add(m.getDensity(),100);
            }
            else{
                DISTILLATION.RB().fi(m.getLiquid(fluidamount(m.amount))).fo(m.distillsInto[0].getLiquid(m.amount[0])).add(m.getDensity(),100);
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
