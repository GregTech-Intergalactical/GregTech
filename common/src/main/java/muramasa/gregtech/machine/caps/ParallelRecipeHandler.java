package muramasa.gregtech.machine.caps;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.util.Utils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ParallelRecipeHandler<T extends BlockEntityMachine<T>> extends MachineRecipeHandler<T> {
    public int concurrentRecipes = 0;
    public ParallelRecipeHandler(T tile) {
        super(tile);
    }

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
