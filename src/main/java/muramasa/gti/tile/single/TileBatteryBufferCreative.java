package muramasa.gti.tile.single;

import muramasa.antimatter.capability.impl.MachineConfigHandler;
import muramasa.antimatter.capability.impl.MachineEnergyHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.tool.AntimatterToolType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import tesseract.util.Dir;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.Random;

public class TileBatteryBufferCreative extends TileEntityMachine {

    public boolean storage = new Random().nextBoolean();

    public TileBatteryBufferCreative(Machine<?> type) {
        super(type);
    }

    @Override
    public void onInit() {
        configHandler = Optional.of(new BufferConfigHandler(this));
        energyHandler = Optional.of(new BufferEnergyHandler(this));
        super.onInit();
    }

    static class BufferConfigHandler extends MachineConfigHandler {

        public BufferConfigHandler(TileBatteryBufferCreative tile) {
            super(tile);
        }

        @Override
        public boolean onInteract(PlayerEntity player, Hand hand, Direction side, AntimatterToolType type) {
            World world = tile.getWorld();
            if (world != null) {
                if (!world.isRemote && hand == Hand.MAIN_HAND) {
                    tile.energyHandler.ifPresent(h -> {
                        tile.getInfo().forEach(string -> player.sendMessage(new StringTextComponent(string)));
                    });
                }
            }
            return false;
        }
    }

    @Override
    public void onServerUpdate() {
        energyHandler.ifPresent(MachineEnergyHandler::onUpdate);
    }

    static class BufferEnergyHandler extends MachineEnergyHandler {

        public BufferEnergyHandler(TileBatteryBufferCreative tile) {
            super(tile, tile.storage ? Integer.MAX_VALUE : 0, Integer.MAX_VALUE, tile.getMachineTier().getVoltage(), tile.getMachineTier().getVoltage(), tile.storage ? 0 : 4, tile.storage ? 16 : 0);
        }

        @Override
        public long extract(long toInsert, boolean simulate) {
            return 0L;
        }

        @Override
        public boolean canOutput(@Nonnull Dir direction) {
            return direction == Dir.UP;//tile.getOutputFacing().getIndex() == direction.getIndex();
        }
    }
}