package muramasa.gti.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.material.TextureSet;
import muramasa.antimatter.texture.IOverlayTexturer;
import muramasa.antimatter.texture.ITextureHandler;
import muramasa.antimatter.texture.Texture;
import muramasa.gti.Ref;

public class Textures {

    public static final ITextureHandler BOILER_HANDLER = (m, t) -> new Texture[] {
        new Texture(Ref.ID, "block/machine/base/brick"),
        new Texture(Ref.ID, "block/machine/base/" + t.getId()),
        new Texture(Ref.ID, "block/machine/base/bricked_" + t.getId()),
        new Texture(Ref.ID, "block/machine/base/bricked_" + t.getId()),
        new Texture(Ref.ID, "block/machine/base/bricked_" + t.getId()),
        new Texture(Ref.ID, "block/machine/base/bricked_" + t.getId()),
    };

    public static final IOverlayTexturer TIER_SPECIFIC_OVERLAY_HANDLER = (type, state, tier) -> {
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

    public static final Texture[] LARGE_TURBINE = new Texture[] {
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_0"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_1"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_2"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_3"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_4"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_5"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_6"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_7"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_8")
    };

    public static final Texture[] LARGE_TURBINE_ACTIVE = new Texture[] {
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_0"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_1"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_2"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_3"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_4"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_5"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_6"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_7"),
        new Texture(Ref.ID, "block/ct/turbine/large_turbine_active_8")
    };

    public static final Texture[] FUSION_1_CT = new Texture[] {
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_0"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_1"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_2"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_3"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_4"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_5"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_6"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_7"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_8"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_9"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_10"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_11"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_1_12")
    };

    public static final Texture[] FUSION_2_CT = new Texture[] {
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_0"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_1"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_2"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_3"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_4"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_5"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_6"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_7"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_8"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_9"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_10"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_11"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_2_12")
    };

    public static final Texture[] FUSION_3_CT = new Texture[] {
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_0"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_1"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_2"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_3"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_4"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_5"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_6"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_7"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_8"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_9"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_10"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_11"),
        new Texture(Ref.ID, "block/ct/fusion/fusion_3_12")
    };
}
