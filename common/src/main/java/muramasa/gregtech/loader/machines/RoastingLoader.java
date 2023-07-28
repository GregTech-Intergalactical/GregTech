package muramasa.gregtech.loader.machines;

import muramasa.gregtech.data.RecipeMaps;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Copper;
import static muramasa.antimatter.data.AntimatterMaterials.Iron;
import static muramasa.gregtech.data.Materials.*;

public class RoastingLoader {
    public static void init(){
        RecipeMaps.ROASTING.RB().fi(Oxygen.getGas(2000)).ii(DUST.getMaterialIngredient(Galena, 1)).io(DUST_SMALL.get(Silver), DUST_SMALL.get(Lead)).fo(SulfurDioxide.getGas(500)).add("galena", 512, 5, 0, 3);
        RecipeMaps.ROASTING.RB().fi(Oxygen.getGas(4000)).ii(DUST.getMaterialIngredient(Chalcopyrite, 1)).io(DUST_SMALL.get(Copper), DUST_SMALL.get(Iron)).fo(SulfurDioxide.getGas(1000)).add("chalcopyrite", 512, 5, 0, 3);
        RecipeMaps.ROASTING.RB().fi(Oxygen.getGas(3000)).ii(DUST.getMaterialIngredient(Pyrite, 1)).io(DUST_SMALL.get(Iron)).fo(SulfurDioxide.getGas(1000)).add("pyrite", 512, 5, 0, 3);
        RecipeMaps.ROASTING.RB().fi(Oxygen.getGas(2000)).ii(DUST.getMaterialIngredient(Tetrahedrite, 1)).io(DUST_SMALL.get(Copper), DUST_SMALL.get(Antimony), DUST_TINY.get(Iron)).fo(SulfurDioxide.getGas(500)).add("tetrahedrite", 512, 5, 0, 3);
        RecipeMaps.ROASTING.RB().fi(Oxygen.getGas(6000)).ii(DUST.getMaterialIngredient(Molybdenite, 1)).io(DUST_SMALL.get(Molybdenum)).fo(SulfurDioxide.getGas(200)).add("molybdenite", 512, 5, 0, 3);
        RecipeMaps.ROASTING.RB().fi(Oxygen.getGas(8000)).ii(DUST.getMaterialIngredient(Pentlandite, 1)).io(DUST_SMALL.get(Nickel, 2)).fo(SulfurDioxide.getGas(1000)).add("pentlandite", 512, 5, 0, 3);
        RecipeMaps.ROASTING.RB().fi(Oxygen.getGas(5000)).ii(DUST.getMaterialIngredient(Stibnite, 1)).io(DUST_SMALL.get(Antimony)).fo(SulfurDioxide.getGas(1000)).add("stibnite", 512, 5, 0, 3);
        RecipeMaps.ROASTING.RB().fi(Oxygen.getGas(4000)).ii(DUST.getMaterialIngredient(Sphalerite, 1)).io(DUST_SMALL.get(Zinc, 2)).fo(SulfurDioxide.getGas(1000)).add("sphalerite", 512, 5, 0, 3);
        RecipeMaps.ROASTING.RB().fi(Oxygen.getGas(3000)).ii(DUST.getMaterialIngredient(Cobaltite, 1)).io(DUST_SMALL.get(Cobalt), DUST_SMALL.get(Arsenic)).fo(SulfurDioxide.getGas(1000)).add("cobaltite", 512, 5, 0, 3);
        RecipeMaps.ROASTING.RB().fi(Oxygen.getGas(8000)).ii(DUST.getMaterialIngredient(Sulfur, 1)).io(DUST_TINY.get(Sulfur)).fo(SulfurDioxide.getGas(3000)).add("pyrite", 512, 5, 0, 3);



    }
}
