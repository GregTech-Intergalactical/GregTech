package muramasa.gti.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.gui.GuiData;
import muramasa.antimatter.integration.jei.renderer.IInfoRenderer;
import muramasa.antimatter.machine.Tier;
import net.minecraft.util.ResourceLocation;

import static muramasa.antimatter.gui.SlotType.*;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gti.data.Machines.*;

public class Guis {

    //TODO move these to the API somehow
    public static GuiData MULTI_DISPLAY = new GuiData("antimatter", "multi_display").add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 143, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34).add(FL_IN, 17, 63).add(FL_IN, 35, 63).add(FL_IN, 53, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63).add(FL_OUT, 143, 63);
    public static GuiData MULTI_DISPLAY_COMPACT = new GuiData("antimatter", "multi_display").add(MULTI_DISPLAY).setPadding(0, 0, 0, 0);
    public static GuiData BASIC_TANK = new GuiData("antimatter", "basic_tank").add(IT_IN, 8, 17).add(IT_OUT, 8, 53).add(FL_IN, 106, 43);

    public static GuiData ORE_BYPRODUCTS = new GuiData("antimatter", "ore_byproducts") {
        @Override
        public ResourceLocation getTexture(Tier tier) {
            return new ResourceLocation(loc.getNamespace(), "textures/gui/" + loc.getPath() + ".png");
        }
    }.setPadding(0, 0, 0, 0).add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34);

    public static IInfoRenderer STEAM_INFO_RENDERER = (r, m, startY, width, height, mouseX, mouseY) -> {
        if (r.hasInputFluids()) m.fontRenderer.drawString("Steam: " + r.getPower() + "L/t (" + r.getSpecialValue() + " total)", 10, startY, 0x000000);
        if (r.getDuration() > 0) m.fontRenderer.drawString("Time: " + (r.getDuration() / (float)20) + "s (" + r.getDuration() + " ticks)", 10, startY + 10, 0x000000);
    };

    public static void init() {

        AntimatterAPI.registerJEICategory(RecipeMaps.ORE_BYPRODUCTS, Guis.ORE_BYPRODUCTS);
//        GregTechAPI.registerJEICategory(RecipeMaps.SMELTING, Guis.MULTI_DISPLAY_COMPACT);
        AntimatterAPI.registerJEICategory(RecipeMaps.STEAM_FUELS, Guis.MULTI_DISPLAY_COMPACT);
        AntimatterAPI.registerJEICategory(RecipeMaps.GAS_FUELS, Guis.MULTI_DISPLAY_COMPACT);
        AntimatterAPI.registerJEICategory(RecipeMaps.COMBUSTION_FUELS, Guis.MULTI_DISPLAY_COMPACT);
        AntimatterAPI.registerJEICategory(RecipeMaps.NAQUADAH_FUELS, Guis.MULTI_DISPLAY_COMPACT);
        AntimatterAPI.registerJEICategory(RecipeMaps.PLASMA_FUELS, Guis.MULTI_DISPLAY_COMPACT);

        //TODO changing slots of a machine in world, will crash from GTItemHandler.validateSlot()

        ALLOY_SMELTER.getGui().add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25);
        ASSEMBLER.getGui().add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 25);
        BENDER.getGui().add(ALLOY_SMELTER);
        CANNER.getGui().add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25);
        COMPRESSOR.getGui().add(IT_IN, 53, 25).add(IT_OUT, 107, 25);
        CUTTER.getGui().add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25);
        FURNACE.getGui().add(IT_IN, 53, 25).add(IT_OUT, 107, 25);
        EXTRACTOR.getGui().add(COMPRESSOR);
        EXTRUDER.getGui().add(ALLOY_SMELTER);
        LATHE.getGui().add(CUTTER);
        PULVERIZER.getGui().add(COMPRESSOR);
        PULVERIZER.getGui().add(HV, PULVERIZER).add(HV, IT_OUT, 125, 25);
        PULVERIZER.getGui().add(EV, PULVERIZER).add(EV, IT_OUT, 125, 25).add(EV, IT_OUT, 143, 25);
        PULVERIZER.getGui().add(IV, IT_IN, 53, 25).add(IV, IT_OUT, 107, 16).add(IV, IT_OUT, 125, 16).add(IV, IT_OUT, 107, 34).add(IV, IT_OUT, 125, 34);
        RECYCLER.getGui().add(COMPRESSOR);
        SCANNER.getGui().add(COMPRESSOR);
        WIRE_MILL.getGui().add(COMPRESSOR);
        CENTRIFUGE.getGui().add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34);
        ELECTROLYZER.getGui().add(CENTRIFUGE);
        THERMAL_CENTRIFUGE.getGui().add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25);
        ORE_WASHER.getGui().add(THERMAL_CENTRIFUGE).add(FL_IN, 53, 63).add(FL_OUT, 107, 63);
        CHEMICAL_REACTOR.getGui().add(CANNER);
        FLUID_CANNER.getGui().add(COMPRESSOR);
        DISASSEMBLER.getGui().add(ALLOY_SMELTER); //TODO
        MASS_FABRICATOR.getGui().add(COMPRESSOR);
        AMP_FABRICATOR.getGui().add(COMPRESSOR);
        REPLICATOR.getGui().add(COMPRESSOR);
        FERMENTER.getGui().add(COMPRESSOR);
        FLUID_EXTRACTOR.getGui().add(COMPRESSOR);
        FLUID_SOLIDIFIER.getGui().add(COMPRESSOR).add(FL_IN, 53, 63);
        DISTILLERY.getGui().add(COMPRESSOR);
        CHEMICAL_BATH.getGui().add(THERMAL_CENTRIFUGE);
        AUTOCLAVE.getGui().add(COMPRESSOR);
        MIXER.getGui().add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 25);
        LASER_ENGRAVER.getGui().add(ALLOY_SMELTER);
        FORMING_PRESS.getGui().add(ALLOY_SMELTER);
        FORGE_HAMMER.getGui().add(FURNACE);
        SIFTER.getGui().add(DISASSEMBLER);
        ARC_FURNACE.getGui().add(ALLOY_SMELTER); //TODO
        PLASMA_ARC_FURNACE.getGui().add(ARC_FURNACE);

        COAL_BOILER.getGui().add(ALLOY_SMELTER); //TODO
        LAVA_BOILER.getGui().add(ALLOY_SMELTER);
        SOLAR_BOILER.getGui().add(ALLOY_SMELTER);

        STEAM_ALLOY_SMELTER.getGui().add(BRONZE, ALLOY_SMELTER).add(BRONZE, FL_IN, 53, 63).setInfoRenderer(STEAM_INFO_RENDERER);
        STEAM_ALLOY_SMELTER.getGui().add(STEEL, ALLOY_SMELTER).add(STEEL, FL_IN, 53, 63).setInfoRenderer(STEAM_INFO_RENDERER);
        STEAM_COMPRESSOR.getGui().add(BRONZE, COMPRESSOR).add(BRONZE, FL_IN, 53, 63).setInfoRenderer(STEAM_INFO_RENDERER);
        STEAM_COMPRESSOR.getGui().add(STEEL, COMPRESSOR).add(STEEL, FL_IN, 53, 63).setInfoRenderer(STEAM_INFO_RENDERER);
        STEAM_FURNACE.getGui().add(BRONZE, FURNACE).add(BRONZE, FL_IN, 53, 63).setInfoRenderer(STEAM_INFO_RENDERER);
        STEAM_FURNACE.getGui().add(STEEL, FURNACE).add(STEEL, FL_IN, 53, 63).setInfoRenderer(STEAM_INFO_RENDERER);
        STEAM_EXTRACTOR.getGui().add(BRONZE, EXTRACTOR).add(BRONZE, FL_IN, 53, 63).setInfoRenderer(STEAM_INFO_RENDERER);
        STEAM_EXTRACTOR.getGui().add(STEEL, EXTRACTOR).add(STEEL, FL_IN, 53, 63).setInfoRenderer(STEAM_INFO_RENDERER);
        STEAM_PULVERIZER.getGui().add(BRONZE, PULVERIZER).add(BRONZE, FL_IN, 53, 63).setInfoRenderer(STEAM_INFO_RENDERER);
        STEAM_PULVERIZER.getGui().add(STEEL, PULVERIZER).add(STEEL, FL_IN, 53, 63).setInfoRenderer(STEAM_INFO_RENDERER);
        STEAM_FORGE_HAMMER.getGui().add(BRONZE, FORGE_HAMMER).add(BRONZE, FL_IN, 53, 63).setInfoRenderer(STEAM_INFO_RENDERER);
        STEAM_FORGE_HAMMER.getGui().add(STEEL, FORGE_HAMMER).add(STEEL, FL_IN, 53, 63).setInfoRenderer(STEAM_INFO_RENDERER);

        STEAM_GENERATOR.getGui().add(BASIC_TANK);
        GAS_GENERATOR.getGui().add(BASIC_TANK);
        COMBUSTION_ENGINE.getGui().add(BASIC_TANK);
        NAQUADAH_GENERATOR.getGui().add(BASIC_TANK);
        PLASMA_GENERATOR.getGui().add(BASIC_TANK);

        QUANTUM_TANK.getGui().add(BASIC_TANK);

        PRIMITIVE_BLAST_FURNACE.getGui().add(IT_IN, 53, 16).add(IT_IN, 53, 34).add(IT_IN, 53, 52).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).setPadding(0, 0, 0, 0);
        BRONZE_BLAST_FURNACE.getGui().add(IT_IN, 53, 16).add(IT_IN, 53, 34).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25);
        FUSION_REACTOR.getGui().setEnablePlayerSlots(false);

        HATCH_MUFFLER.getGui().add(IT_IN, 79, 34);

        HATCH_ITEM_I.getGui().add(ULV, IT_IN, 79, 34);
        HATCH_ITEM_I.getGui().add(LV, IT_IN, 70, 25).add(LV, IT_IN, 88, 25).add(LV, IT_IN, 70, 43).add(LV, IT_IN, 88, 43);
        HATCH_ITEM_I.getGui().add(MV, IT_IN, 61, 16).add(MV, IT_IN, 79, 16).add(MV, IT_IN, 97, 16).add(MV, IT_IN, 61, 34).add(MV, IT_IN, 79, 34).add(MV, IT_IN, 97, 34).add(MV, IT_IN, 61, 52).add(MV, IT_IN, 79, 52).add(MV, IT_IN, 97, 52);
        HATCH_ITEM_I.getGui().add(HV, IT_IN, 52, 7).add(HV, IT_IN, 70, 7).add(HV, IT_IN, 88, 7).add(HV, IT_IN, 106, 7).add(HV, IT_IN, 52, 25).add(HV, IT_IN, 70, 25).add(HV, IT_IN, 88, 25).add(HV, IT_IN, 106, 25).add(HV, IT_IN, 52, 43).add(HV, IT_IN, 70, 43).add(HV, IT_IN, 88, 43).add(HV, IT_IN, 106, 43).add(HV, IT_IN, 52, 61).add(HV, IT_IN, 70, 61).add(HV, IT_IN, 88, 61).add(HV, IT_IN, 106, 61);
        HATCH_ITEM_I.getGui().add(EV, HATCH_ITEM_I, HV);
        HATCH_ITEM_I.getGui().add(IV, HATCH_ITEM_I, HV);
        HATCH_ITEM_I.getGui().add(LUV, HATCH_ITEM_I, HV);
        HATCH_ITEM_I.getGui().add(ZPM, HATCH_ITEM_I, HV);
        HATCH_ITEM_I.getGui().add(UV, HATCH_ITEM_I, HV);
        HATCH_ITEM_I.getGui().add(MAX, HATCH_ITEM_I, HV);

        HATCH_ITEM_O.getGui().add(ULV, IT_OUT, 79, 34);
        HATCH_ITEM_O.getGui().add(LV, IT_OUT, 70, 25).add(LV, IT_OUT, 88, 25).add(LV, IT_OUT, 70, 43).add(LV, IT_OUT, 88, 43);
        HATCH_ITEM_O.getGui().add(MV, IT_OUT, 61, 16).add(MV, IT_OUT, 79, 16).add(MV, IT_OUT, 97, 16).add(MV, IT_OUT, 61, 34).add(MV, IT_OUT, 79, 34).add(MV, IT_OUT, 97, 34).add(MV, IT_OUT, 61, 52).add(MV, IT_OUT, 79, 52).add(MV, IT_OUT, 97, 52);
        HATCH_ITEM_O.getGui().add(HV, IT_OUT, 52, 7).add(HV, IT_OUT, 70, 7).add(HV, IT_OUT, 88, 7).add(HV, IT_OUT, 106, 7).add(HV, IT_OUT, 52, 25).add(HV, IT_OUT, 70, 25).add(HV, IT_OUT, 88, 25).add(HV, IT_OUT, 106, 25).add(HV, IT_OUT, 52, 43).add(HV, IT_OUT, 70, 43).add(HV, IT_OUT, 88, 43).add(HV, IT_OUT, 106, 43).add(HV, IT_OUT, 52, 61).add(HV, IT_OUT, 70, 61).add(HV, IT_OUT, 88, 61).add(HV, IT_OUT, 106, 61);
        HATCH_ITEM_O.getGui().add(EV, HATCH_ITEM_O, HV);
        HATCH_ITEM_O.getGui().add(IV, HATCH_ITEM_O, HV);
        HATCH_ITEM_O.getGui().add(LUV, HATCH_ITEM_O, HV);
        HATCH_ITEM_O.getGui().add(ZPM, HATCH_ITEM_O, HV);
        HATCH_ITEM_O.getGui().add(UV, HATCH_ITEM_O, HV);
        HATCH_ITEM_O.getGui().add(MAX, HATCH_ITEM_O, HV);

        HATCH_FLUID_I.getGui().add(FL_IN, 79, 34);
        HATCH_FLUID_I.getGui().add(LV, HATCH_FLUID_I, ULV);
        HATCH_FLUID_I.getGui().add(MV, HATCH_FLUID_I, ULV);
        HATCH_FLUID_I.getGui().add(HV, HATCH_FLUID_I, ULV);
        HATCH_FLUID_I.getGui().add(EV, FL_IN, 61, 16).add(EV, FL_IN, 79, 16).add(EV, FL_IN, 97, 16).add(EV, FL_IN, 61, 34).add(EV, FL_IN, 79, 34).add(EV, FL_IN, 97, 34).add(EV, FL_IN, 61, 52).add(EV, FL_IN, 79, 52).add(EV, FL_IN, 97, 52);
        HATCH_FLUID_I.getGui().add(IV, HATCH_FLUID_I, ULV);
        HATCH_FLUID_I.getGui().add(LUV, HATCH_FLUID_I, ULV);
        HATCH_FLUID_I.getGui().add(ZPM, HATCH_FLUID_I, ULV);
        HATCH_FLUID_I.getGui().add(UV, HATCH_FLUID_I, ULV);
        HATCH_FLUID_I.getGui().add(MAX, HATCH_FLUID_I, ULV);

        HATCH_FLUID_O.getGui().add(FL_OUT, 79, 34);
        HATCH_FLUID_O.getGui().add(LV, HATCH_FLUID_O, ULV);
        HATCH_FLUID_O.getGui().add(MV, HATCH_FLUID_O, ULV);
        HATCH_FLUID_O.getGui().add(HV, HATCH_FLUID_O, ULV);
        HATCH_FLUID_O.getGui().add(EV, HATCH_FLUID_O, ULV);
        HATCH_FLUID_O.getGui().add(IV, HATCH_FLUID_O, ULV);
        HATCH_FLUID_O.getGui().add(LUV, HATCH_FLUID_O, ULV);
        HATCH_FLUID_O.getGui().add(ZPM, HATCH_FLUID_O, ULV);
        HATCH_FLUID_O.getGui().add(UV, FL_OUT, 61, 16).add(UV, FL_OUT, 79, 16).add(UV, FL_OUT, 97, 16).add(UV, FL_OUT, 61, 34).add(UV, FL_OUT, 79, 34).add(UV, FL_OUT, 97, 34).add(UV, FL_OUT, 61, 52).add(UV, FL_OUT, 79, 52).add(UV, FL_OUT, 97, 52);
        HATCH_FLUID_O.getGui().add(MAX, HATCH_FLUID_O, ULV);
    }
}
