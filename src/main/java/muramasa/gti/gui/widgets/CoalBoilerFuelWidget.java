package muramasa.gti.gui.widgets;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.Widget;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jei.AntimatterJEIPlugin;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.gti.tile.single.TileEntityCoalBoiler;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static muramasa.antimatter.gui.ICanSyncData.SyncDirection.SERVER_TO_CLIENT;

public class CoalBoilerFuelWidget extends Widget {
    private int fuel = 0, maxFuel = 0;
    protected CoalBoilerFuelWidget(@Nonnull GuiInstance gui, @Nullable IGuiElement parent) {
        super(gui, parent);
    }

    public static WidgetSupplier build() {
        return builder(CoalBoilerFuelWidget::new);
    }

    @Override
    public void init() {
        super.init();
        gui.syncInt(() -> ((TileEntityCoalBoiler)((ContainerMachine<?>)gui.container).getTile()).getFuel(), i -> fuel = i, SERVER_TO_CLIENT);
        gui.syncInt(() -> ((TileEntityCoalBoiler)((ContainerMachine<?>)gui.container).getTile()).getMaxFuel(), i -> maxFuel = i, SERVER_TO_CLIENT);
    }

    @Override
    public void render(MatrixStack stack, double mouseX, double mouseY, float partialTicks) {
        if (fuel > 0) {
            float per = (float) fuel / maxFuel;
            if (per > 1.0F) {
                per = 1.0F;
            }
            int lvl = (int) (per * (float) 18);
            if (lvl < 0) {
                return;
            }
            int y = (realY() + 18) - lvl;
            drawTexture(stack, gui.handler.getGuiTexture(), realX(), y, gui.screen.getXSize(), 18 - lvl, 18, lvl);
        }
    }

    @Override
    public void mouseOver(MatrixStack stack, double mouseX, double mouseY, float partialTicks) {
        super.mouseOver(stack, mouseX, mouseY, partialTicks);
        renderTooltip(stack,"Show Recipes", mouseX, mouseY, 0, 0, 18, 18);
        renderTooltip(stack,"Fuel: " + fuel, mouseX, mouseY + 10, 0, 10, 18, 18);
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
    public void onClick(double mouseX, double mouseY, int button) {
        super.onClick(mouseX, mouseY, button);
        if (this.gui.handler instanceof TileEntityMachine) {
            AntimatterJEIPlugin.showCategory(((TileEntityMachine<?>)this.gui.handler).getMachineType());
        }
    }
}
