package muramasa.gregtech.blockentity.single;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.item.TrackedItemHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.data.Machines;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tesseract.api.item.PlatformItemHandler;

public class BlockEntityLimitedOutput<T extends BlockEntityLimitedOutput<T>> extends BlockEntityMachine<T> {
    protected int stackLimit = 0;
    boolean observeStackLimit = false;
    public BlockEntityLimitedOutput(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.itemHandler.set(() -> new LimitedOutputItemHandler<>((T)this));
    }

    public static boolean transferItems(PlatformItemHandler from, PlatformItemHandler to, boolean once) {
        boolean successful = false;
        for (int i = 0; i < from.getSlots(); i++) {
            ItemStack toInsert = from.extractItem(i, from.getStackInSlot(i).getCount(), true);
            if (toInsert.isEmpty()) {
                continue;
            }
            ItemStack inserted = Utils.insertItem(to, toInsert, true);
            if (inserted.isEmpty()){
                Utils.insertItem(to, toInsert, false);
                from.extractItem(i, toInsert.getCount(), false);
                if (!successful) successful = true;
                if (once) break;
            }
        }
        return successful;
    }

    @Override
    public InteractionResult onInteractServer(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, @Nullable AntimatterToolType type) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(AntimatterDefaultTools.SCREWDRIVER.getTag())){
            if (hit.getDirection() == getFacing().getOpposite()){
                if (!observeStackLimit){
                    if (stackLimit > 0 && stackLimit < 65){
                        observeStackLimit = true;
                    } else {
                        stackLimit = player.isCrouching() ? 65 : 0;
                    }
                }
                if (player.isCrouching()){
                    stackLimit--;
                } else {
                    stackLimit++;
                }
                if (stackLimit == 65 || stackLimit == 0){
                    observeStackLimit = false;
                    player.sendMessage(Utils.literal("Do not regulate Item Stack size"), player.getUUID());
                } else {
                    player.sendMessage(Utils.literal("Item Output Limit: " +stackLimit), player.getUUID());
                }
                stack.hurt(1, world.random, (ServerPlayer) player);
                return InteractionResult.SUCCESS;
            }
        }
        return super.onInteractServer(state, world, pos, player, hand, hit, type);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("stackLimit", stackLimit);
        tag.putBoolean("observeStackLimit", observeStackLimit);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        stackLimit = tag.getInt("stackLimit");
        observeStackLimit = tag.getBoolean("observerStackLimit");
    }

    public static class LimitedOutputItemHandler<T extends BlockEntityLimitedOutput<T>> extends MachineItemHandler<T> {

        public LimitedOutputItemHandler(T tile) {
            super(tile);
            int count = tile.getMachineType() == Machines.SUPER_BUFFER ? 256 : tile.getMachineType().getCount(tile.getMachineTier(), SlotType.STORAGE);
            this.inventories.put(SlotType.STORAGE, new TrackedItemHandler<>(tile, SlotType.STORAGE, count, true, true, (t, s) -> true){
                @NotNull
                @Override
                public ItemStack extractItem(int slot, int amount, boolean simulate) {
                    if (!tile.observeStackLimit) return super.extractItem(slot, amount, simulate);
                    if (amount < tile.stackLimit) return ItemStack.EMPTY;
                    amount = tile.stackLimit;
                    return super.extractItem(slot, amount, simulate);
                }
            });
        }
    }
}
