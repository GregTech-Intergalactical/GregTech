//package muramasa.gtu.client.render.bakedmodels;
//
//import muramasa.antimatter.GregTechProperties;
//import muramasa.antimatter.texture.TextureData;
//import muramasa.antimatter.client.ModelUtils;
//import muramasa.antimatter.client.QuadLayer;
//import net.minecraft.block.BlockState;
//import net.minecraft.client.renderer.model.BakedQuad;
//import net.minecraft.util.Direction;
//import net.minecraftforge.common.property.IExtendedBlockState;
//
//import javax.annotation.Nullable;
//import java.util.LinkedList;
//import java.util.List;
//
////TODO if enough people want it
//public class BakedMachineBasic extends BakedMachine {
//
//    @Override
//    public List<BakedQuad> getBakedQuads(@Nullable BlockState state, @Nullable Direction side, long rand) {
//        IExtendedBlockState exState = (IExtendedBlockState) state;
//        int side = exState.getValue(GregTechProperties.FACING);
//        TextureData data = exState.getValue(GregTechProperties.TEXTURE);
//
//        List<BakedQuad> quads = new LinkedList<>(BASE.getQuads(state, side, rand));
//        ModelUtils.tex(quads, data.getBaseMode(), data.getBase(), QuadLayer.BASE);
//
////        Cover[] covers;
////        List<BakedQuad> coverQuads = null;
////        if (Utils.hasUnlistedProperty(exState, GTProperties.COVER) && (covers = exState.getValue(GTProperties.COVER)) != null) {
////            Texture[] overlays = data.getOverlay();
////            for (int i = 0; i < covers.length; i++) {
////                if (!covers[i].isEmpty()) {
//////                    overlays[i].setEmpty();
////                    coverQuads = Utils.trans(COVER[covers[i].getInternalId()].getQuads(state, side, rand), i);
////                    if (covers[i].retextureToMachineTier()) {
////                        Utils.tex(coverQuads, 0, RenderHelper.getSprite(Tier.getTransform(exState.getValue(GTProperties.TIER)).getBaseTexture()));
////                    }
////                    coverQuads.addAll(covers[i].onRender(coverQuads));
////                }
////            }
////            Utils.tex(quads, data.getOverlayMode(), overlays, 1);
////        } else if (data.hasOverlays()) {
////            Utils.tex(quads, data.getOverlayMode(), data.getOverlay(), 1);
////        }
//
//        if (side > 2) quads = ModelUtils.trans(quads, side);
////        if (coverQuads != null) quads.addAll(coverQuads);
//
//        return quads;
//    }
//}
