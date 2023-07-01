package muramasa.gregtech.loader.machines;

import muramasa.gregtech.data.GregTechMaterialTags;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterialTypes.GEM;
import static muramasa.antimatter.data.AntimatterMaterials.Water;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.RecipeMaps.AUTOCLAVING;

public class Autoclave {
    public static void init() {
        GregTechMaterialTags.CRYSTALLIZE.all().stream().filter(m -> m.has(GEM) && m.has(DUST)).forEach(m -> {
            int dur;
            long d = m.getDensity();
            if(d <= 1000){
                dur = 100;
            }else if(d <= 2000){
                dur = 200;
            }else{
                dur = 400;
            }
            AUTOCLAVING.RB().ii(DUST.getMaterialIngredient(m, 1)).fi(Water.getLiquid(200)).io(GEM.get(m, 1)).add(m.getId(), dur,8);
        });
    }
}
