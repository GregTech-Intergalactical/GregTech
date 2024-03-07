package muramasa.gregtech.blockentity.single;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.base.PlatformFluidHandler;
import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import muramasa.antimatter.blockentity.BlockEntityCache;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractCapUtils;
import tesseract.TesseractGraphWrappers;

import java.util.*;
import java.util.stream.Collectors;

public class BlockEntityPump extends BlockEntityMachine<BlockEntityPump> {
    int nextCheck = 0;
    byte mDir = 0;
    public ArrayList<BlockPos> mCheckList = new ArrayList<>();
    public LinkedList<BlockPos> mPumpList = new LinkedList<>();
    public Set<BlockPos> mChecked = new HashSet<>();
    public List<Fluid> mPumpedFluids = new ArrayList<>();
    public BlockEntityPump(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.fluidHandler.set(() -> new MachineFluidHandler<>(this, 8000 * (this.getMachineTier().getIntegerId()), 1000, 0, 1));
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        if (getMachineState() == MachineState.DISABLED) return;
        if (energyHandler.map(e -> e.getEnergy() < getMachineTier().getVoltage() / 2).orElse(false)) {
            if (getMachineState() == MachineState.ACTIVE) setMachineState(MachineState.IDLE);
            return;
        }
        nextCheck--;
        if (getMachineState() == MachineState.IDLE) {
            setMachineState(MachineState.ACTIVE);
        }
        exportFluid();
        if (mCheckList.isEmpty()) {
            if (nextCheck < 0) {
                // Reset everything and add the Fluid Block in front of the Pump to the Lists.
                scanForFluid(getBlockPos().relative(this.getFacing()));
                // Next Reset should only happen in two and a half Minutes or so.
                nextCheck = 3000;
            } else {
                if (mPumpList.isEmpty()) {
                    // We are done with this Y-Level, lets scan again in a second!
                    if (nextCheck > 20) nextCheck = 20;
                } else {
                    if (level.getGameTime() % ((6 - this.getMachineTier().getIntegerId()) * 20L) != 0){
                        return;
                    }
                    Boolean bool = drainFluid(mPumpList.removeLast()); // boxed boolean so I can have 3 values instead of 2
                    if (bool == null){
                        if (getMachineState() == MachineState.ACTIVE) setMachineState(MachineState.IDLE);
                    } else if (!bool){
                        // Something changed for some reason, lets scan again right away!
                        nextCheck = 0;
                    }
                }
            }
        } else {
            // If the List still contains Elements, then scan the next Y Level for more Fluids.
            scanForFluid(offsetX(), offsetZ());
            // Next Reset should only happen in two and a half Minutes or so.
            nextCheck = 3000;
        }
    }

    private Boolean drainFluid(BlockPos aCoords) {
        FluidState state = level.getFluidState(aCoords);
        Fluid fluid = state.getType();
        BlockState blockState = level.getBlockState(aCoords);
        // Seems like someone removed or replaced a Fluid Block! Scan again!
        if (!mPumpedFluids.contains(fluid)) return false;
        // Determine the Fluid that is produced.
        if (state.isSource()){
            FluidHolder stack = FluidHooks.newFluidHolder(fluid, 1000 * TesseractGraphWrappers.dropletMultiplier, null);
            if (fluidHandler.map(f -> f.fillOutput(stack, true) != 1000 * TesseractGraphWrappers.dropletMultiplier).orElse(false)){
                return null;
            }
            fluidHandler.ifPresent(f -> f.fillOutput(stack, false));
        }
        BlockState newState = Blocks.AIR.defaultBlockState();
        if (fluid == Fluids.WATER && blockState.getBlock() != Blocks.WATER && blockState.hasProperty(BlockStateProperties.WATERLOGGED) && blockState.getValue(BlockStateProperties.WATERLOGGED)){
            newState = blockState.setValue(BlockStateProperties.WATERLOGGED, false);
        }

        if (!level.setBlock(aCoords, newState, 11)) return false;

        if (this.level instanceof ServerLevel serverLevel) {
            serverLevel.getFluidTicks().clearArea(BoundingBox.fromCorners(aCoords.offset(-2, -2, -2), aCoords.offset(2, 2, 2)));
        }

        // Consume Energy based on Fluid Amount absorbed.
        energyHandler.ifPresent(e -> e.extractEu(getMachineTier().getVoltage() / 2, false));
        // If there is a Fluid Block above this one, clearly the Y-Level is off due to a recent Blockchange! Scan again!
        if (mPumpedFluids.contains(level.getFluidState(aCoords).getType())) return false;
        // Somehow this Block is completely surrounded by pumpable Fluid, this should not be possible unless it is the literal Cornercase! Scan again!
        return !(
                mPumpedFluids.contains(level.getFluidState(aCoords.offset(1, 0, 0)).getType()) &&
                        mPumpedFluids.contains(level.getFluidState(aCoords.offset(-1, 0, 0)).getType()) &&
                        mPumpedFluids.contains(level.getFluidState(aCoords.offset(0, 0, 1)).getType()) &&
                        mPumpedFluids.contains(level.getFluidState(aCoords.offset(0, 0, -1)).getType()));
    }

    public void exportFluid() {
        if (fluidHandler.map(f -> f.getOutputTanks().isEmpty()).orElse(false)) return;
        Arrays.stream(Direction.values()).filter(f -> f != this.getFacing()).collect(Collectors.toList()).forEach(this::exportFluidFromMachineToSide);
    }

    public void exportFluidFromMachineToSide(Direction side){
        if (fluidHandler.map(f -> f.getOutputTanks().isEmpty()).orElse(false)) return;
        Optional<PlatformFluidHandler> cap = BlockEntityCache.getFluidHandlerCached(getLevel(), getBlockPos().relative(side), side.getOpposite());
        fluidHandler.ifPresent(f -> cap.ifPresent(other -> Utils.transferFluids(f.getOutputTanks(), other, 1000)));
    }

    private void scanForFluid(int aX, int aZ) {
        BlockPos[] tNeedsToBeChecked = mCheckList.toArray(BlockPos[]::new);
        mCheckList.clear();

        for (BlockPos tPos : tNeedsToBeChecked) {
            if (mDir != 0 && mPumpedFluids.contains(level.getFluidState(tPos.offset(0, mDir, 0)).getType())) {
                mPumpList = new LinkedList<>();
                mCheckList.clear();
                mChecked.clear();
                addToList(tPos.getX(), tPos.getY() + mDir, tPos.getZ());
                return;
            }
            if (tPos.getX() < aX + 64) addToList(tPos.getX() + 1, tPos.getY(), tPos.getZ());
            if (tPos.getX() > aX - 64) addToList(tPos.getX() - 1, tPos.getY(), tPos.getZ());
            if (tPos.getZ() < aZ + 64) addToList(tPos.getX(), tPos.getY(), tPos.getZ() + 1);
            if (tPos.getZ() > aZ - 64) addToList(tPos.getX(), tPos.getY(), tPos.getZ() - 1);
        }
    }

    private int offsetX(){
        return this.getBlockPos().getX() + this.getFacing().getStepX();
    }

    private int offsetZ(){
        return this.getBlockPos().getZ() + this.getFacing().getStepZ();
    }

    private void scanForFluid(BlockPos offset) {
        mPumpList = new LinkedList<>();
        mCheckList.clear();
        mChecked.clear();

        mPumpedFluids.clear();
        FluidState aBlock = this.level.getFluidState(offset);
        if (!aBlock.isEmpty() && aBlock.getType() instanceof FlowingFluid fluid) {
            mPumpedFluids.add(fluid.getSource());
            mPumpedFluids.add(fluid.getFlowing());
            mDir = (byte)(FluidPlatformUtils.INSTANCE.isFluidGaseous(aBlock.getType()) ? -1 : +1);
        } else {
            energyHandler.ifPresent(e -> {
                e.extractEu(2, false);
            });
            return;
        }

        addToList(offset.getX(), offset.getY(), offset.getZ());
    }

    private boolean addToList(int aX, int aY, int aZ) {
        BlockPos tCoordinate = new BlockPos(aX, aY, aZ);
        if (mChecked.add(tCoordinate)) {
            if (mPumpedFluids.contains(level.getFluidState(tCoordinate).getType())) {
                mPumpList.add(tCoordinate);
                mCheckList.add(tCoordinate);
                return true;
            }
        }
        return false;
    }
}
