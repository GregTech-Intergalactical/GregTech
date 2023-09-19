package muramasa.gregtech;

import muramasa.antimatter.Ref;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class GregTechConfig {
    public static final Gameplay GAMEPLAY = new Gameplay();
    //public static final ModCompatibility MOD_COMPAT = new ModCompatibility();

    public static final CommonConfig COMMON_CONFIG;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {

        final Pair<CommonConfig, ForgeConfigSpec> COMMON_PAIR = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON_CONFIG = COMMON_PAIR.getLeft();
        COMMON_SPEC = COMMON_PAIR.getRight();

    }

    public static void onModConfigEvent(final ModConfig config) {
        if (config.getModId().equals(GTIRef.ID)){
            if (config.getSpec() == COMMON_SPEC) bakeCommonConfig();
        }
    }

    public static class Gameplay {

        /** @see CommonConfig **/

        public boolean HARDER_WOOD, MORE_COMPLICATED_CHEMICAL_RECIPES, HARDER_CIRCUITS, HARDER_ALUMINIUM_PROCESSING;

    }

    public static class CommonConfig {

        public ForgeConfigSpec.BooleanValue HARDER_WOOD, MORE_COMPLICATED_CHEMICAL_RECIPES, HARDER_CIRCUITS;

        public ForgeConfigSpec.BooleanValue REPLACEMENT_VANILLA_ORE_GEN;

        public CommonConfig(ForgeConfigSpec.Builder builder) {

            builder.push("Gameplay");

            MORE_COMPLICATED_CHEMICAL_RECIPES = builder.comment("Enables more complicated chemical recipes. - Default: false")
                    .translation(GTIRef.ID + ".config.more_complicated_chemical_recipes")
                    .define("MORE_COMPLICATED_CHEMICAL_RECIPES", false);

            HARDER_WOOD = builder.comment("If true logs to planks and planks to sticks give half of vanilla amounts - Default: false")
                    .translation(GTIRef.ID + ".config.harder_wood")
                    .define("HARDER_WOOD", false);
            HARDER_CIRCUITS = builder.comment("Enables more complicated circuit recipes added in versions of gt5u after 509.25 - Default: false")
                    .translation(GTIRef.ID + ".config.harder_circuits")
                    .define("HARDER_CIRCUITS", false);
            builder.pop();

        }

    }

    private static void bakeCommonConfig() {
        GAMEPLAY.HARDER_WOOD = COMMON_CONFIG.HARDER_WOOD.get();
        GAMEPLAY.MORE_COMPLICATED_CHEMICAL_RECIPES = COMMON_CONFIG.MORE_COMPLICATED_CHEMICAL_RECIPES.get();
        GAMEPLAY.HARDER_CIRCUITS = COMMON_CONFIG.HARDER_CIRCUITS.get();

    }
}
