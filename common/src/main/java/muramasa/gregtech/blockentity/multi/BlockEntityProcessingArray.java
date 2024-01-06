package muramasa.gregtech.blockentity.multi;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.IFilterableHandler;
import muramasa.antimatter.capability.item.TrackedItemHandler;
import muramasa.antimatter.capability.machine.MultiMachineItemHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.ICanSyncData;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jeirei.renderer.IInfoRenderer;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.types.BasicMachine;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.map.IRecipeMap;
import muramasa.antimatter.recipe.map.RecipeMap;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.block.BlockCoil;
import muramasa.gregtech.machine.caps.ParallelRecipeHandler;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityProcessingArray extends BlockEntityMultiMachine<BlockEntityProcessingArray> implements IFilterableHandler {

    public BlockEntityProcessingArray(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.itemHandler.set(() -> new MultiMachineItemHandler<>(this){
            @Override
            protected TrackedItemHandler<BlockEntityProcessingArray> createTrackedHandler(SlotType<?> type, BlockEntityProcessingArray tile) {
                if (type == SlotType.STORAGE){
                    return new TrackedItemHandler<>(tile, type, 1, type.output, type.input, type.tester, 16);
                }
                return super.createTrackedHandler(type, tile);
            }
        });
        this.recipeHandler.set(() -> new ParallelRecipeHandler<>(this){
            IRecipeMap recipeMap = null;
            Tier tier = null;

            @Override
            protected int maxSimultaneousRecipes(){
                return itemHandler.map(i -> i.getHandler(SlotType.STORAGE).getItem(0).getCount()).orElse(0);
            }

            @Override
            public void onMachineEvent(IMachineEvent event, Object... data) {
                if (event == SlotType.STORAGE){
                    IRecipeMap oldRecipeMap = recipeMap;
                    this.tier = null;
                    ItemStack stack = itemHandler.map(i -> i.getHandler(SlotType.STORAGE).getItem(0)).orElse(ItemStack.EMPTY);
                    if (stack.getItem() instanceof BlockItem blockItem){
                        if (blockItem.getBlock() instanceof BlockMachine machine && machine.getType() instanceof BasicMachine){
                            if (machine.getType().getRecipeMap(machine.getTier()) != null){
                                this.recipeMap = machine.getType().getRecipeMap(machine.getTier());
                                this.tier = machine.getTier();
                            }
                        }
                    }
                    if (oldRecipeMap != recipeMap){
                        checkRecipe();
                    }
                }
                super.onMachineEvent(event, data);
            }

            @Override
            public void init() {
                ItemStack stack = itemHandler.map(i -> i.getHandler(SlotType.STORAGE).getItem(0)).orElse(ItemStack.EMPTY);
                if (stack.getItem() instanceof BlockItem blockItem){
                    if (blockItem.getBlock() instanceof BlockMachine machine && machine.getType() instanceof BasicMachine){
                        if (machine.getType().getRecipeMap(machine.getTier()) != null){
                            this.recipeMap = machine.getType().getRecipeMap(machine.getTier());
                            this.tier = machine.getTier();
                        }
                    }
                }
                super.init();
            }

            @Override
            protected IRecipe cachedRecipe() {
                if (recipeMap == null) return null;
                return super.cachedRecipe();
            }

            @Override
            public IRecipe findRecipe() {
                if (lastRecipe != null) {
                    activeRecipe = lastRecipe;
                    if (canRecipeContinue()) {
                        activeRecipe = null;
                        return lastRecipe;
                    }
                    activeRecipe = null;
                }
                return recipeMap != null ? recipeMap.find(tile.itemHandler, tile.fluidHandler, tile.getMachineTier(), this::validateRecipe) : null;
            }

            @Override
            public long getPower() {
                if (activeRecipe == null) return 0;
                return super.getPower() * (concurrentRecipes == 0 ? 1 : concurrentRecipes);
            }

            @Override
            public int getOverclock() {
                if (activeRecipe == null || tier == null) return 0;
                int oc = 0;
                if (activeRecipe.getPower() > 0 && tier.getVoltage() > activeRecipe.getPower()) {
                    long voltage = this.activeRecipe.getPower();
                    int tier = Utils.getVoltageTier(voltage);
            /*//Dont use utils, because we allow overclocking from ulv. (If we don't just change this).
            for (int i = 0; i < Ref.V.length; i++) {
                if (voltage <= Ref.V[i]) {
                    tier = i;
                    break;
                }
            }*/
                    long tempoverclock = (this.tile.getPowerLevel().getVoltage() / Ref.V[tier]);
                    while (tempoverclock > 1) {
                        tempoverclock >>= 2;
                        oc++;
                    }
                }
                return oc;
            }

            @Override
            public CompoundTag serialize() {
                CompoundTag nbt = super.serialize();
                nbt.putString("recipeMap", recipeMap.getId());
                return nbt;
            }

            @Override
            public void deserialize(CompoundTag nbt) {
                super.deserialize(nbt);
                this.recipeMap = AntimatterAPI.get(RecipeMap.class, nbt.getString("recipeMap"));
            }
        });
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

    @Override
    public boolean test(SlotType<?> slotType, int slot, ItemStack stack) {
        if (slotType == SlotType.STORAGE){
            if (stack.getItem() instanceof BlockItem blockItem){
                if (blockItem.getBlock() instanceof BlockMachine machine && machine.getType() instanceof BasicMachine){
                    if (machine.getType().getRecipeMap(machine.getTier()) != null){
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public static class MultiSmelterInfoWidget extends InfoRenderWidget.MultiRenderWidget{
        int concurrentRecipes;
        protected MultiSmelterInfoWidget(GuiInstance gui, IGuiElement parent, IInfoRenderer<MultiRenderWidget> renderer) {
            super(gui, parent, renderer);
        }

        @Override
        public void init() {
            super.init();
            BlockEntityProcessingArray m = (BlockEntityProcessingArray) gui.handler;
            gui.syncInt(() -> m.recipeHandler.map(r -> ((ParallelRecipeHandler<?>)r).concurrentRecipes).orElse(0), i -> concurrentRecipes = i, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        }

        public static WidgetSupplier build() {
            return builder((a, b) -> new MultiSmelterInfoWidget(a, b, (IInfoRenderer) a.handler));
        }
    }
}
