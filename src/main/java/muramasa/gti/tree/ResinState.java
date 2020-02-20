package muramasa.gti.tree;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum ResinState implements IStringSerializable {
    NONE,
    EMPTY,
    FILLED;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
