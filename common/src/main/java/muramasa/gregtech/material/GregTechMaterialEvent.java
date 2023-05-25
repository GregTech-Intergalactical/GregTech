package muramasa.gregtech.material;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.material.IMaterialTag;
import muramasa.antimatter.material.MaterialTags;
import muramasa.gregtech.data.GregTechMaterialTags;

import static muramasa.antimatter.material.MaterialTags.HAS_CUSTOM_SMELTING;
import static muramasa.antimatter.material.MaterialTags.METAL;

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
}
