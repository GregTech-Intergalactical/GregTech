package muramasa.gregtech.cover;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.base.PlatformFluidHandler;
import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.blockentity.pipe.BlockEntityFluidPipe;
import muramasa.gregtech.GTIRef;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;

import javax.annotation.Nullable;

import java.util.Optional;

public class CoverDrain extends BaseCover {
    public static String ID = "drain";

    public CoverDrain(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
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
        Level world = tile.getLevel();
        Optional<PlatformFluidHandler> cap = FluidHooks.safeGetBlockFluidManager(tile, side);
        if (tile instanceof BlockEntityFluidPipe pipe){
            cap = pipe.getPipeCapHolder().side(side);
        }
        BlockPos offset = tile.getBlockPos().relative(side);
        if (side == Direction.UP && world.isRainingAt(offset) && world.getGameTime() % 60 == 0){
            cap.ifPresent(f -> {
                for (int i = 0; i < f.getTankAmount(); i++) {
                    FluidHolder toInsert = FluidPlatformUtils.createFluidStack(Fluids.WATER, 4 * TesseractGraphWrappers.dropletMultiplier);
                    long filled = f.insertFluid(toInsert, false);
                    if (filled > 0) {
                        break;
                    }
                }
            });
        }
        if (world.getGameTime() % (20) != 0) {
            return;
        }
        BlockState blockState = world.getBlockState(offset);
        FluidState state = world.getFluidState(offset);
        if (state.getType() == Fluids.EMPTY || !state.getType().isSource(state)) return;
        Fluid fluid = state.getType();
        cap.ifPresent(f -> {
            for (int i = 0; i < f.getTankAmount(); i++) {
                FluidHolder toInsert = FluidPlatformUtils.createFluidStack(fluid, 1000 * TesseractGraphWrappers.dropletMultiplier);
                long filled = f.insertFluid(toInsert, true);
                if (filled == 1000 * TesseractGraphWrappers.dropletMultiplier) {
                    f.insertFluid(FluidPlatformUtils.createFluidStack(toInsert.getFluid(), filled), false);
                    Holder<Biome> biome = world.getBiome(offset);
                    if (fluid != Fluids.WATER || (!biome.is(BiomeTags.IS_DEEP_OCEAN) && !biome.is(BiomeTags.IS_OCEAN) && !biome.is(BiomeTags.IS_RIVER))){
                        BlockState newState = Blocks.AIR.defaultBlockState();
                        if (fluid == Fluids.WATER && blockState.getBlock() != Blocks.WATER && blockState.hasProperty(BlockStateProperties.WATERLOGGED) && blockState.getValue(BlockStateProperties.WATERLOGGED)){
                            newState = blockState.setValue(BlockStateProperties.WATERLOGGED, false);
                        }
                        world.setBlockAndUpdate(offset, newState);
                    }
                    break;
                }
            }
        });
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
