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
import muramasa.antimatter.util.Utils;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;


public class CoverConveyor extends BaseCover {

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
    }


    @Override
    public <T> boolean blocksCapability(Class<T> cap, Direction side) {
        return side == null && cap != ExtendedItemContainer.class;
    }

    @Override
    public List<Consumer<GuiInstance>> getCallbacks() {
        var list = super.getCallbacks();
        list.add(g -> {
            g.addWidget(new WidgetSupplier((a,b) -> new ButtonWidget(a,b,new ResourceLocation(getDomain(), "textures/gui/button/gui_buttons.png"), ButtonBody.GREY, ButtonOverlay.TORCH_ON, ButtonOverlay.TORCH_OFF, but -> but.gui.sendPacket(but.gui.handler.createGuiPacket(new CoverGuiEvent(CoverGuiEvent.ConveyorEvent.INPUT_OUTPUT)))) {
                @Override
                public void init() {
                    super.init();
                    gui.syncBoolean(() -> CoverConveyor.this.extracting, e -> CoverConveyor.this.extracting = e, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
                }
            }.setStateHandler(sh -> ((CoverConveyor) sh.gui.handler).extracting)).setSize(10, 15, 16, 16));
           g.addWidget(TextWidget.build("Extract:", 4210752).setPos(10+16+2,15));
        });
        return list;
    }

    //Useful for using the same model for multiple tiers where id is dependent on tier.

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicDepthModel();
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        this.extracting = nbt.getBoolean("e");
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag tag = super.serialize();
        tag.putBoolean("e", extracting);
        return tag;
    }

    @Override
    public void onUpdate() {
        if (handler.getTile().getLevel().isClientSide || handler.getTile().getLevel().getGameTime() % (speeds.get(tier)) != 0)
            return;
        BlockState state = handler.getTile().getLevel().getBlockState(handler.getTile().getBlockPos().relative(side));
        //Drop into world.
        if (state == Blocks.AIR.defaultBlockState() && extracting) {
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
        Optional<PlatformItemHandler> handler = TesseractCapUtils.getItemHandler(adjTile, side.getOpposite());
        if (handler.isEmpty()) return;
        TesseractCapUtils.getItemHandler(this.handler.getTile(), side).ifPresent(ih -> handler.ifPresent(other -> {
            if (extracting) {
                Utils.transferItems(ih, other, true);
            } else {
                Utils.transferItems(other, ih, true);
            }
        }));
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
