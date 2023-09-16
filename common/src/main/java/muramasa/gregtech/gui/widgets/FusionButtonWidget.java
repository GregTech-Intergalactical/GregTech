package muramasa.gregtech.gui.widgets;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.ICanSyncData;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.Widget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jeirei.AntimatterJEIREIPlugin;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.blockentity.multi.BlockEntityFusionReactor;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FusionButtonWidget extends Widget {
    ResourceLocation middle = new ResourceLocation(GTIRef.ID, "textures/gui/background/fusion_computer_middle_overlay.png");
    ResourceLocation top_bottom = new ResourceLocation(GTIRef.ID, "textures/gui/background/fusion_computer_top_bottom_overlay.png");

    BlockEntityFusionReactor.Display display = BlockEntityFusionReactor.Display.REGULAR;

    protected FusionButtonWidget(@Nonnull GuiInstance gui, @Nullable IGuiElement parent) {
        super(gui, parent);
        setW(176);
        setH(182);
    }

    @Override
    public void init() {
        gui.syncInt(() -> getTile().getDisplay().ordinal(), i -> this.display = BlockEntityFusionReactor.Display.values()[i], ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
    }

    private BlockEntityFusionReactor getTile() {
        return (BlockEntityFusionReactor) gui.handler;
    }

    @Override
    public void onClick(double mouseX, double mouseY, int button) {
        super.onClick(mouseX, mouseY, button);
        if (isInside(154, 4, 18, 18, mouseX, mouseY)){
            AntimatterJEIREIPlugin.showCategory(getTile().getMachineType(), getTile().getMachineTier());
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return isInside(154, 4, 18, 18, mouseX, mouseY) && super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void mouseOver(PoseStack stack, double mouseX, double mouseY, float partialTicks) {
        super.mouseOver(stack, mouseX, mouseY, partialTicks);
        if (isInside(154, 4, 18, 18, mouseX, mouseY)){
            renderTooltip(stack, Utils.translatable("antimatter.gui.show_recipes"), mouseX, mouseY);
        }
    }

    public boolean isInside(int x, int y, int w, int h, double mouseX, double mouseY) {
        int realX = realX() + x;
        int realY = realY() + y;
        return ((mouseX >= realX && mouseX <= realX + w) && (mouseY >= realY && mouseY <= realY + h));
    }

    public static WidgetSupplier build() {
        return builder(FusionButtonWidget::new);
    }

    @Override
    public void render(PoseStack matrixStack, double mouseX, double mouseY, float partialTicks) {
        BlockEntityFusionReactor tile = getTile();
        if (display == BlockEntityFusionReactor.Display.REGULAR){
            drawTexture(matrixStack, gui.handler.getGuiTexture(), realX() + 154, realY() + 22, 176, 0, 18, 18);
        } else if (display == BlockEntityFusionReactor.Display.MIDDLE){
            drawTexture(matrixStack, gui.handler.getGuiTexture(), realX() + 154, realY() + 40, 176, 18, 18, 18);
            drawTexture(matrixStack, middle, realX() + 6, realY() + 6, 0, 0, 145, 145);
        } else {
            drawTexture(matrixStack, gui.handler.getGuiTexture(), realX() + 154, realY() + 58, 176, 36, 18, 18);
            drawTexture(matrixStack, top_bottom, realX() + 6, realY() + 6, 0, 0, 145, 145);
        }
    }
}
