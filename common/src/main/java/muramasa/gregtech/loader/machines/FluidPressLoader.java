package muramasa.gregtech.loader.machines;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.Ref.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.FLUID_PRESS;

public class FluidPressLoader {
    public static void init() {
        FLUID_PRESS.RB().ii(RecipeIngredient.of(TagUtils.getForgelikeItemTag("seeds"))).fo(SeedOil.getLiquid(10)).add("seed_oil", 32, 2);
        FLUID_PRESS.RB().ii(Items.PUFFERFISH).fo(FishOil.getLiquid(30)).add("fish_oil_pufferfish", 16, 4);
        FLUID_PRESS.RB().ii(Items.COD).fo(FishOil.getLiquid(40)).add("fish_oil_cod", 16, 4);
        FLUID_PRESS.RB().ii(Items.SALMON).fo(FishOil.getLiquid(60)).add("fish_oil_salmon", 16, 4);
        FLUID_PRESS.RB().ii(Items.TROPICAL_FISH).fo(FishOil.getLiquid(70)).add("fish_oil_tropical_fish", 16, 4);
        FLUID_PRESS.RB().ii(Items.INK_SAC).fo(SquidInk.getLiquid(L)).add("squid_ink", 128, 4);
    }
}
