package muramasa.gti.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static muramasa.antimatter.Data.INGOT;
import static muramasa.antimatter.Data.WIRE_FINE;
import static muramasa.gti.data.RecipeMaps.WIRE_MILLING;

public class WiremillLoader {
    public static void init() {
        AntimatterAPI.all(Wire.class).forEach(t -> {
            Item wireItem = t.getBlockItem(PipeSize.TINY);
            ItemStack stack = new ItemStack(wireItem,2);
            AntimatterIngredient ing = INGOT.getMaterialIngredient(t.getMaterial(),1);
            if (ing.hasNoMatchingItems()) return;
            WIRE_MILLING.RB().ii(INGOT.getMaterialIngredient(t.getMaterial(),1)).io(stack).add(t.getMaterial().getMass()*2,24);
            if (WIRE_FINE.allowItemGen(t.getMaterial())) {
                WIRE_MILLING.RB().ii(AntimatterIngredient.of(wireItem,1)).io(WIRE_FINE.get(t.getMaterial(),4)).add((long)( t.getMaterial().getMass()*2.5),16);
            }
        });

    }
}
