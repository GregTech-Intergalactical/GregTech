package muramasa.gregtech.tile.multi;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.gregtech.nuclear.TileEntityNuclearReactor;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import tesseract.api.heat.IHeatHandler;

import java.util.Collections;
import java.util.List;

public class TileEntityHeatExchanger extends TileEntityMultiMachine<TileEntityHeatExchanger> {

    protected List<IHeatHandler> HEAT_HANDLERS;

    @Override
    public int drawInfo(InfoRenderWidget.MultiRenderWidget instance, PoseStack stack, Font renderer, int left, int top) {
        int size = super.drawInfo(instance, stack, renderer, left, top);
        renderer.draw(stack, "Heat: " + ((TileEntityNuclearReactor.HeatInfoWidget)instance).heat, left, top + size, 16448255);
        return size + 8;
    }

    @Override
    public WidgetSupplier getInfoWidget() {
        return TileEntityNuclearReactor.HeatInfoWidget.build().setPos(10, 10);
    }

    public TileEntityHeatExchanger(Machine type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new MachineRecipeHandler<>(this) {
            @Override
            public boolean consumeResourceForRecipe(boolean simulate) {
                Recipe r = activeRecipe;
                if (simulate) return TileEntityHeatExchanger.this.HEAT_HANDLERS.stream().mapToInt(IHeatHandler::getHeat).sum() >= r.getPower();
                int[] count = new int[1];
                count[0] = (int) r.getPower();
                for (IHeatHandler heat_handler : TileEntityHeatExchanger.this.HEAT_HANDLERS) {
                    var txn = heat_handler.extract();
                    txn.addData(count[0], -1, a -> count[0] -= a);
                    txn.commit();
                    if (count[0] == 0) break;
                }
                return true;
            }
        });
    }
}
