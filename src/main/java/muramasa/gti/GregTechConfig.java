package muramasa.gti;

import muramasa.antimatter.Ref;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

import static net.minecraftforge.common.ForgeConfigSpec.*;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class GregTechConfig {

    public static final Data DATA = new Data();

    public static final CommonConfig COMMON_CONFIG;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {

        final Pair<CommonConfig, ForgeConfigSpec> COMMON_PAIR = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON_CONFIG = COMMON_PAIR.getLeft();
        COMMON_SPEC = COMMON_PAIR.getRight();

    }

    @SubscribeEvent
    public static void onModConfigEvent(final ModConfig.ModConfigEvent e) {

        if (e.getConfig().getSpec() == COMMON_SPEC) bakeCommonConfig();

    }

    public static class Data {

        public boolean ITEM_REPLACEMENTS;

    }

    public static class CommonConfig {

        public final BooleanValue ITEM_REPLACEMENTS;

        public CommonConfig(Builder builder) {

            ITEM_REPLACEMENTS = builder.comment("Replace some GT items with other variants, eg: Vanilla's Gold Ingot - Default: true")
                    .translation(Ref.ID + ".config.item_replacements")
                    .define("ITEM_REPLACEMENTS", true);

        }

    }

    private static void bakeCommonConfig() {

        DATA.ITEM_REPLACEMENTS = COMMON_CONFIG.ITEM_REPLACEMENTS.get();

    }

}
