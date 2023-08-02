package muramasa.gregtech.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTypeFluid;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.Materials;
import muramasa.gregtech.data.RecipeMaps;
import tesseract.TesseractGraphWrappers;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.material.MaterialTags.MOLTEN;
import static muramasa.gregtech.data.Materials.UUAmplifier;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class UUMatter {
    public static void init(){
        RecipeMaps.MASS_FABRICATING.RB().fi(UUAmplifier.getLiquid(1)).fo(Materials.UUMatter.getLiquid(1)).add("uu_matter", 803, 256);
        RecipeMaps.MASS_FABRICATING.RB().ii(INT_CIRCUITS.get(0)).fo(Materials.UUMatter.getLiquid(1)).add("uu_matter_expensive", 3215, 256);
        AntimatterAPI.all(Material.class).stream().filter(m -> m.getElement() != null && (m.has(DUST) || m.has(LIQUID) || m.has(GAS))).forEach(m -> {
            RecipeBuilder b = RecipeMaps.AMP_FABRICATING.RB();
            if (m.has(AntimatterMaterialTypes.DUST)){
                b.ii(DUST.getMaterialIngredient(m, 1));
            } else if (m.has(LIQUID)){
                b.fi(m.getLiquid(1000));
            } else if (m.has(GAS)){
                b.fi(m.getGas(1000));
            }
            b.fo(UUAmplifier.getLiquid((m.getProtons() + m.getNeutrons()) * TesseractGraphWrappers.dropletMultiplier)).add("uu_amplifier_from_" + m.getId(), 100, 32);
        });
    }
}
