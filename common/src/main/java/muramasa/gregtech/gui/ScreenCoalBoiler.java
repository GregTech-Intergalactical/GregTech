package muramasa.gregtech.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.screen.ScreenMachine;
import muramasa.antimatter.integration.jeirei.AntimatterJEIREIPlugin;
import muramasa.antimatter.machine.MachineFlag;
import muramasa.gregtech.blockentity.single.BlockEntityCoalBoiler;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import tesseract.TesseractGraphWrappers;

public class ScreenCoalBoiler<T extends ContainerMachine<BlockEntityCoalBoiler>> extends ScreenMachine<BlockEntityCoalBoiler, T> {
    public ScreenCoalBoiler(T container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
        drawTitle(stack, mouseX, mouseY);
        if (container.getTile().has(MachineFlag.RECIPE)) {
            drawTooltipInArea(stack, "Show Recipes", mouseX, mouseY, 115, 43, 18, 18);
        }
        if (container.getTile().has(MachineFlag.FLUID)) {
            //TODO
            container.getTile().fluidHandler.ifPresent(t -> {
                FluidHolder[] inputs = t.getInputs();
                long water = inputs[0].getFluidAmount() / TesseractGraphWrappers.dropletMultiplier;
                if (water >= 1) {
                    drawTooltipInArea(stack,"Water: " + water + " MB", mouseX, mouseY, 84, 25, 10, 54);
                }
                FluidHolder[] outputs = t.getOutputs();
                long steam = outputs[0].getFluidAmount() / TesseractGraphWrappers.dropletMultiplier;
                if (steam >= 1) {
                    drawTooltipInArea(stack,"Steam: " + steam + " MB", mouseX, mouseY, 70, 25, 10, 54);
                }
            });
            if (container.getTile() != null){
                BlockEntityCoalBoiler tile = container.getTile();
                drawTooltipInArea(stack,"Heat: " + tile.getHeat() + "K out of " + tile.getMaxHeat(), mouseX, mouseY, 96, 25, 10, 54);
                drawTooltipInArea(stack,"Fuel: " + tile.getFuel(), mouseX, mouseY + 10, 115, 53, 18, 18);
            }
        }
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(stack, partialTicks, mouseX, mouseY);
        drawTitle(stack, mouseX, mouseY);
        ResourceLocation gui = container.source().handler.getGuiTexture();
        if (container.getTile().has(MachineFlag.FLUID)) {
            //TODO
            container.getTile().fluidHandler.ifPresent(t -> {
                FluidHolder[] inputs = t.getInputs();
                long water = inputs[0].getFluidAmount() / TesseractGraphWrappers.dropletMultiplier;
                if (water >= 1) {
                    float per = (float) water / 16000;
                    if (per > 1.0F) {
                        per = 1.0F;
                    }
                    int lvl = (int) (per * (float) 54);
                    if (lvl < 0) {
                        return;
                    }
                    int y = (topPos + 25 + 54) - lvl;
                    drawTexture(stack, gui, leftPos + 83, y, imageWidth + 28, 54 - lvl, 10, lvl);

                }
                FluidHolder[] outputs = t.getOutputs();
                long steam = outputs[0].getFluidAmount() / TesseractGraphWrappers.dropletMultiplier;
                if (steam >= 1) {
                    float per = (float) steam / 16000;
                    if (per > 1.0F) {
                        per = 1.0F;
                    }
                    int lvl = (int) (per * (float) 54);
                    if (lvl < 0) {
                        return;
                    }
                    int y = (topPos + 25 + 54) - lvl;
                    drawTexture(stack, gui, leftPos + 70, y, imageWidth + 18, 54 - lvl, 10, lvl);
                }
            });
            if (container.getTile() instanceof BlockEntityCoalBoiler){
                BlockEntityCoalBoiler tile = (BlockEntityCoalBoiler)container.getTile();
                int heat = tile.getHeat();
                if (heat >= 1) {
                    float per = (float) heat / tile.getMaxHeat();
                    if (per > 1.0F) {
                        per = 1.0F;
                    }
                    int lvl = (int) (per * (float) 54);
                    if (lvl < 0) {
                        return;
                    }
                    int y = (topPos + 25 + 54) - lvl;
                    drawTexture(stack, gui, leftPos + 96, y, imageWidth + 38, 54 - lvl, 10, lvl);
                }
                int fuel = tile.getFuel();
                if (fuel > 0) {
                    float per = (float) fuel / tile.getMaxFuel();
                    if (per > 1.0F) {
                        per = 1.0F;
                    }
                    int lvl = (int) (per * (float) 18);
                    if (lvl < 0) {
                        return;
                    }
                    int y = (topPos + 42 + 18) - lvl;
                    drawTexture(stack, gui, leftPos + 115, y, imageWidth, 18 - lvl, 18, lvl);
                }
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if ((!AntimatterAPI.isModLoaded(Ref.MOD_JEI) && !AntimatterAPI.isModLoaded(Ref.MOD_REI)) || !container.getTile().has(MachineFlag.RECIPE))
            return super.mouseClicked(mouseX, mouseY, mouseButton);
        if (isInGui((imageWidth / 2) - 10, 24, 20, 18, mouseX, mouseY)) {
            return false;
        }
        if (isInGui(115, 43, 18, 18, mouseX, mouseY)) {
            AntimatterJEIREIPlugin.showCategory(container.getTile().getMachineType(), container.getTile().getMachineTier());
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
