package muramasa.gti.gui.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.ButtonOverlay;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.Widget;
import muramasa.antimatter.gui.container.AntimatterContainer;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.event.GuiEvent;
import muramasa.antimatter.gui.screen.AntimatterContainerScreen;
import muramasa.antimatter.gui.widget.AbstractSwitchWidget;
import muramasa.antimatter.gui.widget.ButtonWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.util.int4;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static muramasa.antimatter.Data.COVEROUTPUT;

public class IOWidget extends AbstractSwitchWidget {

    protected ButtonWidget item;
    protected ButtonWidget fluid;
    private static final int4 itemLoc = new int4(174, 17, 16, 16), fluidLoc = new int4(175, 35, 16, 16);

    private boolean itemState = false;
    private boolean fluidState = false;

    protected IOWidget(GuiInstance instance, int x, int y, int w, int h) {
        //TODO: Move textures around
        super(instance, new ResourceLocation(instance.handler.getDomain(), "textures/gui/button/gui_buttons.png"), ButtonOverlay.INPUT_OUTPUT, IOWidget::handler, ((ContainerMachine<?>)instance.container).getTile().coverHandler.map(t -> COVEROUTPUT.shouldOutputFluids(t.get(t.getOutputFacing()))).orElse(false));
        this.setX(x);
        this.setY(y);
        this.setW(w);
        this.setH(h);
        this.item = (ButtonWidget) ButtonWidget.build(new ResourceLocation(instance.handler.getDomain(), "textures/gui/button/gui_buttons.png"), instance.handler.getGuiTexture(), itemLoc, null, GuiEvent.ITEM_EJECT,0).setSize(x+26, y, w, h).get().get(instance);
        this.fluid = (ButtonWidget) ButtonWidget.build(new ResourceLocation(instance.handler.getDomain(), "textures/gui/button/gui_buttons.png"), instance.handler.getGuiTexture(), fluidLoc, null, GuiEvent.FLUID_EJECT,0).setSize(x+44, y, w, h).get().get(instance);
        item.setStateHandler(wid -> itemState);
        fluid.setStateHandler(wid -> fluidState);
        item.setEnabled(false);
        fluid.setEnabled(false);
        instance.addWidget(item);
        instance.addWidget(fluid);
    }

    @Override
    public void init() {
        super.init();
        ContainerMachine<?> m = (ContainerMachine<?>) gui.container;
        gui.syncBoolean(() -> (m.getTile().coverHandler.map(t -> COVEROUTPUT.shouldOutputItems(t.getOutputCover())).orElse(false)), this::setItem);
        gui.syncBoolean(() -> (m.getTile().coverHandler.map(t -> COVEROUTPUT.shouldOutputFluids(t.getOutputCover())).orElse(false)), this::setFluid);
    }

    private static void handler(AbstractSwitchWidget widget, boolean state) {
        IOWidget wid = (IOWidget) widget;
        wid.item.setEnabled(state);
        wid.fluid.setEnabled(state);
    }

    private void setItem(boolean item) {
        this.itemState = item;
    }

    private void setFluid(boolean item) {
        this.fluidState = item;
    }

    public static WidgetSupplier build(int x, int y, int w, int h) {
        return builder(i -> new IOWidget(i, x, y, w, h));
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (state()) {
            item.render(matrixStack, mouseX, mouseY, partialTicks);
            fluid.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }
}
