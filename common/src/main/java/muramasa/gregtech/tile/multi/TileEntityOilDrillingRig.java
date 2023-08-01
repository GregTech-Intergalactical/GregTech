package muramasa.gregtech.tile.multi;

import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.Materials;
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
import net.minecraft.world.level.material.FluidState;

import javax.annotation.Nullable;

import static muramasa.antimatter.machine.Tier.*;

public class TileEntityOilDrillingRig extends TileEntityMultiMachine<TileEntityOilDrillingRig> {
    boolean foundBottom = false;
    boolean erroring = false;
    BlockPos.MutableBlockPos miningPos = this.getBlockPos().mutable();

    public TileEntityOilDrillingRig(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        if (erroring || !validStructure) return;
        ItemStack stack = itemHandler.map(i -> i.getHandler(SlotType.STORAGE).getStackInSlot(0)).orElse(ItemStack.EMPTY);
        if (stack.getItem() == GregTechData.MINING_PIPE.asItem() || foundBottom){
            if (!foundBottom){
                miningPos.below();
                BlockState block = level.getBlockState(miningPos);

                if (block.isAir()) return;
                if (!destroyBlock(level, pos, true, null, Items.DIAMOND.getDefaultInstance())){
                    erroring = true;
                } else {
                    level.setBlock(miningPos, GregTechData.MINING_PIPE.defaultBlockState(), 0);
                }
            }
        }
    }

    public boolean destroyBlock(Level level, BlockPos pos, boolean dropBlock, @Nullable Entity entity, ItemStack item) {
        BlockState blockstate = level.getBlockState(pos);
        if (blockstate.isAir()) {
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
                    blockstate.getBlock().getDrops(blockstate, (ServerLevel)level, pos, blockentity, entity, item).forEach((itemStack) -> {
                        itemHandler.ifPresent(i -> i.addOutputs(itemStack));
                    });
                }
            }

            boolean flag = level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3, 512);
            /*if (flag) {
                level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(entity, blockstate));
            }*/

            return flag;
        }
    }
}
