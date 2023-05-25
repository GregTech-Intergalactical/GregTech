package muramasa.gregtech.loader.multi;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.gregtech.data.Materials;
import muramasa.gregtech.data.RecipeMaps;

import static muramasa.gregtech.data.RecipeMaps.PYROLYSISING;

public class PyrolysisOven {
    public static void init(){
        PYROLYSISING.RB().ii(AntimatterMaterialTypes.DUST.getMaterialIngredient(Materials.OilShale, 1)).fo(Materials.Oil.getLiquid(25)).add("oil_shale", 200, 120);
    }
}
