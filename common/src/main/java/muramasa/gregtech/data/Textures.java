package muramasa.gregtech.data;

import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.texture.IOverlayModeler;
import muramasa.antimatter.texture.IOverlayTexturer;
import muramasa.antimatter.texture.ITextureHandler;
import muramasa.antimatter.texture.Texture;
import muramasa.gregtech.GTIRef;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class Textures {

    public static final ITextureHandler BOILER_HANDLER = (m, t, s) -> new Texture[] {
        new Texture(GTIRef.ID, "block/machine/base/brick"),
        new Texture(GTIRef.ID, "block/machine/base/" + t.getId()),
        new Texture(GTIRef.ID, "block/machine/base/bricked_" + t.getId()),
        new Texture(GTIRef.ID, "block/machine/base/bricked_" + t.getId()),
        new Texture(GTIRef.ID, "block/machine/base/bricked_" + t.getId()),
        new Texture(GTIRef.ID, "block/machine/base/bricked_" + t.getId()),
    };

    public static final IOverlayTexturer LEFT_RIGHT_HANDLER = (type, state, tier, i) -> {
        if (state != MachineState.ACTIVE && state != MachineState.INVALID_STRUCTURE) state = MachineState.IDLE;
        String stateDir = state == MachineState.IDLE ? "" : state.getId() + "/";

        return new Texture[] {
                new Texture(GTIRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "bottom"),
                new Texture(GTIRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "top"),
                new Texture(GTIRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "back"),
                new Texture(GTIRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "front"),
                new Texture(GTIRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "right"),
                new Texture(GTIRef.ID, "block/machine/overlay/" + type.getId() + "/" + stateDir + "left"),
        };
    };

    public static final IOverlayTexturer MINI_NETHER_PORTAL = (type, state, tier, i) -> {
        if (state != MachineState.ACTIVE) state = MachineState.IDLE;
        if (state == MachineState.ACTIVE){
            return new Texture[] {
                    new Texture("block/nether_portal"),
                    new Texture("block/nether_portal"),
                    new Texture("block/nether_portal"),
                    new Texture("block/nether_portal"),
                    new Texture("block/nether_portal"),
                    new Texture("block/nether_portal"),
            };
        }
        return new Texture[] {
                new Texture(GTIRef.ID, "block/machine/empty"),
                new Texture(GTIRef.ID, "block/machine/empty"),
                new Texture(GTIRef.ID, "block/machine/empty"),
                new Texture(GTIRef.ID, "block/machine/empty"),
                new Texture(GTIRef.ID, "block/machine/empty"),
                new Texture(GTIRef.ID, "block/machine/empty"),
        };
    };

    public static final IOverlayTexturer MINI_END_PORTAL = (type, state, tier, i) -> {
        if (state != MachineState.ACTIVE) state = MachineState.IDLE;
        if (state == MachineState.ACTIVE){
            return new Texture[] {
                    new Texture("entity/end_portal"),
                    new Texture("entity/end_portal"),
                    new Texture("entity/end_portal"),
                    new Texture("entity/end_portal"),
                    new Texture("entity/end_portal"),
                    new Texture("entity/end_portal"),
            };
        }
        return new Texture[] {
                new Texture(GTIRef.ID, "block/machine/empty"),
                new Texture(GTIRef.ID, "block/machine/empty"),
                new Texture(GTIRef.ID, "block/machine/empty"),
                new Texture(GTIRef.ID, "block/machine/empty"),
                new Texture(GTIRef.ID, "block/machine/empty"),
                new Texture(GTIRef.ID, "block/machine/empty"),
        };
    };

    public static final IOverlayTexturer TIER_SPECIFIC_OVERLAY_HANDLER = (type, state, tier, i) -> {
        if (state != MachineState.ACTIVE && state != MachineState.INVALID_STRUCTURE) state = MachineState.IDLE;
        String stateDir = state == MachineState.IDLE ? "" : state.getId() + "/";

        return new Texture[] {
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + stateDir + "bottom"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + stateDir + "top"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + stateDir + "back"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + stateDir + "front"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + stateDir + "side"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + stateDir + "side"),
        };
    };

    public static final IOverlayTexturer STATE_IGNORANT_TIER_SPECIFIC_OVERLAY_HANDLER = (type, state, tier, i) -> {
        return new Texture[] {
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + "bottom"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + "top"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + "back"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + "front"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + "side"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + "side"),
        };
    };

    public static final IOverlayTexturer HATCH_OVERLAY_HANDLER = (type, state, tier, i) -> {
        return new Texture[] {
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + "side"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + "side"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + "side"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + tier.getId() + "/" + "front"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + "side"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + "side"),
        };
    };

    public static final IOverlayTexturer REACTOR_CORE_OVERLAY_HANDLER = (type, state, tier, i) -> {
        return new Texture[] {
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + "side"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + "top"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + "side"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + "side"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + "side"),
                new Texture(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + "side"),
        };
    };

    public static IOverlayModeler TURBINE = (type, state, side) -> {
        String suffix = "";
        if (side == Direction.SOUTH && state != MachineState.INVALID_STRUCTURE){
            suffix = "_" + state.getId();
        }
        return new ResourceLocation(type.getDomain(), "block/machine/overlay/" + type.getId() + "/" + side.getSerializedName() + suffix);
    };

    public static IOverlayModeler MINI_PORTAL = (a,s,d) -> new ResourceLocation(a.getDomain(), "block/machine/overlay/miniature_portal/" + d.getSerializedName());
}
