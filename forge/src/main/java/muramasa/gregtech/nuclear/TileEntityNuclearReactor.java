package muramasa.gregtech.nuclear;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.capability.Holder;
import muramasa.antimatter.capability.IComponentHandler;
import muramasa.antimatter.capability.IHeatHandler;
import muramasa.antimatter.capability.machine.DefaultHeatHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.ICanSyncData;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jei.renderer.IInfoRenderer;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.structure.StructureHandle;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.antimatter.util.Utils;
import muramasa.antimatter.util.int3;
import muramasa.gregtech.GregTech;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class TileEntityNuclearReactor extends TileEntityMultiMachine<TileEntityNuclearReactor> {

    protected float efficiencyBonus;


    protected final StructureHandle<TileEntityNuclearReactor> LEFT = new StructureHandle<>(TileEntityNuclearReactor.class, this, new int3(3, 0, 0), this::onRemoveReactor, this::onAddReactor);
    protected final StructureHandle<TileEntityNuclearReactor> RIGHT = new StructureHandle<>(TileEntityNuclearReactor.class, this, new int3(-3, 0, 0), this::onRemoveReactor, this::onAddReactor);
    protected final StructureHandle<TileEntityNuclearReactor> FORWARD = new StructureHandle<>(TileEntityNuclearReactor.class, this, new int3(0, -3,0), this::onRemoveReactor, this::onAddReactor);
    protected final StructureHandle<TileEntityNuclearReactor> BACKWARD = new StructureHandle<>(TileEntityNuclearReactor.class, this, new int3(0, 3,0 ), this::onRemoveReactor, this::onAddReactor);

    public TileEntityNuclearReactor(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        efficiencyBonus = 1;
        recipeHandler.set(() -> new MachineRecipeHandler<>(this) {
            @Override
            public boolean consumeResourceForRecipe(boolean simulate) {
                return true;
            }

            @Override
            protected boolean validateRecipe(Recipe r) {
                return r.getPower() > 0 && r.getDuration() > 0;
            }
        });
    }

    private void onAddReactor(TileEntityNuclearReactor reactor) {
        GregTech.LOGGER.info("Added reactor at " + reactor.getBlockPos());
        efficiencyBonus += 0.3;
    }



    @Override
    public void onRecipePostTick() {
        super.onRecipePostTick();
        //Insert heat
        Recipe r = this.recipeHandler.map(MachineRecipeHandler::getActiveRecipe).orElse(null);
        long power = r.getPower();
        int in = (int)((float)power*efficiencyBonus);
        IHeatHandler.HeatTransaction tx = new IHeatHandler.HeatTransaction(in, 0, Utils.sink()).ignoreTemperature();
        for (IHeatHandler handler : HEAT_HANDLERS) {
            handler.insert(tx);
        }
        tx.commit();
    }

    private void onRemoveReactor(TileEntityNuclearReactor reactor) {
        GregTech.LOGGER.info("Removed reactor at " + reactor.getBlockPos());
        efficiencyBonus -= 0.3;
    }
    @Override
    public int drawInfo(InfoRenderWidget.MultiRenderWidget instance, PoseStack stack, Font renderer, int left, int top) {
        int size = super.drawInfo(instance, stack, renderer, left, top);
        renderer.draw(stack, "Heat: " + ((NuclearInfoWidget)instance).heat, left, size, 16448255);
        return size + 8;
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        for (IHeatHandler handler : HEAT_HANDLERS) {
            handler.update(getMachineState() == MachineState.ACTIVE);
        }
    }

    @Override
    public int maxShares() {
        return 0;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {

        return super.getCapability(cap, side);
    }

    @Override
    public WidgetSupplier getInfoWidget() {
        return NuclearInfoWidget.build();
    }

    public static class NuclearInfoWidget extends InfoRenderWidget.MultiRenderWidget {

        public int heat;
        public int neighbourBonus;
        protected NuclearInfoWidget(GuiInstance gui, IGuiElement parent, IInfoRenderer<MultiRenderWidget> renderer) {
            super(gui, parent, renderer);
        }

        public static WidgetSupplier build() {
            return builder((a, b) -> new NuclearInfoWidget(a, b, (IInfoRenderer<MultiRenderWidget>) a.handler));
        }
        @Override
        public void init() {
            super.init();
            TileEntityNuclearReactor m = (TileEntityNuclearReactor) gui.handler;
            gui.syncInt(() -> m.HEAT_HANDLERS.stream().mapToInt(IHeatHandler::getTemperature).sum() / m.HEAT_HANDLERS.size(), a -> this.heat = a, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
            gui.syncInt(() -> (int) (m.efficiencyBonus-1)*100, a -> this.neighbourBonus = a, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        }
    }
}
