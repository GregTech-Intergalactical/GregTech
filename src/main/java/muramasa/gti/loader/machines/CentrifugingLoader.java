package muramasa.gti.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import net.minecraft.item.ItemStack;

import java.util.stream.Collectors;

import static muramasa.antimatter.Data.*;
import static muramasa.gti.data.Materials.*;
import static muramasa.gti.data.RecipeMaps.CENTRIFUGING;
import static muramasa.antimatter.recipe.ingredient.AntimatterIngredient.*;

public class CentrifugingLoader {
    public static void init() {
        DUST_IMPURE.all().forEach(dust -> {
            if (dust.hasByProducts()) CENTRIFUGING.RB().ii(of(DUST_IMPURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1), DUST_TINY.get(dust.getByProducts().get(0), 1)).add(400, 2);
            else CENTRIFUGING.RB().ii(of(DUST_IMPURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1)).chances(100, 10).add(400, 2);
        });

        add(Pyrite,24,200);
        add(Chalcopyrite,24,300);
        add(SodiumSulfide,24,150);
    }

    private static void add(Material mat, long power, long duration) {
        if (!mat.has(DUST)) return;
        CENTRIFUGING.RB().ii(AntimatterIngredient.of(DUST.get(mat),1)).io(mat.getProcessInto().stream().map(t -> new ItemStack(DUST.get(t.m),t.s)).toArray(ItemStack[]::new)).add(duration,power);
    }
}
