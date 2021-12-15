package muramasa.gregtech.block.tree;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.Locale;

public enum ResinState implements StringRepresentable {

    NONE,
    EMPTY,
    FILLED;

    public static final EnumProperty<ResinState> INSTANCE = EnumProperty.create("resin_state", ResinState.class);

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
