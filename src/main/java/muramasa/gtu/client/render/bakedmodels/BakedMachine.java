package muramasa.gtu.client.render.bakedmodels;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import muramasa.antimatter.client.baked.BakedBase;
import muramasa.antimatter.cover.Cover;
import muramasa.antimatter.texture.TextureData;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static muramasa.antimatter.AntimatterProperties.*;

public class BakedMachine extends BakedBase {

    public static IBakedModel BASE;
    public static IBakedModel[][] OVERLAYS;
    public static IBakedModel[] OVERLAY_EMPTY;
    public static Object2ObjectOpenHashMap<String, IBakedModel> ITEMS = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectOpenHashMap<String, IBakedModel> COVERS = new Object2ObjectOpenHashMap<>();

    @Override
    public List<BakedQuad> getBakedQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData data) {
        List<BakedQuad> quads = new LinkedList<>();
        if (!data.hasProperty(MACHINE_TYPE) || !data.hasProperty(MACHINE_FACING)) return quads;

        int type = data.getData(MACHINE_TYPE).getInternalId();
        int facing = data.getData(MACHINE_FACING).getIndex();
        TextureData textureData = data.getData(MACHINE_TEXTURE);
        Cover[] covers = data.getData(MACHINE_COVER);

        if (covers != null) {
            for (int s = 0; s < 6; s++) {
                if (!covers[s].isEmpty()) {
                    //quads.addAll(covers[s].onRender(this, getCovers(covers[s], s, state, rand), s));
                } else {
                    //quads.addAll(getOverlays(type, s, textureData.getOverlay(), state, rand));
                }
            }
        } else {
            for (int s = 0; s < 6; s++) {
                //quads.addAll(getOverlays(type, s, textureData.getOverlay(), state, rand));
            }
        }

        //ModelUtils.tex(quads, TextureMode.SINGLE, textureData.getBase(), QuadLayer.BASE); //Machine Base
        //ModelUtils.tex(quads, TextureMode.SINGLE, textureData.getBase(), QuadLayer.COVER_BASE); //Cover Base
//        texOverlays(quads, data.getOverlayMode(), data.getOverlay());`
        //quads = ModelUtils.trans(quads, facing);

        return quads;
    }

//    public List<BakedQuad> getOverlays(int t, int s, Texture[] data, BlockState state, Random rand) {
//        return OVERLAYS[t][s] != null ? ModelUtils.tex(OVERLAYS[t][s].getQuads(state, null, rand), QuadLayer.OVERLAY, data[s]) : OVERLAY_EMPTY[s].getQuads(state, null, rand);
//    }
//
//    public List<BakedQuad> getCovers(Cover cover, int s, BlockState state, Random rand) {
//        return ModelUtils.trans(COVERS.get(cover.getId()).getQuads(state, null, rand), s);
//    }
}
