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
    private boolean storage;

    @Override
    public void onLoad() {
        super.onLoad();
        storage = new Random().nextInt(1) == 0;
        configHandler = Optional.of(new BufferConfigHandler(this));
        energyHandler = Optional.of(new BufferEnergyHandler(this));
    }

    static class BufferConfigHandler extends MachineConfigHandler {
        public BufferConfigHandler(TileEntityMachine tile) {
            super(tile);
        }
        @Override
        public boolean onInteract(PlayerEntity player, Hand hand, Direction side, AntimatterToolType type) {
            if (!getTile().getWorld().isRemote && hand == Hand.MAIN_HAND) {
                ((TileBatteryBufferCreative) getTile()).energyHandler.ifPresent(x -> {
                    player.sendMessage(new StringTextComponent("Energy: " + x.getPower()));
                });
            }

            return false;
        }
    }

    static class BufferEnergyHandler extends MachineEnergyHandler {
        TileBatteryBufferCreative tile;
        public BufferEnergyHandler(TileBatteryBufferCreative tile) {
            super(tile);
            this.tile = tile;
            this.energy = tile.storage ? Long.MAX_VALUE : 0L;
        }
    }
}