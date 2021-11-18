package muramasa.gti.tile.single;

import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Explosion;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.Arrays;
import java.util.stream.Collectors;

import static muramasa.antimatter.machine.MachineState.ACTIVE;
import static muramasa.antimatter.machine.MachineState.IDLE;
import static muramasa.antimatter.machine.Tier.BRONZE;
import static muramasa.gti.data.Materials.DistilledWater;
import static muramasa.gti.data.Materials.Steam;
import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE;

public class TileEntityCoalBoiler extends TileEntityMachine<TileEntityCoalBoiler> {
    int maxHeat = 500, heat, fuel = 0, maxFuel, lossTimer = 0;
    boolean hadNoWater;

    public TileEntityCoalBoiler(Machine<?> type) {
        super(type);
        fluidHandler.set(() -> new CoalBoilerFluidHandler(this));
        recipeHandler.set(() -> new CoalBoilerRecipeHandler(this));
    }

    public int getFuel() {
        return recipeHandler.map(r -> (((CoalBoilerRecipeHandler) r).getFuel())).orElse(0);
    }

    public int getHeat() {
        return recipeHandler.map(r -> (((CoalBoilerRecipeHandler) r).getHeat())).orElse(0);
    }

    public int getMaxFuel() {
        return recipeHandler.map(r -> (((CoalBoilerRecipeHandler) r).getMaxFuel())).orElse(0);

    }

    public int getMaxHeat() {
        return recipeHandler.map(r -> (((CoalBoilerRecipeHandler) r).getMaxHeat())).orElse(0);

    }

    public static class CoalBoilerRecipeHandler extends MachineRecipeHandler<TileEntityCoalBoiler> {
        int maxHeat = 500, heat, fuel = 0, maxFuel, lossTimer = 0;
        boolean hadNoWater;

        protected final IIntArray GUI_SYNC_DATA2 = new IIntArray() {

            @Override
            public int get(int index) {
                switch (index) {
                    case 0:
                        return CoalBoilerRecipeHandler.this.heat;
                    case 1:
                        return CoalBoilerRecipeHandler.this.maxHeat;
                    case 2:
                        return CoalBoilerRecipeHandler.this.fuel;
                    case 3:
                        return CoalBoilerRecipeHandler.this.maxFuel;
                }
                return 0;
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        CoalBoilerRecipeHandler.this.heat = value;
                        break;
                    case 1:
                        CoalBoilerRecipeHandler.this.maxHeat = value;
                        break;
                    case 2:
                        CoalBoilerRecipeHandler.this.fuel = value;
                        break;
                    case 3:
                        CoalBoilerRecipeHandler.this.maxFuel = value;
                        break;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };

        public CoalBoilerRecipeHandler(TileEntityCoalBoiler tile) {
            super(tile);
            GUI_SYNC_DATA2.set(0, 0);
            maxHeat = tile.getMachineTier() == BRONZE ? 500 : 1000;
        }

        public int getFuel() {
            return fuel;
        }

        public int getHeat() {
            return heat;
        }

        public int getMaxFuel() {
            return maxFuel;
        }

        public int getMaxHeat() {
            return maxHeat;
        }

        @Override
        public void onServerUpdate() {
            if (this.heat <= 20) {
                this.heat = 20;
                this.lossTimer = 0;
            }
            int delay = tile.getMachineTier() == BRONZE ? 45 : 40;
            if (++this.lossTimer > delay) {
                this.heat -= 1;
                this.lossTimer = 0;
            }
            Arrays.stream(Direction.values()).filter(f -> f != Direction.DOWN).collect(Collectors.toList()).forEach(this::exportFluidFromMachineToSide);
            delay = tile.getMachineTier() == BRONZE ? 25 : 10;
            if (tile.getLevel().getGameTime() % delay == 0) {
                tile.fluidHandler.ifPresent(f -> {
                    FluidStack[] inputs = f.getInputs();
                    if (this.heat > 100) {
                        if (inputs[0].getAmount() == 0) {
                            hadNoWater = true;
                        } else {
                            if (hadNoWater) {
                                tile.getLevel().explode(null, tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ(), 4.0F, Explosion.Mode.DESTROY);
                                tile.getLevel().setBlockAndUpdate(tile.getBlockPos(), Blocks.AIR.defaultBlockState());
                                return;
                            }
                            f.drainInput(new FluidStack(Fluids.WATER, 1), IFluidHandler.FluidAction.EXECUTE);
                            int room = 16000 - f.getOutputs()[0].getAmount();
                            int fill = Math.min(room, 150);
                            if (room > 0){
                                f.fillOutput(Steam.getGas(fill), IFluidHandler.FluidAction.EXECUTE);
                            }
                            if (fill < 150) {
                                //TODO:steam sounds
                                tile.getLevel().playSound(null, tile.getBlockPos(), SoundEvents.FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 1.0f);
                                if (tile.getLevel() instanceof ServerWorld)
                                    ((ServerWorld) tile.getLevel()).sendParticles(ParticleTypes.SMOKE, tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ(), tile.getLevel().getRandom().nextInt(8) + 1, 0.0D, 0.2D, 0.0D, 0.0D);
                                f.drain(4000, IFluidHandler.FluidAction.EXECUTE);
                            }
                        }
                    } else {
                        this.hadNoWater = false;
                    }
                });
            }
            super.onServerUpdate();
        }

        public void exportFluidFromMachineToSide(Direction side){
            TileEntity adjTile = tile.getLevel().getBlockEntity(tile.getBlockPos().relative(side));
            if (adjTile == null) return;
            LazyOptional<IFluidHandler> cap = adjTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side.getOpposite());
            tile.fluidHandler.ifPresent(f -> cap.ifPresent(other -> Utils.transferFluids(f.getOutputTanks(), other, 1000)));
        }

        @Override
        protected MachineState recipeFinish() {
            if (!canRecipeContinue()) {
                this.resetRecipe();
                return IDLE;
            } else {
                return ACTIVE;
            }
        }

        @Override
        protected MachineState tickRecipe() {
            if (this.activeRecipe == null) {
                System.out.println("Check Recipe when active recipe is null");
                return tile.getMachineState();
            }
            else {
                tile.onRecipePreTick();
                if (fuel <= 0 && canRecipeContinue()) {
                    if (fuel < 0) {
                        fuel = 0;
                    }
                    this.maxFuel = activeRecipe.getDuration();
                    addOutputs();
                    this.fuel += maxFuel;
                    consumeInputs();
                }
                if ((this.heat < maxHeat) && (this.fuel > 0) && (tile.getLevel().getGameTime() % 12L == 0L)) {
                    int fuelSubtract = tile.getMachineTier() == BRONZE ? 1 : 2;
                    this.fuel -= fuelSubtract;
                    this.heat += 1;
                }
                tile.onRecipePostTick();
                if (fuel == 0){
                    return this.recipeFinish();
                }
                return ACTIVE;
            }
        }

        public void setActive(boolean t){
            if (t && tile.getMachineState() != ACTIVE){
                tile.setMachineState(ACTIVE);
            } else if (!t && tile.getMachineState() == ACTIVE){
                tile.setMachineState(IDLE);
            }
        }

        @Override
        public boolean accepts(FluidStack fluid) {
            return fluid.getFluid() == Fluids.WATER || fluid.getFluid() == DistilledWater.getLiquid();
        }

        @Override
        public boolean canOutput() {
            return true;
        }

        @Override
        public CompoundNBT serializeNBT() {
            CompoundNBT nbt = super.serializeNBT();
            nbt.putInt("heat", heat);
            nbt.putInt("maxHeat", maxHeat);
            nbt.putInt("fuel", fuel);
            nbt.putInt("maxFuel", maxFuel);
            nbt.putInt("lossTimer", lossTimer);
            nbt.putBoolean("hadNoWater", hadNoWater);
            return nbt;
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt) {
            super.deserializeNBT(nbt);
            this.heat = nbt.getInt("heat");
            this.maxHeat = nbt.getInt("maxHeat");
            this.fuel = nbt.getInt("fuel");
            this.maxFuel = nbt.getInt("maxFuel");
            this.lossTimer = nbt.getInt("lossTimer");
            this.hadNoWater = nbt.getBoolean("hadNoWater");
        }
    }

    public static class CoalBoilerFluidHandler extends MachineFluidHandler<TileEntityCoalBoiler> {

        private boolean fillingCell = false;

        public CoalBoilerFluidHandler(TileEntityCoalBoiler tile) {
            super(tile, 16000, 1000 * (250 + tile.getMachineTier().getIntegerId()));
            tanks.put(FluidDirection.INPUT, FluidTanks.create(tile, ContentEvent.FLUID_INPUT_CHANGED, b -> {
                b.tank(16000);
                return b;
            }));
            tanks.put(FluidDirection.OUTPUT, FluidTanks.create(tile, ContentEvent.FLUID_OUTPUT_CHANGED, b -> {
                b.tank(16000);
                return b;
            }));
        }

        @Override
        public void fillCell(int cellSlot, int maxFill) {
            if (fillingCell) return;
            fillingCell = true;
            if (getInputTanks() != null) {
                tile.itemHandler.ifPresent(ih -> {
                    if (ih.getCellInputHandler() == null) return;
                    ItemStack cell = ih.getCellInputHandler().getStackInSlot(cellSlot);
                    if (cell.isEmpty()) return;
                    ItemStack toActOn = cell.copy();
                    toActOn.setCount(1);
                    toActOn.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).ifPresent(cfh -> {
                        ItemStack checkContainer = toActOn.copy().getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).map(t -> {
                            if (t.getFluidInTank(0).isEmpty()) {
                                t.fill(FluidUtil.tryFluidTransfer(t,this.getInputTanks(), maxFill, false), EXECUTE);
                            } else {
                                t.drain(maxFill, EXECUTE);
                            }
                            return t.getContainer();
                        }).orElse(null/* throw exception */);
                        if (!ih.getCellOutputHandler().insertItem(cellSlot,checkContainer,true).isEmpty()) return;

                        FluidStack stack;
                        if (cfh.getFluidInTank(0).isEmpty()) {
                            stack = FluidUtil.tryFluidTransfer(cfh,this.getInputTanks(), maxFill, true);
                        } else {
                            stack = FluidUtil.tryFluidTransfer(this.getInputTanks(),cfh, maxFill, true);
                        }
                        if (!stack.isEmpty()) {
                            ItemStack insert = cfh.getContainer();
                            insert.setCount(1);
                            ih.getCellOutputHandler().insertItem(cellSlot, insert, false);
                            ih.getCellInputHandler().extractItem(cellSlot, 1, false);
                        }
                    });
                });
            }
            fillingCell = false;
        }
    }
}
