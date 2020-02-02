package muramasa.gtu.client;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.client.AntimatterModelManager;
import muramasa.antimatter.client.model.ModelDynamic;
import muramasa.antimatter.datagen.builder.AntimatterBlockModelBuilder;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.PipeType;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import muramasa.gtu.data.Textures;

import static muramasa.antimatter.blocks.pipe.BlockPipe.getPipeID;
import static muramasa.gtu.common.Data.CASING_FUSION_3;
import static muramasa.gtu.common.Data.COIL_NICHROME;
import static net.minecraft.util.Direction.*;

public class Models {

    public static void init() {
        //ModelLoader.addSpecialModel(new ResourceLocation(Ref.ID, "block/preset/simple"));
        //ModelLoader.addSpecialModel(new ResourceLocation(Ref.ID, "block/preset/layered"));


        AntimatterModelManager.addOverride(COIL_NICHROME, (b, p) -> {
            p.simpleBlock(b, p.getBuilder(b).loader(AntimatterModelManager.DEFAULT_LOADER).model("minecraft:block/bedrock"));
        });

        AntimatterModelManager.addOverride(CASING_FUSION_3, (b, p) -> {
            p.simpleBlock(b, basic(p.getBuilder(b).loader(AntimatterModelManager.DYNAMIC_LOADER), CASING_FUSION_3, Textures.FUSION_3_CT));
        });

//        AntimatterModelLoader.put(COIL_NICHROME, b -> new AntimatterModel(b.tex("all", "mc:block/bedrock").tex("up", "mc:block/diamond_block")));
//
//        AntimatterModelLoader.put(CASING_FUSION_1, $ -> new ModelDynamic(CASING_FUSION_1).config(m -> basic(m, FUSION_1_CT)));
//        AntimatterModelLoader.put(CASING_FUSION_2, $ -> new ModelDynamic(CASING_FUSION_2).config(m -> basic(m, FUSION_2_CT)));
//        AntimatterModelLoader.put(CASING_FUSION_3, $ -> new ModelDynamic(CASING_FUSION_3).config(m -> basic(m, FUSION_3_CT)));
//
//        Function<ModelBuilder, AntimatterModel> modelPipe = $ -> new ModelDynamic().config(Models::pipe).bake((l, d, p) -> new BakedDynamic(l, d, p).onlyNullSide());
//        AntimatterAPI.all(BlockPipe.class).forEach(p -> AntimatterModelLoader.put(p, modelPipe));
    }

    public static AntimatterBlockModelBuilder basic(AntimatterBlockModelBuilder b, ITextureProvider textureProvider, Texture[] tex) {
        if (tex.length < 13) return b;

        b.model(textureProvider.getTextures());

        //Single (1)
        b.config(1, tex[12], tex[12], tex[1], tex[1], tex[1], tex[1]);
        b.config(2, tex[12], tex[12], tex[1], tex[1], tex[1], tex[1]);
        b.config(4, tex[1], tex[1], tex[0], tex[12], tex[0], tex[0]);
        b.config(8, tex[1], tex[1], tex[12], tex[0], tex[0], tex[0]);
        b.config(16, tex[0], tex[0], tex[0], tex[0], tex[0], tex[12]);
        b.config(32, tex[0], tex[0], tex[0], tex[0], tex[12], tex[0]);

        //Lines (2)
        b.config(3, tex[12], tex[12], tex[1], tex[1], tex[1], tex[1]);
        b.config(12, tex[1], tex[1], tex[12], tex[12], tex[0], tex[0]);
        b.config(48, tex[0], tex[0], tex[0], tex[0], tex[12], tex[12]);

        //Elbows (2)
        b.config(6, tex[1], tex[12], tex[0], tex[1], tex[10], tex[11]);
        b.config(5, tex[12], tex[1], tex[12], tex[1], tex[9], tex[8]);
        b.config(9, tex[12], tex[1], tex[1], tex[12], tex[8], tex[9]);
        b.config(10, tex[1], tex[12], tex[1], tex[12], tex[11], tex[10]);
        b.config(17, tex[12], tex[0], tex[8], tex[9], tex[12], tex[1]);
        b.config(18, tex[0], tex[12], tex[11], tex[10], tex[12], tex[1]);
        b.config(33, tex[12], tex[0], tex[9], tex[8], tex[1], tex[12]);
        b.config(34, tex[0], tex[12], tex[10], tex[11], tex[1], tex[10]);
        b.config(20, tex[10], tex[10], tex[0], tex[0], tex[0], tex[0]);
        b.config(24, tex[9], tex[9], tex[0], tex[0], tex[0], tex[0]);
        b.config(36, tex[11], tex[11], tex[0], tex[0], tex[0], tex[0]);
        b.config(40, tex[8], tex[8], tex[0], tex[0], tex[0], tex[0]);

        //Side (3)
        b.config(7, tex[12], tex[12], tex[12], tex[1], tex[4], tex[2]);
        b.config(11, tex[12], tex[12], tex[1], tex[12], tex[2], tex[4]);
        b.config(13, tex[12], tex[1], tex[12], tex[12], tex[3], tex[3]);
        b.config(14, tex[1], tex[12], tex[12], tex[12], tex[5], tex[5]);
        b.config(19, tex[12], tex[12], tex[2], tex[4], tex[12], tex[1]);
        b.config(28, tex[4], tex[4], tex[12], tex[12], tex[12], tex[0]);
        b.config(35, tex[12], tex[12], tex[4], tex[2], tex[1], tex[12]);
        b.config(44, tex[2], tex[2], tex[12], tex[12], tex[0], tex[12]);
        b.config(49, tex[12], tex[0], tex[3], tex[3], tex[12], tex[12]);
        b.config(50, tex[0], tex[12], tex[5], tex[5], tex[12], tex[12]);
        b.config(52, tex[3], tex[5], tex[12], tex[0], tex[12], tex[12]);
        b.config(56, tex[5], tex[3], tex[0], tex[12], tex[12], tex[12]);

        //Corner (3)
        b.config(21, tex[10], tex[10], tex[0], tex[9], tex[0], tex[8]);
        b.config(22, tex[10], tex[10], tex[0], tex[10], tex[0], tex[11]);
        b.config(25, tex[9], tex[9], tex[8], tex[0], tex[0], tex[9]);
        b.config(26, tex[9], tex[9], tex[11], tex[0], tex[0], tex[10]);
        b.config(37, tex[11], tex[11], tex[0], tex[8], tex[9], tex[0]);
        b.config(38, tex[11], tex[11], tex[0], tex[11], tex[10], tex[0]);
        b.config(41, tex[8], tex[8], tex[9], tex[0], tex[8], tex[0]);
        b.config(42, tex[8], tex[8], tex[10], tex[0], tex[11], tex[0]);

        //Arrow (4)
        b.config(23, tex[12], tex[12], tex[12], tex[4], tex[12], tex[2]);
        b.config(27, tex[12], tex[12], tex[2], tex[12], tex[12], tex[4]);
        b.config(29, tex[12], tex[4], tex[12], tex[12], tex[12], tex[3]);
        b.config(30, tex[4], tex[12], tex[12], tex[12], tex[12], tex[5]);
        b.config(39, tex[12], tex[12], tex[12], tex[2], tex[4], tex[12]);
        b.config(43, tex[12], tex[12], tex[4], tex[12], tex[2], tex[12]);
        b.config(45, tex[12], tex[2], tex[12], tex[12], tex[3], tex[12]);
        b.config(46, tex[2], tex[12], tex[12], tex[12], tex[5], tex[12]);
        b.config(53, tex[12], tex[5], tex[12], tex[3], tex[12], tex[12]);
        b.config(54, tex[3], tex[12], tex[12], tex[5], tex[12], tex[12]);
        b.config(57, tex[12], tex[3], tex[3], tex[12], tex[12], tex[12]);
        b.config(58, tex[5], tex[12], tex[5], tex[12], tex[12], tex[12]);

        //Cross (4)
        b.config(15, tex[12], tex[12], tex[12], tex[12], tex[6], tex[6]);
        b.config(51, tex[12], tex[12], tex[6], tex[6], tex[12], tex[12]);
        b.config(60, tex[6], tex[6], tex[12], tex[12], tex[12], tex[12]);

        //Five (5)
        b.config(31, tex[12], tex[12], tex[12], tex[12], tex[12], tex[6]);
        b.config(47, tex[12], tex[12], tex[12], tex[12], tex[6], tex[12]);
        b.config(55, tex[12], tex[12], tex[12], tex[6], tex[12], tex[12]);
        b.config(59, tex[12], tex[12], tex[6], tex[12], tex[12], tex[12]);
        b.config(61, tex[12], tex[6], tex[12], tex[12], tex[12], tex[12]);
        b.config(62, tex[6], tex[12], tex[12], tex[12], tex[12], tex[12]);

        //All (6)
        b.config(63, tex[12], tex[12], tex[12], tex[12], tex[12], tex[12]);

        return b;
    }

    public static void pipe(ModelDynamic model) {
        for (PipeType t : AntimatterAPI.all(PipeType.class)) {
            for (PipeSize s : PipeSize.VALUES) {
                for (int c = 0; c < 2; c++) {
                    int cc = c; //Cause Java
                    //Default Shape (0 Connections)
                    model.add(getPipeID(0, s, t, c), b -> b.of(s.getLoc("base", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));

                    //Single Shapes (1 Connections)
                    model.add(getPipeID(1, s, t, c), b -> b.of(s.getLoc("single", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(DOWN));
                    model.add(getPipeID(2, s, t, c), b -> b.of(s.getLoc("single", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(UP));
                    model.add(getPipeID(4, s, t, c), b -> b.of(s.getLoc("single", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));
                    model.add(getPipeID(8, s, t, c), b -> b.of(s.getLoc("single", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH));
                    model.add(getPipeID(16, s, t, c), b -> b.of(s.getLoc("single", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST));
                    model.add(getPipeID(32, s, t, c), b -> b.of(s.getLoc("single", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST));

                    //Line Shapes (2 Connections)
                    model.add(getPipeID(3, s, t, c), b -> b.of(s.getLoc("line", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(UP));
                    model.add(getPipeID(12, s, t, c), b -> b.of(s.getLoc("line", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));
                    model.add(getPipeID(48, s, t, c), b -> b.of(s.getLoc("line", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST));

                    //Elbow Shapes (2 Connections)
                    model.add(getPipeID(5, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, UP, EAST));
                    model.add(getPipeID(6, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, DOWN, EAST));
                    model.add(getPipeID(9, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, UP, EAST));
                    model.add(getPipeID(10, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, DOWN, EAST));
                    model.add(getPipeID(17, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(NORTH, DOWN, WEST));
                    model.add(getPipeID(18, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH, DOWN, EAST));
                    model.add(getPipeID(20, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST));
                    model.add(getPipeID(24, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH));
                    model.add(getPipeID(33, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(NORTH, UP, EAST));
                    model.add(getPipeID(34, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(NORTH, DOWN, EAST));
                    model.add(getPipeID(36, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));
                    model.add(getPipeID(40, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST));

                    //Side Shapes (3 Connections)
                    model.add(getPipeID(7, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH, UP));
                    model.add(getPipeID(11, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(NORTH, UP));
                    model.add(getPipeID(13, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(DOWN, DOWN));
                    model.add(getPipeID(14, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));
                    model.add(getPipeID(19, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, UP));
                    model.add(getPipeID(28, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, DOWN, EAST));
                    model.add(getPipeID(35, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, UP));
                    model.add(getPipeID(44, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, DOWN, WEST));
                    model.add(getPipeID(49, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, DOWN, DOWN));
                    model.add(getPipeID(50, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST));
                    model.add(getPipeID(52, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(NORTH, DOWN, WEST));
                    model.add(getPipeID(56, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH, DOWN, WEST));

                    //Corner Shapes (3 Connections)
                    model.add(getPipeID(21, s, t, c), b -> b.of(s.getLoc("corner", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, DOWN));
                    model.add(getPipeID(22, s, t, c), b -> b.of(s.getLoc("corner", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST));
                    model.add(getPipeID(25, s, t, c), b -> b.of(s.getLoc("corner", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH, DOWN));
                    model.add(getPipeID(26, s, t, c), b -> b.of(s.getLoc("corner", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH));
                    model.add(getPipeID(41, s, t, c), b -> b.of(s.getLoc("corner", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, DOWN));
                    model.add(getPipeID(42, s, t, c), b -> b.of(s.getLoc("corner", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST));
                    model.add(getPipeID(37, s, t, c), b -> b.of(s.getLoc("corner", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(NORTH, DOWN));
                    model.add(getPipeID(38, s, t, c), b -> b.of(s.getLoc("corner", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));

                    //Arrow Shapes (4 Connections)
                    model.add(getPipeID(23, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, DOWN, EAST));
                    model.add(getPipeID(27, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH, DOWN, EAST));
                    model.add(getPipeID(29, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, DOWN));
                    model.add(getPipeID(30, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST));
                    model.add(getPipeID(39, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, DOWN, WEST));
                    model.add(getPipeID(43, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH, DOWN, WEST));
                    model.add(getPipeID(45, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, DOWN));
                    model.add(getPipeID(46, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST));
                    model.add(getPipeID(53, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(DOWN));
                    model.add(getPipeID(54, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));
                    model.add(getPipeID(57, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH, DOWN));
                    model.add(getPipeID(58, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH));

                    //Cross Shapes (4 Connections)
                    model.add(getPipeID(15, s, t, c), b -> b.of(s.getLoc("cross", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, UP));
                    model.add(getPipeID(51, s, t, c), b -> b.of(s.getLoc("cross", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(UP));
                    model.add(getPipeID(60, s, t, c), b -> b.of(s.getLoc("cross", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));

                    //Five Shapes (5 Connections)
                    model.add(getPipeID(31, s, t, c), b -> b.of(s.getLoc("five", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, UP));
                    model.add(getPipeID(47, s, t, c), b -> b.of(s.getLoc("five", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, UP));
                    model.add(getPipeID(55, s, t, c), b -> b.of(s.getLoc("five", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH, UP));
                    model.add(getPipeID(59, s, t, c), b -> b.of(s.getLoc("five", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(NORTH, UP));
                    model.add(getPipeID(61, s, t, c), b -> b.of(s.getLoc("five", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(DOWN, DOWN));
                    model.add(getPipeID(62, s, t, c), b -> b.of(s.getLoc("five", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));

                    //All Shapes (6 Connections)
                    model.add(getPipeID(63, s, t, c), b -> b.of(s.getLoc("all", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));
                }
            }
        }
    }
}
