package muramasa.gregtech.cover;

import com.google.common.collect.ImmutableMap;
import earth.terrarium.botarium.common.fluid.base.FluidContainer;
import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.capability.IFilterableHandler;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.cover.base.CoverBasicTransport;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;
import tesseract.TesseractCapUtils;

import java.util.Map;
import java.util.Objects;

public class CoverPump extends CoverBasicTransport implements IFilterableHandler {

    public static String ID = "pump";
    private final CoverFluidFilter filter;

    public static final Map<Tier, Integer> speeds = ImmutableMap.<Tier, Integer>builder().
            put(Tier.LV, 640 / 20)
            .put(Tier.MV, 2560 / 20)
            .put(Tier.HV, 10240 / 20)
            .put(Tier.EV, 40960 / 20)
            .put(Tier.IV, 163840 / 20).build();

    public CoverPump(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        Objects.requireNonNull(tier);
        this.filter = new CoverFluidFilter(source, null, side, GregTechData.COVER_FLUID_FILTER);
        filter.onCreate();
        this.gui.getSlots().add(SlotType.STORAGE, 79, 53);
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicDepthModel();
    }

    @Override
    public boolean onTransfer(Object object, boolean inputSide, boolean simulate) {
        if (object instanceof FluidHolder stack){
            if (getInventory(SlotType.STORAGE).getItem(0).isEmpty()) return false;
            return filter.onTransfer(stack, inputSide, simulate);
        }
        return super.onTransfer(object, inputSide, simulate);
    }

    @Override
    public <T> boolean blocksCapability(Class<T> cap, Direction side) {
        return cap != FluidContainer.class;
    }

    @Override
    public void onUpdate() {
        //Pump acts on each tick.
        if (handler.getTile().getLevel().isClientSide) return;
        if (handler.getTile() == null) return;
        BlockEntity adjTile = handler.getTile().getLevel().getBlockEntity(handler.getTile().getBlockPos().relative(side));
        if (adjTile == null) return;
        BlockPos from = handler.getTile().getBlockPos();
        BlockPos to = handler.getTile().getBlockPos().relative(side);
        Direction fromSide = side;
        if (exportMode == ImportExportMode.IMPORT || exportMode == ImportExportMode.IMPORT_EXPORT){
            from = handler.getTile().getBlockPos().relative(side);
            to = handler.getTile().getBlockPos();
            fromSide = side.getOpposite();
        }
        BlockPos finalTo = to;
        if (canMove(side)) {
            Direction finalFromSide = fromSide;
            TesseractCapUtils.getFluidHandler(handler.getTile().getLevel(), from, fromSide).ifPresent(ih -> TesseractCapUtils.getFluidHandler(handler.getTile().getLevel(), finalTo, finalFromSide.getOpposite()).ifPresent(other -> Utils.transferFluids(ih, other, speeds.get(tier))));
        }
    }
    protected boolean canMove(Direction side){
        if (redstoneMode != RedstoneMode.NO_WORK){
            boolean powered = isPowered(side);
            return (redstoneMode == RedstoneMode.INVERTED) != powered;
        }
        return true;
    }

    @Override
    public boolean test(SlotType<?> type, int slot, ItemStack stack) {
        return stack.getItem() == GregTechData.COVER_FLUID_FILTER.getItem().getItem();
    }

    @Override
    public void onMachineEvent(IGuiHandler tile, IMachineEvent event, int... data) {
        if (tile == this && event == SlotType.STORAGE){
            ItemStack slotStack = getInventory(SlotType.STORAGE).getItem(data[0]);
            if (slotStack.isEmpty()){
                filter.clearFilter();
            } else {
                filter.addInfoFromStack(slotStack);
            }
        }
        super.onMachineEvent(tile, event, data);
    }

    @Override
    public void addInfoFromStack(ItemStack stack) {
        super.addInfoFromStack(stack);
        onMachineEvent(this, SlotType.STORAGE, 0);
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        if (nbt.contains("filter")) {
            filter.deserialize(nbt.getCompound("filter"));
        }
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag nbt =  super.serialize();
        nbt.put("filter", filter.serialize());
        return nbt;
    }
}
