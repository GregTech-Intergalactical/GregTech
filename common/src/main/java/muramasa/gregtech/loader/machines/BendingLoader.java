package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.Materials;

import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;
import static muramasa.gregtech.data.RecipeMaps.BENDING;

public class BendingLoader {
    public static void init() {
        AntimatterMaterialTypes.PLATE.all().forEach(t -> {
            if (!t.has(AntimatterMaterialTypes.INGOT)) return;
            long duration = Math.max(t.getHardness(), 1);
            BENDING.RB().ii(AntimatterMaterialTypes.INGOT.getMaterialIngredient(t,1),INT_CIRCUITS.get(1)).io(AntimatterMaterialTypes.PLATE.get(t,1)).add("plate_" + t.getId(),duration, 24);
        });
        AntimatterMaterialTypes.FOIL.all().forEach(foil -> {
            if (!foil.has(AntimatterMaterialTypes.PLATE)) return;
            long duration = Math.max(foil.getHardness(),1);
            BENDING.RB().ii(AntimatterMaterialTypes.PLATE.getMaterialIngredient(foil,1), INT_CIRCUITS.get(1)).io(AntimatterMaterialTypes.FOIL.get(foil,4)).add("foil_" + foil.getId(),duration,24);
        });
        BENDING.RB().ii(AntimatterMaterialTypes.PLATE.getMaterialIngredient(Materials.Tin,2), INT_CIRCUITS.get(4)).io(GregTechData.CellTin.getDefaultInstance()).add("ten_cell",80,24);
        BENDING.RB().ii(AntimatterMaterialTypes.PLATE.getMaterialIngredient(Materials.Steel,2), INT_CIRCUITS.get(4)).io(GregTechData.CellSteel.getDefaultInstance()).add("steel_cell",80,96);
        BENDING.RB().ii(AntimatterMaterialTypes.PLATE.getMaterialIngredient(Materials.TungstenSteel,2), INT_CIRCUITS.get(4)).io(GregTechData.CellTungstensteel.getDefaultInstance()).add("tungstensteel_cell",80,384);
    }
}
