package muramasa.gregtech.gui.widgets;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.Widget;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.widget.SwitchButtonWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.gui.ButtonOverlays;
import muramasa.gregtech.tile.single.TileEntityItemFilter;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FilterButtonArrayWidget extends Widget {
    protected SwitchButtonWidget energyWidget;
    protected SwitchButtonWidget redstoneControlWidget;
    protected SwitchButtonWidget invertRedstoneWidget;
    protected SwitchButtonWidget blacklistWidget;
    protected SwitchButtonWidget nbtWidget;
    ResourceLocation background = new ResourceLocation(GTIRef.ID, "textures/gui/button/filter_button_background.png");
    boolean blacklist = false;
    boolean nbt = true;
    boolean outputRedstone = false;
    boolean invertRedstone = false;
    boolean emitEnergy = false;
    protected FilterButtonArrayWidget(@Nonnull GuiInstance instance, @Nullable IGuiElement parent) {
        super(instance, parent);
        this.setX(7);
        this.setY(62);
        this.setW(90);
        this.setH(18);
        this.energyWidget = (SwitchButtonWidget) SwitchButtonWidget.build(ButtonOverlays.ENERGY_OFF, ButtonOverlays.ENERGY_ON, h -> cast(h).isEmitEnergy(), GuiEvents.EXTRA_BUTTON,0).setSize(1, 1, 16, 16).buildAndAdd(instance, this);
        this.energyWidget.setDepth(depth() + 1);
        this.redstoneControlWidget = (SwitchButtonWidget) SwitchButtonWidget.build(ButtonOverlays.REDSTONE_CONTROL_OFF, ButtonOverlays.REDSTONE_CONTROL_ON, h -> cast(h).isOutputRedstone(), GuiEvents.EXTRA_BUTTON,1).setSize(19, 1, 16, 16).buildAndAdd(instance, this);
        this.redstoneControlWidget.setDepth(depth() + 1);
        this.invertRedstoneWidget = (SwitchButtonWidget) SwitchButtonWidget.build(ButtonOverlays.INVERT_REDSTONE_OFF, ButtonOverlays.INVERT_REDSTONE_ON, h -> cast(h).isInvertRedstone(), GuiEvents.EXTRA_BUTTON,2).setSize(37, 1, 16, 16).buildAndAdd(instance, this);
        this.invertRedstoneWidget.setDepth(depth() + 1);
        this.blacklistWidget = (SwitchButtonWidget) SwitchButtonWidget.build(ButtonOverlays.BLACKLIST_OFF, ButtonOverlays.BLACKLIST_ON, h -> cast(h).isBlacklist(), GuiEvents.EXTRA_BUTTON,3).setSize(55, 1, 16, 16).buildAndAdd(instance, this);
        this.blacklistWidget.setDepth(depth() + 1);
        this.nbtWidget = (SwitchButtonWidget) SwitchButtonWidget.build(ButtonOverlays.NBT_OFF, ButtonOverlays.NBT_ON, h -> cast(h).isNbt(), GuiEvents.EXTRA_BUTTON,4).setSize(73, 1, 16, 16).buildAndAdd(instance, this);
        this.nbtWidget.setDepth(depth() + 1);
    }

    private TileEntityItemFilter cast(IGuiHandler h){
        return (TileEntityItemFilter) h;
    }

    @Override
    public void render(PoseStack matrixStack, double mouseX, double mouseY, float partialTicks) {
        drawTexture(matrixStack, background, realX(), realY(), 0, 0, 90, 18, 90, 18);
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
        /*ContainerMachine<?> m = (ContainerMachine<?>) gui.container;
        TileEntityItemFilter filter = (TileEntityItemFilter) m.getTile();
        gui.syncBoolean(filter::isBlacklist, b -> blacklist = b, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        gui.syncBoolean(filter::isNbt, b -> nbt = b, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        gui.syncBoolean(filter::isOutputRedstone, b -> outputRedstone = b, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        gui.syncBoolean(filter::isInvertRedstone, b -> invertRedstone = b, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        gui.syncBoolean(filter::isEmitEnergy, b -> emitEnergy = b, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);*/
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }
}