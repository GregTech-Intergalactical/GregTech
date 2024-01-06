package muramasa.gregtech.blockentity.multi;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.machine.DefaultHeatHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.ICanSyncData;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jeirei.renderer.IInfoRenderer;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.TesseractGraphWrappers;
import tesseract.api.heat.IHeatHandler;

import static muramasa.antimatter.data.AntimatterMaterials.Water;
import static muramasa.gregtech.data.Materials.Steam;

public class BlockEntityHeatExchanger extends BlockEntityMultiMachine<BlockEntityHeatExchanger> {

    @Override
    public int drawInfo(InfoRenderWidget.MultiRenderWidget instance, PoseStack stack, Font renderer, int left, int top) {
        int size = super.drawInfo(instance, stack, renderer, left, top);
        renderer.draw(stack, "Heat: " + ((HeatInfoWidget)instance).heat, left, top + size, 16448255);
        return size + 8;
    }

    @Override
    public WidgetSupplier getInfoWidget() {
        return HeatInfoWidget.build().setPos(10, 10);
    }

    public BlockEntityHeatExchanger(Machine type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        heatHandler.set(() -> new DefaultHeatHandler(this, 80 * 4, 80, 0));
        recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            @Override
            protected boolean canRecipeContinue() {
                return super.canRecipeContinue() && heatHandler.map(h -> h.getHeat() + (activeRecipe.getPower() * activeRecipe.getDuration()) <= h.getHeatCap()).orElse(false);
            }

            @Override
            public boolean canOutput() {
                //ignore chance for canOutput.
                if (tile.itemHandler.isPresent() && activeRecipe.hasOutputItems() && !tile.itemHandler.map(t -> t.canOutputsFit(activeRecipe.getFlatOutputItems())).orElse(false))
                    return false;
                return !tile.fluidHandler.isPresent() || !activeRecipe.hasOutputFluids() || tile.fluidHandler.map(t -> t.getOutputTanks() != null && t.getOutputTanks().getSize() > 1 && t.getOutputTanks().getTank(0).internalInsert(activeRecipe.getOutputFluids()[0], true) == activeRecipe.getOutputFluids()[0].getFluidAmount()).orElse(false);
            }

            @Override
            public boolean consumeResourceForRecipe(boolean simulate) {
                return heatHandler.map(h -> h.insert((int) activeRecipe.getPower(), simulate) > 0).orElse(false);
            }

            protected void addOutputs() {
                if (activeRecipe.hasOutputItems()) {
                    tile.itemHandler.ifPresent(h -> {
                        //Roll the chances here. If they don't fit add flat (no chances).
                        ItemStack[] out = activeRecipe.getOutputItems(true);
                        if (h.canOutputsFit(out)) {
                            h.addOutputs(out);
                        } else {
                            h.addOutputs(activeRecipe.getFlatOutputItems());
                        }
                        tile.onMachineEvent(MachineEvent.ITEMS_OUTPUTTED);
                    });
                }
                if (activeRecipe.hasOutputFluids()) {
                    tile.fluidHandler.ifPresent(h -> {
                        if (h.getOutputTanks() == null) return;
                        h.getOutputTanks().getTank(0).internalInsert(activeRecipe.getOutputFluids()[0], false);
                        tile.onMachineEvent(MachineEvent.FLUIDS_OUTPUTTED);
                    });
                }
            }
        });

    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        fluidHandler.ifPresent(f -> {
            heatHandler.ifPresent(h -> {
                if (h.getHeat() >= 80){
                    if (f.drainInput(Water.getLiquid(1), true).getFluidAmount() == TesseractGraphWrappers.dropletMultiplier){
                        if (f.getOutputTanks() != null && f.getOutputTanks().getSize() >= 2 && f.getOutputTanks().getTank(1).internalInsert(Steam.getGas(160), true) == 160 * TesseractGraphWrappers.dropletMultiplier){
                            f.drainInput(Water.getLiquid(1), false);
                            f.getOutputTanks().getTank(1).internalInsert(Steam.getGas(160), false);
                            h.extract(80, false);
                        }
                    }
                }
            });

        });
    }

    public static class HeatInfoWidget extends InfoRenderWidget.MultiRenderWidget {

        public int heat;
        protected HeatInfoWidget(GuiInstance gui, IGuiElement parent, IInfoRenderer<MultiRenderWidget> renderer) {
            super(gui, parent, renderer);
        }

        public static WidgetSupplier build() {
            return builder((a, b) -> new HeatInfoWidget(a, b, (IInfoRenderer<MultiRenderWidget>) a.handler));
        }
        @Override
        public void init() {
            super.init();
            BlockEntityMultiMachine<?> m = (BlockEntityMultiMachine) gui.handler;
            gui.syncInt(() -> m.getHeatHandlers().size() == 0 ? 0 : m.getHeatHandlers().stream().mapToInt(IHeatHandler::getTemperature).sum() / m.getHeatHandlers().size(), a -> this.heat = a, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        }
    }
}
