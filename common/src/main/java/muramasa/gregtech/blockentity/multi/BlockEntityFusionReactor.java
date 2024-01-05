package muramasa.gregtech.blockentity.multi;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.fluid.FluidTank;
import muramasa.antimatter.capability.machine.DefaultHeatHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.texture.Texture;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.TesseractGraphWrappers;

import static muramasa.antimatter.data.AntimatterMaterials.Water;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gregtech.data.Materials.Steam;

public class BlockEntityFusionReactor extends BlockEntityMultiMachine<BlockEntityFusionReactor> {

    Display display = Display.REGULAR;

    public BlockEntityFusionReactor(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.heatHandler.set(() -> new DefaultHeatHandler(this, 32768, 8192, 0));
        this.recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            @Override
            protected boolean canRecipeContinue() {
                return super.canRecipeContinue() && heatHandler.map(h -> h.getHeat() + (activeRecipe.getPower() * activeRecipe.getDuration()) <= h.getHeatCap()).orElse(false);
            }

            @Override
            public boolean consumeResourceForRecipe(boolean simulate) {
                return super.consumeResourceForRecipe(simulate) && heatHandler.map(h -> h.insert(8192, simulate) > 0).orElse(false);
            }
        });
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        fluidHandler.ifPresent(f -> {
            if (f.getInputTanks() == null) return;
            heatHandler.ifPresent(h -> {
                if (h.getHeat() >= 80){
                    int recipeOffset = recipeHandler.map(r -> r.getLastRecipe().hasOutputFluids() ? r.getLastRecipe().getOutputFluids().length : 0).orElse(0);
                    int heatMultiplier = h.getHeat() / 80;
                    FluidTank waterTank = f.getInputTanks().getTank(f.getInputTanks().getFirstAvailableTank(Water.getLiquid(1), true));
                    if (waterTank != null) {
                        heatMultiplier = (int) Math.min(heatMultiplier, waterTank.getTankAmount() / TesseractGraphWrappers.dropletMultiplier);
                        if (waterTank.extractFluid(Water.getLiquid(heatMultiplier), true).getFluidAmount() == heatMultiplier *  TesseractGraphWrappers.dropletMultiplier) {
                            if (f.getOutputTanks() != null && f.getOutputTanks().getSize() >= recipeOffset + 1) {
                                long inserted = f.getOutputTanks().getTank(recipeOffset).internalInsert(Steam.getGas(heatMultiplier * 160), true);
                                if (inserted >= TesseractGraphWrappers.dropletMultiplier){
                                    heatMultiplier = (int) Math.min(heatMultiplier, inserted / TesseractGraphWrappers.dropletMultiplier);
                                    f.drainInput(Water.getLiquid(heatMultiplier), false);
                                    f.getOutputTanks().getTank(recipeOffset).internalInsert(Steam.getGas(heatMultiplier * 160), false);
                                    h.extract(heatMultiplier * 80, false);
                                }
                            }
                        }
                    }
                }
            });

        });
    }

//    @Override
//    public void onRecipeFound() {
//        consumeEnergy(activeRecipe.getSpecialValue());
//        System.out.println("Consumed Starting Energy");
//    }

    public Display getDisplay() {
        return display;
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("display", display.ordinal());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.display = Display.values()[tag.getInt("display")];
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        super.onGuiEvent(event, playerEntity);
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){
            GuiEvents.GuiEvent ev =(GuiEvents.GuiEvent) event;
            int[] data = ev.data;
            if (data[1] == 0){
                this.display = Display.REGULAR;
            } else if (data[1] == 1){
                this.display = Display.MIDDLE;
            } else if (data[1] == 2){
                this.display = Display.TOP_BOTTOM;
            }
        }
    }

    public Texture getTextureForHatches(Direction dir, BlockPos hatchPos){
        return new Texture(GTIRef.ID, "block/casing/fusion_1");
    }

    @Override
    public int guiHeight() {
        return 182;
    }

    @Override
    public int drawInfo(InfoRenderWidget.MultiRenderWidget instance, PoseStack stack, Font renderer, int left, int top) {
        return 0;
    }

    public enum Display{
        REGULAR,
        MIDDLE,
        TOP_BOTTOM
    }
}
