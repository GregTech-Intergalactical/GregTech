package muramasa.gregtech.cover;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.base.PlatformFluidHandler;
import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import muramasa.antimatter.blockentity.pipe.BlockEntityFluidPipe;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;

import java.util.Optional;

public class CoverDrain extends BaseCover {
    public static String ID = "drain";

    FluidHolder contained = FluidHooks.emptyFluid();
    boolean receivedBlockUpdate = false;

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
        if (side == Direction.UP && world.isRainingAt(offset) && world.getGameTime() % 60 == 0 && contained.isEmpty()){
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
        if (!contained.isEmpty()){
            cap.ifPresent(f ->{
                long filled = f.insertFluid(contained.copyHolder(), true);
                if (filled > 0) {
                    f.insertFluid(Utils.ca(filled, contained), false);
                    contained.setAmount(contained.getFluidAmount() - filled);
                    if (contained.getFluidAmount() <= 0){
                        contained = FluidHooks.emptyFluid();
                    }
                }
            });
        }
        if (!(receivedBlockUpdate || world.getGameTime() % (20) == 5)) {
            return;
        }
        if (!contained.isEmpty()){
            if (!receivedBlockUpdate) receivedBlockUpdate = true;
            return;
        }
        BlockState blockState = world.getBlockState(offset);
        FluidState state = world.getFluidState(offset);
        if (state.getType() == Fluids.EMPTY || !state.getType().isSource(state)) return;
        Fluid fluid = state.getType();
        contained = FluidPlatformUtils.createFluidStack(fluid, 1000 * TesseractGraphWrappers.dropletMultiplier);
        Holder<Biome> biome = world.getBiome(offset);
        if (fluid != Fluids.WATER || (!biome.is(BiomeTags.IS_DEEP_OCEAN) && !biome.is(BiomeTags.IS_OCEAN) && !biome.is(BiomeTags.IS_RIVER))){
            BlockState newState = Blocks.AIR.defaultBlockState();
            if (fluid == Fluids.WATER && blockState.getBlock() != Blocks.WATER && blockState.hasProperty(BlockStateProperties.WATERLOGGED) && blockState.getValue(BlockStateProperties.WATERLOGGED)){
                newState = blockState.setValue(BlockStateProperties.WATERLOGGED, false);
            }
            world.setBlockAndUpdate(offset, newState);
        }
        if (receivedBlockUpdate) receivedBlockUpdate = false;
    }

    @Override
    public void onBlockUpdate() {
        super.onBlockUpdate();
        receivedBlockUpdate = true;
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

    @Override
    public ItemStack getDroppedStack() {
        ItemStack stack = super.getDroppedStack();
        if (!contained.isEmpty()){
            stack.getOrCreateTag().put("containedFluid", contained.serialize());
        }
        return stack;
    }

    @Override
    public void addInfoFromStack(ItemStack stack) {
        super.addInfoFromStack(stack);
        if (stack.getTag() != null && stack.getTag().contains("containedFluid")){
            contained = AntimatterPlatformUtils.fromTag(stack.getTag().getCompound("containedFluid"));
        }
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag tag = super.serialize();
        if (!contained.isEmpty()){
            tag.put("contained", contained.serialize());
        }
        return tag;
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        if (nbt.contains("contained")){
            contained = AntimatterPlatformUtils.fromTag(nbt.getCompound("contained"));
        }
    }
}
