package muramasa.gregtech.blockentity.miniportals;

import io.github.gregtechintergalactical.gtcore.data.GTCoreTags;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class BlockEntityMiniEndPortal extends BlockEntityMiniPortal{
    public static List<BlockEntityMiniPortal> sListEndSide = new ArrayList<>();
    public BlockEntityMiniEndPortal(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    protected boolean isPortalSetter(ItemStack stack){
        return stack.is(Items.ENDER_EYE);
    }

    @Override
    protected void playActivationSound(Player player){

    }

    @Override
    public List<BlockEntityMiniPortal> getPortalListA() {
        return sListWorldSide;
    }

    @Override
    public List<BlockEntityMiniPortal> getPortalListB() {
        return sListEndSide;
    }


    @Override
    public void addThisPortalToLists() {
        if (level.dimension() == Level.OVERWORLD) {
            if (!sListWorldSide.contains(this)) sListWorldSide.add(this);
        } else if (level.dimension() == Level.END) {
            if (!sListEndSide.contains(this)) sListEndSide.add(this);
        }
    }

    @Override
    protected void findTargetPortal() {
        otherSide = null;
        if (level != null && isServerSide()) {
            if (level.dimension() == Level.OVERWORLD) {
                long tShortestDistance = 512*512;
                for (BlockEntityMiniPortal tTarget : sListEndSide) if (tTarget != this && !tTarget.isRemoved() && tTarget.isSame(this)) {
                    long tXDifference = getBlockPos().getX()-tTarget.getBlockPos().getX(), tZDifference = getBlockPos().getZ()-tTarget.getBlockPos().getZ();
                    long tTempDist = tXDifference * tXDifference + tZDifference * tZDifference;
                    if (tTempDist < tShortestDistance) {
                        tShortestDistance = tTempDist;
                        otherSide = tTarget;
                    } else if (tTempDist == tShortestDistance && (otherSide == null || Math.abs(tTarget.getBlockPos().getY()-getBlockPos().getY()) < Math.abs(otherSide.getBlockPos().getY()-getBlockPos().getY()))) {
                        otherSide = tTarget;
                    }
                }
            } else if (level.dimension() == Level.END) {
                long tShortestDistance = 512*512;
                for (BlockEntityMiniPortal tTarget : sListWorldSide) if (tTarget != this && !tTarget.isRemoved() && tTarget.isSame(this)) {
                    long tXDifference = tTarget.getBlockPos().getX()-getBlockPos().getX(), tZDifference = tTarget.getBlockPos().getZ()-getBlockPos().getZ();
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
}
