//package muramasa.gtu.client.events;
//
//import muramasa.antimatter.capability.GTCapabilities;
//import muramasa.antimatter.items.MaterialItem;
//import muramasa.antimatter.items.GregTechItem;
//import muramasa.antimatter.tools.GregTechToolType;
//import muramasa.antimatter.util.Utils;
//import muramasa.antimatter.client.RenderHelper;
//import net.minecraft.block.BlockState;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.BlockRendererDispatcher;
//import net.minecraft.client.renderer.BufferBuilder;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.Tessellator;
//import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
//import net.minecraft.init.Blocks;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.MathHelper;
//import net.minecraftforge.client.event.DrawBlockHighlightEvent;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import org.lwjgl.opengl.GL11;
//
//public class BlockHighlightHandler {
//
//    //TODO digest and implement ghost block rendering
//    //https://github.com/aidancbrady/Mekanism/blob/master/src/main/java/mekanism/client/render/RenderResizableCuboid.java
//
//    private static Minecraft MC = Minecraft.getMinecraft();
//
//    @SubscribeEvent
//    public static void onDrawBlockHighlight(DrawBlockHighlightEvent event) {
//        if (event.getPlayer() == null || event.getPlayer().world == null || event.getTarget().getBlockPos() == null) return;
//        TileEntity tile = Utils.getTile(event.getPlayer().world, event.getTarget().getBlockPos());
//        if (tile == null) return;
//        if (tile.hasCapability(GTCapabilities.CONFIGURABLE, null) || tile.hasCapability(GTCapabilities.COVERABLE, null)) {
//            ItemStack stack = event.getPlayer().getHeldItemMainhand();
//            if (stack.isEmpty()) return;
//            if (GregTechToolType.doesShowExtendedHighlight(stack) || GregTechItem.doesShowExtendedHighlight(stack) || MaterialItem.doesShowExtendedHighlight(stack)) {
//                drawGrid(event);
//            }
//        }
//    }
//
//    public static void drawMultiblockHighlight(DrawBlockHighlightEvent event) {
//        GlStateManager.pushMatrix();
//        GlStateManager.disableLighting();
//        GlStateManager.enableAlpha();
//
//        BlockState state = Blocks.STONE.getDefaultState();
//        BlockPos position = event.getTarget().getBlockPos().offset(event.getPlayer().getHorizontalFacing());
//        position.add(0.5, 0, 0.5);
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder buffer = tessellator.getBuffer();
//        buffer.begin(7, DefaultVertexFormats.BLOCK);
//        GlStateManager.translate(position.getX(), position.getY(), position.getZ());
//
//        BlockRendererDispatcher rendererDispatcher = MC.getBlockRendererDispatcher();
//        rendererDispatcher.getBlockModelRenderer().renderModel(event.getPlayer().world, rendererDispatcher.getModelForState(state), state, position, buffer, false, MathHelper.getPositionRandom(position));
//        tessellator.draw();
//
//        GlStateManager.enableLighting();
//        GlStateManager.popMatrix();
//    }
//
//    public static void drawGrid(DrawBlockHighlightEvent event) {
//        BlockPos pos = event.getTarget().getBlockPos();
//        GL11.glPushMatrix();
//        GL11.glTranslated(-(event.getPlayer().lastTickPosX + (event.getPlayer().posX - event.getPlayer().lastTickPosX) * (double) event.getPartialTicks()), -(event.getPlayer().lastTickPosY + (event.getPlayer().posY - event.getPlayer().lastTickPosY) * (double) event.getPartialTicks()), -(event.getPlayer().lastTickPosZ + (event.getPlayer().posZ - event.getPlayer().lastTickPosZ) * (double) event.getPartialTicks()));
//        GL11.glTranslated((float) pos.getX() + 0.5F, (float) pos.getY() + 0.5F, (float) pos.getZ() + 0.5F);
//        RenderHelper.applyGLRotationForSide(event.getTarget().sideHit);
//        GL11.glTranslated(0.0D, -0.501D, 0.0D);
//        GL11.glLineWidth(2.0F);
//        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.5F);
//        GL11.glBegin(1);
//        GL11.glVertex3d(0.5D, 0.0D, -0.25D);
//        GL11.glVertex3d(-0.5D, 0.0D, -0.25D);
//        GL11.glVertex3d(0.5D, 0.0D, 0.25D);
//        GL11.glVertex3d(-0.5D, 0.0D, 0.25D);
//        GL11.glVertex3d(0.25D, 0.0D, -0.5D);
//        GL11.glVertex3d(0.25D, 0.0D, 0.5D);
//        GL11.glVertex3d(-0.25D, 0.0D, -0.5D);
//        GL11.glVertex3d(-0.25D, 0.0D, 0.5D);
//        GL11.glEnd();
//        GL11.glPopMatrix();
//    }
//}
