package muramasa.gregtech.blockentity.single;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.IFilterableHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.RecipeMaps;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.TesseractGraphWrappers;
import tesseract.api.item.ExtendedItemContainer;

import static muramasa.gregtech.data.Materials.Glue;

public class BlockEntityScanner extends BlockEntityMachine<BlockEntityScanner> implements IFilterableHandler {
    public BlockEntityScanner(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            @Override
            public IRecipe findRecipe() {
                IRecipe recipe = super.findRecipe();
                if (recipe == null){
                    MachineItemHandler<?> ih = itemHandler.orElse(null);
                    ExtendedItemContainer inputHandler = ih.getInputHandler();
                    ItemStack dataStick = inputHandler.getItem(0);
                    ItemStack stored = ih.getHandler(SlotType.STORAGE).getItem(0);
                    if (!dataStick.isEmpty() && dataStick.getItem() == GregTechData.DataStick){
                        CompoundTag prospect = dataStick.getTagElement("prospectData");
                        if (prospect != null){
                            ItemStack output = dataStick.copy();
                            output.getTagElement("prospectData").putBoolean("analyzed", true);
                            return RecipeMaps.SCANNING.RB().recipeMapOnly().ii(RecipeIngredient.of(dataStick.copy())).io(output).add("data_stick_prospection", 1000, 32);
                        }
                    }
                }
                return recipe;
            }

            @Override
            public boolean accepts(ItemStack stack) {
                return super.accepts(stack) || stack.getItem() == GregTechData.DataStick;
            }
        });
        //recipeHandler.set(() -> new ParallelRecipeHandler<>(this, true, 5));
    }

    @Override
    public boolean test(SlotType<?> slotType, int slot, ItemStack stack) {
        if (slotType == SlotType.STORAGE){
            return stack.getItem() == GregTechData.DataStick;
        }
        return true;
    }
}
