package muramasa.gti.gui.widgets;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.ICanSyncData;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.Widget;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.gti.tile.single.TileEntityCoalBoiler;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static muramasa.antimatter.gui.ICanSyncData.SyncDirection.SERVER_TO_CLIENT;

public class CoalBoilerWidget extends Widget {
    private int heat = 0, maxHeat = 0, water = 0, steam = 0;

    protected CoalBoilerWidget(@Nonnull GuiInstance gui, @Nullable IGuiElement parent) {
        super(gui, parent);
    }

    public static WidgetSupplier build() {
        return builder(CoalBoilerWidget::new);
    }

    @Override
    public void init() {
        super.init();
        gui.syncInt(() -> ((TileEntityCoalBoiler)((ContainerMachine<?>)gui.container).getTile()).getHeat(), i -> heat = i, SERVER_TO_CLIENT);
        gui.syncInt(() -> ((TileEntityCoalBoiler)((ContainerMachine<?>)gui.container).getTile()).getMaxHeat(), i -> maxHeat = i, SERVER_TO_CLIENT);
        gui.syncInt(() -> ((ContainerMachine<?>)gui.container).getTile().fluidHandler.map(t -> t.getInputs()[0].getAmount()).orElse(0), i -> water = i, SERVER_TO_CLIENT);
        gui.syncInt(() -> ((ContainerMachine<?>)gui.container).getTile().fluidHandler.map(t -> t.getOutputs()[0].getAmount()).orElse(0), i -> steam = i, SERVER_TO_CLIENT);
    }

    @Override
    public void render(MatrixStack stack, double mouseX, double mouseY, float partialTicks) {
        if (water >= 1) {
            float per = (float) water / 16000;
            if (per > 1.0F) {
                per = 1.0F;
            }
            int lvl = (int) (per * (float) 54);
            if (lvl < 0) {
                return;
            }
            int y = (realY() + 54) - lvl;
            drawTexture(stack, gui.handler.getGuiTexture(), realX() + 13, y, gui.screen.getXSize() + 28, 54 - lvl, 10, lvl);

        }
        if (steam >= 1) {
            float per = (float) steam / 16000;
            if (per > 1.0F) {
                per = 1.0F;
            }
            int lvl = (int) (per * (float) 54);
            if (lvl < 0) {
                return;
            }
            int y = (realY() + 54) - lvl;
            drawTexture(stack, gui.handler.getGuiTexture(), realX(), y, gui.screen.getXSize() + 18, 54 - lvl, 10, lvl);
        }
        if (heat >= 1) {
            float per = (float) heat / maxHeat;
            if (per > 1.0F) {
                per = 1.0F;
            }
            int lvl = (int) (per * (float) 54);
            if (lvl < 0) {
                return;
            }
            int y = (gui.screen.getGuiTop() + 25 + 54) - lvl;
            drawTexture(stack, gui.handler.getGuiTexture(), realX() + 26, y, gui.screen.getXSize() + 38, 54 - lvl, 10, lvl);
        }
    }

    @Override
    public void mouseOver(MatrixStack stack, double mouseX, double mouseY, float partialTicks) {
        if (water >= 1) {
            renderTooltip(stack,"Water: " + water + " MB", mouseX, mouseY, 14, 0, 10, 54);
        }
        if (steam >= 1) {
            renderTooltip(stack,"Steam: " + steam + " MB", mouseX, mouseY, 0, 0, 10, 54);
        }
        renderTooltip(stack,"Heat: " + heat + "K out of " + maxHeat, mouseX, mouseY, 26, 0, 10, 54);
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderTooltip(MatrixStack matrixStack, String text, double mouseX, double mouseY, int x, int y, int w, int h) {
        if (isInside(x, y, w, h, mouseX, mouseY)){
            renderTooltip(matrixStack, new StringTextComponent(text), mouseX, mouseY);
        }

    }

    public boolean isInside(int x, int y, int w, int h, double mouseX, double mouseY) {
        int realX = realX() + x;
        int realY = realY() + y;
        return ((mouseX >= realX && mouseX <= realX + w) && (mouseY >= realY && mouseY <= realY + h));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }
}
