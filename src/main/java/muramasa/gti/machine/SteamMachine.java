package muramasa.gti.machine;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.Data;
import muramasa.antimatter.gui.BarDir;
import muramasa.antimatter.gui.widget.IOWidget;
import muramasa.antimatter.gui.widget.MachineStateWidget;
import muramasa.antimatter.gui.widget.ProgressWidget;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.gti.data.Guis;
import muramasa.gti.tile.single.TileEntitySteamMachine;

import static muramasa.antimatter.machine.MachineFlag.*;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.antimatter.machine.Tier.IV;

public class SteamMachine extends Machine<SteamMachine> {

    public SteamMachine(String domain, String id) {
        super(domain, id);
        setTile(() -> new TileEntitySteamMachine<>(this));
        addFlags(BASIC, STEAM, COVERABLE);
        setGUI(Data.BASIC_MENU_HANDLER);
        setGuiTiers(ImmutableMap.<Tier, Tier>builder().put(BRONZE, BRONZE).put(STEEL, STEEL));
    }
}