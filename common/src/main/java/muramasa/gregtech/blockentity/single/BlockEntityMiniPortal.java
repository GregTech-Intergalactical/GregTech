package muramasa.gregtech.blockentity.single;

import io.github.gregtechintergalactical.gtcore.data.GTCoreTags;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.data.GregTechTags;
import muramasa.gregtech.data.Machines;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BlockEntityMiniPortal extends BlockEntityMachine<BlockEntityMiniPortal> {

    public static List<BlockEntityMiniPortal>
            sListNetherSide = new ArrayList<>(),
            sListWorldSide  = new ArrayList<>();
    BlockEntityMiniPortal otherSide;
    public BlockEntityMiniPortal(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public BlockEntityMiniPortal getOtherSide() {
        return otherSide;
    }

    public void setOtherSide(BlockEntityMiniPortal otherSide) {
        this.otherSide = otherSide;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (level != null && isServerSide()) {
            if (level.dimension() == Level.OVERWORLD) {
                if (!sListWorldSide.contains(this)) sListWorldSide.add(this);
            } else if (level.dimension() == Level.NETHER) {
                if (!sListNetherSide.contains(this)) sListNetherSide.add(this);
            }
        }
    }

    int invalidatingCaps = 0;
    @Override
    public void invalidateCaps() {
        if (invalidatingCaps > 0) return;
        invalidatingCaps++;
        super.invalidateCaps();
        if (otherSide != null){
            otherSide.invalidateCaps();
        }
        invalidatingCaps--;
    }

    @Override
    public void invalidateCap(Class<?> cap) {
        if (invalidatingCaps > 0) return;
        invalidatingCaps++;
        super.invalidateCap(cap);
        if (otherSide != null){
            otherSide.invalidateCap(cap);
        }
        invalidatingCaps--;
    }

    @Override
    public void invalidateCaps(Direction side) {
        if (invalidatingCaps > 0) return;
        invalidatingCaps++;
        super.invalidateCaps(side);
        if (otherSide != null){
            otherSide.invalidateCaps(side);
        }
        invalidatingCaps--;
    }

    @Override
    protected boolean allowExplosionsInRain() {
        return false;
    }

    @Override
    public InteractionResult onInteractBoth(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, @Nullable AntimatterToolType type) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(GTCoreTags.FIRESTARTER) && setPortal()){
            if (stack.isDamageableItem()) {
                Utils.damageStack(stack, hand, player);
            } else {
                stack.shrink(1);
            }
            if (level.isClientSide) {
                level.playSound(player, this.getBlockPos(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            }
            return InteractionResult.SUCCESS;
        }
        return super.onInteractBoth(state, world, pos, player, hand, hit, type);
    }

    private boolean setPortal(){
        if (level == null) return false;
        if (getMachineState() != MachineState.ACTIVE){
            findTargetPortal();
            this.setMachineState(MachineState.ACTIVE);
            return true;
        }
        return false;
    }

    @Override
    public void setMachineState(MachineState newState) {
        super.setMachineState(newState);
        invalidateCaps();
    }

    public void findTargetPortal() {
        otherSide = null;
        if (level != null && isServerSide()) {
            if (level.dimension() == Level.OVERWORLD) {
                long tShortestDistance = 128*128;
                for (BlockEntityMiniPortal tTarget : sListNetherSide) if (tTarget != this && !tTarget.isRemoved()) {
                    long tXDifference = getBlockPos().getX()-tTarget.getBlockPos().getX()*8, tZDifference = getBlockPos().getZ()-tTarget.getBlockPos().getZ()*8;
                    long tTempDist = tXDifference * tXDifference + tZDifference * tZDifference;
                    if (tTempDist < tShortestDistance) {
                        tShortestDistance = tTempDist;
                        otherSide = tTarget;
                    } else if (tTempDist == tShortestDistance && (otherSide == null || Math.abs(tTarget.getBlockPos().getY()-getBlockPos().getY()) < Math.abs(otherSide.getBlockPos().getY()-getBlockPos().getY()))) {
                        otherSide = tTarget;
                    }
                }
            } else if (level.dimension() == Level.NETHER) {
                long tShortestDistance = 128*128;
                for (BlockEntityMiniPortal tTarget : sListWorldSide) if (tTarget != this && !tTarget.isRemoved()) {
                    long tXDifference = tTarget.getBlockPos().getX()-getBlockPos().getX()*8, tZDifference = tTarget.getBlockPos().getZ()-getBlockPos().getZ()*8;
                    long tTempDist = tXDifference * tXDifference + tZDifference * tZDifference;
                    if (tTempDist < tShortestDistance) {
                        tShortestDistance = tTempDist;
                        otherSide = tTarget;
                    } else if (tTempDist == tShortestDistance && (otherSide == null || Math.abs(tTarget.getBlockPos().getY()-getBlockPos().getY()) < Math.abs(otherSide.getBlockPos().getY()-getBlockPos().getY()))) {
                        otherSide = tTarget;
                    }
                }
            }
            if (otherSide != null){
                otherSide.setOtherSide(this);
                if (otherSide.getMachineState() != MachineState.ACTIVE){
                    otherSide.setMachineState(MachineState.ACTIVE);
                }
            }
        }
    }

    @Override
    public void onRemove() {
        super.onRemove();
        if (otherSide != null){
            otherSide.setOtherSide(null);
        }
        sListWorldSide.remove(this);
        sListNetherSide.remove(this);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (this.otherSide == null) return;
        CompoundTag otherSide = new CompoundTag();
        CompoundTag pos = new CompoundTag();
        pos.putInt("X", this.otherSide.getBlockPos().getX());
        pos.putInt("Y", this.otherSide.getBlockPos().getY());
        pos.putInt("Z", this.otherSide.getBlockPos().getZ());
        otherSide.put("pos", pos);
        otherSide.putString("dimension", this.otherSide.getLevel().dimension().location().toString());
        tag.put("otherSide", otherSide);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (!tag.contains("otherSide")) return;
        CompoundTag otherSide = tag.getCompound("otherSide");
        CompoundTag pos = otherSide.getCompound("pos");
        BlockPos blockPos = new BlockPos(pos.getInt("X"), pos.getInt("Y"), pos.getInt("Z"));
        ResourceLocation dimension = new ResourceLocation(otherSide.getString("dimension"));
        if (AntimatterPlatformUtils.getCurrentServer().getLevel(ResourceKey.create(Registry.DIMENSION_REGISTRY, dimension)).getBlockEntity(blockPos) instanceof BlockEntityMiniPortal portal){
            this.otherSide = portal;
        }
    }

    @Override
    public boolean toggleMachine() {
        return false;
    }
}
