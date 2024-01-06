package muramasa.gregtech.blockentity.single;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechItems;
import muramasa.gregtech.data.RecipeMaps;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.TesseractGraphWrappers;
import tesseract.api.item.ExtendedItemContainer;

import static muramasa.gregtech.data.Materials.Glue;

public class BlockEntityAssembler extends BlockEntityMachine<BlockEntityAssembler> {
    public BlockEntityAssembler(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            @Override
            public IRecipe findRecipe() {
                IRecipe recipe = super.findRecipe();
                if (recipe == null){
                    ExtendedItemContainer container = itemHandler.get().getInputHandler();
                    ItemStack printedPages = ItemStack.EMPTY;
                    boolean leather = false;
                    for (int i = 0; i < container.getContainerSize(); i++) {
                        ItemStack stack = container.getItem(i);
                        if (stack.getItem() == GregTechItems.PrintedPages && printedPages.isEmpty()){
                            printedPages = stack;
                        } else if (stack.getItem() == Items.LEATHER){
                            leather = true;
                        }
                    }
                    if (!printedPages.isEmpty() && leather){
                        FluidHolder glue = fluidHandler.map(f -> f.getFluidInTank(0)).orElse(FluidHooks.emptyFluid());
                        if (!glue.isEmpty() && glue.matches(Glue.getLiquid(20)) && glue.getFluidAmount() >= 20 * TesseractGraphWrappers.dropletMultiplier){
                            ItemStack output = new ItemStack(Items.WRITTEN_BOOK);
                            output.setTag(printedPages.copy().getTag());
                            return RecipeMaps.ASSEMBLER.RB().recipeMapOnly().ii(RecipeIngredient.of(printedPages.copy()), RecipeIngredient.of(Items.LEATHER)).fi(Glue.getLiquid(20)).io(output).add("written_book", 32, 8);
                        }
                    }
                }
                return recipe;
            }

            @Override
            public boolean accepts(ItemStack stack) {
                return super.accepts(stack) || stack.getItem() == GregTechItems.PrintedPages;
            }
        });
    }
}
