package muramasa.gregtech.blockentity.single;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.CoverHandler;
import muramasa.antimatter.capability.item.FakeTrackedItemHandler;
import muramasa.antimatter.capability.item.SidedCombinedInvWrapper;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import tesseract.api.item.ExtendedItemContainer;

import java.util.Optional;

public class BlockEntityTrashCan extends BlockEntityMachine<BlockEntityTrashCan> {
    public BlockEntityTrashCan(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.itemHandler.set(() -> new MachineItemHandler<>(this){
            @Override
            public Optional<ExtendedItemContainer> forSide(Direction side) {
                return Optional.of(new TrashCanCombinedHandler(side, tile.coverHandler.map(c -> c).orElse(null), this.inventories.values().stream().filter(t -> !(t instanceof FakeTrackedItemHandler)).toArray(ExtendedItemContainer[]::new)));
            }
        });
        this.fluidHandler.set(() -> new MachineFluidHandler<>(this){
            @Override
            public long insertFluid(FluidHolder fluid, boolean simulate) {
                return fluid.getFluidAmount();
            }
        });
    }

    @Override
    public void onContainerClose(ContainerMachine<BlockEntityTrashCan> c, Player player) {
        super.onContainerClose(c, player);
        if (this.openContainers.isEmpty()){
            this.itemHandler.ifPresent(i -> {
                i.getHandler(SlotType.STORAGE).clearContent();
            });
        }
    }

    public static class TrashCanCombinedHandler extends SidedCombinedInvWrapper {
        public TrashCanCombinedHandler(Direction side, CoverHandler<?> coverHandler, ExtendedItemContainer... itemHandler) {
            super(side, coverHandler, itemHandler);
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            return ItemStack.EMPTY;
        }

        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack getItem(int slot) {
            return ItemStack.EMPTY;
        }
    }
}
