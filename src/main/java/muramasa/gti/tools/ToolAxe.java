package muramasa.gti.tools;

import muramasa.antimatter.Configs;
import muramasa.antimatter.items.MaterialTool;
import muramasa.antimatter.tools.AntimatterToolType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class ToolAxe extends MaterialTool {

    public ToolAxe(String namespace) {
        super(namespace, AntimatterToolType.AXE);
    }

    public ToolAxe(String namespace, AntimatterToolType type) {
        super(namespace, type);
    }

    @Override
    public Set<BlockPos> getAOEBlocks(ItemStack stack, World world, PlayerEntity player, BlockPos origin) {
        if (Configs.GAMEPLAY.AXE_TIMBER && player.isCrouching()) {
            Set<BlockPos> set = new HashSet<>();
            BlockPos tempPos;
            BlockState state;
            for (int y = origin.getY() + 1; y < origin.getY() + Configs.GAMEPLAY.AXE_TIMBER_MAX; y++) {
                tempPos = new BlockPos(origin.getX(), y, origin.getZ());
                state = world.getBlockState(tempPos);
                if (state.getBlock().isAir(state, world, tempPos)) {
                    break;
                } else if (state.getMaterial() == Material.WOOD) {
                    set.add(tempPos);
                }
            }
            return set;
//            return Utils.getCubicPosArea(new int3(0, Ref.MAX_AXE_TIMBER / 2, 0), null, origin, player, true);
        }
        return super.getAOEBlocks(stack, world, player, origin);
    }
}
