package muramasa.gregtech.blockentity.multi;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.ICanSyncData;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jeirei.renderer.IInfoRenderer;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.block.BlockCoil;
import muramasa.gregtech.machine.caps.ParallelRecipeHandler;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.machine.Tier.*;

public class BlockEntityMultiSmelter extends BlockEntityMultiMachine<BlockEntityMultiSmelter> {
    private BlockCoil.CoilData coilData;

    public BlockEntityMultiSmelter(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new ParallelRecipeHandler<>(this){
            @Override
            protected int maxSimultaneousRecipes(){
                return coilData.maxSimultaneousRecipes();
            }
        });
    }

//    @Override
//    public void onRecipeFound() {
////        this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
////        this.mEfficiencyIncrease = 10000;
//
//        int tier = Utils.getVoltageTier(getMaxInputVoltage());
//        EUt = (-4 * (1 << tier - 1) * (1 << tier - 1) * level / discount);
//        maxProgress = Math.max(1, 512 / (1 << tier - 1));
//    }

    public void setCoilData(BlockCoil.CoilData coilData) {
        this.coilData = coilData;
    }

    public BlockCoil.CoilData getCoilData() {
        return coilData;
    }

    @Override
    public WidgetSupplier getInfoWidget() {
        return MultiSmelterInfoWidget.build().setPos(10, 10);
    }

    @Override
    public int drawInfo(InfoRenderWidget.MultiRenderWidget instance, PoseStack stack, Font renderer, int left, int top) {
        int superDraw = super.drawInfo(instance, stack, renderer, left, top);
        if (getMachineState() == MachineState.ACTIVE && instance.drawActiveInfo()){
            renderer.draw(stack, "Concurrent Recipes: " + ((MultiSmelterInfoWidget)instance).concurrentRecipes, left, top + 32, 16448255);
            return superDraw + 8;
        }
        return superDraw;
    }

    public static class MultiSmelterInfoWidget extends InfoRenderWidget.MultiRenderWidget{
        int concurrentRecipes;
        protected MultiSmelterInfoWidget(GuiInstance gui, IGuiElement parent, IInfoRenderer<MultiRenderWidget> renderer) {
            super(gui, parent, renderer);
        }

        @Override
        public void init() {
            super.init();
            BlockEntityMultiSmelter m = (BlockEntityMultiSmelter) gui.handler;
            gui.syncInt(() -> m.recipeHandler.map(r -> ((ParallelRecipeHandler<?>)r).concurrentRecipes).orElse(0), i -> concurrentRecipes = i, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        }

        public static WidgetSupplier build() {
            return builder((a, b) -> new MultiSmelterInfoWidget(a, b, (IInfoRenderer) a.handler));
        }
    }
}
