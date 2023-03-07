package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;

import static muramasa.antimatter.material.MaterialTags.*;
import static muramasa.gregtech.data.RecipeMaps.*;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class DistilleryLoader {
    public static void init() {
        DISTILL_INTO.getAll().forEach((material, fluidProducts) -> {
            if(fluidProducts.size() > 1) return; // Use the distillation tower for materials with more than 1 product
            Material fo = fluidProducts.get(0).mat();
            DISTILLING.RB()
                    .ii(INT_CIRCUITS.get(1).setNoConsume())
                    .fi(material.has(AntimatterMaterialTypes.LIQUID) ? material.getLiquid(DISTILLATION_FLUID_INPUT_AMOUNT.getInt(material)) : material.getGas(DISTILLATION_FLUID_INPUT_AMOUNT.getInt(material)))
                    .fo(fo.has(AntimatterMaterialTypes.LIQUID) ? fo.getLiquid(fluidProducts.get(0).amount()) : fo.getGas(fluidProducts.get(0).amount()))
                    .add(material.getId(), material.getMass(), 20);
        });
    }
}