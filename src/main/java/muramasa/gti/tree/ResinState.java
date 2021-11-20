package muramasa.gti.tree;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum ResinState implements IStringSerializable {

    NONE,
    EMPTY,
    FILLED;

    public String getName() {
        return name().toLowerCase(Locale.ENGLISH);
    }

    @Override //Needed for generating BlockStates with the correct lower case name
    public String toString() {
        return getName();
    }

    @Override
    public String getSerializedName() {
        return getName();
    }
}
