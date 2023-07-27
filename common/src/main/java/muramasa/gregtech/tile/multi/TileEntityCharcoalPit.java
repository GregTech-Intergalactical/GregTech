package muramasa.gregtech.tile.multi;

import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.tile.multi.TileEntityMultiMachine;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.Utils;
import muramasa.antimatter.util.int3;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.Machines;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TileEntityCharcoalPit extends TileEntityMachine<TileEntityCharcoalPit> {
    List<BlockPos> blockLists = new ArrayList<>();
    int progress = 0;
    int maxProgress = 0;
    boolean running = false;

    public TileEntityCharcoalPit(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        if (maxProgress > 0 && !blockLists.isEmpty() & this.level != null){
            if (this.getMachineState() == MachineState.IDLE) setMachineState(MachineState.ACTIVE);
            if (++progress >= maxProgress){
                blockLists.forEach(b -> {
                    this.getLevel().setBlock(b, GregTechData.BRITTLE_CHARCOAL.defaultBlockState(), 3);
                });
                setMachineState(MachineState.IDLE);
                maxProgress = 0;
                progress = 0;
            }
        } else if (maxProgress == 0 && this.getMachineState() == MachineState.ACTIVE){
            setMachineState(MachineState.IDLE);
        }
    }

    @Override
    public InteractionResult onInteractBoth(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, @Nullable AntimatterToolType type) {
        if (player.getItemInHand(hand).getItem() == Items.FLINT_AND_STEEL){
            if (maxProgress == 0 && checkRecursiveBlocks()){
                Utils.damageStack(player.getItemInHand(hand), hand, player);
                if (level.isClientSide) {
                    level.playSound(player, this.getBlockPos(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return super.onInteractBoth(state, world, pos, player, hand, hit, type);
    }

    private boolean checkRecursiveBlocks() {
        blockLists.clear();;
        List<int3> toCheck = new ArrayList<>();

        BlockPos.MutableBlockPos mutableBlockPos = this.getBlockPos().mutable();
        Block tBlock = this.getLevel().getBlockState(mutableBlockPos.move(0, -1, 0)).getBlock();
        if (!isWoodLog(tBlock)) {
            return false;
        } else {
            toCheck.add(new int3(0, -1, 0));
        }
        while (!toCheck.isEmpty()) {
            int3 tPos = toCheck.get(0);
            toCheck.remove(0);
            if (!checkAllBlockSides(tPos.getX(), tPos.getY(), tPos.getZ(), blockLists, toCheck)) {
                return false;
            }
        }
        this.maxProgress = (int) Math.sqrt(blockLists.size() * 240000);
        return true;
    }

    private boolean checkAllBlockSides(int aX, int aY, int aZ, List<BlockPos> toAdd, List<int3> toCheck) {
        boolean p1 = false;
        boolean p2 = false;
        boolean p3 = false;
        boolean p4 = false;
        boolean p5 = false;
        boolean p6 = false;
        Block tBlock = this.getBlock(aX + 1, aY, aZ);
        if (aX + 1 <  6 && (isWoodLog(tBlock))) {
            if (!toAdd.contains(getRelativePos(aX + 1, aY, aZ)) && (!toCheck.contains(new int3(aX + 1, aY, aZ))))
                p1 = true;
        } else if (!(tBlock.defaultBlockState().is(BlockTags.DIRT))) {
            return false;
        }

        tBlock = this.getBlock(aX - 1, aY, aZ);
        if (aX - 1 > -6 && (isWoodLog(tBlock))) {
            if (!toAdd.contains(getRelativePos(aX - 1, aY, aZ)) && (!toCheck.contains(new int3(aX - 1, aY, aZ))))
                p2 = true;
        } else if (!(tBlock.defaultBlockState().is(BlockTags.DIRT))) {
            return false;
        }

        tBlock = this.getBlock(aX, aY + 1, aZ);
        if (aY + 1 < 1 && (isWoodLog(tBlock))) {
            if (!toAdd.contains(getRelativePos(aX, aY + 1, aZ)) && (!toCheck.contains(new int3(aX, aY + 1, aZ))))
                p3 = true;
        }else if (!(tBlock.defaultBlockState().is(BlockTags.DIRT) ||
                (aX == 0 && aY == -1 && aZ == 0 && tBlock == Machines.CHARCOAL_PIT.getBlockState(Tier.NONE)))) {
            return false;
        }

        tBlock = this.getBlock(aX, aY - 1, aZ);
        if (aY - 1 > -6 && (isWoodLog(tBlock))) {
            if (!toAdd.contains(getRelativePos(aX, aY - 1, aZ)) && (!toCheck.contains(new int3(aX, aY - 1, aZ))))
                p4 = true;
        } else if (tBlock != Blocks.BRICKS) {
            return false;
        }

        tBlock = this.getBlock(aX, aY, aZ + 1);
        if (aZ + 1 < 6 && (isWoodLog(tBlock))) {
            if (!toAdd.contains(getRelativePos(aX, aY, aZ + 1)) && (!toCheck.contains(new int3(aX, aY, aZ + 1))))
                p5 = true;
        } else if (!(tBlock.defaultBlockState().is(BlockTags.DIRT))) {
            return false;
        }

        tBlock = this.getBlock(aX, aY, aZ - 1);
        if (aZ - 1 > -6 && (isWoodLog(tBlock))) {
            if (!toAdd.contains(getRelativePos(aX, aY, aZ - 1)) && (!toCheck.contains(new int3(aX, aY, aZ - 1))))
                p6 = true;
        } else if (!(tBlock.defaultBlockState().is(BlockTags.DIRT))) {
            return false;
        }
        toAdd.add(getRelativePos(aX, aY, aZ));
        if (p1) toCheck.add(new int3(aX + 1, aY, aZ));
        if (p2) toCheck.add(new int3(aX - 1, aY, aZ));
        if (p3) toCheck.add(new int3(aX, aY + 1, aZ));
        if (p4) toCheck.add(new int3(aX, aY - 1, aZ));
        if (p5) toCheck.add(new int3(aX, aY, aZ + 1));
        if (p6) toCheck.add(new int3(aX, aY, aZ - 1));
        return true;
    }

    private Block getBlock(int rX, int rY, int rZ){
        return this.getLevel().getBlockState(this.getBlockPos().offset(rX, rY, rZ)).getBlock();
    }

    private BlockPos getRelativePos(int rX, int rY, int rZ){
        return this.getBlockPos().offset(rX, rY, rZ);
    }

    public boolean isWoodLog(Block log){
        return log.defaultBlockState().is(BlockTags.LOGS);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (maxProgress > 0){
            tag.putInt("progress", progress);
            tag.putInt("maxProgress", maxProgress);
        }
        ListTag listTag = new ListTag();
        blockLists.forEach(b -> {
            CompoundTag tag1 = new CompoundTag();
            tag1.putInt("x", b.getX());
            tag1.putInt("y", b.getY());
            tag1.putInt("z", b.getZ());
            listTag.add(tag1);
        });
        if (!listTag.isEmpty()){
            tag.put("blockLists", listTag);
        }
    }

    @Override
    public void deserializeNBT(BlockState state, CompoundTag nbt) {
        super.deserializeNBT(state, nbt);
        if (nbt.contains("progress")){
            progress = nbt.getInt("progress");
            maxProgress = nbt.getInt("maxProgress");
        }
        if (nbt.contains("blockLists")){
            ListTag listTag = nbt.getList("blockLists", 10);
            listTag.forEach(t -> {
                CompoundTag tag = (CompoundTag) t;
                blockLists.add(new BlockPos(tag.getInt("x"), tag.getInt("y"), tag.getInt("z")));
            });
        }

    }
}
