package muramasa.gregtech.blockentity.single;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static muramasa.antimatter.machine.Tier.LV;
import static muramasa.antimatter.machine.Tier.NONE;

public class BlockEntitySolarPanel extends BlockEntityMachine<BlockEntitySolarPanel> {
    public BlockEntitySolarPanel(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.energyHandler.set(() -> new MachineEnergyHandler<>(this, 0, (this.getMachineTier() == Tier.NONE ? 2 : this.getMachineTier().getVoltage()) * 40L, 0, this.getMachineTier() == LV ? 32 : 8, 0, 1){

            @Override
            public boolean canOutput(Direction direction) {
                return direction == Direction.DOWN;
            }
        });
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        this.energyHandler.ifPresent(e -> {
            long generation = this.getMachineTier() == NONE ? 1 : this.getMachineTier().getVoltage();
            if (skyBlockCheck(level, pos) && isSunVisible(level, pos.above()) && e.insertInternal(generation, true) == generation){
                e.insertInternal(generation, false);
                if (this.getMachineState() != MachineState.ACTIVE){
                    setMachineState(MachineState.ACTIVE);
                }
            } else {
                if (this.getMachineState() == MachineState.ACTIVE){
                    setMachineState(MachineState.IDLE);
                }
            }
        });
    }

    @Override
    protected boolean allowExplosionsInRain() {
        return false;
    }

    @Override
    public boolean toggleMachine() {
        return false;
    }

    public boolean skyBlockCheck(Level level, BlockPos pos){
        return level.canSeeSkyFromBelowWater(pos.above()) && level.dimensionType().hasSkyLight();
    }

    public boolean isSunVisible(@NotNull Level world, BlockPos pos) {
        if (!world.isDay()) return false;
        Holder<Biome> biome = world.getBiome(pos);
        return biome.value().getPrecipitation() == Biome.Precipitation.NONE || (!world.isRaining() && !world.isThundering());
    }
}
