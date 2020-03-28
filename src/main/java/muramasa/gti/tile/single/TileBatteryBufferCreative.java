package muramasa.gti.tile.single;

import muramasa.antimatter.capability.impl.MachineConfigHandler;
import muramasa.antimatter.capability.impl.MachineEnergyHandler;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.tool.AntimatterToolType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;

import java.util.Optional;
import java.util.Random;

public class TileBatteryBufferCreative extends TileEntityMachine {
    private boolean full;

    @Override
    public void onLoad() {
        super.onLoad();
        full = new Random().nextInt(2) == 0;
        configHandler = Optional.of(new BufferConfigHandler(this));
        energyHandler = Optional.of(new BufferEnergyHandler(this));
    }

    static class BufferConfigHandler extends MachineConfigHandler {
        TileBatteryBufferCreative tile;
        public BufferConfigHandler(TileBatteryBufferCreative tile) {
            super(tile);
            this.tile = tile;
        }

        @Override
        public boolean onInteract(PlayerEntity player, Hand hand, Direction side, AntimatterToolType type) {
            if (!tile.getWorld().isRemote && hand == Hand.MAIN_HAND) {
                tile.energyHandler.ifPresent(h -> {
                    tile.getInfo().forEach(string -> player.sendMessage(new StringTextComponent(string)));
                });
            }
            return false;
        }
    }

    @Override
    public void onServerUpdate() {
        energyHandler.ifPresent(h -> h.update());
    }

    static class BufferEnergyHandler extends MachineEnergyHandler {
        TileBatteryBufferCreative tile;
        public BufferEnergyHandler(TileBatteryBufferCreative tile) {
            super(tile);
            this.tile = tile;
            if (tile.full) {
                this.energy = this.capacity;
                this.input = 0;
                this.output = 32;
            } else {
                this.energy = 0;
                this.input = 32;
                this.output = 0;
            }
        }
    }
}