package muramasa.gregtech.tile.single;

import muramasa.antimatter.capability.item.TrackedItemHandler;
import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.gui.*;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.event.ContentEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.data.Machines;
import muramasa.gregtech.gui.ButtonOverlays;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
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

import static muramasa.antimatter.machine.MachineFlag.ENERGY;

public class TileEntityBuffer extends TileEntityMachine<TileEntityBuffer> {
    protected int stackLimit = 1;
    boolean emitEnergy = false;

    public TileEntityBuffer(Machine<?> type, BlockPos pos, BlockState state) {
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
                player.sendMessage(new TextComponent("Item Output Limit: " +stackLimit), player.getUUID());
                stack.hurt(1, world.random, (ServerPlayer) player);
                return InteractionResult.SUCCESS;
            }
        }
        return super.onInteractServer(state, world, pos, player, hand, hit, type);
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
            booleans[0] = this.itemHandler.map(h -> Utils.transferItems(h.getHandler(SlotType.STORAGE), adjHandler,true)).orElse(false);
        });
        return booleans[0];
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("stackLimit", stackLimit);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        stackLimit = tag.getInt("stackLimit");
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON) {
            int[] data = ((GuiEvents.GuiEvent)event).data;
            switch (data[0]) {
                case 0 -> {
                    emitEnergy = !emitEnergy;
                    playerEntity.sendMessage(new TextComponent((emitEnergy ? "Emit energy to output side" : "Don't emit energy")), playerEntity.getUUID());
                    AntimatterPlatformUtils.markAndNotifyBlock(level, this.getBlockPos(), this.level.getChunkAt(this.getBlockPos()), this.getBlockState(), this.getBlockState(), 1, 512);
                }
                case 1 -> {
                    /*outputRedstone = !outputRedstone;
                    playerEntity.sendMessage(new TextComponent((outputRedstone ? "Emit redstone if slots contain something" : "Don't emit redstone")), playerEntity.getUUID());
                    AntimatterPlatformUtils.markAndNotifyBlock(level, this.getBlockPos(), this.level.getChunkAt(this.getBlockPos()), this.getBlockState(), this.getBlockState(), 1, 512);*/
                }
            }
        }
    }

    @Override
    public void addWidgets(GuiInstance instance, IGuiElement parent) {
        super.addWidgets(instance, parent);
        instance.addSwitchButton(8, 63, 16, 16, ButtonOverlays.ENERGY_OFF, ButtonOverlays.ENERGY_ON, h -> ((TileEntityBuffer)h).emitEnergy);
    }

    public static class BufferItemHandler extends MachineItemHandler<TileEntityBuffer> {

        public BufferItemHandler(TileEntityBuffer tile) {
            super(tile);
            int count = tile.getMachineType() == Machines.SUPER_BUFFER ? 256 : tile.getMachineType().getCount(tile.getMachineTier(), SlotType.STORAGE);
            this.inventories.put(SlotType.STORAGE, new TrackedItemHandler<>(tile, count, true, true, (t, s) -> true, ContentEvent.ITEM_INPUT_CHANGED){
                @NotNull
                @Override
                public ItemStack extractItem(int slot, int amount, boolean simulate) {
                    if (amount < tile.stackLimit) return ItemStack.EMPTY;
                    amount = Math.min(amount, tile.stackLimit);
                    return super.extractItem(slot, amount, simulate);
                }
            });
        }
    }
}
