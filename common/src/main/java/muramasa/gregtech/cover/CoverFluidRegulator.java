package muramasa.gregtech.cover;

import com.google.common.collect.ImmutableMap;
import earth.terrarium.botarium.common.fluid.base.FluidContainer;
import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.capability.IFilterableHandler;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.ButtonOverlay;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.gui.widget.SyncableTextWidget;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.cover.base.CoverBasicTransport;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;
import tesseract.TesseractCapUtils;
import tesseract.TesseractGraphWrappers;

import java.util.Map;
import java.util.Objects;

public class CoverFluidRegulator extends CoverBasicTransport {

    public static String ID = "pump";


    public static final Map<Tier, Integer> speeds = ImmutableMap.<Tier, Integer>builder().
            put(Tier.LV, 640 / 20)
            .put(Tier.MV, 2560 / 20)
            .put(Tier.HV, 10240 / 20)
            .put(Tier.EV, 4096010 / 20)
            .put(Tier.IV, 163840 / 20).build();

    int fluidLimit;
    public CoverFluidRegulator(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        Objects.requireNonNull(tier);
        fluidLimit = speeds.get(tier);
        addGuiCallback(t -> {
            t.addButton(52,53, ButtonOverlay.MINUS, true);
            t.addButton(106,53, ButtonOverlay.PLUS, true);
            t.addWidget(SyncableTextWidget.build(i -> {
                CoverFluidRegulator itemRegulator = (CoverFluidRegulator) i;
                if (itemRegulator.fluidLimit == 0) return "N/A";
                return String.valueOf(itemRegulator.fluidLimit);
            }, 4210752, true).setSize(61, 58, 36, 18));
        });
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicDepthModel();
    }

    @Override
    public boolean onTransfer(Object object, boolean inputSide, boolean simulate) {
        if (object instanceof FluidHolder stack && !exportMode.isExport() && handler.getTile() instanceof BlockEntityMachine<?> machine && inputSide){
            if (machine.fluidHandler.isPresent()){
                MachineFluidHandler<?> fluidHandler = machine.fluidHandler.get();
                if (stack.isEmpty()) return true;
                if (fluidLimit > 0 && stack.getFluidAmount() < fluidLimit * TesseractGraphWrappers.dropletMultiplier) return true;
                FluidHolder toInsert = fluidLimit > 0 ? Utils.ca(fluidLimit * TesseractGraphWrappers.dropletMultiplier, stack) : stack.copyHolder();
                if (fluidHandler == null) return true;
                long inserted = fluidHandler.insertFluid(toInsert, true);

            }
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
            TesseractCapUtils.getFluidHandler(handler.getTile().getLevel(), from, fromSide).ifPresent(ih -> TesseractCapUtils.getFluidHandler(handler.getTile().getLevel(), finalTo, finalFromSide.getOpposite()).ifPresent(other -> Utils.transferFluids(ih, other, fluidLimit > 0 ? fluidLimit : speeds.get(tier))));
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
    public void addInfoFromStack(ItemStack stack) {
        super.addInfoFromStack(stack);
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        fluidLimit = nbt.getInt("fluidLimit");
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag nbt =  super.serialize();
        nbt.putInt("fluidLimit", fluidLimit);
        return nbt;
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        super.onGuiEvent(event, playerEntity);
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){

            GuiEvents.GuiEvent ev = (GuiEvents.GuiEvent) event;
            int button = ev.data[1];
            if (button == 2){
                if (fluidLimit > 0){
                    fluidLimit--;
                    handler.getTile().setChanged();
                }
            }
            if (button == 3){
                if (fluidLimit < speeds.get(tier)){
                    fluidLimit++;
                    handler.getTile().setChanged();
                }
            }
        }
    }
}
