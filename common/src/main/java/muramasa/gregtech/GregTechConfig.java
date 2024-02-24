package muramasa.gregtech;

import carbonconfiglib.config.Config;
import carbonconfiglib.config.ConfigEntry;
import carbonconfiglib.config.ConfigHandler;
import carbonconfiglib.config.ConfigSection;
import muramasa.antimatter.Ref;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class GregTechConfig {

    //TODO needed?
    public static boolean MORE_COMPLICATED_CHEMICAL_RECIPES = true;
    //TODO change to gt core's config for this
    public static boolean HARDER_CIRCUITS = false;
    public static ConfigEntry.BoolValue HARDER_ALUMINIUM_PROCESSING;
    public static ConfigEntry.BoolValue GT5U_OIL;
    public static ConfigEntry.BoolValue GT6_ORE_GEN;
    static ConfigHandler CONFIG;

    public static void createConfig(){
        Config config = new Config("gti");
        ConfigSection section = config.add("general");
        /*MORE_COMPLICATED_CHEMICAL_RECIPES = section.addBool("more_complicated_chemical_recipes", false, "Enables more complicated chemical recipes. - Default: false");
        HARDER_CIRCUITS = section.addBool("harder_circuits", false, "Enables more complicated circuit recipes added in versions of gt5u after 509.25 - Default: false");*/
        GT5U_OIL = section.addBool("gt5u_oil", false, "Enables gt5u oil processing, if false 6 oil processing is used instead. - Default: false");
        HARDER_ALUMINIUM_PROCESSING = section.addBool("harder_aluminium_processing", true, "Enables gt6's alumina processing, if disabled alumina reverts back to just being in the blast furnace - Default: true");
        GT6_ORE_GEN = section.addBool("gt6_ore_gen", false, "Enables gt6 style veins insteadof gt5 style veins. - Default: false");
        CONFIG = AntimatterPlatformUtils.createConfig(GTIRef.ID, config);
        CONFIG.register();
    }
}
