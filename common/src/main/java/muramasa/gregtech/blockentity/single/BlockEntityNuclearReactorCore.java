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
    public BlockEntityNuclearReactorCore(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.fluidHandler.set(() -> new MachineFluidHandler<>(this, 64000, 1000, 1, 1));
    }

    private ItemStack getRod(int slot){
        return itemHandler.map(i -> i.getHandler(SlotType.STORAGE).getItem(slot)).orElse(ItemStack.EMPTY);
    }

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
