package muramasa.gregtech.cover;

import muramasa.antimatter.blockentity.BlockEntityCache;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.ButtonOverlay;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.gui.widget.SyncableTextWidget;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.cover.base.CoverBasicRedstone;
import muramasa.gregtech.cover.base.CoverBasicTransport;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import tesseract.TesseractCapUtils;
import tesseract.api.item.PlatformItemHandler;

import java.util.function.Predicate;

import static muramasa.gregtech.cover.CoverConveyor.speeds;

public class CoverItemRegulator extends CoverBasicTransport {
    int slotLimit = 0;
    public CoverItemRegulator(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        addGuiCallback(t -> {
            t.addButton(52,53, ButtonOverlay.MINUS, true);
            t.addButton(106,53, ButtonOverlay.PLUS, true);
            t.addWidget(SyncableTextWidget.build(i -> {
                CoverItemRegulator itemRegulator = (CoverItemRegulator) i;
                if (itemRegulator.slotLimit == 0) return "N/A";
                return String.valueOf(itemRegulator.slotLimit);
            }, 4210752, true).setSize(61, 58, 36, 18));
        });
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicDepthModel();
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        super.onGuiEvent(event, playerEntity);
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){

            GuiEvents.GuiEvent ev = (GuiEvents.GuiEvent) event;
            int button = ev.data[1];
            if (button == 2){
                if (slotLimit > 0){
                    slotLimit--;
                    handler.getTile().setChanged();
                }
            }
            if (button == 3){
                if (slotLimit < 64){
                    slotLimit++;
                    handler.getTile().setChanged();
                }
            }
        }
    }

    @Override
    public boolean onTransfer(Object object, boolean inputSide, boolean simulate) {
        if (object instanceof ItemStack stack && !exportMode.isExport() && handler.getTile() instanceof BlockEntityMachine<?> machine && inputSide) {
            if (machine.itemHandler.isPresent()){
                if (stack.isEmpty()) return true;
                if (slotLimit > 0 && stack.getCount() < slotLimit) return true;
                ItemStack toInsert = slotLimit > 0 ? Utils.ca(slotLimit, stack) : stack.copy();
                MachineItemHandler<?> itemHandler = machine.itemHandler.get();
                if (itemHandler == null) return true;
                ItemStack inserted = itemHandler.forSide(side).map(i -> Utils.insertItem(i, toInsert, true)).orElse(toInsert);
                if (inserted.isEmpty()){
                    if (!simulate) {
                        itemHandler.forSide(side).ifPresent(i -> Utils.insertItem(i, toInsert, false));
                    }
                    stack.setCount(0);
                } else if (inserted.getCount() < toInsert.getCount()) {
                    if (!simulate) {
                        itemHandler.forSide(side).ifPresent(i -> Utils.insertItem(i, toInsert, false));
                    }
                    stack.setCount(stack.getCount() - inserted.getCount());
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void onUpdate() {
        if (handler.getTile().getLevel().isClientSide || handler.getTile().getLevel().getGameTime() % (speeds.get(tier)) != 0)
            return;
        BlockState state = handler.getTile().getLevel().getBlockState(handler.getTile().getBlockPos().relative(side));
        //Drop into world.
        if (state == Blocks.AIR.defaultBlockState() && exportMode.isExport()) {
            Level world = handler.getTile().getLevel();
            BlockPos pos = handler.getTile().getBlockPos();
            ItemStack stack = TesseractCapUtils.INSTANCE.getItemHandler(handler.getTile(), side).map(this::extractAny).orElse(ItemStack.EMPTY);
            if (stack.isEmpty()) return;
            world.addFreshEntity(new ItemEntity(world, pos.getX() + side.getStepX(), pos.getY() + side.getStepY(), pos.getZ() + side.getStepZ(), stack));
        }
        if (!(state.hasBlockEntity())) return;
        BlockEntity adjTile = BlockEntityCache.getBlockEntity(handler.getTile().getLevel(), handler.getTile().getBlockPos().relative(side));
        if (adjTile == null) {
            return;
        }
        BlockEntity from = handler.getTile();
        BlockEntity to = adjTile;
        Direction fromSide = side;
        boolean isImporting = exportMode == ImportExportMode.IMPORT || exportMode == ImportExportMode.IMPORT_EXPORT;
        if (isImporting){
            from = adjTile;
            to = handler.getTile();
            fromSide = side.getOpposite();
        }
        BlockEntity finalTo = to;
        if (canMove(side)){
            Direction finalFromSide = fromSide;
            TesseractCapUtils.INSTANCE.getItemHandler(from, fromSide).ifPresent(ih -> TesseractCapUtils.INSTANCE.getItemHandler(finalTo, finalFromSide.getOpposite()).ifPresent(oh -> {
                Predicate<ItemStack> filter = s -> {
                    if (isImporting || slotLimit == 0) return true;
                    if (s.getCount() < slotLimit) return false;
                    s.setCount(slotLimit);
                    return true;
                };
                Utils.transferItems(ih, oh, true, filter);
            }));
        }
    }

    public ItemStack extractAny(PlatformItemHandler handler) {
        for (int i = 0; i < handler.getSlots(); i++) {
            ItemStack stack = handler.extractItem(i, slotLimit > 0 ? slotLimit : 64, true);
            if (!stack.isEmpty() && (slotLimit == 0 || stack.getCount() == slotLimit)) {
                handler.extractItem(i, slotLimit > 0 ? slotLimit : 64, false);
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    protected boolean canMove(Direction side){
        if (redstoneMode != RedstoneMode.NO_WORK){
            boolean powered = isPowered(side);
            return (redstoneMode == RedstoneMode.INVERTED) != powered;
        }
        return true;
    }
}
