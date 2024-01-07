package muramasa.gregtech.blockentity.single;

import muramasa.antimatter.Ref;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.gregtech.items.IItemReactorRod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

import static muramasa.antimatter.Ref.B;

public class BlockEntityNuclearReactorCore extends BlockEntityMachine<BlockEntityNuclearReactorCore> {
    public int[] mNeutronCounts = new int[]{0, 0, 0, 0};
    public int[] oNeutronCounts = new int[]{0, 0, 0, 0};
    public long mEnergy = 0, oEnergy = 0;
    public byte mMode = 0;
    public static final int[] S2103 = new int[] {0,0,2,1,0,3,0}, S0312 = new int[] {0,0,0,3,1,2,0};

    public BlockEntityNuclearReactorCore(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.fluidHandler.set(() -> new MachineFluidHandler<>(this, 64000, 1000, 1, 1));
    }

    private ItemStack getRod(int slot){
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

    public void updateReactorRodModeration(int aSlot) {/**/}

    public int getReactorRodNeutronEmission(int aSlot) {
        return 0;
    }

    public boolean getReactorRodNeutronReaction(int aSlot) {
        return false;
    }

    public int getReactorRodNeutronReflection(int aSlot, int aNeutrons, boolean aModerated) {
        return 0;
    }
}
