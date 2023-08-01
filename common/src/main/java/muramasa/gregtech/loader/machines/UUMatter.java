package muramasa.gregtech.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTypeFluid;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.Materials;
import muramasa.gregtech.data.RecipeMaps;
import tesseract.TesseractGraphWrappers;

import static muramasa.antimatter.data.AntimatterMaterialTypes.BLOCK;
import static muramasa.antimatter.material.MaterialTags.MOLTEN;
import static muramasa.gregtech.data.Materials.UUAmplifier;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class UUMatter {
    public static void init(){
        RecipeMaps.MASS_FABRICATING.RB().fi(UUAmplifier.getLiquid(1)).fo(Materials.UUMatter.getLiquid(1)).add("uu_matter", 803, 256);
        RecipeMaps.MASS_FABRICATING.RB().ii(INT_CIRCUITS.get(0)).fo(Materials.UUMatter.getLiquid(1)).add("uu_matter_expensive", 3215, 256);
        RecipeMaps.AMP_FABRICATING.RB().ii(RecipeIngredient.of(GregTechData.Scrap, 9)).fo(UUAmplifier.getLiquid(1)).add("uu_amplifier", 180, 32);
        AntimatterAPI.all(Material.class).stream().filter(m -> m.getElement() != null).forEach(m -> {
            int multiplier = m == Materials.Silicon || m == Materials.Oxygen ? 1 : 2;
            m.getTypes().forEach(t -> {
                if (t instanceof MaterialTypeFluid<?> fluid && fluid.get() instanceof MaterialTypeFluid.IFluidGetter fluidGetter){
                    long fluidAmount = m.has(MOLTEN) ? Ref.L : 1000 * TesseractGraphWrappers.dropletMultiplier;
                    RecipeMaps.RECYCLING.RB().fi(fluidGetter.get(m, fluidAmount)).io(GregTechData.Scrap).chances(500 * multiplier).add("scrap_from_" + m.getId() + "_" + t.getId(), 100, 2);
                } else if (t == BLOCK){
                    RecipeMaps.RECYCLING.RB().ii(BLOCK.getMaterialIngredient(m, 1)).io(GregTechData.Scrap).chances(4500 * multiplier).add("scrap_from_" + m.getId() + "_block", 100, 2);
                } else if (t instanceof MaterialTypeItem<?> item){
                    long chance = (500 * t.getUnitValue()) / Ref.U;
                    RecipeMaps.RECYCLING.RB().ii(t.getMaterialIngredient(m, 1)).io(GregTechData.Scrap).chances(Math.min(10000, (int)chance) * multiplier).add("scrap_from_" + m.getId() + "_" + t.getId(), 100, 2);
                }
            });
        });
    }
}
