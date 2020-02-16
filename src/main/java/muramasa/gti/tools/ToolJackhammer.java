package muramasa.gti.tools;

import com.google.common.collect.Sets;
import muramasa.antimatter.items.MaterialTool;
import muramasa.antimatter.tools.AntimatterToolType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Set;

public class ToolJackhammer extends MaterialTool {

    public ToolJackhammer(String namespace) {
        super(namespace, AntimatterToolType.JACKHAMMER);
    }

    public ToolJackhammer(String namespace, AntimatterToolType type) {
        super(namespace, type);
    }

    @Override
    public Set<BlockPos> getAOEBlocks(ItemStack stack, World world, PlayerEntity player, BlockPos origin) {
        if (!player.isCrouching()) return Sets.newHashSet();
        RayTraceResult result = rayTrace(player.world, player, RayTraceContext.FluidMode.NONE);

        //TODO use player looking dir?
        //if (result.sideHit == null) return Sets.newHashSet();
        //return Utils.getCubicPosArea(new int3(1, 1, 0), result.sideHit, origin, player, true);
        return Sets.newHashSet();
    }
}
