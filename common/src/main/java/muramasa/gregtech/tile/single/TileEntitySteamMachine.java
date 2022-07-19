package muramasa.gregtech.tile.single;

import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.Collections;
import java.util.List;

import static muramasa.antimatter.machine.Tier.BRONZE;

public class TileEntitySteamMachine<T extends TileEntitySteamMachine<T>> extends TileEntityMachine<T> {

    public static final TagKey<Fluid> STEAM =  TagKey.create(Registry.FLUID_REGISTRY, new ResourceLocation((AntimatterPlatformUtils.isForge() ? "forge" : "c"), "steam"));

    public TileEntitySteamMachine(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new MachineRecipeHandler<T>((T)this) {
            @Override
            public boolean consumeResourceForRecipe(boolean simulate) {
                return tile.fluidHandler.map(t -> t.consumeTaggedInput(STEAM, (int) getPower(), simulate).getAmount() > 0)
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
                            if (data[0] instanceof FluidStack && ((FluidStack)data[0]).getFluid().builtInRegistryHolder().is(STEAM)) {
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
                return stack.getFluid().is(STEAM);
            }
        });
    }

    @Override
    public Tier getPowerLevel() {
        return Tier.LV;
    }
}
