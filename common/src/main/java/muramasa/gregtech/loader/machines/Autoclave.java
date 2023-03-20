package muramasa.gregtech.loader.machines;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterialTypes.GEM;
import static muramasa.antimatter.data.AntimatterMaterials.Water;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.RecipeMaps.AUTOCLAVING;

public class Autoclave {
    public static void init() {
        GEM.all().forEach(g -> {
            if(!g.has(DUST)) return;
            int dur;
            long d = g.getDensity();
            if(d <= 1000){
                dur = 100;
            }else if(d <= 2000){
                dur = 200;
            }else{
                dur = 400;
            }
            AUTOCLAVING.RB().ii(of(DUST.get(g))).fi(Water.getLiquid(1000)).io(GEM.get(g)).add(g.getId()+"_autoclaving", dur,8);
        });
    }
}
