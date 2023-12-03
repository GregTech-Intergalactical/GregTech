package muramasa.gregtech.blockentity.single;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.worldgen.vein.VeinSavedData;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.worldgen.OilSpoutEntry;
import muramasa.gregtech.worldgen.OilSpoutSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import tesseract.FluidPlatformUtils;

import java.util.List;

public class BlockEntitySeismicProspector extends BlockEntityMachine<BlockEntitySeismicProspector> {
    int progress, maxProgress;
    OilSpoutEntry entry = null;
    List<Material> ores;
    public BlockEntitySeismicProspector(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public InteractionResult onInteractServer(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, @Nullable AntimatterToolType type) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.getItem() == Items.TNT && stack.getCount() >= 4 && entry == null){
            maxProgress = 120;
            if (!player.isCreative()) stack.shrink(4);
            return InteractionResult.SUCCESS;
        }
        if (stack.getItem() == GregTechData.DataStick && entry != null){
            CompoundTag prospectData = stack.getOrCreateTagElement("prospectData");
            prospectData.putLong("pos", pos.asLong());
            prospectData.putString("dimension", level.dimension().location().toString());
            if (entry.getFluid() != null){
                CompoundTag fluid = new CompoundTag();
                fluid.putString("name", FluidPlatformUtils.getFluidId(entry.getFluid().fluid()).toString());
                fluid.putLong("maxYield", entry.getFluid().getMaxYield());
                prospectData.put("fluid", fluid);
            } else {
                prospectData.putBoolean("no_fluid", true);
            }
            if (!ores.isEmpty()){
                ListTag ores = new ListTag();
                this.ores.forEach(m -> {
                    ores.add(StringTag.valueOf(m.getId()));
                });
                prospectData.put("ores", ores);
            }
            return InteractionResult.SUCCESS;
        }
        return super.onInteractServer(state, world, pos, player, hand, hit, type);
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        if (maxProgress > 0){
            if (this.getMachineState() != MachineState.ACTIVE) setMachineState(MachineState.ACTIVE);
            if (progress < maxProgress){
                progress++;
            } else {
                progress = maxProgress = 0;
                setMachineState(MachineState.IDLE);
                ChunkPos pos1 = level.getChunk(pos).getPos();
                this.entry = OilSpoutSavedData.getOrCreate((ServerLevel) level).getFluidVeinWorldEntry(pos1.x, pos1.z);
                this.ores = VeinSavedData.getOrCreate((ServerLevel) level).geOresInChunk(pos1.x, pos1.z);
            }
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (entry != null){
            tag.putInt("progress", 120);
            tag.putInt("maxProgress", 120);
        } else {
            tag.putInt("progress", progress);
            tag.putInt("maxProgress", maxProgress);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.progress = tag.getInt("progress");
        this.maxProgress = tag.getInt("maxProgress");
    }
}
