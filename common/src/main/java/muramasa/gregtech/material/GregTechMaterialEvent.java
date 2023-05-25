package muramasa.gregtech.material;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.material.IMaterialTag;
import muramasa.gregtech.data.GregTechMaterialTags;

import java.util.Arrays;

import static muramasa.antimatter.material.MaterialTags.*;

public class GregTechMaterialEvent extends MaterialEvent<GregTechMaterialEvent> {
    public GregTechMaterialEvent asSolid(int meltingPoint, int blastFurnaceTemp, IMaterialTag... tags) {
        asSolid(meltingPoint, tags);
        GregTechMaterialTags.BLAST_FURNACE_TEMP.add(material, blastFurnaceTemp);
        if (blastFurnaceTemp >= 1000){
            flags(GregTechMaterialTags.NEEDS_BLAST_FURNACE, HAS_CUSTOM_SMELTING);
        }
        if (blastFurnaceTemp > 1750) {
            flags(AntimatterMaterialTypes.INGOT_HOT);
        }
        return this;
    }

    public GregTechMaterialEvent asMetal(int meltingPoint, int blastFurnaceTemp, IMaterialTag... tags) {
        flags(METAL);
        asSolid(meltingPoint, blastFurnaceTemp, tags);
        return this;
    }

    public GregTechMaterialEvent asFluid(int fuelPower, int temp, FluidProduct[] distillationProducts, int distillationAmount) {
        asFluid(fuelPower, temp);
        GregTechMaterialTags.DISTILLATION_FLUID_INPUT_AMOUNT.add(this.material, distillationAmount);
        GregTechMaterialTags.DISTILL_INTO.add(this.material, Arrays.stream(distillationProducts).toList());
        return this;
    }

    public GregTechMaterialEvent asGas(int fuelPower, int temp, FluidProduct[] distillationProducts, int distillationAmount) {
        asGas(fuelPower, temp);
        GregTechMaterialTags.DISTILLATION_FLUID_INPUT_AMOUNT.add(this.material, distillationAmount);
        GregTechMaterialTags.DISTILL_INTO.add(this.material, Arrays.stream(distillationProducts).toList());
        return this;
    }

    public GregTechMaterialEvent asPlasma(int fuelPower, int temp, FluidProduct[] distillationProducts, int distillationAmount) {
        flags(AntimatterMaterialTypes.PLASMA);
        return asGas(fuelPower,temp,distillationProducts,distillationAmount);
    }
}
