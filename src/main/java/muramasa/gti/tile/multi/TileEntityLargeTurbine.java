package muramasa.gti.tile.multi;

import com.mojang.blaze3d.matrix.MatrixStack;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jei.renderer.IInfoRenderer;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import static muramasa.antimatter.gui.ICanSyncData.SyncDirection.SERVER_TO_CLIENT;
import static muramasa.gti.data.Materials.DistilledWater;

public class TileEntityLargeTurbine extends TileEntityMultiMachine<TileEntityLargeTurbine> {

    /**
     * SYNC-DATA
     */
    protected int lastConsume = 0;
    protected int recipeConsumption = 0;
    protected long lastEU = 0;

    public TileEntityLargeTurbine(Machine type) {
        super(type);
        recipeHandler.set(() ->
                new MachineRecipeHandler<TileEntityLargeTurbine>(this) {

                    private Recipe sourceRecipe;
                    private double efficiency;

                    @Override
                    protected boolean validateRecipe(Recipe r) {
                        return true;
                    }

                    @Override
                    public Recipe findRecipe() {
                        Recipe r = super.findRecipe();
                        if (r == null) return null;
                        sourceRecipe = r;
                        //Source recipe contains fluid amounts, map to turbine
                        FluidStack[] stacks = r.getInputFluids();
                        if (stacks == null || stacks.length == 0) return null;
                        //ItemStack t = tile.itemHandler.map(tt -> tt.getSpecial()).orElse(ItemStack.EMPTY);
                        //if (!(t.getItem() instanceof ItemTurbine)) return null;
                        // ItemTurbine turbine = (ItemTurbine) t.getItem();
                        int flow = 120;//turbine.optimalEUT;
                        efficiency = 1.15;//turbine.efficency;
                        long toConsume = calculateGeneratorConsumption(flow, sourceRecipe);
                        TileEntityLargeTurbine.this.recipeConsumption = (int) toConsume;
                        return Utils.getFluidPoweredRecipe(new FluidStack[]{new FluidStack(stacks[0].getFluid(),(int) toConsume)},
                                new FluidStack[]{new FluidStack(DistilledWater.getLiquid(), stacks[0].getAmount())},// Arrays.stream(sourceRecipe.getOutputFluids()).map(tt -> new FluidStack(tt.getFluid(), (int) (tt.getAmount()*toConsume))).toArray(FluidStack[]::new),
                                1, flow,1);
                    }

                    @Override
                    protected boolean consumeGeneratorResources(boolean simulate) {
                        if (!activeRecipe.hasInputFluids()) {
                            throw new RuntimeException("Missing fuel in active generator recipe!");
                        }
                        //boolean shouldRun = tile.energyHandler.map(h -> h.insert((long)(tile.getMachineType().getMachineEfficiency()*(double)tile.getMachineTier().getVoltage()),true) > 0).orElse(false);
                        ///if (!shouldRun) return false;
                        int recipeAmount = activeRecipe.getInputFluids()[0].getAmount();
                        long toConsume = recipeAmount; // calculateGeneratorConsumption(tile.getMachineTier().getVoltage(), activeRecipe);// (long) ((double)tile.getMachineTier().getVoltage() / (activeRecipe.getPower() /(double) Objects.requireNonNull(activeRecipe.getInputFluids())[0].getAmount()));
                        int consumed = tile.fluidHandler.map(h -> {
                        /*
                            How much wiggle room? So, at optimal flow : generate regular. Otherwise, dampen by a factor of 1/(optimal-current) or 1/(current-optimal). Allow
                            consuming up to 1.5x optimal
                         */
                            int amount = h.getInputTanks().drain(new FluidStack(activeRecipe.getInputFluids()[0],(int)(toConsume*1.5)), IFluidHandler.FluidAction.SIMULATE).getAmount();

                            if (amount > 0) {
                                if (!simulate) {
                                    h.getInputTanks().drain(new FluidStack(activeRecipe.getInputFluids()[0], amount), IFluidHandler.FluidAction.EXECUTE);
                                    TileEntityLargeTurbine.this.lastConsume = amount;
                                }
                                return amount;
                            }
                            return 0;
                        }).orElse(0);
                        if (consumed > 0){
                            if (consumed < recipeAmount) consumed *= Math.pow(1d/(recipeAmount-consumed),0.04);
                            if (consumed > recipeAmount) consumed *= Math.pow(1d/(consumed-recipeAmount),0.04);
                            //Input energy
                            int finalConsumed = consumed;
                            //Ignore the actual inserted amount a la multiblock.
                            if (!simulate) tile.energyHandler.ifPresent(handler -> {
                                long eu = (long) (efficiency*activeRecipe.getPower()*finalConsumed/ recipeAmount);
                                Utils.addEnergy(handler, eu);
                                TileEntityLargeTurbine.this.lastEU = eu;
                            });
                            return true;
                        }
                        return false;
                    }
            });
    }

    @Override
    public int drawInfo(InfoRenderWidget.MultiRenderWidget instance, MatrixStack stack, FontRenderer renderer, int left, int top) {
        int size = super.drawInfo(instance, stack, renderer, left, top);
        if (this.getMachineState() == MachineState.ACTIVE) {
            LargeTurbineWidget wid = (LargeTurbineWidget) instance;
            renderer.draw(stack, "Current: " + wid.currentConsumption + " mb/t", left, top + size, 16448255);
            renderer.draw(stack, "Optimal: " + wid.recommendedConsumption + " mb/t", left, top + size + 8, 16448255);
            renderer.draw(stack, "EU generation: " + wid.lastEU, left, top + size + 16, 16448255);
            return size + 24;
        }
        return size;
    }


    public static class LargeTurbineWidget extends InfoRenderWidget.MultiRenderWidget {

        public int currentConsumption = 0;
        public long lastEU = 0;
        public int recommendedConsumption = 0;

        protected LargeTurbineWidget(GuiInstance gui, IGuiElement parent, IInfoRenderer<MultiRenderWidget> renderer) {
            super(gui, parent, renderer);
        }

        @Override
        public void init() {
            super.init();
            TileEntityLargeTurbine turbine = (TileEntityLargeTurbine) gui.handler;
            gui.syncInt(() -> turbine.lastConsume, i -> this.currentConsumption = i, SERVER_TO_CLIENT);
            gui.syncInt(() -> turbine.recipeConsumption, i -> this.recommendedConsumption = i, SERVER_TO_CLIENT);
            gui.syncLong(() -> turbine.lastEU, i -> this.lastEU = i, SERVER_TO_CLIENT);
        }

        public static WidgetSupplier build() {
            return builder((a, b) -> new LargeTurbineWidget(a, b, (IInfoRenderer<MultiRenderWidget>) a.handler));
        }

        @Override
        public boolean drawActiveInfo() {
            return false;
        }
    }

    @Override
    public WidgetSupplier getInfoWidget() {
        return LargeTurbineWidget.build().setPos(10, 10);
    }
}
