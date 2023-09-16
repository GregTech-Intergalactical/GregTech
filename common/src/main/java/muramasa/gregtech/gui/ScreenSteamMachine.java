package muramasa.gregtech.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.screen.ScreenMachine;
import muramasa.gregtech.blockentity.single.BlockEntitySteamMachine;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ScreenSteamMachine<T extends BlockEntitySteamMachine, U extends ContainerBasicMachine<BlockEntitySteamMachine>> extends ScreenMachine<BlockEntitySteamMachine, U> {

    public ScreenSteamMachine(U container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
        super.renderLabels(stack, mouseX, mouseY);
        drawTooltipInArea(stack, container.getTile().getMachineState().getDisplayName(), mouseX, mouseY, (imageWidth / 2) - 5, 45, 10, 8);
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(stack, partialTicks, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        /*if (container.getTile().has(MachineFlag.ITEM)) {
            addButton(new SwitchButtonWidget(gui, guiLeft + 26, guiTop + 63, 16, 16, ITEM, (b, s) -> {
                Antimatter.NETWORK.sendToServer(container.getTile().createGuiPacket(GuiEvent.ITEM_EJECT, s ? 1 : 0));
            },container.getTile().coverHandler.map(t -> COVEROUTPUT.shouldOutputItems(t.get(t.getOutputFacing()))).orElse(false)));
        }
        if (container.getTile().has(MachineFlag.FLUID)) {
            addButton(new SwitchButtonWidget(gui, guiLeft + 8, guiTop + 63, 16, 16, FLUID, (b, s) -> {
                Antimatter.NETWORK.sendToServer(container.getTile().createGuiPacket(GuiEvent.FLUID_EJECT, s ? 1 : 0));
            },container.getTile().coverHandler.map(t -> COVEROUTPUT.shouldOutputFluids(t.get(t.getOutputFacing()))).orElse(false)));
        }*/
    }
}
