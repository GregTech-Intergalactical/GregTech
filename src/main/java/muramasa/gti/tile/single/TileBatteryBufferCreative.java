package muramasa.gti.tile.single;

import muramasa.antimatter.Data;
import muramasa.antimatter.capability.impl.MachineConfigHandler;
import muramasa.antimatter.capability.impl.MachineEnergyHandler;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.tool.AntimatterToolType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import java.util.Optional;

public class TileBatteryBufferCreative extends TileEntityMachine {
    private int amperage = 1;
    @Override
    public void onLoad() {
        super.onLoad();
        configHandler = Optional.of(new BufferConfigHandler(this));
        energyHandler = Optional.of(new BufferEnergyHandler(this));
    }
    static class BufferConfigHandler extends MachineConfigHandler {
        public BufferConfigHandler(TileEntityMachine tile) {
            super(tile);
        }
        @Override
        public boolean onInteract(PlayerEntity player, Hand hand, Direction side, AntimatterToolType type) {
            if (type == Data.SCREWDRIVER) {

            }
            if (++((TileBatteryBufferCreative)getTile()).amperage > 16)
                ((TileBatteryBufferCreative)getTile()).amperage = 1;

            player.sendMessage(new StringTextComponent("Amperage: " + ((TileBatteryBufferCreative)getTile()).amperage));

            return super.onInteract(player, hand, side, type);
        }
    }
    static class BufferEnergyHandler extends  MachineEnergyHandler {
        TileBatteryBufferCreative tile;
        public BufferEnergyHandler(TileBatteryBufferCreative tile) {
            super(tile);
            this.tile = tile;
            this.energy = Integer.MAX_VALUE;
        }
    }
}
