package muramasa.gregtech.tile.multi;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.base.PlatformFluidHandler;
import io.github.gregtechintergalactical.gtutility.blockentity.BlockEntityMaterialBasicMultiMachine;
import io.github.gregtechintergalactical.gtutility.machine.MaterialBasicMultiMachine;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.capability.fluid.FluidTank;
import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.machine.MultiblockTankMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractCapUtils;
import tesseract.TesseractGraphWrappers;
import tesseract.graph.INode;

import javax.annotation.Nullable;
import java.util.Optional;

import static muramasa.antimatter.data.AntimatterMaterials.Wood;
import static net.minecraft.core.Direction.DOWN;
import static net.minecraft.core.Direction.UP;

public class TileEntityLargeTank extends BlockEntityMaterialBasicMultiMachine<TileEntityLargeTank> {
    public TileEntityLargeTank(MultiblockTankMachine type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.fluidHandler.set(() -> new LargeTankFluidHandler(this, type.getCapacity(), 10000, 1, 0));
    }

    @Override
    public boolean allowsFakeTiles() {
        return true;
    }

    public Block getCasing(){
        Block block = AntimatterAPI.get(Block.class, material.getId() + "_wall", GTIRef.ID);
        if (block != null) return block;
        return Blocks.AIR;
    }

    public static class LargeTankFluidHandler extends MachineFluidHandler<TileEntityLargeTank> {

        public LargeTankFluidHandler(TileEntityLargeTank tile, int capacity, int pressure, int inputCount, int outputCount) {
            super(tile, capacity, pressure, inputCount, outputCount);
        }

        @Nullable
        @Override
        public FluidTanks getOutputTanks() {
            return super.getInputTanks();
        }

        @Override
        protected FluidTank getTank(int tank) {
            return getInputTanks().getTank(tank);
        }

        @Override
        public FluidTanks getTanks(int tank) {
            return getInputTanks();
        }

        @Override
        public void onUpdate() {
            super.onUpdate();
            if (tile.getLevel().getGameTime() % 20 == 0){
                Direction dir = tile.getFacing();
                if (getTank(0).getStoredFluid().getFluidAmount() > 0 && dir != UP){
                    BlockEntity adjacent = tile.getLevel().getBlockEntity(tile.getBlockPos().relative(dir));
                    if (adjacent != null){
                        Optional<PlatformFluidHandler> cap = TesseractCapUtils.getFluidHandler(tile.getLevel(), tile.getBlockPos().relative(dir), dir.getOpposite());
                        cap.ifPresent(other -> Utils.transferFluids(this.getOutputTanks(), other, 1000));
                    }
                }
            }
        }

        @Override
        public long insertFluid(FluidHolder fluid, boolean simulate) {
            if (tile.getMaterial() == Wood){
                if (FluidPlatformUtils.isFluidGaseous(fluid.getFluid())) {
                    long inserted = super.insertFluid(fluid, true);
                    if (inserted > 0) {
                        if (!simulate) tile.getLevel().playSound(null, tile.getBlockPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 1.0f);
                        return inserted;
                    }
                    return 0;
                }
                if (FluidPlatformUtils.getFluidTemperature(fluid.getFluid()) > 350){
                    long inserted = super.insertFluid(fluid, simulate);
                    if (inserted > 0 && !simulate){
                        meltdown();
                    }
                    return inserted;
                }
            }
            return super.insertFluid(fluid, simulate);
        }

        public boolean meltdown() {
            BlockPos offset = tile.getBlockPos().relative(tile.getFacing().getOpposite());
            int tX = offset.getX(), tY = offset.getY(), tZ = offset.getZ();
            for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) for (int k = -1; k <= 1; k++) {
                burn(tile.level, tX+i, tY+j, tZ+k);
                if (tile.getLevel().random.nextInt(4) == 0) tile.getLevel().setBlock(new BlockPos(tX+i, tY+j, tZ+k), Blocks.FIRE.defaultBlockState(), 3);
            }
            FluidHolder fluidHolder = getInputTanks().getTank(0).getStoredFluid();
            if (fluidHolder.getFluidAmount() >= 1000 * TesseractGraphWrappers.dropletMultiplier && fluidHolder.getFluid() == Fluids.LAVA){
                tile.getLevel().setBlock(offset, Blocks.LAVA.defaultBlockState(), 3);
            }
            tile.getLevel().setBlock(tile.getBlockPos(), Blocks.FIRE.defaultBlockState(), 3);
            return true;
        }

        public static void burn(Level aWorld, int aX, int aY, int aZ) {
            BlockPos pos = new BlockPos(aX, aY, aZ);
            for (Direction tSide : Direction.values()) {
                fire(aWorld, pos.relative(tSide), false);
            }
        }

        public static boolean fire(Level aWorld, BlockPos pos, boolean aCheckFlammability) {
            BlockState tBlock = aWorld.getBlockState(pos);
            if (tBlock.getMaterial() == Material.LAVA || tBlock.getMaterial() == Material.FIRE) return false;
            if (tBlock.getMaterial() == Material.CLOTH_DECORATION || tBlock.getCollisionShape(aWorld, pos).isEmpty()) {
                if (AntimatterPlatformUtils.getFlammability(tBlock, aWorld, pos, Direction.NORTH) > 0) return aWorld.setBlock(pos, Blocks.FIRE.defaultBlockState(), 3);
                if (aCheckFlammability) {
                    for (Direction tSide : Direction.values()) {
                        BlockState tAdjacent = aWorld.getBlockState(pos.relative(tSide));
                        if (tAdjacent.getBlock() == Blocks.CHEST || tAdjacent.getBlock() == Blocks.TRAPPED_CHEST) return aWorld.setBlock(pos, Blocks.FIRE.defaultBlockState(), 3);
                        if (AntimatterPlatformUtils.getFlammability(tAdjacent, aWorld, pos.relative(tSide), tSide.getOpposite()) > 0) return aWorld.setBlock(pos, Blocks.FIRE.defaultBlockState(), 3);
                    }
                } else {
                    return aWorld.setBlock(pos, Blocks.FIRE.defaultBlockState(), 3);
                }
            }
            return false;
        }
    }
}
