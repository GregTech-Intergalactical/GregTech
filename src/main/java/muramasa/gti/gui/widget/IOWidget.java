package muramasa.gti.gui.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.ButtonOverlay;
import muramasa.antimatter.gui.container.AntimatterContainer;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.event.GuiEvent;
import muramasa.antimatter.gui.screen.AntimatterContainerScreen;
import muramasa.antimatter.gui.widget.AbstractSwitchWidget;
import muramasa.antimatter.gui.widget.ButtonWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.util.int4;
import muramasa.gti.Ref;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static muramasa.antimatter.Data.COVEROUTPUT;
import static muramasa.antimatter.gui.widget.AntimatterWidget.builder;

public class IOWidget extends AbstractSwitchWidget {

    protected final Widget item;
    protected final Widget fluid;
    private static final int4 itemLoc = new int4(174, 17, 16, 16), fluidLoc = new int4(175, 35, 16, 16);

    protected IOWidget(AntimatterContainerScreen<?> screen, IGuiHandler handler, int x, int y, int w, int h) {
        super(screen, handler, new ResourceLocation("gti", "textures/gui/button/gui_buttons.png"), ButtonOverlay.INPUT_OUTPUT, IOWidget::handler, ((ContainerMachine<?>)screen.getContainer()).getTile().coverHandler.map(t -> COVEROUTPUT.shouldOutputFluids(t.get(t.getOutputFacing()))).orElse(false));
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.item = ButtonWidget.build(new ResourceLocation(Ref.ID, "textures/gui/button/gui_buttons.png"), screen.sourceGui(), itemLoc, null, GuiEvent.ITEM_EJECT,0).setSize(x+26, y, w, h).cast().get(screen, handler);
        this.fluid = ButtonWidget.build(new ResourceLocation(Ref.ID, "textures/gui/button/gui_buttons.png"), screen.sourceGui(), fluidLoc, null, GuiEvent.FLUID_EJECT,0).setSize(x+44, y, w, h).cast().get(screen, handler);
        ((ButtonWidget)item).setStateHandler(wid -> ((ContainerMachine<?>) wid.screen().getContainer()).getTile().coverHandler.map(t -> COVEROUTPUT.shouldOutputItems(t.getOutputCover())).orElse(false));
        ((ButtonWidget)fluid).setStateHandler(wid -> ((ContainerMachine<?>) wid.screen().getContainer()).getTile().coverHandler.map(t -> COVEROUTPUT.shouldOutputFluids(t.getOutputCover())).orElse(false));
        item.active = false;
        fluid.active = false;
        screen.widgetsFromData.add(item);
        screen.widgetsFromData.add(fluid);
    }

    private static void handler(AbstractSwitchWidget widget, boolean state) {
        IOWidget wid = (IOWidget) widget;
        wid.item.active = state;
        wid.fluid.active = state;
    }

    public static <T extends AntimatterContainer> WidgetSupplier<T> build(int x, int y, int w, int h) {
        return builder(((screen1, handler) -> new IOWidget(screen1, handler, x, y, w, h)));
    }

    @Override
    public void renderWidget(@Nonnull MatrixStack stack, int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        super.renderWidget(stack, p_renderButton_1_, p_renderButton_2_, p_renderButton_3_);
        if (state()) {
            item.renderWidget(stack, p_renderButton_1_, p_renderButton_2_, p_renderButton_3_);
            fluid.renderWidget(stack, p_renderButton_1_, p_renderButton_2_, p_renderButton_3_);
        }
    }
}
