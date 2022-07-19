package muramasa.gregtech.gui.widgets;

import muramasa.antimatter.gui.ButtonBody;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.ICanSyncData;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.Widget;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.gregtech.tile.single.TileEntityItemFilter;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.mojang.blaze3d.vertex.PoseStack;

public class FilterButtonArrayWidget extends Widget {
    protected ChangingButtonWidget energyWidget;
    protected ChangingButtonWidget redstoneControlWidget;
    protected ChangingButtonWidget invertRedstoneWidget;
    protected ChangingButtonWidget blacklistWidget;
    protected ChangingButtonWidget nbtWidget;
    boolean blacklist = false;
    boolean nbt = true;
    boolean outputRedstone = false;
    boolean invertRedstone = false;
    boolean emitEnergy = false;
    private static final ButtonBody energyLoc = new ButtonBody("",176, 80, 0, -80, 16, 16), redstoneControlLoc = new ButtonBody("", 176, 80, 0, -64, 16, 16), invertRedstoneLoc = new ButtonBody("",176, 80, 0, -48, 16, 16), blacklistLoc = new ButtonBody("",176, 80, 0, -32, 16, 16), nbtLoc = new ButtonBody("",176, 80, 0, -16, 16, 16);
    protected FilterButtonArrayWidget(@Nonnull GuiInstance instance, @Nullable IGuiElement parent) {
        super(instance, parent);
        this.setX(7);
        this.setY(62);
        this.setW(90);
        this.setH(18);
        this.setVisible(false);
        this.energyWidget = (ChangingButtonWidget) ChangingButtonWidget.build(instance.handler.getGuiTexture(), energyLoc, null, GuiEvents.EXTRA_BUTTON,0).setSize(1, 1, 16, 16).buildAndAdd(instance, this);
        this.energyWidget.setStateHandler(w -> emitEnergy).setDepth(depth() + 1);
        this.redstoneControlWidget = (ChangingButtonWidget) ChangingButtonWidget.build(instance.handler.getGuiTexture(), redstoneControlLoc, null, GuiEvents.EXTRA_BUTTON,1).setSize(19, 1, 16, 16).buildAndAdd(instance, this);
        this.redstoneControlWidget.setStateHandler(w -> outputRedstone).setDepth(depth() + 1);
        this.invertRedstoneWidget = (ChangingButtonWidget) ChangingButtonWidget.build(instance.handler.getGuiTexture(), invertRedstoneLoc, null, GuiEvents.EXTRA_BUTTON,2).setSize(37, 1, 16, 16).buildAndAdd(instance, this);
        this.invertRedstoneWidget.setStateHandler(w -> invertRedstone).setDepth(depth() + 1);
        this.blacklistWidget = (ChangingButtonWidget) ChangingButtonWidget.build(instance.handler.getGuiTexture(), blacklistLoc, null, GuiEvents.EXTRA_BUTTON,3).setSize(55, 1, 16, 16).buildAndAdd(instance, this);
        this.blacklistWidget.setStateHandler(w -> blacklist).setDepth(depth() + 1);
        this.nbtWidget = (ChangingButtonWidget) ChangingButtonWidget.build(instance.handler.getGuiTexture(), nbtLoc, null, GuiEvents.EXTRA_BUTTON,4).setSize(73, 1, 16, 16).buildAndAdd(instance, this);
        this.nbtWidget.setStateHandler(w -> !nbt).setDepth(depth() + 1);
    }

    @Override
    public void render(PoseStack matrixStack, double mouseX, double mouseY, float partialTicks) {

    }

    @Override
    public void updateSize() {
        super.updateSize();
        if (this.energyWidget != null) this.energyWidget.updateSize();
        if (this.redstoneControlWidget != null) this.redstoneControlWidget.updateSize();
        if (this.invertRedstoneWidget != null) this.invertRedstoneWidget.updateSize();
        if (this.blacklistWidget != null) this.blacklistWidget.updateSize();
        if (this.nbtWidget != null) this.nbtWidget.updateSize();
    }

    public static WidgetSupplier build() {
        return builder(FilterButtonArrayWidget::new);
    }

    @Override
    public void init() {
        super.init();
        ContainerMachine<?> m = (ContainerMachine<?>) gui.container;
        TileEntityItemFilter filter = (TileEntityItemFilter) m.getTile();
        gui.syncBoolean(filter::isBlacklist, b -> blacklist = b, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        gui.syncBoolean(filter::isNbt, b -> nbt = b, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        gui.syncBoolean(filter::isOutputRedstone, b -> outputRedstone = b, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        gui.syncBoolean(filter::isInvertRedstone, b -> invertRedstone = b, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        gui.syncBoolean(filter::isEmitEnergy, b -> emitEnergy = b, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }
}