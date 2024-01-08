package muramasa.gregtech.block;

import muramasa.antimatter.Ref;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.MachineState;
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

import static muramasa.gregtech.data.Materials.Lead;

public class BlockNuclearReactorCore extends BlockMachine implements IColorHandler {
    public BlockNuclearReactorCore(Machine<?> type, Tier tier) {
        super(type, tier);
    }

    @Override
    public int getBlockColor(BlockState state, @Nullable BlockGetter world, @Nullable BlockPos pos, int i) {
        if (i > 1 && i < 9 && world != null && pos != null){
            BlockEntity entity = world.getBlockEntity(pos);
            int slot = i > 4 ? i - 5 : i - 1;
            if (entity instanceof BlockEntityNuclearReactorCore core){
                if (i < 5){
                    boolean on = core.getMachineState() == MachineState.ACTIVE && (core.mode & Ref.B[slot]) == 0;
                    return on ? -1 : Lead.getRGB();
                } else {
                    ItemStack rod = core.getRod(i - 1);
                    if (!rod.isEmpty() && rod.getItem() instanceof IItemReactorRod reactorRod){
                        return reactorRod.getItemColor(rod, state.getBlock(), 0);
                    }
                }
            }
        }
        return IColorHandler.super.getBlockColor(state, world, pos, i);
    }
}
