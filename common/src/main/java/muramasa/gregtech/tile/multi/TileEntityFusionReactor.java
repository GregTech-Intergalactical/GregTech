package muramasa.gregtech.tile.multi;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import static muramasa.antimatter.machine.Tier.LUV;
import static muramasa.antimatter.machine.Tier.ZPM;

public class TileEntityFusionReactor extends TileEntityMultiMachine<TileEntityFusionReactor> {

    Display display = Display.REGULAR;

    public TileEntityFusionReactor(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

//    @Override
//    public void onRecipeFound() {
//        consumeEnergy(activeRecipe.getSpecialValue());
//        System.out.println("Consumed Starting Energy");
//    }

    public Display getDisplay() {
        return display;
    }

    public Block getCoil(){
        if (tier == LUV){
            return GregTechData.COIL_SUPERCONDUCTOR;
        }
        return GregTechData.COIL_FUSION;
    }

    public Block getCasing(){
        if (tier == LUV){
            return GregTechData.CASING_LUV;
        } else if (tier == ZPM){
            return GregTechData.CASING_FUSION_1;
        }
        return GregTechData.CASING_FUSION_2;
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
