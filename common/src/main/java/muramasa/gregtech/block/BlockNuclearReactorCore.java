package muramasa.gregtech.block;

import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.gregtech.blockentity.single.BlockEntityNuclearReactorCore;
import muramasa.gregtech.items.IItemReactorRod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

public class BlockNuclearReactorCore extends BlockMachine implements IColorHandler {
    public BlockNuclearReactorCore(Machine<?> type, Tier tier) {
        super(type, tier);
    }

    @Override
    public int getBlockColor(BlockState state, @Nullable BlockGetter world, @Nullable BlockPos pos, int i) {
        if (i > 0 && i < 5 && world != null && pos != null){
            BlockEntity entity = world.getBlockEntity(pos);
            if (entity instanceof BlockEntityNuclearReactorCore core){
                ItemStack rod = core.getRod(i - 1);
                if (!rod.isEmpty() && rod.getItem() instanceof IItemReactorRod reactorRod){
                    return reactorRod.getItemColor(rod, state.getBlock(), 0);
                }
            }
        }
        return IColorHandler.super.getBlockColor(state, world, pos, i);
    }
}
