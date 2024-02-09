package muramasa.gregtech.blockentity.miniportals;

import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.gregtech.loader.WorldGenLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.data.AntimatterMaterialTypes.GEM;

public class BlockEntityMiniTwilightPortal extends BlockEntityMiniPortal{
    public static List<BlockEntityMiniPortal> sListTwilightSide = new ArrayList<>();
    public BlockEntityMiniTwilightPortal(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    protected boolean isPortalSetter(ItemStack stack){
        return stack.is(GEM.getMaterialTag(AntimatterMaterials.Diamond));
    }

    @Override
    public InteractionResult onInteractBoth(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, @Nullable AntimatterToolType type) {
        InteractionResult result = super.onInteractBoth(state, world, pos, player, hand, hit, type);
        if (result == InteractionResult.SUCCESS){
            LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
            bolt.setPos(this.getBlockPos().getX() + 0.5, this.getBlockPos().getY(), this.getBlockPos().getZ() + 0.5);
            bolt.setVisualOnly(false);
            level.addFreshEntity(bolt);
        }
        return result;
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
        return sListTwilightSide;
    }


    @Override
    public void addThisPortalToLists() {
        if (level.dimension() == Level.OVERWORLD) {
            if (!sListWorldSide.contains(this)) sListWorldSide.add(this);
        } else if (level.dimension() == WorldGenLoader.TWILIGHT_FOREST) {
            if (!sListTwilightSide.contains(this)) sListTwilightSide.add(this);
        }
    }

    @Override
    protected void findTargetPortal() {
        otherSide = null;
        if (level != null && isServerSide()) {
            if (level.dimension() == Level.OVERWORLD) {
                long tShortestDistance = 512*512;
                for (BlockEntityMiniPortal tTarget : sListTwilightSide) if (tTarget != this && !tTarget.isRemoved()) {
                    long tXDifference = getBlockPos().getX()-tTarget.getBlockPos().getX(), tZDifference = getBlockPos().getZ()-tTarget.getBlockPos().getZ();
                    long tTempDist = tXDifference * tXDifference + tZDifference * tZDifference;
                    if (tTempDist < tShortestDistance) {
                        tShortestDistance = tTempDist;
                        otherSide = tTarget;
                    } else if (tTempDist == tShortestDistance && (otherSide == null || Math.abs(tTarget.getBlockPos().getY()-getBlockPos().getY()) < Math.abs(otherSide.getBlockPos().getY()-getBlockPos().getY()))) {
                        otherSide = tTarget;
                    }
                }
            } else if (level.dimension() == WorldGenLoader.TWILIGHT_FOREST) {
                long tShortestDistance = 512*512;
                for (BlockEntityMiniPortal tTarget : sListWorldSide) if (tTarget != this && !tTarget.isRemoved()) {
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
