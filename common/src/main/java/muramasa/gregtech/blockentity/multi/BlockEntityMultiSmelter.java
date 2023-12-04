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
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.machine.Tier.*;

public class BlockEntityMultiSmelter extends BlockEntityMultiMachine<BlockEntityMultiSmelter> {

    private int level = 1, discount = 1;
    private BlockCoil.CoilData coilData;
    int concurrentRecipes = 0;

    public BlockEntityMultiSmelter(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new MachineRecipeHandler<>(this){


            @Override
            public boolean consumeInputs() {
                concurrentRecipes = 0;
                for (int i = 0; i < maxSimultaneousRecipes(); i++) {
                    boolean consumeInput = super.consumeInputs();
                    if (!consumeInput) break;
                    concurrentRecipes++;
                }
                return concurrentRecipes > 0;
            }

            @Override
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
                }
                tile.onMachineEvent(MachineEvent.ITEMS_OUTPUTTED);
            }


            /*@Override
            protected MachineState tickRecipe() {
                if (this.currentProgress >= this.maxProgress) {
                    if (!canOutputParallel()) {
                        tickTimer += WAIT_TIME_OUTPUT_FULL;
                        return OUTPUT_FULL;
                    }
                }
                return super.tickRecipe();
            }*/

            public boolean canOutput() {
                if (concurrentRecipes <= 1) return super.canOutput();
                //ignore chance for canOutput.
                if (!tile.itemHandler.isPresent() || !activeRecipe.hasOutputItems()) return true;
                List<ItemStack> outputs = new ArrayList<>();
                for (int i = 0; i < concurrentRecipes; i++) {
                    for (ItemStack item : activeRecipe.getFlatOutputItems()) {
                        outputs.add(item.copy());
                    }
                }
                List<ItemStack> merged = Utils.mergeItems(new ArrayList<>(), outputs);
                return tile.itemHandler.map(t -> t.canOutputsFit(merged.toArray(ItemStack[]::new))).orElse(false);
            }

            private int maxSimultaneousRecipes(){
                Tier powerLevel = tile.getPowerLevel();
                if (powerLevel == LV){
                    return 6;
                }
                if (powerLevel == MV){
                    return 8;
                }
                if (powerLevel == HV){
                    return 10;
                }
                if (powerLevel == EV){
                    return 12;
                }
                if (powerLevel == IV){
                    return 14;
                }
                if (powerLevel == LUV){
                    return 16;
                }
                if (powerLevel == ZPM){
                    return 18;
                }
                return 1;
            }

            @Override
            public CompoundTag serialize() {
                CompoundTag nbt = super.serialize();
                nbt.putInt("concurrentRecipes", concurrentRecipes);
                return nbt;
            }

            @Override
            public void deserialize(CompoundTag nbt) {
                super.deserialize(nbt);
                concurrentRecipes = nbt.getInt("concurrentRecipes");
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

    /*@Override
    public boolean onStructureFormed() {
        super.onStructureFormed();
        List<BlockState> coils = getStates("coil");
        BlockCoil firstType = ((BlockCoil) coils.get(0).getBlock());
        if (coils.stream().allMatch(s -> s.getBlock() == firstType)) {
            setCoilValues(firstType);
            return true;
        } else {
            this.result.withError("all coils do not match");
            return false;
        }
    }*/

    public void setCoilValues(BlockCoil coil) {
        switch (coil.getId()) {
            case "kanthal":
                level = 2;
                break;
            case "nichrome":
                level = 4;
                break;
            case "tungstensteel":
                level = 8;
                break;
            case "hssg":
                level = 16;
                discount = 2;
                break;
            case "naquadah":
                level = 16;
                discount = 4;
                break;
            case "naquadah_alloy":
                level = 16;
                discount = 8;
                break;
        }
    }

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
            gui.syncInt(() -> m.concurrentRecipes, i -> concurrentRecipes = i, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        }

        public static WidgetSupplier build() {
            return builder((a, b) -> new MultiSmelterInfoWidget(a, b, (IInfoRenderer) a.handler));
        }
    }
}
