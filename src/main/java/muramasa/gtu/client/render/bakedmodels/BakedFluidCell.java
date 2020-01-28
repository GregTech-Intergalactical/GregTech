//package muramasa.gtu.client.render.bakedmodels;
//
//import muramasa.antimatter.client.ModelUtils;
//import muramasa.gtu.client.render.models.ModelFluidCell;
//import muramasa.gtu.client.render.overrides.ItemOverrideFluidCell;
//import net.minecraft.block.BlockState;
//import net.minecraft.client.renderer.model.BakedQuad;
//import net.minecraft.client.renderer.model.IBakedModel;
//import net.minecraft.client.renderer.model.ItemCameraTransforms;
//import net.minecraft.client.renderer.model.ItemOverrideList;
//import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//import net.minecraft.client.renderer.vertex.VertexFormat;
//import net.minecraft.util.Direction;
//import net.minecraftforge.client.model.data.IModelData;
//import org.apache.commons.lang3.tuple.Pair;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//import javax.vecmath.Matrix4f;
//import java.util.List;
//import java.util.Random;
//
//public class BakedFluidCell extends BakedBase {
//
//    private static ItemOverrideFluidCell OVERRIDE = new ItemOverrideFluidCell();
//
//    private final List<BakedQuad> quads;
//    private final TextureAtlasSprite particle;
//    public final ModelFluidCell parent;
//    public final VertexFormat format;
//
//    public BakedFluidCell(List<BakedQuad> quads, ModelFluidCell parent, TextureAtlasSprite particle, VertexFormat format) {
//        this.quads = quads;
//        this.parent = parent;
//        this.particle = particle;
//        this.format = format;
//    }
//
//    @Override
//    public List<BakedQuad> getBakedQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData data) {
//        return quads;
//    }
//
//    @Override
//    public TextureAtlasSprite getParticleTexture() {
//        return particle;
//    }
//
//    @Override
//    public ItemOverrideList getOverrides() {
//        return OVERRIDE;
//    }
//
//    @Override
//    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
//        return Pair.of(this, ModelUtils.getItemTransform(cameraTransformType));
//    }
//}
