package muramasa.gregtech.cover;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.*;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.gui.widget.ButtonWidget;
import muramasa.antimatter.gui.widget.TextWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.AntimatterCapUtils;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.cover.redstone.CoverRedstoneMachineController;
import muramasa.gregtech.gui.widgets.ChangingButtonWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import tesseract.TesseractCapUtils;
import tesseract.api.item.ExtendedItemContainer;
import tesseract.api.item.PlatformItemHandler;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import static muramasa.gregtech.gui.ButtonOverlays.*;
import static muramasa.gregtech.gui.ButtonOverlays.EXPORT_IMPORT_INVERT_CONDITIONAL;


public class CoverConveyor extends CoverBasicTransport {

    static {
        CoverGuiEvent.init();
    }

    public static String ID = "conveyor";

    private boolean extracting = true;

    public static final Map<Tier, Integer> speeds = ImmutableMap.<Tier, Integer>builder().
            put(Tier.LV, 400)
            .put(Tier.MV, 100)
            .put(Tier.HV, 20)
            .put(Tier.EV, 10)
            .put(Tier.IV, 1).build();

    public CoverConveyor(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        Objects.requireNonNull(tier);
        ButtonBody[][] overlays = new ButtonBody[][]{{IMPORT, IMPORT_CONDITIONAL, IMPORT_INVERT_CONDITIONAL, EXPORT, EXPORT_CONDITIONAL, EXPORT_INVERT_CONDITIONAL}, {IMPORT_EXPORT, IMPORT_EXPORT_CONDITIONAL, IMPORT_EXPORT_INVERT_CONDITIONAL, EXPORT_IMPORT, EXPORT_IMPORT_CONDITIONAL, EXPORT_IMPORT_INVERT_CONDITIONAL}};
        addGuiCallback(t -> {
            for (int x = 0; x < 6; x++){
                for (int y = 0; y < 2; y++){
                    t.addButton(35 + (x * 18), 25 + (y * 18), 16, 16, overlays[y][x], "tooltip.gti." + overlays[y][x].getId());
                }
            }
        });
    }


    @Override
    public <T> boolean blocksCapability(Class<T> cap, Direction side) {
        return side == null && cap != ExtendedItemContainer.class;
    }

    @Override
    public <T> boolean blocksInput(Class<T> cap, @Nullable Direction side) {
        int mode = coverMode.ordinal();
        return mode == 0 || mode == 2 || mode == 4;
    }

    @Override
    public <T> boolean blocksOutput(Class<T> cap, @Nullable Direction side) {
        int mode = coverMode.ordinal();
        return mode == 1 || mode == 3 || mode == 5;
    }

    //Useful for using the same model for multiple tiers where id is dependent on tier.

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicDepthModel();
    }

    @Override
    public void onUpdate() {
        if (handler.getTile().getLevel().isClientSide || handler.getTile().getLevel().getGameTime() % (speeds.get(tier)) != 0)
            return;
        BlockState state = handler.getTile().getLevel().getBlockState(handler.getTile().getBlockPos().relative(side));
        //Drop into world.
        if (state == Blocks.AIR.defaultBlockState() && coverMode.name.contains("output")) {
            Level world = handler.getTile().getLevel();
            BlockPos pos = handler.getTile().getBlockPos();
            ItemStack stack = TesseractCapUtils.getItemHandler(handler.getTile(), side).map(Utils::extractAny).orElse(ItemStack.EMPTY);
            if (stack.isEmpty()) return;
            world.addFreshEntity(new ItemEntity(world, pos.getX() + side.getStepX(), pos.getY() + side.getStepY(), pos.getZ() + side.getStepZ(), stack));
        }
        if (!(state.hasBlockEntity())) return;
        BlockEntity adjTile = handler.getTile().getLevel().getBlockEntity(handler.getTile().getBlockPos().relative(side));
        if (adjTile == null) {
            return;
        }
        BlockEntity from = handler.getTile();
        BlockEntity to = adjTile;
        Direction fromSide = side;
        if (getCoverMode().getName().startsWith("Import")){
            from = adjTile;
            to = handler.getTile();
            fromSide = side.getOpposite();
        }
        BlockEntity finalTo = to;
        if (canMove(side)){
            Direction finalFromSide = fromSide;
            TesseractCapUtils.getItemHandler(from, fromSide).ifPresent(ih -> TesseractCapUtils.getItemHandler(finalTo, finalFromSide.getOpposite()).ifPresent(oh -> {
                Utils.transferItems(ih, oh, false);
            }));
        }
    }

    protected boolean canMove(Direction side){
        String name = getCoverMode().getName();
        if (name.contains("Conditional")){
            boolean powered = AntimatterCapUtils.getCoverHandler(handler.getTile(), side).map(h -> {
                List<CoverRedstoneMachineController> list = new ArrayList<>();
                for (Direction dir : Direction.values()){
                    if (h.get(dir) instanceof CoverRedstoneMachineController machineController){
                        list.add(machineController);
                    }
                }
                int i = 0;
                int j = 0;
                for (CoverRedstoneMachineController coverStack : list){
                    j++;
                    if (coverStack.isPowered()){
                        i++;
                    }
                }
                return i > 0 && i == j;
            }).orElse(false);
            return name.contains("Invert") != powered;
        }
        return true;
    }


    static class CoverGuiEvent implements IGuiEvent {

        public static void init() {

        }
        enum ConveyorEvent {
            INPUT_OUTPUT,
        }
        public final ConveyorEvent event;
        public CoverGuiEvent(IGuiEventFactory fac, FriendlyByteBuf buffer) {
            this.event = ConveyorEvent.values()[buffer.readVarInt()];
        }

        public CoverGuiEvent(ConveyorEvent ev) {
            this.event = ev;
        }

        @Override
        public boolean forward() {
            return false;
        }

        @Override
        public void write(FriendlyByteBuf buffer) {
            buffer.writeVarInt(event.ordinal());
        }

        @Override
        public void handle(Player player, GuiInstance instance) {
            CoverConveyor source = (CoverConveyor) instance.handler;
            switch (event) {
                case INPUT_OUTPUT -> source.extracting = !source.extracting;
            }
        }
        public static final IGuiEvent.IGuiEventFactory INSTANCE = AntimatterAPI.register(IGuiEventFactory.class, new IGuiEventFactory() {
            @Override
            public BiFunction<IGuiEventFactory, FriendlyByteBuf, IGuiEvent> factory() {
                return CoverGuiEvent::new;
            }

            @Override
            public String getId() {
                return "cover_gui_factory";
            }
        });

        @Override
        public IGuiEventFactory getFactory() {
            return INSTANCE;
        }
    }
}
