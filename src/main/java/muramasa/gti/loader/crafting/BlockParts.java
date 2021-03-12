package muramasa.gti.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import net.minecraft.data.IFinishedRecipe;

import java.util.function.Consumer;

import static muramasa.antimatter.Data.*;

public class BlockParts {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        FRAME.all().forEach(frame -> {
            if (!frame.has(ROD)) return;
            provider.addItemRecipe(output, "gtblockparts", "has_wrench", provider.hasItem(WRENCH.getTag()),FRAME.get().get(frame).asItem(),
                    ImmutableMap.of('R', ROD.get(frame), 'W', WRENCH.getTag())
            , "RRR","RWR", "RRR");
        });
    }
}
