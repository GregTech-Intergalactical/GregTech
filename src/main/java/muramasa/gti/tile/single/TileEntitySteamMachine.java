package muramasa.gti.tile.single;

import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.gti.data.Materials;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static muramasa.antimatter.machine.Tier.BRONZE;

public class TileEntitySteamMachine<T extends TileEntitySteamMachine<T>> extends TileEntityMachine<T> {

    private static final ResourceLocation STEAM = new ResourceLocation("forge", "steam");

    public TileEntitySteamMachine(Machine<?> type) {
        super(type);
        recipeHandler.set(() -> new MachineRecipeHandler<T>((T)this) {
            @Override
            public boolean consumeResourceForRecipe(boolean simulate) {
                return tile.fluidHandler.map(t -> t.consumeAndReturnInputs(Arrays.asList(Materials.Steam.getGas((int)getPower())), simulate).size() == 0)
                        .orElse(false);
            }
            //Allow up to 16 .
            @Override
            protected boolean validateRecipe(Recipe r) {
                List<ItemStack> consumed = this.tile.itemHandler.map(t -> t.consumeInputs(r, true)).orElse(Collections.emptyList());
                return r.getPower() <= Tier.LV.getVoltage() && consumed.size() > 0;
            }

            @Override
            public void onMachineEvent(IMachineEvent event, Object... data) {
                super.onMachineEvent(event, data);
                if (event instanceof ContentEvent) {
                    if (event == ContentEvent.FLUID_INPUT_CHANGED) {
                        if (data != null && data.length > 0) {
                            if (data[0] instanceof FluidStack && ((FluidStack)data[0]).getFluid().getTags().contains(STEAM)) {
                                checkRecipe();
                            }
                        }
                    }
                }
            }

            @Override
            public int getOverclock() {
                return tile.getMachineTier() == BRONZE ? 0 : 1;
            }

            @Override
            public boolean accepts(FluidStack stack) {
                return stack.getFluid().getTags().contains(STEAM);
            }
        });
    }

    @Override
    public Tier getPowerLevel() {
        return Tier.LV;
    }
}
