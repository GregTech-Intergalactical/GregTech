package muramasa.gregtech.data;

import com.google.common.collect.ImmutableMap;

import io.github.gregtechintergalactical.gtutility.GTUtilityData;
import io.github.gregtechintergalactical.gtutility.machine.DrumMachine;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.*;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.tile.single.TileEntityBatteryBuffer;
import muramasa.antimatter.tile.single.TileEntityDigitalTransformer;
import muramasa.antimatter.tile.single.TileEntityTransformer;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.machine.SteamMachine;
import muramasa.gregtech.machine.maps.DisassemblingMap;
import muramasa.gregtech.nuclear.TileEntityNuclearReactor;
import muramasa.gregtech.tile.multi.*;
import muramasa.gregtech.tile.single.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.machine.MachineFlag.*;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gregtech.data.GregTechData.COVER_STEAM_VENT;
import static muramasa.gregtech.data.RecipeMaps.*;

public class Machines {
    /**
     ** Steam Singleblock Machines
     **/
    public static SteamMachine COAL_BOILER = new SteamMachine(GTIRef.ID, "coal_boiler").setTiers(BRONZE, STEEL).setMap(COAL_BOILERS).addFlags(GUI, STEAM, ITEM, FLUID, CELL).baseTexture(Textures.BOILER_HANDLER).setTile(TileEntityCoalBoiler::new).noCovers();
    public static SteamMachine LAVA_BOILER = new SteamMachine(GTIRef.ID, "lava_boiler").setTiers(STEEL).addFlags(GUI, STEAM, ITEM, FLUID).setTile(TileEntityLavaBoiler::new).noCovers();
    public static SteamMachine SOLAR_BOILER = new SteamMachine(GTIRef.ID, "solar_boiler").setTiers(BRONZE).addFlags(GUI, STEAM, ITEM, FLUID).setTile(TileEntitySolarBoiler::new).allowFrontIO().noCovers();
    public static SteamMachine STEAM_ALLOY_SMELTER = new SteamMachine(GTIRef.ID, "steam_alloy_smelter").setTiers(BRONZE, STEEL).setMap(STEAM_ALLOY_SMELTING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.FURNACE,  0.6f).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_COMPRESSOR = new SteamMachine(GTIRef.ID, "steam_compressor").setTiers(BRONZE, STEEL).setMap(STEAM_COMPRESSING).addFlags(GUI, ITEM, FLUID).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_EXTRACTOR = new SteamMachine(GTIRef.ID, "steam_extractor").setTiers(BRONZE, STEEL).setMap(STEAM_EXTRACTING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.EXTRACTOR,  0.6f).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_FORGE_HAMMER = new SteamMachine(GTIRef.ID, "steam_forge_hammer").setTiers(BRONZE, STEEL).setMap(STEAM_HAMMERING).addFlags(GUI, ITEM, FLUID).covers(COVER_STEAM_VENT).setSound(SoundEvents.ANVIL_PLACE, 0.6f);
    public static SteamMachine STEAM_FURNACE = new SteamMachine(GTIRef.ID, "steam_furnace").setTiers(BRONZE, STEEL).setMap(STEAM_SMELTING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.FURNACE,  0.6f).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_MACERATOR = new SteamMachine(GTIRef.ID, "steam_macerator").setTiers(BRONZE, STEEL).setMap(STEAM_MACERATING).addFlags(GUI, ITEM, FLUID).covers(COVER_STEAM_VENT).setSound(GregTechSounds.MACERATOR,  0.6f);
    public static SteamMachine STEAM_SIFTER = new SteamMachine(GTIRef.ID, "steam_sifter").setTiers(BRONZE, STEEL).setMap(STEAM_SIFTING).addFlags(GUI, ITEM, FLUID).covers(COVER_STEAM_VENT);
    /**
     ** Hatchless Multiblock Machines (Steam Age)
     **/
    public static BasicMachine CHARCOAL_PIT = new BasicMachine(GTIRef.ID, "charcoal_pit").setTiers(NONE).baseTexture(new Texture(GTIRef.ID, "block/machine/base/charcoal_pit")).setTile(TileEntityCharcoalPit::new).noCovers().setAmbientTicking();
    public static BasicMultiMachine<?> COKE_OVEN = new BasicMultiMachine<>(GTIRef.ID, "coke_oven").setTiers(NONE).setMap(COKING).addFlags(GUI, ITEM, FLUID).setTile(TileEntityCokeOven::new);
    public static BasicMultiMachine<?> PRIMITIVE_BLAST_FURNACE = new BasicMultiMachine<>(GTIRef.ID, "primitive_blast_furnace").setTiers(NONE).setMap(BASIC_BLASTING).addFlags(GUI, ITEM).setTile(TileEntityPrimitiveBlastFurnace::new);
    /**
     ** Electric Singleblock Machines
     **/
    /**
     * Processors
     **/
    public static BasicMachine ALLOY_SMELTER = new BasicMachine(GTIRef.ID, "alloy_smelter").setMap(ALLOY_SMELTING).addFlags(GUI, ITEM).setSound(GregTechSounds.FURNACE,  0.6f);
    public static BasicMachine AMP_FABRICATOR = new BasicMachine(GTIRef.ID, "amp_fabricator").setMap(AMP_FABRICATING).addFlags(GUI, ITEM);
    public static BasicMachine ARC_FURNACE = new BasicMachine(GTIRef.ID, "arc_furnace").setMap(ARC_SMELTING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.FURNACE,  0.6f).amps(3);
    public static BasicMachine ASSEMBLER = new BasicMachine(GTIRef.ID, "assembler").setMap(ASSEMBLING).addFlags(GUI, ITEM, FLUID).setAllowVerticalFacing(true).custom();
    public static BasicMachine AUTOCLAVE = new BasicMachine(GTIRef.ID, "autoclave").setMap(AUTOCLAVING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine BENDER = new BasicMachine(GTIRef.ID, "bender").setMap(BENDING).addFlags(GUI, ITEM);
    public static BasicMachine CANNER = new BasicMachine(GTIRef.ID, "canner").setMap(CANNING).addFlags(GUI, ITEM);
    public static BasicMachine CENTRIFUGE = new BasicMachine(GTIRef.ID, "centrifuge").setMap(CENTRIFUGING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine CHEMICAL_BATH = new BasicMachine(GTIRef.ID, "chemical_bath").setMap(CHEMICAL_BATHING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.EXTRACTOR, 0.6f);
    public static BasicMachine CHEMICAL_DEHYDRATOR = new BasicMachine(GTIRef.ID, "chemical_dehydrator").setMap(DEHYDRATING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine CHEMICAL_REACTOR = new BasicMachine(GTIRef.ID, "chemical_reactor").setMap(CHEMICAL_REACTING).addFlags(GUI, ITEM, FLUID).renderContainedLiquids().custom();
    public static BasicMachine COMPRESSOR = new BasicMachine(GTIRef.ID, "compressor").setMap(COMPRESSING).addFlags(GUI, ITEM);
    public static BasicMachine CUTTER = new BasicMachine(GTIRef.ID, "cutter").setMap(CUTTING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine DECAY_CHAMBER = new BasicMachine(GTIRef.ID, "decay_chamber").setMap(DECAYING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine DISASSEMBLER = new BasicMachine(GTIRef.ID, "disassembler").setMap(new DisassemblingMap()).addFlags(GUI, ITEM).custom();
    public static BasicMachine DISTILLERY = new BasicMachine(GTIRef.ID, "distillery").setMap(DISTILLING).addFlags(GUI, ITEM, FLUID).custom().renderContainedLiquids().setAllowVerticalFacing(true).setSound(GregTechSounds.EXTRACTOR,  0.6f);
    public static BasicMachine ELECTROLYZER = new BasicMachine(GTIRef.ID, "electrolyzer").setMap(ELECTROLYZING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.MAGNETIZER, 0.6f);
    public static BasicMachine ELECTROMAGNETIC_SEPARATOR = new BasicMachine(GTIRef.ID, "electromagnetic_separator").setMap(ELECTROMAGNETIC_SEPARATING).addFlags(GUI, ITEM);
    public static BasicMachine EXTRACTOR = new BasicMachine(GTIRef.ID, "extractor").setMap(EXTRACTING).addFlags(GUI, ITEM).setSound(GregTechSounds.EXTRACTOR,  0.6f);
    public static BasicMachine EXTRUDER = new BasicMachine(GTIRef.ID, "extruder").setMap(EXTRUDING).addFlags(GUI, ITEM).custom();
    public static BasicMachine FERMENTER = new BasicMachine(GTIRef.ID, "fermenter").setMap(FERMENTING).addFlags(GUI, ITEM, FLUID).custom().renderContainedLiquids();
    public static BasicMachine FLUID_CANNER = new BasicMachine(GTIRef.ID, "fluid_canner").setMap(FLUID_CANNING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.EXTRACTOR,  0.6f);
    public static BasicMachine FLUID_EXTRACTOR = new BasicMachine(GTIRef.ID, "fluid_extractor").setMap(FLUID_EXTRACTING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FLUID_HEATER = new BasicMachine(GTIRef.ID, "fluid_heater").setMap(FLUID_HEATING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FLUID_SOLIDIFIER = new BasicMachine(GTIRef.ID, "fluid_solidifier").setMap(FLUID_SOLIDIFYING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.EXTRACTOR,  0.6f);
    public static BasicMachine FORGE_HAMMER = new BasicMachine(GTIRef.ID, "forge_hammer").setMap(HAMMERING).addFlags(GUI, ITEM).setSound(SoundEvents.ANVIL_PLACE, 0.6f);
    public static BasicMachine FORMING_PRESS = new BasicMachine(GTIRef.ID, "forming_press").setMap(PRESSING).addFlags(GUI, ITEM);
    public static BasicMachine FURNACE = new BasicMachine(GTIRef.ID, "furnace").setMap(SMELTING).addFlags(GUI, ITEM).setSound(GregTechSounds.FURNACE,  0.6f);
    public static BasicMachine LASER_ENGRAVER = new BasicMachine(GTIRef.ID, "laser_engraver").setMap(LASER_ENGRAVING).addFlags(GUI, ITEM).setSound(GregTechSounds.MAGNETIZER,  0.6f);
    public static BasicMachine LATHE = new BasicMachine(GTIRef.ID, "lathe").setMap(LATHING).addFlags(GUI, ITEM);
    public static BasicMachine MACERATOR = new BasicMachine(GTIRef.ID, "macerator").setMap(MACERATING).custom().addFlags(GUI, ITEM).setGuiTiers(ImmutableMap.<Tier, Tier>builder().put(HV, HV).put(EV, EV).put(IV, IV)).setSound(GregTechSounds.MACERATOR,  0.6f);
    public static BasicMachine MASS_FABRICATOR = new BasicMachine(GTIRef.ID, "mass_fabricator").setMap(MASS_FABRICATING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine MIXER = new BasicMachine(GTIRef.ID, "mixer").setMap(MIXING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine ORE_WASHER = new BasicMachine(GTIRef.ID, "ore_washer").setMap(ORE_WASHING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine PACKAGER = new BasicMachine(GTIRef.ID, "packager").setMap(PACKAGING).addFlags(GUI, ITEM);
    public static BasicMachine POLARIZER = new BasicMachine(GTIRef.ID, "polarizer").setMap(POLARIZING).addFlags(GUI, ITEM);
    public static BasicMachine PLASMA_ARC_FURNACE = new BasicMachine(GTIRef.ID, "plasma_arc_furnace").setMap(PLASMA_ARC_SMELTING).addFlags(GUI, ITEM, FLUID).amps(3);
    public static BasicMachine ROASTER = new BasicMachine(GTIRef.ID, "roaster").setMap(ROASTING).addFlags(GUI, ITEM, FLUID).amps(3);
    public static BasicMachine RECYCLER = new BasicMachine(GTIRef.ID, "recycler").setMap(RECYCLING).addFlags(GUI, ITEM);
    public static BasicMachine REPLICATOR = new BasicMachine(GTIRef.ID, "replicator").setMap(REPLICATING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine SCANNER = new BasicMachine(GTIRef.ID, "scanner").setMap(SCANNING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.MAGNETIZER,  0.6f);
    public static BasicMachine SIFTER = new BasicMachine(GTIRef.ID, "sifter").setMap(SIFTING).addFlags(GUI, ITEM);
    public static BasicMachine THERMAL_CENTRIFUGE = new BasicMachine(GTIRef.ID, "thermal_centrifuge").setMap(THERMAL_CENTRIFUGING).addFlags(GUI,ITEM).amps(2);
    public static BasicMachine WIRE_MILL = new BasicMachine(GTIRef.ID, "wire_mill").setMap(WIRE_MILLING).addFlags(GUI, ITEM).custom();
    /**
     * Battery Buffers
     **/
    public static BasicMachine BATTERY_BUFFER_SIXTEEN = new BasicMachine(GTIRef.ID, "16x_battery_buffer").addFlags(GUI, ENERGY, ITEM).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).noCovers().setTile(TileEntityBatteryBuffer::new).setAllowVerticalFacing(true).allowFrontIO();
    public static BasicMachine BATTERY_BUFFER_EIGHT = new BasicMachine(GTIRef.ID, "8x_battery_buffer").addFlags(GUI, ENERGY, ITEM).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).noCovers().setTile(TileEntityBatteryBuffer::new).setAllowVerticalFacing(true).allowFrontIO();
    public static BasicMachine BATTERY_BUFFER_FOUR = new BasicMachine(GTIRef.ID, "4x_battery_buffer").addFlags(GUI, ENERGY, ITEM).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).noCovers().setTile(TileEntityBatteryBuffer::new).setAllowVerticalFacing(true).allowFrontIO();
    public static BasicMachine BATTERY_BUFFER_ONE = new BasicMachine(GTIRef.ID, "1x_battery_buffer").addFlags(GUI, ENERGY, ITEM).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).noCovers().setTile(TileEntityBatteryBuffer::new).setAllowVerticalFacing(true).allowFrontIO();
    /**
     * Filters
     **/
    public static BasicMachine ELECTRIC_ITEM_FILTER = null;
    public static BasicMachine ELECTRIC_TYPE_FILTER = null;
    /**
     * Drums
     */
    public static DrumMachine BRONZE_DRUM = GTUtilityData.createDrum(Materials.Bronze, 16000);
    public static DrumMachine INVAR_DRUM = GTUtilityData.createDrum(Materials.Invar, 32000);
    public static DrumMachine STAINLESS_DRUM = GTUtilityData.createDrum(Materials.StainlessSteel, 64000);
    public static DrumMachine TITANIUM_DRUM = GTUtilityData.createDrum(Materials.Titanium, 128000);
    public static DrumMachine NETHERRITE_DRUM = GTUtilityData.createDrum(AntimatterMaterials.Netherite, 128000);
    public static DrumMachine TUNGSTENSTEEL_DRUM = GTUtilityData.createDrum(Materials.TungstenSteel, 256000);
    public static DrumMachine TUNGSTEN_DRUM = GTUtilityData.createDrum(Materials.Tungsten, 256000);

    /**
     * Transformers
     **/
    public static BasicMachine TRANSFORMER = new BasicMachine(GTIRef.ID, "transformer").addFlags(ENERGY).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).setTile((v, pos, state) -> new TileEntityTransformer<>(v, pos, state, 1)).noCovers().allowFrontIO().setAllowVerticalFacing(true).setTooltipInfo((machine, stack, world, tooltip, flag) -> {
        tooltip.remove(tooltip.size() - 1);
        tooltip.remove(tooltip.size() - 1);
        Tier upper = Tier.getTier(machine.getTier().getVoltage() * 4);
        tooltip.add(new TranslatableComponent("machine.transformer.voltage_info", new TextComponent(upper.getId().toUpperCase()), new TextComponent(machine.getTier().getId().toUpperCase())));
        tooltip.add(new TranslatableComponent("machine.voltage.in").append(": ").append(new TextComponent(upper.getVoltage() + " (" + upper.getId().toUpperCase() + ")")).withStyle(ChatFormatting.GREEN));
        tooltip.add(new TranslatableComponent("machine.voltage.out").append(": ").append(new TextComponent(machine.getTier().getVoltage() + " (" + machine.getTier().getId().toUpperCase() + ")")).withStyle(ChatFormatting.GREEN));
        tooltip.add(new TranslatableComponent("generic.amp").append(": ").append(new TextComponent(String.valueOf(4)).withStyle(ChatFormatting.YELLOW)));
        tooltip.add(new TranslatableComponent("machine.power.capacity").append(": ").append(new TextComponent(String.valueOf(512L + machine.getTier().getVoltage() * 8L))).withStyle(ChatFormatting.BLUE));
    });
    public static BasicMachine ADJUSTABLE_TRANSFORMER = new BasicMachine(GTIRef.ID, "adjustable_transformer").setTiers(EV, IV).addFlags(GUI, ENERGY).setTile(TileEntityDigitalTransformer::new).noCovers().allowFrontIO();
    //public static BasicMachine TRANSFORMER_HIAMP = new BasicMachine(GTIRef.ID, "transformer_hiamp").addFlags(ENERGY).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).setTile((v, pos, state) -> new TileEntityTransformer<>(v, pos, state, 4)).noCovers().allowFrontIO();
    //public static BasicMachine TRANSFORMER_ULTRA = new BasicMachine(GTIRef.ID, "transformer_ultra").addFlags(ENERGY).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).setTile((v, pos, state) -> new TileEntityTransformer<>(v, pos, state, 16)).noCovers().allowFrontIO();
    /**
     ** Multiblock Hatch Machines (Electrical Age)
     **/
    public static MultiMachine ADVANCED_MINER = new MultiMachine(GTIRef.ID, "advanced_miner").setTiers(LV).addFlags(GUI, ITEM, ENERGY).setTile(TileEntityAdvancedMiner::new);
    public static MultiMachine BLAST_FURNACE = new MultiMachine(GTIRef.ID, "electric_blast_furnace").setTiers(LV).setMap(BLASTING).addFlags(GUI, ITEM, FLUID, ENERGY).setTile(TileEntityElectricBlastFurnace::new).custom();
    public static MultiMachine COMBUSTION_ENGINE = new MultiMachine(GTIRef.ID, "combustion_engine").setTiers(EV).setMap(COMBUSTION_FUELS).addFlags(GUI, FLUID, ENERGY).setTile(TileEntityCombustionEngine::new).custom();
    public static MultiMachine CRACKING_UNIT = new MultiMachine(GTIRef.ID, "cracking_unit").setTiers(HV).setMap(CRACKING).addFlags(GUI, ITEM, FLUID, ENERGY).setTile(TileEntityOilCrackingUnit::new).custom();
    public static MultiMachine DISTLLATION_TOWER = new MultiMachine(GTIRef.ID, "distillation_tower").setTiers(HV).setMap(DISTILLATION).addFlags(GUI, ITEM, FLUID,ENERGY).setTile(TileEntityDistillationTower::new).custom();
    public static MultiMachine FUSION_REACTOR = new MultiMachine(GTIRef.ID, "fusion_control_computer").setTiers(LUV, ZPM, UV).setMap(FUSION).addFlags(GUI, FLUID,ENERGY).setTile(TileEntityFusionReactor::new);
    public static MultiMachine HEAT_EXCHANGER = new MultiMachine(GTIRef.ID, "heat_exchanger").setTiers(EV).setMap(HEAT_EXCHANGING).addFlags(GUI, FLUID, ENERGY).setTile(TileEntityHeatExchanger::new).custom();
    public static MultiMachine IMPLOSION_COMPRESSOR = new MultiMachine(GTIRef.ID, "implosion_compressor").setTiers(HV).setMap(IMPLOSION_COMPRESSING).addFlags(GUI, ITEM, ENERGY).setTile(TileEntityImplosionCompressor::new);
    public static MultiMachine LARGE_BOILER = new MultiMachine(GTIRef.ID, "large_boiler").setTiers(LV, MV, HV, EV).addFlags(GUI, ITEM, FLUID).setMap(LARGE_BOILERS).setTile(TileEntityLargeBoiler::new).custom();
    public static MultiMachine LARGE_TURBINE = new MultiMachine(GTIRef.ID, "large_turbine").setTiers(HV, EV, IV, UV).setMap(STEAM_FUELS, HV).setMap(HP_STEAM_FUELS, IV).setMap(GAS_FUELS, EV, UV).addFlags(GUI, FLUID, ENERGY, GENERATOR).setTile(TileEntityLargeTurbine::new).custom(Textures.TURBINE);
    public static MultiMachine MULTI_SMELTER = new MultiMachine(GTIRef.ID, "multi_smelter").setTiers(HV).setMap(SMELTING).addFlags(GUI, ITEM, ENERGY).setTile(TileEntityMultiSmelter::new).custom();
    public static MultiMachine NUCLEAR_REACTOR = new MultiMachine(GTIRef.ID, "nuclear_reactor").setTiers(EV).setMap(NUCLEAR).addFlags(GUI, ITEM, FLUID, ENERGY).setTile(TileEntityNuclearReactor::new).custom();
    public static MultiMachine OIL_DRILLING_RIG = new MultiMachine(GTIRef.ID, "oil_drilling_rig").setTiers(MV, HV, EV, IV).addFlags(GUI, FLUID, ENERGY).setTile(TileEntityOilDrillingRig::new).custom();
    public static MultiMachine PYROLYSIS_OVEN = new MultiMachine(GTIRef.ID, "pyrolysis_oven").setTiers(MV).setMap(PYROLYSISING).addFlags(GUI, ITEM, FLUID, ENERGY).setTile(TileEntityPyrolysisOven::new).custom();
    public static MultiMachine VACUUM_FREEZER = new MultiMachine(GTIRef.ID, "vacuum_freezer").setTiers(HV).setMap(VACUUM_FREEZING).addFlags(GUI, ITEM, FLUID, ENERGY).setTile(TileEntityVacuumFreezer::new);
    /**
     ** Generators
     **/
    public static GeneratorMachine COMBUSTION_GENERATOR = new GeneratorMachine(GTIRef.ID, "combustion_generator").setTiers(LV, MV, HV).setMap(COMBUSTION_FUELS).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO().custom();
    public static GeneratorMachine SEMIFLUID_GENERATOR = new GeneratorMachine(GTIRef.ID, "semifluid_generator").setTiers(LV, MV, HV).setMap(SEMI_FUELS).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO().custom();
    public static GeneratorMachine GAS_GENERATOR = new GeneratorMachine(GTIRef.ID, "gas_turbine").setTiers(LV, MV, HV).setMap(GAS_FUELS).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO().custom();
    public static GeneratorMachine NAQUADAH_GENERATOR = new GeneratorMachine(GTIRef.ID, "naquadah_generator").setTiers(EV, IV, LUV).setMap(NAQUADAH_FUELS).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO();
    public static GeneratorMachine PLASMA_GENERATOR = new GeneratorMachine(GTIRef.ID, "plasma_generator").setTiers(IV, LUV, ZPM).setMap(PLASMA_FUELS).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO();
    public static GeneratorMachine STEAM_GENERATOR = new GeneratorMachine(GTIRef.ID, "steam_turbine").setTiers(LV, MV, HV).setMap(STEAM_FUELS).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO().custom();
    /**
     ** Hatches
     **/
    public static HatchMachine HATCH_DYNAMO = new HatchMachine(GTIRef.ID, "hatch_dynamo", COVERDYNAMO).addFlags(ENERGY);
    public static HatchMachine HATCH_ENERGY = new HatchMachine(GTIRef.ID, "hatch_energy", COVERENERGY).addFlags(ENERGY);
    public static HatchMachine HATCH_FLUID_I = new HatchMachine(GTIRef.ID, "hatch_fluid_input", COVERINPUT).addFlags(GUI, FLUID, CELL);
    public static HatchMachine HATCH_FLUID_O = new HatchMachine(GTIRef.ID, "hatch_fluid_output", COVEROUTPUT).addFlags(GUI, FLUID, CELL);
    //public static final HeatHatch HATCH_HEAT_COPPER = new HeatHatch(GTIRef.ID, "copper_heat", Copper, 386);
    public static HatchMachine HATCH_ITEM_I = new HatchMachine(GTIRef.ID, "hatch_item_input", COVERINPUT).addFlags(GUI, ITEM);
    public static HatchMachine HATCH_MUFFLER = new HatchMachine(GTIRef.ID, "hatch_muffler", COVERMUFFLER).addFlags(GUI, ITEM).setClientTick();
    public static HatchMachine HATCH_ITEM_O = new HatchMachine(GTIRef.ID, "hatch_item_output", COVEROUTPUT).addFlags(GUI, ITEM);
    /**
     ** Tanks
     **/
    public static TankMachine QUANTUM_TANK = new TankMachine(GTIRef.ID, "quantum_tank", t -> (int) (1602000 * Math.pow(6,  (t.getIntegerId() - 1)))).addFlags(BASIC, GUI, CELL).frontCovers();
    /**
     ** Creative Machines
     **/
    public static TankMachine INFINITE_STEAM = new TankMachine(GTIRef.ID, "infinite_steam").addFlags(FLUID, CELL, GUI).setTile(TileEntityInfiniteFluid::new).setTiers(LV);
    //public static BasicMachine INFINITE_STORAGE = new BasicMachine(GTIRef.ID, "infinite_storage").setTiers(ULV, LV, MV, HV, EV, IV, LUV, ZPM, UV, MAX).addFlags(ENERGY).setTile(TileEntityInfiniteStorage::new).noCovers().setAllowVerticalFacing(true).allowFrontIO();

    public static void init() {
        if (!AntimatterAPI.isModLoaded("gt4r")) {
            ELECTRIC_ITEM_FILTER = new BasicMachine(GTIRef.ID, "electric_item_filter").setTiers(LV).addFlags(GUI, ITEM).setTile(TileEntityItemFilter::new).noCovers().allowFrontIO().setAllowVerticalFacing(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);
            ELECTRIC_TYPE_FILTER = new BasicMachine(GTIRef.ID, "electric_type_filter").setTiers(LV).addFlags(GUI, ITEM).setTile(TileEntityTypeFilter::new).noCovers().allowFrontIO().setAllowVerticalFacing(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);
        }
    }
}
