package muramasa.gregtech.cover;

import earth.terrarium.botarium.common.fluid.base.PlatformFluidHandler;
import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tile.pipe.TileEntityFluidPipe;
import muramasa.gregtech.GTIRef;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static muramasa.gregtech.data.Materials.Air;

public class CoverAirVent extends BaseCover {
    public static String ID = "air_vent";
    public CoverAirVent(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public String getDomain() {
        return GTIRef.ID;
    }

    @Override
    public void onUpdate() {
        BlockEntity tile = handler.getTile();
        if (tile == null) {
            return;
        }
        if (tile.getLevel().isClientSide) return;
        Level level = tile.getLevel();
        Optional<PlatformFluidHandler> cap = FluidHooks.safeGetBlockFluidManager(tile, side);
        if (tile instanceof TileEntityFluidPipe pipe){
            cap = pipe.getPipeCapHolder().side(side);
        }
        BlockPos offset = tile.getBlockPos().relative(side);
        BlockState state = level.getBlockState(offset);
        if (state.isAir() && cap.isPresent()){
            if (level.getGameTime() % 360 == (30 + (60L * side.get3DDataValue()))){
                if (level.dimension() == Level.OVERWORLD){
                    cap.get().insertFluid(Air.getGas(64000), false);
                }
            }
        }
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    protected String getRenderId() {
        return ID;
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }
}
