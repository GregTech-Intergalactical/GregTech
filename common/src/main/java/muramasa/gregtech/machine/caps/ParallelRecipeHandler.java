package muramasa.gregtech.machine.caps;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.util.Utils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static muramasa.antimatter.machine.MachineState.OUTPUT_FULL;

public class ParallelRecipeHandler<T extends BlockEntityMachine<T>> extends MachineRecipeHandler<T> {
    public int concurrentRecipes = 0;
    public ParallelRecipeHandler(T tile) {
        super(tile);
    }

    @Override
    public boolean consumeInputs() {
        concurrentRecipes = 0;
        for (int i = 0; i < maxSimultaneousRecipes(); i++) {
            if (!canOutput()) break;
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
            if (activeRecipe.hasOutputFluids()) {
                tile.fluidHandler.ifPresent(h -> {
                    h.addOutputs(activeRecipe.getOutputFluids());
                });
            }
        }
        if (activeRecipe.hasOutputItems()) tile.onMachineEvent(MachineEvent.ITEMS_OUTPUTTED);
        if (activeRecipe.hasOutputFluids()) tile.onMachineEvent(MachineEvent.FLUIDS_OUTPUTTED);
        concurrentRecipes = 0;
    }

    private void addPartialOutputs(){
        int successfulRecipes = 0;
        for (int i = 0; i < concurrentRecipes; i++) {
            AtomicBoolean successful = new AtomicBoolean(true);
            if (activeRecipe.hasOutputItems()) {
                tile.itemHandler.ifPresent(h -> {
                    //Roll the chances here. If they don't fit add flat (no chances).
                    ItemStack[] out = activeRecipe.getOutputItems(true);
                    if (h.canOutputsFit(out)) {
                        h.addOutputs(out);
                        successful.set(true);
                    } else if (h.canOutputsFit(activeRecipe.getFlatOutputItems())){
                        successful.set(true);
                        h.addOutputs(activeRecipe.getFlatOutputItems());
                    }
                });
            }
            if (activeRecipe.hasOutputFluids()) {
                tile.fluidHandler.ifPresent(h -> {
                    if (h.canOutputsFit(activeRecipe.getOutputFluids())) {
                        h.addOutputs(activeRecipe.getOutputFluids());
                        successful.set(true);
                    }
                });
            }
            if (successful.get()){
                successfulRecipes++;
            }
        }
        concurrentRecipes-= successfulRecipes;
        if (activeRecipe.hasOutputItems()) tile.onMachineEvent(MachineEvent.ITEMS_OUTPUTTED);
        if (activeRecipe.hasOutputFluids()) tile.onMachineEvent(MachineEvent.FLUIDS_OUTPUTTED);
    }

    @Override
    public void onServerUpdate() {
        if (tile.getMachineState() == OUTPUT_FULL) {
            if (!canOutput() && super.canOutput()) {
                addPartialOutputs();
                return;
            }
        }
        if (activeRecipe == null && concurrentRecipes > 0) concurrentRecipes = 0;
        super.onServerUpdate();
    }

    public boolean canOutput() {
        if (concurrentRecipes <= 1) return super.canOutput();
        if (tile.itemHandler.isPresent() && activeRecipe.hasOutputItems() && !tile.itemHandler.map(t -> {
            List<ItemStack> outputs = new ArrayList<>();
            for (int i = 0; i < concurrentRecipes; i++) {
                for (ItemStack item : activeRecipe.getFlatOutputItems()) {
                    outputs.add(item.copy());
                }
            }
            List<ItemStack> merged = Utils.mergeItems(new ArrayList<>(), outputs);
            return t.canOutputsFit(merged.toArray(ItemStack[]::new));
        }).orElse(false))
            return false;
        if (!tile.fluidHandler.isPresent() || !activeRecipe.hasOutputFluids()) return true;
        List<FluidHolder> outputs = new ArrayList<>();
        for (int i = 0; i < concurrentRecipes; i++) {
            for (FluidHolder fluidHolder : activeRecipe.getOutputFluids()) {
                outputs.add(fluidHolder.copyHolder());
            }
        }
        List<FluidHolder> merged = Utils.mergeFluids(new ArrayList<>(), outputs);
        return tile.fluidHandler.map(t -> t.canOutputsFit(merged.toArray(FluidHolder[]::new))).orElse(false);
    }

    protected int maxSimultaneousRecipes(){
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
}
