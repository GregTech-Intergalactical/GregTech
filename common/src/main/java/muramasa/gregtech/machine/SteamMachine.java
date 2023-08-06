package muramasa.gregtech.machine;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.Data;
import muramasa.antimatter.gui.BarDir;
import muramasa.antimatter.gui.SlotData;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.screen.AntimatterContainerScreen;
import muramasa.antimatter.gui.slot.ISlotProvider;
import muramasa.antimatter.gui.widget.*;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.tile.single.TileEntitySteamMachine;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

import static muramasa.antimatter.machine.MachineFlag.*;
import static muramasa.antimatter.machine.Tier.*;

public class SteamMachine extends Machine<SteamMachine> {

    public SteamMachine(String domain, String id) {
        super(domain, id);
        setTile(TileEntitySteamMachine::new);
        addFlags(BASIC, STEAM, COVERABLE);
        setGUI(Data.BASIC_MENU_HANDLER);
        setClientTick();
        getGui().getMachineData().setMachineStateLocation(BRONZE, "bronze_machine_state").setMachineStateLocation(STEEL, "steel_machine_state");
        String suffix = id.contains("furnace") || id.contains("smelter") || id.contains("boiler") ? "default" : id.replace("steam_", "");
        getGui().getMachineData().setProgressLocation(BRONZE, "bronze_" + suffix).setProgressLocation(STEEL, "steel_" + suffix);
        String bSuffix = id.contains("macerator") || id.contains("forge_hammer") ? id.replace("steam", "") : "";
        if (id.contains("boiler")){
            getGui().setBackgroundTexture(BRONZE, id + "_bronze").setBackgroundTexture(STEEL, id + "_steel");
        } else {
            getGui().setBackgroundTexture(BRONZE, "machine_bronze" + bSuffix).setBackgroundTexture(STEEL, "machine_steel" + bSuffix);
        }
    }

    protected void setupGui() {
        super.setupGui();
        addGuiCallback(t -> {
            t.addWidget(WidgetSupplier.build((a, b) -> TextWidget.build(((AntimatterContainerScreen<?>) b).getTitle().getString(), 4210752).build(a, b)).setPos(9, 5).clientSide());
            if (has(RECIPE) && !getId().contains("boiler")) {
                t.addWidget(ProgressWidget.build())
                        .addWidget(MachineStateWidget.build());
            }
        });
    }

    @Override
    public Machine<SteamMachine> add(ISlotProvider<?> provider) {
        List<SlotData<?>> list = provider.getAnySlots();
        for (SlotData<?> slot : list) {
            String suffix = slot.getType() == SlotType.FL_IN ? "fluid" : "item";
            add(BRONZE, slot.getType(), slot.getX(), slot.getY(), new ResourceLocation(GTIRef.ID, "bronze_"+suffix));
            add(STEEL, slot.getType(), slot.getX(), slot.getY(), new ResourceLocation(GTIRef.ID, "steel_"+suffix));
        }
        return this;
    }

    @Override
    public Machine<SteamMachine> add(SlotType<?> type, int x, int y) {
        String suffix = type == SlotType.FL_IN ? "fluid" : "item";
        add(BRONZE, type, x, y, new ResourceLocation(GTIRef.ID, "bronze_"+suffix));
        add(STEEL, type, x, y, new ResourceLocation(GTIRef.ID, "steel_"+suffix));
        return this;
    }
}