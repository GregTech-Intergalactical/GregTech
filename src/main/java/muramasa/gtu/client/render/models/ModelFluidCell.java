//package muramasa.gtu.client.render.models;
//
//import com.google.common.collect.ImmutableList;
//import muramasa.gtu.Ref;
//import muramasa.antimatter.client.ModelUtils;
//import muramasa.gtu.client.render.bakedmodels.BakedFluidCell;
//import net.minecraft.client.renderer.model.BakedQuad;
//import net.minecraft.client.renderer.model.IBakedModel;
//import net.minecraft.client.renderer.model.ModelBakery;
//import net.minecraft.client.renderer.texture.ISprite;
//import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//import net.minecraft.client.renderer.vertex.VertexFormat;
//import net.minecraft.fluid.Fluid;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.client.model.IModel;
//
//import java.util.List;
//import java.util.function.Function;
//
//public class ModelFluidCell implements IModel<ModelFluidCell> {
//
//    private static ResourceLocation BASE = new ResourceLocation(Ref.MODID, "items/fluid_cell");
//    private static IBakedModel BAKED_BASE, BAKED_OVERLAY;
//
//    private Fluid fluid;
//
//    public ModelFluidCell() {
//        //Default Constructor
//    }
//
//    public ModelFluidCell(Fluid fluid) {
//        this.fluid = fluid;
//    }
//
//    @Override
//    public IBakedModel bake(ModelBakery bakery, Function<ResourceLocation, TextureAtlasSprite> spriteGetter, ISprite sprite, VertexFormat format) {
//        if (BAKED_BASE == null) BAKED_BASE = ModelUtils.load(Ref.MODID, "fluid_cell_model").bake(bakery, spriteGetter, sprite, format);
//        if (BAKED_OVERLAY == null) BAKED_OVERLAY = ModelUtils.load(Ref.MODID, "fluid_cell_overlay_model").bake(bakery, spriteGetter, sprite, format);
//
//        ImmutableList.Builder<BakedQuad> builder = ImmutableList.builder();
//
//        if (fluid != null) {
//            TextureAtlasSprite fluidSprite = spriteGetter.apply(fluid.getAttributes().getStillTexture());
//            if (fluidSprite != null) {
//                List<BakedQuad> quads = BAKED_OVERLAY.getQuads(null, null, Ref.RNG);
//                quads = ModelUtils.texAndTint(quads, fluid.getAttributes().getColor(), fluidSprite);
//                builder.addAll(quads);
//            }
//        }
//        builder.addAll(BAKED_BASE.getQuads(null, null, Ref.RNG));
//
//        return new BakedFluidCell(builder.build(), this, spriteGetter.apply(BASE), format);
//    }
//
////    @Override
////    public Collection<ResourceLocation> getTextures() {
////        return Collections.singletonList(BASE);
////    }
//}
