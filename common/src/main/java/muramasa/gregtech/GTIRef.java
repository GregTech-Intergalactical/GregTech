package muramasa.gregtech;

import muramasa.antimatter.Ref;
import muramasa.antimatter.util.AntimatterPlatformUtils;

public class GTIRef {

    /** Mod Data **/
    public static final String ID = AntimatterPlatformUtils.isFabric() ? "gti" : "gregtech";
    public static final String NAME = "GregTech";
    public static final String VERSION = "0.0.1";
    public static final String ANTIMATTER = Ref.ID;
    public static final String ANTIMATTER_SHARED = Ref.SHARED_ID;

    /** Test Values **/
    public static boolean mixedOreYieldsTwoThirdsPureOre = false; //TODO 5U remnant, determine if needed
}
