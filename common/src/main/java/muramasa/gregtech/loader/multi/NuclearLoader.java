package muramasa.gregtech.loader.multi;

import muramasa.antimatter.material.Element;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DEPLETED_FISSILE_FUEL;
import static muramasa.antimatter.data.AntimatterMaterialTypes.FISSILE_FUEL;
import static muramasa.antimatter.material.Element.getFromProtons;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.RecipeMaps.NUCLEAR;

public class NuclearLoader {
    public static void init() {
        FISSILE_FUEL.all().forEach(f -> {
            int dur;
            int pow;
            long hls = f.getElement().halfLifeSeconds;
            if (hls < 60){
                dur = 200;
            }else if (hls < 3600){
                dur = 200*10;
            }else if (hls < 3600*24){
                dur = 200*30;
            }else if (hls < 3600*24*7){
                dur = 200*60;
            }else if (hls < 3600*24*30){
                dur = 200*120;
            }else if (hls < 3600*24*365){
                dur = 200*300;
            }else{
                dur = 200*600;
            }
            int start_protons = f.getElement().getProtons();
            int start_neutrons = f.getElement().getNeutrons();
            int end_protons_1;
            int end_protons_2;
            int free_neutrons;
            if(start_protons % 2 == 0){
                end_protons_1 = start_protons/2;
                Element end_element = getFromProtons(end_protons_1, false);
                free_neutrons = start_neutrons - 2 * end_element.getNeutrons() - 1;
            }else{
                end_protons_1 = (start_protons+1)/2;
                end_protons_2 = (start_protons-1)/2;
                Element end_element_1 = getFromProtons(end_protons_1, false);
                Element end_element_2 = getFromProtons(end_protons_2, false);
                free_neutrons = start_neutrons - end_element_1.getNeutrons() - end_element_2.getNeutrons() - 1;
            }
            pow = 1000 * free_neutrons;
            NUCLEAR.RB().ii(of(FISSILE_FUEL.get(f))).io(DEPLETED_FISSILE_FUEL.get(f)).add(f.getId()+"_fissile_nuclear_processing",dur,pow);
        });
    }
}
