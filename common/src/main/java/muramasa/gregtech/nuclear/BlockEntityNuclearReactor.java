package muramasa.gregtech.nuclear;

import com.mojang.blaze3d.vertex.PoseStack;
import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.ICanSyncData;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jeirei.renderer.IInfoRenderer;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.structure.StructureHandle;
import muramasa.antimatter.util.Utils;
import muramasa.antimatter.util.int3;
import muramasa.gregtech.GregTech;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.TesseractGraphWrappers;
import tesseract.api.heat.HeatTransaction;
import tesseract.api.heat.IHeatHandler;

import static muramasa.gregtech.data.Materials.Coolant;
import static muramasa.gregtech.data.Materials.HotCoolant;

public class BlockEntityNuclearReactor extends BlockEntityMultiMachine<BlockEntityNuclearReactor> {

    protected float efficiencyBonus;
    int count = 0;


    protected final StructureHandle<BlockEntityNuclearReactor> LEFT = new StructureHandle<>(BlockEntityNuclearReactor.class, this, new int3(3, 0, 0), this::onRemoveReactor, this::onAddReactor);
    protected final StructureHandle<BlockEntityNuclearReactor> RIGHT = new StructureHandle<>(BlockEntityNuclearReactor.class, this, new int3(-3, 0, 0), this::onRemoveReactor, this::onAddReactor);
    protected final StructureHandle<BlockEntityNuclearReactor> FORWARD = new StructureHandle<>(BlockEntityNuclearReactor.class, this, new int3(0, -3,0), this::onRemoveReactor, this::onAddReactor);
    protected final StructureHandle<BlockEntityNuclearReactor> BACKWARD = new StructureHandle<>(BlockEntityNuclearReactor.class, this, new int3(0, 3,0 ), this::onRemoveReactor, this::onAddReactor);

    public BlockEntityNuclearReactor(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        efficiencyBonus = 1;
        recipeHandler.set(() -> new MachineRecipeHandler<>(this) {

            @Override
            protected boolean validateRecipe(IRecipe r) {
                return r.getPower() > 0 && r.getDuration() > 0;
            }

            @Override
            protected MachineState tickRecipe(){
                MachineState state = super.tickRecipe();
                if (state == MachineState.ACTIVE){
                    long conversionAmount = activeRecipe.getPower() * TesseractGraphWrappers.dropletMultiplier;
                    fluidHandler.ifPresent(handler -> {
                        FluidHolder coolant = Coolant.getLiquid(conversionAmount);
                        FluidHolder drained = handler.drainInput(coolant, true);
                        if (drained.getFluidAmount() == coolant.getFluidAmount()){
                            if (handler.canOutputsFit(new FluidHolder[]{HotCoolant.getLiquid(conversionAmount)})){
                                handler.drainInput(coolant, false);
                                handler.fillOutput(HotCoolant.getLiquid(conversionAmount), false);
                                onMachineEvent(MachineEvent.FLUIDS_OUTPUTTED);
                            }
                        }
                    });
                }
                return state;
            }

            @Override
            public boolean consumeResourceForRecipe(boolean simulate) {
                return true;
            }
        });
    }

    private void onAddReactor(BlockEntityNuclearReactor reactor) {
        GregTech.LOGGER.info("Added reactor at " + reactor.getBlockPos());
        efficiencyBonus += 0.3;
    }


    @Override
    public void onRecipePostTick() {
        super.onRecipePostTick();
        /*//Insert heat
        IRecipe r = this.recipeHandler.map(MachineRecipeHandler::getActiveRecipe).orElse(null);
        long power = r.getPower();
        int in = (int)((float)power*efficiencyBonus);
        HeatTransaction tx = new HeatTransaction(in, 0, Utils.sink()).ignoreTemperature();
        for (IHeatHandler handler : heatHandlers) {
            handler.insert(tx);
        }
        tx.commit();*/
    }

    private void onRemoveReactor(BlockEntityNuclearReactor reactor) {
        GregTech.LOGGER.info("Removed reactor at " + reactor.getBlockPos());
        efficiencyBonus -= 0.3;
    }
    @Override
    public int drawInfo(InfoRenderWidget.MultiRenderWidget instance, PoseStack stack, Font renderer, int left, int top) {
        int size = super.drawInfo(instance, stack, renderer, left, top);
        renderer.draw(stack, "Heat: " + ((HeatInfoWidget)instance).heat, left, top + size, 16448255);
        //renderer.draw(stack, "So oft gecalled: " + count, left, top + 4*size, 16448255);
        return size + 8;
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        /*for (IHeatHandler handler : heatHandlers) {
            handler.update(getMachineState() == MachineState.ACTIVE);
        }*/
    }

    @Override
    public int maxShares() {
        return 0;
    }

    @Override
    public WidgetSupplier getInfoWidget() {
        return HeatInfoWidget.build().setPos(10, 10);
    }

    public static class HeatInfoWidget extends InfoRenderWidget.MultiRenderWidget {

        public int heat;
        public int neighbourBonus;
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
            gui.syncInt(() -> (m.getHeatHandlers().size()-1) *100, a -> this.neighbourBonus = a, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        }
    }
}
