package muramasa.gregtech.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.Ref;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DEPLETED_FISSILE_FUEL;
import static muramasa.antimatter.material.Element.getFromProtons;
import static muramasa.gregtech.data.RecipeMaps.THERMAL_CENTRIFUGING;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class ThermalCentrifuge {
    public static void init() {
        AntimatterMaterialTypes.CRUSHED_PURIFIED.all().forEach(m -> {
            Material aOreByProduct1 = m.getByProducts().size() >= 1 ? m.getByProducts().get(0) : MaterialTags.MACERATE_INTO.getMapping(m);
            Material aOreByProduct2 = m.getByProducts().size() >= 2 ? m.getByProducts().get(1) : aOreByProduct1;
            ItemStack stoneDust = AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Stone, 1);

            THERMAL_CENTRIFUGING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.CRUSHED_PURIFIED.get(m),1)).io(AntimatterMaterialTypes.CRUSHED_REFINED.get(m, 1), AntimatterMaterialTypes.DUST_TINY.get(aOreByProduct2, 1), stoneDust).add("purified_" + m.getId(),500, 48,0,2);
        });
        fuel_reprocessing();
    }

    private static void fuel_reprocessing() {
        DEPLETED_FISSILE_FUEL.all().forEach(f -> {
            int protons = f.getElement().getProtons();
            if(protons != 101){
                String id = getFromProtons(protons + 1, false).getId().split("_")[0];
                System.out.println(f.getId().split("_")[0]);
                THERMAL_CENTRIFUGING.RB().ii(of(DEPLETED_FISSILE_FUEL.get(f))).io(new ItemStack(AntimatterAPI.get(ItemBasic.class, f.getId().split("_")[0]+"_waste", Ref.ID),3),new ItemStack(AntimatterAPI.get(ItemBasic.class, id+"_waste", Ref.ID))).add(f.getId()+"_depleted_fuel_reprocessing",200,1000);
            }else{
                THERMAL_CENTRIFUGING.RB().ii(of(DEPLETED_FISSILE_FUEL.get(f))).io(new ItemStack(AntimatterAPI.get(ItemBasic.class, f.getId().split("_")[0]+"_waste", Ref.ID),4)).add(f.getId()+"_depleted_fuel_reprocessing",200,1000);
            }
        });
    }
}
