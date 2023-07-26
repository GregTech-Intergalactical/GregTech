package muramasa.gregtech.loader.machines;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.RecipeMaps.CANNING;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.*;

public class CanningLoader {

    static int MULTIPLE_SMALL = 2;
    static int MULTIPLE_MEDIUM = 8;
    static int MULTIPLE_LARGE = 32;

    public static void init() {
        CANNING.RB().ii(of(BatteryHullSmall.getDefaultInstance()), of(DUST.get(Sodium, MULTIPLE_SMALL))).io(BatterySmallSodium.getDefaultInstance()).add("battery_small_sodium",100, 2);
        CANNING.RB().ii(of(BatteryHullSmall.getDefaultInstance()), of(DUST.get(Cadmium, MULTIPLE_SMALL))).io(BatterySmallCadmium.getDefaultInstance()).add("battery_small_cadmium",100, 2);
        CANNING.RB().ii(of(BatteryHullSmall.getDefaultInstance()), of(DUST.get(Lithium, MULTIPLE_SMALL))).io(BatterySmallLithium.getDefaultInstance()).add("battery_small_lithium",100, 2);

        CANNING.RB().ii(of(BatteryHullMedium.getDefaultInstance()), of(DUST.get(Sodium, MULTIPLE_MEDIUM))).io(BatteryMediumSodium.getDefaultInstance()).add("battery_medium_sodium",400, 2);
        CANNING.RB().ii(of(BatteryHullMedium.getDefaultInstance()), of(DUST.get(Cadmium, MULTIPLE_MEDIUM))).io(BatteryMediumCadmium.getDefaultInstance()).add("battery_medium_cadmium",400, 2);
        CANNING.RB().ii(of(BatteryHullMedium.getDefaultInstance()), of(DUST.get(Lithium, MULTIPLE_MEDIUM))).io(BatteryMediumLithium.getDefaultInstance()).add("battery_medium_lithium",400, 2);

        CANNING.RB().ii(of(BatteryHullLarge.getDefaultInstance()), of(DUST.get(Sodium, MULTIPLE_LARGE))).io(BatteryLargeSodium.getDefaultInstance()).add("battery_large_sodium",1600, 2);
        CANNING.RB().ii(of(BatteryHullLarge.getDefaultInstance()), of(DUST.get(Cadmium, MULTIPLE_LARGE))).io(BatteryLargeCadmium.getDefaultInstance()).add("battery_large_cadmium",1600, 2);
        CANNING.RB().ii(of(BatteryHullLarge.getDefaultInstance()), of(DUST.get(Lithium, MULTIPLE_LARGE))).io(BatteryLargeLithium.getDefaultInstance()).add("battery_large_lithium",1600, 2);
    }
}
