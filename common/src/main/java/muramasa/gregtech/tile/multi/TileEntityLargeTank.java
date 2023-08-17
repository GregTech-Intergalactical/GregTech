package muramasa.gregtech.tile.multi;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.base.PlatformFluidHandler;
import io.github.gregtechintergalactical.gtutility.blockentity.BlockEntityMaterialBasicMultiMachine;
import io.github.gregtechintergalactical.gtutility.machine.MaterialBasicMultiMachine;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.capability.fluid.FluidTank;
import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.machine.MultiblockTankMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractCapUtils;

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
        Block block = AntimatterAPI.get(Block.class, GTIRef.ID, material.getId() + "_wall");
        if (block != null) return block;
        return Blocks.BEDROCK;
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

            }
            return super.insertFluid(fluid, simulate);
        }
    }
}
