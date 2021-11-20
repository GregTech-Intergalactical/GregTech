package muramasa.gti.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.gui.ButtonBody;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.screen.ScreenMachine;
import muramasa.gti.tile.single.TileEntitySteamMachine;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class ScreenSteamMachine<T extends TileEntitySteamMachine<T>, U extends ContainerBasicMachine<T>> extends ScreenMachine<T, U> {

    private final static ButtonBody FLUID = new ButtonBody("fluid_eject", 8, 63, 169, -44,16, 16);
    private final static ButtonBody ITEM = new ButtonBody("item_eject", 26, 63, 151, -26,16, 16);

    public ScreenSteamMachine(U container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }

    @Override
    protected void renderLabels(MatrixStack stack, int mouseX, int mouseY) {
        super.renderLabels(stack, mouseX, mouseY);
        drawTooltipInArea(stack, container.getTile().getMachineState().getDisplayName(), mouseX, mouseY, (imageWidth / 2) - 5, 45, 10, 8);
    }

    @Override
    protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(stack, partialTicks, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        /*if (container.getTile().has(MachineFlag.ITEM)) {
            addButton(new SwitchWidget(gui, guiLeft + 26, guiTop + 63, 16, 16, ITEM, (b, s) -> {
                Antimatter.NETWORK.sendToServer(container.getTile().createGuiPacket(GuiEvent.ITEM_EJECT, s ? 1 : 0));
            },container.getTile().coverHandler.map(t -> COVEROUTPUT.shouldOutputItems(t.get(t.getOutputFacing()))).orElse(false)));
        }
        if (container.getTile().has(MachineFlag.FLUID)) {
            addButton(new SwitchWidget(gui, guiLeft + 8, guiTop + 63, 16, 16, FLUID, (b, s) -> {
                Antimatter.NETWORK.sendToServer(container.getTile().createGuiPacket(GuiEvent.FLUID_EJECT, s ? 1 : 0));
            },container.getTile().coverHandler.map(t -> COVEROUTPUT.shouldOutputFluids(t.get(t.getOutputFacing()))).orElse(false)));
        }*/
    }
}
