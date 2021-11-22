package muramasa.gti.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.GuiData;
import muramasa.antimatter.gui.MenuHandlerMachine;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.screen.AntimatterContainerScreen;
import muramasa.antimatter.gui.slot.ISlotProvider;
import muramasa.antimatter.gui.widget.TextWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.gti.gui.widgets.CoalBoilerFuelWidget;
import muramasa.gti.gui.widgets.CoalBoilerWidget;
import muramasa.gti.tile.single.TileEntityCoalBoiler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;

import javax.annotation.Nullable;

import static muramasa.antimatter.gui.ButtonBody.*;
import static muramasa.antimatter.gui.SlotType.*;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gti.data.Machines.*;

public class Guis {

    //TODO move these to the API somehow
    public static GuiData MULTI_DISPLAY = new GuiData("antimatter", "multi_display").setSlots(ISlotProvider.DEFAULT().add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 143, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34).add(FL_IN, 17, 63).add(FL_IN, 35, 63).add(FL_IN, 53, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63).add(FL_OUT, 143, 63));
    public static GuiData MULTI_DISPLAY_COMPACT = new GuiData("antimatter", "multi_display").setSlots(ISlotProvider.DEFAULT().add(MULTI_DISPLAY.getSlots())).setPadding(0, 0, 0, 0);
    public static GuiData BASIC_TANK = new GuiData("antimatter", "basic_tank").setSlots(ISlotProvider.DEFAULT().add(CELL_IN, 9, 22).add(CELL_OUT, 9, 58).add(FL_IN, 106, 43));

    public static GuiData ORE_BYPRODUCTS = new GuiData("antimatter", "ore_byproducts") {
        @Override
        public ResourceLocation getTexture(Tier tier,String type) {
            return new ResourceLocation(loc.getNamespace(), "textures/gui/" + loc.getPath() + ".png");
        }
    }.setPadding(0, 0, 0, 0).setSlots(ISlotProvider.DEFAULT().add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34));

    public static MenuHandlerMachine<TileEntityCoalBoiler,? extends ContainerMachine> COAL_BOILER_MENU_HANDLER = new MenuHandlerMachine(Ref.ID, "container_coal_boiler") {
        @Override
        public ContainerBasicMachine getMenu(IGuiHandler tile, PlayerInventory playerInv, int windowId) {
            return tile instanceof TileEntityMachine ? new ContainerBasicMachine((TileEntityMachine<?>) tile, playerInv, this, windowId) : null;
        }

        @Override
        public Object screen() {
            return ClientData.SCREEN_COAL;
        }
    };

    public static void init(Dist side) {
        ALLOY_SMELTER.add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        ASSEMBLER.add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 25)
                .add(FL_IN,53, 63)
                .add(ENERGY,80, 63);
        BENDER.add(ALLOY_SMELTER);
        CANNER.add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        COMPRESSOR.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        CUTTER.add(IT_IN, 53, 25).add(FL_IN, 53, 63).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(ENERGY,80, 63);
        FURNACE.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY,80, 63);
        EXTRACTOR.add(COMPRESSOR);
        EXTRUDER.add(ALLOY_SMELTER);
        LATHE.add(CUTTER);
        PULVERIZER.add(COMPRESSOR);
        PULVERIZER.add(HV, PULVERIZER).add(HV, IT_OUT, 125, 25);
        PULVERIZER.add(EV, PULVERIZER).add(EV, IT_OUT, 125, 25).add(EV, IT_OUT, 143, 25);
        PULVERIZER.add(IV, IT_IN, 53, 25).add(IV, IT_OUT, 107, 16).add(IV, IT_OUT, 125, 16).add(IV, IT_OUT, 107, 34).add(IV, IT_OUT, 125, 34);
        RECYCLER.add(COMPRESSOR);
        SCANNER.add(COMPRESSOR);
        WIRE_MILL.add(COMPRESSOR);
        CENTRIFUGE.add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34).add(ENERGY,80, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63);
        ELECTROLYZER.add(CENTRIFUGE);
        THERMAL_CENTRIFUGE.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).add(ENERGY,80, 63);
        ORE_WASHER.add(THERMAL_CENTRIFUGE).add(FL_IN, 53, 63).add(FL_OUT, 107, 63);
        CHEMICAL_REACTOR.add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(FL_IN, 35, 63).add(FL_IN, 53, 63).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63);
        FLUID_CANNER.add(COMPRESSOR).add(FL_IN, 53, 63).add(FL_OUT, 107, 63);
        DISASSEMBLER.add(IT_IN, 53, 25)
                .add(IT_OUT, 107, 7).add(IT_OUT, 107+18, 7).add(IT_OUT, 107+18*2, 7)
                .add(IT_OUT, 107, 25).add(IT_OUT, 107+18, 25).add(IT_OUT, 107+18*2, 25)
                .add(IT_OUT, 107, 43).add(IT_OUT, 107+18, 43).add(IT_OUT, 107+18*2, 43)
                .add(ENERGY,80, 63);
        MASS_FABRICATOR.add(COMPRESSOR);
        AMP_FABRICATOR.add(COMPRESSOR);
        REPLICATOR.add(COMPRESSOR);
        FERMENTER.add(COMPRESSOR);
        FLUID_EXTRACTOR.add(COMPRESSOR).add(FL_OUT, 107, 63);
        FLUID_SOLIDIFIER.add(COMPRESSOR).add(FL_IN, 53, 63);
        DISTILLERY.add(FLUID_CANNER);
        CHEMICAL_BATH.add(THERMAL_CENTRIFUGE).add(FL_IN, 53, 63);
        AUTOCLAVE.add(COMPRESSOR);
        MIXER.add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(FL_IN, 35, 63).add(FL_IN, 53, 63).add(IT_OUT, 107, 25).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63).add(ENERGY,80, 63);
        LASER_ENGRAVER.add(ALLOY_SMELTER);
        FORMING_PRESS.add(ALLOY_SMELTER);
        FORGE_HAMMER.add(FURNACE);
        SIFTER.add(DISASSEMBLER);
        ARC_FURNACE.add(ALLOY_SMELTER); //TODO
        PLASMA_ARC_FURNACE.add(ARC_FURNACE);

        COKE_OVEN.add(IT_IN, 53,16).add(IT_IN, 53, 34).add(IT_IN, 53, 52).add(IT_OUT,107,25);

        BATTERY_BUFFER_FOUR.add(ENERGY,71,27).add(ENERGY,89,27).add(ENERGY,71,45).add(ENERGY,89,45);
        BATTERY_BUFFER_ONE.add(ENERGY,80,40);
        BATTERY_BUFFER_NINE.add(ENERGY,62,21).add(ENERGY,80,21).add(ENERGY,98,21)
                .add(ENERGY,62,39).add(ENERGY,80,39).add(ENERGY,98,39)
                .add(ENERGY,62,57).add(ENERGY,80,57).add(ENERGY,98,57);

        COAL_BOILER.add(BRONZE, CELL_IN, 44, 26).add(BRONZE, CELL_OUT, 44, 62).add(BRONZE, IT_OUT, 116, 26).add(BRONZE, IT_IN, 116, 62);
        COAL_BOILER.add(STEEL, CELL_IN, 44, 26).add(STEEL, CELL_OUT, 44, 62).add(STEEL, IT_OUT, 116, 26).add(STEEL, IT_IN, 116, 62);
        LAVA_BOILER.add(ALLOY_SMELTER);
        SOLAR_BOILER.add(ALLOY_SMELTER);

        STEAM_ALLOY_SMELTER.add(BRONZE, ALLOY_SMELTER).add(BRONZE, FL_IN, 53, 63);
        STEAM_ALLOY_SMELTER.add(STEEL, ALLOY_SMELTER).add(STEEL, FL_IN, 53, 63);
        STEAM_COMPRESSOR.add(BRONZE, COMPRESSOR).add(BRONZE, FL_IN, 53, 63);
        STEAM_COMPRESSOR.add(STEEL, COMPRESSOR).add(STEEL, FL_IN, 53, 63);
        STEAM_FURNACE.add(BRONZE, FURNACE).add(BRONZE, FL_IN, 53, 63);
        STEAM_FURNACE.add(STEEL, FURNACE).add(STEEL, FL_IN, 53, 63);
        STEAM_EXTRACTOR.add(BRONZE, EXTRACTOR).add(BRONZE, FL_IN, 53, 63);
        STEAM_EXTRACTOR.add(STEEL, EXTRACTOR).add(STEEL, FL_IN, 53, 63);
        STEAM_PULVERIZER.add(BRONZE, PULVERIZER).add(BRONZE, FL_IN, 53, 63);
        STEAM_PULVERIZER.add(STEEL, PULVERIZER).add(STEEL, FL_IN, 53, 63);
        STEAM_FORGE_HAMMER.add(BRONZE, FORGE_HAMMER).add(BRONZE, FL_IN, 53, 63);
        STEAM_FORGE_HAMMER.add(STEEL, FORGE_HAMMER).add(STEEL, FL_IN, 53, 63);

        STEAM_GENERATOR.add(BASIC_TANK.getSlots());
        GAS_GENERATOR.add(BASIC_TANK.getSlots());
        COMBUSTION_GENERATOR.add(BASIC_TANK.getSlots());
        NAQUADAH_GENERATOR.add(BASIC_TANK.getSlots());
        PLASMA_GENERATOR.add(BASIC_TANK.getSlots());

        INFINITE_STEAM.add(BASIC_TANK.getSlots());

        QUANTUM_TANK.add(BASIC_TANK.getSlots());

        PRIMITIVE_BLAST_FURNACE.add(IT_IN, 53, 16).add(IT_IN, 53, 34).add(IT_IN, 53, 52).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).getGui().setPadding(0, 0, 0, 0);
        BRONZE_BLAST_FURNACE.add(IT_IN, 53, 16).add(IT_IN, 53, 34).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25);

        HATCH_MUFFLER.add(IT_IN, 79, 34);

        HATCH_ITEM_I.add(ULV, IT_IN, 79, 34);
        HATCH_ITEM_I.add(LV, IT_IN, 70, 25).add(LV, IT_IN, 88, 25).add(LV, IT_IN, 70, 43).add(LV, IT_IN, 88, 43);
        HATCH_ITEM_I.add(MV, IT_IN, 61, 16).add(MV, IT_IN, 79, 16).add(MV, IT_IN, 97, 16).add(MV, IT_IN, 61, 34).add(MV, IT_IN, 79, 34).add(MV, IT_IN, 97, 34).add(MV, IT_IN, 61, 52).add(MV, IT_IN, 79, 52).add(MV, IT_IN, 97, 52);
        HATCH_ITEM_I.add(HV, IT_IN, 52, 7).add(HV, IT_IN, 70, 7).add(HV, IT_IN, 88, 7).add(HV, IT_IN, 106, 7).add(HV, IT_IN, 52, 25).add(HV, IT_IN, 70, 25).add(HV, IT_IN, 88, 25).add(HV, IT_IN, 106, 25).add(HV, IT_IN, 52, 43).add(HV, IT_IN, 70, 43).add(HV, IT_IN, 88, 43).add(HV, IT_IN, 106, 43).add(HV, IT_IN, 52, 61).add(HV, IT_IN, 70, 61).add(HV, IT_IN, 88, 61).add(HV, IT_IN, 106, 61);
        HATCH_ITEM_I.add(EV, HATCH_ITEM_I, HV);
        HATCH_ITEM_I.add(IV, HATCH_ITEM_I, HV);
        HATCH_ITEM_I.add(LUV, HATCH_ITEM_I, HV);
        HATCH_ITEM_I.add(ZPM, HATCH_ITEM_I, HV);
        HATCH_ITEM_I.add(UV, HATCH_ITEM_I, HV);
        HATCH_ITEM_I.add(MAX, HATCH_ITEM_I, HV);

        HATCH_ITEM_O.add(ULV, IT_OUT, 79, 34);
        HATCH_ITEM_O.add(LV, IT_OUT, 70, 25).add(LV, IT_OUT, 88, 25).add(LV, IT_OUT, 70, 43).add(LV, IT_OUT, 88, 43);
        HATCH_ITEM_O.add(MV, IT_OUT, 61, 16).add(MV, IT_OUT, 79, 16).add(MV, IT_OUT, 97, 16).add(MV, IT_OUT, 61, 34).add(MV, IT_OUT, 79, 34).add(MV, IT_OUT, 97, 34).add(MV, IT_OUT, 61, 52).add(MV, IT_OUT, 79, 52).add(MV, IT_OUT, 97, 52);
        HATCH_ITEM_O.add(HV, IT_OUT, 52, 7).add(HV, IT_OUT, 70, 7).add(HV, IT_OUT, 88, 7).add(HV, IT_OUT, 106, 7).add(HV, IT_OUT, 52, 25).add(HV, IT_OUT, 70, 25).add(HV, IT_OUT, 88, 25).add(HV, IT_OUT, 106, 25).add(HV, IT_OUT, 52, 43).add(HV, IT_OUT, 70, 43).add(HV, IT_OUT, 88, 43).add(HV, IT_OUT, 106, 43).add(HV, IT_OUT, 52, 61).add(HV, IT_OUT, 70, 61).add(HV, IT_OUT, 88, 61).add(HV, IT_OUT, 106, 61);
        HATCH_ITEM_O.add(EV, HATCH_ITEM_O, HV);
        HATCH_ITEM_O.add(IV, HATCH_ITEM_O, HV);
        HATCH_ITEM_O.add(LUV, HATCH_ITEM_O, HV);
        HATCH_ITEM_O.add(ZPM, HATCH_ITEM_O, HV);
        HATCH_ITEM_O.add(UV, HATCH_ITEM_O, HV);
        HATCH_ITEM_O.add(MAX, HATCH_ITEM_O, HV);

        HATCH_FLUID_I.add(FL_IN, 79, 34).add(CELL_IN, 9, 22).add(CELL_OUT, 9, 58);
        HATCH_FLUID_I.add(LV, HATCH_FLUID_I, ULV);
        HATCH_FLUID_I.add(MV, HATCH_FLUID_I, ULV);
        HATCH_FLUID_I.add(HV, HATCH_FLUID_I, ULV);
        HATCH_FLUID_I.add(EV, FL_IN, 61, 16).add(EV, FL_IN, 79, 16).add(EV, FL_IN, 97, 16).add(EV, FL_IN, 61, 34).add(EV, FL_IN, 79, 34).add(EV, FL_IN, 97, 34).add(EV, FL_IN, 61, 52).add(EV, FL_IN, 79, 52).add(EV, FL_IN, 97, 52);
        HATCH_FLUID_I.add(IV, HATCH_FLUID_I, ULV);
        HATCH_FLUID_I.add(LUV, HATCH_FLUID_I, ULV);
        HATCH_FLUID_I.add(ZPM, HATCH_FLUID_I, ULV);
        HATCH_FLUID_I.add(UV, HATCH_FLUID_I, ULV);
        HATCH_FLUID_I.add(MAX, HATCH_FLUID_I, ULV);

        HATCH_FLUID_O.add(FL_OUT, 79, 34).add(CELL_IN, 9, 22).add(CELL_OUT, 9, 58);
        HATCH_FLUID_O.add(LV, HATCH_FLUID_O, ULV);
        HATCH_FLUID_O.add(MV, HATCH_FLUID_O, ULV);
        HATCH_FLUID_O.add(HV, HATCH_FLUID_O, ULV);
        HATCH_FLUID_O.add(EV, HATCH_FLUID_O, ULV);
        HATCH_FLUID_O.add(IV, HATCH_FLUID_O, ULV);
        HATCH_FLUID_O.add(LUV, HATCH_FLUID_O, ULV);
        HATCH_FLUID_O.add(ZPM, HATCH_FLUID_O, ULV);
        HATCH_FLUID_O.add(UV, FL_OUT, 61, 16).add(UV, FL_OUT, 79, 16).add(UV, FL_OUT, 97, 16).add(UV, FL_OUT, 61, 34).add(UV, FL_OUT, 79, 34).add(UV, FL_OUT, 97, 34).add(UV, FL_OUT, 61, 52).add(UV, FL_OUT, 79, 52).add(UV, FL_OUT, 97, 52);
        HATCH_FLUID_O.add(MAX, HATCH_FLUID_O, ULV);

        COAL_BOILER.addGuiCallback(t -> {
            t.addWidget(WidgetSupplier.build((a, b) -> TextWidget.build(((AntimatterContainerScreen<?>)b).getTitle().getString(), 4210752).build(a,b)).setPos(9, 5).clientSide());
            t.addWidget(CoalBoilerWidget.build().setSize(70, 25, 36, 54)).addWidget(CoalBoilerFuelWidget.build().setSize(115, 43, 18, 18));
        });

        //if (side.isClient()) {
            TRANSFORMER_DIGITAL.addGuiCallback(t -> {
                t.addButton(10, 18, 14, 14, APAD_LEFT)
                        .addButton(25, 18, 14, 14, PAD_LEFT)
                        .addButton(10, 33, 14, 14, APAD_LEFT)
                        .addButton(25, 33, 14, 14, PAD_LEFT)
                        .addButton(10, 48, 14, 14, APAD_LEFT)
                        .addButton(25, 48, 14, 14, PAD_LEFT)
                        .addButton(10, 63, 14, 14, APAD_LEFT)
                        .addButton(25, 63, 14, 14, PAD_LEFT)
                        .addButton(137, 18, 14, 14, PAD_RIGHT)
                        .addButton(152, 18, 14, 14, APAD_RIGHT)
                        .addButton(137, 33, 14, 14, PAD_RIGHT)
                        .addButton(152, 33, 14, 14, APAD_RIGHT)
                        .addButton(137, 48, 14, 14, PAD_RIGHT)
                        .addButton(152, 48, 14, 14, APAD_RIGHT)
                        .addButton(137, 63, 14, 14, PAD_RIGHT)
                        .addButton(152, 63, 14, 14, APAD_RIGHT);
            });
        }
        //}
}
