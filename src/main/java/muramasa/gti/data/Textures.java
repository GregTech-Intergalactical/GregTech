package muramasa.gti.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.TextureSet;
import muramasa.antimatter.texture.ITextureHandler;
import muramasa.antimatter.texture.Texture;
import muramasa.gti.Ref;

public class Textures {

    public static TextureSet DULL = AntimatterAPI.registerIfAbsent(TextureSet.class, "dull", () -> new TextureSet(Ref.ID, "dull"));
    public static TextureSet METALLIC = AntimatterAPI.registerIfAbsent(TextureSet.class, "metallic", () ->  new TextureSet(Ref.ID, "metallic"));
    public static TextureSet SHINY = AntimatterAPI.registerIfAbsent(TextureSet.class, "shiny", () ->  new TextureSet(Ref.ID, "shiny"));
    public static TextureSet ROUGH = AntimatterAPI.registerIfAbsent(TextureSet.class, "rough", () ->  new TextureSet(Ref.ID, "rough"));
    public static TextureSet MAGNETIC = AntimatterAPI.registerIfAbsent(TextureSet.class, "magnetic", () ->  new TextureSet(Ref.ID, "magnetic"));
    public static TextureSet DIAMOND = AntimatterAPI.registerIfAbsent(TextureSet.class, "diamond", () ->  new TextureSet(Ref.ID, "diamond"));
    public static TextureSet RUBY = AntimatterAPI.registerIfAbsent(TextureSet.class, "ruby", () ->  new TextureSet(Ref.ID, "ruby"));
    public static TextureSet LAPIS = AntimatterAPI.registerIfAbsent(TextureSet.class, "lapis", () ->  new TextureSet(Ref.ID, "lapis"));
    public static TextureSet GEM_H = AntimatterAPI.registerIfAbsent(TextureSet.class, "gem_h", () ->  new TextureSet(Ref.ID, "gem_h"));
    public static TextureSet GEM_V = AntimatterAPI.registerIfAbsent(TextureSet.class, "gem_v", () ->  new TextureSet(Ref.ID, "gem_v"));
    public static TextureSet QUARTZ = AntimatterAPI.registerIfAbsent(TextureSet.class, "quartz", () ->  new TextureSet(Ref.ID, "quartz"));
    public static TextureSet FINE = AntimatterAPI.registerIfAbsent(TextureSet.class, "fine", () ->  new TextureSet(Ref.ID, "fine"));
    public static TextureSet FLINT = AntimatterAPI.registerIfAbsent(TextureSet.class, "flint", () ->  new TextureSet(Ref.ID, "flint"));
    public static TextureSet LIGNITE = AntimatterAPI.registerIfAbsent(TextureSet.class, "lignite", () ->  new TextureSet(Ref.ID, "lignite"));

    public static final ITextureHandler BOILER_HANDLER = (m, t) -> new Texture[] {
        new Texture(Ref.ID, "block/machine/base/brick"),
        new Texture(Ref.ID, "block/machine/base/" + t.getId()),
        new Texture(Ref.ID, "block/machine/base/bricked_" + t.getId()),
        new Texture(Ref.ID, "block/machine/base/bricked_" + t.getId()),
        new Texture(Ref.ID, "block/machine/base/bricked_" + t.getId()),
        new Texture(Ref.ID, "block/machine/base/bricked_" + t.getId()),
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
