package muramasa.gti.machine;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.gti.data.Guis;

import static muramasa.antimatter.machine.MachineFlag.*;

public class SteamMachine extends Machine<SteamMachine> {

    public SteamMachine(String domain, String id, Object... data) {
        super(domain, id, data);
        setTile(() -> new TileEntityMachine<>(this));
        addFlags(BASIC, STEAM, COVERABLE, CONFIGURABLE);
        setGUI(Guis.STEAM_MENU_HANDLER);
    }
}