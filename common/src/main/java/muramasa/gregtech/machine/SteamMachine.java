package muramasa.gregtech.machine;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.Data;
import muramasa.antimatter.gui.BarDir;
import muramasa.antimatter.gui.screen.AntimatterContainerScreen;
import muramasa.antimatter.gui.widget.*;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.gregtech.tile.single.TileEntitySteamMachine;

import static muramasa.antimatter.machine.MachineFlag.*;
import static muramasa.antimatter.machine.Tier.*;

public class SteamMachine extends Machine<SteamMachine> {

    public SteamMachine(String domain, String id) {
        super(domain, id);
        setTile(TileEntitySteamMachine::new);
        addFlags(BASIC, STEAM, COVERABLE);
        setGUI(Data.BASIC_MENU_HANDLER);
        setClientTick();
        setGuiTiers(ImmutableMap.<Tier, Tier>builder().put(BRONZE, BRONZE).put(STEEL, STEEL));
    }

    protected void setupGui() {
        super.setupGui();
        addGuiCallback(t -> {
            t.addWidget(WidgetSupplier.build((a, b) -> TextWidget.build(((AntimatterContainerScreen<?>) b).getTitle().getString(), 4210752).build(a, b)).setPos(9, 5).clientSide());
            if (has(RECIPE)) {
                t.addWidget(ProgressWidget.build(BarDir.LEFT, true))
                        .addWidget(MachineStateWidget.build().setPos(84, 46).setWH(8, 8));
            }
           // if ((has(ITEM) || has(FLUID)))
           //     t.addWidget(IOWidget.build(9, 63, 16, 16).onlyIf(u -> u.handler.getClass() == TileEntityMachine.class));
        });
    }
}