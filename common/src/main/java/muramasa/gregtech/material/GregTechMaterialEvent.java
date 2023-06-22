package muramasa.gregtech.material;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.material.IMaterialTag;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.gregtech.data.GregTechMaterialTags;
import net.minecraft.world.item.enchantment.Enchantment;

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

    @Override
    public GregTechMaterialEvent addTools(float toolDamage, float toolSpeed, int toolDurability, int toolQuality, ImmutableMap<Enchantment, Integer> toolEnchantment, AntimatterToolType... toolTypes) {
        flags(AntimatterMaterialTypes.ROD_LONG);
        return super.addTools(toolDamage, toolSpeed, toolDurability, toolQuality, toolEnchantment, toolTypes);
    }
}
