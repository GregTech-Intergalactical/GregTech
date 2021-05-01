package muramasa.gti.cover;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.cover.CoverStack;
import muramasa.antimatter.cover.CoverTiered;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.Utils;
import muramasa.gti.Ref;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class CoverConveyor extends CoverTiered {

    public static String ID = "conveyor";

    static final Map<Tier, Integer> speeds = ImmutableMap.<Tier,Integer>builder().
            put(Tier.LV,400)
            .put(Tier.MV, 100)
            .put(Tier.HV, 20)
            .put(Tier.EV, 10)
            .put(Tier.IV, 1).build();

    public CoverConveyor(Tier tier) {
        super(tier);
    }

    public CoverConveyor() {
        super();
    }

    @Override
    public boolean hasGui() {
        return true;
    }

    //Useful for using the same model for multiple tiers where id is dependent on tier.
    @Override
    protected String getRenderId() {
        return ID();
    }

    @Override
    public ResourceLocation getModel(Direction dir, Direction facing) {
        return getBasicDepthModel();
    }

    @Override
    protected String ID() {
        return ID;
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }

    @Override
    public void onUpdate(CoverStack<?> instance, Direction side) {
        if (instance.getTile() == null || instance.getTile().getWorld().getGameTime() % (speeds.get(tier)) != 0)
            return;
        BlockState state = instance.getTile().getWorld().getBlockState(instance.getTile().getPos().offset(side));
        //Drop into world.
        if (state == Blocks.AIR.getDefaultState()) {
            World world = instance.getTile().getWorld();
            BlockPos pos = instance.getTile().getPos();
            ItemStack stack = instance.getTile().getCapability(ITEM_HANDLER_CAPABILITY, side).map(t -> Utils.extractAny(t)).orElse(ItemStack.EMPTY);
            if (stack.isEmpty()) return;
            world.addEntity(new ItemEntity(world,pos.getX()+side.getXOffset(), pos.getY()+side.getYOffset(), pos.getZ()+side.getZOffset(),stack));
        }
        if (!(state.hasTileEntity())) return;
        TileEntity adjTile = instance.getTile().getWorld().getTileEntity(instance.getTile().getPos().offset(side));
        if (adjTile == null) {
            return;
        }
        instance.getTile().getCapability(ITEM_HANDLER_CAPABILITY, side).ifPresent(ih -> adjTile.getCapability(ITEM_HANDLER_CAPABILITY, side.getOpposite()).ifPresent(other -> Utils.transferItems(other, ih,true)));
    }

    @Override
    protected CoverTiered getTiered(Tier tier) {
        return new CoverConveyor(tier);
    }


}
