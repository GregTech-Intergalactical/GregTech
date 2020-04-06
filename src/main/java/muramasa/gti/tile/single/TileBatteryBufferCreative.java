package muramasa.gti.tile.single;

import muramasa.antimatter.capability.impl.MachineConfigHandler;
import muramasa.antimatter.capability.impl.MachineEnergyHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.tool.AntimatterToolType;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class TileBatteryBufferCreative extends TileEntityMachine {

    public TileBatteryBufferCreative(Machine<?> type) {
        super(type);
    }

    @Override
    public void initCaps() {
        configHandler = Optional.of(new BufferConfigHandler(this));
        energyHandler = Optional.of(new BufferEnergyHandler(this));
        super.initCaps();
    }

    static class BufferConfigHandler extends MachineConfigHandler {
        TileBatteryBufferCreative tile;
        public BufferConfigHandler(TileBatteryBufferCreative tile) {
            super(tile);
            this.tile = tile;
        }

        @Override
        public boolean onInteract(PlayerEntity player, Hand hand, Direction side, AntimatterToolType type) {
            if (!Objects.requireNonNull(tile.getWorld()).isRemote && hand == Hand.MAIN_HAND) {
                tile.energyHandler.ifPresent(h -> {
                    tile.getInfo().forEach(string -> player.sendMessage(new StringTextComponent(string)));
                });
            }
            return false;
        }
    }

    @Override
    public void onServerUpdate() {
        energyHandler.ifPresent(MachineEnergyHandler::update);
    }

    static class BufferEnergyHandler extends MachineEnergyHandler {
        TileBatteryBufferCreative tile;

        public BufferEnergyHandler(TileBatteryBufferCreative tile) {
            super(tile, true);
            this.tile = tile;
            boolean storage = new Random().nextInt(2) == 1;
            this.energy = storage ? Integer.MAX_VALUE : 0;
            this.capacity = Integer.MAX_VALUE;
            this.voltage_in = tile.getMachineTier().getVoltage();
            this.voltage_out = tile.getMachineTier().getVoltage();
            this.amperage_in = storage ? 0 : 4;
            this.amperage_out = storage ? 4 : 0;
        }

        @Override
        public void onOverVoltage(long node) {
            World world = tile.getWorld();
            if (world != null) {
                BlockPos pos = BlockPos.fromLong(node);
                if (!world.isRemote)
                    world.createExplosion(null, pos.getX(), pos.getY() + 0.0625D, pos.getZ(), 4.0F, Explosion.Mode.BREAK);
                else
                    world.addParticle(ParticleTypes.SMOKE, pos.getX(), pos.getY() + 0.5D, pos.getZ(), 0.0D, 0.0D, 0.0D);
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }

        @Override
        public void onOverAmperage(long cable) {
            World world = tile.getWorld();
            if (world != null) {
                BlockPos pos = BlockPos.fromLong(cable); boolean fired = false;
                for (Direction direction : Direction.values()) {
                    BlockPos offset = pos.offset(direction);
                    if (world.getBlockState(offset) == Blocks.AIR.getDefaultState()) {
                        world.setBlockState(offset, Blocks.FIRE.getDefaultState());
                        fired = true;
                    }
                }
                if (!fired) world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }

        /*@Override
        public long insert(long toInsert, boolean simulate) {
            return 0L;
        }*/

        @Override
        public long extract(long toInsert, boolean simulate) {
            return 0L;
        }
    }
}