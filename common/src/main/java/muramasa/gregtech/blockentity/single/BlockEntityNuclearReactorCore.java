package muramasa.gregtech.blockentity.single;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.IFilterableHandler;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.gregtech.items.IItemReactorRod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import static muramasa.antimatter.Ref.B;

public class BlockEntityNuclearReactorCore extends BlockEntityMachine<BlockEntityNuclearReactorCore> implements IFilterableHandler {
    public int[] mNeutronCounts = new int[]{0, 0, 0, 0};
    public int[] oNeutronCounts = new int[]{0, 0, 0, 0};
    public long mEnergy = 0, oEnergy = 0;
    public byte mMode = 0;
    public static final int[] S2103 = new int[] {0,0,2,1,0,3,0}, S0312 = new int[] {0,0,0,3,1,2,0};

    public BlockEntityNuclearReactorCore(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.fluidHandler.set(() -> new MachineFluidHandler<>(this, 64000, 1000, 1, 1));
    }

    public ItemStack getRod(int slot){
        return itemHandler.map(i -> i.getHandler(SlotType.STORAGE).getItem(slot)).orElse(ItemStack.EMPTY);
    }

    // 0 and 2 are at SIDE_Z_NEG(North)    1 3      -->X+
    // 1 and 3 are at SIDE_Z_POS(South)  2|0 2|0   |0 2
    // 0 and 1 are at SIDE_X_NEG(West)   3|1 3|1   v1 3
    // 2 and 3 are at SIDE_X_POS(East)     0 2     Z+
    public boolean isReactorRodModerated(int aSlot) {
        if (getRod(aSlot).getItem() instanceof IItemReactorRod reactorRod) {
            boolean isModerated = reactorRod.isModerated(this, aSlot, getRod(aSlot));
            if (getMachineState() == MachineState.DISABLED || (mMode & B[aSlot]) != 0) return false;
            return isModerated;
        }
        return false;
    }

    public void updateReactorRodModeration(int aSlot) {
        if (getRod(aSlot).getItem() instanceof IItemReactorRod reactorRod){
            reactorRod.updateModeration(this, aSlot, getRod(aSlot));
        }
    }

    public int getReactorRodNeutronEmission(int aSlot) {
        if (getMachineState() != MachineState.DISABLED && (mMode & B[aSlot]) == 0 && getRod(aSlot).getItem() instanceof IItemReactorRod reactorRod){
            return reactorRod.getReactorRodNeutronEmission(this, aSlot, getRod(aSlot));
        }
        mNeutronCounts[aSlot] = 0;
        return 0;
    }

    public boolean getReactorRodNeutronReaction(int aSlot) {
        if (getLevel().getGameTime() % 20 == 18) mNeutronCounts[aSlot] -= oNeutronCounts[aSlot];
        if (getMachineState() != MachineState.DISABLED && (mMode & B[aSlot]) == 0 && getRod(aSlot).getItem() instanceof IItemReactorRod reactorRod){
            return reactorRod.getReactorRodNeutronReaction(this, aSlot, getRod(aSlot));
        }
        return false;
    }

    public int getReactorRodNeutronReflection(int aSlot, int aNeutrons, boolean aModerated) {
        if (getMachineState() != MachineState.DISABLED && (mMode & B[aSlot]) == 0 && getRod(aSlot).getItem() instanceof IItemReactorRod reactorRod){
            return reactorRod.getReactorRodNeutronReflection(this, aSlot, getRod(aSlot), aNeutrons, aModerated);
        }
        return 0;
    }

    @Override
    public InteractionResult onInteractServer(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, @Nullable AntimatterToolType type) {
        if (hit.getDirection() == Direction.UP){
            ItemStack held = player.getItemInHand(hand);
            if (held.getItem() instanceof IItemReactorRod reactorRod && reactorRod.isReactorRod(held)) {
                Vec3 vec = hit.getLocation();
                double x = vec.x - pos.getX();
                double z = vec.z - pos.getZ();
                int tSlot = x < 0.5 ? z < 0.5 ? 0 : 1 : z < 0.5 ? 2 : 3;
                Antimatter.LOGGER.info(tSlot);
                return InteractionResult.SUCCESS;
            }
        }
        return super.onInteractServer(state, world, pos, player, hand, hit, type);
    }

    @Override
    public boolean test(SlotType<?> slotType, int slot, ItemStack stack) {
        return stack.getItem() instanceof IItemReactorRod reactorRod && reactorRod.isReactorRod(stack);
    }
}
