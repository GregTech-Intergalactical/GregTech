package muramasa.gregtech.loader.multi;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.world.item.crafting.Ingredient;

import static muramasa.gregtech.data.RecipeMaps.NUCLEAR;

public class NuclearLoader {
    public static void init() {
        NUCLEAR.RB().ii(RecipeIngredient.of(GregTechData.UraniumFuelCell.getDefaultInstance()))
                .add("uranium_fuel_cell",400, 25);
        NUCLEAR.RB().ii(RecipeIngredient.of(GregTechData.ThoriumFuelCell))
                .add("thorium_fuel_cell",800, 10);
    }
}
