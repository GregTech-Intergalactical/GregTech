package muramasa.gregtech.tile.single;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.gregtech.data.GregTechTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.TesseractGraphWrappers;

import static muramasa.antimatter.machine.Tier.BRONZE;
import static muramasa.gregtech.data.Machines.STEAM_FORGE_HAMMER;

public class TileEntitySteamMachine extends TileEntityMachine<TileEntitySteamMachine> {

    public TileEntitySteamMachine(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new SteamMachineRecipeHandler(this));
    }

    @Override
    public Tier getPowerLevel() {
        return Tier.LV;
    }

    public static class SteamMachineRecipeHandler extends MachineRecipeHandler<TileEntitySteamMachine>{
        protected boolean isSteamClear = false;

        public SteamMachineRecipeHandler(TileEntitySteamMachine tile) {
            super(tile);
        }

        @Override
        public boolean consumeResourceForRecipe(boolean simulate) {
            return tile.fluidHandler.map(t -> t.consumeTaggedInput(GregTechTags.STEAM, getPower() * TesseractGraphWrappers.dropletMultiplier, simulate).getFluidAmount() > 0)
                    .orElse(false);
        }
        //Allow up to 16 .
        @Override
        protected boolean validateRecipe(IRecipe r) {
            return r.getPower() <= Tier.LV.getVoltage();
        }

        public void setSteamClear(boolean steamClear) {
            isSteamClear = steamClear;
        }

        @Override
        protected boolean canRecipeContinue() {
            isSteamClear = tile.level.isEmptyBlock(tile.worldPosition.relative(tile.getOutputFacing()));
            return super.canRecipeContinue() && isSteamClear;
        }

        @Override
        public float getClientProgress() {
            if (tile.getMachineType() == STEAM_FORGE_HAMMER){
                float percent = (float) currentProgress / ((float) maxProgress / 3);
                if (percent > 2){
                    percent -= 2;
                } else if (percent > 1){
                    percent -=1;
                }
                return percent;
            }
            return super.getClientProgress();
        }

        @Override
        public int getOverclock() {
            return tile.getMachineTier() == BRONZE ? 0 : 1;
        }

        @Override
        public boolean accepts(FluidHolder stack) {
            return stack.getFluid().builtInRegistryHolder().is(GregTechTags.STEAM);
        }

        @Override
        protected boolean consumeGeneratorResources(boolean simulate) {
            return isSteamClear && super.consumeGeneratorResources(simulate);
        }

        @Override
        public void onMachineEvent(IMachineEvent event, Object... data) {
            super.onMachineEvent(event, data);
            if (event instanceof ContentEvent) {
                if (event == ContentEvent.FLUID_INPUT_CHANGED) {
                    if (data != null && data.length > 0) {
                        if (data[0] instanceof FluidHolder && ((FluidHolder)data[0]).getFluid().builtInRegistryHolder().is(GregTechTags.STEAM)) {
                            checkRecipe();
                        }
                    }
                }
            }
        }
    }
}
