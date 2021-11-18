package muramasa.gti.cover;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;

import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class CoverConveyor extends BaseCover {

    public static String ID = "conveyor";

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
    public <T> boolean blocksCapability(Capability<T> cap, Direction side) {
        return side == null && cap != CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    //Useful for using the same model for multiple tiers where id is dependent on tier.

    @Override
    public ResourceLocation getModel(String type, Direction dir, Direction facing) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicDepthModel();
    }

    @Override
    public void onUpdate() {
        if (handler.getTile().getLevel().isClientSide || handler.getTile().getLevel().getGameTime() % (speeds.get(tier)) != 0)
            return;
        BlockState state = handler.getTile().getLevel().getBlockState(handler.getTile().getBlockPos().relative(side));
        //Drop into world.
        if (state == Blocks.AIR.defaultBlockState()) {
            World world = handler.getTile().getLevel();
            BlockPos pos = handler.getTile().getBlockPos();
            ItemStack stack = handler.getTile().getCapability(ITEM_HANDLER_CAPABILITY, side).map(t -> Utils.extractAny(t)).orElse(ItemStack.EMPTY);
            if (stack.isEmpty()) return;
            world.addFreshEntity(new ItemEntity(world, pos.getX() + side.getStepX(), pos.getY() + side.getStepY(), pos.getZ() + side.getStepZ(), stack));
        }
        if (!(state.hasTileEntity())) return;
        TileEntity adjTile = handler.getTile().getLevel().getBlockEntity(handler.getTile().getBlockPos().relative(side));
        if (adjTile == null) {
            return;
        }
        LazyOptional<IItemHandler> handler = adjTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side.getOpposite());
        if (!handler.isPresent()) return;
        this.handler.getTile().getCapability(ITEM_HANDLER_CAPABILITY, side).ifPresent(ih -> handler.ifPresent(other -> Utils.transferItems(ih, other, true)));
    }
}
