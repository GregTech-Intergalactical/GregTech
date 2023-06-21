package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.gregtech.data.GregTechMaterialTags;

import static muramasa.antimatter.material.MaterialTags.*;
import static muramasa.gregtech.data.RecipeMaps.*;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class DistilleryLoader {
    public static void init() {
        GregTechMaterialTags.DISTILL_INTO.getAll().forEach((material, fluidProducts) -> {
            for (int i = 0; i < fluidProducts.size(); i++){
                Material fo = fluidProducts.get(i).mat();
                DISTILLING.RB()
                        .ii(INT_CIRCUITS.get(i + 1).setNoConsume())
                        .fi(material.has(AntimatterMaterialTypes.LIQUID) ? material.getLiquid(GregTechMaterialTags.DISTILLATION_FLUID_INPUT_AMOUNT.getInt(material)) : material.getGas(GregTechMaterialTags.DISTILLATION_FLUID_INPUT_AMOUNT.getInt(material)))
                        .fo(fo.has(AntimatterMaterialTypes.LIQUID) ? fo.getLiquid(fluidProducts.get(i).amount()) : fo.getGas(fluidProducts.get(i).amount()))
                        .add(material.getId(), material.getMass(), 20);
            }
        });
    }
}