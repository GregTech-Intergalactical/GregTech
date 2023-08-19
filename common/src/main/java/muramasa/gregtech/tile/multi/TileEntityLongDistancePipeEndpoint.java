package muramasa.gregtech.tile.multi;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.base.PlatformFluidHandler;
import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.client.scene.TrackedDummyWorld;
import muramasa.antimatter.machine.MachineFlag;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.structure.StructureCache;
import muramasa.antimatter.tile.multi.TileEntityBasicMultiMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import tesseract.TesseractCapUtils;
import tesseract.api.item.ExtendedItemContainer;
import tesseract.api.item.PlatformItemHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TileEntityLongDistancePipeEndpoint extends TileEntityBasicMultiMachine<TileEntityLongDistancePipeEndpoint> {
    TileEntityLongDistancePipeEndpoint target = null, sender = null;
    public TileEntityLongDistancePipeEndpoint(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        if (type.has(MachineFlag.FLUID)){
            this.fluidHandler.set(() -> new MachineFluidHandler<>(this, 0, 0, 0, 0){
                @Override
                public boolean canInput(Direction direction) {
                    return direction == tile.getFacing();
                }

                @Override
                public long insertFluid(FluidHolder fluid, boolean simulate) {
                    if (tile.target == null) return 0;
                    PlatformFluidHandler fluidHandler1 = TesseractCapUtils.getFluidHandler(tile.target.level, tile.target.worldPosition.relative(tile.target.getFacing().getOpposite()), tile.target.getFacing()).orElse(null);
                    if (fluidHandler1 == null) return 0;
                    return fluidHandler1.insertFluid(fluid, simulate);
                }

                @Override
                public FluidHolder extractFluid(FluidHolder fluid, boolean simulate) {
                    return FluidHooks.emptyFluid();
                }
            });
        }
        if (type.has(MachineFlag.ITEM)){
            this.itemHandler.set(() -> new MachineItemHandler<>(this){
                @Override
                public Optional<ExtendedItemContainer> forSide(Direction side) {
                    return Optional.of(new ExtendedItemContainer() {
                        @Override
                        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                            if (tile.target == null) return stack;
                            PlatformItemHandler itemHandler1 = TesseractCapUtils.getItemHandler(tile.target.getLevel().getBlockEntity(tile.target.getBlockPos().relative(tile.target.getFacing().getOpposite())), tile.target.getFacing()).orElse(null);
                            if (itemHandler1 == null) return stack;
                            return itemHandler1.insertItem(slot, stack, simulate);
                        }

                        @Override
                        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
                            return ItemStack.EMPTY;
                        }

                        @Override
                        public int getSlotLimit(int slot) {
                            if (tile.target == null) return 0;
                            PlatformItemHandler itemHandler1 = TesseractCapUtils.getItemHandler(tile.target.getLevel().getBlockEntity(tile.target.getBlockPos().relative(tile.target.getFacing().getOpposite())), tile.target.getFacing()).orElse(null);
                            if (itemHandler1 == null) return 0;
                            return itemHandler1.getSlotLimit(slot);
                        }

                        @Override
                        public void deserialize(CompoundTag nbt) {

                        }

                        @Override
                        public CompoundTag serialize(CompoundTag nbt) {
                            return null;
                        }

                        @Override
                        public int getContainerSize() {
                            if (tile.target == null) return 0;
                            PlatformItemHandler itemHandler1 = TesseractCapUtils.getItemHandler(tile.target.getLevel().getBlockEntity(tile.target.getBlockPos().relative(tile.target.getFacing().getOpposite())), tile.target.getFacing()).orElse(null);
                            if (itemHandler1 == null) return 0;
                            return itemHandler1.getSlots();
                        }

                        @Override
                        public ItemStack getItem(int index) {
                            if (tile.target == null) return ItemStack.EMPTY;
                            PlatformItemHandler itemHandler1 = TesseractCapUtils.getItemHandler(tile.target.getLevel().getBlockEntity(tile.target.getBlockPos().relative(tile.target.getFacing().getOpposite())), tile.target.getFacing()).orElse(null);
                            if (itemHandler1 == null) return ItemStack.EMPTY;
                            return itemHandler1.getStackInSlot(index);
                        }

                        @Override
                        public void setItem(int index, ItemStack stack) {
                            if (tile.target == null) return;
                            PlatformItemHandler itemHandler1 = TesseractCapUtils.getItemHandler(tile.target.getLevel().getBlockEntity(tile.target.getBlockPos().relative(tile.target.getFacing().getOpposite())), tile.target.getFacing()).orElse(null);
                            if (itemHandler1 == null) return;
                            itemHandler1.setStackInSlot(index, stack);
                        }
                    });
                }
            });
        }
    }

    LongList successfulPositions;
    @Override
    public boolean checkStructure() {
        checkingStructure++;

        boolean fail = false;
        boolean succeed = false;
        Direction to = this.getFacing().getOpposite();
        BlockPos.MutableBlockPos mut = this.getBlockPos().mutable();
        successfulPositions = new LongArrayList();
        while (true){
            mut.move(to);
            BlockState state =  this.getLevel().getBlockState(mut);
            if (state.getBlock() == this.getBlockState().getBlock()){
                if (this.getLevel().getBlockEntity(mut) instanceof TileEntityLongDistancePipeEndpoint endpoint && endpoint.getFacing() == to.getOpposite()){
                    endpoint.validStructure = true;
                    this.target = endpoint;
                    this.target.sender = this;
                    successfulPositions.add(mut.asLong());
                    succeed = true;
                }
                break;
            }
            if (state.getBlock() == Blocks.DIAMOND_BLOCK){
                successfulPositions.add(mut.asLong());
                continue;
            }
            mut.move(to.getOpposite());
            int failed = 0;
            int succeeded = 0;
            for (Direction dir : Direction.Plane.HORIZONTAL){
                if (dir == to || dir == to.getOpposite()) continue;
                BlockState state2 =  this.getLevel().getBlockState(mut.immutable().relative(dir));
                if (state2.getBlock() == Blocks.DIAMOND_BLOCK){
                    if (succeeded == 0) to = dir;
                    succeeded++;
                } else {
                    failed++;
                }
            }
            if (failed == 2 || succeeded > 1){
                break;
            }
        }
        checkingStructure--;
        validStructure = succeed;
        if (validStructure){
            if (level instanceof TrackedDummyWorld) {
                StructureCache.add(level, worldPosition, successfulPositions);
                StructureCache.validate(level, worldPosition, successfulPositions, maxShares());
                checkingStructure--;
                return true;
            } else if (onStructureFormed() && StructureCache.validate(this.getLevel(), this.getBlockPos(), successfulPositions, maxShares())){
                if (isServerSide()){
                    afterStructureFormed();
                    if (machineState != MachineState.ACTIVE && machineState != MachineState.DISABLED) {
                        setMachineState(MachineState.IDLE);
                    }
                }
                sidedSync(true);
                StructureCache.add(level, getBlockPos(), successfulPositions);
            } else {
                validStructure = false;
            }

        }
        if (!validStructure) successfulPositions.clear();
        return validStructure;
    }

    public void onBlockUpdate(BlockPos pos) {
        if (checkingStructure > 0)
            return;
        if (validStructure) {
            BlockState state = this.getLevel().getBlockState(pos);
            if (successfulPositions.contains(pos.asLong()) && state.getBlock() != Blocks.DIAMOND_BLOCK){
                invalidateStructure();
            }
        } else {
            checkStructure();
        }
    }

    @Override
    protected void invalidateStructure() {
        super.invalidateStructure();
        if (target != null){
            target.validStructure = false;
            target.sender = null;
            target = null;
        }
    }
}
