package muramasa.gregtech.data;

import muramasa.antimatter.Ref;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.*;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.slot.ISlotProvider;
import muramasa.antimatter.gui.widget.*;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.registration.Side;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.gui.widgets.*;
import muramasa.gregtech.blockentity.single.BlockEntityCoalBoiler;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;

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

    public static GuiData MULTI_DISPLAY_DISTILLATION = new GuiData(GTIRef.ID, "multi_display_distillation").setSlots(ISlotProvider.DEFAULT()
            .add(FL_IN, 53, 43)
            .add(IT_OUT, 107, 61).add(FL_OUT, 125, 61).add(FL_OUT, 143, 61)
            .add(FL_OUT, 107, 43).add(FL_OUT, 125, 43).add(FL_OUT, 143, 43)
            .add(FL_OUT, 107, 25).add(FL_OUT, 125, 25).add(FL_OUT, 143, 25)
            .add(FL_OUT, 107, 7).add(FL_OUT, 125, 7).add(FL_OUT, 143, 7));
    public static GuiData MULTI_DISPLAY_COMPACT = new GuiData(GTIRef.ID, "multi_display")
            .setSlots(ISlotProvider.DEFAULT().add(MULTI_DISPLAY.getSlots()));
    public static GuiData BASIC_TANK = new GuiData(GTIRef.ID, "basic_tank").setBackgroundTexture("basic_tank")
            .setSlots(ISlotProvider.DEFAULT().add(CELL_IN, 8, 17).add(CELL_OUT, 8, 53).add(FL_IN, 55, 43, new ResourceLocation(GTIRef.ID, "blank")));

    public static GuiData MULTIBLOCK = new GuiData(GTIRef.ID, "multiblock").setBackgroundTexture("multiblock").setSlots(ISlotProvider.DEFAULT().add(STORAGE, 152, 5));

    public static GuiData ORE_BYPRODUCTS = new GuiData("antimatter", "ore_byproducts") {
        @Override
        public ResourceLocation getTexture(Tier tier, String type) {
            return new ResourceLocation(loc.getNamespace(), "textures/gui/" + loc.getPath() + ".png");
        }
    }.setSlots(ISlotProvider.DEFAULT().add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16)
                    .add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16)
                    .add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34));

    public static MenuHandlerMachine<BlockEntityCoalBoiler, ? extends ContainerMachine> COAL_BOILER_MENU_HANDLER = new MenuHandlerMachine(
            Ref.ID, "container_coal_boiler") {
        @Override
        public ContainerBasicMachine getMenu(IGuiHandler tile, Inventory playerInv, int windowId) {
            return tile instanceof BlockEntityMachine
                    ? new ContainerBasicMachine((BlockEntityMachine<?>) tile, playerInv, this, windowId)
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
        slots();
        backgroundTextures();
        machineData();
        widgets();
    }

    public static void slots(){
        ALLOY_SMELTER.add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY, 80, 63);
        ARC_FURNACE.add(IT_IN, 53, 25).add(FL_IN, 53, 63).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16)
                .add(IT_OUT, 125, 34).add(IT_OUT, 107, 34).add(ENERGY, 80, 63);
        ASSEMBLER.add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34)
                .add(IT_IN, 53, 34).add(IT_OUT, 107, 25)
                .add(FL_IN, 53, 63)
                .add(ENERGY, 80, 63);
        BENDER.add(ALLOY_SMELTER);
        CANNER.add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY, 80, 63);
        CIRCUIT_ASSEMBLER.add(ASSEMBLER);
        COMPRESSOR.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY, 80, 63);
        CUTTER.add(IT_IN, 53, 25).add(FL_IN, 53, 63).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(ENERGY, 80, 63);
        FURNACE.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(ENERGY, 80, 63);
        EXTRACTOR.add(COMPRESSOR);
        EXTRUDER.add(ALLOY_SMELTER);
        LATHE.add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(ENERGY, 80, 63);
        MACERATOR.add(COMPRESSOR);
        MACERATOR.add(MV, MACERATOR).add(MV, IT_OUT, 125, 25);
        MACERATOR.add(HV, MACERATOR).add(HV, IT_OUT, 125, 25).add(HV, IT_OUT, 143, 25);
        MACERATOR.add(EV, IT_IN, 53, 25).add(EV, IT_OUT, 107, 16).add(EV, IT_OUT, 125, 16).add(EV, IT_OUT, 107, 34)
                .add(EV, IT_OUT, 125, 34);
        ROASTER.add(IT_IN, 53, 25).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 107, 34)
                .add(IT_OUT, 125, 34).add(FL_IN, 53, 63).add(FL_OUT, 107, 63);
        RECYCLER.add(COMPRESSOR).add(FL_IN, 53, 63);
        WIRE_MILL.add(COMPRESSOR);
        CENTRIFUGE.add(IT_IN, 35, 25)
                .add(FL_IN, 53, 25)
                .add(IT_OUT, 107, 7).add(IT_OUT, 125, 7).add(IT_OUT, 143, 7)
                .add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25)
                .add(FL_OUT, 107, 43).add(FL_OUT, 125, 43).add(FL_OUT, 143, 43)
                .add(FL_OUT, 107, 61).add(FL_OUT, 125, 61).add(FL_OUT, 143, 61)
                .add(ENERGY, 17, 25);
        ELECTRIC_OVEN.add(FURNACE);
        ELECTROLYZER.add(CENTRIFUGE).add(IT_IN, 35, 43).add(FL_IN,53, 43);
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
        MASS_FABRICATOR.add(COMPRESSOR).add(FL_IN, 53, 63).add(FL_OUT, 107, 63);
        AMP_FABRICATOR.add(COMPRESSOR).add(FL_IN, 53, 63).add(FL_OUT, 107, 63);
        REPLICATOR.add(FLUID_CANNER);
        FERMENTER.add(FLUID_CANNER);
        FLUID_PRESS.add(IT_IN, 53, 25).add(ENERGY, 80, 63).add(FL_OUT, 107, 63);
        SMELTER.add(FLUID_PRESS);
        FLUID_HEATER.add(ENERGY, 80, 63).add(FL_IN, 53, 63).add(FL_OUT, 107, 63);
        FLUID_SOLIDIFIER.add(COMPRESSOR).add(FL_IN, 53, 63);
        SCANNER.add(FLUID_SOLIDIFIER).add(IT_IN, 125, 63);
        PRINTER.add(SCANNER);
        DISTILLERY.add(FLUID_CANNER);
        BATH.add(THERMAL_CENTRIFUGE).add(FL_IN, 53, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63);
        AUTOCLAVE.add(ALLOY_SMELTER).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).add(FL_IN, 53, 63);
        PACKAGER.add(COMPRESSOR);
        POLARIZER.add(COMPRESSOR);
        MIXER.add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(FL_IN, 44, 63)
                .add(FL_IN, 62, 63).add(IT_OUT, 107, 25).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63).add(ENERGY, 80, 63);
        LASER_ENGRAVER.add(ALLOY_SMELTER);
        FORMING_PRESS.add(ALLOY_SMELTER);
        FORGE_HAMMER.add(FURNACE);
        for (int y = 0; y < 3; y++){
            for (int x = 0; x < 9; x++){
                CHEST_BUFFER.add(STORAGE, 8 + (x * 18), 5 + (y * 18));
            }
        }
        SIFTER.add(IT_IN, 53, 25)
                .add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 143, 16)
                .add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34)
                .add(ENERGY, 80, 63);
        ELECTROMAGNETIC_SEPARATOR.add(COMPRESSOR).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25);
        DEHYDRATOR.add(IT_IN, 35, 25).add(IT_IN, 53, 25)
                .add(FL_IN,35,43).add(FL_IN,53,43)
                .add(IT_OUT, 107, 7).add(IT_OUT, 125, 7).add(IT_OUT, 143, 7)
                .add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25)
                .add(IT_OUT, 107, 43).add(IT_OUT, 125, 43).add(IT_OUT, 143, 43)
                .add(FL_OUT,107,63).add(FL_OUT,125,63).add(FL_OUT,143,63)
                .add(ENERGY,80,63);
        COKE_OVEN.add(IT_IN, 53, 25, new ResourceLocation(GTIRef.ID, "primitive_ingot"))
                .add(IT_OUT, 107, 25, new ResourceLocation(GTIRef.ID, "primitive_ingot"))
                .add(FL_OUT, 125, 25, new ResourceLocation(GTIRef.ID, "primitive_cell"));
        ResourceLocation bat = new ResourceLocation(GTIRef.ID, "battery");
        BATTERY_BUFFER_FOUR.add(ENERGY, 71, 27, bat).add(ENERGY, 89, 27, bat).add(ENERGY, 71, 45, bat).add(ENERGY, 89, 45, bat);
        BATTERY_BUFFER_ONE.add(ENERGY, 80, 40, bat);
        BATTERY_BUFFER_EIGHT
                .add(ENERGY,53,27, bat).add(ENERGY,71,27, bat).add(ENERGY,89,27, bat).add(ENERGY,107,27, bat)
                .add(ENERGY,53,45, bat).add(ENERGY,71,45, bat).add(ENERGY,89,45, bat).add(ENERGY,107,45, bat);
        BATTERY_BUFFER_SIXTEEN
                .add(ENERGY,53,9, bat).add(ENERGY,71,9, bat).add(ENERGY,89,9, bat).add(ENERGY,107,9, bat)
                .add(ENERGY,53,27, bat).add(ENERGY,71,27, bat).add(ENERGY,89,27, bat).add(ENERGY,107,27, bat)
                .add(ENERGY,53,45, bat).add(ENERGY,71,45, bat).add(ENERGY,89,45, bat).add(ENERGY,107,45, bat)
                .add(ENERGY,53,63, bat).add(ENERGY,71,63, bat).add(ENERGY,89,63, bat).add(ENERGY,107,63, bat);

        COAL_BOILER.add(CELL_IN, 44, 26).add(CELL_OUT, 44, 62).add(IT_OUT, 116, 26).add(IT_IN, 116, 62);
        COAL_BOILER.add(CELL_IN, 44, 26).add(CELL_OUT, 44, 62).add(IT_OUT, 116, 26).add(STEEL,
                IT_IN, 116, 62);
        LAVA_BOILER.add(CELL_IN, 44, 26).add(CELL_OUT, 44, 62);
        SOLAR_BOILER.add(CELL_IN, 44, 26).add(CELL_OUT, 44, 62);

        STEAM_ALLOY_SMELTER.add(ALLOY_SMELTER).add(FL_IN, 53, 63);
        STEAM_COMPRESSOR.add(COMPRESSOR).add(FL_IN, 53, 63);
        STEAM_FURNACE.add(FURNACE).add(FL_IN, 53, 63);
        STEAM_EXTRACTOR.add(EXTRACTOR).add(FL_IN, 53, 63);
        STEAM_MACERATOR.add(MACERATOR).add(FL_IN, 53, 63);
        STEAM_FORGE_HAMMER.add(FORGE_HAMMER).add(FL_IN, 53, 63);
        STEAM_SIFTER.add(SIFTER).add(FL_IN, 53, 63);
        STEAM_GENERATOR.add(BASIC_TANK.getSlots());
        GAS_GENERATOR.add(BASIC_TANK.getSlots());
        COMBUSTION_GENERATOR.add(BASIC_TANK.getSlots());
        SEMIFLUID_GENERATOR.add(BASIC_TANK.getSlots());
        NAQUADAH_GENERATOR.add(BASIC_TANK.getSlots());

        CROP_HARVESTER.add(IT_OUT, 62, 16).add(IT_OUT, 80, 16).add(IT_OUT, 98, 16)
                .add(IT_OUT, 62, 34).add(IT_OUT, 80, 34).add(IT_OUT, 98, 34)
                .add(IT_OUT, 62, 52).add(IT_OUT, 80, 52).add(IT_OUT, 98, 52);
        INFINITE_STEAM.add(BASIC_TANK.getSlots());

        QUANTUM_TANK.add(BASIC_TANK.getSlots());
        PRIMITIVE_BLAST_FURNACE.add(IT_IN, 53, 16, new ResourceLocation(GTIRef.ID, "primitive_ingot"))
                .add(IT_IN, 53, 34, new ResourceLocation(GTIRef.ID, "primitive_fire"))
                .add(IT_IN, 53, 52, new ResourceLocation(GTIRef.ID, "primitive_fire"))
                .add(IT_OUT, 107, 25, new ResourceLocation(GTIRef.ID, "primitive_ingot"))
                .add(IT_OUT, 125, 25, new ResourceLocation(GTIRef.ID, "primitive_dust"))
                .add(IT_OUT, 143, 25, new ResourceLocation(GTIRef.ID, "primitive_dust"));

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
        HATCH_ITEM_I.add(UHV, HATCH_ITEM_I, HV);

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
        HATCH_ITEM_O.add(UHV, HATCH_ITEM_O, HV);

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
        HATCH_FLUID_I.add(UHV, HATCH_FLUID_I, ULV);

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
        HATCH_FLUID_O.add(UHV, HATCH_FLUID_O, ULV);
        ELECTRIC_ITEM_FILTER
                .add(DISPLAY_SETTABLE, 18, 6, new ResourceLocation(GTIRef.ID, "blank")).add(DISPLAY_SETTABLE, 35, 6, new ResourceLocation(GTIRef.ID, "blank")).add(DISPLAY_SETTABLE, 52, 6, new ResourceLocation(GTIRef.ID, "blank"))
                .add(DISPLAY_SETTABLE, 18, 23, new ResourceLocation(GTIRef.ID, "blank")).add(DISPLAY_SETTABLE, 35, 23, new ResourceLocation(GTIRef.ID, "blank")).add(DISPLAY_SETTABLE, 52, 23, new ResourceLocation(GTIRef.ID, "blank"))
                .add(DISPLAY_SETTABLE, 18, 40, new ResourceLocation(GTIRef.ID, "blank")).add(DISPLAY_SETTABLE, 35, 40, new ResourceLocation(GTIRef.ID, "blank")).add(DISPLAY_SETTABLE, 52, 40, new ResourceLocation(GTIRef.ID, "blank"))
                .add(STORAGE, 98, 5).add(STORAGE, 98 + 18, 5)
                .add(STORAGE, 98 + 18 * 2, 5)
                .add(STORAGE, 98, 23).add(STORAGE, 98 + 18, 23)
                .add(STORAGE, 98 + 18 * 2, 23)
                .add(STORAGE, 98, 41).add(STORAGE, 98 + 18, 41)
                .add(STORAGE, 98 + 18 * 2, 41);

        ELECTRIC_TYPE_FILTER
                .add(DISPLAY_SETTABLE, 35, 23, new ResourceLocation(GTIRef.ID, "blank"))
                .add(STORAGE, 98, 5).add(STORAGE, 98 + 18, 5)
                .add(STORAGE, 98 + 18 * 2, 5)
                .add(STORAGE, 98, 23).add(STORAGE, 98 + 18, 23)
                .add(STORAGE, 98 + 18 * 2, 23)
                .add(STORAGE, 98, 41).add(STORAGE, 98 + 18, 41)
                .add(STORAGE, 98 + 18 * 2, 41);
        BLAST_FURNACE.add(MULTIBLOCK.getSlots());
        IMPLOSION_COMPRESSOR.add(MULTIBLOCK.getSlots());
        VACUUM_FREEZER.add(MULTIBLOCK.getSlots());
        MULTI_SMELTER.add(MULTIBLOCK.getSlots());
        LARGE_BOILER.add(MULTIBLOCK.getSlots());
        LARGE_CHEMICAL_REACTOR.add(MULTIBLOCK.getSlots());
        LARGE_TURBINE.add(MULTIBLOCK.getSlots());
        HEAT_EXCHANGER.add(MULTIBLOCK.getSlots());
        OIL_DRILLING_RIG.add(MULTIBLOCK.getSlots());
        ADVANCED_MINER.add(MULTIBLOCK.getSlots());
        PROCESSING_ARRAY.add(MULTIBLOCK.getSlots());
        PYROLYSIS_OVEN.add(MULTIBLOCK.getSlots());
        COMBUSTION_ENGINE.add(MULTIBLOCK.getSlots());
        DISTLLATION_TOWER.add(MULTIBLOCK.getSlots());
        CRYO_DISTLLATION_TOWER.add(MULTIBLOCK.getSlots());
        CRACKING_UNIT.add(MULTIBLOCK.getSlots());
    }

    public static void backgroundTextures(){
        MACERATOR.getGui().setBackgroundTexture("machine_macerator");
        //FORGE_HAMMER.getGui().setBackgroundTexture("machine_forge_hammer");
        CENTRIFUGE.getGui().setBackgroundTexture("centrifuge");
        ELECTROLYZER.getGui().setBackgroundTexture("centrifuge");
        COKE_OVEN.getGui().setBackgroundTexture("coke_oven");
        PRIMITIVE_BLAST_FURNACE.getGui().setBackgroundTexture("primitive_blast_furnace");
        SUPER_BUFFER.getGui().setBackgroundTexture("super_buffer");
        CHEST_BUFFER.getGui().setBackgroundTexture("chest_buffer");
        COKE_OVEN.getGui().setBackgroundTexture("coke_oven");
        STEAM_GENERATOR.getGui().setBackgroundTexture("basic_tank");
        GAS_GENERATOR.getGui().setBackgroundTexture("basic_tank");
        COMBUSTION_GENERATOR.getGui().setBackgroundTexture("basic_tank");
        SEMIFLUID_GENERATOR.getGui().setBackgroundTexture("basic_tank");
        NAQUADAH_GENERATOR.getGui().setBackgroundTexture("basic_tank");
        INFINITE_STEAM.getGui().setBackgroundTexture("basic_tank");
        QUANTUM_TANK.getGui().setBackgroundTexture("basic_tank");
        ELECTRIC_ITEM_FILTER.getGui().setBackgroundTexture("electric_item_filter");
        ELECTRIC_TYPE_FILTER.getGui().setBackgroundTexture("electric_type_filter");
        BLAST_FURNACE.getGui().setBackgroundTexture("multiblock");
        IMPLOSION_COMPRESSOR.getGui().setBackgroundTexture("multiblock");
        VACUUM_FREEZER.getGui().setBackgroundTexture("multiblock");
        MULTI_SMELTER.getGui().setBackgroundTexture("multiblock");
        LARGE_BOILER.getGui().setBackgroundTexture("multiblock");
        LARGE_CENTRIFUGE.getGui().setBackgroundTexture("multiblock");
        LARGE_CHEMICAL_REACTOR.getGui().setBackgroundTexture("multiblock");
        LARGE_ELECTROLYZER.getGui().setBackgroundTexture("multiblock");
        LARGE_MACERATOR.getGui().setBackgroundTexture("multiblock");
        LARGE_TURBINE.getGui().setBackgroundTexture("multiblock");
        HEAT_EXCHANGER.getGui().setBackgroundTexture("multiblock");
        OIL_DRILLING_RIG.getGui().setBackgroundTexture("multiblock");
        ADVANCED_MINER.getGui().setBackgroundTexture("multiblock");
        PROCESSING_ARRAY.getGui().setBackgroundTexture("multiblock");
        PYROLYSIS_OVEN.getGui().setBackgroundTexture("multiblock");
        COMBUSTION_ENGINE.getGui().setBackgroundTexture("multiblock");
        DISTLLATION_TOWER.getGui().setBackgroundTexture("multiblock");
        CRYO_DISTLLATION_TOWER.getGui().setBackgroundTexture("multiblock");
        CRACKING_UNIT.getGui().setBackgroundTexture("multiblock");
        FUSION_REACTOR.setGUI(MenuHandlers.FUSION_MENU_HANDLER);
        FUSION_REACTOR.getGui().setBackgroundTexture("fusion_control_computer");
    }

    public static void machineData(){
        ASSEMBLER.getGui().getMachineData().setProgressLocation("assembler");
        CANNER.getGui().getMachineData().setProgressLocation("canner");
        CIRCUIT_ASSEMBLER.getGui().getMachineData().setProgressLocation("assembler");
        COMPRESSOR.getGui().getMachineData().setProgressLocation("compressor");
        CUTTER.getGui().getMachineData().setProgressLocation("cutter");
        EXTRACTOR.getGui().getMachineData().setProgressLocation("extractor");
        EXTRUDER.getGui().getMachineData().setProgressLocation("extruder");
        LATHE.getGui().getMachineData().setProgressLocation("lathe");
        MACERATOR.getGui().getMachineData().setProgressLocation("macerator");
        WIRE_MILL.getGui().getMachineData().setProgressLocation("wiremill");
        CENTRIFUGE.getGui().getMachineData().setProgressLocation("extractor");
        ELECTROLYZER.getGui().getMachineData().setProgressLocation("extractor");
        ORE_WASHER.getGui().getMachineData().setProgressLocation("ore_washer");
        CHEMICAL_REACTOR.getGui().getMachineData().setProgressLocation("chemical_reactor");
        FLUID_CANNER.getGui().getMachineData().setProgressLocation("canner");
        AMP_FABRICATOR.getGui().getMachineData().setProgressLocation("extractor");
        FERMENTER.getGui().getMachineData().setProgressLocation("chemical_reactor");
        FLUID_PRESS.getGui().getMachineData().setProgressLocation("extractor");
        SMELTER.getGui().getMachineData().setProgressLocation("smelter");
        DISTILLERY.getGui().getMachineData().setProgressLocation("chemical_reactor");
        BATH.getGui().getMachineData().setProgressLocation("ore_washer");
        POLARIZER.getGui().getMachineData().setProgressLocation("electromagnetic_separator");
        MIXER.getGui().getMachineData().setProgressLocation("mixer");
        FORMING_PRESS.getGui().getMachineData().setProgressLocation("compressor");
        SIFTER.getGui().getMachineData().setProgressLocation("sifter");
        ELECTROMAGNETIC_SEPARATOR.getGui().getMachineData().setProgressLocation("electromagnetic_separator");
        COKE_OVEN.getGui().getMachineData().setProgressLocation("coke_oven");
        PRIMITIVE_BLAST_FURNACE.getGui().getMachineData().setProgressLocation("coke_oven");
        FORGE_HAMMER.setGuiProgressBarForJEI(BarDir.BOTTOM, false).getGui().getMachineData().setMachineStatePos(84, 46).setProgressLocation("forge_hammer");
        STEAM_FORGE_HAMMER.setGuiProgressBarForJEI(BarDir.BOTTOM, false).getGui().getMachineData().setMachineStatePos(80, 50);
        FUSION_REACTOR.getGui().setEnablePlayerSlots(false)
                .getMachineData().setProgressLocation("fusion_reactor").setProgressPos(163, 4).setProgressSize(149, 16);
    }

    public static void widgets(){
        FORGE_HAMMER.addGuiCallback(t -> {
            t.addWidget(IconWidget.build(new ResourceLocation(GTIRef.ID, "textures/gui/button/forge_hammer_overlay.png"), 78, 42, 20, 6));
        });
        COAL_BOILER.addGuiCallback(t -> {
            t.addWidget(CoalBoilerWidget.build().setSize(70, 25, 36, 54))
                    .addWidget(CoalBoilerFuelWidget.build().setSize(115, 43, 18, 18));
        });

        LAVA_BOILER.addGuiCallback(t -> {
            t.addWidget(LavaBoilerWidget.build().setSize(70, 25, 62, 54));
        });

        SOLAR_BOILER.addGuiCallback(t -> {
            t.addWidget(SolarBoilerWidget.build().setSize(70, 25, 62, 54));
        });
        ADJUSTABLE_TRANSFORMER.getGui().setBackgroundTexture(new ResourceLocation(Ref.ID, "creative_generator"));
        // if (side.isClient()) {
        ADJUSTABLE_TRANSFORMER.addGuiCallback(t -> {
            t.addButton(10, 18, ButtonOverlay.APAD_LEFT, false)
                    .addButton(25, 18, ButtonOverlay.PAD_LEFT, false)
                    .addButton(10, 33, ButtonOverlay.APAD_LEFT, false)
                    .addButton(25, 33, ButtonOverlay.PAD_LEFT, false)
                    .addButton(10, 48, ButtonOverlay.APAD_LEFT, false)
                    .addButton(25, 48, ButtonOverlay.PAD_LEFT, false)
                    .addButton(10, 63, ButtonOverlay.APAD_LEFT, false)
                    .addButton(25, 63, ButtonOverlay.PAD_LEFT, false)
                    .addButton(137, 18, ButtonOverlay.PAD_RIGHT, false)
                    .addButton(152, 18, ButtonOverlay.APAD_RIGHT, false)
                    .addButton(137, 33, ButtonOverlay.PAD_RIGHT, false)
                    .addButton(152, 33, ButtonOverlay.APAD_RIGHT, false)
                    .addButton(137, 48, ButtonOverlay.PAD_RIGHT, false)
                    .addButton(152, 48, ButtonOverlay.APAD_RIGHT, false)
                    .addButton(137, 63, ButtonOverlay.PAD_RIGHT, false)
                    .addButton(152, 63, ButtonOverlay.APAD_RIGHT, false);
        });

        ELECTRIC_ITEM_FILTER.getCallbacks().remove(1);
        ELECTRIC_TYPE_FILTER.getCallbacks().remove(1);
        CHEST_BUFFER.getCallbacks().remove(1);
        FUSION_REACTOR.addGuiCallback(t -> {
            t.addButton(155, 23, ButtonOverlay.NO_OVERLAY, false).addButton(155, 41, ButtonOverlay.NO_OVERLAY, false).addButton(155, 59, ButtonOverlay.NO_OVERLAY, false).addWidget(makeProgress()).addWidget(FusionButtonWidget.build());
        });
    }

    public static WidgetSupplier makeProgress(){
        return builder(ProgressWidget::new);
    }
    // }
}
