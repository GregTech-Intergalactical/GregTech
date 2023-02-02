package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.gregtech.data.Materials;

import static muramasa.antimatter.data.AntimatterMaterialTypes.INGOT;
import static muramasa.gregtech.data.RecipeMaps.ARC_SMELTING;

public class ArcFurnace {
    public static void init() {
        ARC_SMELTING.RB().ii(INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1)).fi(Materials.Oxygen.getGas(56)).io(INGOT.get(Materials.WroughtIron, 1)).add("wrought_iron_ingot",56, 30, 0, 3);
    }
}
