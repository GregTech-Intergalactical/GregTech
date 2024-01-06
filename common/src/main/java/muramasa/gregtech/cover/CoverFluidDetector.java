package muramasa.gregtech.cover;

import earth.terrarium.botarium.common.fluid.base.FluidContainer;
import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.capability.IFilterableHandler;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.ButtonOverlay;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.gregtech.data.GregTechCovers;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

public class CoverFluidDetector extends BaseCover implements IFilterableHandler {
    boolean inverted = false;
    int outputRedstone = 0;

    private final CoverFluidFilter filter;
    public CoverFluidDetector(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        this.filter = new CoverFluidFilter(source, null, side, GregTechCovers.COVER_FLUID_FILTER);
        filter.onCreate();
        addGuiCallback(t -> {
            t.addSwitchButton(70, 34, 16, 16, ButtonOverlay.TORCH_OFF, ButtonOverlay.TORCH_ON, h -> inverted, true, b -> "tooltip.gti.redstone_mode." + (b ? "inverted" : "normal"));
        });
        this.gui.getSlots().add(SlotType.STORAGE, 88, 34);
    }

    @Override
    public boolean canPlace() {
        return handler.getTile() instanceof BlockEntityMachine<?> machine && machine.fluidHandler.side(side).isPresent();
    }

    @Override
    public String getId() {
        return "fluid_detector";
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }

    @Override
    public boolean hasGui() {
        return true;
    }

    @Override
    public void onUpdate() {
        if (handler.getTile().getLevel() == null || handler.getTile().getLevel().isClientSide) return;
        if (handler.getTile() instanceof BlockEntityMachine<?> machine && machine.fluidHandler.side(side).isPresent()){
            FluidContainer fluidContainer = machine.fluidHandler.side(side).get();
            int oldRedstone = outputRedstone;
            long scale = IntStream.range(0, fluidContainer.getSize()).mapToLong(tankSlot -> {
                FluidHolder fluidHolder = fluidContainer.getFluids().get(tankSlot);
                if (filter.onTransfer(fluidHolder, true, true)) return 0;
                return fluidContainer.getTankCapacity(tankSlot);
            }).sum() / 15L;
            long totalFluid = IntStream.range(0, fluidContainer.getSize()).mapToLong(tankSlot -> {
                FluidHolder fluidHolder = fluidContainer.getFluids().get(tankSlot);
                if (filter.onTransfer(fluidHolder, true, true)) return 0;
                return fluidHolder.getFluidAmount();
            }).sum();
            if (scale > 0){
                outputRedstone = inverted ? (int) (15L - totalFluid / scale) : (int) (totalFluid / scale);
            } else {
                outputRedstone = inverted ? 15 : 0;
            }
            if (outputRedstone != oldRedstone){
                markAndNotifySource();
            }
        }
    }

    @Override
    public int getWeakPower() {
        return outputRedstone;
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag nbt =  super.serialize();
        nbt.put("filter", filter.serialize());
        nbt.putBoolean("inverted", inverted);
        return nbt;
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){
            GuiEvents.GuiEvent ev = (GuiEvents.GuiEvent) event;
            if (ev.data[1] == 0){
                inverted = !inverted;
                this.handler.getTile().setChanged();
            }
        }
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        if (nbt.contains("filter")) {
            filter.deserialize(nbt.getCompound("filter"));
        }
        inverted = nbt.getBoolean("inverted");
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
    public boolean test(SlotType<?> slotType, int slot, ItemStack stack) {
        return stack.getItem() == GregTechCovers.COVER_FLUID_FILTER.getItem().getItem();
    }
}
