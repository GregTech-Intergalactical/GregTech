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

//    public static void pipe(ModelDynamic model) {
//        for (PipeType t : AntimatterAPI.all(PipeType.class)) {
//            for (PipeSize s : PipeSize.VALUES) {
//                for (int c = 0; c < 2; c++) {
//                    int cc = c; //Cause Java
//                    //Default Shape (0 Connections)
//                    model.add(getPipeID(0, s, t, c), b -> b.of(s.getLoc("base", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));
//
//                    //Single Shapes (1 Connections)
//                    model.add(getPipeID(1, s, t, c), b -> b.of(s.getLoc("single", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(DOWN));
//                    model.add(getPipeID(2, s, t, c), b -> b.of(s.getLoc("single", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(UP));
//                    model.add(getPipeID(4, s, t, c), b -> b.of(s.getLoc("single", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));
//                    model.add(getPipeID(8, s, t, c), b -> b.of(s.getLoc("single", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH));
//                    model.add(getPipeID(16, s, t, c), b -> b.of(s.getLoc("single", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST));
//                    model.add(getPipeID(32, s, t, c), b -> b.of(s.getLoc("single", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST));
//
//                    //Line Shapes (2 Connections)
//                    model.add(getPipeID(3, s, t, c), b -> b.of(s.getLoc("line", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(UP));
//                    model.add(getPipeID(12, s, t, c), b -> b.of(s.getLoc("line", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));
//                    model.add(getPipeID(48, s, t, c), b -> b.of(s.getLoc("line", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST));
//
//                    //Elbow Shapes (2 Connections)
//                    model.add(getPipeID(5, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, UP, EAST));
//                    model.add(getPipeID(6, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, DOWN, EAST));
//                    model.add(getPipeID(9, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, UP, EAST));
//                    model.add(getPipeID(10, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, DOWN, EAST));
//                    model.add(getPipeID(17, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(NORTH, DOWN, WEST));
//                    model.add(getPipeID(18, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH, DOWN, EAST));
//                    model.add(getPipeID(20, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST));
//                    model.add(getPipeID(24, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH));
//                    model.add(getPipeID(33, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(NORTH, UP, EAST));
//                    model.add(getPipeID(34, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(NORTH, DOWN, EAST));
//                    model.add(getPipeID(36, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));
//                    model.add(getPipeID(40, s, t, c), b -> b.of(s.getLoc("elbow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST));
//
//                    //Side Shapes (3 Connections)
//                    model.add(getPipeID(7, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH, UP));
//                    model.add(getPipeID(11, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(NORTH, UP));
//                    model.add(getPipeID(13, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(DOWN, DOWN));
//                    model.add(getPipeID(14, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));
//                    model.add(getPipeID(19, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, UP));
//                    model.add(getPipeID(28, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, DOWN, EAST));
//                    model.add(getPipeID(35, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, UP));
//                    model.add(getPipeID(44, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, DOWN, WEST));
//                    model.add(getPipeID(49, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, DOWN, DOWN));
//                    model.add(getPipeID(50, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST));
//                    model.add(getPipeID(52, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(NORTH, DOWN, WEST));
//                    model.add(getPipeID(56, s, t, c), b -> b.of(s.getLoc("side", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH, DOWN, WEST));
//
//                    //Corner Shapes (3 Connections)
//                    model.add(getPipeID(21, s, t, c), b -> b.of(s.getLoc("corner", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, DOWN));
//                    model.add(getPipeID(22, s, t, c), b -> b.of(s.getLoc("corner", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST));
//                    model.add(getPipeID(25, s, t, c), b -> b.of(s.getLoc("corner", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH, DOWN));
//                    model.add(getPipeID(26, s, t, c), b -> b.of(s.getLoc("corner", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH));
//                    model.add(getPipeID(41, s, t, c), b -> b.of(s.getLoc("corner", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, DOWN));
//                    model.add(getPipeID(42, s, t, c), b -> b.of(s.getLoc("corner", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST));
//                    model.add(getPipeID(37, s, t, c), b -> b.of(s.getLoc("corner", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(NORTH, DOWN));
//                    model.add(getPipeID(38, s, t, c), b -> b.of(s.getLoc("corner", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));
//
//                    //Arrow Shapes (4 Connections)
//                    model.add(getPipeID(23, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, DOWN, EAST));
//                    model.add(getPipeID(27, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH, DOWN, EAST));
//                    model.add(getPipeID(29, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, DOWN));
//                    model.add(getPipeID(30, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST));
//                    model.add(getPipeID(39, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, DOWN, WEST));
//                    model.add(getPipeID(43, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH, DOWN, WEST));
//                    model.add(getPipeID(45, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, DOWN));
//                    model.add(getPipeID(46, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST));
//                    model.add(getPipeID(53, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(DOWN));
//                    model.add(getPipeID(54, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));
//                    model.add(getPipeID(57, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH, DOWN));
//                    model.add(getPipeID(58, s, t, c), b -> b.of(s.getLoc("arrow", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH));
//
//                    //Cross Shapes (4 Connections)
//                    model.add(getPipeID(15, s, t, c), b -> b.of(s.getLoc("cross", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, UP));
//                    model.add(getPipeID(51, s, t, c), b -> b.of(s.getLoc("cross", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(UP));
//                    model.add(getPipeID(60, s, t, c), b -> b.of(s.getLoc("cross", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));
//
//                    //Five Shapes (5 Connections)
//                    model.add(getPipeID(31, s, t, c), b -> b.of(s.getLoc("five", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(EAST, UP));
//                    model.add(getPipeID(47, s, t, c), b -> b.of(s.getLoc("five", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(WEST, UP));
//                    model.add(getPipeID(55, s, t, c), b -> b.of(s.getLoc("five", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(SOUTH, UP));
//                    model.add(getPipeID(59, s, t, c), b -> b.of(s.getLoc("five", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(NORTH, UP));
//                    model.add(getPipeID(61, s, t, c), b -> b.of(s.getLoc("five", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)).rot(DOWN, DOWN));
//                    model.add(getPipeID(62, s, t, c), b -> b.of(s.getLoc("five", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));
//
//                    //All Shapes (6 Connections)
//                    model.add(getPipeID(63, s, t, c), b -> b.of(s.getLoc("all", cc)).tex("0", t.getSide()).tex("1", t.getFace(s)));
//                }
//            }
//        }
//    }
}
