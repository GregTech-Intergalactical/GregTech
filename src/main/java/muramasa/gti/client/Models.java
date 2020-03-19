package muramasa.gti.client;

import muramasa.antimatter.client.AntimatterModelManager;
import muramasa.gti.data.Textures;

import static muramasa.gti.common.Data.*;

public class Models {

    public static void init() {
        AntimatterModelManager.put(COIL_NICHROME, (b, p, x) -> p.state(b, x.model("simple", "mc:block/bedrock")));

        AntimatterModelManager.put(CASING_FUSION_1, (b, p, x) -> p.state(b, x.basicConfig(CASING_FUSION_1, Textures.FUSION_1_CT)));
        AntimatterModelManager.put(CASING_FUSION_2, (b, p, x) -> p.state(b, x.basicConfig(CASING_FUSION_2, Textures.FUSION_2_CT)));
        AntimatterModelManager.put(CASING_FUSION_3, (b, p, x) -> p.state(b, x.basicConfig(CASING_FUSION_3, Textures.FUSION_3_CT)));

//        Function<ModelBuilder, AntimatterModel> modelPipe = $ -> new ModelDynamic().config(Models::pipe).bake((l, d, p) -> new BakedDynamic(l, d, p).onlyNullSide());
//        AntimatterAPI.all(BlockPipe.class).forEach(p -> AntimatterModelLoader.put(p, modelPipe));
    }
}
