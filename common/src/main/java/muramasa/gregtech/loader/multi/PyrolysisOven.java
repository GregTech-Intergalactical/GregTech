package muramasa.gregtech.loader.multi;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.Materials;
import muramasa.gregtech.data.RecipeMaps;
import muramasa.gregtech.data.TierMaps;

import static muramasa.gregtech.data.RecipeMaps.PYROLYSISING;

public class PyrolysisOven {
    public static void init(){
        PYROLYSISING.RB().ii(AntimatterMaterialTypes.DUST.getMaterialIngredient(Materials.OilShale, 1)).fo(Materials.Oil.getLiquid(25)).add("oil", 200, 120);
        PYROLYSISING.RB().ii(RecipeIngredient.of(GregTechData.Biochaff, 4), TierMaps.INT_CIRCUITS.get(1)).fi(AntimatterMaterials.Water.getLiquid(4000)).fo(Materials.Biomass.getLiquid(5000)).add("biomass", 900, 10);
    }
}
