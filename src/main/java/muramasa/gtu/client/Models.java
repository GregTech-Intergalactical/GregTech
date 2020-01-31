package muramasa.gtu.client;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.blocks.pipe.BlockPipe;
import muramasa.antimatter.client.AntimatterModelLoader;
import muramasa.antimatter.client.ModelBuilder;
import muramasa.antimatter.client.baked.BakedDynamic;
import muramasa.antimatter.client.model.AntimatterModel;
import muramasa.antimatter.client.model.ModelDynamic;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.PipeType;
import muramasa.antimatter.texture.Texture;
import muramasa.gtu.Ref;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import java.util.function.Function;

import static muramasa.antimatter.blocks.pipe.BlockPipe.getPipeID;
import static muramasa.gtu.common.Data.*;
import static muramasa.gtu.data.Textures.*;
import static net.minecraft.util.Direction.*;

public class Models {

    public static void init() {
        ModelLoader.addSpecialModel(new ResourceLocation(Ref.ID, "block/preset/simple"));
        ModelLoader.addSpecialModel(new ResourceLocation(Ref.ID, "block/preset/layered"));

        AntimatterModelLoader.put(COIL_NICHROME, b -> new AntimatterModel(b.tex("all", "mc:block/bedrock").tex("up", "mc:block/diamond_block")));

        AntimatterModelLoader.put(CASING_FUSION_1, $ -> new ModelDynamic(CASING_FUSION_1).config(m -> basic(m, FUSION_1_CT)));
        AntimatterModelLoader.put(CASING_FUSION_2, $ -> new ModelDynamic(CASING_FUSION_2).config(m -> basic(m, FUSION_2_CT)));
        AntimatterModelLoader.put(CASING_FUSION_3, $ -> new ModelDynamic(CASING_FUSION_3).config(m -> basic(m, FUSION_3_CT)));

        Function<ModelBuilder, AntimatterModel> modelPipe = $ -> new ModelDynamic().config(Models::pipe).bake((l, d, p) -> new BakedDynamic(l, d, p).onlyNullSide());
        AntimatterAPI.all(BlockPipe.class).forEach(p -> AntimatterModelLoader.put(p, modelPipe));
    }

    public static void basic(ModelDynamic model, Texture[] tex) {
        if (tex.length < 13) return;
        //Single (1)
        model.add(1, tex[12], tex[12], tex[1], tex[1], tex[1], tex[1]);
        model.add(2, tex[12], tex[12], tex[1], tex[1], tex[1], tex[1]);
        model.add(4, tex[1], tex[1], tex[0], tex[12], tex[0], tex[0]);
        model.add(8, tex[1], tex[1], tex[12], tex[0], tex[0], tex[0]);
        model.add(16, tex[0], tex[0], tex[0], tex[0], tex[0], tex[12]);
        model.add(32, tex[0], tex[0], tex[0], tex[0], tex[12], tex[0]);

        //Lines (2)
        model.add(3, tex[12], tex[12], tex[1], tex[1], tex[1], tex[1]);
        model.add(12, tex[1], tex[1], tex[12], tex[12], tex[0], tex[0]);
        model.add(48, tex[0], tex[0], tex[0], tex[0], tex[12], tex[12]);

        //Elbows (2)
        model.add(6, tex[1], tex[12], tex[0], tex[1], tex[10], tex[11]);
        model.add(5, tex[12], tex[1], tex[12], tex[1], tex[9], tex[8]);
        model.add(9, tex[12], tex[1], tex[1], tex[12], tex[8], tex[9]);
        model.add(10, tex[1], tex[12], tex[1], tex[12], tex[11], tex[10]);
        model.add(17, tex[12], tex[0], tex[8], tex[9], tex[12], tex[1]);
        model.add(18, tex[0], tex[12], tex[11], tex[10], tex[12], tex[1]);
        model.add(33, tex[12], tex[0], tex[9], tex[8], tex[1], tex[12]);
        model.add(34, tex[0], tex[12], tex[10], tex[11], tex[1], tex[10]);
        model.add(20, tex[10], tex[10], tex[0], tex[0], tex[0], tex[0]);
        model.add(24, tex[9], tex[9], tex[0], tex[0], tex[0], tex[0]);
        model.add(36, tex[11], tex[11], tex[0], tex[0], tex[0], tex[0]);
        model.add(40, tex[8], tex[8], tex[0], tex[0], tex[0], tex[0]);

        //Side (3)
        model.add(7, tex[12], tex[12], tex[12], tex[1], tex[4], tex[2]);
        model.add(11, tex[12], tex[12], tex[1], tex[12], tex[2], tex[4]);
        model.add(13, tex[12], tex[1], tex[12], tex[12], tex[3], tex[3]);
        model.add(14, tex[1], tex[12], tex[12], tex[12], tex[5], tex[5]);
        model.add(19, tex[12], tex[12], tex[2], tex[4], tex[12], tex[1]);
        model.add(28, tex[4], tex[4], tex[12], tex[12], tex[12], tex[0]);
        model.add(35, tex[12], tex[12], tex[4], tex[2], tex[1], tex[12]);
        model.add(44, tex[2], tex[2], tex[12], tex[12], tex[0], tex[12]);
        model.add(49, tex[12], tex[0], tex[3], tex[3], tex[12], tex[12]);
        model.add(50, tex[0], tex[12], tex[5], tex[5], tex[12], tex[12]);
        model.add(52, tex[3], tex[5], tex[12], tex[0], tex[12], tex[12]);
        model.add(56, tex[5], tex[3], tex[0], tex[12], tex[12], tex[12]);

        //Corner (3)
        model.add(21, tex[10], tex[10], tex[0], tex[9], tex[0], tex[8]);
        model.add(22, tex[10], tex[10], tex[0], tex[10], tex[0], tex[11]);
        model.add(25, tex[9], tex[9], tex[8], tex[0], tex[0], tex[9]);
        model.add(26, tex[9], tex[9], tex[11], tex[0], tex[0], tex[10]);
        model.add(37, tex[11], tex[11], tex[0], tex[8], tex[9], tex[0]);
        model.add(38, tex[11], tex[11], tex[0], tex[11], tex[10], tex[0]);
        model.add(41, tex[8], tex[8], tex[9], tex[0], tex[8], tex[0]);
        model.add(42, tex[8], tex[8], tex[10], tex[0], tex[11], tex[0]);

        //Arrow (4)
        model.add(23, tex[12], tex[12], tex[12], tex[4], tex[12], tex[2]);
        model.add(27, tex[12], tex[12], tex[2], tex[12], tex[12], tex[4]);
        model.add(29, tex[12], tex[4], tex[12], tex[12], tex[12], tex[3]);
        model.add(30, tex[4], tex[12], tex[12], tex[12], tex[12], tex[5]);
        model.add(39, tex[12], tex[12], tex[12], tex[2], tex[4], tex[12]);
        model.add(43, tex[12], tex[12], tex[4], tex[12], tex[2], tex[12]);
        model.add(45, tex[12], tex[2], tex[12], tex[12], tex[3], tex[12]);
        model.add(46, tex[2], tex[12], tex[12], tex[12], tex[5], tex[12]);
        model.add(53, tex[12], tex[5], tex[12], tex[3], tex[12], tex[12]);
        model.add(54, tex[3], tex[12], tex[12], tex[5], tex[12], tex[12]);
        model.add(57, tex[12], tex[3], tex[3], tex[12], tex[12], tex[12]);
        model.add(58, tex[5], tex[12], tex[5], tex[12], tex[12], tex[12]);

        //Cross (4)
        model.add(15, tex[12], tex[12], tex[12], tex[12], tex[6], tex[6]);
        model.add(51, tex[12], tex[12], tex[6], tex[6], tex[12], tex[12]);
        model.add(60, tex[6], tex[6], tex[12], tex[12], tex[12], tex[12]);

        //Five (5)
        model.add(31, tex[12], tex[12], tex[12], tex[12], tex[12], tex[6]);
        model.add(47, tex[12], tex[12], tex[12], tex[12], tex[6], tex[12]);
        model.add(55, tex[12], tex[12], tex[12], tex[6], tex[12], tex[12]);
        model.add(59, tex[12], tex[12], tex[6], tex[12], tex[12], tex[12]);
        model.add(61, tex[12], tex[6], tex[12], tex[12], tex[12], tex[12]);
        model.add(62, tex[6], tex[12], tex[12], tex[12], tex[12], tex[12]);

        //All (6)
        model.add(63, tex[12], tex[12], tex[12], tex[12], tex[12], tex[12]);
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
