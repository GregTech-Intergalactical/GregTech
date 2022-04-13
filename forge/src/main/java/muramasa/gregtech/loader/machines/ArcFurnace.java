package muramasa.gregtech.loader.machines;

import muramasa.antimatter.Data;
import muramasa.gregtech.data.Materials;

import static muramasa.antimatter.Data.INGOT;
import static muramasa.gregtech.data.RecipeMaps.ARC_SMELTING;

public class ArcFurnace {
    public static void init() {
        ARC_SMELTING.RB().ii(INGOT.getMaterialIngredient(Data.Iron, 1)).fi(Materials.Oxygen.getGas(56)).io(INGOT.get(Materials.WroughtIron, 1)).add(56, 30, 0, 3);
    }
}
