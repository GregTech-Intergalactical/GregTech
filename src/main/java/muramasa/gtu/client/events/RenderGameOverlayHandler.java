//package muramasa.gtu.client.events;
//
//import muramasa.gtu.Ref;
//import muramasa.antimatter.items.MaterialTool;
//import muramasa.antimatter.tileentities.TileEntityBase;
//import muramasa.antimatter.util.Utils;
//import muramasa.gtu.common.Data;
//import net.minecraft.client.Minecraft;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.text.TextFormatting;
//import net.minecraftforge.client.event.RenderGameOverlayEvent;
//import org.lwjgl.opengl.GL11;
//
//public class RenderGameOverlayHandler extends Gui {
//
//    private static Minecraft MC = Minecraft.getMinecraft();
//    private static ResourceLocation energyBar = new ResourceLocation(Ref.MODID, "textures/gui/energy_bar.png");
//
//    @SubscribeEvent
//    public static void onRenderDebugInfo(RenderGameOverlayEvent.Text e) {
//        if (MC.gameSettings.showDebugInfo && MC.objectMouseOver != null) {
//            TileEntity tile;
//            tile = Utils.getTile(FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(MC.world.provider.getDimension()), MC.objectMouseOver.getBlockPos());
//            if (tile instanceof TileEntityBase) {
//                e.getLeft().add("");
//                e.getLeft().add(TextFormatting.AQUA + "[GregTech Debug Server]");
//                e.getLeft().addAll(((TileEntityBase) tile).getInfo());
//            }
//            if (MC.player.isSneaking()) {
//                tile = Utils.getTile(MC.world, MC.objectMouseOver.getBlockPos());
//                if (tile instanceof TileEntityBase) {
//                    e.getLeft().add("");
//                    e.getLeft().add(TextFormatting.AQUA + "[GregTech Debug Client]");
//                    e.getLeft().addAll(((TileEntityBase) tile).getInfo());
//                }
//            }
//        }
//    }
//
//    @SubscribeEvent(receiveCanceled = true)
//    public static void onRenderGameOverlay(RenderGameOverlayEvent.Pre e) {
//        EntityPlayerSP entityPlayerSP = MC.player;
//        ItemStack stack = entityPlayerSP.getHeldItemMainhand();
//        if (!(stack.getItem() instanceof MaterialTool)) return;
//        MaterialTool tool = (MaterialTool) stack.getItem();
//        if (tool.getType().isPowered() && e.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE && !entityPlayerSP.isCreative()) {
//            e.setCanceled(true);
//        }
//    }
//
//    @SubscribeEvent(receiveCanceled = true)
//    public static void onRenderGameOverlay(RenderGameOverlayEvent.Post e) {
//        if (e.getType() == RenderGameOverlayEvent.ElementType.ALL) {
//            EntityPlayerSP entityPlayerSP = MC.player;
//            ItemStack stack = entityPlayerSP.getHeldItemMainhand();
//            if (!(stack.getItem() instanceof MaterialTool)) return;
//            MaterialTool tool = (MaterialTool) stack.getItem();
//            if (tool.getType().isPowered() && !entityPlayerSP.isCreative()) {
//                GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
//                GL11.glPushMatrix();
//                int x = (e.getResolution().getScaledWidth() / 2) - 91;
//                int y = e.getResolution().getScaledHeight() - 29;
//                MC.renderEngine.bindTexture(energyBar);
//                int energySize = (int)(180 * ((float) tool.getEnergy(stack) / (float) tool.getMaxEnergy(stack)));
//                drawModalRectWithCustomSizedTexture(x, y, 0, 0, 182, 5, 182, 15);
//                drawModalRectWithCustomSizedTexture(x + 1, y + 1, 0, 6, energySize, 3, 182, 15);
//                int iconU = energySize == 0 ? 4 : 0;
//                drawModalRectWithCustomSizedTexture(x + 89, y, iconU, 10, 4, 5, 182, 15);
//                GL11.glPopAttrib();
//                GL11.glPopMatrix();
//            } else if (Data.DebugScanner.isEqual(stack)) {
//
//            }
//        }
//    }
//}
