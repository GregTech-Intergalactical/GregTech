package muramasa.gregtech.tile.multi;

import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.antimatter.util.int3;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.Nullable;

import static muramasa.gregtech.data.GregTechData.MINING_PIPE;
import static muramasa.gregtech.data.GregTechData.MINING_PIPE_THIN;

public class TileEntityOilDrillingRig extends TileEntityMultiMachine<TileEntityOilDrillingRig> {
    boolean foundBottom = false;
    boolean stopped = false;
    BlockPos.MutableBlockPos miningPos;

    public TileEntityOilDrillingRig(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        miningPos = new int3(pos, this.getFacing(state)).back(1);
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        if (!validStructure) return;
        ItemStack stack = itemHandler.map(i -> i.getHandler(SlotType.STORAGE).getStackInSlot(0)).orElse(ItemStack.EMPTY);
        if (stack.getItem() == GregTechData.MINING_PIPE_THIN.asItem() || foundBottom){
            if (!foundBottom){

                if (getMachineState() != MachineState.ACTIVE) setMachineState(MachineState.ACTIVE);
                if (level.getGameTime() % 40 != 0) return;
                if (!stopped) miningPos.below();

                BlockState block = level.getBlockState(miningPos);

                if (block.getBlock() == Blocks.BEDROCK || block.getBlock() == Blocks.VOID_AIR){
                    foundBottom = true;
                    return;
                }

                if (!destroyBlock(level, miningPos.immutable(), true, null, Items.NETHERITE_PICKAXE.getDefaultInstance())){
                    stopped = true;
                    return;
                }
                stack.shrink(1);
            }
        } else {
            if (getMachineState() == MachineState.ACTIVE) setMachineState(MachineState.IDLE);
        }
    }

    public boolean destroyBlock(Level level, BlockPos pos, boolean dropBlock, @Nullable Entity entity, ItemStack item) {
        BlockState blockstate = level.getBlockState(pos);
        if (blockstate.getDestroySpeed(level, pos) < 0) {
            return false;
        } else {
            FluidState fluidstate = level.getFluidState(pos);
            if (!(blockstate.getBlock() instanceof BaseFireBlock)) {
                level.levelEvent(2001, pos, Block.getId(blockstate));
            }

            CompoundTag tag = item.getTag();
            BlockEntity blockentity = blockstate.hasBlockEntity() ? level.getBlockEntity(pos) : null;
            //BlockEve event = new BlockEvent.BreakEvent(level, pos, blockstate, entity instanceof Player player ? player : null);
            //MinecraftForge.EVENT_BUS.post(event);
            /*if (event.isCanceled()){
                return false;
            }*/
            if (dropBlock) {
                if (level instanceof ServerLevel) {
                    Block.dropResources(blockstate, level, pos);
                }
            }

            boolean flag = level.setBlock(pos, MINING_PIPE.defaultBlockState(), 3, 512) || blockstate.getBlock() == MINING_PIPE;
            if (flag && pos.getY() + 1 < this.getBlockPos().getY()) {
                level.setBlock(pos.above(), MINING_PIPE_THIN.defaultBlockState(), 11);
                //level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(entity, blockstate));
            }

            return flag;
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("foundBottom", foundBottom);
        tag.putLong("miningPos", miningPos.asLong());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.foundBottom = nbt.getBoolean("foundBottom");
        this.miningPos = BlockPos.of(nbt.getLong("miningPos")).mutable();
    }
}
