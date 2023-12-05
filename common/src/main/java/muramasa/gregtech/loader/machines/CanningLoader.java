package muramasa.gregtech.loader.machines;

import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.gregtech.data.RecipeMaps.CANNER;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.*;

public class CanningLoader {

    static int MULTIPLE_SMALL = 2;
    static int MULTIPLE_MEDIUM = 8;
    static int MULTIPLE_LARGE = 32;

    public static void init() {
        CANNER.RB().ii(of(GTCoreItems.BatteryHullSmall.getDefaultInstance()), of(DUST.get(Sodium, MULTIPLE_SMALL))).io(GTCoreItems.BatterySmallSodium.getDefaultInstance()).add("battery_small_sodium",100, 2);
        CANNER.RB().ii(of(GTCoreItems.BatteryHullSmall.getDefaultInstance()), of(DUST.get(Cadmium, MULTIPLE_SMALL))).io(GTCoreItems.BatterySmallCadmium.getDefaultInstance()).add("battery_small_cadmium",100, 2);
        CANNER.RB().ii(of(GTCoreItems.BatteryHullSmall.getDefaultInstance()), of(DUST.get(Lithium, MULTIPLE_SMALL))).io(GTCoreItems.BatterySmallLithium.getDefaultInstance()).add("battery_small_lithium",100, 2);

        CANNER.RB().ii(of(GTCoreItems.BatteryHullMedium.getDefaultInstance()), of(DUST.get(Sodium, MULTIPLE_MEDIUM))).io(GTCoreItems.BatteryMediumSodium.getDefaultInstance()).add("battery_medium_sodium",400, 2);
        CANNER.RB().ii(of(GTCoreItems.BatteryHullMedium.getDefaultInstance()), of(DUST.get(Cadmium, MULTIPLE_MEDIUM))).io(GTCoreItems.BatteryMediumCadmium.getDefaultInstance()).add("battery_medium_cadmium",400, 2);
        CANNER.RB().ii(of(GTCoreItems.BatteryHullMedium.getDefaultInstance()), of(DUST.get(Lithium, MULTIPLE_MEDIUM))).io(GTCoreItems.BatteryMediumLithium.getDefaultInstance()).add("battery_medium_lithium",400, 2);

        CANNER.RB().ii(of(GTCoreItems.BatteryHullLarge.getDefaultInstance()), of(DUST.get(Sodium, MULTIPLE_LARGE))).io(GTCoreItems.BatteryLargeSodium.getDefaultInstance()).add("battery_large_sodium",1600, 2);
        CANNER.RB().ii(of(GTCoreItems.BatteryHullLarge.getDefaultInstance()), of(DUST.get(Cadmium, MULTIPLE_LARGE))).io(GTCoreItems.BatteryLargeCadmium.getDefaultInstance()).add("battery_large_cadmium",1600, 2);
        CANNER.RB().ii(of(GTCoreItems.BatteryHullLarge.getDefaultInstance()), of(DUST.get(Lithium, MULTIPLE_LARGE))).io(GTCoreItems.BatteryLargeLithium.getDefaultInstance()).add("battery_large_lithium",1600, 2);
    }
}
