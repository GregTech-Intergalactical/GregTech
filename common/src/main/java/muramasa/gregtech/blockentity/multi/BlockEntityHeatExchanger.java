package muramasa.gregtech.blockentity.multi;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.IFilterableHandler;
import muramasa.antimatter.capability.fluid.FluidTank;
import muramasa.antimatter.capability.machine.DefaultHeatHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.ICanSyncData;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jeirei.renderer.IInfoRenderer;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.data.Materials;
import muramasa.gregtech.items.ItemIntCircuit;
import muramasa.gregtech.machine.caps.ParallelRecipeHandler;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.TesseractGraphWrappers;
import tesseract.api.heat.IHeatHandler;

import static muramasa.antimatter.data.AntimatterMaterials.Water;
import static muramasa.gregtech.data.Materials.*;

public class BlockEntityHeatExchanger extends BlockEntityMultiMachine<BlockEntityHeatExchanger> implements IFilterableHandler {

    int superheatedThreshold = 80000;
    int efficiency = 1000;
    int dryHeatCounter = 0;
    int dryHeatMaximum = 100;

    public BlockEntityHeatExchanger(Machine type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        heatHandler.set(() -> new DefaultHeatHandler(this, Integer.MAX_VALUE, 80, 0));
        recipeHandler.set(() -> new ParallelRecipeHandler<>(this){

            @Override
            protected int maxSimultaneousRecipes() {
                if (activeRecipe != null){
                    int base = 100;
                    long totalHu = activeRecipe.getTotalPower();
                    if (totalHu == 0) return 0;
                    double ratio = 80.0 / totalHu;
                    int totalRecipes = (int) (base * ratio);
                    return Math.min(1, totalRecipes);
                }
                return super.maxSimultaneousRecipes();
            }

            @Override
            protected boolean canRecipeContinue() {
                return super.canRecipeContinue() && heatHandler.map(h -> h.getHeat() + (activeRecipe.getTotalPower()) <= h.getHeatCap()).orElse(false);
            }

            @Override
            public boolean canOutput() {
                return !tile.fluidHandler.isPresent() || !activeRecipe.hasOutputFluids() || tile.fluidHandler.map(t -> t.getOutputTanks() != null && t.getOutputTanks().getSize() > 1 && t.getOutputTanks().getTank(0).internalInsert(Utils.ca(activeRecipe.getOutputFluids()[0].getFluidAmount() * concurrentRecipes, activeRecipe.getOutputFluids()[0]), true) == activeRecipe.getOutputFluids()[0].getFluidAmount() * concurrentRecipes).orElse(false);
            }

            @Override
            public boolean consumeResourceForRecipe(boolean simulate) {
                return heatHandler.map(h -> h.insert((int) activeRecipe.getPower() * concurrentRecipes, simulate) > 0).orElse(false);
            }

            protected void addOutputs() {
                for (int i = 0; i < concurrentRecipes; i++) {
                    if (activeRecipe.hasOutputItems()) {
                        tile.itemHandler.ifPresent(h -> {
                            //Roll the chances here. If they don't fit add flat (no chances).
                            ItemStack[] out = activeRecipe.getOutputItems(true);
                            if (h.canOutputsFit(out)) {
                                h.addOutputs(out);
                            } else {
                                h.addOutputs(activeRecipe.getFlatOutputItems());
                            }
                        });
                    }
                    if (activeRecipe.hasOutputFluids()) {
                        tile.fluidHandler.ifPresent(h -> {
                            if (h.getOutputTanks() == null) return;
                            h.getOutputTanks().getTank(0).internalInsert(activeRecipe.getOutputFluids()[0].copyHolder(), false);
                        });
                    }
                }
                if (activeRecipe.hasOutputItems()) tile.onMachineEvent(MachineEvent.ITEMS_OUTPUTTED);
                if (activeRecipe.hasOutputFluids()) tile.onMachineEvent(MachineEvent.FLUIDS_OUTPUTTED);
            }
        });

    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        if (level.getGameTime() % 20 == 0){
            fluidHandler.ifPresent(f -> {
                heatHandler.ifPresent(h -> {
                    if (h.getHeat() >= 80){
                        int heatMultiplier = h.getHeat() / 80;
                        FluidTank waterTank = f.getInputTanks().getTank(f.getInputTanks().getFirstAvailableTank(DistilledWater.getLiquid(1), true));
                        if (waterTank != null) {
                            heatMultiplier = (int) Math.min(heatMultiplier, waterTank.getTankAmount() / TesseractGraphWrappers.dropletMultiplier);
                            if (waterTank.extractFluid(DistilledWater.getLiquid(heatMultiplier), true).getFluidAmount() == heatMultiplier *  TesseractGraphWrappers.dropletMultiplier) {
                                if (f.getOutputTanks() != null && f.getOutputTanks().getSize() >= 2){
                                    Material steam = Steam;
                                    if (h.getHeat() >= superheatedThreshold){
                                        steam = SuperheatedSteam;
                                    }
                                    float tEfficiency = steam == Steam ? 1 : efficiency / 1000.0f;
                                    int waterMultiplier = steam == Steam ? 160 : 80;
                                    int steamToAdd = (int) (heatMultiplier  * waterMultiplier *  tEfficiency);
                                    long inserted = f.getOutputTanks().getTank(1).internalInsert(steam.getGas(steamToAdd), true);
                                    if (inserted >= TesseractGraphWrappers.dropletMultiplier){
                                        heatMultiplier = Math.min(heatMultiplier, (int)((inserted / TesseractGraphWrappers.dropletMultiplier) / tEfficiency));
                                        f.drainInput(DistilledWater.getLiquid(heatMultiplier), false);
                                        f.getOutputTanks().getTank(1).internalInsert(steam.getGas(steamToAdd), false);
                                        h.extract(heatMultiplier * 80, false);
                                    }
                                }
                                dryHeatCounter = 0;
                            } else {
                                dryHeatCounter++;
                            }
                        } else {
                            dryHeatCounter++;
                        }
                    }
                });

            });
            if (dryHeatCounter >= dryHeatMaximum){
                explodeMultiblock();
            }
        }

    }

    @Override
    public boolean test(SlotType<?> slotType, int slot, ItemStack stack) {
        if (slotType == SlotType.STORAGE) return stack.getItem() instanceof ItemIntCircuit;
        return true;
    }

    @Override
    public void onMachineEvent(IMachineEvent event, Object... data) {
        if (event == SlotType.STORAGE){
            ItemStack circuit = itemHandler.map(i -> i.getHandler(SlotType.STORAGE).getItem(0)).orElse(ItemStack.EMPTY);
            if (circuit.getItem() instanceof ItemIntCircuit circuit1){
                superheatedThreshold = 80000 - (circuit1.circuitId * 3000);
                efficiency = 1000 - (circuit1.circuitId * 15);
            } else {
                superheatedThreshold = 80000;
                efficiency = 1000;
            }
        }
        super.onMachineEvent(event, data);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("efficiency", efficiency);
        tag.putInt("superheatedThreshold", superheatedThreshold);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        efficiency = tag.getInt("efficiency");
        superheatedThreshold = tag.getInt("superheatedThreshold");
    }

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
