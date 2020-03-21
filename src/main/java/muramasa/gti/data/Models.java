package muramasa.gti.data;

import muramasa.antimatter.client.AntimatterModelManager;

import static muramasa.gti.common.Data.*;

public class Models {

    public static void init() {
        AntimatterModelManager.put(COIL_NICHROME, (b, p, x) -> p.state(b, x.model("simple", "mc:block/bedrock")));

        AntimatterModelManager.put(CASING_FUSION_1, (b, p, x) -> p.state(b, x.basicConfig(CASING_FUSION_1, Textures.FUSION_1_CT)));
        AntimatterModelManager.put(CASING_FUSION_2, (b, p, x) -> p.state(b, x.basicConfig(CASING_FUSION_2, Textures.FUSION_2_CT)));
        AntimatterModelManager.put(CASING_FUSION_3, (b, p, x) -> p.state(b, x.basicConfig(CASING_FUSION_3, Textures.FUSION_3_CT)));
    }
}
