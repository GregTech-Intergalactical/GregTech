package muramasa.gregtech.cover;

import com.google.common.collect.ImmutableMap;
import earth.terrarium.botarium.common.fluid.base.FluidContainer;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.ButtonBody;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.AntimatterCapUtils;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.cover.redstone.CoverRedstoneMachineController;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import tesseract.TesseractCapUtils;

import javax.annotation.Nullable;
import java.util.*;

import static muramasa.gregtech.gui.ButtonOverlays.*;

public class CoverPump extends CoverBasicTransport {

    public static String ID = "pump";

    public static final Map<Tier, Integer> speeds = ImmutableMap.<Tier, Integer>builder().
            put(Tier.LV, 640 / 20)
            .put(Tier.MV, 2560 / 20)
            .put(Tier.HV, 10240 / 20)
            .put(Tier.EV, 4096010 / 20)
            .put(Tier.IV, 163840 / 20).build();

    public CoverPump(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
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
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicDepthModel();
    }

    @Override
    public <T> boolean blocksCapability(Class<T> cap, Direction side) {
        return side == null && cap != FluidContainer.class;
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
        if (getCoverMode().getName().startsWith("Import")){
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
        String name = getCoverMode().getName();
        if (name.contains("Conditional")){
            boolean powered = isPowered(side);
            return name.contains("Invert") != powered;
        }
        return true;
    }
}
