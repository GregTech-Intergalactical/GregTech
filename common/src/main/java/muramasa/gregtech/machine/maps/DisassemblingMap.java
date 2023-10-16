package muramasa.gregtech.machine.maps;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.IRecipeMap;
import muramasa.antimatter.recipe.map.RecipeMap;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;

public class DisassemblingMap implements IRecipeMap {

    private RecipeManager manager;

    private final Map<Machine<?>, ShapedRecipe> LOOKUP = new Object2ObjectOpenHashMap<>();

    //private Recipe last;

    public DisassemblingMap() {
        AntimatterAPI.register(IRecipeMap.class, this);
    }

    @Override
    public String getId() {
        return "gt_disassembling";
    }

    @Override
    public Recipe find(@NotNull ItemStack[] items, @NotNull FluidHolder[] fluids, Tier tier, @NotNull Predicate<IRecipe> canHandle) {
        if (items.length == 0) return null;

        Machine<?> machine = ((BlockMachine)((BlockItem)items[0].getItem()).getBlock()).getType();
        ShapedRecipe recipe = LOOKUP.get(machine);
        if (recipe == null) {
            Optional<CraftingRecipe> rec = manager.getAllRecipesFor(RecipeType.CRAFTING).stream().filter(t -> {
                if (t instanceof ShapedRecipe r) {
                    if (r.getResultItem().getItem() instanceof BlockItem it) {
                        if (it.getBlock() instanceof BlockMachine m) {
                            return m.getType() == machine;
                        }
                    }
                }
                return false;
            }).findFirst();
            if (rec.isEmpty()) return null;
            LOOKUP.put(machine, (ShapedRecipe) rec.get());
            recipe = (ShapedRecipe) rec.get();
        }
        Deque<Ingredient> inputs = getInputs(recipe);
        float size;
        if (tier == Tier.ULV) {
            size = 0.2f;
        } else {
            size = 0.2f + (float) (tier.getIntegerId()-1)/10;
        }
        Random rand = new Random((long) machine.getLoc().hashCode() | (long) tier.getId().hashCode() << 32);
        for (int i = 0; i < inputs.size(); i++) {
            if (rand.nextFloat() >= size) {
                inputs.removeLast();
            }
        }
        ItemStack[] stacks = inputs.stream().filter(t -> t.getItems().length > 0).map(t -> t.getItems()[0]).toArray(ItemStack[]::new);
        ItemStack out = items[0].copy();
        out.setCount(1);
        return new Recipe(Collections.singletonList(RecipeIngredient.of(out)), RecipeMap.uniqueItems(stacks), Collections.emptyList(), EMPTY_FLUID, 200, tier.getVoltage()-(2L << tier.getIntegerId()), 0, 1);
    }

    private Deque<Ingredient> getInputs(ShapedRecipe recipe) {
        return new ArrayDeque<>(recipe.getIngredients());
    }

    @Override
    public void add(IRecipe recipe) {

    }

    @Override
    public void compileRecipe(IRecipe recipe) {

    }

    @Override
    public void compile(RecipeManager manager) {
        LOOKUP.clear();
        this.manager = manager;
    }

    @Override
    public void resetCompiled() {

    }

    @Override
    public Collection<IRecipe> getRecipes(boolean filterHidden) {
        return Collections.emptyList();
    }

    @Override
    public boolean acceptsItem(ItemStack item) {
        if (item.getItem() instanceof BlockItem it) {
            return it.getBlock() instanceof BlockMachine;
        }
        return false;
    }

    @Override
    public boolean acceptsFluid(FluidHolder fluid) {
        return false;
    }
}
