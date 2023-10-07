package muramasa.gregtech.blockentity.single;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.item.TrackedItemHandler;
import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.data.Machines;
import muramasa.gregtech.gui.ButtonOverlays;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tesseract.TesseractCapUtils;
import tesseract.api.item.PlatformItemHandler;

import static muramasa.antimatter.machine.MachineFlag.ENERGY;

public class BlockEntityBuffer extends BlockEntityMachine<BlockEntityBuffer> {
    protected int stackLimit = 1;
    boolean emitEnergy = false;
    boolean outputRedstone = false;
    boolean invertRedstone = false;

    public BlockEntityBuffer(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        itemHandler.set(() -> new BufferItemHandler(this));
        if (type.has(ENERGY)) {
            energyHandler.set(() -> new MachineEnergyHandler<>(this, 0L, this.getMachineTier().getVoltage() * 66L, this.getMachineTier().getVoltage(), this.getMachineTier().getVoltage(), 1, 1){
                @Override
                public boolean canOutput(Direction direction) {
                    return super.canOutput(direction) && direction == tile.getFacing().getOpposite() && tile.emitEnergy;
                }
            });
        }
    }


    @Override
    public InteractionResult onInteractServer(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, @Nullable AntimatterToolType type) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(AntimatterDefaultTools.SCREWDRIVER.getTag())){
            if (hit.getDirection() == getFacing().getOpposite()){
                stackLimit++;
                if (stackLimit == 65) stackLimit = 1;
                player.sendMessage(Utils.literal("Item Output Limit: " +stackLimit), player.getUUID());
                stack.hurt(1, world.random, (ServerPlayer) player);
                return InteractionResult.SUCCESS;
            }
        }
        return super.onInteractServer(state, world, pos, player, hand, hit, type);
    }

    @Override
    public int getWeakRedstonePower(Direction facing) {
        if (outputRedstone){
            int[] redstone = new int[1];
            redstone[0] = this.itemHandler.map(i -> {
                for (int slot = 0; slot < i.getHandler(SlotType.STORAGE).getSlots(); slot++){
                    ItemStack stack = i.getHandler(SlotType.STORAGE).getStackInSlot(slot);
                    if (!stack.isEmpty()) return invertRedstone ? 0 : 15;
                }
                return invertRedstone ? 15 : 0;
            }).orElse(0);
            if (redstone[0] > 0){
                return redstone[0];
            }
        }
        return super.getWeakRedstonePower(facing);
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        if (getCover(this.getFacing().getOpposite()).isEmpty()){
            processItemOutput();
            /*if (this.energyHandler.map(e -> e.getEnergy() > 0).orElse(false)){
                if(processItemOutput()){
                    this.energyHandler.ifPresent(e -> e.extractEu(1, false));
                }
            }*/

        }
    }

    protected boolean processItemOutput() {
        Direction outputDir = this.getFacing().getOpposite();
        BlockEntity adjTile = Utils.getTile(this.getLevel(), this.getBlockPos().relative(outputDir));
        if (adjTile == null) return false;
        boolean[] booleans = new boolean[1];
        booleans[0] = false;
        TesseractCapUtils.getItemHandler(adjTile, outputDir.getOpposite()).ifPresent(adjHandler -> {
            booleans[0] = this.itemHandler.map(h -> transferItems(h.getHandler(SlotType.STORAGE), adjHandler,true)).orElse(false);
        });
        return booleans[0];
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
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("stackLimit", stackLimit);
        tag.putBoolean("outputRedstone", outputRedstone);
        tag.putBoolean("invertRedstone", invertRedstone);
        tag.putBoolean("emitEnergy", emitEnergy);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        stackLimit = tag.getInt("stackLimit");
        outputRedstone = tag.getBoolean("outputRedstone");
        invertRedstone = tag.getBoolean("invertRedstone");
        emitEnergy = tag.getBoolean("emitEnergy");
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON) {
            int[] data = ((GuiEvents.GuiEvent)event).data;
            switch (data[1]) {
                case 0 -> {
                    emitEnergy = !emitEnergy;
                    playerEntity.sendMessage(Utils.literal((emitEnergy ? "Emit energy to output side" : "Don't emit energy")), playerEntity.getUUID());
                    AntimatterPlatformUtils.markAndNotifyBlock(level, this.getBlockPos(), this.level.getChunkAt(this.getBlockPos()), this.getBlockState(), this.getBlockState(), 1, 512);
                }
                case 1 -> {
                    outputRedstone = !outputRedstone;
                    playerEntity.sendMessage(Utils.literal( (outputRedstone ? "Emit redstone if slots contain something" : "Don't emit redstone")), playerEntity.getUUID());
                    AntimatterPlatformUtils.markAndNotifyBlock(level, this.getBlockPos(), this.level.getChunkAt(this.getBlockPos()), this.getBlockState(), this.getBlockState(), 1, 512);
                }
                case 2 -> {
                    invertRedstone = !invertRedstone;
                    playerEntity.sendMessage(Utils.literal( (invertRedstone ? "I" : "Don't i") + "nvert redstone"), playerEntity.getUUID());
                    AntimatterPlatformUtils.markAndNotifyBlock(level, this.getBlockPos(), this.level.getChunkAt(this.getBlockPos()), this.getBlockState(), this.getBlockState(), 1, 512);
                }
            }
        }
    }

    @Override
    public void addWidgets(GuiInstance instance, IGuiElement parent) {
        super.addWidgets(instance, parent);
        instance.addSwitchButton(8, 63, 16, 16, ButtonOverlays.ENERGY_OFF, ButtonOverlays.ENERGY_ON, h -> ((BlockEntityBuffer)h).emitEnergy, true);
        instance.addSwitchButton(26, 63, 16, 16, ButtonOverlays.REDSTONE_CONTROL_OFF, ButtonOverlays.REDSTONE_CONTROL_ON, h -> ((BlockEntityBuffer)h).outputRedstone, true);
        instance.addSwitchButton(44, 63, 16, 16, ButtonOverlays.INVERT_REDSTONE_OFF, ButtonOverlays.INVERT_REDSTONE_ON, h -> ((BlockEntityBuffer)h).invertRedstone, true);
    }

    public static class BufferItemHandler extends MachineItemHandler<BlockEntityBuffer> {

        public BufferItemHandler(BlockEntityBuffer tile) {
            super(tile);
            int count = tile.getMachineType() == Machines.SUPER_BUFFER ? 256 : tile.getMachineType().getCount(tile.getMachineTier(), SlotType.STORAGE);
            this.inventories.put(SlotType.STORAGE, new TrackedItemHandler<>(tile, SlotType.STORAGE, count, true, true, (t, s) -> true){
                @NotNull
                @Override
                public ItemStack extractItem(int slot, int amount, boolean simulate) {
                    if (amount < tile.stackLimit) return ItemStack.EMPTY;
                    amount = tile.stackLimit;
                    return super.extractItem(slot, amount, simulate);
                }
            });
        }
    }
}
