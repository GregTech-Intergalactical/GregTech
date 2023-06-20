package muramasa.gregtech.loader.machines;

import muramasa.antimatter.util.AntimatterPlatformUtils;

public class FluidAmountUtils {
    public static long ingot(){
        return AntimatterPlatformUtils.isForge() ? 144 : 9000;
    }
    public static long nugget(){
        return AntimatterPlatformUtils.isForge() ? 16 : 1000;
    }

    public static long quarter(){
        return AntimatterPlatformUtils.isForge() ? 36 : 2250;
    }
}
