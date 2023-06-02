package muramasa.gregtech.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.*;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.screen.AntimatterContainerScreen;
import muramasa.antimatter.gui.slot.ISlotProvider;
import muramasa.antimatter.gui.widget.*;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.int2;
import muramasa.antimatter.util.int4;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.gui.widgets.*;
import muramasa.gregtech.tile.single.TileEntityCoalBoiler;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;

import static muramasa.antimatter.gui.ButtonBody.*;
import static muramasa.antimatter.gui.SlotType.*;
import static muramasa.antimatter.gui.Widget.builder;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gregtech.data.Machines.*;

public class Guis {

    // TODO move these to the API somehow
    public static GuiData MULTI_DISPLAY = new GuiData(GTIRef.ID, "multi_display").setSlots(ISlotProvider.DEFAULT()
            .add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34)
            .add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 143, 16).add(IT_OUT, 107, 34)
            .add(IT_OUT, 125, 34).add(IT_OUT, 143, 34).add(FL_IN, 17, 63).add(FL_IN, 35, 63).add(FL_IN, 53, 63)
            .add(FL_OUT, 107, 63).add(FL_OUT, 125, 63).add(FL_OUT, 143, 63));

    public static GuiData MULTI_DISPLAY_FLUID = new GuiData(GTIRef.ID, "multi_display_fluid").setSlots(ISlotProvider.DEFAULT()
            .add(FL_IN, 17, 63).add(FL_IN, 35, 63).add(FL_IN, 53, 63)
            .add(FL_OUT, 107, 7).add(FL_OUT, 125, 7).add(FL_OUT, 143, 7)
            .add(FL_OUT, 107, 25).add(FL_OUT, 125, 25).add(FL_OUT, 143, 25)
            .add(FL_OUT, 107, 43).add(FL_OUT, 125, 43).add(FL_OUT, 143, 43)
            .add(FL_OUT, 107, 61).add(FL_OUT, 125, 61).add(FL_OUT, 143, 61));
    public static GuiData MULTI_DISPLAY_COMPACT = new GuiData(GTIRef.ID, "multi_display")
            .setSlots(ISlotProvider.DEFAULT().add(MULTI_DISPLAY.getSlots())).setPadding(0, 0, 0, 0);
    public static GuiData BASIC_TANK = new GuiData(GTIRef.ID, "basic_tank")
            .setSlots(ISlotProvider.DEFAULT().add(CELL_IN, 8, 17).add(CELL_OUT, 8, 53).add(FL_IN, 55, 43));

    public static GuiData MULTIBLOCK = new GuiData(GTIRef.ID, "multiblock").setSlots(ISlotProvider.DEFAULT().add(STORAGE, 152, 5));

    public static GuiData ORE_BYPRODUCTS = new GuiData("antimatter", "ore_byproducts") {
        @Override
        public ResourceLocation getTexture(Tier tier, String type) {
            return new ResourceLocation(loc.getNamespace(), "textures/gui/" + loc.getPath() + ".png");
        }
    }.setPadding(0, 0, 0, 0)
            .setSlots(ISlotProvider.DEFAULT().add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16)
                    .add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16)
                    .add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34));

    public static MenuHandlerMachine<TileEntityCoalBoiler, ? extends ContainerMachine> COAL_BOILER_MENU_HANDLER = new MenuHandlerMachine(
            Ref.ID, "container_coal_boiler") {
        @Override
        public ContainerBasicMachine getMenu(IGuiHandler tile, Inventory playerInv, int windowId) {
            return tile instanceof TileEntityMachine
                    ? new ContainerBasicMachine((TileEntityMachine<?>) tile, playerInv, this, windowId)
                    : null;
        }

        @Override
        public String screenDomain() {
            return GTIRef.ID;
        }

        @Override
        public String screenID() {
            return "coal";
        }
    };

    public static void init(Side side) {
        ALLOY_SMELTER.add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY, 80, 63);
        ARC_FURNACE.add(IT_IN, 53, 25).add(FL_IN, 53, 63).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16)
                .add(IT_OUT, 125, 34).add(IT_OUT, 107, 34).add(ENERGY, 80, 63);
        ASSEMBLER.add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34)
                .add(IT_IN, 53, 34).add(IT_OUT, 107, 25)
                .add(FL_IN, 53, 63)
                .add(ENERGY, 80, 63);
        BENDER.add(ALLOY_SMELTER);
        CANNER.add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY, 80, 63);
        COMPRESSOR.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY, 80, 63);
        CUTTER.add(IT_IN, 53, 25).add(FL_IN, 53, 63).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(ENERGY, 80, 63);
        FURNACE.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY, 80, 63);
        EXTRACTOR.add(COMPRESSOR);
        EXTRUDER.add(ALLOY_SMELTER);
        LATHE.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(ENERGY, 80, 63);
        MACERATOR.add(COMPRESSOR);
        MACERATOR.add(HV, MACERATOR).add(HV, IT_OUT, 125, 25);
        MACERATOR.add(EV, MACERATOR).add(EV, IT_OUT, 125, 25).add(EV, IT_OUT, 143, 25);
        MACERATOR.add(IV, IT_IN, 53, 25).add(IV, IT_OUT, 107, 16).add(IV, IT_OUT, 125, 16).add(IV, IT_OUT, 107, 34)
                .add(IV, IT_OUT, 125, 34);
        RECYCLER.add(COMPRESSOR);
        SCANNER.add(COMPRESSOR);
        WIRE_MILL.add(COMPRESSOR);
        CENTRIFUGE.add(IT_IN, 35, 25)
                .add(FL_IN, 53, 25)
                .add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 143, 16)
                .add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34)
                .add(FL_OUT, 35, 63).add(FL_OUT, 53, 63).add(FL_OUT, 71, 63)
                .add(FL_OUT, 89, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63)
                .add(ENERGY, 17, 25);
        ELECTROLYZER.add(CENTRIFUGE);
        THERMAL_CENTRIFUGE.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).add(ENERGY,
                80, 63);
        ORE_WASHER.add(THERMAL_CENTRIFUGE).add(FL_IN, 53, 63).add(FL_OUT, 107, 63);
        CHEMICAL_REACTOR.add(IT_IN, 26, 16).add(IT_IN, 44, 16)
                .add(FL_IN, 17, 34).add(FL_IN, 35, 34).add(FL_IN, 53, 34)
                .add(IT_OUT, 116, 16).add(IT_OUT, 134, 16)
                .add(FL_OUT, 107, 34).add(FL_OUT, 125, 34).add(FL_OUT, 143, 34)
                .add(ENERGY, 80, 63);
        FLUID_CANNER.add(COMPRESSOR).add(FL_IN, 53, 63).add(FL_OUT, 107, 63);
        DISASSEMBLER.add(IT_IN, 53, 25)
                .add(IT_OUT, 107, 7).add(IT_OUT, 107 + 18, 7).add(IT_OUT, 107 + 18 * 2, 7)
                .add(IT_OUT, 107, 25).add(IT_OUT, 107 + 18, 25).add(IT_OUT, 107 + 18 * 2, 25)
                .add(IT_OUT, 107, 43).add(IT_OUT, 107 + 18, 43).add(IT_OUT, 107 + 18 * 2, 43)
                .add(ENERGY, 80, 63);
        MASS_FABRICATOR.add(COMPRESSOR);
        AMP_FABRICATOR.add(COMPRESSOR);
        REPLICATOR.add(FLUID_CANNER);
        FERMENTER.add(FLUID_CANNER);
        FLUID_EXTRACTOR.add(COMPRESSOR).add(FL_OUT, 107, 63);
        FLUID_SOLIDIFIER.add(COMPRESSOR).add(FL_IN, 53, 63);
        DISTILLERY.add(FLUID_CANNER);
        CHEMICAL_BATH.add(THERMAL_CENTRIFUGE).add(FL_IN, 53, 63);
        AUTOCLAVE.add(COMPRESSOR).add(FL_IN, 53, 63);
        PACKAGER.add(COMPRESSOR);
        MIXER.add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(FL_IN, 35, 63)
                .add(FL_IN, 53, 63).add(IT_OUT, 107, 25).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63).add(ENERGY, 80, 63);
        LASER_ENGRAVER.add(ALLOY_SMELTER);
        FORMING_PRESS.add(ALLOY_SMELTER);
        FORGE_HAMMER.add(FURNACE);
        SIFTER.add(IT_IN, 53, 25)
                .add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 143, 16)
                .add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34)
                .add(ENERGY, 80, 63);
        PLASMA_ARC_FURNACE.add(ARC_FURNACE);
        ELECTROMAGNETIC_SEPARATOR.add(COMPRESSOR).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25);
        DECAY_CHAMBER.add(IT_IN, 17, 25).add(IT_IN, 35, 25)
                .add(IT_OUT, 107, 25).add(FL_IN,53,25).add(FL_OUT,125,25).add(ENERGY,80,63);
        CHEMICAL_DEHYDRATOR.add(IT_IN, 35, 25).add(IT_IN, 53, 25)
                .add(FL_IN,35,63).add(FL_IN,53,63)
                .add(IT_OUT, 107, 7).add(IT_OUT, 125, 7).add(IT_OUT, 143, 7)
                .add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25)
                .add(IT_OUT, 107, 43).add(IT_OUT, 125, 43).add(IT_OUT, 143, 43)
                .add(FL_OUT,107,63).add(FL_OUT,125,63).add(FL_OUT,143,63)
                .add(ENERGY,80,63);

        COKE_OVEN.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(FL_OUT, 125, 25);
        BATTERY_BUFFER_FOUR.add(ENERGY, 71, 27).add(ENERGY, 89, 27).add(ENERGY, 71, 45).add(ENERGY, 89, 45);
        BATTERY_BUFFER_ONE.add(ENERGY, 80, 40);
        BATTERY_BUFFER_EIGHT
                .add(ENERGY,53,27).add(ENERGY,71,27).add(ENERGY,89,27).add(ENERGY,107,27)
                .add(ENERGY,53,45).add(ENERGY,71,45).add(ENERGY,89,45).add(ENERGY,107,45);
        BATTERY_BUFFER_SIXTEEN
                .add(ENERGY,53,9).add(ENERGY,71,9).add(ENERGY,89,9).add(ENERGY,107,9)
                .add(ENERGY,53,27).add(ENERGY,71,27).add(ENERGY,89,27).add(ENERGY,107,27)
                .add(ENERGY,53,45).add(ENERGY,71,45).add(ENERGY,89,45).add(ENERGY,107,45)
                .add(ENERGY,53,63).add(ENERGY,71,63).add(ENERGY,89,63).add(ENERGY,107,63);

        COAL_BOILER.add(BRONZE, CELL_IN, 44, 26).add(BRONZE, CELL_OUT, 44, 62).add(BRONZE, IT_OUT, 116, 26).add(BRONZE,
                IT_IN, 116, 62);
        COAL_BOILER.add(STEEL, CELL_IN, 44, 26).add(STEEL, CELL_OUT, 44, 62).add(STEEL, IT_OUT, 116, 26).add(STEEL,
                IT_IN, 116, 62);
        LAVA_BOILER.add(STEEL, CELL_IN, 44, 26).add(STEEL, CELL_OUT, 44, 62);
        SOLAR_BOILER.add(BRONZE, CELL_IN, 44, 26).add(BRONZE, CELL_OUT, 44, 62);

        STEAM_ALLOY_SMELTER.add(BRONZE, ALLOY_SMELTER).add(BRONZE, FL_IN, 53, 63);
        STEAM_ALLOY_SMELTER.add(STEEL, ALLOY_SMELTER).add(STEEL, FL_IN, 53, 63);
        STEAM_COMPRESSOR.add(BRONZE, COMPRESSOR).add(BRONZE, FL_IN, 53, 63);
        STEAM_COMPRESSOR.add(STEEL, COMPRESSOR).add(STEEL, FL_IN, 53, 63);
        STEAM_FURNACE.add(BRONZE, FURNACE).add(BRONZE, FL_IN, 53, 63);
        STEAM_FURNACE.add(STEEL, FURNACE).add(STEEL, FL_IN, 53, 63);
        STEAM_EXTRACTOR.add(BRONZE, EXTRACTOR).add(BRONZE, FL_IN, 53, 63);
        STEAM_EXTRACTOR.add(STEEL, EXTRACTOR).add(STEEL, FL_IN, 53, 63);
        STEAM_MACERATOR.add(BRONZE, MACERATOR).add(BRONZE, FL_IN, 53, 63);
        STEAM_MACERATOR.add(STEEL, MACERATOR).add(STEEL, FL_IN, 53, 63);
        STEAM_FORGE_HAMMER.add(BRONZE, FORGE_HAMMER).add(BRONZE, FL_IN, 53, 63);
        STEAM_FORGE_HAMMER.add(STEEL, FORGE_HAMMER).add(STEEL, FL_IN, 53, 63);

        STEAM_GENERATOR.add(BASIC_TANK.getSlots()).getGui().setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));
        GAS_GENERATOR.add(BASIC_TANK.getSlots()).getGui().setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));
        COMBUSTION_GENERATOR.add(BASIC_TANK.getSlots()).getGui().setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));
        NAQUADAH_GENERATOR.add(BASIC_TANK.getSlots()).getGui().setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));
        PLASMA_GENERATOR.add(BASIC_TANK.getSlots()).getGui().setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));

        INFINITE_STEAM.add(BASIC_TANK.getSlots()).getGui().setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));

        QUANTUM_TANK.add(BASIC_TANK.getSlots()).getGui().setOverrideLocation(BASIC_TANK.getTexture(LV, "machine"));

        PRIMITIVE_BLAST_FURNACE.add(IT_IN, 53, 16).add(IT_IN, 53, 34).add(IT_IN, 53, 52).add(IT_OUT, 107, 25)
                .add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).getGui().setPadding(0, 0, 0, 0);

        HATCH_MUFFLER.add(IT_IN, 79, 34);

        HATCH_ITEM_I.add(ULV, IT_IN, 79, 34);
        HATCH_ITEM_I.add(LV, IT_IN, 70, 25).add(LV, IT_IN, 88, 25).add(LV, IT_IN, 70, 43).add(LV, IT_IN, 88, 43);
        HATCH_ITEM_I.add(MV, IT_IN, 61, 16).add(MV, IT_IN, 79, 16).add(MV, IT_IN, 97, 16).add(MV, IT_IN, 61, 34)
                .add(MV, IT_IN, 79, 34).add(MV, IT_IN, 97, 34).add(MV, IT_IN, 61, 52).add(MV, IT_IN, 79, 52)
                .add(MV, IT_IN, 97, 52);
        HATCH_ITEM_I.add(HV, IT_IN, 52, 7).add(HV, IT_IN, 70, 7).add(HV, IT_IN, 88, 7).add(HV, IT_IN, 106, 7)
                .add(HV, IT_IN, 52, 25).add(HV, IT_IN, 70, 25).add(HV, IT_IN, 88, 25).add(HV, IT_IN, 106, 25)
                .add(HV, IT_IN, 52, 43).add(HV, IT_IN, 70, 43).add(HV, IT_IN, 88, 43).add(HV, IT_IN, 106, 43)
                .add(HV, IT_IN, 52, 61).add(HV, IT_IN, 70, 61).add(HV, IT_IN, 88, 61).add(HV, IT_IN, 106, 61);
        HATCH_ITEM_I.add(EV, HATCH_ITEM_I, HV);
        HATCH_ITEM_I.add(IV, HATCH_ITEM_I, HV);
        HATCH_ITEM_I.add(LUV, HATCH_ITEM_I, HV);
        HATCH_ITEM_I.add(ZPM, HATCH_ITEM_I, HV);
        HATCH_ITEM_I.add(UV, HATCH_ITEM_I, HV);
        HATCH_ITEM_I.add(MAX, HATCH_ITEM_I, HV);

        HATCH_ITEM_O.add(ULV, IT_OUT, 79, 34);
        HATCH_ITEM_O.add(LV, IT_OUT, 70, 25).add(LV, IT_OUT, 88, 25).add(LV, IT_OUT, 70, 43).add(LV, IT_OUT, 88, 43);
        HATCH_ITEM_O.add(MV, IT_OUT, 61, 16).add(MV, IT_OUT, 79, 16).add(MV, IT_OUT, 97, 16).add(MV, IT_OUT, 61, 34)
                .add(MV, IT_OUT, 79, 34).add(MV, IT_OUT, 97, 34).add(MV, IT_OUT, 61, 52).add(MV, IT_OUT, 79, 52)
                .add(MV, IT_OUT, 97, 52);
        HATCH_ITEM_O.add(HV, IT_OUT, 52, 7).add(HV, IT_OUT, 70, 7).add(HV, IT_OUT, 88, 7).add(HV, IT_OUT, 106, 7)
                .add(HV, IT_OUT, 52, 25).add(HV, IT_OUT, 70, 25).add(HV, IT_OUT, 88, 25).add(HV, IT_OUT, 106, 25)
                .add(HV, IT_OUT, 52, 43).add(HV, IT_OUT, 70, 43).add(HV, IT_OUT, 88, 43).add(HV, IT_OUT, 106, 43)
                .add(HV, IT_OUT, 52, 61).add(HV, IT_OUT, 70, 61).add(HV, IT_OUT, 88, 61).add(HV, IT_OUT, 106, 61);
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
        HATCH_FLUID_I.add(EV, FL_IN, 61, 16).add(EV, FL_IN, 79, 16).add(EV, FL_IN, 97, 16).add(EV, FL_IN, 61, 34)
                .add(EV, FL_IN, 79, 34).add(EV, FL_IN, 97, 34).add(EV, FL_IN, 61, 52).add(EV, FL_IN, 79, 52)
                .add(EV, FL_IN, 97, 52);
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
        HATCH_FLUID_O.add(UV, FL_OUT, 61, 16).add(UV, FL_OUT, 79, 16).add(UV, FL_OUT, 97, 16).add(UV, FL_OUT, 61, 34)
                .add(UV, FL_OUT, 79, 34).add(UV, FL_OUT, 97, 34).add(UV, FL_OUT, 61, 52).add(UV, FL_OUT, 79, 52)
                .add(UV, FL_OUT, 97, 52);
        HATCH_FLUID_O.add(MAX, HATCH_FLUID_O, ULV);

        STEAM_FORGE_HAMMER.getGui().getProgressData().setBarDir(BarDir.BOTTOM).setBarFill(false).setUVs(93, 0);
        FORGE_HAMMER.getGui().getProgressData().setBarDir(BarDir.BOTTOM).setBarFill(false).setUVs(0, 198);

        /*FORGE_HAMMER.getCallbacks().remove(1);
        FORGE_HAMMER.setGuiProgressBarForJEI(BarDir.BOTTOM, false).addGuiCallback(t -> {
            t.addWidget(WidgetSupplier.build((a, b) -> TextWidget.build(((AntimatterContainerScreen<?>)b).getTitle().getString(), 4210752).build(a,b)).setPos(9, 5).clientSide())
                    .addWidget(ProgressWidget.build(BarDir.BOTTOM, false, t.handler.getGui().getProgressData().uv, t.handler.getGui().getProgressData().pos))
                    .addWidget(MachineStateWidget.build().setPos(84,46).setWH(8,8))
                    .addWidget(IOWidget.build(9,63,16,16));
        });

        STEAM_FORGE_HAMMER.getCallbacks().remove(1);
        STEAM_FORGE_HAMMER.setGuiProgressBarForJEI(BarDir.BOTTOM, false).addGuiCallback(t -> {
            t.addWidget(WidgetSupplier.build((a, b) -> TextWidget.build(((AntimatterContainerScreen<?>)b).getTitle().getString(), 4210752).build(a,b)).setPos(9, 5).clientSide())
                    .addWidget(ProgressWidget.build(BarDir.BOTTOM, false, t.handler.getGui().getProgressData().uv, t.handler.getGui().getProgressData().pos))
                    .addWidget(MachineStateWidget.build().setPos(84,46).setWH(8,8));
        });*/

        COAL_BOILER.addGuiCallback(t -> {
            t.addWidget(WidgetSupplier
                    .build((a, b) -> TextWidget
                            .build(((AntimatterContainerScreen<?>) b).getTitle().getString(), 4210752).build(a, b))
                    .setPos(9, 5).clientSide());
            t.addWidget(CoalBoilerWidget.build().setSize(70, 25, 36, 54))
                    .addWidget(CoalBoilerFuelWidget.build().setSize(115, 43, 18, 18));
        });

        LAVA_BOILER.addGuiCallback(t -> {
            t.addWidget(WidgetSupplier
                    .build((a, b) -> TextWidget
                            .build(((AntimatterContainerScreen<?>)b).getTitle().getString(), 4210752).build(a, b))
                    .setPos(9, 5).clientSide());
            t.addWidget(LavaBoilerWidget.build().setSize(70, 25, 62, 54));
        });

        SOLAR_BOILER.addGuiCallback(t -> {
            t.addWidget(WidgetSupplier
                    .build((a, b) -> TextWidget
                            .build(((AntimatterContainerScreen<?>)b).getTitle().getString(), 4210752).build(a, b))
                    .setPos(9, 5).clientSide());
            t.addWidget(SolarBoilerWidget.build().setSize(70, 25, 62, 54));
        });
 
        if (!AntimatterAPI.isModLoaded("gt4r")) {
            ELECTRIC_ITEM_FILTER
                    .add(DISPLAY_SETTABLE, 18, 6).add(DISPLAY_SETTABLE, 35, 6).add(DISPLAY_SETTABLE, 52, 6)
                    .add(DISPLAY_SETTABLE, 18, 23).add(DISPLAY_SETTABLE, 35, 23).add(DISPLAY_SETTABLE, 52, 23)
                    .add(DISPLAY_SETTABLE, 18, 40).add(DISPLAY_SETTABLE, 35, 40).add(DISPLAY_SETTABLE, 52, 40)
                    .add(SlotTypes.FILTERABLE, 98, 5).add(SlotTypes.FILTERABLE, 98 + 18, 5)
                    .add(SlotTypes.FILTERABLE, 98 + 18 * 2, 5)
                    .add(SlotTypes.FILTERABLE, 98, 23).add(SlotTypes.FILTERABLE, 98 + 18, 23)
                    .add(SlotTypes.FILTERABLE, 98 + 18 * 2, 23)
                    .add(SlotTypes.FILTERABLE, 98, 41).add(SlotTypes.FILTERABLE, 98 + 18, 41)
                    .add(SlotTypes.FILTERABLE, 98 + 18 * 2, 41);

            ELECTRIC_TYPE_FILTER
                    .add(DISPLAY_SETTABLE, 35, 23)
                    .add(SlotTypes.FILTERABLE, 98, 5).add(SlotTypes.FILTERABLE, 98 + 18, 5)
                    .add(SlotTypes.FILTERABLE, 98 + 18 * 2, 5)
                    .add(SlotTypes.FILTERABLE, 98, 23).add(SlotTypes.FILTERABLE, 98 + 18, 23)
                    .add(SlotTypes.FILTERABLE, 98 + 18 * 2, 23)
                    .add(SlotTypes.FILTERABLE, 98, 41).add(SlotTypes.FILTERABLE, 98 + 18, 41)
                    .add(SlotTypes.FILTERABLE, 98 + 18 * 2, 41);
        }

        // if (side.isClient()) {
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

        if (!AntimatterAPI.isModLoaded("gt4r")){
            ELECTRIC_ITEM_FILTER.getCallbacks().remove(1);
            ELECTRIC_ITEM_FILTER.addGuiCallback(t -> {
                t.addWidget(FilterButtonArrayWidget.build());
            });
            ELECTRIC_TYPE_FILTER.getCallbacks().remove(1);
            ELECTRIC_TYPE_FILTER.addGuiCallback(t -> {
                t.addWidget(FilterButtonArrayWidget.build());
            });
        }

        BLAST_FURNACE.add(MULTIBLOCK.getSlots()).getGui().setOverrideLocation(MULTIBLOCK.getTexture(LV, "machine"));
        IMPLOSION_COMPRESSOR.add(MULTIBLOCK.getSlots()).getGui().setOverrideLocation(MULTIBLOCK.getTexture(LV, "machine"));
        VACUUM_FREEZER.add(MULTIBLOCK.getSlots()).getGui().setOverrideLocation(MULTIBLOCK.getTexture(LV, "machine"));
        MULTI_SMELTER.add(MULTIBLOCK.getSlots()).getGui().setOverrideLocation(MULTIBLOCK.getTexture(LV, "machine"));
        LARGE_BOILER.add(MULTIBLOCK.getSlots()).getGui().setOverrideLocation(MULTIBLOCK.getTexture(LV, "machine"));
        LARGE_TURBINE.add(MULTIBLOCK.getSlots()).getGui().setOverrideLocation(MULTIBLOCK.getTexture(LV, "machine"));
        HEAT_EXCHANGER.add(MULTIBLOCK.getSlots()).getGui().setOverrideLocation(MULTIBLOCK.getTexture(LV, "machine"));
        OIL_DRILLING_RIG.add(MULTIBLOCK.getSlots()).getGui().setOverrideLocation(MULTIBLOCK.getTexture(LV, "machine"));
        ADVANCED_MINER.add(MULTIBLOCK.getSlots()).getGui().setOverrideLocation(MULTIBLOCK.getTexture(LV, "machine"));
        PYROLYSIS_OVEN.add(MULTIBLOCK.getSlots()).getGui().setOverrideLocation(MULTIBLOCK.getTexture(LV, "machine"));
        COMBUSTION_ENGINE.add(MULTIBLOCK.getSlots()).getGui().setOverrideLocation(MULTIBLOCK.getTexture(LV, "machine"));
        DISTLLATION_TOWER.add(MULTIBLOCK.getSlots()).getGui().setOverrideLocation(MULTIBLOCK.getTexture(LV, "machine"));
        CRACKING_UNIT.add(MULTIBLOCK.getSlots()).getGui().setOverrideLocation(MULTIBLOCK.getTexture(LV, "machine"));
        NUCLEAR_REACTOR.add(MULTIBLOCK.getSlots()).getGui().setOverrideLocation(MULTIBLOCK.getTexture(LV, "machine"));
        FUSION_REACTOR.setGUI(MenuHandlers.FUSION_MENU_HANDLER);
        FUSION_REACTOR.getGui().setEnablePlayerSlots(false).setProgressTexture(FUSION_REACTOR.getGui().getTexture(LV, "machine"));
        FUSION_REACTOR.addGuiCallback(t -> {
            t.addButton(155, 23, 16, 16, ButtonBody.NO_OVERLAY).addButton(155, 41, 16, 16, NO_OVERLAY).addButton(155, 59, 16, 16, NO_OVERLAY).addWidget(ProgressWidget.build(new ProgressData().setPos(new int2(4, 162)).setUV(new int4(0, 235, 149, 16)).setBackgroundUV(null))).addWidget(FusionButtonWidget.build());
        });
    }
}
