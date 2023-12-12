package muramasa.gregtech.blockentity.single;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

public class BlockEntityCropHarvester extends BlockEntityMachine<BlockEntityCropHarvester> {
    AABB boundingBox;
    public BlockEntityCropHarvester(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        boundingBox = new AABB(pos.offset(4,-1,4), pos.offset(-4,-1,-4));
    }

    @Override
    protected boolean allowExplosionsInRain() {
        return false;
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        if (isServerSide() && level.getGameTime() % 100 == 0) {
            BlockPos.betweenClosedStream(boundingBox).forEach(p -> {
                BlockState blockState = level.getBlockState(p);
                if (blockState.getBlock() instanceof CropBlock cropBlock){
                    int age = blockState.getValue(cropBlock.getAgeProperty());
                    if (age == cropBlock.getMaxAge() && energyHandler.map(e -> e.getEnergy() >= 16).orElse(false)){
                        List<ItemStack> drops = CropBlock.getDrops(blockState, (ServerLevel) level, p, null);
                        ItemStack replant = blockState.getBlock().getCloneItemStack(level, p, blockState);
                        for (ItemStack drop : drops) {
                            if (drop.getItem() == replant.getItem()){
                                drop.shrink(1);
                                break;
                            }
                        }
                        if (itemHandler.map(i -> i.canOutputsFit(drops.toArray(new ItemStack[0]))).orElse(false)){
                            itemHandler.ifPresent(i -> i.addOutputs(drops.toArray(new ItemStack[0])));
                            level.setBlock(p, blockState.setValue(cropBlock.getAgeProperty(), 0), 3);
                            energyHandler.ifPresent(e -> e.extractEu(16, false));
                        }
                    }
                }
            });
        }
    }
}
