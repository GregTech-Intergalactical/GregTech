package muramasa.gregtech.blockentity.miniportals;

import io.github.gregtechintergalactical.gtcore.data.GTCoreTags;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.Utils;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class BlockEntityMiniPortal extends BlockEntityMachine<BlockEntityMiniPortal> {

    public static List<BlockEntityMiniPortal>
            sListWorldSide  = new ArrayList<>();
    BlockEntityMiniPortal otherSide;
    BlockPos otherSidePos = null;
    ResourceLocation otherSideDimension;
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
            addThisPortalToLists();
        }
    }

    @Override
    public void onFirstTick() {
        super.onFirstTick();
        if (otherSidePos != null && otherSideDimension != null){
            if (AntimatterPlatformUtils.getCurrentServer().getLevel(ResourceKey.create(Registry.DIMENSION_REGISTRY, otherSideDimension)).getBlockEntity(otherSidePos) instanceof BlockEntityMiniPortal portal){
                this.otherSide = portal;
                otherSideDimension = null;
                otherSidePos = null;
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
        if (isPortalSetter(stack) && setPortal()){
            if (!player.isCreative()) {
                if (stack.isDamageableItem()) {
                    Utils.damageStack(stack, hand, player);
                } else {
                    stack.shrink(1);
                }
            }
            if (level.isClientSide()) {
                playActivationSound(player);
            }
            return InteractionResult.SUCCESS;
        }
        return super.onInteractBoth(state, world, pos, player, hand, hit, type);
    }

    protected abstract boolean isPortalSetter(ItemStack stack);

    protected abstract void playActivationSound(Player player);

    public abstract List<BlockEntityMiniPortal> getPortalListA();
    public abstract List<BlockEntityMiniPortal> getPortalListB();

    protected abstract void findTargetPortal();

    public abstract void addThisPortalToLists();

    private boolean setPortal(){
        if (level == null) return false;
        if (getMachineState() != MachineState.ACTIVE){
            findTargetPortal();
            this.setMachineState(MachineState.ACTIVE);
            return true;
        }
        return false;
    }

    protected boolean isSame(BlockEntityMiniPortal otherSide){
        return otherSide.getBlockState().getBlock() == this.getBlockState().getBlock();
    }

    @Override
    public void setMachineState(MachineState newState) {
        super.setMachineState(newState);
        if (level != null){
            invalidateCaps();
        }
    }

    @Override
    public void onRemove() {
        super.onRemove();
        if (otherSide != null){
            otherSide.setOtherSide(null);
        }
        getPortalListA().remove(this);
        getPortalListB().remove(this);
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
        otherSidePos = new BlockPos(pos.getInt("X"), pos.getInt("Y"), pos.getInt("Z"));
        otherSideDimension = new ResourceLocation(otherSide.getString("dimension"));
    }

    @Override
    public boolean toggleMachine() {
        return false;
    }

    @Override
    public List<String> getInfo(boolean simple) {
        List<String> info = super.getInfo(simple);
        if (otherSide != null){
            info.add("Target at: x: " + otherSide.getBlockPos().getX() + " y: " + otherSide.getBlockPos().getY() + " z: " + otherSide.getBlockPos().getZ() + " in " + otherSide.getLevel().dimension().location());
        } else {
            info.add("No target");
        }
        return info;
    }
}
