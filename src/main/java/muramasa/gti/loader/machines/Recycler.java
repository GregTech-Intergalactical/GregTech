package muramasa.gti.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gti.data.GregTechData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;
import static muramasa.gti.data.RecipeMaps.RECYCLING;

public class Recycler {
    public static void init() {
        AntimatterAPI.all(ItemBasic.class, Recycler::add);
        Registry.ITEM.forEach(Recycler::add);
    }

    private static void add(Item i) {
        RECYCLING.RB()
                .ii(RecipeIngredient.of(i,1))
                .io(new ItemStack(GregTechData.Scrap))
                .chances(10)
                .add(40, 64);
    }
}
