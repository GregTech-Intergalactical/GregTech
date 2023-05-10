package muramasa.gregtech.data;

import muramasa.antimatter.material.MaterialTag;
import muramasa.antimatter.material.tags.DoubleMaterialTag;

public class GregTechMaterialTags {
    public static final MaterialTag ELEC = new MaterialTag("elec"); //Add Electrolyzer Recipes - SHOULD NOT SHARE MATS WITH CENT
    public static final MaterialTag CENT = new MaterialTag("cent"); //Add Centrifuging Recipes - SHOULD NOT SHARE MATS WITH ELEC
    public static final MaterialTag CRACK = new MaterialTag("crack"); //Add Cracking Recipes
    public static final MaterialTag SMELTG = new MaterialTag("smelt_g"); //Add Smelting to Gem Recipes
    public static final MaterialTag SMELTF = new MaterialTag("smelt_f"); //Add Smelting to Fluid Recipes
    public static final MaterialTag GRINDABLE = new MaterialTag("grindable"); //Is Grindable with the Mortar
    public static final MaterialTag CRYSTALLIZE = new MaterialTag("crystallize"); //Allows Dust > Gem Recipes
    public static final MaterialTag CALCITE2X = new MaterialTag("calcite_2x"); //Blast Furnace Calcite 2x Multiplier
    public static final MaterialTag CALCITE3X = new MaterialTag("calcite_3x"); //Blast Furnace Calcite 3x Multiplier
    public static final MaterialTag NOSMASH = new MaterialTag("no_smash"); //Material is not able to be smashed
    public static final MaterialTag NOSMELT = new MaterialTag("no_smelt"); //Material is not able to be smelted
    public static final MaterialTag WASHM = new MaterialTag("wash_m"); //Adds Crushed > ByProducts with Mercury
    public static final MaterialTag WASHS = new MaterialTag("wash_s"); //Adds Crushed > ByProducts with Sodium
    public static final MaterialTag NOBBF = new MaterialTag("no_ebf"); //Stops Dust > Ingot in BBF
    public static final MaterialTag ELECSEPI = new MaterialTag("elec_sep_i");
    public static final MaterialTag ELECSEPG = new MaterialTag("elec_sep_g");
    public static final MaterialTag ELECSEPN = new MaterialTag("elec_sep_n");
    public static final MaterialTag SOLDER = new MaterialTag("solder"); //Can be used in Soldering Recipes
    public static final MaterialTag BRITTLEG = new MaterialTag("brittle_g"); //This is for Gems that cannot be used in recipes such as Gem > Rod in Lathe
    public static final MaterialTag DECAYABLE = new MaterialTag("decayable");
    public static final DoubleMaterialTag CHEMBATH_MERCURY = new DoubleMaterialTag("chembath_mercury");
    public static final DoubleMaterialTag CHEMBATH_PERSULFATE = new DoubleMaterialTag("chembath_persulfate");
}
